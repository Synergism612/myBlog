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
            v-model="RegisterFrom.password"
            type="password"
            placeholder="请输入密码"
            clearable
          />
        </el-form-item>
        <el-form-item label="密码" prop="password_agen">
          <el-input
            v-model="RegisterFrom.password"
            type="password"
            placeholder="请确认密码"
            clearable
          />
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

export default defineComponent({
  setup() {
    const data = reactive({
      RegisterFrom: {
        username: "",
        password: "",
      },
      rules: {
        username: [{}],
        password: [{}],
      },
      hideRequiredAsterisk: false,
    });

    const register = () => {
      console.log("你好");
    };

    const registerFormRef = ref();

    return { ...toRefs(data), register, registerFormRef };
  },
  components: { Menu },
});
</script>
<style lang="less">
@import url(./Register.less);
</style>
