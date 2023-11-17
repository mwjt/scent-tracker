<script>
import { ref } from 'vue'
import { getProfile } from '../services/user.service'

export default {
  name: 'ProfileItem',
  data() {
    return {
      login: '',
      email: '',
      avatarPath: '',
    }
  },
  computed: {
    currentUser() {
      return this.$store.state.auth.user
    },
  },
  async beforeMount() {
    this.$loading = ref(true)
    console.log(this.currentUser)
    let res = await getProfile(this.currentUser.login)
    console.log(res)
    this.login = res.login
    this.email = res.email
    this.avatarPath = res.avatarPath
    this.$loading = ref(false)
  },
}
</script>

<template>
  <v-col cols="auto">
    <v-sheet elevation="10" class="py-4 px-1">
      <v-chip-group mandatory selected-class="text-primary">
        <v-chip> {{ login }} </v-chip>
        <v-chip>{{ email }}</v-chip>
      </v-chip-group>
    </v-sheet>
  </v-col>
</template>
