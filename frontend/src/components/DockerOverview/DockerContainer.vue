<template>
    <v-card :loading="loading" class="mx-auto">
        <template slot="progress">
            <v-progress-linear
                color="deep-purple"
                height="10"
                indeterminate
            ></v-progress-linear>
        </template>
        <v-card-title>{{title}}</v-card-title>
        <v-card-subtitle>{{subtitle}}:<v-spacer/>{{state}}</v-card-subtitle>

        <v-card-actions>
            <v-btn
                elevation="1"
                fab
                dark
                small
                color="light-green"
                v-if="state=='exited' || state=='created'"
                @click="startContainer"
            >
                <v-icon dark>mdi-play</v-icon>
            </v-btn>

            <v-btn
                elevation="1"
                fab
                dark
                small
                color="red"
                v-if="state=='running'"
                @click="stopContainer"
            >
                <v-icon dark>mdi-stop</v-icon>
            </v-btn>

            <v-btn
                elevation="1"
                fab
                dark
                small
                color="orange"
                @click="restartContainer"
            >
                <v-icon dark>mdi-restart</v-icon>
            </v-btn>

            <v-spacer/>

            <v-btn
                elevation="1"
                fab
                small
                dark
                color="red"
                @click="deleteContainer"
            >
                <v-icon dark>mdi-delete</v-icon>
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
import axios from 'axios';

export default {
    name: "DockerContainer",
    props: {
        title: String,
        subtitle: String,
        id: String,
        state: String,
        index: Number
    },
    data: () => ({
        loading: false
    }),
    methods: {
        stopContainer () {
            this.loading = true;
            axios.post("http:/api/containers/" + this.id + "/stop")
            .then(response => {
                if(response.status == 204) {
                    this.$emit('statusUpdate', this.index, "exited")
                    this.loading = false
                }
            }, rejected => {
                this.loading = false
                this.$emit('error', rejected)
                console.log("Something went wrong: " + rejected)
            })
        },
        startContainer() {
            this.loading = true;
            axios.post("http:/api/containers/" + this.id + "/start")
                 .then(response => {
                if(response.status == 204) {
                    this.$emit('statusUpdate', this.index, "running")
                    this.loading = false
                }
            }, rejected => {
                this.loading = false
                this.$emit('error', rejected)
                console.log("Something went wrong: " + rejected)
                 })
        },
        restartContainer() {
            this.loading = true;
            axios.post("http:/api/containers/" + this.id + "/restart")
                 .then(response => {
                if(response.status == 204) {
                    this.$emit('statusUpdate', this.index, "running")
                    this.loading = false
                }
            }, rejected => {
                this.loading = false
                this.$emit('error', rejected)
                console.log("Something went wrong: " + rejected)
                 })
        },
        deleteContainer() {
            this.loading = true;
            axios.delete("http:/api/containers/" + this.id)
                 .then(response => {
                this.$emit('deleted', this.index)
                this.loading = false
            }, rejected => {
                this.loading = false
                this.$emit('error', rejected)
                console.log("Something went wrong: " + rejected)
                 })
        }

    },
}
</script>

<style scoped>
</style>
