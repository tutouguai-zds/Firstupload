<template>
  <div class="common-layout">
    <el-container>
        <el-header>当前购物车</el-header>
        <el-main>
          <cartinfo></cartinfo>
        </el-main>
        <el-footer>
          <el-button type="info">清空购物车</el-button>
          <el-button type="primary">确认下单</el-button>
        </el-footer>
    </el-container>
  </div>
</template>
<script>
import cartinfo from'./cartcomponents/cartinfo.vue'
export default{
  components:{
    cartinfo
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
  widows: 100%;
  height: 100%;
}
</style>
