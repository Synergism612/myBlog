<template>
  <div class="box" v-if="show">
    <div class="toolboxes">
      <div class="lessen tool" v-if="lessen" @click="toLessen">
        <OffScreen theme="outline" size="24" fill="#333" />
      </div>
      <div class="full tool" v-if="full" @click="toFull">
        <FullScreen theme="outline" size="24" fill="#333" />
      </div>
      <div class="index tool" v-if="index" @click="toIndex">
        <Home theme="outline" size="24" fill="#333" />
      </div>
      <div class="top tool" v-if="top" @click="toTop">
        <ArrowUp theme="outline" size="24" fill="#333" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { ArrowUp, FullScreen, Home, OffScreen } from "@icon-park/vue-next";
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
import Toolboxe from "./Toolboxe";

export default defineComponent({
  props: {
    pageName: { type: String, required: true },
  },
  setup(props, { emit }) {
    /**数据仓初始化 */
    const viewData = reactive(new Toolboxe());

    const html = document.documentElement;
    const screen = html.clientHeight;

    const router = useRouter();

    const showInit = (): void => {
      window.addEventListener("scroll", () => {
        // 滚动事件
        if (html.scrollTop >= screen) {
          //当滚动高度大于等于100返回顶部出现
          viewData.show = true;
        } else {
          viewData.show = false;
        }
      });
    };

    const toLessen = (): void => {
      viewData.full = true;
      emit("toFull", false);
      viewData.lessen = false;
    };

    const toFull = (): void => {
      viewData.lessen = true;
      emit("toFull", true);
      viewData.full = false;
    };

    const toIndex = (): void => {
      router.push({ name: "Index" });
    };

    const toTop = (): void => {
      var timer = setInterval(() => {
        if (html.scrollTop <= 0) {
          clearInterval(timer);
        }
        html.scrollTop = html.scrollTop - screen / 10;
      }, 10);
    };

    onMounted(() => {
      switch (props.pageName) {
        case "index":
          {
            viewData.top = true;
            showInit();
          }
          break;
        case "content":
          {
            viewData.show = true;
            viewData.full = true;
            viewData.index = true;
            viewData.top = true;
          }
          break;

        default:
          break;
      }
    });

    return {
      ...toRefs(viewData),
      toLessen,
      toFull,
      toIndex,
      toTop,
    };
  },
  components: {
    ArrowUp,
    Home,
    FullScreen,
    OffScreen,
  },
});
</script>
<style lang="less">
@import url(./Toolboxe.less);
</style>
