<script>
import { ref } from 'vue'
import { getPage } from '../services/perfume.service'
import { getImage } from '../services/gallery.service'
import { handleError } from 'vue'

export default {
  name: 'PerfumesItem',
  data() {
    return {
      page: 1,
      items: [],
      length: 1,
      perPage: 10,
    }
  },
  async beforeMount() {
    this.$loading = ref(true)
    await getPage(1).then(
      (response) => {
        this.items = []
        for (const p of response.data.perfumes)
          this.items.push({
            title: p.brand + ' ' + p.name,
            props: {
              to: '/perfumes/' + p.brand.replace(' ', '_') + '/' + p.name.replace(' ', '_'),
            },
          })
        this.length = response.data.total > 0 ? Math.ceil(response.data.total / this.perPage) : 1
        this.page = this.page > this.length ? this.length : this.page
      },
      (error) => {
        this.$store.dispatch(
          'snackbar/display',
          (error.response && error.response.data && error.response.data.message) || error.message || error.toString()
        )
      }
    )
  },
  watch: {
    async page(val) {
      this.$loading = true
      await getPage(val).then(
        (response) => {
          this.items = []
          for (const p of response.data.perfumes)
            this.items.push({
              title: p.brand + ' ' + p.name,
              props: {
                to: '/perfumes/' + p.brand.replace(' ', '_') + '/' + p.name.replace(' ', '_'),
              },
            })
          this.length = response.data.total > 0 ? Math.ceil(response.data.total / this.perPage) : 1
          this.page = this.page > this.length ? this.length : this.page
        },
        (error) => {
          this.$store.dispatch(
            'snackbar/display',
            (error.response && error.response.data && error.response.data.message) || error.message || error.toString()
          )
        }
      )
    },
  },
}
</script>

<template>
  <v-card class="mx-auto" max-width="300">
    <v-list :items="items"></v-list>
  </v-card>
  <div class="text-center">
    <v-container>
      <v-row justify="center">
        <v-col cols="8">
          <v-container class="max-width">
            <v-pagination v-model="page" class="my-4" :length="length"></v-pagination>
          </v-container>
        </v-col>
      </v-row>
    </v-container>
  </div>
</template>
