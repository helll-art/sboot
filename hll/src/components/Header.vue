<template>
<div style = "line-height: 60px ; display: flex">
  <div style = "flex : 1 ">
    <span :class="collapseBtnClass" style = "cursor:pointer ; font-size : 20px" @click = "collapse"></span>
    <el-breadcrumb separator = "/" style = "display:  inline-block ; margin-left: 10px; ">
      <el-breadcrumb-item :to= "{path:'/'}" style = "font-size: 20px">首页</el-breadcrumb-item>
      <el-breadcrumb-item style = "font-size:  10px ; text-align: center ; ">用户管理</el-breadcrumb-item>
    </el-breadcrumb>
  </div>

  <el-dropdown style = "width: 70px ; cursor:pointer">
    <span>王小虎</span><i class="el-icon-arrow-down"></i>
    <!--            <i class="el-icon-setting" style="margin-right: 15px"></i>-->
    <el-dropdown-menu v-slot="dropdown" style = "width : 100px ; text-align: center ">
      <el-dropdown-item style = "font-size: 14px ; padding : 5px 0">个人信息</el-dropdown-item>
      <el-dropdown-item style = "font-size: 14px ; padding : 5px 0">退出</el-dropdown-item>
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
  }
}
</script>

<style scoped>

</style>