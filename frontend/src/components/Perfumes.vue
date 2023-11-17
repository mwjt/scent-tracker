<script>
import { ref } from 'vue'
import { getPage } from '../services/perfume.service'

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
    let res = await getPage(1)
    this.items = []
    for (const p of res.perfumes)
      this.items.push({
        title: p.brand + ' ' + p.name,
        props: {
          to: '/perfumes/' + p.brand.replace(' ', '_') + '/' + p.name.replace(' ', '_'),
        },
      })
    this.length = res.total > 0 ? Math.ceil(res.total / this.perPage) : 1
    this.page = this.page > this.length ? this.length : this.page
    this.$loading = ref(false)
  },
  watch: {
    async page(val) {
      this.$loading = true
      let res = await getPage(val)
      this.items = []
      for (const p of res.perfumes)
        this.items.push({
          title: p.brand + ' ' + p.name,
          props: {
            to: '/perfumes/' + p.brand.replace(' ', '_') + '/' + p.name.replace(' ', '_'),
          },
        })
      this.length = res.total > 0 ? Math.ceil(res.total / this.perPage) : 1
      this.page = this.page > this.length ? this.length : this.page
      this.$loading = false
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
