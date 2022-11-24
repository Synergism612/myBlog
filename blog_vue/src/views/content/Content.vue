<template>
  <div class="contentBox">
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
                  <span class="click">作者:{{ author.nickname }}</span>
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
                    :showCodeRowNumber="true"
                  />
                </el-row>

                <!-- 文章分类标签信息 -->
                <el-divider />
                <el-row class="more">
                  <el-col :span="24">
                    文章所属分类:
                    <span class="element" @click="classifyClick(classify.id)">
                      {{ classify.name }}
                    </span>
                  </el-col>
                  <el-col :span="24">
                    文章所属标签:
                    <span v-for="tag in tagList" :key="tag.id">
                      <span class="element" @click="tagClick(tag.id)">{{
                        tag.name
                      }}</span>
                    </span>
                  </el-col>
                  <el-col :span="24">
                    <span class="click function" @click="likeArticle">
                      <span v-if="likeState">
                        <font-awesome-icon :icon="['fas', 'thumbs-up']" />
                        取消点赞
                      </span>
                      <span v-if="!likeState">
                        <font-awesome-icon :icon="['far', 'thumbs-up']" />
                        点赞
                      </span>
                    </span>
                    <span class="click function" @click="myfavorite(true)">
                      <font-awesome-icon :icon="['fas', 'heart']" />
                      添加至收藏夹
                    </span>
                  </el-col>
                </el-row>

                <el-divider />
                <el-row :gutter="20">
                  <!-- 推荐区 -->
                  <el-col :xs="0" :sm="4" :md="4" :lg="4">
                    <div class="nominate">
                      <el-row>
                        <el-col :span="24">
                          同分类文章
                          <ul v-if="classifyNominate[0].id != -1">
                            <li
                              v-for="nominate in classifyNominate"
                              :key="nominate.id"
                            >
                              <span
                                class="click"
                                @click="toArticle(nominate.id)"
                              >
                                {{ nominate.title }}
                              </span>
                            </li>
                          </ul>
                          <ul v-if="classifyNominate[0].id === -1">
                            <li>该分类下没有其他文章</li>
                          </ul>
                        </el-col>
                      </el-row>
                      <el-row>
                        <el-col :span="24">
                          标签匹配文章
                          <ul v-if="tagNominate[0].id != -1">
                            <li
                              v-for="nominate in tagNominate"
                              :key="nominate.id"
                            >
                              <span
                                class="click"
                                @click="toArticle(nominate.id)"
                              >
                                {{ nominate.title }}
                              </span>
                            </li>
                          </ul>
                          <ul v-if="tagNominate[0].id === -1">
                            <li>没有查到含有相同标签的其他文章</li>
                          </ul>
                        </el-col>
                      </el-row>
                    </div>
                  </el-col>
                  <!-- 评论区 -->
                  <el-col :xs="24" :sm="16" :md="16" :lg="16">
                    <el-col :span="24">
                      <div ref="forumRef" v-if="article.id != -1">
                        <Forum
                          :articleID="article.id"
                          @toForum="toForum"
                        ></Forum>
                      </div>
                    </el-col>
                  </el-col>
                  <!-- 作者信息 -->
                  <el-col :xs="0" :sm="4" :md="4" :lg="4">
                    <div class="author">
                      <div
                        class="icon"
                        :style="{
                          backgroundImage: 'url(' + author.icon + ')',
                        }"
                      ></div>
                      <span>昵称:{{ author.nickname }}</span>
                      <span>性别:{{ author.sexName }}</span>
                      <span> 生日:{{ author.birthday || "不愿透露" }} </span>
                      <span>园龄:{{ author.upToNow }}</span>
                      <span>简介:{{ author.intro }}</span>
                      <span>文章数:{{ author.articleCount }}</span>
                      <span>关注数:{{ author.notableCount }}</span>
                      <span>粉丝数:{{ author.fansCount }}</span>
                      <br />
                      <span>
                        <text class="click">关注他</text>
                      </span>
                    </div>
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
      <el-drawer
        v-model="catalogShow"
        title="目录"
        :withHeader="false"
        :modal="false"
        :lock-scroll="false"
      >
        <MdCatalog :editorId="editorName" :scroll-element="html" />
      </el-drawer>
    </div>

    <Toolboxe
      v-if="toolBoxShow"
      page-name="content"
      @toFull="toFullOrlessen"
      @toForum="toForum"
      @toCatalog="toCatalog"
    ></Toolboxe>

    <Enshrine
      @close="myfavorite"
      v-model:show="collectionShow"
      :title="article.title"
      :href="href"
      :synopsis="article.synopsis"
    ></Enshrine>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, ref, toRefs } from "vue";
