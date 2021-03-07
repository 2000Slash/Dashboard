<template>
    <div align="center">
        <p class="text-h6">{{title}}</p>
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
     },
     computed: {
         color: function() {
             return this.rgbToHex(Math.round(this.percent / 100.0 * 255), Math.round(255 - this.percent / 100.0 * 255), 0)
         }
     },
     methods: {
         rgbToHex(r, g, b) {
             return "#" + this.componentToHex(r) + this.componentToHex(g) + this.componentToHex(b);
         },
         componentToHex(c) {
             var hex = c.toString(16);
             return hex.length == 1 ? "0" + hex : hex;
         }
     }
 }
</script>
