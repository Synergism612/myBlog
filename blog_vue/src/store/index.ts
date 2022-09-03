import { createStore } from "vuex";
import AESUtil from "@/utils/AESUtil";
import RSAUtil from "@/utils/RSAUtil";
import StoreUtil from "@/utils/StoreUtil";
import { api } from "@/api/api";

/**
 * 数据仓库
 */
export default createStore({
  //存放数据
  state: {
    // 检查本地记录是否包含
    ANOTHER_WORLD_KEY: StoreUtil.fetch("ANOTHER_WORLD_KEY") || "",
    PUBLIC_KEY: StoreUtil.fetch("PUBLIC_KEY") || "",
  },
  //同步函数
  mutations: {
    /**
     * 为state.ANOTHER_WORLD_KEY赋值并存储
     * @param state state
     * @param ANOTHER_WORLD_KEY 密钥
     */
    SET_ANOTHER_WORLD_KEY: (state, ANOTHER_WORLD_KEY: string) => {
      state.ANOTHER_WORLD_KEY = ANOTHER_WORLD_KEY;
      StoreUtil.save("ANOTHER_WORLD_KEY", ANOTHER_WORLD_KEY);
    },

    /**
     * 为state.ANOTHER_WORLD_KEY赋值并存储
     * @param state state
     * @param PUBLIC_KEY 密钥
     */
    SET_PUBLIC_KEY: (state, PUBLIC_KEY: string) => {
      state.PUBLIC_KEY = PUBLIC_KEY;
      StoreUtil.save("ANOTHER_WORLD_KEY", PUBLIC_KEY);
    },
  },
  //异步函数
  actions: {},
  //分类管理
  modules: {},
});
