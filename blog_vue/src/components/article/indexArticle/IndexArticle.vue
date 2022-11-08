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

    <transition
      appear
      appear-active-class="animate__animated animate__backInLeft"
    >
      <el-row class="tool" v-if="isMy === true">
        <el-col :span="24">
          <span>文章管理：</span>
          <span class="click" @click="write">
            <font-awesome-icon :icon="['fas', 'pen']" />
            创作
          </span>
          <span class="click" @click="updateWriteShow = true">
            <font-awesome-icon :icon="['fas', 'edit']" />
            修改
          </span>
          <span class="click" @click="deleteArticleShow = true">
            <font-awesome-icon :icon="['fas', 'trash']" />
            删除
          </span>
        </el-col>
      </el-row>
    </transition>
    <div>
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

  <div v-if="total <= 0" style="text-align: center">没有文章</div>

  <el-dialog v-model="updateWriteShow" :show-close="false" destroy-on-close>
    <template #header>
      <div class="my-header">
        <div class="close">
          <span class="title">要修改的文章</span>
          <span @click="updateWriteShow = false" class="click rotate">
            <font-awesome-icon :icon="['fas', 'times']" />
          </span>
        </div>
      </div>
    </template>
    <el-row justify="space-around" align="middle">
      <el-col :span="24">
        <el-select
          v-model="articleID"
          placeholder="您可以输入筛选关键字"
          filterable
        >
          <el-option
            v-for="article in articleInformationList"
            :key="article.id"
            :label="article.title"
            :value="article.id"
          />
        </el-select>
      </el-col>
      <el-col :span="24" class="button">
        <span @click="update" class="click">修改</span>
        <span @click="updateWriteShow = false" class="click">取消</span>
      </el-col>
    </el-row>
  </el-dialog>

  <el-dialog v-model="deleteArticleShow" :show-close="false" destroy-on-close>
    <template #header>
      <div class="my-header">
        <div class="close">
          <span class="title">要删除的文章</span>
          <span @click="deleteArticleShow = false" class="click rotate">
            <font-awesome-icon :icon="['fas', 'times']" />
          </span>
        </div>
      </div>
    </template>
    <el-row justify="space-around" align="middle">
      <el-col :span="24">
        <el-select
          v-model="articleIDList"
          placeholder="您可以输入筛选关键字"
          filterable
          :multiple="true"
        >
          <el-option
            v-for="article in articleInformationList"
            :key="article.id"
            :label="article.title"
            :value="article.id"
          />
        </el-select>
      </el-col>
      <el-col :span="24" class="button">
        <span @click="remove" class="click">确定删除</span>
        <span @click="deleteArticleShow = false" class="click">取消</span>
      </el-col>
    </el-row>
  </el-dialog>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import IndexArticle from "./IndexArticle";
import Article from "src/components/article/Article.vue";
import StringUtil from "src/utils/StringUtil";
import Message from "src/utils/MessageUtil";
import { useRouter } from "vue-router";
import { api } from "src/api/api";
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

    const router = useRouter();
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

    const write = (): void => {
      router.push({
        name: "Write",
      });
    };

    const update = (): void => {
      if (viewData.articleID || 0 > 0) {
        router.push({
          name: "Write",
          params: {
            id: viewData.articleID,
          },
        });
      } else {
        Message.warningMessage("无效的选择");
      }
    };

    const remove = (): void => {
      api
        .removeIndexArticle(viewData.username, viewData.articleIDList)
        .then((): void => {
          Message.successMessage("删除成功");
          viewData.deleteArticleShow = false;
          viewData.getPagination();
        });
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
      classifyClick,
      tagClick,
      write,
      update,
      remove,
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
