<template>
    <van-cell title="账号"  :value="user.userAccount" />
    <van-cell title="昵称" is-link :value="user.userName" @click="clickCell('昵称', user.userName)" />
    <van-cell title="简介" is-link  :value="user.userProfile" />
    <van-cell title="头像" is-link   >
        <img :src="user.userAvatar" style="width: 50px;height: 50px;">
    </van-cell>
    <van-cell title="标签" is-link  >
        <van-tag type="primary" closable  v-for="(item,index) in user.tags" :key="index" >{{item}}</van-tag>
    </van-cell>
    <van-cell title="创建时间"  :value="user.createTime.toISOString()" />
</template>


<script setup lang="ts">
import {ref, reactive} from 'vue'
import {useRouter} from 'vue-router'
const router = useRouter();
const user = {
    id: 1,

    userAccount: 'sdsad',

    userName: 'admin',

    userAvatar: 'www.baidu.com',

    userProfile: '是一个人',

    tags: ['java', 'c++'],

    userRole: 'admin',

    createTime: new Date(),
}

const clickCell = (formLabel: string, formName: string, type?:string) => {
    router.push({
        path: '/edit',
        query: {
            id: user.id,
            formLabel,
            formName,
            type
        }    
    })
}

</script>


<style scoped>


</style>