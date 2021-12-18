<template>
<div id="app">
    <header>
        <a href="/">
            <div class="logo">
                <i class="fas fa-paw">Adopt Pet</i>
            </div>
        </a>

        <hr>
        <nav>
            <div class="nav">

                <div :class="{active:page('/admin/pet') }"><router-link to="/admin/pet" class="nav-link">Pet</router-link></div>
                <div :class="{active:page('/admin/category') }"><router-link to="/admin/category" class="nav-link">Category</router-link></div>
                <div :class="{active:page('/admin/adopt-pet') }"><router-link to="/admin/adopt-pet" class="nav-link">Adopt Pet</router-link></div>
                <div :class="{active:page('/admin/user') }"><router-link to="/admin/user" class="nav-link">User</router-link></div>
                <div :class="{active:page('/admin/customer') }"><router-link to="/admin/customer" class="nav-link">Customer</router-link></div>

            </div>
        </nav>
        <hr>
        <footer>
            <div class="foot">
                <div class="address">
                    <h1>Address</h1>
                    <a href="">Quoc lo 23, london england</a>
                </div>
                <div class="phone">
                    <h1>Phone</h1>
                    <a href="#">090 090 0909</a>
                </div>
            </div>
        </footer>
    </header>
    <div class="tmp"></div>
    <div class="main container">
        <div class="head">
            <div class="image">
                <img width="50px" height="50px" src="https://th.bing.com/th/id/OIP.iYpFSu2O2kVP1OptEdJ-uwHaHx?pid=ImgDet&rs=1" alt="">
            </div>
            <h1>username</h1>
        </div>
        <hr>
        <div class="content">
           <router-view></router-view>
        </div>
    </div>
</div>
</template>

<script>
// import CategoryList from './pages/admin/category/CategoryList.vue'
// import CategoryDetail from './pages/admin/category/CategoryDetail.vue'

export default {
    name: 'App',
    data() {
        return {
            listCategory: {},
            baseUrl: 'http://127.0.0.1:8080',
        }
    },
    components: {
        // CategoryList,
        // CategoryDetail
    },
    methods: {
       page(string){
           let path = this.$route.path;
           if(path.includes(string)){
               return true;
           }
           return false;
       }
    },
    mounted: async function () {
        //alert(this.$route.params.id);
        const url = this.baseUrl + '/api/category';
        console.log('url=', url)
        let resp = await fetch(url);
        this.listCategory = await resp.json();
        console.log(this.listCategory);
        console.log("content-type "+resp.headers.get("Content-Type"));
        console.log("page "+resp.headers.get("page"));
    },
    computed:{
        
    }
}
</script>

<style>
.display {
    display: none;
}

* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

body {
    background-color: rgba(0, 0, 0, 0.1);

}

#app {
    display: flex;
    justify-content: space-around;
}

header {
    font-size: 2rem;
    /* border: 1px solid; */
    width: 25%;
    height: 100vh;
    background-color: rgba(177, 170, 170, 0.2);
    box-shadow: 1px 1px rgba(12, 12, 12, 0.3);
    overflow: scroll;
    position: fixed;
    left: 0;
    padding: 1px;
}

.tmp {

    width: 35%;
    height: 100vh;

}

header .logo {
    padding-left: 2rem;
    height: 8%;
}

header nav {
    /* border: 1px solid; */
    background-color: rgb(119, 124, 122);
    justify-content: space-around;
}

div.nav div {
    width: 100%;
    /* border: 1px solid; */
    padding: .5rem;

}

div.nav div:hover {
    background-color: black;
    color: #fff;
    transition: .5s ease;
    transform: scale(1, 1.2);
}

div.nav div .nav-link {
    text-decoration: none;
    color: rgb(236, 238, 243);

}

header footer {
    display: flex;
    width: 100%;
    font-size: 20px;
    flex-direction: row;
    justify-content: center;
}

header footer h1 {
    font-size: 20px;

}

.head {
    height: 8vh;
    display: flex;
    justify-content: flex-end;
    color: rgb(32, 65, 212);
}

.image img {
    border-radius: 9999999px;
    border: 2px solid;

}

div.active {
    background-color: rgb(41, 42, 128);
}
</style>
