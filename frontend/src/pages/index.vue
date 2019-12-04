<template>
  <q-page class="current-page">
    <div class="row wrap items-start justify-center q-pa-md">
      <q-card class="col-md-2 q-ma-xs" v-for="(p, idx) in photoList" :key="idx" @click="openPhotoViewer(p)">
        <img :src="p.absolutePath">

        <q-card-section class="q-pa-xs">
          <div class="text-h6">{{p.title}}</div>

          <q-btn flat round icon="favorite" color="red"  size="sm" />
          <q-btn flat round icon="bookmark" color="teal" size="sm" />
        </q-card-section>
      </q-card>
    </div>

    <sub-gnb-search
      :autoCompleteSubject="suggestList"
      @after-register-success="getPhotoList"
    >
    </sub-gnb-search>

    <photo-viewer
      ref="photoViewer"
      :photoData="photoInfo"
    >
    </photo-viewer>
  </q-page>
</template>

<script>
import PhotoApi    from '../api/photo';
import searchBar   from '../components/gnb/sub-gnb/search-bar';
import photoViewer from '../components/modal/photo-viewer';
export default {
  components: {
    'sub-gnb-search': searchBar,
    'photo-viewer'  : photoViewer
  },
  data () {
    return {
      photoList   : [],
      suggestList : [],
      photoInfo   : {
        absolutePath : '',
        photoLabel   : ''
      }
    }
  },
  async beforeMount () {
    this.getPhotoList();
    this.getSuggestList();
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
      PhotoApi
        .getDistinctPhotoLabel()
        .then(data => { this.suggestList = data.data.distinctLabelList })
    },

    openPhotoViewer (p) {
      this.photoInfo = p;
      this.$refs.photoViewer.open();
    }
  }
}
</script>

<style scoped>
  .current-page {
    width : 100%;
    margin: 50px auto 0;
  }
</style>
