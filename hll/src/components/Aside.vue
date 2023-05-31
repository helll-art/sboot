<template>
  <el-menu :default-openeds="['1', '3']" style = "min-height: 100% ; overflow-x:hidden ;max-width: 300px"
           background-color = "#fff" text-color = "#7f7184"
           active-text-color = "#7366ff"
           :collapse-trasition = "false"
           :collapse="isCollapse"

            router>
    <div style = "height : 100px;line-height: 60px;text-align: center">
      <img src = "../picture/download.png" alt = "" style = "width : 60px ; position : relative ; margin-top: 20px; margin-left :20px">
      <img src = "../picture/logo.png" alt = "" style = "position : relative ; margin-right : 20px; height : 42px; margin-left: 20px">
<!--      <b style = "color : white" v-show = "logoTextShow">老龄人监管系统</b>-->
    </div>

    <div v-for = "item in menus" :key = "item.id">
      <div v-if = "item.path">
        <el-menu-item :index = "item.path">
          <i :class="item.icon"></i>
          <span slot = "title">{{item.name}}</span>
        </el-menu-item>
      </div>
      <div v-else>
        <el-submenu :index = "item.id + ''">
          <template slot = "title">
            <i :class="item.icon"></i>
            <span slot = "title">{{item.name}}</span>
          </template>
          <div v-for = "subItem in item.children" :key = "subItem.id">
            <el-menu-item :index = "subItem.path">
              <i :class="subItem.icon"></i>
              <span slot = "title">{{subItem.name}}</span>
            </el-menu-item>
          </div>
        </el-submenu>
      </div>
    </div>
<!--    <el-submenu index="1">-->
<!--      <template slot="title"><i class="el-icon-message"></i><span slot = "title">用户管理</span></template>-->
<!--      <el-menu-item-group>-->
<!--        <el-menu-item index="/" ><i class = "el-icon-user-solid"></i>监管人员</el-menu-item>-->
<!--        <el-menu-item index="1-2"><i class = "el-icon-user"></i>老人家属</el-menu-item>-->
<!--        <el-menu-item index="/user"><i class = "el-icon-s-check"></i>管理员</el-menu-item>-->
<!--        <el-menu-item index="/role"><i class = "el-icon-s-check"></i>角色管理</el-menu-item>-->
<!--        <el-menu-item index="/menu"><i class = "el-icon-s-check"></i>菜单管理</el-menu-item>-->
<!--      </el-menu-item-group>-->
<!--    </el-submenu>-->
<!--    <el-submenu index="2">-->
<!--      <template slot="title"><span slot = "title"><i class = "el-icon-suitcase"></i>健康管理</span></template>-->
<!--      <el-menu-item-group>-->
<!--        <el-menu-item index="/elder"><i class = "el-icon-first-aid-kit"></i>健康档案</el-menu-item>-->
<!--        <el-menu-item index="/case"><i class = "el-icon-suitcase-1"></i>病例档案</el-menu-item>-->
<!--        <el-menu-item index="/drug"><i class = "el-icon-suitcase-1"></i>药品管理</el-menu-item>-->
<!--      </el-menu-item-group>-->
<!--    </el-submenu>-->
<!--    <el-submenu index="3">-->
<!--      <template slot="title"><i class = "el-icon-set-up"></i>生活管理</template>-->
<!--      <el-menu-item-group>-->
<!--        <el-menu-item index="4-1"><i class = "el-icon-fork-spoon"></i>每日餐饮</el-menu-item>-->
<!--        <el-menu-item index="4-2"><i class = "el-icon-data-board"></i>外出报备</el-menu-item>-->
<!--      </el-menu-item-group>-->
<!--    </el-submenu>-->
<!--    <el-submenu index="4">-->
<!--      <template slot="title"><span slot = "title"><i class = "el-icon-pie-chart"></i>入住管理</span></template>-->
<!--        <el-menu-item-group>-->
<!--          <el-menu-item index="/institution"><i class = "el-icon-receiving"></i>综合查询</el-menu-item>-->
<!--          <el-menu-item index="4-1"><i class = "el-icon-link"></i>入住登记</el-menu-item>-->
<!--          <el-menu-item index="4-2"><i class = "el-icon-connection"></i>寝室分配</el-menu-item>-->
<!--        </el-menu-item-group>-->
<!--    </el-submenu>-->
<!--    <el-submenu index="5">-->
<!--      <template slot="title"><span slot = "title"><i class = "el-icon-bangzhu"></i>安全管理</span></template>-->
<!--      <el-menu-item-group>-->
<!--        <el-menu-item index="4-1"><i class = "el-icon-news"></i>事故记录</el-menu-item>-->
<!--        <el-menu-item index="/company"><i class = "el-icon-office-building"></i>公司管理</el-menu-item>-->
<!--        <el-menu-item index="/file"><i class = "el-icon-news"></i>访客记录</el-menu-item>-->
<!--        <el-menu-item ><i class = "el-icon-map-location"></i>老人位置</el-menu-item>-->
<!--      </el-menu-item-group>-->
<!--    </el-submenu>-->
<!--    <el-submenu index="6">-->
<!--      <template slot="title">-->
<!--        <span slot = "title"><i class = "el-icon-guide"></i>收费标准</span></template>-->
<!--      <el-menu-item-group>-->

<!--        <el-menu-item index="4-1"><i class = "el-icon-guide"></i>入住费用</el-menu-item>-->
<!--        <el-menu-item index="4-2"><i class = "el-icon-notebook-1"></i>护理费用</el-menu-item>-->
<!--      </el-menu-item-group>-->

<!--    </el-submenu>-->



  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props : {
    isCollapse :Boolean,
    logoTextShow : Boolean,
  },
  data(){
    return {
      menus :localStorage.getItem("menus") ? JSON.parse(localStorage.getItem("menus")) : []
    }
  },

}
</script>

<style scoped>
  /deep/ .el-aside{
    weight:300px;
  }
</style>