<template>
  <div class="p-4">
    <BasicTable @register="register">
      <template #toolbar>
        <a-button type="primary" @click="edit"> 添加游戏机资产 </a-button>
      </template>
      <template #action="{ record }">
        <TableAction
          :actions="[
            {
              label: '删除',
              icon: 'ic:outline-delete-outline',
              onClick: handleDelete.bind(null, record),
            },
            {
              label: '修改',
              icon: 'ic:baseline-edit',
              onClick: edit.bind(null, record),
            },
          ]"
        />
      </template>
    </BasicTable>
    <AddAssetConsole @register="modalregister" />
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getAssetConsoleList } from '/@/api/egame/egame';
  import { useModal } from '/@/components/Modal';
  import AddAssetConsole from './form/AddAssetConsole.vue';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { defHttp } from '/@/utils/http/axios';

  function test() {
    console.log('test');
  }

  export default defineComponent({
    components: { BasicTable, TableAction, AddAssetConsole },
    setup() {
      const { createConfirm } = useMessage();
      const [modalregister, { openModal: openModal }] = useModal();
      const [register, methods] = useTable({
        title: '游戏机资产列表',
        api: getAssetConsoleList,
        columns: [
          {
            title: 'ID',
            dataIndex: 'id',
            fixed: 'left',
            width: 100,
          },
          {
            title: '游戏机类型',
            dataIndex: 'gameConsole',
            width: 200,
          },
          {
            title: '购入价格',
            dataIndex: 'price',
            width: 150,
          },
          {
            title: '入库时间',
            dataIndex: 'storageTime',
            width: 250,
          },
          {
            title: '单价（小时）',
            dataIndex: 'unitPrice',
            width: 150,
          },
          {
            title: '租借状态',
            dataIndex: 'status',
            width: 150,
          },
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
        handleDelete: (record) => {
          console.log(record);
          createConfirm({
            iconType: 'warning',
            title: '删除',
            content: '确定删除？',
            onOk: () => {
              defHttp
                .post({
                  url: '/assets/console/del',
                  data: {
                    id: record.id,
                  },
                  headers: {
                    'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
                  },
                })
                .then(() => {
                  methods.reload();
                });
            },
          });
        },
      };
    },
  });
</script>
