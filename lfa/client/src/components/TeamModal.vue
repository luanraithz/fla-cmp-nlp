<template>
    <v-col cols="sm">
        <v-dialog
          v-model="dialog"
          width="500"
        >
          <template v-slot:activator="{ on }">
            <v-btn
              color="primary"
              dark
              v-on="on"
            >
                <v-icon >mdi-account-multiple</v-icon>
                Equipe
            </v-btn>
          </template>

          <v-card>
            <v-card-title
              class="headline grey lighten-2 text-center"
              primary-title
            >
              Equipe
            </v-card-title>

            <v-card-text>
              <br/>
              <v-row justify="center">
                  Luan Raithz Machado
              </v-row>
              <br/>
              <v-row justify="center">
                  <a href="https://github.com/luanraithz/fla-cmp-nlp/tree/master/lfa/client">
                      Github do projeto
                  </a>
              </v-row>
              <br/>
              <br/>
              <v-row justify="center">
                  Feito usando Vue, Typescript e
                  <v-icon class="heart">mdi-heart</v-icon>
              </v-row>
            </v-card-text>

            <v-divider></v-divider>

            <v-card-actions>
              <div class="flex-grow-1"></div>
              <v-btn
                color="primary"
                text
                @click="dialog = false"
              >
                Fechar
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
    </v-col>
</template>
<script lang="ts">
import Vue from 'vue';
import { FormattedOutput } from '../App.vue';

export default Vue.extend({
    name: 'TeamModal',
    data: (): { dialog: boolean } => ({
        dialog: false
    })
});
</script>

<style scoped>
    a {
        text-decoration: none;
    }
    .heart {
        color: #E53935;
    }
</style>

