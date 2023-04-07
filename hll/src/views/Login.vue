<template>
  <div class = "wrapper">
    <div style = "margin : 200px auto; background-color:#fff; width : 350px ; height :300px ; padding :20px ; border-radius: 10px">
      <div style = "margin : 20px 0 ; text-align: center ; font-size : 24px">
        <b>登录</b>
      </div>
      <el-form :model = "user" :rules = "rules" ref = "userForm" >
        <el-form-item prop = "username"><el-input size = "medium" style = "margin : 10px 0" prefix-icon = "el-icon-user" v-model = "user.username"></el-input></el-form-item>
        <el-form-item prop = "password"><el-input size = "medium" style = "margin : 10px 0"  prefix-icon = "el-icon-lock" show-password v-model = "user.password"></el-input></el-form-item>


      </el-form>

      <div style = "margin : 10px 0; text-align: right">
        <el-button type = "primary" size = "small" aria-autocomplete="off" @click = "login">登录</el-button>
<!--        <el-button type = "warning" size = "small" aria-autocomplete="off" >注 册</el-button>-->
        <el-button type = "warning" size = "small" aria-autocomplete="off" @click = "$router.push('/register')">注 册</el-button>
<!--        <el-button type = "warning" size = "small" aria-autocomplete="off" @click = "$router.push('/register')">注 册</el-button>-->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur'},
          {min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur'}
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur'},
          {min: 5, max: 20, message: '长度在 5 到 20 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods : {
    login(){
      this.$refs['userForm'].validate((valid) =>{
        if(valid){ //表单校验合法
          this.request.post("/user/Login",this.user).then(res =>{
            //request默认get接口
            if(res.code === '200'){
              localStorage.setItem("user",JSON.stringify((res.data)))//存储用户信息到浏览器
              this.$message.success("登录成功");
              this.$router.push("/")

              // this.$router.push("/user/Login")
            }else{
              this.$message.error(res.msg)
            }
          })
        }
      });

    }
  }
}
</script>

<style scoped>
  .wrapper {
    height : 100vh;
    background-image: linear-gradient(to bottom right , #FC4668,#3F5EFB);
    overflow : hidden;
  }
</style>