<template>
    <div >
        <h2 align="center">Simple Vue Dashboard</h2>
        <v-row class="mt-5" align="center" justify="center" :cols="3">
            <v-col lg="2" class="mx-auto">
                <Gauge title="CPU" color="red" :percent="30" />
            </v-col>
            <v-col>
                <Gauge title="Memory" color="green" :percent="50" />
            </v-col>
            <v-col lg="2">
                <Gauge title="Temperature" color="green" :percent="10" />
            </v-col>
        </v-row>
    </div>
</template>


<script>
import Gauge from './Gauge.vue'
 export default {
     name: "HomePage",
     components: {
         Gauge
     }
 }
</script>
