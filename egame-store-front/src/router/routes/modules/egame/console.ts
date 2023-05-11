import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const permission: AppRouteModule = {
  path: '/gameconsole',
  name: 'gameconsole',
  component: LAYOUT,
  redirect: '/gameconsole/game',
  meta: {
    orderNo: 1,
    icon: 'ion:game-controller',
    title: '游戏&游戏机管理',
  },

  children: [
    {
      path: 'gameType',
      name: 'gameType',
      component: () => import('/@/views/egame/gameType.vue'),
      meta: {
        title: '游戏类型',
      },
    },
    {
      path: 'game',
      name: 'game',
      component: () => import('/@/views/egame/game.vue'),
      meta: {
        title: '游戏',
      },
    },
    {
      path: 'console',
      name: 'console',
      component: () => import('/@/views/egame/console.vue'),
      meta: {
        title: '游戏机',
      },
    },
  ],
};

export default permission;
