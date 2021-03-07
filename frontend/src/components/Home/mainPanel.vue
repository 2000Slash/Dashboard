<template>
    <div>
        <h2 class="mb-5" align="center">Dashboard</h2>
        <v-row align="center" justify="center" :cols="6">
            <v-col class="mx-auto">
                <Gauge title="CPU" :percent="percentCpu" />
            </v-col>
            <v-col>
                <Gauge title="Ram" :percent="percentMem" />
            </v-col>
        </v-row>
        <PidList @autoUpdate="autoUpdate" :processes="processes" class="mt-10" />
    </div>
</template>

<script>
 import Gauge from './Gauge.vue'
 import axios from 'axios'
 import PidList from './PidList.vue'

 export default {
     name: "HomePage",
     components: {
         Gauge, PidList
     },
     data: function() {
         return {
             percentCpu: 0,
             percentMem: 0,
             processes: [],
             timer: ''
         }
     },
     created () {
         this.fetchCpu();
         this.fetchMem();
         this.fetchProcesses();
     },
     beforeDestroy() {
       clearInterval(this.timer)
     },
     methods: {
         autoUpdate() {
             if (this.timer == '') {
                 this.enableUpdate();
             } else {
                 clearInterval(this.timer)
                 this.timer = ''
             }
         },
         fetchCpu() {
             axios.get("http:/api/info/cpu")
                  .then(response => this.percentCpu = Math.round(response.data*100));
         },
         fetchMem() {
             axios.get("http:/api/info/memory")
                  .then(response => {
                      this.percentMem = Math.round((response.data.total - response.data.available) / response.data.total * 100);
                  })
         },
         fetchProcesses() {
             axios.get("http:/api/info/processes")
                  .then(response => this.processes = response.data)
         },
         enableUpdate() {
             var cpu = this.fetchCpu;
             var mem = this.fetchMem;
             var processes = this.fetchProcesses;
             this.timer = setInterval(function() {
                 cpu();
                 mem();
                 processes();
             }, 1000);
         }
     }
 }
</script>
