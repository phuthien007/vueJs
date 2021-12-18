<template>
<div class="detail-user">
    <nav>
        <h1>User Detail</h1>
        <button class="btn btn-primary" @click="handleEdit" :disabled="edit === '1'"><i class="fas fa-pen">Edit</i></button>
    </nav>
    <form action="" @submit.prevent="">
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="name" placeholder="Username" v-model="this.user.username" :disabled="edit==='0'">
            <label for="name">Username</label>
        </div>
        <div class="form-floating">
            <input type="email" class="form-control" id="code" placeholder="Email" v-model="this.user.email" :disabled="edit==='0'">
            <label for="code">Email</label>
        </div>
        <div class="form-floating">
            <input type="datetime" class="form-control" id="code" placeholder="Register Date" v-model="this.user.registerDate" :disabled="edit==='0'">
            <label for="code">Register Date</label>
        </div>
        <div class="action">
            <button class="btn btn-warning custom-btn" @click="update" :disabled=" edit === '0' "><i class="fas fa-edit">Update</i></button>
            <button class="btn btn-danger custom-btn"><i class="fas fa-trash">Delete</i></button>
        </div>

    </form>

</div>
</template>

<script>
export default {
    name: 'detail-category',

    data() {
        return {
            user: {

            },
            edit: '0'
        }
    },
    mounted: async function () {
        let url = `http://localhost:8080/api/user/` + this.$route.params.id;
        let resp = await fetch(url);
        this.user = await resp.json();
    },
    computed: {},
    methods: {
        handleEdit() {
            this.edit === '0' ? this.edit = '1' : this.edit = '0';
        },
        update() {
            this.edit === '0' ? this.edit = '1' : this.edit = '0';
        }
    }
}
</script>

<style scoped>
.detail-user input {
    font-size: 2rem;
    outline: none;
    border-bottom: 3px solid;
}

nav {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.action {
    display: flex;
    padding: 2rem;
    justify-content: center;

}

.custom-btn {
    padding: 1rem;
    margin-left: 10px;
}
</style>
