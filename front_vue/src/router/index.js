import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import Login from '../views/Login.vue'
import Signup from '../views/Signup.vue'
import Userdetail from '../views/Userdetail.vue'
import EditProfile from '../views/EditProfile.vue'


Vue.use(VueRouter)

const router = new VueRouter({
  mode: 'history',
  routes: [
  {
    path: '/',
    name: 'home',
    component: Home
  },
  {
    path: '/about',
    name: 'about',
    component: About
  },
  {
    path:'/login',
    name: 'login',
    component: Login
  },
  {
    path:'/signup',
    name:'signup',
    component: Signup
  },
{
    path: '/userdetail', 
    name:'userdetail', 
    component: Userdetail
  }, 
  {
    path: '/editprofile', 
    name: 'editprofile', 
    component: EditProfile
  },

]
})


export default router