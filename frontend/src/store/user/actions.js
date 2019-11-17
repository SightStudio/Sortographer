import userApi    from '../../api/user'
import { Dialog } from 'quasar'
/**
 * 로그인 action
 *
 * @param  data { email, password }
 * @author Dong-Min Seol
 * @since  2019.11.17
 */
export function signin (context, data) {
  const { email, password } = data;
  const formData = new FormData();

  formData.set('email'   , email);
  formData.set('password', password);

  userApi
    .signIn(formData)
    .then(res => {
      if (res.status === 200) {
        context.commit('signin', { jwt : res.data.data.jwt });
        Dialog.create({ title: '로그인 성공!', message: '메인화면으로 이동합니다.' })
      }
    })
    .catch(reason => {
      Dialog.create({ title: '로그인 실패' , message: '아이디와 비밀번호를 확인해주세요.' });
    })
}
