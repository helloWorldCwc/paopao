<template>
    <van-nav-bar title="标题"  left-arrow @click-left="onClickLeft">
        <template #right>
            <van-icon name="search" size="18" @Click="goSearch" />
        </template>
    </van-nav-bar>
    <router-view />
    <van-tabbar v-model="active" @change="onChange" route>
        <van-tabbar-item icon="home-o" name="index" to="/"> 主页 </van-tabbar-item>
        <van-tabbar-item icon="search" name="team"  to="/team">队伍</van-tabbar-item>
        <van-tabbar-item icon="friends-o" name="my"  to="/my">我的</van-tabbar-item>
    </van-tabbar>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from "vue-router";
import { showToast } from 'vant'
const router = useRouter()
const onClickLeft = () => history.back();
const active = ref('index');
const onChange = (index) => showToast(`标签 ${index}`);
const goSearch = () => {
    router.push('/search')
}
</script>




<style scoped>
</style>