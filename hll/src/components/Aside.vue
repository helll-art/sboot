<template>
  <el-menu :default-openeds="['1', '3']" style = "min-height: 100% ; overflow-x:hidden"
           background-color = "rgb(48,65,86)" text-color = "#fff"
           active-text-color = "#ffd04b"
           :collapse-trasition = "false"
           :collapse="isCollapse"
            router>
    <div style = "height : 60px;line-height: 60px;text-align: center">
      <img src = "../assets/logo.png" alt = "" style = "width : 20px ; position : relative ; top : 5px ; margin-right: 5px;">
      <b style = "color : white" v-show = "logoTextShow">老龄人监管系统</b>
    </div>
    <el-submenu index="1">
      <template slot="title"><i class="el-icon-message"></i><span slot = "title">用户管理</span></template>
      <el-menu-item-group>
        <el-menu-item index="/" ><i class = "el-icon-user-solid"></i>监管人员</el-menu-item>
        <el-menu-item index="1-2"><i class = "el-icon-user"></i>老人家属</el-menu-item>
        <el-menu-item index="/user"><i class = "el-icon-s-check"></i>管理员</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="2">
      <template slot="title"><span slot = "title"><i class = "el-icon-suitcase"></i>健康管理</span></template>
      <el-menu-item-group>
        <el-menu-item index="/elder"><i class = "el-icon-first-aid-kit"></i>健康档案</el-menu-item>
        <el-menu-item index="/case"><i class = "el-icon-suitcase-1"></i>病例档案</el-menu-item>
        <el-menu-item index="/drug"><i class = "el-icon-suitcase-1"></i>药品管理</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="3">
      <template slot="title"><i class = "el-icon-set-up"></i>生活管理</template>
      <el-menu-item-group>
        <el-menu-item index="4-1"><i class = "el-icon-fork-spoon"></i>每日餐饮</el-menu-item>
        <el-menu-item index="4-2"><i class = "el-icon-data-board"></i>外出报备</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="4">
      <template slot="title"><span slot = "title"><i class = "el-icon-pie-chart"></i>入住管理</span></template>
        <el-menu-item-group>
          <el-menu-item index="4-1"><i class = "el-icon-link"></i>入住登记</el-menu-item>
          <el-menu-item index="4-2"><i class = "el-icon-connection"></i>寝室分配</el-menu-item>
        </el-menu-item-group>
    </el-submenu>
    <el-submenu index="5">
      <template slot="title"><span slot = "title"><i class = "el-icon-bangzhu"></i>安全管理</span></template>
      <el-menu-item-group>
        <el-menu-item index="4-1"><i class = "el-icon-news"></i>事故记录</el-menu-item>
        <el-menu-item index="4-2"><i class = "el-icon-news"></i>访客记录</el-menu-item>
      </el-menu-item-group>
    </el-submenu>
    <el-submenu index="6">
      <template slot="title">
        <span slot = "title"><i class = "el-icon-guide"></i>收费标准</span></template>
      <el-menu-item-group>

        <el-menu-item index="4-1"><i class = "el-icon-guide"></i>入住费用</el-menu-item>
        <el-menu-item index="4-2"><i class = "el-icon-notebook-1"></i>护理费用</el-menu-item>
      </el-menu-item-group>

    </el-submenu>



  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props : {
    isCollapse :Boolean,
    logoTextShow : Boolean
  }
}
</script>

<style scoped>

</style>