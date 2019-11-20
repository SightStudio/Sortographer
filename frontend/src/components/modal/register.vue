<template>
  <q-dialog v-model="card" class="row q-pa-md">

    <q-card class="col-10">

      <q-uploader
        url="/api/img/temp"
        label="이미지 등록"
        field-name="file"
        style="margin: 0; padding: 0; width: 100%;"
        @uploaded="afterTempUpload"
      />

      <q-card-section>
        <div>
          <q-btn
            fab
            color="primary"
            icon="add"
            class="absolute"
            style="top: 0; right: 20px; transform: translateY(-50%);"
          />

          <div class="row no-wrap items-center">
            <div class="col text-h6 ellipsis">제목</div>
            <div class="col-auto text-grey q-pt-md">
              <q-icon name="add" /> new photo
            </div>
          </div>

          <q-rating v-model="stars" :max="5" size="32px" />
        </div>

      </q-card-section>

      <q-card-section>
        <div class="text-subtitle1">이미지 분석 결과</div>
        <div class="text-subtitle2 text-grey">{{ detectionLabel }}</div>
      </q-card-section>

      <q-separator/>

      <q-card-actions class="justify-end">
        <q-btn color="primary" v-close-popup>등록하기</q-btn>
      </q-card-actions>

    </q-card>
  </q-dialog>
</template>

<script>
import PhotoApi from '../../api/photo';
export default {
  data () {
    return {
      card: false,
      stars: 3,
      tempImg: [],
      detectionLabel : ''
    }
  },
  methods: {
    open  () { this.card = true;  },
    close () { this.card = false; },
    afterTempUpload (info) {
      const repl = info.xhr.responseText;
      const obj  = JSON.parse(repl);
      obj.data.tempUrlList.forEach(s3Path => this.tempImg.push({
        label : PhotoApi.getImgLabel(s3Path),
        url   : s3Path.absolutePath
      }));

      this.tempImg.forEach(imgs => {
        imgs.label.then(list => {
          list.forEach(label => {
            this.detectionLabel += `${label.name}, `;
          })
        })
      });
    }
  }
}
</script>

<style scoped>
@media (min-width: 600px) {
  .q-card {
    width: 45%;
  }
}

@media (max-width: 600px) {
  .q-card {
    width: 95%;
  }
}
</style>
