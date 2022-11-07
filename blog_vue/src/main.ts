import { createApp } from "vue";
import App from "src/App.vue";
import router from "src/router";
import { store } from "src/store";

import "./assets/less/all.less";

/**
 * 引入ElementPlus
 */
import ElementPlus from "element-plus";
import zhCn from "element-plus/es/locale/lang/zh-cn";
import "element-plus/dist/index.css";

/**
 * 引入axios，配置$axios。
 * 在页面中就可以用this.$axios来获取axios
 */
import axios from "src/axios/axios";
import { api } from "./api/api";

/**
 * 引入动态粒子效果
 */
import Particles from "particles.vue3";

// 全局引入动画相关的样式
import "animate.css";

/* fontawesome 核心 */
import { library } from "@fortawesome/fontawesome-svg-core";
/* awesome图库 */
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
/* specific图库 */
import { fas } from "@fortawesome/free-solid-svg-icons";
library.add(fas);

const app = createApp(App);
api.getPublicKey().then((): void => {
  app
    .use(router)
    .use(store)
    .use(ElementPlus, {
      locale: zhCn,
    })
    .use(Particles);
  app.component("font-awesome-icon", FontAwesomeIcon);
  app.config.globalProperties.$axios = axios;
  app.mount("#app");
});
