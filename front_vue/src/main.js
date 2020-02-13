import Vue from 'vue';
import App from './App.vue';
import store from './store';
import router from './router';
import vuetify from './plugins/vuetify';
import custom from './plugins';
import 'material-design-icons-iconfont/dist/material-design-icons.css';

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  vuetify,
  custom,
  // beforeCreate() {
  //   this.$store.dispatch('beforeStart');
  // },

  render: h => h(App),
}).$mount('#app');
