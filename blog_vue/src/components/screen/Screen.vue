<template>
  <div class="screen_box" ref="screenRef">
    <div v-if="on">
      <Particles
        id="tsparticles"
        :particlesInit="particlesInit"
        :particlesLoaded="particlesLoaded"
        :options="options"
      />
    </div>
  </div>
  <div class="screen_shade"></div>
</template>
<script lang="ts">
import { defineComponent, reactive, toRefs } from "vue";
import { loadFull } from "tsparticles";
import { Engine } from "tsparticles-engine/types/engine";

export default defineComponent({
  props: {
    on: {
      type: Boolean,
      required: true, //该参数不可为空
    },
  },
  setup() {
    const viewData = reactive({});

    const particlesInit = async (engine: Engine): Promise<void> => {
      await loadFull(engine);
    };

    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const particlesLoaded = async (container: any): Promise<void> => {
      console.log("Particles container loaded", container);
    };
    const options = reactive({
      background: {
        color: {
          value: "", // 背景颜色-透明
        },
      },
      fpsLimit: 60,
      interactivity: {
        events: {
          onClick: {
            enable: true,
            mode: "repulse", // 可用的click模式有: "push", "remove", "repulse", "bubble"。
          },
          onHover: {
            enable: true,
            mode: "grab", // 可用的hover模式有: "grab", "repulse", "bubble"。
          },
          resize: true,
        },
        modes: {
          bubble: {
            distance: 400,
            duration: 2,
            opacity: 0.8,
            size: 40,
          },
          push: {
            quantity: 4,
          },
          repulse: {
            distance: 200,
            duration: 0.4,
          },
        },
      },
      particles: {
        color: {
          value: "#ffffff",
        },
        links: {
          color: "#ffffff", // '#dedede'。线条颜色。
          distance: 150, // 线条长度
          enable: true, // 是否有线条
          opacity: 0.3, // 线条透明度。
          width: 0.8, // 线条宽度。
        },
        collisions: {
          enable: false,
        },
        move: {
          direction: "none",
          enable: true,
          outMode: "bounce",
          random: false,
          speed: 2, // 粒子运动速度。
          straight: false,
        },
        number: {
          density: {
            enable: true,
            area: 800,
          },
          value: 50, // 粒子数量。
        },
        opacity: {
          value: 0.5, // 粒子透明度。
        },
        shape: {
          type: "polygon", // 可用的粒子外观类型有："circle","edge","triangle", "polygon","star"
        },
        size: {
          random: true,
          value: 3,
        },
      },
      detectRetina: true,
    });

    return { ...toRefs(viewData), particlesInit, particlesLoaded, options };
  },
});
</script>
<style lang="less">
@import url("./Screen.less");
</style>
