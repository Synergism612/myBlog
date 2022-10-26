<template>
  <div class="box" ref="contentRef">
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
                    <a href="/">{{ article.title }}</a>
                  </el-breadcrumb-item>
                </el-breadcrumb>
              </el-col>
            </transition>
          </el-row>
        </el-header>
        <el-main>
          <el-row justify="center" v-if="article.id != -1">
            <transition
              appear
              appear-active-class="animate__animated animate__zoomIn"
            >
              <el-col :xs="24" :sm="24" :md="24" :lg="20" class="content frame">
                <!-- 标题 -->
                <el-row class="title" justify="space-around">
                  {{ article.title }}
                </el-row>
                <el-row class="signature" justify="space-around">
                  作者:{{ author.nickname }}
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

                <el-divider />
                <el-row :gutter="20">
                  <!-- 推荐区 -->
                  <el-col :span="4"> 推荐区 </el-col>
                  <!-- 评论区 -->
                  <el-col :span="16">
                    <el-col :span="24">
                      <div ref="forumRef">
                        <Forum
                          v-if="commentParentList[0].id !== -1"
                          :comment-list="commentParentList"
                        ></Forum>
                      </div>
                      <div
                        v-if="commentParentList[0].id === -1"
                        style="text-align: center"
                      >
                        还没有评论哦~
                      </div>
                    </el-col>
                  </el-col>
                  <!-- 作者信息 -->
                  <el-col :span="4" class="author">
                    <div
                      class="icon"
                      :style="{
                        backgroundImage: 'url(' + author.icon + ')',
                      }"
                    ></div>
                    <span>昵称:{{ author.nickname }}</span>
                    <span>性别:{{ author.sex }}</span>
                    <span> 生日:{{ author.birthday || "不愿透露" }} </span>
                    <span>园龄:{{ author.upToNow }}</span>
                    <span>简介:{{ author.intro }}</span>
                    <span>文章数:{{ author.articleCount }}</span>
                    <span>关注数:{{ author.notableCount }}</span>
                    <span>粉丝数:{{ author.fansCount }}</span>
                    <br />
                    <span><span>关注他</span> <span>加好友</span></span>
                  </el-col>
                </el-row>
              </el-col>
            </transition>
          </el-row>
        </el-main>
      </el-container>
    </div>

    <!-- 目录 -->
    <div>
      <el-drawer v-model="catalogShow" title="目录" :withHeader="false">
        <MdCatalog :editorId="editorName" :scroll-element="html" />
      </el-drawer>
    </div>

    <Toolboxe
      page-name="content"
      @toFull="toFullOrlessen"
      @toForum="toForum"
      @toCatalog="toCatalog"
    ></Toolboxe>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, ref, toRefs } from "vue";
import Menu from "@/components/menu/Menu.vue";
import Content from "./Content";
import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";
const MdCatalog = MdEditor.MdCatalog;

import { useRoute } from "vue-router";
import { api } from "@/api/api";
import CommentParent from "@/model/comment/CommentParent";
import Forum from "@/components/forum/Forum.vue";
import Toolboxe from "@/components/toolboxe/Toolboxe.vue";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Content());

    /**路由传参，分清route和router */
    const route = useRoute();
    /**从路由中传递的参数只会是字符串|字符串数组类型 */
    const id = Number(route.params.id); //字符串读取为数字

    const article = (): void => {
      api.getContentArticle(id).then(({ data }) => {
        viewData.article = data;
        // viewData.article.body = "省略";
      });
    };
    const author = (): void => {
      api.getContentAuthor(id).then(({ data }) => {
        viewData.author = data;
      });
    };
    const classify = (): void => {
      api.getContentClassify(id).then(({ data }) => {
        viewData.classify = data;
      });
    };
    const tagList = (): void => {
      api.getContentTagList(id).then(({ data }) => {
        viewData.tagList = data;
      });
    };

    const commentList = (): void => {
      api.getContentCommentList(id).then(({ data }) => {
        viewData.commentParentList = data;
        console.log(data);
        return null;
      });
    };

    const html = document.documentElement;

    /**
     * 全屏或缩小函数
     * @param pageFullScreen 是否全屏
     */
    const toFullOrlessen = (pageFullScreen: boolean) => {
      if (!pageFullScreen) {
        /**放大后会将原本的body滚动条设置为不显示,所以需要删掉 */
        document.body.style.removeProperty("overflow");
      }
      viewData.pageFullScreen = pageFullScreen;
      viewData.refresh = viewData.refresh + 1;
    };

    /**绑定整个页面dom */
    const contentRef = ref<HTMLElement>(document.createElement("div"));
    /**绑定评论区dom */
    const forumRef = ref<HTMLElement>(document.createElement("div"));

    /**
     * 跳转到评论区
     */
    const toForum = (): void => {
      const height = forumRef.value.getBoundingClientRect().top;
      jump(height);
    };

    /**
     * 跳转函数
     * @param to 跳转位置
     */
    const jump = (to: number): void => {
      var timer = setInterval(() => {
        if (
          html.scrollTop + html.clientHeight >=
          contentRef.value.clientHeight
        ) {
          clearInterval(timer);
        }
        html.scrollTop = html.scrollTop + to / 100;
      }, 10);
    };

    /**
     * 弹出目录
     */
    const toCatalog = (): void => {
      viewData.catalogShow = !viewData.catalogShow;
    };

    onMounted(() => {
      article();
      // author();
      // classify();
      // tagList();
      commentList();

      //**我认了，匪夷所思 */
      // viewData.id = viewData.article.id;
      // viewData.title = viewData.article.title;
    });

    return {
      ...toRefs(viewData),
      html,
      contentRef,
      forumRef,
      toFullOrlessen,
      toForum,
      toCatalog,
    };
  },
  components: { Menu, MdEditor, MdCatalog, Forum, Toolboxe },
});
</script>
<style lang="less">
@import url("./content.less");
</style>
