<template>
  <div id="index_background"></div>
  <div id="index_container">
    <Menu></Menu>
    <Screen></Screen>
    <div id="index_notice">1234567</div>
  </div>
</template>
<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";
import Menu from "@/components/menu/Menu.vue";
import Screen from "@/components/screen/Screen.vue";
export default defineComponent({
  setup() {
    const viewData = reactive({});
    return { ...toRefs(viewData) };
  },
  components: { Menu, Screen },
});
</script>
<style lang="less">
@import url("./Index.less");
</style>