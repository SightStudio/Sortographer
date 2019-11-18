import Vue from 'vue';

/**
 * 로그인 mutation
 * @author Dong-Min Seol
 * @since  2019.11.17
 */
export function signin (state, { jwt }) {
  Vue.set(state, 'jwt', jwt);
  sessionStorage.setItem('jwt', jwt);
}

/**
 * 로그아웃 mutation
 * @author Dong-Min Seol
 * @since  2019.11.17
 */
export function logout (state) {
  Vue.set(state, 'jwt', '');
  sessionStorage.removeItem('jwt');

  Vue.set(state, 'profile', '');
  sessionStorage.removeItem('profile');
}

export function setProfile (state, { profile }) {
  Vue.set(state, 'profile', profile);
  sessionStorage.setItem('profile', JSON.stringify(profile));
}
