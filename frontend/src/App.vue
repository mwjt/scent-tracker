<script>
export default {
  data: () => ({
    drawer: false,
  }),
  computed: {
    currentUser() {
      return this.$store.state.auth.user
    },
    showAdminBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ADMIN')
      }

      return false
    },
    showModeratorBoard() {
      if (this.currentUser && this.currentUser['roles']) {
        return this.currentUser['roles'].includes('ROLE_MODERATOR')
      }

      return false
    },
  },
  methods: {
    logOut() {
      this.$store.dispatch('auth/logout')
      this.$router.push('/login')
    },
  },
  watch: {
    group() {
      this.drawer = false
    },
  },
}
</script>

<template>
  <v-card color="grey-lighten-4" flat rounded="0">
    <v-app>
      <v-app-bar color="primary" density="compact">
        <template v-slot:prepend>
          <v-app-bar-nav-icon variant="text" @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
        </template>

        <router-link to="/home" style="text-decoration: none; color: inherit">
          <v-app-bar-title>Scent Tracker</v-app-bar-title>
        </router-link>

        <v-spacer></v-spacer>

        <template v-slot:append>
          <router-link to="/login" style="text-decoration: none; color: inherit">
            <v-btn prepend-icon="mdi-login" class="text-capitalize" v-if="!currentUser"> Login </v-btn>
          </router-link>

          <router-link to="/profile" style="text-decoration: none; color: inherit">
            <v-btn icon="mdi-account" v-if="currentUser"> </v-btn>
          </router-link>

          <v-btn prepend-icon="mdi-logout" class="text-capitalize" v-if="currentUser" @click.prevent="logOut">
            Logout
          </v-btn>
        </template>
      </v-app-bar>

      <v-navigation-drawer v-model="drawer" temporary>
        <v-list>
          <router-link to="/perfumes" style="text-decoration: none; color: inherit">
            <v-list-item> Perfumes </v-list-item>
          </router-link>
        </v-list>
      </v-navigation-drawer>

      <v-main>
        <v-container fluid>
          <router-view />
        </v-container>
      </v-main>
    </v-app>
  </v-card>
</template>
