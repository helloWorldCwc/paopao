<template>
<van-cell center title="心动模式">
  <template #right-icon>
    <van-switch v-model="isHeartPattern" />
  </template>
</van-cell>
<UserRecommed  :user-list="userList" @concatMe="concatMe"></UserRecommed>

<van-empty description="暂无用户" v-if="userList.length < 1" />
</template>


<script setup lang="ts">
import UserRecommed from '../components/UserRecommed.vue'

import { getRecommendUsers } from '../apis/user'
import {ref, reactive, onMounted} from 'vue'
import  { showLoadingToast, closeToast } from 'vant'
import type {User} from '../modules/user.d.ts'
const userList = ref<User[]>([])
const isHeartPattern = ref(false);
const concatMe = (value:string) => {
 console.log('点击了开始联系我了。', value);
 
}
const getUserList = async () => {
 const res = await getRecommendUsers()
  userList.value = res.data;
}
// 钩子
onMounted( () => {
 getUserList()
})
</script>


<style scoped>


</style>