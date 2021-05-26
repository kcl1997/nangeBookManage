import Vue from 'vue'
import VueRouter from 'vue-router'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect: '/index'
  },
  {
    path: '/index',
    name: '首页',
    component: () => import('../views/Index'),
    redirect: '/BookShow',
    children: [
      {
        path: '/BookShow',
        name: '显示图书',
        component: ()=> import('../views/BookShow')
      },
      {
        path: '/BookAdd',
        name: '添加图书',
        component: ()=> import('../views/BookAdd')
      },
      {
        path: '/Page1-3',
        name: 'Page1-3',
        component: ()=> import('../views/Page1-3')
      },
      {
        path: '/Page2-1',
        name: 'Page2-1',
        component: ()=> import('../views/Page2-1')
      },
      {
        path: '/Page1-4-1',
        name: 'Page1-4-1',
        component: ()=> import('../views/Page1-4-1')
      }
    ]
  }

]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
