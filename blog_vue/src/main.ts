import { createApp } from "vue";
import App from "@/App.vue";
import router from "@/router";
import { store } from "@/store";

import "./assets/less/all.less";

/**
 * 引入ElementPlus
 */
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";

/**
 * 引入axios，配置$axios。
 * 在页面中就可以用this.$axios来获取axios
 */
import axios from "@/axios/axios";
import { api } from "./api/api";

const app = createApp(App);
api.getPublicKey().then(() => {
  app.use(router).use(store).use(ElementPlus);
  app.config.globalProperties.$axios = axios;
  app.mount("#app");
});
