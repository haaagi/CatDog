import Vue from 'vue';
import App from './App.vue';
import store from './store';
import router from './router';
import vuetify from './plugins/vuetify';
// import * as VeeValidate from 'vee-validate';
// Vue.use(VeeValidate)
// import { ValidationProvider } from 'vee-validate';

// Register it globally
// main.js or any entry file.
// Vue.component('ValidationProvider', ValidationProvider);

Vue.config.productionTip = false;

new Vue({
  store,
  router,
  vuetify,

  render: h => h(App),
}).$mount('#app');
