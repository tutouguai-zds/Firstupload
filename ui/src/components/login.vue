<template>
  <el-form ref="form" :model="form" label-width="120px" :rules="rules">
    <el-form-item label="用户名:" prop="username">
      <el-input v-model="form.username"></el-input>
    </el-form-item>
    <el-form-item label="密码:" prop="password"> 
      <el-input v-model="form.password" type="password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit('form')">登录</el-button>
      <el-button @click="reg">注册</el-button>
    </el-form-item>
  </el-form>
{{message}}


</template>

<script scope>
import axios from'axios';


export default {
  data() {
    return {
      message:"",
      user:{},
      form: {
        username: '',
        password:''
      },
      rules:{
          username:[
              {
                required:true,
                message:"请输入用户名",
                trigger:'blur'
              },
              {
                  min:6,
                  max:18,
                  message:"用户名长度在6至18个字符",
                  trigger:'blur'
              }
          ],
          password:[
              {
                required:true,
                message:"请输入密码",
                trigger:'blur'
              },
              {
                min:5,
                max:12,
                message:"密码长度在5至12个字符"
              }
          ]
      }
    }
  },
  methods: {
    onSubmit(formname) {
      let that=this
      this.$refs[formname].validate((valid)=>{
            if(valid){
                 axios.post('http://localhost:8081/login',this.$qs.stringify(this.form))
                 .then(function(response){
                    that.message=response.data.message
                    that.user=response.data.object
                     // that.role=response.data.object.authorities[0].authority
                     
                     that.$router.push("/content")
                 })
            }
        })
    },
    reg(){
        this.$router.push("/reg")
    }
  },
  beforeRouteLeave(to, from, next){
  if(to.path=="/content")
  {
    if(this.message=='亲爱的用户，欢迎回来'){
      this.$store.commit('setUserInfo',{loginstate:"true",userinfo:this.user})
      next()
    }else if(this.message=='欢迎回来，亲爱的管理员'){
      this.$store.commit('setUserInfo',{loginstate:"true",admininfo:this.user})
      next()
    }else{
      next(false)
    }
  }
  else{
    next()
  }
},
   computed: {
     
   }
}
</script>
