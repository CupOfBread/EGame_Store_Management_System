<template>
  <div class="p-4">
    <BasicTable @register="register">
      <template #toolbar>
        <a-button type="primary"> 操作按钮 </a-button>
      </template>
      <template #action="{ record }">
        <TableAction
          :actions="[
            {
              label: '删除',
              icon: 'ic:outline-delete-outline',
              onClick: test.bind(null, record),
            },
          ]"
        />
      </template>
    </BasicTable>
  </div>
</template>
<script lang="ts">
  import { defineComponent, ref } from 'vue';
  import { BasicTable, useTable, TableAction } from '/@/components/Table';
  import { getGameTypeList } from '/@/api/egame/egame';

  function test() {
    console.log('test');
  }

  export default defineComponent({
    components: { BasicTable, TableAction },
    setup() {
      const [register] = useTable({
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
      };
    },
  });
</script>
