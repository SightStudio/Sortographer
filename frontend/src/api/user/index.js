import axios        from 'axios'
import { API_PATH, AUTH_HEADER } from '../../util/constant'

// const headerAuth = AUTH_HEADER;
const PATH = API_PATH;
const service = {

  /**
   * 회원가입
   *
   * @author Dong-Min Seol
   * @since  2019.11.14
   */
  signUp: (data) => {
    return axios
      .post(`${PATH}/signup`, data)
      .then(res => {
        return res.status === 200
      })
  },

  /**
   * 로그인
   *
   * @author Dong-Min Seol
   * @since  2019.11.14
   */
  signIn: (formData) => {
    return axios
      .post(`${PATH}/signin`, formData)
      .then(res => { return res; })
  },

  /**
   * 유저 정보 가져오기
   *
   * @author Dong-Min Seol
   * @since  2019.11.14
   */
  getProfile: () => {
    return axios
      .get(`${PATH}/me`, AUTH_HEADER())
      .then(res => { return res; })
  }
};

export default service
