import Vue from 'vue'
import VueRouter from 'vue-router'
import store from "../store"

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Manage',
    component: () => import('../views/Manage.vue'),
    redirect : "/home",
    children: [
      {path: 'user', name: '用户管理', component: () => import('../views/User.vue')},
      {path: 'home', name: '', component: () => import('../views/Home1.vue')},
      {path: 'elder', name: '用户管理/老龄人', component: () => import('../views/Elder.vue')},
      {path: 'case', name: '用户管理/病例', component: () => import('../views/Case.vue')},
      {path: 'drug', name: '健康管理/药品', component: () => import('../views/Drug.vue')},
      {path: 'file', name: '文件管理', component: () => import('../views/File.vue')},
    ]
  },
  {
    path : '/Home',
    name : 'Home',
    component:() => import ("../views/Home1.vue")
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
router.beforeEach((to,from,next) => {
  localStorage.setItem("currentPathName", to.name)
  store.commit("setPath")
  next()
})
export default router
