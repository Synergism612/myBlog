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
            <el-col :sm="24" :md="16" class="notice frame">
              <p>公告栏</p>
              <p>公告内容</p>
            </el-col>
          </el-row>
        </el-header>

        <el-main>
          <el-row justify="space-around">
            <el-col :xs="24" :sm="24" :md="16" :span="16">
              <el-row gutter="20" justify="space-around">
                <el-col :xs="24" :sm="16" :md="16" :span="16">
                  <div class="header frame">
                    <el-row>
                      <el-col :span="24"> <p>头部</p></el-col>
                    </el-row>
                  </div>

                  <div class="articles">
                    <el-row
                      v-for="article in pagination.articleInformationList"
                      :key="article.id"
                      class="article frame"
                    >
                      <el-col
                        :span="4"
                        class="left"
                        :style="{
                          backgroundImage: 'url(' + article.icon + ')',
                        }"
                      >
                      </el-col>
                      <el-col :span="20" class="right">
                        <el-row class="title">
                          <el-col :span="24">
                            <span>{{ article.title }}</span>
                          </el-col>
                        </el-row>
                        <el-row class="synopsis">
                          <el-col :span="24">
                            <span>{{ article.synopsis }}</span>
                          </el-col>
                        </el-row>
                        <el-row class="label">
                          <el-col :span="24">
                            <document_folder
                              theme="outline"
                              size="21"
                              fill="#000000"
                              :strokeWidth="2"
                            />
                            <div
                              v-for="classify in article.classifyList"
                              :key="classify.id"
                              class="classifys"
                            >
                              <span>{{ classify.name }}</span>
                            </div>
                          </el-col>
                          <el-col :span="24">
                            <tag_one
                              theme="outline"
                              size="21"
                              fill="#000000"
                              :strokeWidth="2"
                            />
                            <div
                              v-for="tag in article.tagList"
                              :key="tag.id"
                              class="tags"
                            >
                              <span>{{ tag.name }}</span>
                            </div>
                          </el-col>
                        </el-row>
                        <el-row class="footer" :gutter="20">
                          <el-col :span="8" class="icon">
                            <avatar
                              theme="outline"
                              size="21"
                              fill="#000000"
                              :strokeWidth="2"
                            />
                            <span>{{ article.userName }}</span>
                          </el-col>
                          <el-col :span="3" class="icon">
                            <good_two
                              theme="outline"
                              size="21"
                              fill="#000000"
                              :strokeWidth="2"
                            />
                            <span> {{ article.likeCount }} </span>
                          </el-col>
                          <el-col :span="3" class="icon">
                            <preview_open
                              theme="outline"
                              size="21"
                              fill="#000000"
                              :strokeWidth="2"
                            />
                            <span> {{ article.views }} </span>
                          </el-col>
                          <el-col :span="2" class="icon">
                            <comments
                              theme="outline"
                              size="21"
                              fill="#000000"
                              :strokeWidth="2"
                            />
                            <span> {{ article.commentCount }} </span>
                          </el-col>
                          <el-col :span="8" class="icon">
                            <update_rotation
                              theme="outline"
                              size="21"
                              fill="#000000"
                              :strokeWidth="2"
                            />
                            <span> {{ article.modifyTime }} </span>
                          </el-col>
                        </el-row>
                      </el-col>
                    </el-row>
                  </div>

                  <div class="pagination">
                    <el-pagination
                      :page-sizes="[10, 50, 100, 200]"
                      layout="total,sizes, prev, pager, next, jumper"
                      :total="pagination.total"
                      :current-page="currentPage"
                      @update:page-size="handleSizeChange"
                      @update:current-page="handleCurrentChange"
                    />
                  </div>
                </el-col>
                <el-col :xs="0" :sm="8" :md="8" :span="8">
                  <div class="user frame">
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
                        />{{ userInfo.name }}
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
                  </div>

                  <div class="calender frame">
                    <el-calendar id="calender-body" v-model="calender" />
                  </div>

                  <div class="comments frame">
                    <el-row class="comment">
                      <el-col :span="4">头像</el-col>
                      <el-col :span="20">评论内容</el-col>
                    </el-row>

                    <el-row class="comment">
                      <el-col :span="4">头像</el-col>
                      <el-col :span="20">评论内容</el-col>
                    </el-row>
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
import { defineComponent, reactive, toRefs, watch } from "vue";
import { store } from "@/store";
import Menu from "@/components/menu/Menu.vue";
import Screen from "@/components/screen/Screen.vue";
import { api } from "@/api/api";
import Pagination from "@/model/articles/Pagination";
import {
  Avatar as avatar,
  GoodTwo as good_two,
  PreviewOpen as preview_open,
  Comments as comments,
  UpdateRotation as update_rotation,
  DocumentFolder as document_folder,
  TagOne as tag_one,
  Me as me,
  Loading as loading,
} from "@icon-park/vue-next";
import UserInfo from "@/model/user/UserInfo";
import StringUtil from "@/utils/StringUtil";
import CommentList from "@/model/comments/CommentList";

export default defineComponent({
  setup() {
    const viewData = reactive({
      calender: new Date(),
      pagination: new Pagination(),
      userInfo: new UserInfo(),
      CommentList: new CommentList(),
      currentPage: 1,
      pageSize: 10,
    });

    const handleSizeChange = (pageSize: number) => {
      viewData.pageSize = pageSize;
    };

    const handleCurrentChange = (currentPage: number) => {
      viewData.currentPage = currentPage;
    };

    const pagination = () => {
      api
        .getIndexArticle(viewData.currentPage, viewData.pageSize)
        .then(({ data }) => {
          viewData.pagination = data;
          console.log(viewData.pagination);
        });
    };

    const userInformation = () => {
      viewData.userInfo = store.getters.getUser;
      if (StringUtil.checkStringIfEmpty(viewData.userInfo.username)) {
        api.getIndexUserInfo().then(({ data }) => {
          viewData.userInfo = data;
          console.log(viewData.userInfo);
        });
      }
    };

    const comments = () => {
      api.getIndexComments().then(({ data }) => {
        viewData.CommentList = CommentList.getCommentList(data);
        console.log(viewData.CommentList);
      });
    };

    pagination();
    userInformation();
    comments();

    watch(
      () => [viewData.currentPage, viewData.pageSize],
      (newVal, oldVal) => {
        console.log({ newVal, oldVal });
        pagination();
      }
    );

    return { ...toRefs(viewData), handleSizeChange, handleCurrentChange };
  },
  components: {
    Menu,
    Screen,
    avatar,
    good_two,
    preview_open,
    comments,
    update_rotation,
    document_folder,
    tag_one,
    me,
    loading,
  },
});
</script>
<style lang="less">
@import url("./Index.less");
</style>
