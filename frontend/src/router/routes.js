import baseRoutes  from './sub-routes/base-routes';
import userRoutes  from './sub-routes/user-routes';
import photoRoutes from './sub-routes/photo-routes';

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
      ...baseRoutes,
      ...userRoutes,
      ...photoRoutes
    ]
  }
];

// 404 처리
if (process.env.MODE !== 'ssr') {
  routes.push({
    path: '*',
    component: () => import('pages/Error404.vue')
  })
};
export default routes
