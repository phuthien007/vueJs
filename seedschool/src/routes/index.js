import DetailInformationPage from '../views/DetailInformationPage.vue'
// import HelloWorld from '../components/HelloWorld.vue'
import Vue from "vue";
import VueRouter from "vue-router";


Vue.use(VueRouter);
const router = new VueRouter({
  routes: [
    {
      path: '*',
      redirect:'/admin'
      ,
              
    },
    {
      path: '/login',
      name: 'login-admin',
              
      component: () => import(/* webpackChunkName: "about" */ '../views/LoginPage.vue')
    },
    {
      path: '/logout',
      redirect:'/login',
              
    },
  {
    path: '/admin',
    component: DetailInformationPage
    ,
  },
  {
    path: '/admin/action-outdoor',
    name: 'action-outdoor',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/ActionOutdoorPage.vue')
  }
  ,
  {
    path: '/admin/fee-school',
    name: 'fee-school',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/FeeSchoolPage.vue')
  }
  ,
  {
    path: '/admin/lists',
    name: 'lists',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/ListsPage.vue')
  }
  ,
  {
    path: '/admin/settings',
    name: 'settings',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/SettingPage.vue')
  }
  ,
  {
    path: '/admin/statis',
    name: 'statis',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/StaticPage.vue')
  }
  ,
  {
    path: '/admin/menu',
    name: 'menu',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/MenuPage.vue')
  }
]})
export default router;


router.beforeEach((to, from, next) => {
  // chuy???n ?????n trang login n???u ch??a ???????c login
  const publicPages = ['/login'];
  const authRequired = !publicPages.includes(to.path);
  const loggedIn = localStorage.getItem('token');

  if (authRequired && !loggedIn) {
    return next('/login');
  }

  next();
})