<template>
  <div>
    <div v-if="!checkListIsEmpty()" class="box">
      <!-- 文章列表 -->
      <el-row class="articles">
        <el-row
          v-for="article in articleInformationList"
          :key="article.id"
          class="article"
        >
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
                    <span @click="toContent(article.id, article.title)">
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

    <div v-if="checkListIsEmpty()" style="text-align: center">没有文章</div>
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
import {
  Avatar as avatar,
  GoodTwo as good_two,
  PreviewOpen as preview_open,
  UpdateRotation as update_rotation,
  DocumentFolder as document_folder,
  TagOne as tag_one,
} from "@icon-park/vue-next";
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

    /**
     * 检查文章列表是否为空
     */
    const checkListIsEmpty = (): boolean => {
      if (
        viewData.articleInformationList[0] !== (null || undefined) &&
        viewData.articleInformationList[0].id !== -1
      ) {
        return false;
      }
      return true;
    };

    const router = useRouter();

    const toContent = (id: number, title: string): void => {
      router.push({
        name: "Content",
        params: {
          id: id,
          title: title,
        },
      });
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
      checkListIsEmpty,
      toContent,
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
