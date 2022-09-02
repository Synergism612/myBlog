import { createStore } from "vuex";

/**
 * 数据仓库
 */
export default createStore({
  //存放数据
  state: {
    ANOTHER_WORLD_KEY: "",
    PUBLIC_KEY: "",
  },
  //同步函数
  mutations: {
    GET_ANOTHER_WORLD_KEY: {},
  },
  //异步函数
  actions: {},
  //分类管理
  modules: {},
});
