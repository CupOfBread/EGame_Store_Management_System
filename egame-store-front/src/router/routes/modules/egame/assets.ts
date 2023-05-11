import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const permission: AppRouteModule = {
  path: '/assets',
  name: 'assets',
  component: LAYOUT,
  redirect: '/assets/gameAssets',
  meta: {
    orderNo: 2,
    icon: 'ion:card-sharp',
    title: '资产',
  },

  children: [
    {
      path: 'game',
      name: 'gameAssets',
      component: () => import('/@/views/egame/assetGame.vue'),
      meta: {
        title: '游戏资产',
      },
    },
    {
      path: 'console',
      name: 'consoleAssets',
      component: () => import('/@/views/egame/assetConsole.vue'),
      meta: {
        title: '游戏机资产',
      },
    },
  ],
};

export default permission;
