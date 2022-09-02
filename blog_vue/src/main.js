import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

/**
 * 引入ElementPlus
 */
import ElementPlus from "element-plus";

/**
 * 引入axios，配置$axios。
 * 在页面中就可以用this.$axios来获取axios
 */
import axios from "axios";

const app = createApp(App);
app.use(router).use(store).use(ElementPlus);
app.config.globalProperties.$axios = axios;
app.mount("#app");
