<template>

  <el-container style = "min-height: 100vh">
    <el-aside width="sideWidth + 'px'" style="background-color: rgb(238, 241, 246); box-shadow: 2px 0 6px rgb(0 21 41 / 35%)">
      <Aside  :isCollapse = "isCollapse" :logoTextShow = "logoTextShow" />
    </el-aside>

    <el-container>
      <el-header style = "border-bottom:1px solid #929494;height:100px ">
        <Header :collapseBtnClass = "collapseBtnClass" :collapse = "collapse" :user = "user"/>
      </el-header>

      <el-main>
<!--        表示当前页面的子路由会在router-view 里面展示-->
        <router-view @refreshUser = "getUser"/>
      </el-main>
    </el-container>
  </el-container>

</template>

<script>
// @ is an alias to /src

import Aside from "@/components/Aside.vue";
import Header from "@/components/Header.vue";
export default {
  components: {Aside
  ,Header},
  // created() {
  //   //从后台获取最新的user数据
  //   this.getUser()
  // },
  data() {
    return {
      collapseBtnClass:'el-icon-s-fold',
      isCollapse: false,
      sideWidth:200,
      logoTextShow:true,
      user: {}
    }
  },

  methods:{
    collapse(){
      this.isCollapse = !this.isCollapse
      if(this.isCollapse){
        this.sideWidth = 64;
        this.collapseBtnClass = 'el-icon-s-unfold'
        this.logoTextShow = false;
      }else{
        this.sideWidth = 200;
        this.collapseBtnClass = 'el-icon-s-fold'
        this.logoTextShow = true;
      }
    },
    getUser(){
      let username = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).username : ""
      //从后台获取User数据
      this.request.get("/user/username/" + username).then(res =>{
        //重新赋值后台的最新User数据
      this.user = res.data
      })
    }
  }
}
</script>
