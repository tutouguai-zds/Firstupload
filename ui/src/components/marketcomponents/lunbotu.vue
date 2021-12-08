<template>
  <div class="block">
    <el-carousel height="550px">
      <el-carousel-item v-for="item in 4" :key="item">
        <el-image :src="this.baseurl+this.image[item-1]"/>
      </el-carousel-item>
    </el-carousel>
  </div>
</template>
<script>
import axios from'axios'
export default {
  setup() {
    
  },
  data(){
    return{
      image:[String],
      baseurl:'http://localhost:8081'
    }
  },
  methods:{
    getimages(){
      let that=this
      axios.get('http://localhost:8081/api/goodses').then(function(response){
         for (let index = 0; index < 4; index++) {
           that.image[index]=response.data._embedded.goodses[index].images
         }
      })
    }
  },
  mounted(){
    this.getimages()
  }
}
</script>
<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>
