import { createRouter, createWebHistory } from 'vue-router'
import HomeItem from '../components/Home.vue'
import LoginItem from '../components/Login.vue'
import RegisterItem from '../components/Register.vue'
import PerfumesItem from '../components/Perfumes.vue'
import PerfumeItem from '../components/Perfume.vue'
import { getPerfume } from '../services/perfume.service'

const Profile = () => import('../components/Profile.vue')

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeItem,
  },
  {
    path: '/home',
    component: HomeItem,
  },
  {
    path: '/login',
    name: 'login',
    component: LoginItem,
  },
  {
    path: '/register',
    name: 'register',
    component: RegisterItem,
  },
  {
    path: '/profile',
    name: 'profile',
    component: Profile,
  },
  {
    path: '/perfumes',
    name: 'perfumes',
    component: PerfumesItem,
  },
  {
    path: '/perfumes/:brand/:name',
    name: 'perfumeBrandName',
    component: PerfumeItem,
    props: true,
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

export default router
