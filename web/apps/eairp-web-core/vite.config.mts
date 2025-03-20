import { defineConfig } from '@vben/vite-config';

export default defineConfig(async () => {
  return {
    application: {},
    vite: {
      server: {
        proxy: {
          '/erp-api': {
            changeOrigin: true,
            rewrite: (path) => path.replace(/^\/erp-api/, ''),
            target: 'http://localhost:8088/erp-api',
            ws: true,
          },
        },
      },
    },
  };
});
