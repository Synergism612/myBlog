<template>
  <div class="box">
    <Menu></Menu>
    <div class="container">
      <el-container>
        <el-header>
          <el-row justify="center">
            <transition
              appear
              appear-active-class="animate__animated animate__zoomIn"
            >
              <el-col :xs="24" :sm="24" :md="24" :lg="20" class="inform frame">
                <el-breadcrumb separator="/">
                  <el-breadcrumb-item :to="{ path: '/' }"
                    >homepage</el-breadcrumb-item
                  >
                  <el-breadcrumb-item
                    ><a href="/">promotion management</a></el-breadcrumb-item
                  >
                  <el-breadcrumb-item>promotion list</el-breadcrumb-item>
                  <el-breadcrumb-item>promotion detail</el-breadcrumb-item>
                </el-breadcrumb>
              </el-col>
            </transition>
          </el-row>
        </el-header>
        <el-main>
          <el-row justify="center">
            <transition
              appear
              appear-active-class="animate__animated animate__zoomIn"
            >
              <el-col :xs="24" :sm="24" :md="24" :lg="20" class="content frame">
                <div class="title">{{title}}</div>
                <el-divider />
                <div class="info">
                  <MdEditor v-model="body" :preview-only="true"></MdEditor>
                </div>
              </el-col>
            </transition>
          </el-row>
        </el-main>
      </el-container>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";
import Menu from "@/components/menu/Menu.vue";
import MdEditor from "md-editor-v3";

import "md-editor-v3/lib/style.css";
import { useRoute } from "vue-router";

export default defineComponent({
  setup() {
    const viewData = reactive({
      id: "",
      title: "",
      body: "",
    });

    const route = useRoute();

    const init = (): void => {
      console.log(route.params);
      
      if (route.params) {
        viewData.id = route.params.id as string;
        viewData.title = route.params.title as string;

        viewData.title = route.params.title as string;

      }
    };

    return { ...toRefs(viewData) };
  },
  components: { Menu, MdEditor },
});
</script>
<style lang="less">
@import url("./content.less");
</style>
