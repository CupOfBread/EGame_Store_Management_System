<template>
  <div class="p-4">
    <BasicTable @register="register">
      <template #toolbar>
        <a-button type="primary" @click="edit"> 添加类型 </a-button>
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
    <AddGameType @register="modalregister" />
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getGameTypeList } from '/@/api/egame/egame';
  import { useModal } from '/@/components/Modal';
  import AddGameType from './form/AddGameType.vue';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { defHttp } from '/@/utils/http/axios';

  function test() {
    console.log('test');
  }

  export default defineComponent({
    components: { BasicTable, TableAction, AddGameType },
    setup() {
      const { createConfirm } = useMessage();
      const [modalregister, { openModal: openModal }] = useModal();
      const [register, methods] = useTable({
        title: '游戏类型',
        api: getGameTypeList,
        columns: [
          {
            title: 'ID',
            dataIndex: 'id',
            fixed: 'left',
            width: 200,
          },
          {
            title: '游戏类型',
            dataIndex: 'name',
            width: 350,
          },
          {
            title: '备注',
            dataIndex: 'remark',
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
                  url: '/game/type/del',
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
