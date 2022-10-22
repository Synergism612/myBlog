<template>
  <div
    class="box"
    ref="paper"
    @mouseover="setSpeed(30)"
    @mouseleave="setSpeed(1)"
  >
    <span
      v-for="element in props.dataList"
      :key="element.id"
      :ref="setElementRef"
      class="element"
    >
      <span>{{ element.name }}</span>
      <span>{{ element.articleCount }}</span>
    </span>
  </div>
</template>

<script lang="ts">
import ClassifyInformation from "@/model/classify/ClassifyInformation";
import TagInformation from "@/model/tag/TagInformation";
import {
  defineComponent,
  onMounted,
  PropType,
  reactive,
  ref,
  toRefs,
} from "vue";
import { Cloud, CloudElement } from "./Cloud";

export default defineComponent({
  props: {
    dataList: {
      type: Array as PropType<Array<TagInformation | ClassifyInformation>>,
      required: true, //该参数不可为空
    },
  },
  setup(props) {
    const viewData = reactive(new Cloud());

    var elementRefs = ref<HTMLElement[]>([]);
    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    const setElementRef = (element: any): void => {
      if (element) {
        elementRefs.value.push(element);
      }
    };

    const initCustomFormatter = (): void => {
      /**
       * 因为要用2d模拟3d所以我们只需要正的角度值即可
       * 故而，a和b的值为正
       */
      for (let i = 0; i < props.dataList.length; i++) {
        /**当length=10时
         * k = [-0.9,-0.7,-0.5,-0.30000000000000004,-0.09999999999999998,0.10000000000000009,0.30000000000000004,0.5,0.7,0.8999999999999999,]
         * 以下默认length=10
         */
        let k = (2 * (i + 1) - 1) / props.dataList.length - 1;
        /**
         * 求cos反余弦值
         * a = [2.6905658417935308,2.34619382340565,2.0943951023931957,1.8754889808102941,1.6709637479564563,1.4706289056333368,1.266103672779499,1.0471975511965979,0.7953988301841436,0.45102681179626264,]
         */
        let a = Math.acos(k);
        /**
         * 求平方根
         * b = [15.08059791039304,13.150395772155742,11.739066152580703,10.512099263892802,9.365737130215301,8.24286209865575,7.096499964978249,5.8695330762903515,4.458203456715313,2.528001318478014,]
         */
        let b = a * Math.sqrt(props.dataList.length * Math.PI);

        /**
         * 求得球面点具体在坐标轴上的位置
         * r为半径，数据仓里用RADIUS表示
         */

        /**x坐标值=r*sin(a)*cos(b) */
        const x = viewData.RADIUS * Math.sin(a) * Math.cos(b);
        /**y坐标值=r*sin(a)*sin(b) */
        const y = viewData.RADIUS * Math.sin(a) * Math.sin(b);
        /**z坐标值=r*cos(a) */
        const z = viewData.RADIUS * Math.cos(a);

        /**将坐标缓存 */
        const element = { data: elementRefs.value[i], x: x, y: y, z: z };
        viewData.elementList.push(element);
        /**将元素绘制到坐标上 */
        move(element);
      }
      //渲染完成后清空，避免溢出
      elementRefs = ref<HTMLElement[]>([]);
    };

    const paper = ref<HTMLElement | null>(null);

    /**绘画函数 */
    const move = (element: CloudElement): void => {
      let { data, x, y, z } = element;
      let scale = viewData.fallLength / (viewData.fallLength - z);
      let alpha = (z + viewData.RADIUS) / (2 * viewData.RADIUS);
      //让后面的元素文字小一些突出3d感
      data.style.fontSize = viewData.fontSize * scale + "px";
      //让后面的元素淡一些突出3d感
      data.style.opacity = alpha + 0.5 + "";
      data.style.filter = "alpha(opacity = " + (alpha + 0.5) * 100 + ")";
      data.style.zIndex = parseInt(scale * 100 + "") + "";
      //将元素移动到相应的点坐标上
      data.style.transform = `translate(${
        x + viewData.CX - data.offsetWidth / 2
      }px, ${y + viewData.CY - data.offsetHeight / 2}px)`;
    };

    /**旋转x坐标计算函数 */
    const rotateX = (): void => {
      let cos = Math.cos(viewData.angleX);
      let sin = Math.sin(viewData.angleX);

      viewData.elementList.forEach((element) => {
        let y1 = element.y * cos - element.z * sin;
        let z1 = element.z * cos + element.y * sin;
        element.y = y1;
        element.z = z1;
      });
    };
    /**旋转y坐标计算函数 */
    const rotateY = (): void => {
      let cos = Math.cos(viewData.angleY);
      let sin = Math.sin(viewData.angleY);
      viewData.elementList.forEach((element) => {
        let x1 = element.x * cos - element.z * sin;
        let z1 = element.z * cos + element.x * sin;
        element.x = x1;
        element.z = z1;
      });
    };

    /**动画函数 */
    const animate = (): void => {
      clearInterval(time);
      rotateX();
      rotateY();
      viewData.elementList.forEach((element): void => {
        move(element);
      });
      time = setInterval(animate, viewData.speed);
    };

    // eslint-disable-next-line @typescript-eslint/no-explicit-any
    var time = setInterval(animate, viewData.speed);

    /**给与随机颜色，随机生成，限制200，偏暖*/
    const randomColor = (): void => {
      elementRefs.value.forEach((element): void => {
        let r = Math.floor(Math.random() * 200);
        let g = Math.floor(Math.random() * 200);
        let b = Math.floor(Math.random() * 200);
        let color = r + "," + g + "," + b;
        element.style.color = `rgb(${color})`;
      });
    };

    const setSpeed = (speed: number): void => {
      viewData.speed = speed;
    };

    /**初始化函数 */
    const init = () => {
      if (paper.value != null) {
        viewData.CX = paper.value.offsetWidth / 2;
        viewData.CY = paper.value.offsetHeight / 2;
      }
      viewData.angleX = ((Math.random() - 0.5) * Math.PI) / 250;
      viewData.angleY = ((Math.random() - 0.5) * Math.PI) / 250;
      randomColor();
      time;
    };

    onMounted(() => {
      init();
      initCustomFormatter();
    });

    return {
      ...toRefs(viewData),
      props,
      setElementRef,
      paper,
      setSpeed,
    };
  },
});
</script>
<style lang="less">
@import url(./Cloud.less);
</style>
