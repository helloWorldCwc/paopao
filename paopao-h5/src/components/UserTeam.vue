<template>
<van-card
    v-for="teamInfo in teamData"
    style="margin: 10px;"
    :desc="teamInfo.description"
    :title="teamInfo.teamname"
    thumb="https://fastly.jsdelivr.net/npm/@vant/assets/ipad.jpeg"
    >
    <template #tittle>
        <span style="margin-bottom: 5px;">{{ teamInfo.teamname }}</span>
    </template>
    <template #bottom>
       <div style="margin-bottom: 5px;">截止时间：{{ teamInfo.expiredTime }}</div>
       <div style="margin-bottom: 5px;">创建时间：{{ teamInfo.createTime }}</div>
    </template>
    <template #tags>
       <div style="margin: 5px 0;">
            <van-tag plain type="warning" v-if="teamInfo.status === 2">
                加密 
            </van-tag>
            <van-tag plain type="primary" v-if="teamInfo.status === 0">
                公开
            </van-tag>
            <span style="margin-left: 5px;">队伍人数：{{ teamInfo.users.length }} / {{ teamInfo.maxnum }}</span>
       </div>
    </template>
    <template #footer>
        <slot name="footer" :teamRecord="(teamInfo as TeamInfo)"></slot>
    </template>
    </van-card>

</template>


<script setup lang="ts">
import { TeamInfo } from '../modules/team';
const {teamData} = defineProps(['teamData'])


</script>


<style scoped>


</style>