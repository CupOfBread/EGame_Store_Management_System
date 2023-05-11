<template>
  <div class="p-4">
    <BasicTable @register="register">
      <template #toolbar>
        <a-button type="primary" @click="edit"> 生成订单 </a-button>
      </template>
      <template #action="{ record }">
        <TableAction
          :actions="[
            // {
            //   label: '删除',
            //   icon: 'ic:outline-delete-outline',
            //   onClick: test.bind(null, record),
            // },
          ]"
        />
      </template>
    </BasicTable>
    <AddOrder @register="modalregister" />
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getOrderList } from '/@/api/egame/egame';
  import { useModal } from '/@/components/Modal';
  import AddOrder from './form/AddOrder.vue';
  // import { useMessage } from '/@/hooks/web/useMessage';
  // import { defHttp } from '/@/utils/http/axios';

  function test() {
    console.log('test');
  }

  export default defineComponent({
    components: { BasicTable, TableAction, AddOrder },
    setup() {
      // const { createConfirm } = useMessage();
      const [modalregister, { openModal: openModal }] = useModal();
      const [register] = useTable({
        title: '订单列表',
        api: getOrderList,
        columns: [
          { title: 'ID', dataIndex: 'id', fixed: 'left', width: 90 },
          { title: '客户手机', dataIndex: 'customerPhone', fixed: 'left', width: 200 },
          { title: '游戏机', dataIndex: 'consoleAsset', fixed: 'left', width: 150 },
          { title: '租用时间(分钟)', dataIndex: 'leaseTime', fixed: 'left', width: 100 },
          { title: '创建时间', dataIndex: 'createTime', fixed: 'left', width: 100 },
          { title: '金额', dataIndex: 'amount', fixed: 'left', width: 90 },
          { title: '是否支付', dataIndex: 'paid', fixed: 'left', width: 90 },
          { title: '完成情况', dataIndex: 'finished', fixed: 'left', width: 150 },
        ],
        pagination: { pageSize: 20 },
        showIndexColumn: false,
        canResize: false,
        actionColumn: {
          width: 200,
          title: '操作',
          dataIndex: 'action',
          slots: { customRender: 'action' },
        },
      });
      return {
        register,
        test,
        modalregister,
        edit: (record) => {
          console.log(record);
          openModal(true, record);
        },
      };
    },
  });
</script>
