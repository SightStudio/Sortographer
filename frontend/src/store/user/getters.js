export function getProfile (state) {
  if (state.user.profile) {
    return state.user.profile
  } else if (sessionStorage.getItem('profile')) {
    return JSON.parse(sessionStorage.getItem('profile'))
  } else {
    return null;
  }
}
