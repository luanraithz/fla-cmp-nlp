<template>
  <v-app>
        <v-container fluid class="container">
            <v-row>
                <div class="result">
                    <v-textarea
                        :no-resize="true"
                        name="language"
                        v-model="content"
                        >
                    </v-textarea>
                </div>
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
                <TeamModal></TeamModal>
            </v-row>
      </v-container>
    <v-content>
        <div class="result">
            <Table :data="result"/>
        </div>
    </v-content>
  </v-app>
</template>

<script lang="ts">
import Vue from 'vue';
import Table from './components/Table.vue';
import TeamModal from './components/TeamModal.vue';
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
    Table,
    TeamModal
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
<style>
    body, html {
        overflow: auto !important;
    }
    button {
        width: 100%;
    }
    .team-card {
        height: 100px;
        box-sizing: border-box;
        max-height: unset;
        padding: 20px;
    }
    .result {
        padding: 15px;
        width: 100%;
        height: 100%;
    }
    textarea {
        height: 30vh;
        min-height: 187px;
    }

</style>
