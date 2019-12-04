<template>
  <q-dialog v-model="card" class="row q-pa-md" ref="thisDialog">

    <q-card class="col-10">

      <q-uploader
        url="/api/img/temp"
        label="이미지 등록"
        auto-upload
        field-name="file"
        style="margin: 0; padding: 0; width: 100%;"
        @uploaded="afterTempUpload"
      />

      <q-card-section>
        <div class="row no-wrap items-center justify-center">

          <div class="col-8 text-h6 ellipsis">
            <q-input bottom-slots v-model="title" label="제목 입력" counter :dense="true">
              <template v-slot:prepend>
                <q-icon name="subtitles" />
              </template>
              <template v-slot:append>
                <q-icon name="close" @click="title = ''" class="cursor-pointer" />
              </template>
            </q-input>
          </div>

        </div>
      </q-card-section>

      <q-card-section>
        <div class="text-grey q-ml-lg items-end text-right">
          <p class="row">드래그 하여 등록</p>
          <p class="row">또는 우측 상단 + 클릭</p>
        </div>
      </q-card-section>

      <q-card-section>
        <div class="text-subtitle1">이미지 분석 결과</div>
        <div class="text-subtitle2 text-grey">{{ this.detectionLabel.join(', ') }}</div>
      </q-card-section>

      <q-separator/>

      <q-card-actions class="justify-end">
        <q-btn color="primary" @click="register">등록하기</q-btn>
      </q-card-actions>

    </q-card>
  </q-dialog>
</template>

<script>
import PhotoApi from '../../api/photo';
export default {
  data () {
    return {
      card   : false,
      tempImg: [],
      detectionLabel : [],
      title : '',
      s3Path: null
    }
  },
  methods: {
    open  () {
      this.card = true;
      this.detectionLabel.length = 0;
    },
    close () { this.card = false; },

    afterTempUpload (info) {
      const repl = info.xhr.responseText;
      const obj  = JSON.parse(repl);
      obj.data.tempUrlList.forEach(s3Path => {
        this.s3Path = s3Path;
        this.tempImg.push({
          label : PhotoApi.getImgLabel(s3Path),
          url   : s3Path.absolutePath
        })
      });

      this.tempImg.forEach(imgs => {
        imgs.label.then(list => {
          this.detectionLabel.length = 0;
          list.forEach(label => {
            this.detectionLabel.push(label.name);
          })
        })
      });
    },

    register () {
      const data = {
        title : this.title,
        s3Path: this.s3Path,
        labelList: this.detectionLabel
      };

      PhotoApi
        .registerImg(data)
        .then(data => {
          this.$q.dialog({ title: '등록 성공' , message: `사진 등록이 완료되었습니다.` });
          this.$emit('after-register-success', {});
          this.close();
        }).catch(e => {
          this.$q.dialog({ title: '등록 실패' , message: e.message });
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

  .q-uploader {
    min-height: 300px;
  }
}

@media (max-width: 600px) {
  .q-card {
    width: 95%;
  }

  .q-uploader {
    min-height: 150px;
  }
}
</style>
