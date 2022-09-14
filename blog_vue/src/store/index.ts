import { createStore } from "vuex";
import StoreUtil from "@/utils/StoreUtil";

/**
 * 数据仓库
 */
export const store = createStore({
  //存放数据
  state: {
    // 检查本地记录是否包含
    ANOTHER_WORLD_KEY: StoreUtil.fetch("ANOTHER_WORLD_KEY") || "",
    PUBLIC_KEY: StoreUtil.fetch("PUBLIC_KEY") || "",
    KEY: StoreUtil.fetch("KEY") || "",
    AUTH_ID: StoreUtil.fetch("AUTH_ID") || "",
  },
  //同步函数
  mutations: {
    /**
     * 为state.ANOTHER_WORLD_KEY赋值并存储
     * @param state state
     * @param ANOTHER_WORLD_KEY 密钥
     */
    SET_ANOTHER_WORLD_KEY: (state, ANOTHER_WORLD_KEY: string) => {
      // ANOTHER_WORLD_KEY = Base64Util.encode(ANOTHER_WORLD_KEY);
      state.ANOTHER_WORLD_KEY = ANOTHER_WORLD_KEY;
      StoreUtil.save("ANOTHER_WORLD_KEY", ANOTHER_WORLD_KEY);
    },

    /**
     * 为state.PUBLIC_KEY赋值并存储
     * @param state state
     * @param PUBLIC_KEY 公钥
     */
    SET_PUBLIC_KEY: (state, PUBLIC_KEY: string) => {
      // PUBLIC_KEY = Base64Util.encode(PUBLIC_KEY);
      state.PUBLIC_KEY = PUBLIC_KEY;
      StoreUtil.save("PUBLIC_KEY", PUBLIC_KEY);
    },

    /**
     * 为state.KEY赋值并存储
     * @param state state
     * @param KEY 密钥
     */
    SET_KEY: (state, KEY: string) => {
      // KEY = Base64Util.encode(KEY);
      state.KEY = KEY;
      StoreUtil.save("KEY", KEY);
    },

    /**
     * 为state.AUTH_ID赋值并存储
     * @param state state
     * @param KEY 密钥
     */
    SET_AUTH_ID: (state, AUTH_ID: string) => {
      // KEY = Base64Util.encode(KEY);
      state.AUTH_ID = AUTH_ID;
      StoreUtil.save("AUTH_ID", AUTH_ID);
    },
    /**
     * 删除state中的对称钥和加密对称钥
     * @param state state
     */
    DELECT_ALL_KEY: (state) => {
      state.KEY = "";
      state.AUTH_ID = "";
      state.PUBLIC_KEY = "";
      state.ANOTHER_WORLD_KEY = "";
      StoreUtil.save("KEY", "");
      StoreUtil.save("AUTH_ID", "");
      StoreUtil.save("PUBLIC_KEY", "");
      StoreUtil.save("ANOTHER_WORLD_KEY", "");
    },
  },
  //异步函数
  actions: {},
  //分类管理
  modules: {},
});
