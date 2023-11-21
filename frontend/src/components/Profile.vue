<script>
import { ref } from 'vue'
import { getProfile } from '../services/user.service'

export default {
  name: 'ProfileItem',
  data() {
    return {
      user: '',
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
    this.user = res
    this.$loading = ref(false)
  },
}
</script>

<template>
  <v-container>
    <v-row>
      <v-col>
        <v-card class="ma-2 pa-2 entries">
          <div class="top">Username: {{ this.user.login }}</div>
          <div class="mid">E-mail: {{ this.user.email }}</div>
          <div class="bot">Role: {{ this.user.role }}</div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>

  <v-col cols="auto">
    <v-sheet elevation="10" class="py-4 px-1">
      <v-chip-group mandatory selected-class="text-primary">
        <v-chip> {{ login }} </v-chip>
        <v-chip>{{ email }}</v-chip>
      </v-chip-group>
    </v-sheet>
  </v-col>
</template>

<style lang="scss">
.entries {
  font-size: larger;
  .top {
    margin: 0 5px 5px 5px;
  }
  .mid {
    margin: 5px;
  }
  .bot {
    margin: 5px 5px 0px 5px;
  }
}
</style>
