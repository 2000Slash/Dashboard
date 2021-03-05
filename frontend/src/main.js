import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import vuetify from './plugins/vuetify'
import routes from './plugins/router'

Vue.config.productionTip = false
Vue.use(VueRouter)

new Vue({
    vuetify,
    router: new VueRouter({mode: 'history', routes}),
    render: h => h(App),
}).$mount('#app')
