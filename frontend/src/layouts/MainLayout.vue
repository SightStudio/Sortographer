<template>
  <q-layout view="lHh Lpr lFf">
      <q-header reveal>
        <q-toolbar class="row">
            <q-toolbar-title class="col-md-2">
              <q-btn flat to="/">Sortographer</q-btn>
            </q-toolbar-title>

            <div class="q-pa-xs col-md-6"></div>

            <div class="q-gutter-xs col-sm-4 text-right" v-if="!this.$store.getters['user/getProfile']">
              <q-btn unelevated color="white" text-color="primary" label="Sign in" to="/signin"/>
              <q-btn unelevated color="white" text-color="primary" label="Sign up" to="/signup"/>
            </div>

            <div class="q-gutter-xs col-sm-4 text-right" v-else>
              <q-btn @click="logout" unelevated color="white" text-color="primary" label="Logout"/>
            </div>
        </q-toolbar>
      </q-header>

      <q-page-container>
        <transition
          appear
          enter-active-class="animated fadeIn"
          leave-active-class="animated fadeOut"
        >
          <router-view></router-view>
        </transition>
      </q-page-container>

      <!-- 2단 Gnb -->
      <q-page-sticky id="sub-gnb" position="top" expand class="text-white q-mb-xs">
        <q-toolbar class="row justify-end">
          <q-input class="search col-md-4 q-mr-lg" outlined dense v-model="search" value="">
            <template v-slot:append>
              <q-btn flat icon="search"/>
            </template>
          </q-input>

          <div class="q-gutter-md col-sm-4 text-right">
            <q-btn @click="openRegisterModal" unelevated dense color="white" text-color="primary" label="New Post"/>
          </div>
        </q-toolbar>
      </q-page-sticky>

      <q-page-scroller position="bottom-right" :scroll-offset="150" :offset="[18, 18]">
        <q-btn fab icon="keyboard_arrow_up" color="accent" />
      </q-page-scroller>

      <photo-register-modal
        ref="registerModal"
      >
      </photo-register-modal>

  </q-layout>
</template>
<script>
import photoRegtisterModal from '../components/modal/register-photo';
import { mapState } from 'vuex';
export default {
  components : {
    'photo-register-modal' : photoRegtisterModal
  },
  computed: mapState(['status', 'profile']),
  data () {
    return {
      loggedin : false,
      search   : '',
      registerModalBtn: false
    }
  },
  methods: {
    openRegisterModal () {
      if (this.$store.getters['user/getProfile']) {
        this.$refs.registerModal.open();
      } else {
        this.$q.dialog({ title: '로그인 필요' , message: '사진을 업로드 하려면 로그인을 해주세요' });
      }
    },// end openRegisterModal
    logout () {
      this.$store.dispatch('user/logout');
      this.$router.go();
    }
  }
}
</script>
<style>
  #sub-gnb {
    background-color: rgb(60, 53, 53);
    margin-bottom: 50px;
  }

  #sub-gnb .search {
    background-color: #7c4dff;
  }
</style>
