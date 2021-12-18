<template>
<div class="user-list">
    <nav>
        <h1>User</h1>
        <button class="btn btn-primary"><i class="fas fa-plus">New</i></button>
    </nav>

    <!-- paging -->
    <b-pagination v-if="paging.totalElement > 10" v-model="page" :total-rows="paging.totalElement" :per-page="10"></b-pagination>
    <!-- end paging  -->

    <table class="table table-dark table-hover table-bordered border-light">
        <thead>
            <tr>
                <th>STT</th>
                <th>Username</th>
                <th>Email</th>
                <th>Register Date</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(item, index) in UserList" :key="index">
                <td>{{index+1 + (page-1) * 10}}</td>
                <td>{{item.username}}</td>
                <td>{{item.email}}</td>
                <td>{{item.registerDate}}</td>
                <td>

                    <button class="btn btn-success">
                        <router-link class="btn-link" :to="`/admin/user/${item.id}`"><i class="far fa-eye">View</i></router-link>
                    </button>
                    <button class="btn btn-warning">
                        <router-link class="btn-link" :to="`/admin/user/${item.id}`"> <i class="fas fa-pen">Edit</i></router-link>
                    </button>
                    <button class="btn btn-danger">
                        <router-link class="btn-link" :to="`/admin/user/${item.id}`"> <i class="fas fa-trash">Delete</i></router-link>
                    </button>
                </td>
            </tr>
        </tbody>
    </table>
    <!-- <router-view></router-view> -->
</div>
</template>

<script>
import axios from 'axios'
export default {
    name: 'User-list',
    data() {
        return {
            UserList: {},
            paging: {
                totalElement: 0,
                totalPages: 0,
                numberOfElement: 0
            },
            page: 1
        }
    },
    methods: {
        async getData() {
            let resp = await axios.get('http://localhost:8080/api/user?page=' + (this.page - 1));
            this.UserList = await resp.data;
            this.paging.totalElement = (resp.headers.totalelements);
            this.paging.totalPages = (resp.headers.totalpages);
            this.paging.numberOfElement = (resp.headers.numberofelements);
        }
    },
    mounted: async function () {
        this.getData();
    },
    computed: {},
    watch: {
        page: function (newVal, oldVal) {
            if (newVal != oldVal) {
                this.getData();
            }
        }
    }
}
</script>

<style scoped>
nav {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.btn-link {
    color: aliceblue
}
</style>
