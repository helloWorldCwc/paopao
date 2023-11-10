<template>
    <van-search v-model="searchValue" shape="round" background="#4fc08d" @clear="clearKeyword" placeholder="请输入搜索关键词" @search="onSearch" />
    <van-divider content-position="left">选中标签</van-divider>
    <div>
        <van-row :gutter="8">
            <van-col  v-for="tag in activeIds" :key="tag">
                 <van-tag closeable size="medium" type="primary"
                    @close="close(tag)">
                    {{ tag }}
                </van-tag>
            </van-col>
        </van-row>
        <van-empty v-if="activeIds.length === 0" image-size="50" description="未选择标签" style="padding: 0;" />
    </div>
    <van-divider content-position="left">选择标签</van-divider>
    <van-tree-select v-model:active-id="activeIds" @click-item="checkItem" v-model:main-active-index="activeIndex"
        :items="tagsItem" />
    <van-button type="success" size="large" @click="searchUser" >搜索</van-button>

</template>


<script setup lang="ts">
import request from '../utils/request'
import { ref, reactive,onMounted } from 'vue'
import  {TreeSelectChild, TreeSelectItem, showLoadingToast, closeToast } from 'vant'
import { useRouter } from 'vue-router'
const router = useRouter();
const searchValue = ref('')

// select
const activeIds = ref([]);
const activeIndex = ref(0);
// 原始字符串
let items:TreeSelectItem[] = [
    {
        text: '性别',
        children: [
            { text: '男', id: '男' },
            { text: '女', id: '女' },
            { text: '未知', id: '未知' },
        ],
    },
    {
        text: '状态',
        children: [
            { text: '开心', id: '开心' },
            { text: '难过', id: '难过' },
        ],
    },
];
const tagsItem = ref<TreeSelectItem[]>([]);
// TODO 这儿应该是控制选项栏即可
const onSearch = (searchValue:string) => {
    const serItems = JSON.parse(JSON.stringify(items));
    serItems.forEach((tags: any) => {
        tags.children = tags.children.filter((tag: TreeSelectChild) => tag.text.includes(searchValue));
    })  
    tagsItem.value = [...serItems]
}
const clearKeyword = () => {
    console.log(1111);
    
    tagsItem.value = items
}
const close = (tag:string) => {
    // 关闭标签
    activeIds.value.splice(activeIds.value.indexOf(tag), 1);
}


const checkItem = item => {
    // activeIds.value = activeIds.value
}
// 用户搜索， 通过标签的方式
const searchUser = () => {
    router.push({
        path: '/searchResult',
        query: {
            tags: activeIds.value.join(','),
        }
    }) 
}
const getTagsList = async () => {
    showLoadingToast({
        message: '加载中...',
        forbidClick: false,
    });
    const res = await request({
        url: '/tags/all',
    })
    closeToast()
    res.data.forEach((item: any) => {
       item.children = item.children.map((tag:any) => {
         tag.id = tag.text
         return tag;
       })
    })
    tagsItem.value = res.data
    items = res.data
}

// 钩子
onMounted( () => {
    getTagsList();
})
</script>


<style scoped></style>