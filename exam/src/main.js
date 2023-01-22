// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from "vue";
import App from "./App";
import router from "./router";
import echarts from "echarts";
import axios from "axios";
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
import VueCookies from "vue-cookies";
import store from "@/vuex/store";
import VueSession from "vue-session";
//import  createAuth0  from '../../@auth0/auth0-vue';
import { createAuth0 } from "../node_modules/@auth0/auth0-vue";
//import { createApp } from 'vue';

/* const app = Vue.createApp(App);
app.use(
   createAuth0({
    domain: "dev-zw1c1dfq.us.auth0.com",
    client_id: "vkrC4LOKshICcnrZby9BahF77lgZJult",
    redirect_uri: window.location.origin
  })
);
app.mount('#app'); */
Vue.use(ElementUI);
Vue.use(VueCookies);
Vue.use(VueSession);
Vue.config.productionTip = false;
Vue.prototype.bus = new Vue();
Vue.prototype.$echarts = echarts;
Vue.prototype.$axios = axios;

//const app = createApp(App);

//app.mount('#app');

new Vue({
   el: "#app",
   router,
   store,
   render: (h) => h(App),
   components: { App },
   template: "<App/>",
});
