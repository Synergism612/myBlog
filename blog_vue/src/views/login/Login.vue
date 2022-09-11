<template>
  <div id="login_background"></div>
  <div id="login_container">
    <div id="login_from">
      <p id="title">登录</p>
      <el-form
        id="login_form"
        label-position="top"
        label-width="0px"
        :model="LoginFrom"
        :rules="rules"
        style="max-width: 100%"
        hide-required-asterisk="false"
      >
        <el-form-item label="账号" prop="username">
          <el-input
            v-model="LoginFrom.username"
            placeholder="请输入账号"
            clearable
          />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="LoginFrom.password"
            placeholder="请输入密码"
            clearable
          />
        </el-form-item>
        <el-form-item class="button_float">
          <el-button type="text" @click="forget">忘记密码</el-button>
          <el-button type="text" @click="register">注册账号</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";
export default defineComponent({
  /**
   * 组件内setup，setup最早创建，所以没有this
   */
  setup() {
    const data = reactive({
      LoginFrom: {
        username: "",
        password: "",
      },
      rules: {
        username: [{ required: true, message: "* 请输入手机号" }],
        password: [{ required: true, message: "* 请输入密码" }],
      },
    });

    return { ...toRefs(data) };
  },
});
</script>
<style lang="less">
@import url(./Login.less);
</style>
