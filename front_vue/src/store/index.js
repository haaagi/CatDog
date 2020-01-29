import Vuex from 'vuex';
import Vue from 'vue';
import auth from './modules/auth';

Vue.use(Vuex)

const store = new Vuex.Store({
  modules: {
    auth,
  }
});
export default store;



// export default new Vuex.Store({
//   state: { // 데이터 getters 데이터 가져오기 / 밖으로 나가는건 getters랑 actions 다 
//   },
//   mutations: { // 선출데이터 내보내줌 // data 수정하기 
//   },
//   actions: { // 메소드 느낌  mutations 에있는 애들 쓰려면 commit 써야한다. 
//   },
//   modules: {
//   }
// })

// // methods에 actions 넣고
// // computed에 getters 를 쓴다. 
