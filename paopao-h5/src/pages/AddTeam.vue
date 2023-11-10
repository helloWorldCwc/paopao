<template>
<van-form @submit="onSubmit">
  <van-cell-group >
    <van-field
      v-model="formData.teamname"
      name="teamname"
      label="队伍名称"
      placeholder="队伍名称"
      :rules="[{ required: true, message: '请输入队伍名称' }]"
    />
    <van-field
      v-model="formData.description"
      type="textarea"
      name="description"
      label="队伍描述"
      placeholder="队伍描述"
      rows="2"
    autosize
      :rules="[{ required: false, message: '队伍描述' }]"
    />
    </van-cell-group>
        <van-field
            v-model="formData.expiredTime"
            is-link
            readonly
            name="datePicker"
            label="过期时间"
            placeholder="点击选择时间"
            @click="showPicker = true"
        />
        <van-popup v-model:show="showPicker" position="bottom">
           <van-picker-group
            title="过期时间"
            :tabs="['选择日期', '选择时间']"
            next-step-text="下一步"
            @confirm="onConfirm"
            @cancel="showPicker = false"
            >
            <van-date-picker
                v-model="currentDate"
            />
            <van-time-picker v-model="currentTime" />
            </van-picker-group>
        </van-popup>
    <van-field name="maxnum" label="最大人数"  :rules="[{ required: true, message: '请输入队伍人数' }]">
        <template #input>
            <van-stepper v-model="formData.maxnum" />
        </template>
    </van-field>
    <van-field name="status" label="队伍状态" :rules="[{ required: true, message: '请选择队伍状态' }]">
        <template #input>
            <van-radio-group v-model="formData.status" direction="horizontal">
                <van-radio name="0">公开</van-radio>
                <van-radio name="1">私有</van-radio>
                <van-radio name="2">加密</van-radio>
            </van-radio-group>
        </template>
    </van-field>
    <van-field name="password"  v-model="formData.password" label="队伍密码" v-if="formData.status == 2" :rules="[{ required: true, message: '请输入队伍密码' }]">
    </van-field>
  <div style="margin: 16px;">
    <van-button round block type="primary" native-type="submit">
      提交
    </van-button>
  </div>
</van-form>

</template>


<script setup lang="ts">
import { createTeam, getOneTeam, updateTeam } from '../apis/team.ts'
import { useRoute } from 'vue-router'
import type { TeamRequest, Team } from '../modules/team.d.ts'
import {ref, reactive, onMounted} from 'vue'
import { showToast } from 'vant';
const showPicker = ref(false);
const now = new Date();
const currentDate = ref([now.getFullYear() + '', now.getMonth() + 1 + '', now.getDate() + '']);
const currentTime = ref(['12', '00']);
const route = useRoute();
const teamId = ref();
const teamInfo = ref<Team>();
const formData = reactive<any>({
    id: '',
    teamname: '',
    description: '',
    expiredTime: '',
    status: 0, 
    maxnum: 1,
    password: '',
});
const onConfirm = () => {
    formData.expiredTime = currentDate.value.join('-') + ' ' + currentTime.value.join(':');
    showPicker.value = false;
};
const onSubmit = async () => {
    // 更新操作
    if(teamInfo.value?.id){
        const res = await updateTeam(formData);
        if(res.code == 0){
            showToast('更新队伍信息成功')
        }
    }else {
        const res = await createTeam(formData);
        if(res.code == 0){
            showToast('新增队伍成功')
        }
    }
};
onMounted(async () => {
    const id = route.query.id
    if(id){
        const res = await getOneTeam(id)
        teamInfo.value = res.data
        formData.teamname = teamInfo.value.teamname
        formData.id = teamInfo.value.id
        formData.description = teamInfo.value.description
        formData.expiredTime = teamInfo.value.expiredTime
        formData.status = teamInfo.value.status + ''
        formData.maxnum = teamInfo.value.maxnum
        formData.password = teamInfo.value.password
    }
})
</script>


<style scoped>


</style>
