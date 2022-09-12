<template>
  <div id="head_container">
    <el-menu mode="horizontal">
      <el-menu-item index="1">Processing Center</el-menu-item>
      <el-sub-menu index="2">
        <template #title>Workspace</template>
        <el-menu-item index="2-1">item one</el-menu-item>
        <el-menu-item index="2-2">item two</el-menu-item>
        <el-menu-item index="2-3">item three</el-menu-item>
        <el-sub-menu index="2-4">
          <template #title>item four</template>
          <el-menu-item index="2-4-1">item one</el-menu-item>
          <el-menu-item index="2-4-2">item two</el-menu-item>
          <el-menu-item index="2-4-3">item three</el-menu-item>
        </el-sub-menu>
      </el-sub-menu>
      <el-menu-item index="3" disabled>Info</el-menu-item>
      <el-menu-item index="4">Orders</el-menu-item>
    </el-menu>
  </div>
</template>

<script lang="ts">
import { store } from "@/store";
import { defineComponent, reactive, toRefs } from "vue";

export default defineComponent({
  /**
   * 使用组件时传入参数
   */
  props: {},
  /**
   * 组件内setup，setup最早创建，所以没有this
   */
  setup() {
    const data = reactive({
      msg: "aa",
    });
    return { ...toRefs(data) };
  },
});
</script>
<style lang="less">
@import url(./Menu.less);
</style>
