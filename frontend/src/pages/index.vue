<template>
  <q-page class="current-page">
    <div class="row wrap items-start justify-center q-pa-md">
      <q-card class="col-md-2 q-ma-xs" v-for="i in 30" :key="i">
        <img src="https://cdn.quasar.dev/img/mountains.jpg">

        <q-card-section class="q-pa-xs">
          <div class="text-h6">Card {{i}}</div>

          <q-btn flat round icon="favorite" color="red"  size="sm" />
          <q-btn flat round icon="bookmark" color="teal" size="sm" />
        </q-card-section>
      </q-card>
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
  .current-page {
    width: 100%;
    margin: 50px auto 0;
  }
</style>
