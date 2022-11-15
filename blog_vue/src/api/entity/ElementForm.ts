import { store } from "src/store";

export class ClassifyForm {
  username: string;
  name: string;
  annotation: string;

  constructor() {
    this.username = store.getters.getUsername;
    this.name = "";
    this.annotation = "";
  }
}

export class TagForm {
  username: string;
  name: string;
  annotation: string;

  constructor() {
    this.username = store.getters.getUsername;
    this.name = "";
    this.annotation = "";
  }
}