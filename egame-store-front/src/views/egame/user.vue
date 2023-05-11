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
  import { getUserList } from '/@/api/egame/egame';

  function test() {
    console.log('test');
  }

  export default defineComponent({
    components: { BasicTable, TableAction },
    setup() {
      const [register] = useTable({
        title: '用户列表',
        api: getUserList,
        columns: [
          { title: 'ID', dataIndex: 'id', fixed: 'left', width: 90 },
          { title: '用户名', dataIndex: 'userName', fixed: 'left', width: 200 },
          { title: '手机号', dataIndex: 'phone', fixed: 'left', width: 150 },
          { title: '角色', dataIndex: 'role', fixed: 'left', width: 100 },
          { title: '创建时间', dataIndex: 'createTime', fixed: 'left', width: 100 },
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
