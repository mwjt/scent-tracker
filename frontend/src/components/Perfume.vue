<script>
import { getPerfumeByBrandAndName } from '../services/perfume.service'

export default {
  name: 'PerfumeItem',
  data() {
    return {
      brand: '',
      name: '',
    }
  },
  async beforeMount() {
    this.$loading = true
    let res = await getPerfumeByBrandAndName(this.$route.params.brand, this.$route.params.name)
    this.brand = res.brand
    this.name = res.name
    this.$loading = false
  },
}
</script>

<template>
  <v-col cols="auto">
    <v-sheet elevation="10" class="py-4 px-1">
      <v-chip-group mandatory selected-class="text-primary">
        <v-chip> {{ brand }} </v-chip>
        <v-chip>{{ name }}</v-chip>
      </v-chip-group>
    </v-sheet>
  </v-col>
</template>
