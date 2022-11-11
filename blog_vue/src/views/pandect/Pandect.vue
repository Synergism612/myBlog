<template>
  <div class="pandectBox">
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
                    <a href="/">文章总览</a>
                  </el-breadcrumb-item>
                </el-breadcrumb>
              </el-col>
            </transition>
          </el-row>
        </el-header>
        <el-main>
          <!-- 主要内容 -->
          <el-row justify="space-around">
            <!-- 主体框架 -->
            <el-col :xs="24" :sm="24" :md="24" :lg="20">
              <!-- 分左右区 -->
              <el-row :gutter="20" justify="space-around">
                <!-- 左侧 -->
                <el-col :xs="24" :sm="18" :md="18" :span="18">
                  <div class="left">
                    <PandectArticle
                      ref="pandectArticle"
                      v-model:keyword="keyword"
                      v-model:classifyIDList="classifyIDList"
                      v-model:tagIDList="tagIDList"
                      @classifyClick="classifyClick"
                      @tagClick="tagClick"
                      @my-article="myArticle"
                    ></PandectArticle>
                  </div>
                </el-col>
                <!-- 右侧 -->
                <el-col :xs="0" :sm="6" :md="6" :span="6">
                  <div class="right">
                    <el-col :span="24" class="child frame">
                      <div class="center">搜索</div>
                      <el-row class="search">
                        <el-col :span="20">
                          <el-input
                            v-model="keyword"
                            placeholder="请输入搜索内容"
                            @keyup.enter="search()"
                          />
                        </el-col>
                        <el-col :span="3" :offset="1">
                          <span class="click" @click="search">
                            <font-awesome-icon
                              :icon="['fas', 'magnifying-glass']"
                            />
                          </span>
                        </el-col>
                      </el-row>
                    </el-col>
                    <el-col :span="24" class="child frame">
                      <div class="center">分类</div>
                      <el-row :gutter="5" class="elements">
                        <el-col
                          v-for="classify in classifyList"
                          :key="classify.id"
                          :span="12"
                        >
                          <div
                            :class="
                              (classifyIDList.indexOf(classify.id) != -1
                                ? 'down'
                                : '') + ' element'
                            "
                            @click="classifyClick(classify.id)"
                          >
                            {{ classify.name }}
                          </div>
                        </el-col>
                      </el-row>
                    </el-col>
                    <el-col :span="24" class="child frame">
                      <div class="center">标签</div>
                      <el-row :gutter="5" class="elements">
                        <el-col v-for="tag in tagList" :key="tag.id" :span="12">
                          <div
                            :class="
                              (tagIDList.indexOf(tag.id) != -1 ? 'down' : '') +
                              ' element'
                            "
                            @click="tagClick(tag.id)"
                          >
                            {{ tag.name }}
                          </div>
                        </el-col>
                      </el-row>
                    </el-col>
                  </div>
                </el-col>
              </el-row>
            </el-col>
          </el-row>
        </el-main>
      </el-container>
    </div>
    <Toolboxe page-name="pandect"></Toolboxe>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, ref, toRefs } from "vue";
import Pandect from "./Pandect";
import Menu from "src/components/menu/Menu.vue";
import PandectArticle from "src/components/article/pandectArticle/PandectArticle.vue";
import { useRoute } from "vue-router";
import Toolboxe from "src/components/toolboxe/Toolboxe.vue";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Pandect());

    const pandectArticle = ref();

    const search = (): void => {
      if (viewData.checkKeyword()) {
        pandectArticle.value.getPagination();
      }
    };

    const classifyClick = (id: number): void => {
      if (viewData.classifyIDList.indexOf(id) === -1) {
        viewData.classifyIDList.push(id);
      } else {
        viewData.classifyIDList.splice(viewData.classifyIDList.indexOf(id), 1);
      }
      if (
        viewData.classifyIDList.length > 1 &&
        viewData.classifyIDList[0] === -1
      ) {
        viewData.classifyIDList.splice(0, 1);
      }
      if (viewData.classifyIDList.length === 0) {
        viewData.classifyIDList.push(-1);
      }
      search();
    };

    const tagClick = (id: number): void => {
      if (viewData.tagIDList.indexOf(id) === -1) {
        viewData.tagIDList.push(id);
      } else {
        viewData.tagIDList.splice(viewData.tagIDList.indexOf(id), 1);
      }
      if (viewData.tagIDList.length > 1 && viewData.tagIDList[0] === -1) {
        viewData.tagIDList.splice(0, 1);
      }
      if (viewData.tagIDList.length === 0) {
        viewData.tagIDList.push(-1);
      }
      search();
    };

    const route = useRoute();

    const routeInit = (): void => {
      const type = route.params.type;
      const id = Number(route.params.id);
      switch (type) {
        case "tag":
          tagClick(id);
          break;
        case "classify":
          classifyClick(id);
          break;
        default:
          search();
          break;
      }
    };

    const myArticle = (isMy: boolean): void => {
      if (isMy) {
        viewData.isMyInit();
      } else {
        viewData.init();
      }
    };

    onMounted(() => {
      viewData.init();
      routeInit();
    });

    return {
      ...toRefs(viewData),
      pandectArticle,
      search,
      classifyClick,
      tagClick,
      myArticle,
    };
  },
  components: { Menu, PandectArticle, Toolboxe },
});
</script>
<style lang="less">
@import url("./Pandect.less");
</style>
