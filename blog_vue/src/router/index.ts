import { createRouter, createWebHistory, RouteRecordRaw } from "vue-router";
import Write from "src/views/write/Write.vue";
import Homepage from "src/views/homepage/Homepage.vue";
import Pandect from "src/views/pandect/Pandect.vue";
import Content from "src/views/content/Content.vue";
import Index from "src/views/index/Index.vue";
import Login from "src/views/login/Login.vue";
import Register from "src/views/register/Register.vue";
import Pigeonhole from "src/views/pigeonhole/Pigeonhole.vue";
import DBank from "src/views/dbank/DBank.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "Home",
    redirect: {
      name: "Index",
    },
  },
  {
    path: "/blog/dbank",
    name: "DBank",
    component: DBank,
  },
  {
    path: "/blog/pigeonhole",
    name: "Pigeonhole",
    component: Pigeonhole,
  },
  {
    path: "/blog/write/:id?",
    name: "Write",
    component: Write,
  },
  {
    path: "/blog/homepage",
    name: "Homepage",
    component: Homepage,
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
