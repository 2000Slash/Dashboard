<template>
    <div align="center">
        <h2>{{title}}</h2>
        <v-progress-circular
            :size="100"
            :width="20"
            :value="percent"
            :color="color"
        >
            {{percent}}
        </v-progress-circular>
    </div>
</template>

<script>
 export default {
     name: "Gauge",
     props: {
         percent: Number,
         title: String,
         color: String
     }
 }
</script>
