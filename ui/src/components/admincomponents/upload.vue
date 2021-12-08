<template>
  <!-- Form -->
  <el-button type="text" @click="dialogFormVisible = true"
    >上传商品信息</el-button
  >
  <el-dialog v-model="dialogFormVisible" title="填写商品信息">
    <el-form :model="form">
      <el-form-item label="商品名称:" :label-width="formLabelWidth">
        <el-input v-model="form.goodsname" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="商品类别:" :label-width="formLabelWidth">
        <el-select v-model="form.category" placeholder="请选择一个类别">
          <el-option label="科学" value="科学"></el-option>
          <el-option label="儿童" value="儿童"></el-option>
          <el-option label="漫画" value="漫画"></el-option>
          <el-option label="技术" value="技术"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="商品描述:" :label-width="formLabelWidth">
        <el-input v-model="form.des" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="商品库存:" :label-width="formLabelWidth">
        <el-input v-model="form.inventory" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="商品图片:" >
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
        <el-button type="primary" @click="upload()"
          >开始上传</el-button
        >
      </span>
    </template>
  </el-dialog>
  {{message}}
</template>

<script scope>
import { defineComponent, reactive, toRefs } from 'vue'
import axios from'axios'
export default defineComponent({
  setup() {
    const state = reactive({
      dialogFormVisible: false,
      form: {
        goodsname: '',
        des:'',
        images:'',
        inventory:'',
        category:''
      },
      formLabelWidth: '120px',
    })

    return {
      ...toRefs(state),
    }
  },
  data(){
      return{
          image:File,
          headurl:'',
          message:''
      }
  },
  methods:{
      request(option){
          this.image=option.file
      },
      beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 5 
      if (!isJPG) {
        this.$message.error('必须为JPEG格式')
      }
      if (!isLt2M) {
        this.$message.error('大小不能超过5MB')
      }
      return isJPG && isLt2M
    },
    handleAvatarSuccess(res, file) {
      this.headurl = URL.createObjectURL(file.raw)
    },
    upload(){
        let that=this
        this.dialogFormVisible = false
        let fileform=new FormData()
        fileform.append('image',this.image)
        fileform.append('form',JSON.stringify(this.form))
        axios.post('http://localhost:8081/savegood',fileform)
        .then(function(response){
            that.message=response.data.message
        })
    },
  }
})
</script>
