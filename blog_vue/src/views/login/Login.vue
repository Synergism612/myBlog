<template>
  <div class="background">asdf</div>
  <div class="login">asdf</div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";

export default defineComponent({
  /**
   * 组件内setup，setup最早创建，所以没有this
   */
  setup() {
    const data = reactive({
      msg: "aa",
    });

    return { ...toRefs(data) };
  },
});
</script>
<style lang="less" scoped>
@import url(./Login.less);
</style>
