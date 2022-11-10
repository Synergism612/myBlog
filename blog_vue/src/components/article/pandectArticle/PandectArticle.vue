<template>
  <div class="pandectArticleBox">
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
    <div v-if="total > 0">
      <Article
        v-if="refresh"
        v-model:dataList="articleInformationList"
        v-model:total="total"
        v-model:currentPage="currentPage"
        v-model:pageSize="pageSize"
        @pagination="pagination"
        @classifyClick="classifyClick"
        @tagClick="tagClick"
      ></Article>
    </div>
  </div>

  <div v-if="total <= 0" style="text-align: center">没有找到文章</div>
</template>

<script lang="ts">
import { defineComponent, PropType, reactive, toRefs, watchEffect } from "vue";
import PandectArticle from "./PandectArticle";
import Article from "src/components/article/Article.vue";
import StringUtil from "src/utils/StringUtil";
import Message from "src/utils/MessageUtil";
import { api } from "src/api/api";
export default defineComponent({
  emits: {
    classifyClick: null,
    tagClick: null,
    myArticle: null,
  },
  props: {
    keyword: {
      type: String,
      required: true,
    },
    classifyIDList: {
      type: Array as PropType<Array<number>>,
      required: true,
    },
    tagIDList: {
      type: Array as PropType<Array<number>>,
      required: true,
    },
  },
  setup(props, { emit }) {
    const viewData = reactive(new PandectArticle());

    watchEffect((): void => {
      viewData.keyword = props.keyword;
      viewData.classifyIDList = props.classifyIDList;
      viewData.tagIDList = props.tagIDList;
    });

    /**
     * 分页数据获取
     */
    const getPagination = (): void => {
      viewData.refresh = false;
      api
        .getPandectArticle(
          viewData.currentPage,
          viewData.pageSize,
          viewData.articleOrderBy,
          viewData.isMy === true ? viewData.username : "",
          viewData.keyword,
          viewData.classifyIDList,
          viewData.tagIDList
        )
        .then(({ data }) => {
          viewData.articleInformationList = data.articleInformationList;
          viewData.total = data.total;
          viewData.refresh = true;
          Message.successMessage("查询到" + viewData.total + "条数据");
        });
    };

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
        emit("myArticle", viewData.isMy);
        getPagination();
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
      getPagination();
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
      getPagination();
    };

    const classifyClick = (id: number): void => {
      emit("classifyClick", id);
    };

    const tagClick = (id: number): void => {
      emit("tagClick", id);
    };

    // onMounted((): void => {
    //   getPagination();
    // });

    return {
      ...toRefs(viewData),
      getPagination,
      handleSizeChange,
      handleCurrentChange,
      updateArticle,
      myArticle,
      pagination,
      classifyClick,
      tagClick,
    };
  },
  components: {
    Article,
  },
});
</script>
<style lang="less">
@import url(./pandectArticle.less);
</style>
