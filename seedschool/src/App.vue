<template>
<!-- App.vue -->

<v-app>

    <v-navigation-drawer app>
        <!-- -->
        <side-bar />
    </v-navigation-drawer>

    <v-app-bar app elevation="3">
        <!-- -->
        <h1>Trường mầm non Seedschool</h1>

        <v-spacer></v-spacer>
        <div>

            <v-btn icon>
                <v-badge :content="messages" :value="messages" color="red" overlap>
                    <v-menu bottom min-width="200px" rounded offset-y>
                        <template v-slot:activator="{ on }">
                            <v-btn icon x-small v-on="on">
                                <v-avatar color="gray" size="30">
                                    <v-icon>mdi-bell</v-icon>
                                </v-avatar>
                            </v-btn>
                        </template>
                        <v-card>
                            <v-list-item-content class="justify-center">
                                <div class="mx-auto text-center">
                                    <v-avatar color="gray">
                                        <v-icon>mdi-bell</v-icon>
                                    </v-avatar>
                                    <h3>{{ user.fullName }}</h3>
                                    <p class="text-caption mt-1">
                                        {{ user.email }}
                                    </p>
                                    <v-divider class="my-3"></v-divider>
                                    <v-btn depressed rounded text>
                                        View Account
                                    </v-btn>
                                    <v-divider class="my-3"></v-divider>
                                    <v-btn depressed rounded text>
                                        Logout
                                    </v-btn>
                                </div>
                            </v-list-item-content>
                        </v-card>
                    </v-menu>

                </v-badge>
            </v-btn>

            <!-- avatar admin/ -->
            <v-btn icon>
                <v-menu bottom min-width="200px" rounded offset-y>
                    <template v-slot:activator="{ on }">
                        <v-btn icon x-large v-on="on">
                            <v-avatar color="brown" size="40">
                                <span class="white--text text-h5">{{ user.initials }}</span>
                            </v-avatar>
                        </v-btn>
                    </template>
                    <v-card>
                        <v-list-item-content class="justify-center">
                            <div class="mx-auto text-center">
                                <v-avatar color="brown">
                                    <span class="white--text text-h5">{{ user.initials }}</span>
                                </v-avatar>
                                <h3>{{ user.fullName }}</h3>
                                <p class="text-caption mt-1">
                                    {{ user.email }}
                                </p>
                                <v-divider class="my-3"></v-divider>
                                <v-btn depressed rounded text>
                                    View Account
                                </v-btn>
                                <v-divider class="my-3"></v-divider>
                                <v-btn depressed rounded text>
                                    Logout
                                </v-btn>
                            </div>
                        </v-list-item-content>
                    </v-card>
                </v-menu>

            </v-btn>

        </div>
    </v-app-bar>

    <!-- Sizes your content based upon application components -->
    <v-main>

        <!-- Provides the application the proper gutter -->
        <v-container fluid>

            <!-- If using vue-router -->
            <router-view></router-view>
        </v-container>
    </v-main>

    <v-footer app>
        <!-- -->
    </v-footer>
</v-app>
</template>

<script>
import HelloWorld from './components/HelloWorld';
import SideBar from './components/SideBar.vue';
import {
    HTTP
} from './api/index.js';
export default {
    name: 'App',

    components: {
        HelloWorld,
        SideBar,
    },

    data: () => ({
        //
        menu: [{
                header: true,
                title: 'Main Navigation',
                hiddenOnCollapse: true
            },
            {
                href: '/',
                title: 'Dashboard',
                icon: 'fa fa-user'
            },
            {
                href: '/charts',
                title: 'Charts',
                icon: 'fa fa-chart-area',
                child: [{
                    href: '/charts/sublink',
                    title: 'Sub Link'
                }]
            }
        ],
        messages: 3,
        user: {
            initials: 'JD',
            fullName: 'John Doe',
            email: 'john.doe@doe.com',
        },
    }),
    methods: {
        loginFunction: async function () {
            let url = 'login';

        }
    }
};
</script>

<style scoped>
</style>
