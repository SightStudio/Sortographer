<template>

  <!-- 2단 Gnb -->
  <q-page-sticky id="sub-gnb" position="top" expand class="text-white q-mb-xs">
    <q-toolbar class="row items-start justify-end">
      <q-select
        filled
        v-model="search"
        use-input
        hide-selected
        fill-input
        input-debounce="0"
        :options="options"
        @filter="filterFn"
        class="search col-md-4 q-mr-lg"
      >
        <template v-slot:no-option>
          <q-item>
            <q-item-section class="text-grey">
              No results
            </q-item-section>
          </q-item>
        </template>
      </q-select>

      <div class="q-gutter-md col-sm-4 text-right">
        <q-btn @click="openRegisterModal" unelevated dense color="white" text-color="primary" label="New Post"/>
      </div>
    </q-toolbar>

    <photo-register-modal
      ref="registerModal"
      v-on="$listeners"
    >
    </photo-register-modal>
  </q-page-sticky>
</template>
<script>
import photoRegtisterModal from '../../../components/modal/register-photo';
export default {
  components : {
    'photo-register-modal' : photoRegtisterModal
  },
  props: {
    autoCompleteSubject: {
      type: Array,
      default: function () {
        return ['']
      }
    }
  },
  data () {
    return {
      search: '',
      options: this.autoCompleteSubject
    }
  },
  methods: {
    openRegisterModal () {
      if (this.$store.getters['user/getProfile']) {
        this.$refs.registerModal.open();
      } else {
        this.$q.dialog({ title: '로그인 필요' , message: '사진을 업로드 하려면 로그인을 해주세요' });
      }
    }, // end openRegisterModal
    filterFn (val, update, abort) {
      if (val.length < 2) {
        abort();
        return
      }

      update(() => {
        const needle = val.toLowerCase();
        this.options = this.autoCompleteSubject.filter(v => v.toLowerCase().indexOf(needle) > -1)
      })
    } // end filterFn
  }
}
</script>
<style>
  #sub-gnb {
    background-color: rgb(60, 53, 53);
    margin-bottom: 50px;
  }

  #sub-gnb .search {
    background-color: #7c4dff;
  }
</style>
