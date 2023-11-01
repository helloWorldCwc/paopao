<template>
    <div class="login">
        <!-- <div style="margin-bottom: 10px;">请登录</div> -->
        <van-form @submit="onSubmit">
            <van-cell-group inset>
                <van-field v-model="userAccount" name="userAccount" label="用户名" placeholder="用户名"
                    :rules="[{ required: true, message: '请填写用户名' }]" />
                <van-field v-model="userPassword" type="password" name="userPassword" label="密码" placeholder="密码"
                    :rules="[{ required: true, message: '请填写密码' }]" />
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                    提交
                </van-button>
            </div>
        </van-form>
    </div>
</template>


<script setup lang="ts">
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import request from '../utils/request'
import  { showLoadingToast, closeToast } from 'vant'
const router = useRouter();
const userAccount = ref('ccTest');
const userPassword = ref('123456789');
const onSubmit = async (data) => {
    console.log('submit', data);
    showLoadingToast({
       message: '登录中...',
       forbidClick: false,
  });
   const res =  await request({
        url: '/user/login',
        method: 'post',
        data,
    })
    closeToast()
    if(res.data.code == 0){
       router.replace("/")
    }
    
};

</script>


<style scoped>
.login {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    height: 100vh;
    background-color: rgb(239, 242, 245);

}
</style>