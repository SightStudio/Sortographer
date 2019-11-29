<template>
  <q-page class="current-page">
    <div class="row wrap items-start justify-center q-pa-md">
      <q-card class="col-md-2 q-ma-xs" v-for="p in photoList" :key="p">
        <!-- <img src="https://cdn.quasar.dev/img/mountains.jpg"> -->
        <img :src="p.absolutePath">

        <q-card-section class="q-pa-xs">
          <div class="text-h5">{{p.title}}</div>
          <div class="text-h6">{{p.photoLabel}}</div>

          <q-btn flat round icon="favorite" color="red"  size="sm" />
          <q-btn flat round icon="bookmark" color="teal" size="sm" />
        </q-card-section>
      </q-card>
    </div>
  </q-page>
</template>

<script>
import PhotoApi from '../api/photo';
export default {
  data () {
    return {
      photoList   : [],
      suggestList : []
    }
  },
  async mounted () {
    this.getPhotoList();
  },
  methods: {
    getPhotoList () {
      const data = {
        page  : 1,
        limit : 20
      };
      PhotoApi
        .getPhotoList(data)
        .then(data => { this.photoList = data.data.photoList })
    },
    getSuggestList () {
      PhotoApi.getDistinctPhotoLabel()
        .then(data => { this.suggestList = data.data.distinctLabelList })
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
