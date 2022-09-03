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
    ANOTHER_WORLD_KEY: StoreUtil.fetch("ANOTHER_WORLD_KEY") || null,
    PUBLIC_KEY: StoreUtil.fetch("PUBLIC_KEY") || null,
    count: 1,
  },
  //同步函数
  mutations: {
    /**
     * 获取并存储新的state方法
     * @param state state.commit("方法名")调用
     */
    SAVE_STATE: (state) => {
      state.count++;
      console.log();
      state.PUBLIC_KEY = api.getPublicKey();
      StoreUtil.save(
        state.ANOTHER_WORLD_KEY,
        RSAUtil.encryptedData(AESUtil.getKey(), state.PUBLIC_KEY)
      );
    },
  },
  //异步函数
  actions: {},
  //分类管理
  modules: {},
});
