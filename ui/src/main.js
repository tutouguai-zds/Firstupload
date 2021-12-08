import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from'./router/index.js'
import qs from'qs'
import axios from'axios'
import store from'./store'
// import { VueRouter} from "vue-router"

// import router from'./router/index'
// const Home = { template: '<div>Home</div>' }
axios.defaults.withCredentials=true

const app = createApp(App)

app.config.globalProperties.$qs=qs
app.use(store)
app.use(ElementPlus)
app.use(router)
app.mount('#app')
