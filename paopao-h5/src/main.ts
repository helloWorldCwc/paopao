import { createApp } from 'vue'
import './style.css'
import 'vant/lib/index.css';
import vant from 'vant'
import App from './App.vue'

const app = createApp(App)
app.use(vant)
app.mount('#app')
