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
        <van-empty v-if="activeIds.length === 0" image-size="50" description="未选择标签" />
    </div>
    <van-divider content-position="left">选择标签</van-divider>
    <van-tree-select v-model:active-id="activeIds" @click-item="checkItem" v-model:main-active-index="activeIndex"
        :items="tagsItem" />
    <van-button type="success" size="large" @click="searchUser" >搜索</van-button>

</template>


<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter();
const searchValue = ref('')

// select
const activeIds = ref(['男', '女']);
const activeIndex = ref(0);
const items = [
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
const tagsItem = ref([...items]);
// TODO 这儿应该是控制选项栏即可
const onSearch = (searchValue:string) => {
    const serItems = JSON.parse(JSON.stringify(items));
    serItems.forEach((tags:any) => {
        tags.children = tags.children.filter(tag => tag.text.includes(searchValue));
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
    console.log('开始搜索', activeIds.value);
    router.push({
        path: '/searchResult',
        query: {
            tags: activeIds.value.join(','),
        }
    })
}
</script>


<style scoped></style>