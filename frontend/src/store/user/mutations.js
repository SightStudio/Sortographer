/**
 * 로그인 mutation
 * @author Dong-Min Seol
 * @since  2019.11.17
 */
export function signin (state, { jwt }) {
  console.log(jwt)
  state.jwt = jwt;
  sessionStorage.setItem('jwt', jwt);
}

/**
 * 로그아웃 mutation
 * @author Dong-Min Seol
 * @since  2019.11.17
 */
export function Logout (state) {
  state.jwt = null;
  sessionStorage.removeItem('jwt');
}
