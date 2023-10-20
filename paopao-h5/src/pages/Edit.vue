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
import {ref, reactive} from 'vue'
import { useRoute } from 'vue-router';
const route = useRoute()
const form = reactive({
    ...route.query
})
const onSubmit = (values: any) => {
console.log('submit', values);
};

</script>


<style scoped>


</style>