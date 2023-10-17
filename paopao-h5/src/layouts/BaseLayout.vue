<template>
    <van-nav-bar title="标题" left-text="返回" left-arrow @click-left="onClickLeft">
        <template #right>
            <van-icon name="search" size="18" />
        </template>
    </van-nav-bar>
    <div>
        <Index v-if="active == 'index'"></Index>
        <My v-if="active == 'my'"></My>
        <Team  v-if="active == 'team'"> </Team>
    </div>
    <van-tabbar v-model="active" @change="onChange">
        <van-tabbar-item icon="home-o" name="index"> 主页 </van-tabbar-item>
        <van-tabbar-item icon="search" name="team">队伍</van-tabbar-item>
        <van-tabbar-item icon="friends-o" name="my">我的</van-tabbar-item>
    </van-tabbar>
</template>

<script setup lang="ts">
import { ref, reactive } from 'vue'
import { showToast } from 'vant'
import  Index  from "../pages/Index.vue"
import  My  from "../pages/My.vue"
import  Team  from "../pages/Team.vue"
const onClickLeft = () => history.back();
const active = ref('index');
const onChange = (index) => showToast(`标签 ${index}`);
</script>




<style scoped></style>