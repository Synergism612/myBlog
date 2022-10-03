import { createStore } from "vuex";
import StoreUtil from "@/utils/StoreUtil";
import UserInfo from "@/entity/UserInfo";

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
    EVIL_EYE: StoreUtil.fetch("EVIL_EYE") || "",
    loginID: StoreUtil.fetch("loginID") || "",
    userInfo: JSON.parse(StoreUtil.fetch("userInfo") || "{}"),
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
     * 为state.PUBLIC_KEY赋值并存储
     * @param state state
     * @param PUBLIC_KEY 公钥
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

    /**
     * 为state.EVIL_EYE赋值并存储
     * @param state state
     * @param EVIL_EYE 密钥
     */
    SET_EVIL_EYE: (state, EVIL_EYE: string) => {
      state.EVIL_EYE = EVIL_EYE;
      StoreUtil.save("EVIL_EYE", EVIL_EYE);
    },

    /**
     * 为state.userInfo赋值并存储
     * @param state state
     * @param userInfo 用户信息
     */
    SET_USER_INFO: (state, userInfo: UserInfo) => {
      state.userInfo = userInfo;
      StoreUtil.save("userInfo", JSON.stringify(userInfo));
    },
    /**
     * 为state.loginID赋值并存储
     * @param state state
     * @param loginID 用户信息对应密钥
     */
    SET_LOGIN_ID: (state, loginID: string) => {
      state.loginID = loginID;
      StoreUtil.save("loginID", loginID);
    },

    /**
     * 删除state中的所有数据
     * @param state state
     */
    DELECT_ALL_KEY: (state) => {
      state.KEY = "";
      state.loginID = "";
      state.EVIL_EYE = "";
      state.userInfo = "";
      state.PUBLIC_KEY = "";
      state.ANOTHER_WORLD_KEY = "";
      StoreUtil.save("KEY", "");
      StoreUtil.save("loginID", "");
      StoreUtil.save("EVIL_EYE", "");
      StoreUtil.save("userInfo", "");
      StoreUtil.save("PUBLIC_KEY", "");
      StoreUtil.save("ANOTHER_WORLD_KEY", "");
    },

    DELECT_USER_INFO: (state) => {
      state.userInfo = "";
      StoreUtil.save("userInfo", "");
    },

    DELECT_LOGIN_ID: (state) => {
      state.loginID = "";
      StoreUtil.save("loginID", "");
    },
  },
  //
  getters: {
    //get方法，也可以不写
    getUser: (state): UserInfo => {
      return state.userInfo as UserInfo;
    },
  },
  //异步函数
  actions: {},
  //分类管理
  modules: {},
});
