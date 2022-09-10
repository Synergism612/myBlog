<template>
  <div class="index">asdfasdf</div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";

export default defineComponent({
  /**
   * 组件内setup，setup最早创建，所以没有this
   */
  setup() {
    /**
     * 参数定义
     */
    const data = reactive({
      msg: "aa",
    });

    /**
     * 返回组件内数据
     */
    return { ...toRefs(data) };
  },
});
</script>
