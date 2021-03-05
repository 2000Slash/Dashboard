import Vue from 'vue'
import VueRouter from 'vue-router'
import DockerOverview from '../components/DockerOverview/mainPanel.vue'
import Home from '../components/Home/mainPanel.vue'

Vue.use(VueRouter);

const routes = [
    { path: '/', component: Home },
    { path: '/docker', component: DockerOverview}
]

export default routes;
