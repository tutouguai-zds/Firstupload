<template>
  <el-row type="flex" justify=start>
    <el-col
      v-for="o, in this.sciimg.length-1"
      :key="o"
      :span="6"
      :offset="1"
    >
    <button>
      <el-card :body-style="{ padding: '0px',width:'100%',height:'300px'}">
        <el-image :src="this.baseurl+this.sciimg[o]" class="image">
        </el-image>
          {{this.sci[o].goodsname}}
          {{this.sci[o].des}}
      </el-card>
    </button>
      
    </el-col>
  </el-row>
</template>

<script scope>
import { defineComponent, ref } from 'vue'
import axios from'axios'
export default defineComponent({
  setup() {
    const currentDate = ref(new Date())

    return {
      currentDate,
    }
  },
  data(){
    return{
      sciimg:[String],
      sci:[{}],
      baseurl:'http://localhost:8081'
    }
  },
  methods:{
    getchild(){
      let that=this
      axios.get('http://localhost:8081/api/goodses').then(function(response){
         for (let index = 0; index < response.data._embedded.goodses.length; index++) {
           if(response.data._embedded.goodses[index].category=='科学'){
             that.sci.push(response.data._embedded.goodses[index])
             that.sciimg.push(response.data._embedded.goodses[index].images)
           }
         }
      })
    }
  },
  mounted(){
    this.getchild()
  }
})
</script>

<style>

.image {
  width: 100%;
  display: block;
}
</style>
