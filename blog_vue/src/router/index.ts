import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Pandect from "../views/pandect/Pandect.vue";
import Content from "../views/content/Content.vue";
import Index from "../views/index/Index.vue";
import Login from "../views/login/Login.vue";
import Register from "../views/register/Register.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    redirect: {
      name: "Index",
    },
  },
  {
    path: "/blog/pandect/:type?/:id?",
    name: "Pandect",
    component: Pandect,
  },
  {
    path: "/blog/content/:id?/:body?",
    name: "Content",
    component: Content,
  },
  { path: "/blog/index", name: "Index", component: Index },
  {
    path: "/blog/login",
    name: "Login",
    component: Login,
  },
  {
    path: "/blog/register",
    name: "Register",
    component: Register,
  },

  // {
  //   path: "/",
  //   name: "Home",
  //   component: Home,
  // },
  // {
  //   path: "/about",
  //   name: "About",
  //   // route level code-splitting
  //   // this generates a separate chunk (about.[hash].js) for this route
  //   // which is lazy-loaded when the route is visited.
  //   component: () =>
  //     import(/* webpackChunkName: "about" */ "../views/About.vue"),
  // },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
