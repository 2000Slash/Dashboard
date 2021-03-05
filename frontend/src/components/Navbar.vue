<template>
    <div>
        <v-app-bar color="primary" dark fixed app>
            <v-app-bar-nav-icon @click="drawer = true"><v-icon dark>mdi-cube-outline</v-icon></v-app-bar-nav-icon>
            <v-toolbar-title style="margin-left: -15px;">Server Dashboard</v-toolbar-title>
            <v-toolbar-items class="ml-10 hidden-xs-only">
                <v-btn to="/" text>
                    <v-icon left dark>mdi-home</v-icon>
                    Home
                </v-btn>
            </v-toolbar-items>
            <v-toolbar-items class="hidden-xs-only">
                <v-btn to="/docker" text>
                    <v-icon left dark>mdi-docker</v-icon>
                    Docker
                </v-btn>
            </v-toolbar-items>
            <v-spacer></v-spacer>
            <v-btn class="mx-1" elevation="1" fab dark small color="red"><v-icon dark>mdi-power</v-icon></v-btn>
            <v-btn class="mx-1" elevation="1" fab dark small color="warning"><v-icon dark>mdi-restart</v-icon></v-btn>

            
        </v-app-bar>
        <v-navigation-drawer
            v-model="drawer"
            temporary
            absolute
        >
            <v-list-item>
                <v-list-item-content>
                    <v-list-item-title class="title">
                        Dashboard
                    </v-list-item-title>
                </v-list-item-content>
            </v-list-item>
            <v-divider></v-divider>
            <v-list
                nav
                dense
            >
                <v-list-item-group
                    active-class="primary--text"
                >
                    <v-list-item to="/">
                        <v-list-item-icon>
                            <v-icon>mdi-home</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Home</v-list-item-title>
                    </v-list-item>

                    <v-list-item to="/docker">
                        <v-list-item-icon>
                            <v-icon>mdi-docker</v-icon>
                        </v-list-item-icon>
                        <v-list-item-title>Docker</v-list-item-title>
                    </v-list-item>
                </v-list-item-group>
            </v-list>
        </v-navigation-drawer>
    </div>
</template>

<script>
export default {
     name: 'Navbar',
     data: () => ({
         drawer: false,
         group: null
     })
}
</script>

<style scoped>
</style>
