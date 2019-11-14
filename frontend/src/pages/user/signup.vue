<template>
  <q-page
    class="row justify-center items-center"
    style="background: linear-gradient(#8274C5, #5A4A9F);"
  >
    <div class="column q-pa-lg">
      <div class="row">

        <q-card square class="shadow-24" style="width:300px;height:485px;">
          <q-card-section class="bg-deep-purple-7">
            <h4 class="text-h5 text-white q-my-md">회원 가입</h4>
            <div class="absolute-bottom-right q-pr-md" style="transform: translateY(50%);">
              <q-btn fab icon="close" color="purple-4" />
            </div>
          </q-card-section>

          <q-card-section>
            <q-form class="q-px-sm q-pt-xl q-pb-lg">
              <q-input square clearable v-model="form.email" type="email" label="Email">
                <template v-slot:prepend>
                  <q-icon name="email" />
                </template>
              </q-input>
              <q-input square clearable v-model="form.username" type="username" label="Username">
                <template v-slot:prepend>
                  <q-icon name="person" />
                </template>
              </q-input>
              <q-input square clearable v-model="form.password" type="password" label="Password">
                <template v-slot:prepend>
                  <q-icon name="lock" />
                </template>
              </q-input>
            </q-form>
          </q-card-section>

          <q-card-actions class="q-px-lg">
            <q-btn size="lg" color="purple-4" class="full-width text-white" label="회원 가입하기" @click="signIn"/>
          </q-card-actions>

          <q-card-section class="text-center q-pa-sm">
            <q-btn flat class="text-grey-6" to="/user/signin">Return to login</q-btn>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </q-page>
</template>

<script>
import userApi from '../../api/user';
export default {
  name: 'Login',
  data () {
    return {
      form: {
        email: '',
        username: '',
        password: ''
      }
    }
  },

  methods: {

    signIn () {
      const result = userApi.signUp(this.form);
      if (result) {
        this.$q.dialog({
          title: '회원 가입 완료!',
          message: '메인 페이지로 이동합니다'
        }).onOk(() => {
          this.$router.push({ path: '/' })
        }).onCancel(() => {
          // console.log('Cancel')
        }).onDismiss(() => {
          // console.log('I am triggered on both OK and Cancel')
        })
      }
    }
  }
}
</script>

<style>
</style>
