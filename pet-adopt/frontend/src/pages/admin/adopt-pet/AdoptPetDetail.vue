<template>
<div class="detail-adopt-pet">
    <nav>
        <h1>Adopt Pet Detail</h1>
        <button class="btn btn-primary" @click="handleEdit" :disabled="edit === '1'"><i class="fas fa-pen">Edit</i></button>
    </nav>
    <form action="" @submit.prevent="">
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="petId" placeholder="PetId" v-model="this.adoptPet.petId" :disabled="edit==='0'">
            <label for="petId">Pet Id</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="customerId" placeholder="CustomerId" v-model="this.adoptPet.customerId" :disabled="edit==='0'">
            <label for="customerId">Customer Id</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="adoptDate" placeholder="AdoptDate" v-model="this.adoptPet.adoptDate" :disabled="edit==='0'">
            <label for="adoptDate">Adopt Date</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="description" placeholder="Description" v-model="this.adoptPet.description" :disabled="edit==='0'">
            <label for="description">Description</label>
        </div>
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="status" placeholder="Status" v-model="this.adoptPet.status" :disabled="edit==='0'">
            <label for="status">Status</label>
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
    name: 'detail-adopt-pet',

    data() {
        return {
            adoptPet: {

            },
            edit: '0'
        }
    },
    mounted: async function () {
        let url = `http://localhost:8080/api/adoptPet/` + this.$route.params.id;
        let resp = await fetch(url);
        this.adoptPet = await resp.json();
        
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
.detail-adopt-pet input {
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
