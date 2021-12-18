<template>
<div class="detail-pet">
    <nav>
        <h1>Pet Detail</h1>
        <button class="btn btn-primary" @click="handleEdit" :disabled="edit === '1'"><i class="fas fa-pen">Edit</i></button>
    </nav>
    <form action="" @submit.prevent="">
        <div class="form-floating mb-3">
            <input type="text" class="form-control" id="name" placeholder="name" v-model="this.pet.name" :disabled="edit==='0'">
            <label for="name">Name</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="code" placeholder="code" v-model="this.pet.code" :disabled="edit==='0'">
            <label for="code">Code</label>
        </div>
        <div class="form-floating mb-3">
            <img id="name" :src="this.pet.image" height="480px" width="100%">
            <label for="name">Image</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" id="code" placeholder="code" v-model="this.pet.description" :disabled="edit==='0'">
            <label for="code">Description</label>
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
    name: 'detail-pet',

    data() {
        return {
            pet: {

            },
            edit: '0'
        }
    },
    mounted: async function () {
        let url = `http://localhost:8080/api/pet/` + this.$route.params.id;
        console.log(url );
        let resp = await fetch(url);
        this.pet = await resp.json();
    },
    computed: {
      

    },
    methods: {
        handleEdit() {
            this.edit === '0' ? this.edit = '1' : this.edit = '0';
        },
        update() {
            this.edit === '0' ? this.edit = '1' : this.edit = '0';
        },

    }

}
</script>

<style scoped>
.detail-pet input {
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
