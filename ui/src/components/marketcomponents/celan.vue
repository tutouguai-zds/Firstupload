<template>
  <el-menu
    
    class="el-menu-demo"
    mode="vertical"
    background-color="#fff"
    text-color="#000"
    active-text-color="#ffd04b"
    @select="handleSelect"
    router
  >
    <el-menu-item index="/content/market/1">科学</el-menu-item>
    <el-menu-item index="/content/market/2">技术</el-menu-item>
    <el-menu-item index="/content/market/3">儿童</el-menu-item>
    <el-menu-item index="/content/market/4">漫画</el-menu-item>
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
