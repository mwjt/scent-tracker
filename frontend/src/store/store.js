import { createStore } from "vuex";
import { auth } from "./auth.module";
import { snackbar } from "./snackbar.module";

const store = createStore({
  modules: {
    auth,
    snackbar,
  },
});

export default store;