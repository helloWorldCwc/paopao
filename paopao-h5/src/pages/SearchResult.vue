<template>
   <van-card
   v-for="(user) in userList"
    :desc="user.userProfile"
    :title="user.userName"
    thumb="https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg"
    >
  <template #tags>
    <van-tag plain v-for="tag in JSON.parse(user.tags)" type="primary" style="margin-right: 5px;">{{ tag }}</van-tag>
  </template>
  <template #footer>
    <van-button size="mini" @click="concatMe">联系方式</van-button>
  </template>
</van-card>
<van-empty description="暂无用户" v-if="userList.length < 1" />
</template>


<script setup lang="ts">
import request from '../utils/request.ts'
import {ref, reactive, onMounted} from 'vue'
import type {User} from '../modules/user.d.ts'
import { useRoute ,LocationQueryValue} from 'vue-router';
const route = useRoute();
const tags = route.query.tags as LocationQueryValue
const userList = ref<User[]>([])
const concatMe = () => {
  console.log('点击了开始联系我了。');
  
}
// 钩子
onMounted(async () => {
    const {data} = await request({
        url: '/user/tags',
        params: {
             tags
        }
    })
    console.log(data);
    userList.value = data.data;
})
</script>


<style scoped>


</style>