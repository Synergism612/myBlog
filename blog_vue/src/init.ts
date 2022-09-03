import { api } from "@/api/api";
import { useStore } from "vuex";
import AESUtil from "@/utils/AESUtil";
/**
 * 初始化方法
 */
export default function init(): void {
  const store = useStore();
  const PUBLIC_KEY = api.getPublicKey();
  store.commit("SET_PUBLIC_KEY", PUBLIC_KEY);
  const key = AESUtil.getKey();
  const SET_ANOTHER_WORLD_KEY = AESUtil.encrypt(key, PUBLIC_KEY);
  store.commit("SET_ANOTHER_WORLD_KEY", SET_ANOTHER_WORLD_KEY);
}
