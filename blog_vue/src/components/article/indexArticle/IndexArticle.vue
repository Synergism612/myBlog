<template>
  <div v-if="total > 0" class="indexArticleBox">
    <!-- 文章排序 -->
    <transition
      appear
      appear-active-class="animate__animated animate__backInLeft"
    >
      <el-row class="header frame">
        <el-col :span="24">
          <span class="click" @click="myArticle()">我的文章</span>
          <span @click="myArticle()" v-if="!isMy">○</span>
          <span @click="myArticle()" v-if="isMy">●</span>\
          <span class="click" @click="updateArticle(0)">最新发布</span>\
          <span class="click" @click="updateArticle(1)">最新更新</span>\
          <span class="click" @click="updateArticle(2)">查看最多</span>\
          <span class="click" @click="updateArticle(3)">点赞最多</span>\
          <span class="click" @click="updateArticle(4)">评论最多</span>
        </el-col>
      </el-row>
    </transition>
    <Article
      v-if="refresh"
      v-model:dataList="articleInformationList"
      v-model:total="total"
      v-model:currentPage="currentPage"
      v-model:pageSize="pageSize"
      @pagination="pagination"
    ></Article>
  </div>

  <div v-if="total <= 0" style="text-align: center">没有文章</div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import IndexArticle from "./IndexArticle";
import Article from "@/components/article/Article.vue";
import StringUtil from "@/utils/StringUtil";
import Message from "@/utils/MessageUtil";
export default defineComponent({
  setup() {
    const viewData = reactive(new IndexArticle());

    /**
     * 改变isMy的值，分页重导向
     * 选择我的文章时触发函数
     */
    const myArticle = (): void => {
      if (
        viewData.isLogin &&
        !StringUtil.checkStringIfEmpty(viewData.username)
      ) {
        viewData.isMy = !viewData.isMy;
        viewData.getPagination();
      } else {
        Message.warningMessage("您还没有登录");
      }
    };

    /**
     * 根据选择来分别导向不同的排序分页
     * @param x 分页方式的索引
     */
    const updateArticle = (x: number): void => {
      viewData.articleOrderBy = viewData.articleSort[x];
      viewData.currentPage = 1;
      viewData.getPagination();
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

    /**
     * 监听变化后立刻刷新分页
     */
    const pagination = (pagination: Array<number>): void => {
      viewData.currentPage = pagination[0];
      viewData.pageSize = pagination[1];
      viewData.getPagination();
    };

    onMounted((): void => {
      viewData.getPagination();
    });

    return {
      ...toRefs(viewData),
      handleSizeChange,
      handleCurrentChange,
      updateArticle,
      myArticle,
      pagination,
    };
  },
  components: {
    Article,
  },
});
</script>
<style lang="less">
@import url(./IndexArticle.less);
</style>
