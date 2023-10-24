<template>
    <!-- <van-cell title="账号"  :value="user.userAccount" /> -->
    <van-cell title="昵称" is-link :value="user.userName" @click="clickCell('昵称', user.userName, 'userName')" />
    <van-cell title="简介" is-link  :value="user.userProfile" @click="clickCell('简介', user.userProfile, 'userProfile')" />
    <van-cell title="头像" is-link   >
        <img :src="user.userAvatar" style="width: 50px;height: 50px;">
    </van-cell>
    <van-cell title="标签" is-link  >
        <van-tag type="primary" closable  v-for="(item,index) in user.tags" :key="index" >{{item}}</van-tag>
    </van-cell>
    <van-cell title="创建时间"  :value="(user.createTime as string)" />
</template>


<script setup lang="ts">
import {ref, reactive, onMounted} from 'vue'
import {getCurrentUser} from '../apis/user'
import {useRouter, useRoute} from 'vue-router'
import { User } from '../modules/user';
import { showToast } from 'vant';
const router = useRouter();
const route = useRoute();
const user = ref<User>({
    id: 1,

    userAccount: 'da',

    userRole: '12',

    createTime: '333',
    userName: 'aa',
    userProfile: 'bb'
});

const clickCell = (formLabel: string, formName: string, type?:string) => {
    router.push({
        path: '/edit',
        query: {
            id: user.value?.id,
            formLabel,
            formName,
            type
        }    
    })
}

onMounted(async () => {
    const res = await getCurrentUser();
    if(res.data.code === 0){
        user.value = res.data.data;
    }else {
        showToast('获取用户信息失败')
        router.push('/login?back=' + route.path)
    }
})

</script>


<style scoped>


</style>