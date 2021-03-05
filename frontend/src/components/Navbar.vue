<template>
    <div>
        <v-app-bar color="indigo" dark fixed app>
            <v-app-bar-nav-icon><v-icon dark>mdi-cube-outline</v-icon></v-app-bar-nav-icon>
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
    </div>
</template>

<script>
export default {
    name: 'Navbar',
}
</script>

<style scoped>
</style>
