<template>
<UserRecommed  :user-list="userList" @concatMe="concatMe"></UserRecommed>
<van-empty description="暂无用户" v-if="userList.length < 1" />
</template>


<script setup lang="ts">
import UserRecommed from '../components/UserRecommed.vue'
import request from '../utils/request.ts'
import {ref, reactive, onMounted} from 'vue'
import  { showLoadingToast, closeToast } from 'vant'

import type {User} from '../modules/user.d.ts'
import { useRoute ,LocationQueryValue} from 'vue-router';
const route = useRoute();
const tags = route.query.tags as LocationQueryValue
const userList = ref<User[]>([])
const concatMe = (value:string  ) => {
  console.log('点击了开始联系我了。', value);
  
}
const getUserList = async () => {
  showLoadingToast({
        message: '加载中...',
        forbidClick: false,
    });
  const {data} = await request({
        url: '/user/tags',
        params: {
             tags
        }
    })
    closeToast()
    userList.value = data.data;
}
// 钩子
onMounted( () => {
  getUserList()
})
</script>


<style scoped>


</style>