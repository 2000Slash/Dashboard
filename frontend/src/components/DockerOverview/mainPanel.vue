<template>
    <div>
        <v-snackbar
      v-model="snackbar"
    >
      {{error}}

      <template v-slot:action="{ attrs }">
          <v-btn
              color="pink"
              text
              v-bind="attrs"
              @click="snackbar = false"
          >
              Close
          </v-btn>
      </template>
        </v-snackbar>
        <v-row align="center" justify="center">
            <v-col :cols="cols" v-for="(c, index) in containers" :key="c.Id">
                <docker-container @error="showError" @deleted="deleted" @statusUpdate="changeStatus" :id="c.Id" :state="c.State" :index="index" :title="c.Names[0]" :subtitle="c.Image"/>
            </v-col>
        </v-row>
        <div v-if="containers != null && containers.length > 0" align="center">
            <v-btn color="red" @click="deleteStopped" dark large class="mt-5">Delete all Stopped</v-btn>
        </div>
    </div>
</template>

<script>
import DockerContainer from './DockerContainer.vue'
import axios from 'axios'

export default {
    name: "DockerOverview",
    components: {
        DockerContainer
    },
    computed: {
        cols() {
            switch (this.$vuetify.breakpoint.name) {
                case 'xs': return 12
                case 'sm': return 12
                case 'md': return 12
                case 'lg': return 3
                case 'xl': return 3
            }
        }
    },
    data: function() {
        return {
            containers: null,
            snackbar: false,
            error: ""
        }
    },
    methods: {
        changeStatus (index, status) {
            this.containers[index].State = status
        },
        deleted (index) {
            this.containers.splice(index, 1)
        },
        deleteStopped() {
            axios.post("http:/api/containers/prune")
                 .then(result => {
                this.fetchData();
            })
        },
        fetchData() {
            axios.get("http:/api/containers/json", {params: {all: true}})
                 .then(result => {
                    console.log(result)
                    this.containers = result.data
            });
        },
        showError(errorText) {
            this.error = errorText
            this.snackbar = true;

        }
    },
    created: function() {
        this.fetchData()
    }

}
</script>
