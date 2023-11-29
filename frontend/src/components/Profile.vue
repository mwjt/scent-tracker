<script>
import { ref } from 'vue'
import { getProfile } from '../services/user.service'
import { getImage } from '../services/gallery.service'

export default {
  name: 'ProfileItem',
  data() {
    return {
      user: '',
      url: ''
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
    console.log(this.user)
    this.$loading = ref(false)
    res = await getImage(this.user.galleryId)
    this.url = URL.createObjectURL(res)
    console.log(this.url)
  },
}
</script>

<template>
  <v-container>
    <v-row>
      <v-col>
        <v-card class="pa-1 mr-1" height="100%" width="200px">
          <v-img aspect-ratio="1/1" :src="url"></v-img>
        </v-card>
      </v-col>
      <v-col>
        <v-card class="ma-2 pa-2 entries">
          <div class="top">Username: {{ this.user.login }}</div>
          <div class="mid">E-mail: {{ this.user.email }}</div>
          <div class="bot">Role: {{ this.user.role }}</div>
        </v-card>
      </v-col>
    </v-row>
  </v-container>
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
