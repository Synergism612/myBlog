<template>
  <div class="box">
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

                <!-- 文章内容 -->
                <el-divider />
                <el-row class="info">
                  <MdEditor
                    v-model="article.body"
                    :preview-only="true"
                  ></MdEditor>
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
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Menu from "@/components/menu/Menu.vue";
import MdEditor from "md-editor-v3";
import Content from "./Content";

import "md-editor-v3/lib/style.css";
import { useRoute } from "vue-router";
import { api } from "@/api/api";
import CommentParent from "@/model/comment/CommentParent";

export default defineComponent({
  setup() {
    const viewData = reactive(new Content());

    const route = useRoute();

    const init = (): void => {
      console.log(route.params);

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
          console.log(viewData);
          viewData.article.body = "正文省略";
        });
      }
    };

    onMounted(() => {
      init();
    });

    return { ...toRefs(viewData) };
  },
  components: { Menu, MdEditor },
});
</script>
<style lang="less">
@import url("./content.less");
</style>
