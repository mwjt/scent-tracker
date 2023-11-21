<script>
export default {
  name: 'RegisterItem',
  data() {
    return {
      login: null,
      email: null,
      password1: null,
      password2: null,
      loading: false,
      message: '',
      terms: false,
      alert: false,
      form: false,
      rules: {
        required: (value) => !!value || 'Field is required',
        email: (value) => {
          const pattern =
            /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
          return pattern.test(value) || 'Invalid e-mail'
        },
        password: (value) => {
          const pattern = /^^[a-zA-Z0-9]{6,}$/
          return pattern.test(value) || 'Password too simple (use at least 6 characters'
        },
        repeatPassword: (value) => {
          return value == this.password1 || 'Passwords must be identical'
        },
      },
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
      console.log('guwno')

      this.message = ''
      this.loading = true

      let user = {
        login: this.login,
        email: this.email,
        password: this.password1,
      }

      this.$store.dispatch('auth/register', user).then(
        (data) => {
          this.message = data.message
          this.loading = false
        },
        (error) => {
          this.message =
            (error.response && error.response.data && error.response.data.message) || error.message || error.toString()
          this.loading = false
        }
      )
    },
  },
}
</script>

<template>
  <v-container>
    <v-card class="mx-auto px-6 py-2" max-width="344" title="Sign-up">
      <v-form @submit.prevent="onSubmit" v-model="form" ref="formRef">
        <v-text-field
          v-model="login"
          :readonly="loading"
          :rules="[rules.required]"
          class="mb-2"
          clearable
          label="Login"
        ></v-text-field>

        <v-text-field
          v-model="email"
          :readonly="loading"
          :rules="[rules.required, rules.email]"
          class="mb-2"
          clearable
          label="E-mail"
        ></v-text-field>

        <v-text-field
          v-model="password1"
          :readonly="loading"
          :rules="[rules.required, rules.password]"
          clearable
          label="Password"
        ></v-text-field>

        <v-text-field
          v-model="password2"
          :readonly="loading"
          :rules="[rules.required, rules.repeatPassword]"
          clearable
          label="Repeat password"
        ></v-text-field>

        <v-checkbox
          v-model="terms"
          color="secondary"
          label="I agree to site terms and conditions"
          :rules="[rules.required]"
        ></v-checkbox>

        <v-btn :disabled="!form" :loading="loading" block type="submit" color="success">
          Complete Registration

          <v-icon icon="mdi-chevron-right" end></v-icon>
        </v-btn>
      </v-form>
      <v-alert v-model="alert" class="mt-2" type="error" :text="message" />
      <div style="margin: 10px 0px">Registered already? <router-link to="/login"> Sign in! </router-link></div>
    </v-card>
  </v-container>
</template>
