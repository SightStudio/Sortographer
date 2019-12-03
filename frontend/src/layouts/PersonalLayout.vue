<template>
  <q-layout view="lHh Lpr lFf">
      <q-header reveal>
        <q-toolbar class="row">
            <q-toolbar-title class="col-md-2">
              <q-btn flat to="/">Sortographer</q-btn>
            </q-toolbar-title>

            <div class="q-pa-xs col-md-6"></div>

            <div class="q-gutter-xs col-sm-4 text-right">
              <q-btn unelevated color="white" text-color="primary" label="Sign in" to="/signin"/>
              <q-btn unelevated color="white" text-color="primary" label="Sign up" to="/signup"/>
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
export default {
  data () {
    return {
      leftDrawerOpen: false,
    }
  },
  methods: {
    onClick () {
      // console.log('Clicked on a QChip')
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
