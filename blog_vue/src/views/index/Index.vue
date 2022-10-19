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
            <el-col :sm="24" :md="16" class="inform frame">
              通知栏 通知内容
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
                      <el-col :span="24">
                        <span v-if="ifLogin">我的文章\</span>
                        <span @click="updateArticle(0)">最新发布</span>\
                        <span @click="updateArticle(1)">最新更新</span>\
                        <span @click="updateArticle(2)">查看最多</span>\
                        <span @click="updateArticle(3)">点赞最多</span>
                      </el-col>
                    </el-row>
                  </div>

                  <el-row class="articles">
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
                              <el-tooltip
                                class="box-item"
                                effect="dark"
                                :content="classify.annotation"
                                placement="right"
                              >
                                <span>{{ classify.name }}</span>
                              </el-tooltip>
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
                              <el-tooltip
                                class="box-item"
                                effect="dark"
                                :content="tag.annotation"
                                placement="right"
                              >
                                <span>{{ tag.name }}</span>
                              </el-tooltip>
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
                  </el-row>

                  <el-row class="pagination">
                    <el-pagination
                      :page-sizes="[10, 50, 100, 200]"
                      layout="total,sizes, prev, pager, next, jumper"
                      :total="pagination.total"
                      :current-page="currentPage"
                      @update:page-size="handleSizeChange"
                      @update:current-page="handleCurrentChange"
                    />
                  </el-row>
                </el-col>
                <el-col :xs="0" :sm="8" :md="8" :span="8">
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
                  </el-row>

                  <el-row class="calender frame">
                    <el-calendar id="calender-body" v-model="calender" />
                  </el-row>

                  <el-row class="comments frame">
                    <el-col :span="24" class="title"> 最热评论 </el-col>
                    <el-col
                      :span="24"
                      v-for="comment in commentList.comments"
                      :key="comment.id"
                      class="comment"
                    >
                      <el-col :span="4" class="left">
                        <div
                          class="icon"
                          :style="{
                            backgroundImage:
                              'url(' + comment.userInformation.icon + ')',
                          }"
                        ></div>
                      </el-col>
                      <el-col :span="20" class="right">
                        <el-col :span="24" class="name">
                          {{ comment.userInformation.name }}
                        </el-col>
                        <el-col :span="24" class="body">
                          {{ comment.body }}
                        </el-col>
                        <el-col :span="24" class="info">
                          <el-col :span="4" class="likeCount">
                            <good_two
                              theme="outline"
                              size="21"
                              fill="#000000"
                              :strokeWidth="2"
                            />
                            {{ comment.likeCount }}
                          </el-col>
                          <el-col :span="4" class="children">
                            <comments
                              theme="outline"
                              size="21"
                              fill="#000000"
                              :strokeWidth="2"
                            />
                            {{ comment.children.length }}
                          </el-col>
                        </el-col>
                        <el-col
                          :span="24"
                          class="parent"
                          v-if="comment.parent != null"
                        >
                          回复:{{ comment.parent.body }}
                        </el-col>
                      </el-col>
                    </el-col>
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
    const articleSort = ["creation_time", "modify_time", "views", "like_count"];

    const viewData = reactive({
      ifLogin: true,
      articleOrderBy: articleSort[0],

      calender: new Date(),
      pagination: new Pagination(),
      userInfo: new UserInfo(),
      commentList: new CommentList(),

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
        .getIndexArticle(
          viewData.currentPage,
          viewData.pageSize,
          viewData.articleOrderBy
        )
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
          viewData.ifLogin = false;
        });
      }
    };

    const comments = () => {
      api.getIndexComments().then(({ data }) => {
        viewData.commentList = CommentList.getCommentList(data);
        console.log(viewData.commentList);
      });
    };

    const updateArticle = (x: number) => {
      viewData.articleOrderBy = articleSort[x];
      viewData.currentPage = 1;
      pagination();
    };

    pagination();
    // userInformation();
    // comments();

    watch(
      () => [viewData.currentPage, viewData.pageSize],
      (newVal, oldVal) => {
        console.log({ newVal, oldVal });
        pagination();
      }
    );

    return {
      ...toRefs(viewData),
      handleSizeChange,
      handleCurrentChange,
      updateArticle,
    };
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
