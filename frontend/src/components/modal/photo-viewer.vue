<template>
  <q-dialog v-model="card" class="row q-pa-md" ref="thisDialog">

    <q-card class="col-10">

      <q-card-section class="row bg-primary text-white">
        <div class="col-11 no-wrap items-center">
          <div class="text-h6 ellipsis">{{ photoData.title }}</div>
        </div>
      </q-card-section>

      <q-separator/>

      <q-card-section class="row">

        <div class="col-7">
          <q-img :src="photoData.absolutePath" alt="" class="photo-view-img"/>
        </div>

        <div class="q-pl-lg col-4">
          <q-input filled :value="getImgLink"      hint="Image Link"    :dense="true" readonly/>
          <q-input filled :value="getHtmlLink"     hint="Markdown Link" :dense="true" readonly/>
          <q-input filled :value="getMarkdownLink" hint="HTML"          :dense="true" readonly/>
        </div>
      </q-card-section>

      <q-card-section>
        <div class="text-subtitle1">이미지 분석 결과</div>
        <div class="text-subtitle2 text-grey">{{ photoData.photoLabel }}</div>
      </q-card-section>

      <q-separator/>
    </q-card>
  </q-dialog>
</template>

<script>
export default {
  props : {
    photoData: {
      type: Object,
      default: function () {
        return {
          title        : '',
          absolutePath : '',
          photoLabel   : ''
        }
      }
    }
  },
  computed : {
    getImgLink () {
      return this.photoData.absolutePath;
    },

    getHtmlLink () {
      return  `<a href="${this.photoData.absolutePath}"><img src="${this.photoData.absolutePath}" title="source: imgur.com" /></a>`;
    },

    getMarkdownLink () {
      return `[Sortographer](${this.photoData.absolutePath})`;
    }
  },
  data () {
    return {
      card: false
    }
  },
  methods: {
    open  () { this.card = true;  },
    close () { this.card = false; }
  }
}
</script>

<style scoped>
@media (min-width: 600px) {
  .q-card {
    width  : 70%;
    height : 95%;
  }
}

@media (max-width: 600px) {
  .q-card {
    width: 95%;
  }
}
</style>
