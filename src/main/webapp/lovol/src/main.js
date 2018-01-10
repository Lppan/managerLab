// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'

import 'common/style/index.scss'
import 'common/style/iconfont.css'

//引入封装请求
import _request from './frame/request.js'
// import _tool from './frame/tool.js'

Vue.use(ElementUI)
Vue.use(_request)
// Vue.use(_tool)

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  template: '<App/>',
  components: { App }
})
