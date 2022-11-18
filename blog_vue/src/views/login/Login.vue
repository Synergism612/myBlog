<template>
  <div id="login_background"></div>
  <div id="login_container">
    <Menu></Menu>
    <transition appear appear-active-class="animate__animated animate__zoomIn">
      <div id="login_from" class="frame">
        <p id="title">登录</p>
        <el-form
          id="login_form"
          ref="loginFormRef"
          label-position="top"
          label-width="0px"
          :model="LoginFrom"
          :rules="rules"
          style="max-width: 100%"
          :hide-required-asterisk="true"
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
            <el-button link>忘记密码</el-button>
            <el-button link @click="goRegister()">注册账号</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="login()">登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </transition>
  </div>
</template>

<script lang="ts">
import { api } from "src/api/api";
import { store } from "src/store";
import StringUtil from "src/utils/StringUtil";
import { defineComponent, reactive, ref, toRefs } from "vue";
import Message from "src/utils/MessageUtil";
import Menu from "src/components/menu/Menu.vue";
import { useRouter } from "vue-router";
import UserInformation from "src/model/user/UserInformation";

export default defineComponent({
  /**
   * 组件内setup，setup最早创建，所以没有this
   */
  setup() {
    const router = useRouter();
    // 数据仓
    const viewData = reactive({
      LoginFrom: {
        username: "",
        password: "",
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
      ],
    };

    // 获得dom对象
    const loginFormRef = ref();

    // 登录函数
    const login = (): void => {
      if (StringUtil.checkStringIfEmpty(store.state.loginID)) {
        loginFormRef.value
          .validate()
          .then((): void => {
            api
              .login(viewData.LoginFrom.username, viewData.LoginFrom.password)
              .then(({ data }): void => {
                var loginID = data[0] as string;
                var userInfo = data[1] as UserInformation;
                store.commit("SET_LOGIN_ID", loginID);
                store.commit("SET_USER_INFO", userInfo);
                router.push({ name: "Home" });
                Message.successMessage("欢迎回来" + userInfo.nickname);
              });
          })
          .catch((): void => {
            Message.warningMessage("校验未通过");
          });
      } else {
        Message.warningMessage("您已登录");
      }
    };

    const goRegister = (): void => {
      router.push({ name: "Register" });
    };

    // 返回页面所需
    return { ...toRefs(viewData), login, loginFormRef, goRegister, rules };
  },

  // 组件导入
  components: { Menu },
});
</script>
<style lang="less">
@import url(./Login.less);
</style>
