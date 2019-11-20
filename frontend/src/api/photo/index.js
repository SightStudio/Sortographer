import axios        from 'axios'
import { API_PATH } from '../../util/constant'


const PATH = API_PATH;
const service = {

  /**
   * 파일 다운로드
   *
   * @author Dong-Min Seol
   * @since  2019.11.14
   */
  getImgLabel: (data) => {
    return axios
      .post(`${PATH}/photo/rekognition`, data)
      .then(res => { return res.data.data.labelList; })
  }
};

export default service
