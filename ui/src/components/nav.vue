<template>
  <el-menu
    class="el-menu-demo"
    mode="horizontal"
    background-color="#fff"
    text-color="black"
    active-text-color="#ffd04b"
    @select="handleSelect"
    router
  >
    <el-menu-item index="/content/info">个人信息</el-menu-item>
    <el-menu-item index="/content/order">订单</el-menu-item>
    <el-menu-item index="/content/cart" >购物车</el-menu-item>
    <el-menu-item index="/content/market">商城</el-menu-item>
  </el-menu>
</template>

<script scope>

export default ({
  setup() {

    return {
      
    }
  },
})
</script>
