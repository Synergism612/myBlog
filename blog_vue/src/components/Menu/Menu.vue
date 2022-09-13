<template>
  <div id="menu_container">
    <el-menu mode="horizontal" :ellipsis="ellipsis" router>
      <el-menu-item id="menu_logo" index="/blog/index">Synergism</el-menu-item>
      <el-menu-item index="2">网站首页</el-menu-item>
      <el-menu-item index="3">文章专栏</el-menu-item>
      <el-menu-item index="4">归档</el-menu-item>
      <el-menu-item index="3">资源分享</el-menu-item>
      <el-menu-item index="3">点点滴滴</el-menu-item>
      <el-menu-item index="3">关于本站</el-menu-item>
      <el-menu-item index="3" v-if="!isLogin">登录</el-menu-item>
      <el-menu-item index="3" v-if="!isLogin">注册</el-menu-item>
      <el-sub-menu v-if="isLogin">
        <template #title>用户名</template>
        <el-menu-item index="2-1">item one</el-menu-item>
        <el-menu-item index="2-2">item two</el-menu-item>
        <el-menu-item index="2-3">item three</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script lang="ts">
import { store } from "@/store";
import { defineComponent, reactive, toRefs } from "vue";

export default defineComponent({
  /**
   * 组件内setup，setup最早创建，所以没有this
   */
  setup() {
    const data = reactive({
      isLogin: false,
      ellipsis: false,
    });
    return { ...toRefs(data) };
  },
});
</script>
<style lang="less">
@import url(./Menu.less);
</style>
