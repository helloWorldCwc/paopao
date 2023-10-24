<template>
<van-form @submit="onSubmit">
  <van-cell-group inset>
    <van-field
      v-model="form.formName"
      name="value"
      :label="form.formLabel"
      :placeholder="`请输入${form.formLabel}`"
    />
  </van-cell-group>
  <div style="margin: 16px;">
    <van-button round block type="primary" native-type="submit">
      提交
    </van-button>
  </div>
</van-form>
</template>


<script setup lang="ts">
import { showToast, showLoadingToast,closeToast } from 'vant';
import {updateMy} from '../apis/user'
import {ref, reactive} from 'vue'
import { useRoute } from 'vue-router';
const route = useRoute()
const form = reactive({
    ...route.query
})
const onSubmit = async (values: any) => {
  showLoadingToast({
  message: '修改中...',
});
  const userReq = {
    [form.type as string]: values.value
  }
  const res = await updateMy(userReq);
  console.log(res.data.code === 0);
  if(res.data.code === 0){
    showToast('修改成功')
    form.formName = ''
  }
  closeToast()
};

</script>


<style scoped>


</style>