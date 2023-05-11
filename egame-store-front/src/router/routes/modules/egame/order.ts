import type { AppRouteModule } from '/@/router/types';

import { LAYOUT } from '/@/router/constant';

const permission: AppRouteModule = {
  path: '/order',
  name: 'order',
  component: LAYOUT,
  redirect: '/order/index',
  meta: {
    orderNo: 3,
    icon: 'ion:ios-paper',
    title: '订单',
  },

  children: [
    {
      path: 'index',
      name: 'index',
      component: () => import('/@/views/egame/order.vue'),
      meta: {
        title: '订单',
      },
    },
  ],
};

export default permission;
