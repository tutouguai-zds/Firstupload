<template>
    <el-table :data="orders" ref="table" style="width: 100%"> 
        <el-table-column prop="user.username" label="所属用户"></el-table-column>
        <el-table-column prop="goods" label="包含商品"></el-table-column>
        <el-table-column prop="data" label="创建日期"></el-table-column>
    </el-table>
</template>
<script>
import axios from'axios'
export default {
    setup() {
        
    },
    data(){
        return{
            orders:[]
        }
    },
    methods:{
        getorders(){
            let that=this
            axios.get('http://localhost:8081/api/orders').then(function(response){
                that.orders=response.data._embedded.orders
            })
        }
    },
    mounted(){
        this.getorders()
    }
}
</script>
<style scoped>

</style>