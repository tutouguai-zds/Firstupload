<template>
  <div class="common-layout">
    <el-container>
        <el-main><lunbotu></lunbotu></el-main>
    </el-container>
    <el-container direction="horizontal">
        <el-aside><celan></celan></el-aside>
        <el-main><router-view name="abc"></router-view></el-main>
    </el-container>
  </div>
</template>
<script>
import lunbotu from'./marketcomponents/lunbotu.vue'
import celan from'./marketcomponents/celan.vue'
export default {
    components:{
        lunbotu,celan
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
  
}

.el-main {
  background-color: #e9eef3;
  color: var(--el-text-color-primary);
  text-align: center;
  line-height: 160px;
  widows: 100%;
  height: 100%;
}
</style>
