import store from '../../store';

export const API_PATH   = '/api';
export const AUTH_HEADER = () => {
  return { headers: { 'Authorization': `Bearer ${store().getters['user/getProfile']}` } }
};

