<template>
  <div id="register_background"></div>
  <div id="register_container">
    <Menu></Menu>
    <div id="register_from" class="frame">
      <p id="title">注册</p>
      <el-form
        id="register_form"
        ref="registerFormRef"
        label-position="top"
        label-width="0px"
        :model="registerFrom"
        :rules="rules"
        style="max-width: 100%"
        :hide-required-asterisk="hideRequiredAsterisk"
      >
        <el-form-item label="邮箱" prop="username">
          <el-input
            v-model="registerFrom.username"
            placeholder="请输入邮箱"
            clearable
          />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input
            v-model="registerFrom.password"
            type="password"
            placeholder="请输入密码"
            clearable
          />
        </el-form-item>
        <el-form-item label="确认密码" prop="passwordAgen">
          <el-input
            v-model="registerFrom.passwordAgen"
            type="password"
            placeholder="请确认密码"
            clearable
          />
        </el-form-item>
        <el-form-item label="验证码" prop="code" id="security_code">
          <el-input
            v-model="registerFrom.code"
            placeholder="请输入验证码"
            clearable
          />
          <el-button
            type="string"
            @click="getSecurityCode()"
            :disabled="codeButton.disabled"
            >{{ codeButton.text }}</el-button
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
import Menu from "src/components/menu/Menu.vue";
import StringUtil from "src/utils/StringUtil";
import Message from "src/utils/MessageUtil";
import { api } from "src/api/api";
import RegisterForm from "src/api/entity/RegisterForm";

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
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const checkPassword_agen = (rule: any, value: string, callback: any) => {
      if (StringUtil.checkStringIfEmpty(value)) {
        return callback(new Error("不能为空"));
      }
      if (StringUtil.checkStringIfEmpty(viewData.registerFrom.password)) {
        return callback(new Error("请输入密码"));
      }
      if (value.localeCompare(viewData.registerFrom.password) != 0) {
        return callback(new Error("两次输入不一致"));
      }

      return callback();
    };

    const viewData = reactive({
      registerFrom: new RegisterForm(),
      hideRequiredAsterisk: false,
      codeButton: {
        disabled: false,
        text: "获取验证码",
        duration: 60,
      },
    });

    const rules = {
      username: [{ validator: checkUsername }],
      password: [{ validator: checkPassword }],
      passwordAgen: [{ validator: checkPassword_agen }],
    };

    /**
     * 用以获取验证码
     */
    const getSecurityCode = (): void => {
      //定时器，倒计时效果
      const timer = window.setInterval((): void => {
        if (viewData.codeButton.duration < 1) {
          viewData.codeButton.disabled = false;
          viewData.codeButton.text = "获取验证码";
          viewData.codeButton.duration = 60;
          //清理定时器
          clearInterval(timer);
        } else {
          viewData.codeButton.disabled = true;
          viewData.codeButton.text = "" + viewData.codeButton.duration;
          viewData.codeButton.duration -= 1;
        }
      }, 1000);
      //对表单进行校验
      registerFormRef.value
        .validate()
        .then((): void => {
          // 注册请求
          api
            .getSecurityCode(
              viewData.registerFrom.username,
              viewData.registerFrom.key
            )
            .then(({ data }) => {
              viewData.registerFrom.key = data;
            });
        })
        .catch((): void => {
          Message.errorMessage("校验未通过");
        });
    };

    const register = (): void => {
      registerFormRef.value
        .validate()
        .then((): void => {
          api.register(viewData.registerFrom);
        })
        .catch((): void => {
          Message.errorMessage("校验未通过");
        });
    };

    const registerFormRef = ref();

    return {
      ...toRefs(viewData),
      rules,
      register,
      registerFormRef,
      getSecurityCode,
    };
  },
  components: { Menu },
});
</script>
<style lang="less">
@import url(./Register.less);
</style>
