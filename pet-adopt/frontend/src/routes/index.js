import Vue from "vue";
import VueRouter from "vue-router";
import CategoryList from "../pages/admin/category/CategoryList.vue";
import CategoryDetail from "../pages/admin/category/CategoryDetail.vue";
import PetList from "../pages/admin/pet/PetList.vue";
import PetDetail from "../pages/admin/pet/PetDetail.vue";
import CustomerList from "../pages/admin/customer/CustomerList.vue";
import CustomerDetail from "../pages/admin/customer/CustomerDetail.vue";
import UserList from "../pages/admin/user/UserList.vue";
import UserDetail from "../pages/admin/user/UserDetail.vue";
import AdoptPetList from "../pages/admin/adopt-pet/AdoptPetList.vue";
import AdoptPetDetail from "../pages/admin/adopt-pet/AdoptPetDetail.vue";


Vue.use(VueRouter);

const router = new VueRouter({
    routes: [
        //category
        {
            path: '/admin',
            component: CategoryList,
            meta: { page: 0 }
        }, {
            path: '/admin/category',
            component: CategoryList,
            meta: { page: 1 }
        }, {
            path: '/admin/category/:id',
            component: CategoryDetail,
            meta: { page: 1 }
        },
        //pet

        {
            path: '/admin/pet',
            component: PetList,
            meta: { page: 2 }
        },
        {
            path: '/admin/pet/:id',
            component: PetDetail,
            meta: { page: 2 }
        },
        //customer

        {
            path: '/admin/customer',
            component: CustomerList,
            meta: { page: 3 }
        },
        {
            path: '/admin/customer/:id',
            component: CustomerDetail,
            meta: { page: 3 }
        },
        //user

        {
            path: '/admin/user',
            component: UserList,
            meta: { page: 4 }
        },
        {
            path: '/admin/user/:id',
            component: UserDetail,
            meta: { page: 4 }
        },
        //adopt pet

        {
            path: '/admin/adopt-pet',
            component: AdoptPetList,
            meta: { page: 5 }
        },
        {
            path: '/admin/adopt-pet/:id',
            component: AdoptPetDetail,
            meta: { page: 5 }
        }
    ]
});

export default router;