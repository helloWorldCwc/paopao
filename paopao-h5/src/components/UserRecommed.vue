<template>
  <van-skeleton title avatar :row="3" v-for="n in  5" style="margin-bottom: 5px;" :loading="props.userList.length <= 0">
  </van-skeleton>
  <template v-if="props.userList.length" >
    <van-card
    v-for="user in props.userList"
    :desc="user.userProfile"
    :title="user.userName"
    :thumb="user.userAvatar"
    >
  <template #tags>
    <van-tag plain v-for="tag in JSON.parse(user.tags as string)" type="primary" style="margin-right: 5px;">{{ tag }}</van-tag>
  </template>
  <template #footer>
    <van-button size="mini" @click="concatMe">联系方式</van-button>
  </template>
  </van-card>
  </template>
</template>


<script setup lang="ts">
import { User } from '../modules/user';

interface UserRecommend {
    userList: User[]
}
const props =  withDefaults(defineProps<UserRecommend>(), {
    // 默认给一个空
    userList: ():User[] => []
}) 
const emits = defineEmits(['concatMe']);
const concatMe = () => {
    emits('concatMe', '父组件传递值');
}
</script>


<style scoped>


</style>