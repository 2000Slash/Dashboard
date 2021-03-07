<template>
    <div>
        <v-data-table
            :headers="headers"
            :items="processes"
            :items-per-page="10"
            dense
            class="elevation-1">
            <template v-slot:top>
                <v-toolbar flat>
                    <v-toolbar-title>Processes</v-toolbar-title>
                    <v-divider
                        class="mx-4"
                        inset
                        vertical
                    ></v-divider>
                    <v-spacer></v-spacer>
                    <v-btn v-if="!autoUpdateRunning" @click="autoUpdate" dark color="green"><v-icon left dark>mdi-play</v-icon>AutoUpdate</v-btn>
                    <v-btn v-else @click="autoUpdate" dark color="red"><v-icon left dark>mdi-stop</v-icon>AutoUpdate</v-btn>
                </v-toolbar>
            </template>
        </v-data-table>
    </div>
</template>

<script>
 export default {
     name: "PidList",
     props: {
         processes: Array
     },
     methods: {
         autoUpdate() {
             this.$emit('autoUpdate');
             this.autoUpdateRunning = !this.autoUpdateRunning;
         }
     },
     data() {
         return {
             autoUpdateRunning: false,
             headers: [
                 {
                     text: 'pid',
                     align: 'start',
                     sortable: true,
                     value: 'pid',
                 },
                 { text: 'Name', value: 'name' },
                 { text: 'User', value: 'user' },
                 { text: 'Memory', value: 'physicalMemory' },
                 { text: 'Cpu', value: 'cpuUsage'},
                 { text: 'Start time', value: 'startTime' },
             ]
         }
     }
 }
</script>
