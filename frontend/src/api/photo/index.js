import axios        from 'axios'
import { API_PATH, AUTH_HEADER } from '../../util/constant'

const PATH = API_PATH;
const service = {

  /**
   * 이미지 분석 결과 가져오기
   *
   * @author Dong-Min Seol
   * @since  2019.11.14
   */
  getImgLabel: (data) => {
    return axios
      .post(`${PATH}/photo/rekognition`, data)
      .then(res => { return res.data.data.labelList; })
  },


  /**
   * 이미지 등록하기
   *
   * @author Dong-Min Seol
   * @since  2019.11.14
   */
  registerImg: (data) => {
    return axios
      .post(`${PATH}/photo/register`, data, AUTH_HEADER())
      .then(res => { return res.data; })
  },

  /**
   * 사진 목록 가져오기
   *
   * @author Dong-Min Seol
   * @since  2019.11.29
   */
  getPhotoList: (data) => {
    return axios
      .get(`${PATH}/photo/list/${data.page}/${data.limit}`)
      .then(res => { return res.data; })
  },

  /**
   * 이미지 라벨 전체 종류 가져오기
   *
   * @author Dong-Min Seol
   * @since  2019.11.29
   */
  getDistinctPhotoLabel: () => {
    return axios
      .get(`${PATH}/photo/label/all`)
      .then(res => { return res.data; })
  }
};

export default service
