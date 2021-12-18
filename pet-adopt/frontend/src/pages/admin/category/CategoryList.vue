<template>
<div class="category-list">
    <nav>
        <h1>Category</h1>
        <button class="btn btn-primary"><i class="fas fa-plus">New</i></button>
    </nav>
    <!-- paging -->
    <b-pagination v-if="paging.totalElement > 10" v-model="page" :total-rows="paging.totalElement" :per-page="10"></b-pagination>
    <!-- end paging  -->
    <table class="table table-dark table-hover table-bordered border-light">
        <thead>
            <tr>
                <th>STT</th>
                <th>Name</th>
                <th>Code</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="(item, index) in categoryList" :key="index">
                <td>{{index+1 + (page-1) * 10}}</td>
                <td>{{item.name}}</td>
                <td>{{item.code}}</td>
                <td>

                    <button class="btn btn-success">
                        <router-link class="btn-link" :to="`/admin/category/${item.id}`"><i class="far fa-eye">View</i></router-link>
                    </button>
                    <button class="btn btn-warning">
                        <router-link class="btn-link" :to="`/admin/category/${item.id}`"> <i class="fas fa-pen">Edit</i></router-link>
                    </button>
                    <button class="btn btn-danger">
                        <router-link class="btn-link" :to="`/admin/category/${item.id}`"> <i class="fas fa-trash">Delete</i></router-link>
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
    name: 'category-list',
    data() {
        return {
            categoryList: {},
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
            let resp = await axios.get('http://localhost:8080/api/category?page=' + (this.page - 1));
            this.categoryList = await resp.data;
            this.paging.totalElement = (resp.headers.totalelements);
            this.paging.totalPages = (resp.headers.totalpages);
            this.paging.numberOfElement = (resp.headers.numberofelements);
        }
    },
    mounted: async function () {
        this.getData();
    },
    computed: {

    },
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
