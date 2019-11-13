<template>
  <q-page class="current-page">
  <div class="q-pa-md">
    <div class="gallery-img-container row wrap items-start justify-center">

        <q-card class="gallery-img col-md-2 q-ma-xs" v-for="i in 30" :key="i">
          <img src="https://cdn.quasar.dev/img/mountains.jpg">

          <q-card-section class="q-pa-sm">
            <div class="text-h6">Card {{i}}</div>

            <q-btn flat round icon="favorite" color="red"    />
            <q-btn flat round icon="bookmark" color="teal"   />
          </q-card-section>

        </q-card>
    </div>
    </div>
  </q-page>
</template>

<script>
export default {
  data () {
    return {
      lorem: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'
    }
  }
}
</script>

<style scoped>
  /*.gallery-img {*/
  /*   margin: 0 auto;*/
  /* }*/
  .current-page {
    width: 100%;
    margin: 0 auto;
  }

  .img-container {
    width: 80%;
  }
</style>
