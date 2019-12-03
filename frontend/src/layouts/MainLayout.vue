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

      <q-page-scroller position="bottom-right" :scroll-offset="150" :offset="[18, 18]">
        <q-btn fab icon="keyboard_arrow_up" color="accent" />
      </q-page-scroller>
  </q-layout>
</template>
<script>
import { mapState } from 'vuex';
export default {
  computed: mapState(['status', 'profile']),
  data () {
    return {
      loggedin : false
    }
  },
  methods: {
    logout () {
      this.$store.dispatch('user/logout');
      this.$router.go();
    }
  }
}
</script>
<style>
</style>
