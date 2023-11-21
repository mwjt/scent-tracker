import axios from 'axios'
import authHeader from './auth-header'

const API = '/api/v1/gallery/'

export async function getPerfumeImage(id) {
  const promise = axios.get(API + '/perfume', {
    params: { perfumeId: id },
    headers: authHeader(),
    responseType: 'blob',
  })
  const data = promise.then((response) => response.data)
  return data
}