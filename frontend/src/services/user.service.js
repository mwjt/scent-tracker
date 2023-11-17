import axios from 'axios'
import authHeader from './auth-header'

export async function getProfile(login) {
  const promise = axios.get('api/v1/user/' + login, { headers: authHeader() })
  const data = promise.then((response) => response.data)
  return data
}
