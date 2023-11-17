import axios from 'axios'

class AuthService {
  login(user) {
    return axios
      .post('/api/v1/auth/login', {
        login: user.login,
        password: user.password,
      })
      .then((response) => {
        if (response.data.token) {
          localStorage.setItem('user', JSON.stringify(response.data))
        }
        return response.data
      })
  }

  logout() {
    localStorage.removeItem('user')
  }

  register(user) {
    return axios.post('/api/v1/auth/signup', {
      login: user.login,
      email: user.email,
      password: user.password,
    })
  }
}

export default new AuthService()
