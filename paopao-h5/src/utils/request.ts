import axios, {AxiosInstance} from 'axios'
import router from '../router/index'
import { showToast } from 'vant';
const instance: AxiosInstance = axios.create({
    baseURL: 'http://localhost:8888/api',
    timeout: 20000,
});

  // 添加请求拦截器
instance.interceptors.request.use(function (config) {
    // 在发送请求之前做些什么
    config.withCredentials = true
    return config;
  }, function (error) {
    // 对请求错误做些什么
    return Promise.reject(error);
  });

// 添加响应拦截器
instance.interceptors.response.use(function (response) {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    const res =  response.data
    if(res.code === 40100){
      router.replace("/login")
      return Promise.reject(response);
    }else if(res.code === 40000){
      showToast(res.message)
      return Promise.reject(response);
    }
    return res;
  }, function (error) {
    // 超出 2xx 范围的状态码都会触发该函数。
    // 对响应错误做点什么
    return Promise.reject(error);
  });

  export default instance;