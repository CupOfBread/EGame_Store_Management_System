<template>
  <div class="p-4">
    <BasicTable @register="register">
      <template #toolbar>
        <a-button type="primary" @click="edit"> 添加游戏 </a-button>
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
    <AddGame @register="modalregister" />
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getGameList } from '/@/api/egame/egame';
  import { useModal } from '/@/components/Modal';
  import AddGame from './form/AddGame.vue';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { defHttp } from '/@/utils/http/axios';

  export default defineComponent({
    components: { BasicTable, TableAction, AddGame },
    setup() {
      const [modalregister, { openModal: openModal }] = useModal();
      const { createConfirm } = useMessage();
      const [register, methods] = useTable({
        title: '游戏列表',
        api: getGameList,
        columns: [
          { title: 'ID', dataIndex: 'id', fixed: 'left', width: 90 },
          { title: '中文名', dataIndex: 'nameCn', fixed: 'left', width: 200 },
          { title: '英文名', dataIndex: 'nameEn', fixed: 'left', width: 150 },
          { title: '入库价格', dataIndex: 'price', fixed: 'left', width: 100 },
          { title: '年龄限制', dataIndex: 'limitAge', fixed: 'left', width: 100 },
          { title: '游戏机', dataIndex: 'gameConsoleId', fixed: 'left', width: 90 },
          { title: '游戏类型', dataIndex: 'gameTypeId', fixed: 'left', width: 90 },
          { title: '上市时间', dataIndex: 'listingDate', fixed: 'left', width: 150 },
          { title: '发行商', dataIndex: 'publisher', fixed: 'left', width: 150 },
          { title: '开发商', dataIndex: 'developer', fixed: 'left', width: 150 },
          { title: '简介', dataIndex: 'introduction', fixed: 'left' },
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
        modalregister,
        openModal,
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
                  url: '/game/del',
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
