/**
 * 기본 라우터
 *
 * @author Dong-Min Seol
 * @since  2019-11-02
 */
const routes = [
  { path: '/'      , component: () => import('pages/index.vue')   },
  { path: '/signin', component: () => import('pages/user/signin') },
  { path: '/signup', component: () => import('pages/user/signup') }
];

export default routes;
