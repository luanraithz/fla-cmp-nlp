<template>
  <v-app>
        <v-container fluid class="container">
            <v-row>
                <v-textarea
                    name="language"
                    v-model="content"
                    >
                </v-textarea>
            </v-row>
            <v-row>
                <v-col cols="sm">
                    <v-btn
                        color="primary"
                        v-on:click="run">
                        <v-icon >mdi-play</v-icon>
                        Analisar
                    </v-btn>
                </v-col>
                <v-col cols="sm">
                    <v-btn
                        v-on:click="clear"
                        color="primary"
                    >
                        <v-icon >mdi-content-cut</v-icon>
                        Limpar
                    </v-btn>
                </v-col>
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
                          Luan Raithz Machado

                          <br/>
                          <a href="https://github.com/luanraithz">
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
                </v-col>
            </v-row>
      </v-container>
    <v-content>
      <Table :data="result"/>
    </v-content>
  </v-app>
</template>

<script lang="ts">
import Vue from 'vue';
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
    button {
        width: 100%;
    }
    .team-card {
        height: 100px;
        box-sizing: border-box;
        max-height: unset;
        padding: 20px;
    }
</style>
