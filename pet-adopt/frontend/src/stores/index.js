import Vue from "vue"
import Vuex from "vuex"


Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        Id: 0
    },
    mutations: {
        setId(state, ID) {
            state.Id = ID;
            console.log(state.Id + " ID: " + ID);

        }
    }
});
export default store;