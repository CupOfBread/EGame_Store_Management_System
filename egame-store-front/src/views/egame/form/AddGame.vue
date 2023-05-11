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
      field: 'nameCn',
      component: 'Input',
      label: '中文名',
      required: true,
      colProps: {
        span: 16,
      },
    },
    {
      field: 'nameEn',
      component: 'Input',
      label: '英文名',
      colProps: {
        span: 16,
      },
    },
    {
      field: 'limitAge',
      component: 'Input',
      label: '年龄限制',
      colProps: {
        span: 16,
      },
    },
    {
      field: 'gameConsoleId',
      component: 'Input',
      label: '游戏机类型',
      colProps: {
        span: 16,
      },
    },
    {
      field: 'gameTypeId',
      component: 'Input',
      label: '游戏类型',
      colProps: {
        span: 16,
      },
    },
    {
      field: 'listingDate',
      component: 'DatePicker',
      label: '上市时间',
      colProps: {
        span: 16,
      },
    },
    {
      field: 'publisher',
      component: 'Input',
      label: '发行商',
      colProps: {
        span: 16,
      },
    },
    {
      field: 'developer',
      component: 'Input',
      label: '开发商',
      colProps: {
        span: 16,
      },
    },
    {
      field: 'introduction',
      component: 'Input',
      label: '简介',
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
              url: '/game/add',
              data: values,
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
