<template>
  <v-app>
        <v-container fluid class="container">
            <v-row>
                <v-textarea
                    name="language"
                    v-model="content"
                    v-on:keyup.enter="run"
                    >
                </v-textarea>
            </v-row>
            <v-row>
                <v-btn
                    color="primary"
                    v-on:click="run">
                    Analisar
                </v-btn>
                <v-btn v-on:click="clear">
                    Limpar
                </v-btn>
                <v-dialog
                  v-model="dialog"
                  width="500"
                >
                  <template v-slot:activator="{ on }">
                    <v-btn
                      color="red lighten-2"
                      dark
                      v-on="on"
                    >
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
                      Luan Raithz Machado

                      <br/>
                      <a>
                          Github
                      </a>
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
            </v-row>
      </v-container>
    <v-content>
      <Table :data="result"/>
    </v-content>
  </v-app>
</template>

<script lang="ts">
import Vue from 'vue';
import HelloWorld from './components/HelloWorld.vue';
import Table from './components/Table.vue';
import { validate, Result, Output } from './final';

const ResultsMap = {
    [Result.InvalidWord]: 'Erro: Palavra inválida',
    [Result.ValidWord]: 'Palavra válida',
    [Result.InvalidSymbol]: 'Símbolo inválido',
    [Result.SpecialSymbol]: 'Símbolo especial'
}

export type FormattedOutput = {
    line: number,
    content: string,
    result: string,
    path: string
}

const toReadable = (result: Output): FormattedOutput => ({
    ...result,
    path: result.path.join(' '),
    result: ResultsMap[result.result]
})

export default Vue.extend({
  name: 'App',
  components: {
    HelloWorld,
    Table
  },
  methods: {
    run: function() {
        this.result = validate(this.content).map(toReadable)
    },
    clear: function() {
        this.result = []
        this.content = ""
    }
  },
    data: (): {
        content: string,
        expand: boolean,
        dialog: false,
        result: FormattedOutput[]
    } => ({
    result: [],
    content: '',
    expand: false,
    dialog: false
  }),
});
</script>
<style scoped>
    .team-card {
        height: 100px;
        box-sizing: border-box;
        max-height: unset;
        padding: 20px;
    }
</style>
