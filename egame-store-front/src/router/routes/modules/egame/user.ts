import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const permission: AppRouteModule = {
  path: '/user',
  name: 'user',
  component: LAYOUT,
  redirect: '/user/index',
  meta: {
    orderNo: 4,
    icon: 'ion:person-stalker',
    title: '用户',
  },

  children: [
    {
      path: 'index',
      name: 'userindex',
      component: () => import('/@/views/egame/user.vue'),
      meta: {
        title: '用户管理',
      },
    },
  ],
};

export default permission;
