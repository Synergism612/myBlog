<template>
  <div id="register_background"></div>
  <div id="register_container">
    <Menu></Menu>
  </div>
</template>

<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";
import Menu from "@/components/Menu/Menu.vue";

export default defineComponent({
  setup() {
    const data = reactive({
      msg: "",
    });
    return { ...toRefs(data) };
  },
  components: { Menu },
});
</script>
<style lang="less">
@import url(./Register.less);
</style>
