import { createStore } from "vuex";
import StoreUtil from "@/utils/StoreUtil";

/**
 * 数据仓库
 */
const store = createStore({
  //存放数据
  state: {
    // 检查本地记录是否包含
    ANOTHER_WORLD_KEY: StoreUtil.fetch("ANOTHER_WORLD_KEY") || "",
    PUBLIC_KEY: StoreUtil.fetch("PUBLIC_KEY") || "",
    KEY: StoreUtil.fetch("KEY") || "",
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
     * @param PUBLIC_KEY 公钥加密密钥
     */
    SET_PUBLIC_KEY: (state, PUBLIC_KEY: string) => {
      state.PUBLIC_KEY = PUBLIC_KEY;
      StoreUtil.save("PUBLIC_KEY", PUBLIC_KEY);
    },

    /**
     * 为state.KEY赋值并存储
     * @param state state
     * @param KEY 密钥
     */
    SET_KEY: (state, KEY: string) => {
      state.KEY = KEY;
      StoreUtil.save("KEY", KEY);
    },

    DELECT_ALL_KEY: (state) => {
      state.KEY = "";
      state.PUBLIC_KEY = "";
      state.ANOTHER_WORLD_KEY = "";
      StoreUtil.save("KEY", "");
      StoreUtil.save("PUBLIC_KEY", "");
      StoreUtil.save("ANOTHER_WORLD_KEY", "");

    },
  },
  //异步函数
  actions: {},
  //分类管理
  modules: {},
});

export default store;
