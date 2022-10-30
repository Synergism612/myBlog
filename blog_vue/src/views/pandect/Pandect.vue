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
                  <div class="left">
                    <IndexArticle></IndexArticle>
                  </div>
                </el-col>
                <!-- 右侧 -->
                <el-col :xs="0" :sm="6" :md="6" :span="6">
                  <div class="right">
                    <el-col :span="24" class="frame">
                      <div class="center">搜索</div>
                      <el-row class="search">
                        <el-col :span="20">
                          <el-input
                            v-model="searchInput"
                            placeholder="请输入搜索内容"
                          />
                        </el-col>
                        <el-col :span="3" :offset="1">
                          <span class="click">
                            <font-awesome-icon
                              :icon="['fas', 'magnifying-glass']"
                            />
                          </span>
                        </el-col>
                      </el-row>
                    </el-col>
                    <el-col :span="24" class="frame">
                      <div class="center">分类</div>
                      <el-row :gutter="5" class="tags">
                        <el-col :span="12">
                          <div class="tag">你好</div>
                        </el-col>
                        <el-col :span="12">
                          <div class="tag">你好</div>
                        </el-col>
                        <el-col :span="12">
                          <div class="tag">你好</div>
                        </el-col>
                        <el-col :span="12">
                          <div class="tag">你好</div>
                        </el-col>
                      </el-row>
                    </el-col>
                    <el-col :span="24" class="frame">
                      <div class="center">标签</div>
                      <el-row :gutter="5" class="tags">
                        <el-col :span="12">
                          <div class="tag">你好</div>
                        </el-col>
                        <el-col :span="12">
                          <div class="tag">你好</div>
                        </el-col>
                        <el-col :span="12">
                          <div class="tag">你好</div>
                        </el-col>
                        <el-col :span="12">
                          <div class="tag">你好</div>
                        </el-col>
                      </el-row>
                    </el-col>
                  </div>
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
