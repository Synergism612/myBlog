<template>
  <div class="pandectBox">
  
  </div>
</template>
<script lang="ts">
import { defineComponent, onMounted, reactive, toRefs } from "vue";
import Pandect from "./Pandect";
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
  components: {},
});
</script>
<style lang="less">
@import url("./Pandect.less");
</style>
