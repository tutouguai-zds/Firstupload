<template>
  
  <el-table
    ref="filterTable"
    row-key="goods"
    :data="goods"
    style="width: 100%"
  >
    <el-table-column prop="goodsname" label="商品名称"></el-table-column>
    <el-table-column 
     prop="category"
     label="商品类别"
     :filters="[
      { text:'儿童',value:'儿童'},
      { text:'科学',value:'科学'},
      { text:'技术',value:'技术'},
      { text:'漫画',value:'漫画'}
     ]"
     :filter-method="filtercategory"

     ></el-table-column>
    <el-table-column prop="inventory" label="库存"></el-table-column>
    <el-table-column prop="des" label="商品描述"></el-table-column>
    <el-table-column prop="images">
      <template #header>
        <span>图片</span>
      </template>
      <template #default='d'>
        <el-image :src="this.imageurl+d.row.images"/>
      </template>
    </el-table-column>
    <el-table-column>
      <template #header>
        <el-button @click="clearFilter">清除过滤效果</el-button>
      </template>
      <template #default='scope'>
      <el-button @click="handle(scope.$index,scope.row)">修改信息</el-button>
      </template>
    </el-table-column>
  </el-table>
  <el-dialog v-model="dialogFormVisible" title="修改用户信息">
    <el-form :model="form" ref="form">
      <el-form-item label="商品名称:">
        <el-input v-model="form.goodsname" disabled></el-input>
      </el-form-item>
      <el-form-item label="商品类别:">
        <el-select v-model="form.category">
          <el-option label="科学" value="科学"></el-option>
          <el-option label="漫画" value="漫画"></el-option>
          <el-option label="儿童" value="儿童"></el-option>
          <el-option label="技术" value="技术"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="库存:">
        <el-input v-model="form.inventory"></el-input>
      </el-form-item>
      <el-form-item label="商品描述:">
        <el-input v-model="form.des"></el-input>
      </el-form-item>
      <el-form-item label="图片:">
        <el-upload
            class="avatar-uploader"
            action
            :before-upload="beforeAvatarUpload"
            :http-request="request"
             list-type="picture-card"
            :limit=1
             :on-success="handleAvatarSuccess"
        >
          <img v-if="headurl" :src="headurl" class="avatar" />
          <el-icon v-else class="avatar-uploader-icon"><plus /></el-icon>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="change()"
          >确认修改</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script scope>
import axios from'axios'
export default {
  data() {
    return {
      goods:[],
      form:{
        goodsname:'',
        category:'',
        inventory:'',
        des:'',
      },
      imageurl:'http://localhost:8081',
      dialogFormVisible:false,
      image:File,
      headurl:''
    }
  },
  methods: {

    clearFilter() {
      this.$refs.filterTable.clearFilter()
    },
    //value为过滤列表选择后传递过来的值，row是每行的数据，只显示能让函数返回true的数据
    filtercategory(value,row){
      return row.category===value
    },
    getgoods(){
        let that=this
        axios.get('http://localhost:8081/api/goodses').then(function(response){
            that.goods=response.data._embedded.goodses
        })
    },
    handle(a,b){
      this.dialogFormVisible=true
      this.form=b

    },
    change(){
      this.dialogFormVisible=false
      let that=this
      let formfile=new FormData()
      formfile.append('form',JSON.stringify(this.form))
      formfile.append('image',this.image)
      axios.post('http://localhost:8081/cggoods',formfile).then(function(response){
        if(response.data.message=="修改成功"){
          that.getgoods()
        }
      })
    },
    handleAvatarSuccess(res, file) {
      this.headurl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2 
      if (!isJPG) {
        this.$message.error('必须为JPEG格式')
      }
      if (!isLt2M) {
        this.$message.error('大小不能超过2MB')
      }
      return isJPG && isLt2M
    },
    request(option){
        this.image=option.file
    },
  },
  mounted(){
      this.getgoods()
  }
}
</script>
