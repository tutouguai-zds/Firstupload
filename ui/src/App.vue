<template>
  <router-view name="login"></router-view>
  <router-view name="reg"></router-view>
  <router-view name="content"></router-view>
  <router-view name="admin"></router-view>
</template>
<script>

import contents from'./content.vue'
export default {
  
  data(){
    return{
    }
  },
  components:{
    // eslint-disable-next-line vue/no-unused-components
    contents
  },
  methods:{
   
  }
}
</script>


