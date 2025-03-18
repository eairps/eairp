import type { RouteItem } from '#/api/sys/model/menuModel';
import type {
  ComponentRecordType,
  GenerateMenuAndRoutesOptions,
} from '@vben/types';

import { getMenuListByRole } from '#/api/sys/menu';
import { BasicLayout, IFrame } from '#/layouts';
import { $t } from '#/locales';
import { useAuthStore } from '#/store';
import { array2tree } from '@axolo/tree-array';
import { generateAccessible } from '@vben/access';
import { preferences } from '@vben/preferences';
import { message } from 'ant-design-vue';

const forbiddenComponent = () => import('#/views/_core/fallback/forbidden.vue');

async function generateAccess(options: GenerateMenuAndRoutesOptions) {
  const pageMap: ComponentRecordType = import.meta.glob('../views/**/*.vue');

  const layoutMap: ComponentRecordType = {
    BasicLayout,
    IFrame,
  };

  return await generateAccessible(preferences.app.accessMode, {
    ...options,
    fetchMenuListAsync: async () => {
      message.loading({
        content: `${$t('common.loadingMenu')}...`,
        duration: 1.5,
      });
      const menuData = await getMenuListByRole();

      const authStore = useAuthStore();

      authStore.elementPermissionList = [];
      menuData.data.data.forEach((val, _idx, _arr) => {
        if (val.component === 'LAYOUT') {
          val.component = '';
        }

        val.meta.hideInMenu = val.meta.hideMenu as any;
        val.meta.hideInTab = val.meta.hideTab as any;
        val.meta.hideInBreadcrumb = val.meta.hideBreadcrumb as any;
        val.meta.keepAlive = !val.meta.ignoreKeepAlive as boolean;
        val.meta.maxNumOfOpenTab = val.meta.dynamicLevel as any;
        val.meta.affixTab = val.meta.affix as any;

        if (val.permission && val.permission !== '') {
          authStore.elementPermissionList.push(val.permission);
        }
      });

      const treeData: RouteItem[] = array2tree(
        menuData.data.data.filter((val) => val.path !== ''),
      ) as RouteItem[];
      treeData.forEach((val, idx, arr) => {
        if (val.component === '' && arr[idx]) {
          arr[idx].component = 'BasicLayout';
        }
      });
      return treeData;
    },
    // 可以指定没有权限跳转403页面
    forbiddenComponent,
    // 如果 route.meta.menuVisibleWithForbidden = true
    layoutMap,
    pageMap,
  });
}

export { generateAccess };
