<template>
  <div class="indexBox">
    <div class="background">
      <Menu></Menu>
      <Screen></Screen>
    </div>
    <div class="container">
      <el-container>
        <el-header>
          <el-row justify="center">
            <transition
              appear
              appear-active-class="animate__animated animate__zoomIn"
            >
              <el-col :xs="24" :sm="24" :md="24" :lg="20" class="header inform frame">
                通知栏 通知内容
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
                  <!-- 用户信息 -->
                  <el-row class="user frame">
                    <el-col :span="24">
                      <div
                        class="icon"
                        :style="{
                          backgroundImage: 'url(' + userInfo.icon + ')',
                        }"
                      ></div>
                    </el-col>
                    <el-col :span="24">
                      <div class="name">
                        <me
                          theme="outline"
                          size="21"
                          fill="#000000"
                          :strokeWidth="2"
                        />{{ userInfo.nickname }}
                      </div>
                    </el-col>
                    <el-col :span="24">
                      <div class="intro">
                        <loading
                          theme="outline"
                          size="21"
                          fill="#000000"
                          :strokeWidth="2"
                        />
                        {{ userInfo.intro }}
                      </div>
                    </el-col>
                  </el-row>

                  <!-- 日历 -->
                  <el-row class="calender frame">
                    <el-calendar id="calender-body" v-model="calender" />
                  </el-row>

                  <!-- 分类云 -->
                  <el-row class="classifyCloud frame">
                    <span>分类云</span>
                    <Cloud
                      v-if="classifyInformationList[0].id != -1"
                      :data-list="classifyInformationList"
                    ></Cloud>
                  </el-row>

                  <!-- 标签云 -->
                  <el-row class="tagCloud frame">
                    <span>标签云</span>
                    <Cloud
                      v-if="tagInformationList[0].id != -1"
                      :data-list="tagInformationList"
                    ></Cloud>
                  </el-row>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
      <Toolboxe page-name="index"></Toolboxe>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Menu from "@/components/menu/Menu.vue";
import Screen from "@/components/screen/Screen.vue";
import { Me as me, Loading as loading } from "@icon-park/vue-next";
import Index from "./Index";
import Cloud from "@/components/cloud/Cloud.vue";
import IndexArticle from "@/components/article/indexArticle/IndexArticle.vue";
import Toolboxe from "@/components/toolboxe/Toolboxe.vue";

export default defineComponent({
  setup() {
    const viewData = reactive(new Index());

    onMounted(() => {
      viewData.init();
    });

    return {
      ...toRefs(viewData),
    };
  },
  components: {
    Menu,
    Screen,
    me,
    loading,
    Cloud,
    IndexArticle,
    Toolboxe,
  },
});
</script>
<style lang="less">
@import url("./Index.less");
</style>
