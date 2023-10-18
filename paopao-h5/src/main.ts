import { createApp } from 'vue'
import router from './router/index'
// import './style.css'
import 'vant/lib/index.css';
import vant from 'vant'
import App from './App.vue'
const app = createApp(App)
app.use(vant)
app.use(router)
app.mount('#app')
