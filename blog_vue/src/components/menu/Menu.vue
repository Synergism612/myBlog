<template>
  <div id="menu_container">
    <el-menu mode="horizontal" :ellipsis="ellipsis" router>
      <el-menu-item id="menu_logo" index="/blog/index">Synergism</el-menu-item>
      <el-menu-item index="/blog/index">网站首页</el-menu-item>
      <el-menu-item index="3">文章专栏</el-menu-item>
      <el-menu-item index="4">归档</el-menu-item>
      <el-menu-item index="3">资源分享</el-menu-item>
      <el-menu-item index="3">点点滴滴</el-menu-item>
      <el-menu-item index="3">关于本站</el-menu-item>
      <el-menu-item index="/blog/login" v-if="!ifLogin">登录</el-menu-item>
      <el-menu-item index="/blog/register" v-if="!ifLogin">注册</el-menu-item>
      <el-sub-menu v-if="ifLogin">
        <template #title>{{ userInfo.nickname }}</template>
        <el-menu-item index="/user">基本资料</el-menu-item>
        <el-menu-item index @click="logout()">退出登录</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
  <div id="menu_shade"></div>
</template>

<script lang="ts">
import { api } from "@/api/api";
import { store } from "@/store";
import Message from "@/utils/MessageUtil";
import StringUtil from "@/utils/StringUtil";
import { computed, defineComponent, reactive, toRefs, watch } from "vue";

export default defineComponent({
  /**
   * 组件内setup，setup最早创建，所以没有this
   */
  setup() {
    const viewData = reactive({
      ifLogin: false,
      ellipsis: false,
      userInfo: {
        nickname: "",
        icon: "",
      },
    });

    const logout = (): void => {
      api
        .logout(
          store.state.loginID,
          store.getters.getUser.nickname,
          store.getters.getUser.username
        )
        .then(({ data }) => {
          data;
          store.commit("DELECT_USER_INFO");
          store.commit("DELECT_LOGIN_ID");
          Message.successMessage("您已登出");
        })
    };

    const getLoginID = computed((): string => store.state.loginID);

    watch(
      getLoginID,
      (value, oldValue) => {
        if (!StringUtil.checkStringIfEmpty(value)) {
          viewData.userInfo.nickname = store.getters.getUser.nickname;
          viewData.userInfo.icon = store.getters.getUser.icons;
          viewData.ifLogin = true;
        } else {
          viewData.ifLogin = false;
        }
      },
      { immediate: true }
    );

    return { ...toRefs(viewData), logout, getLoginID };
  },
});
</script>
<style lang="less">
@import url(./Menu.less);
</style>
