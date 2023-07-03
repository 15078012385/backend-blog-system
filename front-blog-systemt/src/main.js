import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import 'github-markdown-css/github-markdown.css'
import 'github-markdown-css/github-markdown.css'
import hljs from 'highlight.js'
// 也可以把这自定义指令封装 通过Vue.use()，来注入
Vue.directive('highlight', function (el) {
  const blocks = el.querySelectorAll('pre code')
  blocks.forEach(block => {
    hljs.highlightBlock(block)
  })
})



Vue.use(mavonEditor)
Vue.config.productionTip = false
// 全局引入相关包
import * as echarts from "echarts";
// 开启echarts
Vue.prototype.$echarts = echarts;
// 全局引入axios
import request from "@/utils/request";
// 给vue实例，添加封装好的axios。这样在组件中就可以直接使用this.request来调用axios
Vue.prototype.request = request;

new Vue({
  router,
  store,
  render: function (h) { return h(App) }
}).$mount('#app')
