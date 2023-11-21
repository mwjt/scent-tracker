<script>
import { getPerfumeByBrandAndName } from '../services/perfume.service'
import { getPerfumeImage } from '../services/gallery.service'

export default {
  name: 'PerfumeItem',
  data() {
    return {
      imageLoading: false,
      url: '',
      perfume: {},
    }
  },
  async beforeMount() {
    this.$loading = true
    let res = await getPerfumeByBrandAndName(this.$route.params.brand, this.$route.params.name)
    this.perfume = res
    this.$loading = false
    this.imageLoading = true
    console.log(this.perfume)
    res = await getPerfumeImage(this.perfume.id)
    this.url = URL.createObjectURL(res)
    this.imageLoading = false
  },
}
</script>

<template>
  <v-container>
    <v-row>
      <v-col class="header" cols="4">
        <v-card class="title ma-2 pa-4">
          <div class="name">
            {{ perfume.name }}
          </div>
          <div class="brand">
            <span class="link">
              <router-link to="google.com" class="link">
                {{ perfume.brand }}
              </router-link>
            </span>
          </div>
          <div class="subtitle">
            {{ perfume.concentration }} by
            <span class="link">
              <router-link to="google.com" class="link">
                {{ perfume.perfumer }}
              </router-link>
            </span>
            , {{ perfume.year }}
          </div>
        </v-card>
        <v-row class="ma-2" no-gutters style="width: 100%">
          <v-col>
            <v-card class="pa-1 mr-1" height="100%">
              <v-img aspect-ratio="1/1" :src="url"></v-img>
            </v-card>
          </v-col>
          <v-col>
            <v-card class="pa-1 ml-1" height="100%">
              <v-chip-group column>
                <v-chip v-for="tag in perfume.tags" :key="tag">
                  {{ tag }}
                </v-chip>
              </v-chip-group>
            </v-card>
          </v-col>
        </v-row>

        <v-card class="scores ma-2 pa-4">
          <span class="label"> Scent: </span>
          <span class="value">{{ perfume.scent }} / 10</span>
          <v-progress-linear
            v-model="perfume.scent"
            bg-color="#264653"
            color="#264653"
            max="10"
            height="8"
            rounded
          ></v-progress-linear>
          <br />
          <span class="label"> Sillage: </span>
          <span class="value">{{ perfume.sillage }} / 10</span>
          <v-progress-linear
            v-model="perfume.sillage"
            bg-color="#2A9D8F"
            color="#2A9D8F"
            max="10"
            height="8"
            rounded
          ></v-progress-linear>
          <br />
          <span class="label"> Longevity: </span>
          <span class="value">{{ perfume.longevity }} / 10</span>
          <v-progress-linear
            v-model="perfume.longevity"
            max="10"
            bg-color="#E9C46A"
            color="#E9C46A"
            height="8"
            rounded
          ></v-progress-linear>
          <br />
          <span class="label"> Bottle: </span>
          <span class="value">{{ perfume.bottle }} / 10</span>
          <v-progress-linear
            v-model="perfume.bottle"
            max="10"
            bg-color="#F4A261"
            color="#F4A261"
            height="8"
            rounded
          ></v-progress-linear>
          <br />
          <span class="label"> Value: </span>
          <span class="value">{{ perfume.value }} / 10</span>
          <v-progress-linear
            v-model="perfume.value"
            max="10"
            bg-color="#E76F51"
            color="#E76F51"
            height="8"
            rounded
          ></v-progress-linear>
        </v-card>
      </v-col>
      <v-col>
        <v-card class="reviews ma-2 pa-1"> Reviews </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<style lang="scss">
$subcolor: rgb(85, 85, 85);

.link > a {
  border-bottom: 1px solid white;
  transition: 0.3s ease;
  color: unset;
  text-decoration: none;
  &:hover {
    text-decoration: none;
    color: unset;
    border-color: $subcolor;
  }
}

.header {
  vertical-align: top;
  min-width: 25%;

  .title {
    width: 100%;

    .name {
      font-size: xx-large;
      font-weight: 550;
    }
    .brand {
      font-size: x-large;
      font-weight: 500;
      color: $subcolor;
    }
    .subtitle {
      font-size: large;
      font-weight: 500;
      color: $subcolor;
    }
  }

  .scores {
    width: 100%;
    font-size: large;

    .label {
      float: inline-start;
      font-weight: 450;
    }

    .value {
      float: inline-end;
    }
  }
}

.reviews {
  width: 100%;
}
</style>
