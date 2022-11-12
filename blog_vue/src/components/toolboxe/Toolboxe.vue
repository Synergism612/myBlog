<template>
  <div class="toolBoxesBox" v-if="show">
    <div class="toolboxes">
      <div class="lessen tool" v-if="lessen" @click="toLessen">
        <font-awesome-icon :icon="['fas', 'compress']" />
      </div>
      <div class="full tool" v-if="full" @click="toFull">
        <font-awesome-icon :icon="['fas', 'expand']" />
      </div>
      <div class="catalog tool" v-if="catalog" @click="toCatalog">
        <font-awesome-icon :icon="['fas', 'bars']" />
      </div>
      <div class="forum tool" v-if="forum" @click="toForum">
        <font-awesome-icon :icon="['fas', 'comments']" />
      </div>
      <div class="index tool" v-if="index" @click="toIndex">
        <font-awesome-icon :icon="['fas', 'house']" />
      </div>
      <div class="top tool" v-if="top" @click="toTop">
        <font-awesome-icon :icon="['fas', 'chevron-up']" />
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import { useRouter } from "vue-router";
import Toolboxe from "./Toolboxe";

export default defineComponent({
  props: {
    pageName: { type: String, required: false },
  },
  emits: {
    toFull: null,
    toCatalog: null,
    toForum: null,
  },
  setup(props, { emit }) {
    /**数据仓初始化 */
    const viewData = reactive(new Toolboxe());

    const html = document.documentElement;
    const screen = html.clientHeight;

    const router = useRouter();

    /**
     * 该函数用于是否开启
     * 当滚动高度大于某值时出现工具栏
     */
    const showInit = (): void => {
      window.addEventListener("scroll", () => {
        if (html.scrollTop >= screen) {
          viewData.show = true;
        } else {
          viewData.show = false;
        }
      });
    };

    const toLessen = (): void => {
      viewData.full = true;
      emit("toFull", false);
      viewData.forum = true;
      viewData.catalog = true;
      viewData.lessen = false;
    };

    const toFull = (): void => {
      viewData.lessen = true;
      emit("toFull", true);
      viewData.forum = false;
      viewData.catalog = false;
      viewData.full = false;
    };

    const toCatalog = (): void => {
      emit("toCatalog", true);
    };

    const toForum = (): void => {
      emit("toForum", true);
    };

    const toIndex = (): void => {
      router.push({ name: "Index" });
    };

    const toTop = (): void => {
      top();
    };

    /**回到顶部函数 */
    const top = (): void => {
      viewData.show = false;
      var timer = setInterval(() => {
        if (html.scrollTop <= 0) {
          clearInterval(timer);
          if (viewData.topShow) {
            viewData.show = true;
          }
        }
        html.scrollTop = html.scrollTop - screen / 2;
      }, 10);
    };

    onMounted(() => {
      switch (props.pageName) {
        case "content": {
          viewData.show = true;
          viewData.full = true;
          viewData.catalog = true;
          viewData.forum = true;
          viewData.index = true;
          viewData.top = true;
          viewData.topShow = true;
          break;
        }
        default: {
          viewData.top = true;
          showInit();
          break;
        }
      }
    });

    return {
      ...toRefs(viewData),
      toLessen,
      toFull,
      toCatalog,
      toForum,
      toIndex,
      toTop,
    };
  },
  components: {},
});
</script>
<style lang="less">
@import url(./Toolboxe.less);
</style>
