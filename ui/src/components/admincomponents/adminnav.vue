<template>
  <el-menu
    class="el-menu-demo"
    mode="vertical"
    router
  >
    <el-menu-item index="/admin/usermg">用户管理</el-menu-item>
    <el-menu-item index="/admin/ordermg">订单管理</el-menu-item>
    <el-menu-item index="/admin/goods">商品信息</el-menu-item>
    <el-menu-item index="/admin/upload">上传商品</el-menu-item>
  </el-menu>
</template>
<script scope>
export default({
  setup() {
   
    return {
      
    }
  },
})
</script>
