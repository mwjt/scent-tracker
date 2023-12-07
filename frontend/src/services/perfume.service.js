import axios from 'axios'
import authHeader from './auth-header'

const API = '/api/v1/perfume/'

export async function getPerfume(id) {
  return axios.get(API + '/id', { params: { id: id }, headers: authHeader() })
}


export async function getPage(page) {
  return axios.get(API + '/all', { params: { pageNo: page - 1 }, headers: authHeader() })
}


export async function getPerfumeByBrandAndName(brand, name) {
  return axios.get(API + '/brandAndName', { params: { brand: brand, name: name }, headers: authHeader() })
}
