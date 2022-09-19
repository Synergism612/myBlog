<template>
  <div id="register_background"></div>
  <div id="register_container">
    <Menu></Menu>
    <div id="register_from">
      <p id="title">注册</p>
      <el-form
        id="register_form"
        ref="registerFormRef"
        label-position="top"
        label-width="0px"
        :model="RegisterFrom"
        :rules="rules"
        style="max-width: 100%"
        :hide-required-asterisk="hideRequiredAsterisk"
      >
        <el-form-item label="邮箱" prop="username">
          <el-input
            v-model="RegisterFrom.username"
            placeholder="请输入邮箱"
            clearable
          />
        </el-form-item>
        <el-form-item label="密码" prop="password_first">
          <el-input
            v-model="RegisterFrom.password_first"
            type="password"
            placeholder="请输入密码"
            clearable
          />
        </el-form-item>
        <el-form-item label="确认密码" prop="password_agen">
          <el-input
            v-model="RegisterFrom.password_agen"
            type="password"
            placeholder="请确认密码"
            clearable
          />
        </el-form-item>
        <el-form-item label="验证码" prop="code" id="security_code">
          <el-input
            v-model="RegisterFrom.code"
            type="password"
            placeholder="请输入验证码"
            clearable
          />
          <el-button type="string" @click="getSecurityCode()"
            >获取验证码</el-button
          >
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="register()">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, ref, toRefs } from "vue";
import Menu from "@/components/Menu/Menu.vue";
import StringUtil from "@/utils/StringUtil";
import Message from "@/utils/MessageUtil";
import { api } from "@/api/api";

export default defineComponent({
  setup() {
    // 账号校验
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
      return callback();
    };
    // 密码校验
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
      return callback();
    };

    const checkPassword_agen = (rule: any, value: string, callback: any) => {
      if (StringUtil.checkStringIfEmpty(value)) {
        return callback(new Error("不能为空"));
      }
      if (StringUtil.checkStringIfEmpty(data.RegisterFrom.password_first)) {
        return callback(new Error("请输入密码"));
      }
      if (value.indexOf(data.RegisterFrom.password_first) == -1) {
        return callback(new Error("两次输入不一致"));
      }

      return callback();
    };

    const data = reactive({
      RegisterFrom: {
        username: "",
        password_first: "",
        password_agen: "",
        code: "",
      },
      rules: {
        username: [{ validator: checkUsername }],
        password_first: [{ validator: checkPassword }],
        password_agen: [{ validator: checkPassword_agen }],
      },
      hideRequiredAsterisk: false,
    });

    const getSecurityCode = () => {
      registerFormRef.value
        .validate()
        .then(() => {
          api.getSecurityCode(data.RegisterFrom.username);
        })
        .catch(() => {
          Message.errorMessage("校验未通过");
        });
    };

    const register = () => {
      console.log("你好");
    };

    const registerFormRef = ref();

    return { ...toRefs(data), register, registerFormRef, getSecurityCode };
  },
  components: { Menu },
});
</script>
<style lang="less">
@import url(./Register.less);
</style>
