import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
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
    path: '/userdetail', 
    name:'userdetail', 
    component: Userdetail
  }, 
  {
    path: '/editprofile', 
    name: 'editprofile', 
    component: EditProfile
  }

]
})


export default router