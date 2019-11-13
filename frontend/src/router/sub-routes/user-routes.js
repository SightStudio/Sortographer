/**
 * 유저 라우터
 * @author Dong-Min Seol
 * @since  2019-11-02
 */
const routes = [
  {
    path: '/user/signin',
    component: () => import('pages/user/signin')
  },

  {
    path: '/user/signup',
    component: () => import('pages/user/signup')
  }
]
export default routes
