import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "../store"

Vue.use(VueRouter)

const routes = [

  // {
  //   path: '/',
  //   name: 'Manage',
  //   component: () => import('../views/Manage.vue'),
  //   redirect : "/home",
  //   children: [
  //     {path: 'user', name: '用户管理', component: () => import('../views/User.vue')},
  //     {path: 'home', name: '', component: () => import('../views/Home1.vue')},
  //     {path: 'elder', name: '用户管理/老龄人', component: () => import('../views/Elder.vue')},
  //     {path: 'case', name: '用户管理/病例', component: () => import('../views/Case.vue')},
  //     {path: 'drug', name: '健康管理/药品', component: () => import('../views/Drug.vue')},
  //     {path: 'file', name: '文件管理', component: () => import('../views/File.vue')},
  //     {path: 'company', name: '公司管理', component: () => import('../views/Company.vue')},
  //     {path: 'institution', name: '机构信息', component: () => import('../views/Institution.vue')},
  //     {path: 'menu', name: '菜单管理', component: () => import('../views/Menu.vue')},
  //     {path: 'role', name: '', component: () => import('../views/Role.vue')},
  //   ]
  // },
  {
    path : '/',
    // name : 'Manage',
    component : () => import('../views/Manage.vue'),
    redirect : '/home',
    children : [
      {
        path : 'home',
        name : 'Home',
        component:() => import ("../views/Home1.vue")
      }
    ]
  },
  {
    path : '/404',
    name : '404',
    component : () => import('../views/404.vue')
  },
  {
    path : '/video',
    name : 'video',
    component : () => import('../views/Video.vue')
  },
  {
    path : '/picture',
    name : 'Picture',
    component : () => import('../views/Picture.vue')
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },
  {
    path : '/Login',
    name :'Login',
    component : () => import('../views/Login.vue')
  },
  {
    path : '/register',
    name : 'Register',
    component : () => import('../views/Register.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})


//提供一个重置路由的方法
export const resetRouter = () =>{
  router.matcher = new VueRouter( {
    mode : 'history',
    base : process.env.BASE_URL,
    routes
  })
}
export const setRouters = () => {
  //从浏览器缓存中取出menus
  const storeMenus = localStorage.getItem("menus");
  //判断menus有没有东西
  if(storeMenus){
    //拼装动态路由

    const currentRouteNames = router.getRoutes().map(v => v.name)
  if(!currentRouteNames.includes('Manage')){
    const manageRoute = { path : '/' , name : 'Manage',component : () => import('../views/Manage.vue') , redirect : "/home"  , children : [
        {path:'person' , name : '个人信息',components:() => import('../views/Person.vue')},
        {path:'password' , name : '修改密码',components:() => import('../views/Person.vue')},
      ]}
    const menus = JSON.parse(storeMenus)
    menus.forEach(item => {
      if(item.path){
        let itemMenu = {path : item.path.replace("/" , "") , name : item.name , component : () => import('../views/' + item.pagePath + '.vue')}
        manageRoute.children.push(itemMenu)
      }else if(item.children.length){
        item.children.forEach(item => {
          if(item.path){
            let itemMenu = {path : item.path.replace("/" , "") , name : item.name , component : () => import('../views/' + item.pagePath + '.vue')}
            manageRoute.children.push(itemMenu)
          }
        })
      }
    })
    //动态添加到现在的路由对象中去
    router.addRoute(manageRoute)

  }


  }
}
//重置就再set一次路由
setRouters()
// router.addRoute({
//   path: '/',
//   name: 'Manage',
//   component: () => import('../views/Manage.vue'),
//   redirect : "/home",
//   children: [
//     {path: 'user', name: '用户管理', component: () => import('../views/User.vue')},
//     {path: 'home', name: '', component: () => import('../views/Home1.vue')},
//     {path: 'elder', name: '用户管理/老龄人', component: () => import('../views/Elder.vue')},
//     {path: 'case', name: '用户管理/病例', component: () => import('../views/Case.vue')},
//     {path: 'drug', name: '健康管理/药品', component: () => import('../views/Drug.vue')},
//     {path: 'file', name: '文件管理', component: () => import('../views/File.vue')},
//     {path: 'company', name: '公司管理', component: () => import('../views/Company.vue')},
//     {path: 'institution', name: '机构信息', component: () => import('../views/Institution.vue')},
//     {path: 'menu', name: '菜单管理', component: () => import('../views/Menu.vue')},
//     {path: 'role', name: '', component: () => import('../views/Role.vue')},
//   ]
// },)
router.beforeEach((to,from,next) => {
  localStorage.setItem("currentPathName", to.name)
  store.commit("setPath")
  const storeMenus =  localStorage.getItem("menus");
  //未找到路由的情况
  if(!to.matched.length){
    if(storeMenus){
      next("/404")
    }else{
      next("/login")
    }
  }
  //其他情况都放行
  next()

})
export default router
