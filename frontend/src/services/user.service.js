import axios from 'axios'
import authHeader from './auth-header'

export async function getProfile(login) {
  return axios.get('api/v1/user/' + login, { headers: authHeader() })
}
