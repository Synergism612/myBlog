<template>
  <div class="pigeonholeBox">
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
                  <transition
                    appear
                    appear-active-class="animate__animated animate__backInUp"
                  >
                    <div class="left">
                      <el-timeline>
                        <el-timeline-item
                          v-for="archive in archiveList"
                          :key="archive.month"
                          :timestamp="archive.month"
                          placement="top"
                        >
                          <el-timeline :id="'archive_' + archive.month">
                            <el-timeline-item
                              class="article"
                              v-for="article in archive.articleList"
                              :key="article.id"
                              :timestamp="article.creationTime"
                              placement="top"
                            >
                              <div class="frame" @click="toContent(article.id)">
                                <span>{{ article.title }}</span>
                                <p>{{ article.synopsis }}</p>
                              </div>
                            </el-timeline-item>
                            <el-timeline-item
                              timestamp="本月没有更多了"
                              placement="top"
                            />
                          </el-timeline>
                        </el-timeline-item>
                        <el-timeline-item
                          timestamp="没有更多了"
                          placement="top"
                        />
                      </el-timeline>
                    </div>
                  </transition>
                </el-col>
                <!-- 右侧 -->
                <el-col :xs="0" :sm="6" :md="6" :span="6">
                  <transition
                    appear
                    appear-active-class="animate__animated animate__backInUp"
                  >
                    <div class="right">
                      <span style="margin-right;: 0.5em"> 只显示我的文章 </span>
                      <el-switch
                        v-model="isMy"
                        class="ml-2"
                        @change="change"
                        style="--el-switch-on-color: #3fc4c2"
                      />
                      <el-timeline>
                        <el-timeline-item
                          v-for="month in monthList"
                          :key="month"
                          placement="top"
                          center
                        >
                          <span @click="toDate(month)" class="click">{{
                            month
                          }}</span>
                        </el-timeline-item>
                        <el-timeline-item
                          timestamp="没有更多了"
                          placement="top"
                        />
                      </el-timeline>
                    </div>
                  </transition>
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
import Menu from "src/components/menu/Menu.vue";
import Pigeonhole from "./Pigeonhole";
import { useRouter } from "vue-router";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Pigeonhole());

    const toDate = (month: string): void => {
      const scrollDom = document.getElementById("archive_" + month);
      if (scrollDom !== null) {
        scrollDom.scrollIntoView();
      }
    };

    const router = useRouter();

    const toContent = (id: number): void => {
      router.push({
        name: "Content",
        params: {
          id: id,
        },
      });
    };

    const change = (): void => {
      viewData.init();
    };

    onMounted((): void => {
      viewData.init();
    });

    return {
      ...toRefs(viewData),
      toDate,
      toContent,
      change,
    };
  },
  components: { Menu },
});
</script>
<style lang="less">
@import url("./Pigeonhole.less");
</style>
