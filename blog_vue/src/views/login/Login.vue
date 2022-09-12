<template>
  <div id="login_background"></div>
  <div id="login_container">
    <Menu></Menu>
    <div id="login_from">
      <p id="title">登录</p>
      <el-form
        id="login_form"
        :ref="loginFormRef"
        label-position="top"
        label-width="0px"
        :model="LoginFrom"
        :rules="rules"
        style="max-width: 100%"
        :hide-required-asterisk="hideRequiredAsterisk"
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
            type="password"
            placeholder="请输入密码"
            clearable
          />
        </el-form-item>
        <el-form-item class="button_float">
          <el-button type="text" @click="forget">忘记密码</el-button>
          <el-button type="text" @click="register">注册账号</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="login()">登录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts">
import { api } from "@/api/api";
import StringUtil from "@/utils/StringUtil";
import { defineComponent, reactive, ref, toRefs } from "vue";
import Message from "@/utils/MessageUtil";
import Menu from "../../components/Menu/Menu.vue";

export default defineComponent({
  /**
   * 组件内setup，setup最早创建，所以没有this
   */
  setup() {
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const checkUsername = (rule: any, value: string, callback: any) => {
      if (StringUtil.checkStringIfEmpty(value)) {
        return callback(new Error("不能为空"));
      }
      if (value.indexOf("@") * value.indexOf(".com") < 2) {
        return callback(new Error("格式不规范"));
      }
      if (StringUtil.checkStringIfUnsafe(value)) {
        return callback(new Error("不合法"));
      }
    };
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const checkPassword = (rule: any, value: string, callback: any) => {
      if (StringUtil.checkStringIfEmpty(value)) {
        return callback(new Error("不能为空"));
      }
      if (value.length < 8) {
        return callback(new Error("密码长度不足8位"));
      }
      if (StringUtil.checkStringIfUnsafe(value)) {
        return callback(new Error("不合法"));
      }
    };
    const data = reactive({
      LoginFrom: {
        username: "",
        password: "",
      },
      rules: {
        username: [{ validator: checkUsername }],
        password: [{ validator: checkPassword }],
      },
      //去除红色星号
      hideRequiredAsterisk: false,
    });
    const loginFormRef = ref();
    const login = () => {
      loginFormRef.value.validate((valid: boolean) => {
        if (!valid) {
          Message.errorMessage("校验未通过");
        } else {
          api
            .login(data.LoginFrom.username, data.LoginFrom.password)
            .then((data) => {
              console.log(data);
            });
        }
      });
    };
    return { ...toRefs(data), login, loginFormRef };
  },
  components: { Menu },
});
</script>
<style lang="less">
@import url(./Login.less);
</style>
