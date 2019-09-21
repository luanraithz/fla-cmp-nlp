<template>
  <v-app>
      <input class="hidden" type="file" ref="input-file"/>
        <v-container fluid class="container">
            <v-row>
                <div class="tools">
                    <Button
                        :onClick="newFile"
                        icon="file"
                        label="Novo"
                        :control="true"
                        shortcut="n"
                    />
                    <Button
                        :onClick="openFile"
                        icon="folder-open"
                        label="Abrir"
                        :control="true"
                        shortcut="o"
                    />
                    <Button
                        :onClick="saveFile"
                        icon="content-save"
                        :control="true"
                        label="Salvar"
                        shortcut="s"
                    />
                    <Button
                        :onClick="copySelection"
                        icon="content-copy"
                        label="Copiar"
                        :control="true"
                        shortcut="c"
                    />
                    <Button
                        :onClick="pasteContent"
                        icon="content-paste"
                        label="Colar"
                        :control="true"
                        shortcut="v"
                    />
                    <Button
                        :onClick="cutSelection"
                        icon="content-cut"
                        label="Recortar"
                        shortcut="x"
                        :control="true"
                    />
                    <Button
                        :onClick="compile"
                        icon="play"
                        label="Compilar"
                        shortcut="f9"
                    />
                    <TeamModal></TeamModal>
                </div>
            </v-row>
            <v-row>
                <div class="wrapper">
                    <v-row>
                        <div class="content">
                            <v-textarea
                                :no-resize="true"
                                name="language"
                                v-model="content"
                                >
                            </v-textarea>
                        </div>
                    </v-row>
                </div>
            </v-row>
            <v-row>
                <div class="output">
                    {{ error || message }}
                </div>
            </v-row>
            <v-row>
                <div class="status">
                    {{ status }}
                </div>
            </v-row>
      </v-container>
  </v-app>
</template>


<script lang="ts">

import Vue from 'vue'
import TeamModal from './components/TeamModal.vue'
import Button from './components/Button.vue'
import axios from 'axios'

const Types = {
    "EPSILON": "Epsilon",
    "DOLAR": "Dolar",
    "RESERVED_WORD": "Palavra reservada",
    "ID_INT": "identificador",
    "ID_FLOAT": "identificador",
    "ID_STRING": "identificador",
    "ID_BOOL": "identificador",
    "ID_COMPOSTO": "identificaodr",
    "INT": "constante int",
    "FLOAT": "constante float",
    "STRING": "constante string"
}

type Result = {
    position: number,
    lexem: string,
    type: string,
}

const formatType = t => Types[s]

const formatData = items => items.map(s => ({ ...s, type: Types[s.type]})).filter(({ type }) => type)

export default Vue.extend({
  name: 'App',
  components: {
      TeamModal,
      Button,
  },
  methods: {
      newFile: () => { console.log('new file') },
      openFile: function() {
          const el = this.$refs['input-file']
          if (el instanceof Element) {
              (el as HTMLElement).click()
          }
      },
      saveFile: () => { console.log('save file') },
      copySelection: () => { console.log('copy file') },
      pasteContent: () => { console.log('paste file') },
      cutSelection: () => { console.log('cut file') },
      compile: async function() {
          const { error, result } = await axios.post('http://localhost:8080/lexical', {content: this.content}).then(({ data }) => data)
          this.error = ""
          this.result = []
          console.log(error, result)
          if (error) {
              this.error = error.message
          } else {
              this.result = formatData(result)
          }
      }
  },

    mounted: function() {
        const el = this.$refs['input-file']
        if (el instanceof Element) {
            el.addEventListener('change', async ({ target }: any) => {
                const [file] = target.files
                this.content = await file.text()
            })
        }
    },
  data: (): { content: string, status: string, message: '', error: string } => ({ content: '', status: '', message: '', error: '', result: [] }),
});
</script>
<style>
    * {
        box-sizing: border-box;
    }
    body, html {
        overflow: auto !important;
        min-width: 900px;
    }
    .output {
        width: 100%;
        height: 100px;
        padding: 10px;
        border: 1px solid gray;
        background-color: #f0f0f0;
        min-width: 900px;
        min-height: 100px;
    }
    .status {
        height: 30px;
        width: 100%;
        padding: 5px;
        background-color: #f0f0f0;
        min-width: 900px;
        box-sizing: border-box;
    }
    .tools {
        height: 70px;
        display: flex;
        width: 100%;
        min-width: 900px;
    }

    input[type=file]{
        display: none;
    }
    .container {
        margin: 0 !important;
        padding-top: 0 !important;
        padding-bottom: 0 !important;
    }

    .wrapper {
        width: 100%;
        overflow: hidden;
    }
    .index {
        width: 50px;
        display: inline-flex;
        flex-direction: column;
        align-items: flex-end;
        padding-right: 10px;
    }
    .index > span {
        line-height: 18px;

    }
    .content {
        width: 100%;
        height: 400px;

    }

    .content > div {
        padding-top: 0;
        margin-top: 0;
    }
    textarea {
        width: 100%;
        height: 100%;
        min-height: 400px !important;
        padding: 20px !important;
    }
</style>
