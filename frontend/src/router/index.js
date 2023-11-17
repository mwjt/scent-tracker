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

router.beforeEach((to, from, next) => {
  const publicPages = ['/login', '/register', '/home']
  const authRequired = !publicPages.includes(to.path)
  const loggedIn = localStorage.getItem('user')

  // trying to access a restricted page + not logged in
  // redirect to login page
  if (authRequired && !loggedIn) {
    next('/login')
  } else {
    next()
  }
})

export default router
