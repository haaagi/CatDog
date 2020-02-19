import Vue from 'vue';
import VueRouter from 'vue-router';
import Home from '../views/Home.vue';
import ModalPost from '../components/ModalPost.vue';
// import BoardDetail from '../components/common/BoardDetail.vue';
import MainPage from '../views/MainPage.vue';
import Login from '../views/Login.vue';
import Signup from '../views/Signup.vue';
import UserDetail from '../views/UserDetail.vue';
import EditProfile from '../views/EditProfile.vue';
import Posting from '../views/Posting.vue';
import Health from '../views/Health.vue';
import Info from '../views/Info.vue';
import FollowDetail from '../views/FollowDetail.vue';
import TagDetail from '../views/TagPage.vue';
import PageNotFound from '@/views/PageNotFound';
import Search from '../views/Search.vue';

import AboutUs from '../views/AboutUs.vue';
import Board from '../views/Board.vue';
import writeBoard from '../components/writeBoard.vue';
import selectedBoard from '../components/selectedBoard.vue';
import updateBoard from '../components/updateBoard.vue';
import Chat from '../views/Chat';

Vue.use(VueRouter);

const router = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '*',
      redirect: '/404',
    },
    {
      path: '/404',
      component: PageNotFound,
    },
    {
      path: '/',
      name: 'home',
      component: Home,
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
      component: UserDetail,
    },
    {
      path: '/modal',
      name: 'modal',
      component: ModalPost,
    },
    {
      path: '/chat',
      name: 'chat',
      component: Chat,
    },
    {
      path: '/main',
      name: 'main',
      component: MainPage,
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
    {
      path: '/health',
      name: 'health',
      component: Health,
    },
    {
      path: '/info',
      name: 'info',
      component: Info,
    },

    {
      path: '/followdetail/:nickname',
      name: 'followdetail',
      component: FollowDetail,
      props: true,
    },
    {
      path: '/aboutus',
      name: 'aboutus',
      component: AboutUs,
    },
    {
      path: '/board',
      name: 'board',
      component: Board,
    },
    {
      path: '/writeboard',
      name: 'writeboard',
      component: writeBoard,
    },
    {
      path: '/tagwdetail/:tagname',
      name: 'tagname',
      component: TagDetail,
      props: true,
    },
    {
      path: '/selectedboard/:bid',
      name: 'selectedboard',
      component: selectedBoard,
      props: true,
    },
    {
      path: '/updateboard/',
      name: 'updateboard',
      component: updateBoard,
      props: true,
    },
    // {
    //   path: '/search?keyword=:keywoed',
    //   name: 'search',
    //   component: About,
    //   props: true,
    // },

    {
      path: '/search',
      component: Search,
      props: route => ({ query: route.query.keyword }),
    },
  ],
});

export default router;
