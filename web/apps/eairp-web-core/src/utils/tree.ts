import type { Recordable } from '@vben/types';

import { array2tree } from '@axolo/tree-array';
import { type DataNode } from 'ant-design-vue/es/vc-tree/interface';
import { forEachObj, map, pick } from 'remeda';

export interface buildNodeOption {
  labelField: string;
  idKeyField: string;
  valueField: string;
  parentKeyField: string;
  defaultValue?: object | string;
  childrenKeyField: string;
}

export function buildDataNode(data: any, options: buildNodeOption): DataNode[] {
  const treeNodeData = map(data, (obj) => {
    const tmpData = pick(obj as any, [
      options.labelField,
      options.idKeyField,
      options.valueField,
      options.parentKeyField,
    ]);

    forEachObj(tmpData, (value, key) => {
      if (key === options.labelField) {
        tmpData.title = value;
        // eslint-disable-next-line @typescript-eslint/no-dynamic-delete
        delete tmpData[key];
      } else if (key === options.valueField) {
        tmpData.key = tmpData[key];
        if (key !== options.idKeyField && key !== options.parentKeyField) {
          // eslint-disable-next-line @typescript-eslint/no-dynamic-delete
          delete tmpData[key];
        }
      }
    });

    return tmpData;
  });

  const treeConv = array2tree(treeNodeData, {
    idKey: options.idKeyField,
    parentKey: options.parentKeyField,
    childrenKey: options.childrenKeyField,
  });

  // add default label
  if (options.defaultValue) {
    treeConv.push(options.defaultValue);
  }
  return treeConv as DataNode[];
}

// buildTreeNode returns treeData for tree select from data
export function buildTreeNode(
  data: any,
  options: buildNodeOption,
): Recordable<any>[] {
  const treeNodeData = map(data, (obj) => {
    const tmpData = pick(obj as any, [
      options.labelField,
      options.idKeyField,
      options.valueField,
      options.parentKeyField,
    ]);

    forEachObj(tmpData, (value, key) => {
      if (key === options.labelField) {
        tmpData.label = value;
        // eslint-disable-next-line @typescript-eslint/no-dynamic-delete
        delete tmpData[key];
      } else if (key === options.valueField) {
        tmpData.value = tmpData[key];
        if (key !== options.idKeyField && key !== options.parentKeyField) {
          // eslint-disable-next-line @typescript-eslint/no-dynamic-delete
          delete tmpData[key];
        }
      }
    });

    return tmpData;
  });

  const treeConv = array2tree(treeNodeData, {
    idKey: options.idKeyField,
    parentKey: options.parentKeyField,
    childrenKey: options.childrenKeyField,
  });

  // add default label
  if (options.defaultValue) {
    treeConv.push(options.defaultValue);
  }
  return treeConv as Recordable<any>[];
}
