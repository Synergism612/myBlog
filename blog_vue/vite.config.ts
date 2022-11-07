import { defineConfig } from "vite";
import vue from "@vitejs/plugin-vue";
import { resolve } from "path";

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      "src": resolve(__dirname, "src"), // 路径别名
    },
    extensions: [".js", ".json", ".ts"], // 使用路径别名时想要省略的后缀名，可以自己 增减
  },
  define: { "process.env": {} },
  server: {
    host: "0.0.0.0",
    port: 8080,
    https: false,
    // proxy: {
    //   "/api": {
    //     target: "http://localhost:8088/",
    //     changeOrigin: true,
    //     rewrite: (path) => path.replace(/^\/api/, ""), // 不可以省略rewrite
    //   },
    // },
  },
});
