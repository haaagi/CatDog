import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Signup from '../views/Signup'
import UserDetail from '../views/UserDetail'
import UserList from '../views/UserList'
import MovieDetail from '../views/MovieDetail'


Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  routes: [
  {
    path: '/home',
    name: 'home',
    component: Home
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/signup',
    name: 'signup',
    component: Signup
  },
  {
    path: '/userdetail',
    name: 'userdetail',
    component: UserDetail,
  },
  {
    path: '/userlist',
    name: 'userlist',
    component: UserList,
  },
]
})


export default router
