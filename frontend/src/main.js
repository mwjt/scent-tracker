import { ref, createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store/store'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import { FontAwesomeIcon } from './plugins/font-awesome'

import 'vuetify/styles'
import { createVuetify } from 'vuetify'
import * as components from 'vuetify/components'
import * as directives from 'vuetify/directives'
import '@mdi/font/css/materialdesignicons.css'

const vuetify = createVuetify({
  components,
  directives,
  icons: {
    defaultSet: 'mdi',
  },
})

const app = createApp(App)
app.use(router)
app.use(store)
app.use(vuetify)
app.component('font-awesome-icon', FontAwesomeIcon)
app.config.globalProperties.$loading = ref(false)
app.mount('#app')
