


<template>


  <div >
    <div style = "margin : 200px auto; background-color:#fff; width : 490px ; height :500px ; padding :20px ; border-radius: 10px">
      <div style = "margin : 20px 0 ; text-align: center ; font-size : 24px">
        <a class="logo" >
          <img class="img-fluid" src="../picture/logo.png">
        </a>
      </div>
    <div class = "login-main">
      <el-form :model = "user" :rules = "rules" ref = "userForm" >
        <h4 class = "theme-form" style = "color: #7e7e7e">输入账户和密码进行登录</h4>
        <p>Enter your email & password to login</p>
        <label class = "col-form-label">请输入账户</label>
        <el-form-item prop = "username" ><el-input  placeholder="请输入账户" size = "medium" style = "margin : 10px 0 ; height: auto" prefix-icon = "el-icon-user" v-model = "user.username"></el-input></el-form-item>
        <label class = "col-form-label">请输入密码</label>
        <el-form-item prop = "password"><el-input placeholder="************" size = "medium" style = "margin : 10px 0"  prefix-icon = "el-icon-lock" show-password v-model = "user.password"></el-input></el-form-item>


      </el-form>

      <div class = "p-0">
        <el-checkbox v-model="checked" class = "col-form-label">记住密码</el-checkbox>
        <a class="link text-primary" href="page-forgot-password.html">忘记密码?</a>
      </div>
      <el-button class = "btn-primary" @click = "login">登录</el-button>
      <el-link  class="link" style = "color :#7366ff;"aria-autocomplete="off" @click = "$router.push('/register')">点此创建用户</el-link>
<!--      <div style = "margin : 10px 0; text-align: right">-->
<!--        <el-button type = "primary" size = "small" aria-autocomplete="off" @click = "login">登录</el-button>-->
<!--        &lt;!&ndash;        <el-button type = "warning" size = "small" aria-autocomplete="off" >注 册</el-button>&ndash;&gt;-->
<!--        <el-button type = "warning" size = "small" aria-autocomplete="off" @click = "$router.push('/register')">注 册</el-button>-->
<!--        &lt;!&ndash;        <el-button type = "warning" size = "small" aria-autocomplete="off" @click = "$router.push('/register')">注 册</el-button>&ndash;&gt;-->
<!--      </div>-->
    </div>

    </div>
  </div>

</template>

<script>
import {setRouters} from "@/router";

export default {
  name: "Login",
  data() {
    return {
      user: {},
      checked : false,
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
              //localStorage.removeItem(menus)
              localStorage.setItem("user",JSON.stringify((res.data)))//存储用户信息到浏览器
              localStorage.setItem("menus",JSON.stringify((res.data.menus)))//存储用户信息到浏览器
              localStorage.setItem("ids",JSON.stringify((res.data.ids)))
              //动态设置当前路由
              setRouters()
              this.$router.push("/")
              this.$message.success("登录成功");


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
  .theme-form {
    font-size: 24px;
    font-weight: 500;
    margin-bottom: 5px;
    color: #2b2b2b;
  }
  h1, h2, h3, h4, h5, h6, .h1, .h2, .h3, .h4, .h5, .h6 {
    margin-bottom: 0.5rem;
    font-weight: 500;
    line-height: 1.2;
    color: #3d4465;
  }
  p {
    margin-bottom: 25px;
    font-size: 14px;
    color: #898989;
    display: block;
    margin-block-start: 1em;
    margin-block-end: 1em;
    margin-inline-start: 0px;
    margin-inline-end: 0px;
  }
  .col-form-label{
    padding-top: calc(0.375rem + 1px);
    padding-bottom: calc(0.375rem + 1px);
    margin-bottom: 0;
    font-size: 16px;
    line-height: 1.5;
    color: #7e7e7e;
  }
  .p-0{
    padding: 0!important;
    width : 370px;
  }
  .login-main{
    width: 450px;
    padding: 40px;
    border-radius: 10px;
    -webkit-box-shadow: 0 0 37px rgb(8 21 66 / 5%);
    box-shadow: 0 0 37px rgb(8 21 66 / 5%);
    margin: 0 auto;
    background-color: #fff;
  }
  /deep/ .el-input__inner{
    height : 56px;
  }
  .text-primary{
    top: 10px;
    margin-left:225px;
    font-size: 14px;
    color: #7366ff !important;
  }
  .btn-primary{
    margin-top : 22px;
    height: 43px;
    width: 370px;
    color: #ffffff;
    background-color: #7366ff;
    border-color: #7366ff;
    text-transform: capitalize;
    display: inline-block;
    text-align: center;
    vertical-align: middle;
    user-select: none;
    border: 1px solid transparent;
    padding: 6px 28px;
    font-weight: 400;
    font-size: 14px;
    line-height: 21px;
  }
</style>

