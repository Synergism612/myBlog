<template>
  <div class="pigeonholeBox">
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
                    <el-timeline>
                      <el-timeline-item timestamp="2018/4/12" placement="top">
                        <el-card>
                          <h4>更新 Github 模板</h4>
                          <p>王小虎 提交于 2018/4/12 20:46</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2018/4/3" placement="top">
                        <el-card>
                          <h4>更新 Github 模板</h4>
                          <p>王小虎 提交于 2018/4/3 20:46</p>
                        </el-card>
                      </el-timeline-item>
                      <el-timeline-item timestamp="2018/4/2" placement="top">
                        <el-card>
                          <h4>更新 Github 模板</h4>
                          <p>王小虎 提交于 2018/4/2 20:46</p>
                        </el-card>
                      </el-timeline-item>
                    </el-timeline>
                  </div>
                </el-col>
                <!-- 右侧 -->
                <el-col :xs="0" :sm="6" :md="6" :span="6">
                  <div class="right">右侧</div>
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
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Menu from "src/components/menu/Menu.vue";
import Pigeonhole from "./Pigeonhole";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Pigeonhole());

    onMounted(() => {
      viewData.init();
    });

    return {
      ...toRefs(viewData),
    };
  },
  components: { Menu },
});
</script>
<style lang="less">
@import url("./Pigeonhole.less");
</style>
