<template>
<el-container>
  <el-header>
    <nav1></nav1>
  </el-header>
  <el-main>
  <div>
    <el-container>
      
      <el-main><router-view name="info"></router-view></el-main>
    </el-container>
  </div>
  <div >
    <el-container>
      
      <el-main><router-view name="order"></router-view></el-main>
    </el-container>
  </div>
  <div >
    <el-container>
      
      <el-main><router-view name="cart"></router-view></el-main>
    </el-container>
  </div>
   <div>
    <el-container>
      <el-main><router-view name="market"></router-view></el-main>
    </el-container>
  </div>
  </el-main>
</el-container>
</template>
<script>

import nav1 from'./components/nav.vue'
import store from'./store'
import axios from'axios'
export default {

    data(){
      return{
      }
    },
    components:{
        nav1
    },
    methods:{
       getRole(){
        axios.get('http://localhost:8081/api/goodses').then(function(response){
          if(typeof response.data.message!='undefined'){
              store.commit('deleteAll')
              this.$router.push('/login')
          }
        })
      }
    },
    mounted(){
      this.getRole()
    },
    beforeRouteEnter(to, from, next) {
      store.commit('init')
      if(store.state.loginstate=="true"){
        if(Object.keys(store.state.userinfo).length!=0){
          next()
        }else{
          next('/admin')
        }
      }else{
        next('/login')
      }
    },

}
</script>
<style>
.el-header {
  background-color: #fff;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 60px;
}


.el-main {
  background-color: #fff;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 160px;
  width: 100%;
  height: 100%;
}
</style>
