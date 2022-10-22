<template>
  <div class="box">
    <!-- 文章排序 -->
    <div class="header frame">
      <el-row>
        <el-col :span="24">
          <span @click="myArticle()">我的文章</span>
          <span v-if="!isMy">○</span>
          <span v-if="isMy">●</span>\
          <span @click="updateArticle(0)">最新发布</span>\
          <span @click="updateArticle(1)">最新更新</span>\
          <span @click="updateArticle(2)">查看最多</span>\
          <span @click="updateArticle(3)">点赞最多</span>
        </el-col>
      </el-row>
    </div>
    <!-- 文章列表 -->
    <el-row class="articles">
      <el-row
        v-for="article in articleInformationList"
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
              <div v-for="tag in article.tagList" :key="tag.id" class="tags">
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
          <el-row class="footer" justify="space-between">
            <el-col :xs="8" :span="6">
              <avatar
                theme="outline"
                size="21"
                fill="#000000"
                :strokeWidth="2"
              />
              <span>{{ article.nickname }}</span>
            </el-col>
            <el-col :xs="4" :span="4">
              <good_two
                theme="outline"
                size="21"
                fill="#000000"
                :strokeWidth="2"
              />
              <span> {{ article.likeCount }} </span>
            </el-col>
            <el-col :xs="4" :span="4">
              <preview_open
                theme="outline"
                size="21"
                fill="#000000"
                :strokeWidth="2"
              />
              <span> {{ article.views }} </span>
            </el-col>
            <el-col :xs="0" :span="8">
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
    <!-- 分页插件 -->
    <el-row class="pagination">
      <el-pagination
        :page-sizes="[10, 50, 100, 200]"
        layout="total,sizes, prev, pager, next, jumper"
        :total="total"
        :current-page="currentPage"
        @update:page-size="handleSizeChange"
        @update:current-page="handleCurrentChange"
      />
    </el-row>
  </div>
</template>

<script lang="ts">
import { api } from "@/api/api";
import { defineComponent, onMounted, reactive, toRefs, watch } from "vue";
import Article from "./Article";
import {
  Avatar as avatar,
  GoodTwo as good_two,
  PreviewOpen as preview_open,
  UpdateRotation as update_rotation,
  DocumentFolder as document_folder,
  TagOne as tag_one,
} from "@icon-park/vue-next";
import StringUtil from "@/utils/StringUtil";
import Message from "@/utils/MessageUtil";
export default defineComponent({
  setup() {
    const viewData = reactive(new Article());

    const myArticle = (): void => {
      if (
        viewData.isLogin &&
        !StringUtil.checkStringIfEmpty(viewData.username)
      ) {
        viewData.isMy = !viewData.isMy;
        pagination();
      } else {
        Message.warningMessage("您还没有登录");
      }
    };

    const updateArticle = (x: number): void => {
      viewData.articleOrderBy = viewData.articleSort[x];
      viewData.currentPage = 1;
      pagination();
    };

    /**
     * 分页数据获取
     */
    const pagination = (): void => {
      api
        .getIndexArticle(
          viewData.currentPage,
          viewData.pageSize,
          viewData.articleOrderBy,
          viewData.isMy === true ? viewData.username : ""
        )
        .then(({ data }) => {
          viewData.articleInformationList = data.articleInformationList;
          viewData.total = data.total;
        });
    };
    /**
     *监听页容量
     * @param pageSize 页容量
     */
    const handleSizeChange = (pageSize: number): void => {
      viewData.pageSize = pageSize;
    };

    /**
     *监听页数变化
     * @param currentPage 页数
     */
    const handleCurrentChange = (currentPage: number): void => {
      viewData.currentPage = currentPage;
    };
    watch(
      (): Array<number> => [viewData.currentPage, viewData.pageSize],
      (newVal, oldVal) => {
        newVal;
        oldVal;
        pagination();
      }
    );

    onMounted((): void => {
      pagination();
    });

    return {
      ...toRefs(viewData),
      handleSizeChange,
      handleCurrentChange,
      updateArticle,
      myArticle,
    };
  },
  components: {
    avatar,
    good_two,
    preview_open,
    update_rotation,
    document_folder,
    tag_one,
  },
});
</script>
<style lang="less">
@import url(./Article.less);
</style>
