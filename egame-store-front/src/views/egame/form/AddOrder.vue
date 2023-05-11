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
      field: 'customerPhone',
      component: 'Input',
      label: '客户手机号',
      required: true,
      colProps: {
        span: 16,
      },
    },
    {
      field: 'consoleId',
      component: 'Input',
      label: '游戏机ID',
      required: true,
      colProps: {
        span: 16,
      },
    },
    {
      field: 'leaseTime',
      component: 'InputNumber',
      label: '租用时间(分钟)',
      required: true,
      defaultValue: 60,
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
              url: '/order/new',
              data: values,
              headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8',
              },
            })
            .then((res) => {
              console.log(res);
              if (res.id != null) {
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
