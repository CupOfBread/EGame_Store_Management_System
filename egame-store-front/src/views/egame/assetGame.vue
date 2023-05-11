<template>
  <div class="p-4">
    <BasicTable @register="register">
      <template #toolbar>
        <a-button type="primary" @click="edit"> 增加游戏资产 </a-button>
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
    <AddAssetGame @register="modalregister" />
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getAssetGameList } from '/@/api/egame/egame';
  import { useModal } from '/@/components/Modal';
  import AddAssetGame from './form/AddAssetGame.vue';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { defHttp } from '/@/utils/http/axios';

  function test() {
    console.log('test');
  }

  export default defineComponent({
    components: { BasicTable, TableAction, AddAssetGame },
    setup() {
      const { createConfirm } = useMessage();
      const [modalregister, { openModal: openModal }] = useModal();
      const [register, methods] = useTable({
        title: '游戏资产列表',
        api: getAssetGameList,
        columns: [
          {
            title: 'ID',
            dataIndex: 'id',
            fixed: 'left',
            width: 100,
          },
          {
            title: '游戏',
            dataIndex: 'game',
            width: 380,
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
            title: '是否借出',
            dataIndex: 'isLent',
            width: 100,
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
                  url: '/asset/game/del',
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
