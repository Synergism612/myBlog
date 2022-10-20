<template>
  <div id="box">
    <el-row>
      <el-col :span="2" v-for="element in dataList" :key="element.id">
        {{ element.name }}
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts">
import ClassifyInformation from "@/model/classify/ClassifyInformation";
import TagInformation from "@/model/tag/TagInformation";
import { defineComponent, reactive, toRefs } from "vue";
import { Cloud, CloudElement } from "./Cloud";

export default defineComponent({
  props: {
    list: {
      type: Array,
      required: true, //该参数不可为空
    },
  },
  setup(props) {
    const viewData = reactive(new Cloud());

    viewData.dataList = props.list as
      | Array<TagInformation>
      | Array<ClassifyInformation>;

    const initCustomFormatter = (): void => {
      /**
       * 因为要用2d模拟3d所以我们只需要正的角度值即可
       * 故而，a和b的值为正
       */
      for (let i = 0; i < viewData.dataList.length; i++) {
        /**当length=10时
         * k = [-0.9,-0.7,-0.5,-0.30000000000000004,-0.09999999999999998,0.10000000000000009,0.30000000000000004,0.5,0.7,0.8999999999999999,]
         * 以下默认length=10
         */
        let k = (2 * (i + 1) - 1) / viewData.dataList.length - 1;
        /**
         * 求cos反余弦值
         * a = [2.6905658417935308,2.34619382340565,2.0943951023931957,1.8754889808102941,1.6709637479564563,1.4706289056333368,1.266103672779499,1.0471975511965979,0.7953988301841436,0.45102681179626264,]
         */
        let a = Math.acos(k);
        /**
         * 求平方根
         * b = [15.08059791039304,13.150395772155742,11.739066152580703,10.512099263892802,9.365737130215301,8.24286209865575,7.096499964978249,5.8695330762903515,4.458203456715313,2.528001318478014,]
         */
        let b = a * Math.sqrt(viewData.dataList.length * Math.PI);

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
        const element = { data: viewData.dataList[i], x: x, y: y, z: z };
        viewData.elements.push(element);
        /**将元素绘制到坐标上 */
        // move(element);
      }
    };

    // const move = (element: CloudElement): void => {
    //   let { data, x, y, z } = element;
    //   let scale = viewData.fallLength / (viewData.fallLength - z);
    //   let alpha = (z + viewData.RADIUS) / (2 * viewData.RADIUS); //让后面的元素文字小一些突出3d感
    //   data.style.fontSize = viewData.fontSize * scale + "px"; //让后面的元素淡一些突出3d感
    //   data.style.opacity = alpha + 0.5;
    //   data.style.filter = "alpha(opacity = " + (alpha + 0.5) * 100 + ")";
    //   data.style.zIndex = parseInt(scale * 100); //将元素移动到相应的点坐标上
    //   data.style.transform = `translate(${
    //     x + viewData.CX - data.offsetWidth / 2
    //   }px, ${y + viewData.CY - data.offsetHeight / 2}px)`;
    // };

    return { ...toRefs(viewData), props };
  },
});
</script>
<style lang="less">
@import url(./Cloud.less);
</style>
