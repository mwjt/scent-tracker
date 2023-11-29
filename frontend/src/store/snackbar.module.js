const initialState = {
  show: false,
  text: '',
}

export const snackbar = {
  namespaced: true,
  state: initialState,
  actions: {
    display({ commit }, message) {
      commit('display', message);
    },
    close({ commit }) {
      commit('close');
    }
  },
  mutations: {
    async display(state, message) {
      state.show = true
      state.text = message
      await new Promise(res => setTimeout(res, 5000));
      state.show = false
      state.text = ''
    },
    close(state) {
      state.show = false,
      state.text = ''
    }
  }
};