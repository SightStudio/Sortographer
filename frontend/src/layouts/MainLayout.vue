<template>
  <q-layout view="lHh Lpr lFf">
      <q-header reveal>
        <q-toolbar class="row">
            <q-toolbar-title class="col-md-2">
              The APP
            </q-toolbar-title>

            <div class="q-pa-xs col-md-6">
              <!-- <q-chip clickable @click="onClick" color="primary" text-color="white" icon="event">
                Add to calendar
              </q-chip>
              <q-chip clickable @click="onClick" icon="bookmark">
                Bookmark
              </q-chip>
              <q-chip clickable @click="onClick" color="teal" text-color="white" icon="bookmark">
                Bookmark
              </q-chip>
              <q-chip clickable @click="onClick" color="orange" text-color="white" icon="directions">
                Get directions
              </q-chip> -->
            </div>

            <div class="q-gutter-xs col-sm-4 text-right">
              <q-btn unelevated color="white" text-color="primary" label="Sign in" to="/user/signin"/>
              <q-btn unelevated color="white" text-color="primary" label="Sign up"/>
            </div>
        </q-toolbar>
      </q-header>
      <q-page-container>
        <router-view/>

        <q-page-sticky position="top" expand class="bg-accent text-white">
          <q-toolbar class="row">
            <q-toolbar-title class="col-md-4">
            </q-toolbar-title>

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
