<template>
  <q-layout view="lHh Lpr lFf">
      <q-header reveal>
        <q-toolbar class="row">
            <q-toolbar-title class="col-md-2">
              <router-link to="/">The APP</router-link>
            </q-toolbar-title>

            <div class="q-pa-xs col-md-6"></div>

            <div class="q-gutter-xs col-sm-4 text-right">
              <q-btn unelevated color="white" text-color="primary" label="Sign in" to="/user/signin"/>
              <q-btn unelevated color="white" text-color="primary" label="Sign up" to="/user/signup"/>
            </div>
        </q-toolbar>
      </q-header>

      <q-page-container style="margin-top: 50px;">
        <router-view/>
      </q-page-container>

      <!-- 2단 Gnb -->
      <q-page-sticky position="top" expand class="bg-accent text-white q-mb-xs">
        <q-toolbar class="row">
          <q-toolbar-title class="col-md-4"></q-toolbar-title>

          <q-input class="col-md-4" outlined dense v-model="search" >
            <template v-slot:append>
              <q-btn flat icon="search"/>
            </template>
          </q-input>

          <div class="q-gutter-md col-sm-4 text-right">
            <q-btn unelevated dense color="white" text-color="primary" label="New Post"/>
          </div>
        </q-toolbar>
      </q-page-sticky>

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
      search: ''
    }
  },
  methods: {
    onClick () {
      // console.log('Clicked on a QChip')
    }
  }
}
</script>
