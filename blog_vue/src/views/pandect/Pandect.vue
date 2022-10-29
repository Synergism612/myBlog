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
        <el-main> </el-main>
      </el-container>
    </div>
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Pandect from "./Pandect";
import Menu from "@/components/menu/Menu.vue";

export default defineComponent({
  setup() {
    /**数据仓 */
    const viewData = reactive(new Pandect());

    onMounted(() => {
      console.log("");
    });

    return {
      ...toRefs(viewData),
    };
  },
  components: { Menu },
});
</script>
<style lang="less">
@import url("./Pandect.less");
</style>
