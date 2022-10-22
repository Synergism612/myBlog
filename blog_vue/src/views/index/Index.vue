<template>
  <div class="box">
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
              <el-col :xs="24" :sm="24" :md="24" :lg="20" class="inform frame">
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
              <el-row gutter="20" justify="space-around">
                <!-- 左侧 -->
                <el-col :xs="24" :sm="24" :md="16" :span="16">
                  <Article></Article>
                </el-col>
                <!-- 右侧 -->
                <el-col :xs="0" :sm="8" :md="8" :span="8">
                  <!-- 用户信息 -->
                  <el-row class="user frame">
                    <el-col :span="24">
                      <div
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

                  <!-- 标签云 -->
                  <el-row class="tagCloud frame">
                    <span>标签云</span>
                    <Cloud
                      v-if="TagInformationList[0].id != -1"
                      :data-list="TagInformationList"
                    ></Cloud>
                  </el-row>
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
import { defineComponent, reactive, toRefs } from "vue";
import Menu from "@/components/Menu/Menu.vue";
import Screen from "@/components/screen/Screen.vue";
import { api } from "@/api/api";
import { Me as me, Loading as loading } from "@icon-park/vue-next";
import StringUtil from "@/utils/StringUtil";
import Index from "./Index";
import Cloud from "@/components/Cloud/Cloud.vue";
import Article from "@/components/Article/IndexArticle/IndexArticle.vue";

export default defineComponent({
  setup() {
    const viewData = reactive(new Index());

    const userInfo = (): void => {
      if (StringUtil.checkStringIfEmpty(viewData.userInfo.username)) {
        api.getIndexUserInfo().then(({ data }) => {
          viewData.userInfo = data;
          viewData.ifLogin = false;
        });
      }
    };

    const tags = (): void => {
      api.getIndexTag(viewData.userInfo.username).then(({ data }): void => {
        viewData.TagInformationList = data;
      });
    };

    const classify = (): void => {
      api
        .getIndexClassify(viewData.userInfo.username)
        .then(({ data }): void => {
          viewData.classifyInformationList = data;
        });
    };

    userInfo();
    tags();
    classify();

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
    Article,
  },
});
</script>
<style lang="less">
@import url("./Index.less");
</style>
