import axios from 'axios'
import authHeader from './auth-header'

const user = JSON.parse(localStorage.getItem('user'))
const API = '/api/v1/collection/'

export async function getUserCollection() {
  return axios.get(API + '/user', { params: { id: user.id }, headers: authHeader() })
}

export async function getUserCollectionEntry(perfumeId) {
  return axios.get(API + '/entry', { params: { userId: user.id, perfumeId: perfumeId }, headers: authHeader() })
}

export async function saveUserCollectionEntry(perfumeId, collection) {
  return axios.post(API + '/entry', {
    params: {
      userId: user.id.toString(),
      perfumeId: perfumeId,
      type: collection.type,
      quantity: collection.quantity,
      note: collection.note,
    },
    headers: authHeader(),
  })
}

export async function getUserReviewEntry(perfumeId) {
  return axios.get(API + '/entry/review', { params: { userId: user.id, perfumeId: perfumeId }, headers: authHeader() })
}

export async function saveUserReviewEntry(perfumeId, review) {
  return axios.post(API + '/entry/review', {
    params: { userId: user.id, perfumeId: perfumeId, review: review },
    headers: authHeader(),
  })
}
