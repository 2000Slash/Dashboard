<template>
  <v-app id="app">
    <Navbar/>
    <v-main>
      <v-container class="mt-5">
        <DockerOverview/>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>
import Navbar from './components/Navbar.vue';
import DockerOverview from './components/DockerOverview/mainPanel.vue'

export default {
  name: 'App',
  components: {
      Navbar, DockerOverview
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}
</style>
