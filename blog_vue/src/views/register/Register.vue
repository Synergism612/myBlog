<template>
  <div id="register_background"></div>
  <div id="register_container">
    <Menu></Menu>
    <transition appear appear-active-class="animate__animated animate__zoomIn">
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
          :hide-required-asterisk="true"
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
          <el-form-item label="确认密码" prop="passwordAgain">
            <el-input
              v-model="registerFrom.passwordAgain"
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
    </transition>
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
    const checkPassword_agen = (
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      rule: any,
      value: string,
      // eslint-disable-next-line @typescript-eslint/no-explicit-any
      callback: any
    ): void => {
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
      codeButton: {
        disabled: false,
        text: "获取验证码",
        duration: 60,
      },
    });

    const rules = {
      username: [
        {
          required: true,
          message: "账号不能为空",
          trigger: "blur",
        },
        {
          pattern:
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
          message: "格式不符合",
          trigger: "blur",
        },
      ],
      password: [
        {
          required: true,
          message: "密码不能为空",
          trigger: "blur",
        },
        {
          pattern: /^[a-zA-Z0-9_-]{8,100}$/,
          message: "应为8到100位的字母、数字、下划线或减号",
          trigger: "blur",
        },
      ],
      passwordAgain: [
        {
          required: true,
          message: "确认密码不能为空",
          trigger: "blur",
        },
        { validator: checkPassword_agen, trigger: "blur" },
        {
          pattern: /^[a-zA-Z0-9_-]{8,100}$/,
          message: "应为8到100位的字母、数字、下划线或减号",
          trigger: "blur",
        },
      ],
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
            .then(({ data }): void => {
              viewData.registerFrom.key = data;
            });
        })
        .catch((): void => {
          Message.warningMessage("校验未通过");
        });
    };

    const register = (): void => {
      if (StringUtil.checkStringIfEmpty(viewData.registerFrom.code)) {
        Message.warningMessage("验证码不能为空");
        return;
      }
      registerFormRef.value
        .validate()
        .then((): void => {
          api.register(viewData.registerFrom);
        })
        .catch((): void => {
          Message.warningMessage("校验未通过");
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
