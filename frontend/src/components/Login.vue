<script>
export default {
  name: 'LoginItem',
  data() {
    return {
      loading: false,
      form: false,
      login: null,
      password: null,
    }
  },
  computed: {
    loggedIn() {
      return this.$store.state.auth.status.loggedIn
    },
  },
  mounted() {
    if (this.loggedIn) {
      this.$router.push('/profile')
    }
  },
  methods: {
    onSubmit() {
      if (!this.form) return
      this.loading = true

      let user = {
        login: this.login,
        password: this.password,
      }
      this.$store.dispatch('auth/login', user).then(
        () => {
          this.loading = false
          this.$router.push('/profile')
          this.$store.dispatch('snackbar/display', 'Succesfully logged in')
        },
        (error) => {
          this.loading = false
          this.$store.dispatch(
            'snackbar/display',
            (error.response && error.response.data && error.response.data.message) || error.message || error.toString()
          )
        }
      )
    },
    required(v) {
      return !!v || 'Field is required'
    },
  },
}
</script>

<template>
  <v-container>
    <v-card class="mx-auto px-6 py-2" max-width="344" title="Login">
      <v-form v-model="form" @submit.prevent="onSubmit">
        <v-text-field
          v-model="login"
          :readonly="loading"
          :rules="[required]"
          class="mb-2"
          clearable
          label="Login"
        ></v-text-field>

        <v-text-field
          v-model="password"
          :readonly="loading"
          :rules="[required]"
          clearable
          label="Password"
          type="password"
        ></v-text-field>

        <v-btn :disabled="!form" :loading="loading" type="submit" block color="success">
          Sign In <v-icon icon="mdi-chevron-right" end></v-icon>
        </v-btn>
      </v-form>
      <div style="margin: 10px 0px">Don't have an account? <router-link to="/register"> Sign up! </router-link></div>
    </v-card>
  </v-container>
</template>
