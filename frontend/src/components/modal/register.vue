<template>
  <q-dialog v-model="card">
    <q-card>
      <q-img src="https://media-cdn.tripadvisor.com/media/photo-s/0a/47/a8/91/chicken-salad-sandwich.jpg" />

      <q-card-section>
        <div class="col-6">
          <q-btn
            fab
            color="primary"
            icon="place"
            class="absolute"
            style="top: 0; right: 12px; transform: translateY(-50%);"
          />

          <div class="row no-wrap items-center">
            <div class="col text-h6 ellipsis">Cafe Basilico</div>
            <div class="col-auto text-grey q-pt-md">
              <q-icon name="place" /> 250 ft
            </div>
          </div>

          <q-rating v-model="stars" :max="5" size="32px" />
        </div>

        <div class="col-6">

        </div>
      </q-card-section>

      <q-card-section>
        <div class="text-subtitle1">$・Italian, Cafe</div>
        <div class="text-subtitle2 text-grey">Small plates, salads & sandwiches in an intimate setting.</div>
      </q-card-section>

      <q-separator/>

      <q-card-actions>
        <q-btn flat round icon="event" v-close-popup />
        <q-btn flat v-close-popup>5:30PM</q-btn>
        <q-btn flat v-close-popup>7:30PM</q-btn>
        <q-btn flat v-close-popup>9:00PM</q-btn>
        <q-btn flat color="primary" v-close-popup>Reserve</q-btn>
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
export default {
  data () {
    return {
      card: false,
      stars: 3
    }
  },
  methods: {
    open  () { this.card = true;  },
    close () { this.card = false; }
  }
}
</script>

<style scoped>
  .current-page {
    width: 100%;
    margin: 0 auto;
  }
</style>
