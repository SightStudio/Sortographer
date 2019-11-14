<template>
  <q-page class="current-page">
  <div class="q-pa-md">
    <div class="gallery-img-container row wrap items-start justify-center">

        <q-card class="gallery-img col-md-2 q-ma-xs" v-for="i in 30" :key="i">
          <img src="https://cdn.quasar.dev/img/mountains.jpg">

          <q-card-section class="q-pa-xs">
            <div class="text-h6">Card {{i}}</div>

            <q-btn flat round icon="favorite" color="red"  size="sm" />
            <q-btn flat round icon="bookmark" color="teal" size="sm" />
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
