<template>
  <div class="common-layout">
    <el-container>
        <el-header>订单信息</el-header>
        <el-main>
          <orderinfo></orderinfo>
        </el-main>
        <el-footer>
          <el-button type="info">清空订单</el-button>
        </el-footer>
    </el-container>
  </div>
</template>
<script>
import orderinfo from'./ordercomponents/orderinfo.vue'
export default{
  components:{
    orderinfo
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
