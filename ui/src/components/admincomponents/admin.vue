<template>
  <div class="common-layout">

    <el-container direction="vertical">
        <el-header>管理面板</el-header>
      <el-container>
          <el-aside width="200px"><adminnav></adminnav></el-aside>
          <el-main>
              <router-view name="usermg"></router-view>
              <router-view name="ordermg"></router-view>
              <router-view name="goods"></router-view>
              <router-view name="upload"></router-view>
          </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
import adminnav from'./adminnav.vue'
import store from'../../store'
import axios from'axios'
export default {
    setup() {
        
    },
    components:{
        adminnav
    },
    methods:{
      getRole(){
        axios.get('http://localhost:8081/api/users').then(function(response){
          if(typeof response.data.message!='undefined'){
              store.commit('deleteAll')
              this.$router.push('/login')
          }
        })
      }
    },
    created(){
      this.getRole()
    },
    beforeRouteEnter(to,from,next){
        store.commit('init')
        if(store.state.loginstate=='true' && Object.keys(store.state.admininfo).length!=0){
            next()
        }else{
            next('/login')
        }
    }
}
</script>
<style>
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 60px;
}

.el-aside {
  background-color: #d3dce6;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #e9eef3;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
</style>
