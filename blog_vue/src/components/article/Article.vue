<template>
  <div>
    <div v-if="total > 0" class="articleBox">
      <!-- 文章列表 -->
      <el-row class="articles">
        <el-row v-for="article in articleInformationList" :key="article.id">
          <transition
            appear
            appear-active-class="animate__animated animate__zoomIn"
            enter-active-class="animate__animated animate__zoomIn"
          >
            <div class="article frame">
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
                    <span class="click" @click="toContent(article.id)">
                      {{ article.title }}
                    </span>
                  </el-col>
                </el-row>
                <el-row class="synopsis">
                  <el-col :span="24">
                    <span>{{ article.synopsis }}</span>
                  </el-col>
                </el-row>
                <el-row class="label">
                  <el-col :span="24">
                    <font-awesome-icon :icon="['fas', 'bookmark']" />
                    <div class="classify">
                      <span
                        @click="classifyClick(article.classify.id)"
                        class="click"
                      >
                        {{ article.classify.name }}
                      </span>
                    </div>
                  </el-col>
                  <el-col :span="24">
                    <font-awesome-icon :icon="['fas', 'tags']" />
                    <div
                      v-for="tag in article.tagList"
                      :key="tag.id"
                      class="tags"
                    >
                      <span @click="tagClick(tag.id)" class="click">{{
                        tag.name
                      }}</span>
                    </div>
                  </el-col>
                </el-row>
                <el-row class="footer" justify="space-between">
                  <el-col :md="4" :span="6">
                    <font-awesome-icon :icon="['fas', 'user']" />
                    <span class="click">{{ article.nickname }}</span>
                  </el-col>
                  <el-col :md="2" :span="3">
                    <font-awesome-icon :icon="['fas', 'thumbs-up']" />
                    <span> {{ article.likeCount }} </span>
                  </el-col>
                  <el-col :md="2" :span="3">
                    <font-awesome-icon :icon="['fas', 'eye']" />
                    <span> {{ article.views }} </span>
                  </el-col>
                  <el-col :md="2" :span="3">
                    <font-awesome-icon :icon="['fas', 'comment']" />
                    <span> {{ article.commentCount }} </span>
                  </el-col>
                  <el-col :md="6" :span="9">
                    <font-awesome-icon :icon="['fas', 'clock']" />
                    <span> {{ article.creationTime }} </span>
                  </el-col>
                  <el-col :md="6" :span="0">
                    <font-awesome-icon :icon="['fas', 'clock-rotate-left']" />
                    <span> {{ article.modifyTime }} </span>
                  </el-col>
                </el-row>
              </el-col>
            </div>
          </transition>
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

    <div v-if="total <= 0" style="text-align: center">没有文章</div>
  </div>
</template>

<script lang="ts">
import {
  defineComponent,
  PropType,
  reactive,
  toRefs,
  watch,
  watchEffect,
} from "vue";
import Article from "./Article";
import ArticleInformation from "@/model/article/ArticleInformation";
import { useRouter } from "vue-router";
export default defineComponent({
  props: {
    dataList: {
      type: Array as PropType<Array<ArticleInformation>>,
      required: true, //该参数不可为空
    },
    total: {
      type: Number,
      required: true, //该参数不可为空
    },
    currentPage: {
      type: Number,
      required: true, //该参数不可为空
    },
    pageSize: {
      type: Number,
      required: true, //该参数不可为空
    },
  },
  emits: {
    pagination: null,
    classifyClick: null,
    tagClick: null,
  },
  setup(props, { emit }) {
    const viewData = reactive(new Article());

    watchEffect((): void => {
      viewData.articleInformationList = props.dataList;
      viewData.total = props.total;
      viewData.currentPage = props.currentPage;
      viewData.pageSize = props.pageSize;
    });

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

    const router = useRouter();

    const toContent = (id: number): void => {
      router.push({
        name: "Content",
        params: {
          id: id,
        },
      });
    };

    const classifyClick = (id: number): void => {
      emit("classifyClick", id);
    };

    const tagClick = (id: number): void => {
      emit("tagClick", id);
    };

    /**
     * 监听变化后传给父组件
     */
    watch(
      (): Array<number> => [viewData.currentPage, viewData.pageSize],
      (newVal, oldVal): void => {
        newVal;
        oldVal;
        emit("pagination", newVal);
      }
    );

    return {
      ...toRefs(viewData),
      handleSizeChange,
      handleCurrentChange,
      toContent,
      classifyClick,
      tagClick,
    };
  },
  components: {},
});
</script>
<style lang="less">
@import url(./Article.less);
</style>
