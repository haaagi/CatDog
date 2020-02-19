import Vue from 'vue';
import App from './App.vue';
import store from './store';
import router from './router';
import vuetify from './plugins/vuetify';
import 'material-design-icons-iconfont/dist/material-design-icons.css';
import VueMaterial from 'vue-material';
import 'vue-material/dist/vue-material.css';
import 'vue-material/dist/theme/black-green-light.css';
import Directives from './plugins/directives';
import io from 'socket.io-client';
const socket = io('http://70.12.247.57:8081/');
Vue.prototype.$socket = socket;
Vue.use(VueMaterial);
Vue.use(Directives);
Vue.config.productionTip = false;
new Vue({
  store,
  router,
  vuetify,
  // beforeCreate() {
  //   this.$store.dispatch('beforeStart');
  // },
  render: h => h(App),
}).$mount('#app');
