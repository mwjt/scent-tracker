<script>
export default {
  data() {
    return {
      drawer: false
    }
  },
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
    snackbar() {
      return this.$store.state.snackbar.show;
    },
    snackbar_text() {
      return this.$store.state.snackbar.text;
    }
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
            <v-btn prepend-icon="mdi-account" v-if="currentUser" style="text-transform: unset">
              {{ currentUser.login }}
            </v-btn>
          </router-link>

          <v-btn prepend-icon="mdi-logout" v-if="currentUser" style="text-transform: unset" @click.prevent="logOut">
            Logout
          </v-btn>
        </template>
      </v-app-bar>

      <v-navigation-drawer v-model="drawer" temporary>
        <v-list>
          <router-link to="/collection" style="text-decoration: none; color: inherit" v-if="this.currentUser">
            <v-list-item> My collection </v-list-item>
          </router-link>
          <router-link to="/perfumes" style="text-decoration: none; color: inherit">
            <v-list-item> Perfumes </v-list-item>
          </router-link>
          <router-link to="/brands" style="text-decoration: none; color: inherit">
            <v-list-item> Brands </v-list-item>
          </router-link>
          <router-link to="/perfumers" style="text-decoration: none; color: inherit">
            <v-list-item> Perfumers </v-list-item>
          </router-link>
        </v-list>
      </v-navigation-drawer>

      <v-snackbar v-model="snackbar" multi-line elevation="24" color="blue-accent-4">
      {{ snackbar_text }}
      <template v-slot:actions>
        <v-btn variant="text" @click="this.$store.dispatch('snackbar/close')">
          Close
        </v-btn>
      </template>
    </v-snackbar>

      <v-main>
        <v-container fluid>
          <router-view />
        </v-container>
      </v-main>
    </v-app>
  </v-card>
</template>
