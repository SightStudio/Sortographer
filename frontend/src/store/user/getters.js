export function getProfile (state) {
  if (state.user.jwt) {
    return state.user.jwt
  } else if (sessionStorage.getItem('jwt')) {
    return sessionStorage.getItem('jwt');
  } else {
    return null;
  }
}
