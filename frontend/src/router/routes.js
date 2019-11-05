import userRoutes from './sub-routes/user-routes'

/**
 * 메인 라우터
 * @author Dong-Min Seol
 * @since  2019-11-02
 */
const routes = [
  {
    path: '/',
    component: () => import('layouts/MainLayout.vue'),
    children: [
      { path: '', component: () => import('pages/index.vue') },
      ...userRoutes
    ]
  }
]

// Always leave this as last one
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: () => import('pages/Error404.vue')
  })
}
export default routes
