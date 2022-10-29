<template>
  <div class="pandectBox">
    <Menu></Menu>
    <div class="container">
      <el-container>
        <el-header>
          <!-- 头部面包屑 -->
          <el-row justify="center">
            <transition
              appear
              appear-active-class="animate__animated animate__zoomIn"
            >
              <el-col :xs="24" :sm="24" :md="24" :lg="20" class="header frame">
                <el-breadcrumb separator="/">
                  <el-breadcrumb-item :to="{ path: '/' }">
                    {{ "首页" }}
                  </el-breadcrumb-item>
                  <el-breadcrumb-item>
                    <a href="/">文章总览</a>
                  </el-breadcrumb-item>
                </el-breadcrumb>
              </el-col>
            </transition>
          </el-row>
        </el-header>
        <el-main>
          <!-- 主要内容 -->
          <el-row justify="space-around">
            <!-- 主体框架 -->
            <el-col :xs="24" :sm="24" :md="24" :lg="20">
              <!-- 分左右区 -->
              <el-row :gutter="20" justify="space-around">
                <!-- 左侧 -->
                <el-col :xs="24" :sm="18" :md="18" :span="18">
                  <IndexArticle></IndexArticle>
                </el-col>
                <!-- 右侧 -->
                <el-col :xs="0" :sm="6" :md="6" :span="6">
                  <el-col :span="24" class="frame">搜索栏</el-col>
                  <el-col :span="24" class="frame">分类栏</el-col>
                  <el-col :span="24" class="frame">标签栏</el-col>

                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Pandect from "./Pandect";
import Menu from "@/components/menu/Menu.vue";
import IndexArticle from "@/components/article/indexArticle/IndexArticle.vue";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Pandect());

    onMounted(() => {
      console.log("");
    });

    return {
      ...toRefs(viewData),
    };
  },
  components: { Menu, IndexArticle },
});
</script>
<style lang="less">
@import url("./Pandect.less");
</style>
