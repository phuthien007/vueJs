import Vue from 'vue';
import App from './App.vue';
import router from './routes/index.js';
import store from './stores/index.js';
import BootstrapVue from 'bootstrap-vue';

Vue.config.productionTip = false

Vue.use(BootstrapVue);
new Vue({
    render: h => h(App),
    router: router,
    store: store

}).$mount('#app')