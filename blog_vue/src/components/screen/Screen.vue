<template>
  <div id="screen_container"></div>
  <div id="screen_shade"></div>
</template>
<script lang="ts">
import { defineComponent, onMounted, onUnmounted, reactive, toRefs } from "vue";
export default defineComponent({
  setup() {
    const viewData = reactive({
      top: 0,
    });

    const scrollTop = () => {
      viewData.top = document.documentElement.scrollTop;
      console.log(viewData.top);
    };

    onMounted(() => {
      window.addEventListener("scroll", scrollTop);
    });
    onUnmounted(() => {
      window.removeEventListener("scroll", scrollTop);
    });
    return { ...toRefs(viewData), scrollTop };
  },
});
</script>
<style lang="less">
@import url("./Screen.less");
</style>
