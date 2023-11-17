import axios from 'axios'
import authHeader from './auth-header'

const API = '/api/v1/perfume/'

export async function getPage(page) {
  const promise = axios.get(API + '/all', { params: { pageNo: page - 1 }, headers: authHeader() })
  const data = promise.then((response) => response.data)
  return data
}

export async function getPerfume(id) {
  const promise = axios.get(API + '/id', { params: { id: id }, headers: authHeader() })
  const data = promise.then((response) => response.data)
  return data
}

export async function getPerfumeByBrandAndName(brand, name) {
  const promise = axios.get(API + '/brandAndName', { params: { brand: brand, name: name }, headers: authHeader() })
  const data = promise.then((response) => response.data)
  return data
}