import Menu from "src/components/menu/Menu.vue";
import Content from "./Content";
import MdEditor from "md-editor-v3";
import "md-editor-v3/lib/style.css";
const MdCatalog = MdEditor.MdCatalog;

import { useRoute, useRouter } from "vue-router";
import Forum from "src/components/forum/Forum.vue";
import Toolboxe from "src/components/toolboxe/Toolboxe.vue";
import Enshrine from "src/components/enshrine/Enshrine.vue";
import Message from "src/utils/MessageUtil";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Content());

    /**路由传参，分清route和router */
    const route = useRoute();
    /**
     * 从路由中传递的参数只会是字符串|字符串数组类型
     * 用于初始化
     */
    const id = Number(route.params.id); //字符串读取为数字

    /**
     * 获取全页面数据
     * 用于目录和下方的跳转函数
     */
    const html = document.documentElement;

    /**
     * 全屏或缩小函数
     * @param pageFullScreen 是否全屏
     */
    const toFullOrlessen = (pageFullScreen: boolean): void => {
      if (!pageFullScreen) {
        /**放大后会将原本的body滚动条设置为不显示,所以需要删掉 */
        document.body.style.removeProperty("overflow");
      }
      viewData.pageFullScreen = pageFullScreen;
      viewData.refresh += 1;
    };
    /**用于绑定评论区dom */
    const forumRef = ref<HTMLElement>(document.createElement("div"));

    /**
     * 跳转到评论区
     */
    const toForum = (): void => {
      /**目标元素距离顶部高度=当前距离顶部高度加目标元素距离当前高度 */
      const height =
        html.scrollTop + forumRef.value.getBoundingClientRect().top;
      jump(height);
    };

    /**
     * 跳转动画函数
     * 原理每次向下移动一部分
     * 然后10毫秒触发一次
     * 直到到达目标高度或者到达底部为止
     * @param to 跳转位置
     */
    const jump = (to: number): void => {
      viewData.toolBoxShow = false;
      if (
        to < html.scrollTop + html.clientHeight &&
        to > html.scrollTop - html.clientHeight
      ) {
        viewData.toolBoxShow = true;
        return;
      }

      if (to >= html.scrollTop + html.clientHeight / 2) {
        var time1 = setInterval((): void => {
          if (html.scrollTop + html.clientHeight >= to) {
            viewData.toolBoxShow = true;
            clearInterval(time1);
          }
          html.scrollTop = html.scrollTop + html.clientHeight / 2;
        }, 10);
      } else {
        var time2 = setInterval((): void => {
          if (html.scrollTop <= to) {
            viewData.toolBoxShow = true;
            clearInterval(time2);
          }
          html.scrollTop = html.scrollTop - html.clientHeight / 10;
        }, 10);
      }
    };

    /**
     * 弹出目录
     */
    const toCatalog = (): void => {
      viewData.catalogShow = !viewData.catalogShow;
    };

    const router = useRouter();
    const toArticle = (id: number): void => {
      const open = router.resolve({
        name: "Content",
        params: {
          id: id,
        },
      });
      window.open(open.href, "_blank");
    };
    const classifyClick = (id: number): void => {
      router.push({
        name: "Pandect",
        params: {
          type: "classify",
          id: id,
        },
      });
    };

    const tagClick = (id: number): void => {
      router.push({
        name: "Pandect",
        params: {
          type: "tag",
          id: id,
        },
      });
    };

    const href = window.location.href;

    const myfavorite = (show: boolean): void => {
      if (viewData.isLogin) {
        viewData.collectionShow = show;
      } else {
        Message.warningMessage("您未登录");
      }
    };

    const likeArticle = (): void => {
      if (viewData.isLogin) {
        viewData.likeState = !viewData.likeState;
        viewData.updateArticleLike();
      } else {
        Message.warningMessage("您未登录");
      }
    };

    onMounted((): void => {
      viewData.init(id);
    });

    return {
      ...toRefs(viewData),
      html,
      forumRef,
      toFullOrlessen,
      toForum,
      toCatalog,
      toArticle,
      myfavorite,
      href,
      classifyClick,
      tagClick,
      likeArticle,
    };
  },
  components: { Menu, MdEditor, MdCatalog, Forum, Toolboxe, Enshrine },
});
</script>
<style lang="less">
@import url("./Content.less");
</style>
