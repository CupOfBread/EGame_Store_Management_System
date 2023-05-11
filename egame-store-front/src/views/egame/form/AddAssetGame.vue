<template>
  <BasicModal
    v-bind="$attrs"
    title="编辑"
    :showOkBtn="false"
    cancelText="关闭"
    width="800px"
    @register="register"
  >
    <BasicForm :schemas="schemas" @register="registerForm" @submit="handleSubmit" />
  </BasicModal>
</template>
<script lang="ts">
  import { defineComponent } from 'vue';
  import { BasicModal, useModalInner } from '/@/components/Modal';
  import { BasicForm, FormSchema, useForm } from '/@/components/Form';
  import { useMessage } from '/@/hooks/web/useMessage';
  import { defHttp } from '/@/utils/http/axios';

  const schemas: FormSchema[] = [
    {
      field: 'id',
      component: 'Input',
      label: 'Id(不要改动)',
      colProps: {
        span: 16,
      },
    },
    {
      field: 'game',
      component: 'Input',
      label: '游戏',
      required: true,
      colProps: {
        span: 16,
      },
    },
    {
      field: 'price',
      component: 'Input',
      label: '购入价格',
      colProps: {
        span: 16,
      },
    },
    {
      field: 'storageTime',
      component: 'DatePicker',
      label: '入库时间',
      colProps: {
        span: 16,
      },
    },
    {
      field: 'isLent',
      component: 'InputNumber',
      label: '是否借出',
      defaultValue: 0,
      colProps: {
        span: 16,
      },
    },
  ];

  export default defineComponent({
    components: { BasicForm, BasicModal },
    setup() {
      const { createMessage } = useMessage();
      const [register, { closeModal }] = useModalInner((data) => {
        data && onDataReceive(data);
        if (!data) {
          resetFields();
        }
      });
      function onDataReceive(data) {
        console.log(data);
        resetFields();
        setFieldsValue(data);
      }
      const [registerForm, { setFieldsValue, getFieldsValue, resetFields }] = useForm({
        labelWidth: 120,
        showSubmitButton: true,
        showResetButton: false,
        actionColOptions: {
          span: 24,
        },
      });
      return {
        schemas,
        closeModal,
        register,
        registerForm,
        getFieldsValue,
        resetFields,
        handleSubmit: (values: any) => {
          defHttp
            .post({
              url: '/assets/game/add',
              data: values,
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
              },
            })
            .then((res) => {
              if (res == null) {
                createMessage.success('添加成功');
                resetFields();
                closeModal();
              }
            });
        },
      };
    },
  });
</script>
