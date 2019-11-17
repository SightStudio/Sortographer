import axios        from 'axios'
import { API_PATH } from '../../util/constant'

const PATH = API_PATH;
const service = {

  /**
   * 회원가입
   *
   * @author Dong-Min Seol
   * @since  2019.11.14
   */
  signUp: (data) => {
    return axios.post(`${PATH}/signup`, data)
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
    return axios.post(`${PATH}/signin`, formData)
      .then(res => { return res; })
  }
};

export default service
