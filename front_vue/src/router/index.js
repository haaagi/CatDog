import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import About from '../views/About.vue';
import ModalPost from '../views/ModalPost.vue';
import Board from '../views/Board.vue';
// import BoardDetail from '../components/common/BoardDetail.vue';
import MainPage from '../views/MainPage.vue';
import TodoPage from '../views/TodoPage.vue';
import Login from '../views/Login.vue';
import Signup from '../views/Signup.vue';
import Userdetail from '../views/Userdetail.vue';
import EditProfile from '../views/EditProfile.vue';
import Posting from '../views/Posting.vue';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
    },
    {
      path: '/about',
      name: 'about',
      component: About,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/signup',
      name: 'signup',
      component: Signup,
    },
    {
      path: '/userdetail',
      name: 'userdetail',
      component: Userdetail,
    },
    {
      path: '/modal',
      name: 'modal',
      component: ModalPost,
    },
    {
      path: '/board',
      name: 'board',
      component: Board,
    },
    {
      path: '/main',
      name: 'main',
      component: MainPage,
    },
    {
      path: '/todo',
      name: 'todo',
      component: TodoPage,
    },
    {
      path: '/editprofile',
      name: 'editprofile',
      component: EditProfile,
    },
    {
      path: '/posting',
      name: 'posting',
      component: Posting,
    },
  ],
});

export default router;
