<template>
  <div
    class="window-height window-width row justify-center items-center"
    style="background: linear-gradient(#8274C5, #5A4A9F);"
  >
    <div class="column q-pa-lg">
      <div class="row">
        <q-card square class="shadow-24" style="width:300px;height:485px;">

          <q-card-section class="bg-deep-purple-7">
            <h4 class="text-h5 text-white q-my-md">로그인</h4>
          </q-card-section>

          <q-card-section>
            <q-form class="q-px-sm q-pt-xl">

              <q-input square clearable v-model="email" type="email" label="Email">
                <template v-slot:prepend>
                  <q-icon name="email" />
                </template>
              </q-input>

              <q-input square clearable v-model="password" type="password" label="Password">
                <template v-slot:prepend>
                  <q-icon name="lock" />
                </template>
              </q-input>
            </q-form>
          </q-card-section>

          <q-card-actions class="q-px-lg">
            <q-btn unelevated size="lg" color="purple-4" class="full-width text-white" label="Sign In" />
          </q-card-actions>

          <q-card-section class="text-center q-pa-sm">
            <q-btn flat class="text-grey-6" label="비밀번호를 잃어버리렸나요?">
              <q-popup-proxy>
                <q-banner>
                  <template v-slot:avatar>
                    <q-icon size="sm" name="settings_applications" color="primary" />
                  </template>
                  그럼 뭐 답이없네 포기하셈
                </q-banner>
              </q-popup-proxy>
            </q-btn>
          </q-card-section>
        </q-card>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data () {
    return {
      email: '',
      username: '',
      password: ''
    }
  }
}
</script>

<style>
</style>
