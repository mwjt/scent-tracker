import axios from 'axios'
import authHeader from './auth-header'

const API = '/api/v1/gallery/'

export async function getImage(id) {
  return axios.get(API, {
    params: { id: id },
    headers: authHeader(),
    responseType: 'blob',
  })
}
