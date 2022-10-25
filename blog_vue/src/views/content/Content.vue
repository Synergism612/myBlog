<template>
  <div class="box">
    <div id="content_shade" v-if="pageFullScreen"></div>
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
              <el-col :xs="24" :sm="24" :md="24" :lg="20" class="inform frame">
                <el-breadcrumb separator="/">
                  <el-breadcrumb-item :to="{ path: '/' }">
                    {{ "path" }}
                  </el-breadcrumb-item>
                  <el-breadcrumb-item>
                    <a href="/">{{ title }}</a>
                  </el-breadcrumb-item>
                </el-breadcrumb>
              </el-col>
            </transition>
          </el-row>
        </el-header>
        <el-main>
          <el-row justify="center" v-if="id != -1">
            <transition
              appear
              appear-active-class="animate__animated animate__zoomIn"
            >
              <el-col :xs="24" :sm="24" :md="24" :lg="20" class="content frame">
                <!-- 标题 -->
                <el-row class="title" justify="space-around">
                  {{ title }}
                </el-row>
                <el-row class="signature" justify="space-around">
                  作者:{{ author.nickname }}
                </el-row>

                <!-- 目录 -->
                <el-divider />
                <el-row class="catalog">
                  <MdCatalog
                    :editorId="editorName"
                    :scroll-element="scrollElement"
                  />
                </el-row>

                <!-- 文章内容 -->
                <el-divider />
                <el-row class="info">
                  <MdEditor
                    :key="refresh"
                    :editorId="editorName"
                    v-model="article.body"
                    preview-only
                    v-model:pageFullScreen="pageFullScreen"
                  />
                </el-row>

                <!-- 文章分类标签信息 -->
                <el-divider />
                <el-row>
                  <el-col :span="24"> 文章所属分类:{{ classify.name }}</el-col>
                  <el-col :span="24">
                    文章所属标签:
                    <span v-for="tag in tagList" :key="tag.id">
                      {{ tag.name }}
                    </span>
                  </el-col>
                </el-row>

                <!-- 作者信息 -->
                <el-divider />
                <el-row class="author">
                  <el-col :span="4" class="left">
                    <div
                      class="icon"
                      :style="{
                        backgroundImage: 'url(' + author.icon + ')',
                      }"
                    ></div>
                  </el-col>
                  <el-col :span="10" class="middle">
                    <el-col :span="24">昵称:{{ author.nickname }}</el-col>
                    <el-col :span="24">性别:{{ author.sex }}</el-col>
                    <el-col :span="24">
                      生日:{{ author.birthday || "不愿透露" }}
                    </el-col>
                    <el-col :span="24">园龄:{{ author.upToNow }}</el-col>
                    <el-col :span="24">简介:{{ author.intro }}</el-col>
                  </el-col>
                  <el-col :span="10" class="right">
                    <el-col :span="24">文章数:{{ author.articleCount }}</el-col>
                    <el-col :span="24">关注数:{{ author.notableCount }}</el-col>
                    <el-col :span="24">粉丝数:{{ author.fansCount }}</el-col>
                    <el-col :span="24">关注他</el-col>
                    <el-col :span="24">加好友</el-col>
                  </el-col>
                </el-row>

                <!-- 评论区 -->
                <el-divider />
                <el-row>
                  <el-col :span="24"> 评论区 </el-col>
                </el-row>
              </el-col>
            </transition>
          </el-row>
        </el-main>
      </el-container>
    </div>
    <Toolboxe page-name="content" @toFull="toFull"></Toolboxe>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Menu from "@/components/menu/Menu.vue";
import Content from "./Content";
import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";
const MdCatalog = MdEditor.MdCatalog;

import { useRoute } from "vue-router";
import { api } from "@/api/api";
import CommentParent from "@/model/comment/CommentParent";
import Toolboxe from "@/components/toolboxe/Toolboxe.vue";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Content());

    /**路由传参，分清route和router */
    const route = useRoute();
    /**初始化 */
    const init = (): void => {
      if (route.params) {
        /**从路由中传递的参数只会是字符串|字符串数组类型 */
        const id = Number(route.params.id); //字符串读取为数字
        const title = route.params.title as string; //转为字符串类型

        api.getContent(id, title).then(({ data }): void => {
          viewData.id = data.article.id;
          viewData.title = data.article.title;
          viewData.article = data.article;
          viewData.classify = data.classify;
          viewData.tagList = data.tagList;
          viewData.author = data.author;
          viewData.commentParentList = data.commentParentList || [
            new CommentParent(),
          ];
          // viewData.article.body = "正文省略";
        });
      }
    };

    const scrollElement = document.documentElement;

    const toFullOrlessen = (pageFullScreen: boolean) => {
      if (pageFullScreen) {
        console.log("background-color: var(--md-bk-color);");
      } else {
        /**放大后会将原本的body滚动条删去,所以需要重新添加 */
        document.body.style.overflow = "auto";
      }
      viewData.pageFullScreen = pageFullScreen;
      viewData.refresh = viewData.refresh + 1;
    };

    onMounted(() => {
      init();
    });
    return { ...toRefs(viewData), scrollElement, toFull: toFullOrlessen };
  },
  components: { Menu, MdEditor, MdCatalog, Toolboxe },
});
</script>
<style lang="less">
@import url("./content.less");
</style>
