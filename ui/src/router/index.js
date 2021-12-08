// import { createRouter, createWebHashHistory } from "vue-router"
// const routes=[{
//     path:'/ddd',
//     component: () =>import('../components/userMain.vue')
// }]
// export const router=createRouter({
//     history:createWebHashHistory(),
//     routes:routes
// })

// 2. 定义一些路由
// 每个路由都需要映射到一个组件。
// 我们后面再讨论嵌套路由。
import info from'../components/info.vue'
import order from '../components/order.vue'
import market from'../components/market.vue'
import cart from '../components/cart.vue'
import science from'../components/marketcomponents/Science.vue'
import children from'../components/marketcomponents/Children.vue'
import comic from'../components/marketcomponents/Comic.vue'
import technology from '../components/marketcomponents/Technology.vue'
import login from'../components/login.vue'
import reg from'../components/reg.vue'
import content from'../content.vue'
import admin from'../components/admincomponents/admin.vue'
import usermg from'../components/admincomponents/usermg.vue'
import ordermg from'../components/admincomponents/ordermg.vue'
import upload from'../components/admincomponents/upload.vue'
import goods from'../components/admincomponents/goods.vue'
import { createRouter, createWebHashHistory } from "vue-router"
const routes = [
    { path:"/",redirect:"/login"},
    { path: '/login',components:{
      login:login
      },
    },
    { path:'/reg',components:{
      reg:reg
    }},
    { path:'/admin',components:{
      admin:admin
    },
    children:[
      { path:'usermg',components:{
        usermg:usermg
      }},
      { path:'ordermg',components:{
        ordermg:ordermg
      }},
      { path:'upload',components:{
        upload:upload
      }},
      { path:'goods',components:{
        goods:goods
      }}
    ]
  },
    { path:"/content",components:{
      content:content
    },
      children:[
        { path: 'info', components:  {
          info:info
        },
        },
        { path: 'order',components:  {
          order:order
        }},
        { path: 'cart', components:  {
          cart:cart
        }},
        { path: 'market',  components:  {
          market:market,
        },
        children: [
          {
            path: '1',components: {
              abc :  science,
            },
          },
          {
            path :'2',components:{
              abc:   technology,
            },
          },
          {
            path: '3',components:{
              abc:  children,
            }
          },
          {
            path: '4',components:{
              abc:  comic,
            }
          }
          ]
    
        }
      ]
  }
]
  // 3. 创建路由实例并传递 `routes` 配置
  // 你可以在这里输入更多的配置，但我们在这里
  // 暂时保持简单
const router = createRouter({
    // 4. 内部提供了 history 模式的实现。为了简单起见，我们在这里使用 hash 模式。
    history: createWebHashHistory(),
    routes, // `routes: routes` 的缩写
  })

export default router
