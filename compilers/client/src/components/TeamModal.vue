<template>
        <v-dialog
          v-model="dialog"
          width="500"
        >
          <template v-slot:activator="{ on }">
              <Button
                icon="account-multiple"
                label="Equipe"
                shortcut="F7"
                :onClick="on.click"
              >

              </Button>
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
                  <a href="https://github.com/luanraithz/fla-cmp-nlp/tree/master/compilers/client">
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
</template>
<script lang="ts">
import Vue from 'vue';
import Button from './Button.vue';

export default Vue.extend({
    components: {
        Button
    },
    name: 'TeamModal',
    data: (): { dialog: boolean } => ({ dialog: false }) });
</script>

<style scoped>
    a {
        text-decoration: none;
    }
    .heart {
        color: #E53935;
    }
</style>

