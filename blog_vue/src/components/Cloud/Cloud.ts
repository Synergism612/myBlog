import ClassifyInformation from "@/model/classify/ClassifyInformation";
import TagInformation from "@/model/tag/TagInformation";

class Cloud {
  /**当元素在X轴上字体大小*/
  fontSize!: number;
  /**球体半径*/
  RADIUS!: number;
  /**数据存放 */
  dataList!: Array<TagInformation> | Array<ClassifyInformation>;
  /**球体直径，其实用于计算字体大小，如元素在Y轴最大值即160上;字体显示最大24px*/
  fallLength!: number;
  /**经过计算沿球表面均匀分布的元素*/
  elements!: Array<CloudElement>;
  /**沿X轴旋转角度*/
  angleX!: number;
  /**沿Y轴旋转角度*/
  angleY!: number;
  /**球体中心x轴*/
  CX!: number;
  /**球体中心y轴*/
  CY!: number;

  constructor() {
    this.fontSize = 12;
    this.RADIUS = 80;
    this.fallLength = 160;
    this.elements = [new CloudElement()];
    this.angleX = ((Math.random() - 0.5) * Math.PI) / 250;
    this.angleY = ((Math.random() - 0.5) * Math.PI) / 250;
    this.CX = 0;
    this.CY = 0;
  }
}

class CloudElement {
  data!: TagInformation | ClassifyInformation;
  x!: number;
  y!: number;
  z!: number;

  constructor() {
    this.data = new TagInformation() || new ClassifyInformation();
    this.x = 0;
    this.y = 0;
    this.z = 0;
  }
}

export { Cloud, CloudElement };
