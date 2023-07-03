import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}


const routes = [
  // 常规页面
  {
    path: '/',
    name: 'home',
    component: () => import(/* webpackChunkName: "index" */ '../views/HomeView.vue'),
    children: [
      {
        path: "/index",
        name: "index",
        component: () => import(/* webpackChunkName: "something" */ '../views/DQSY_indexView.vue')
      },
      {
        path: "/something",
        name: "something",
        component: () => import(/* webpackChunkName: "something" */ '../views/DQSY_somethingView.vue')
      },
      {
        path: "/img",
        name: "img",
        component: () => import(/* webpackChunkName: "img" */ '../views/DQSY_imgView.vue')
      },
      {
        path: "/notice",
        name: "notice",
        component: () => import(/* webpackChunkName: "notice" */ '../views/DQSY_noticeView.vue')
      },
      {
        path: "/personal",
        name: "personal",
        component: () => import(/* webpackChunkName: "personal" */ '../views/PersonalView.vue')
      },
      {
        path: "/article",
        name: "article",
        component: () => import(/* webpackChunkName: "article" */ '../views/ArticleView.vue')
      }
    ],
  },
  // 普通用户后台管理
  {
    path: '/user-backend',
    name: 'user-backend',
    component: () => import(/* webpackChunkName: "user-backend" */ '../views/user/UserBackendView.vue'),
    children: [
      {
        path: '/something-manage',
        name: 'something-manage',
        component: () => import(/* webpackChunkName: "something-manage" */ '../views/user/DQSY_somethingManageView.vue')
      },
      {
        path: '/img-manage',
        name: 'img-manage',
        component: () => import(/* webpackChunkName: "img-manage" */ '../views/user/DQSY_imgManageView.vue')
      },
      {
        path: "/msg",
        name: "msg",
        component: () => import(/* webpackChunkName: "msg" */ '../views/user/MsgView.vue')
      },
      {
        path: "/markdown",
        name: "markdown",
        component: () => import(/* webpackChunkName: "markdown" */ '../views/user/UserMarkDownView.vue')
      }
    ]
  },
  // 管理员后台管理
  {
    path: "/admin-backend",
    name: "admin-backend",
    component: () => import(/* webpackChunkName: "admin-backend" */ '../views/admin/AdminBackendView.vue'),
    children: [
      {
        path: '/backend-index',
        name: 'backend-index',
        component: () => import(/* webpackChunkName: "backend-index" */ '../views/admin/BackendIndexView.vue')
      },
      {
        path: "/user-manage",
        name: "user-manage",
        component: () => import(/* webpackChunkName: "user-manage" */ '../views/admin/UserManageView.vue')
      },
      {
        path: "/article-manage",
        name: "article-manage",
        component: () => import(/* webpackChunkName: "article-manage" */ '../views/admin/ArticleManageView.vue')
      },
      {
        path: "/img-manage-admin",
        name: "img-manage-admin",
        component: () => import(/* webpackChunkName: "img-manage-admin" */ '../views/admin/ImgManageView.vue')
      },
      {
        path: "/rotation-manage",
        name: "rotation-manage",
        component: () => import(/* webpackChunkName: "rotation-manage" */ '../views/admin/RotationManageView.vue')
      },
      {
        path: "/remomend-manage",
        name: "remomend-manage",
        component: () => import(/* webpackChunkName: "remomend-manage" */ '../views/admin/RecommendView.vue')
      },
      {
        path: "/hot-search-manage",
        name: "hot-search-manage",
        component: () => import(/* webpackChunkName: "hot-search-manage" */ '../views/admin/HotSearchManageView.vue')
      },
      {
        path: "/notice-manage",
        name: "notice-manage",
        component: () => import(/* webpackChunkName: "notice-manage" */ '../views/admin/NoticeManageView.vue')
      },
      {
        path: "/log",
        name: "log",
        component: () => import(/* webpackChunkName: "log" */ '../views/admin/LogView.vue')
      },
      {
        path: "/markdown-admin",
        name: "markdown-admin",
        component: () => import(/* webpackChunkName: "markdown" */ '../views/admin/AdminMarkDownView.vue')
      }
    ]
  },
  {
    path: "/admin-login",
    name: "admin-login",
    component: () => import(/* webpackChunkName: "admin-login" */ '../views/admin/AdminLoginView.vue')
  },
  

]



const router = new VueRouter({
  base: process.env.BASE_URL,
  routes
})

// 路由前置守卫
router.beforeEach((to, from, next) => {

  if (to.path == '/') {
    next({ path: '/index' })
  } else {
    next()
  }

});

export default router
