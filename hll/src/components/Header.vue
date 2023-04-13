<template>

  <div style = "height: 100px ; display: flex">
  <div style = "flex : 1 ; margin-top: 50px" >
    <span :class="collapseBtnClass" style = "cursor:pointer ; font-size : 20px ;margin-bottom: 10px;" @click = "collapse"></span>
    <el-breadcrumb separator = "/" style = "display:  inline-block ; margin-left: 10px;">
      <el-breadcrumb-item :to= "{path:'/'}" style = "font-size: 20px">首页</el-breadcrumb-item>
      <el-breadcrumb-item style = "font-size:  19px">{{ currentPathName }}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>

<!--  <el-dropdown style = "width: auto ; cursor:pointer ; text-align: right">-->
<!--    <div style="display:  inline-block">-->
<!--&lt;!&ndash;      本地图片<img :src="require('../assets/logo.png')">&ndash;&gt;-->
<!--      <img :src = "user.avatarUrl" alt = ""-->
<!--           style = "width : 60px ; border-radius:  50%; position : relative ; top : 10px ; right : 5px ; margin-top: 20px">-->

<!--      <span style="font-size: 18px ;display: flow ">{{ user.nickname }}</span><i class="el-icon-arrow-down" style = "margin-left: 5px ;margin-bottom:10px"></i>-->
<!--      <span style="font-size: 13px">{{ user.userstatus }}</span>-->
<!--    </div>-->
    <el-dropdown style = "width: auto ; cursor:pointer ; text-align: right">
    <div class="my-account-wrapper">
      <div class="account-wrapper">
        <div class="account-control">
          <a class="login header-profile" href="#" title="Sign in">
            <div class="header-info">
<!--              <div style = "height : 11px"></div>-->
              <span style="font-size: 18px ;display: flow ;margin-top:10px">{{ user.nickname }}</span>
<!--              <div style = "height : 20px"></div>-->
                <small  v-if="user.userstatus === '1'">管理员</small>
                <small  v-else-if="user.userstatus === '2'">经理</small>
                <small  v-else-if="user.userstatus === '3'">监管人员</small>
            </div>
            <img :src = "user.avatarUrl" alt = ""
                 style = "width : 60px ; border-radius:  50%; position : relative ; top : -2px ; right : 5px ; margin-top: 20px">
          </a>
          </div>
        </div>
      </div>

    <!--            <i class="el-icon-setting" style="margin-right: 15px"></i>-->
    <el-dropdown-menu v-slot="dropdown" style = "width : 100px ; text-align: center ">
      <el-dropdown-item style = "font-size: 14px ; padding : 5px 0">个人信息</el-dropdown-item>
      <el-dropdown-item style = "font-size: 14px ; padding : 5px 0">
<!--        <router-link to="/login" style = "text-decoration: none " >退出</router-link>-->
        <span style = "text-decoration: none" @click = "logout">退出</span>
      </el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</div>
</template>

<script>
export default {
  name: "Header",
  props : {
    collapseBtnClass : String,
    collapse: Function
  },
  computed: {
    currentPathName(){
      return this.$store.state.currentPathName;
    }
  },
  data(){
    return  {
      user : localStorage.getItem("user") ?JSON.parse(localStorage.getItem("user")) : {}
    }
  },
  methods:{
    collapse(){
      this.$emit("asideCollapse")
    },
    logout(){
      this.$router.push("/login")
      localStorage.removeItem("user")
      this.$message.success("退出成功")
    }
  },
  watch : {
    currentPathName (newVal , oldVal){
      console.log(newVal)
    }
  }
}
</script>

<style scoped>
  .my-account-wrapper{
    height:99px;
    background-color: #ffffff;
    padding: 10px 12px;
    position: relative;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
  }
  .my-account-wrapper .account-control {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  a {
    color: #7e7e7e;
    text-decoration: none;
  }
  .my-account-wrapper .account-control .header-profile .header-info span {
    font-weight: 500;
    line-height: 24px;
    color: #2b2b2b;
  }
  .my-account-wrapper .account-control .header-profile img {

    background-color: #f8f8f8;
    -webkit-border-radius: 5px;
    -moz-border-radius: 5px;
    border-radius: 5px;
  }
  .my-account-wrapper .account-control .header-profile .header-info {
    padding-right: 20px;
    text-align: right;
  }
.my-account-wrapper .account-control .header-profile .header-info small {
    display: block;
    font-weight: 400;
    font-size: 12px;
    line-height: 1;
    font-family: "Roboto", sans-serif;
  }
  img, svg {
    vertical-align: middle;
  }
  .my-account-wrapper .account-control .header-profile {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
</style>