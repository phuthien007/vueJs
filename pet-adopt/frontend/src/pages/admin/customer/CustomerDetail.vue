<template>
<div class="detail-customer">
    <nav>
        <h1>Customer Detail</h1>
        <button class="btn btn-primary" @click="handleEdit" :disabled="edit === '1'"><i class="fas fa-pen">Edit</i></button>
    </nav>
    <form action="" @submit.prevent="">
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="name" placeholder="Name" v-model="this.customer.name" :disabled="edit==='0'">
            <label for="name">Name</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="code" placeholder="Phone" v-model="this.customer.code" :disabled="edit==='0'">
            <label for="code">Phone</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="code" placeholder="Address" v-model="this.customer.address" :disabled="edit==='0'">
            <label for="code">Address</label>
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
    name: 'detail-customer',

    data() {
        return {
            customer: {

            },
            edit: '0'
        }
    },
    mounted: async function () {
        let url = `http://localhost:8080/api/customer/` + this.$route.params.id;
        let resp = await fetch(url);
        this.customer = await resp.json();
       
    },
    computed: {
        

    },
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
.detail-customer input {
    font-size: 2rem;
    outline: none;
    border-bottom: 3px solid;
}

nav {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
}

.action{
    display: flex;
    padding: 2rem;
    justify-content: center;
    
}
.custom-btn{
    padding: 1rem;
    margin-left: 10px;
}
</style>
