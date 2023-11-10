<template>
<van-search v-model="searchTeamRequest.teamname" placeholder="请输入搜索关键词" @search="searchTeam" />
<van-tabs v-model:active="active" @click-tab="onClickTab">
  <van-tab title="公开" name="public">
    <UserTeam :team-data="teamData">
        <template #footer="{teamRecord}">
            <van-button  size="mini" type="success" style="margin-right: 5px;" @click="joinTeam(teamRecord)">加入队伍</van-button>
            <van-button v-if="teamRecord.isCreated" size="mini" type="primary" style="margin-right: 5px;" @click="updateTeam(teamRecord)">更新队伍</van-button>
            <van-button v-if="isSuccessAddTeam(teamRecord)"  size="mini" type="warning" style="margin-right: 5px;" @click="quitTeam(teamRecord)" >退出队伍</van-button>
        </template>
    </UserTeam>

  </van-tab>
  <van-tab title="加密" name="pwd">
    <UserTeam :team-data="teamData">
        <template #footer="{teamRecord}">
            <van-button size="mini" type="success" style="margin-right: 5px;" @click="joinTeamConfire(teamRecord)">加入队伍</van-button>
            <van-button v-if="teamRecord.isCreated" size="mini" type="primary" style="margin-right: 5px;" @click="updateTeam(teamRecord)">更新队伍</van-button>
            <van-button v-if="isSuccessAddTeam(teamRecord)"  size="mini" type="warning" style="margin-right: 5px;" @click="quitTeam(teamRecord)" >退出队伍</van-button>
        </template>
    </UserTeam>
  </van-tab>
</van-tabs>
<van-floating-bubble icon="plus" @click="createTeam"  v-model:offset="offset" />
<van-dialog v-model:show="showFormPassword" title="队伍密码" show-cancel-button @confirm="passwordSubmit">
    <van-field
      v-model="password"
      name="密码"
      label="密码"
      placeholder="请输入密码"
      :rules="[{ required: true, message: '请填写密码' }]"
    />
</van-dialog>

</template>


<script setup lang="ts">
import UserTeam from '../components/UserTeam.vue'
import {ref, reactive, onMounted, toRefs} from 'vue'
import { getTeamList, updateTeam } from '../apis/team.ts'
import type { TeamPageRquest, TeamInfo } from '../modules/team.d'
import { joinTeam as joinTeamForMe ,quitTeam as quitTeamForMe  } from '../apis/team.ts'
import { getCurrentUser } from '../apis/user.ts'
import { showDialog, showToast } from 'vant'  
import { User } from '../modules/user'
import { useRouter } from 'vue-router'
const router = useRouter()
const active = ref('');
const password = ref<string>('');
const showFormPassword = ref<boolean>(false);
const teamData = ref<TeamInfo[]>();
const userInfo = ref();
const offset = ref({  y: 625 });
const searchTeamRequest = reactive<TeamPageRquest>({
    current: 1,
    pageSize: 10,
    teamname: '',
    description: '',
    status: 0,
})


const onClickTab = ({ title }) => {
    searchTeamRequest.status = title === '公开' ?   0 :  2;
    getData();
}

const getData = async () => {
    const res = await getTeamList(searchTeamRequest);
    if(res.code === 0){
        const records = res.data.records as TeamInfo[];
        teamData.value = records;
    }

}
const searchTeam = () => {
    searchTeamRequest.current = 1
    searchTeamRequest.pageSize = 10
    getData();
}
let teampTeamInfo = {} as TeamInfo;
const joinTeamConfire = (teamInfo: TeamInfo) => {
    showFormPassword.value = true;
    teampTeamInfo = teamInfo;
}
const passwordSubmit = async () => {
    if(password.value === ''){
        showToast('请输入队伍密码')
        return
    }
    joinTeam(teampTeamInfo);
}
const joinTeam = async (teamInfo: TeamInfo) => {
    const data = {
        id: teamInfo.id,
        password: JSON.parse(JSON.stringify(password.value)),
    }
    password.value = '';
    const res =  await joinTeamForMe(data);
    
    if(res.code === 0){
        showToast('加入队伍成功')
        getData()
    }
    
}
const updateTeam = (teamInfo: TeamInfo) => {
    router.push({
        path: '/addTeam',
        query: {id: teamInfo.id}
    })
}
const quitTeam = async (teamInfo: TeamInfo) => {
    let res = await quitTeamForMe({id: teamInfo.id});
    if(res.code === 0){
        showToast('退出队伍成功')
        getData();
    }
}
const isSuccessAddTeam = (teamRecord: TeamInfo) => {
    return teamRecord.users.some((item: User) =>  item.userId == userInfo.value?.id)
}
const createTeam = () => {
    router.push('/addTeam')
}
onMounted(async () => {
    getData()
    const res = await getCurrentUser();
    if(res.code === 0){
        userInfo.value = res.data;
    }
})
</script>


<style scoped>


</style>