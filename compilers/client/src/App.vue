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
                            <prism-editor
                                language="js"
                                :code="code"
                                v-model="code"
                                :line-numbers="true"
                            >
                            </prism-editor>
                        </div>
                    </v-row>
                </div>
            </v-row>
            <v-row>
                <div class="output">
                    <div v-if="result.type === 'LEXIC'">
                        <v-simple-table>
                            <template v-slot:default>
                              <thead>
                                <tr>
                                  <th class="text-left">Linha</th>
                                  <th class="text-left">Classe</th>
                                  <th class="text-left">Lexema</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr v-for="result in result">
                                  <td>{{ result.line }}</td>
                                  <td>{{ result.type }}</td>
                                  <td>{{ result.lexeme }}</td>
                                </tr>
                                Programa compilado com sucesso!
                              </tbody>
                            </template>
                        </v-simple-table>
                    </div>
                    <div v-if="result">
                        {{ result }}
                    </div>
                    <div v-if="error">
                        {{ error || message }}
                    </div>
                </div>
            </v-row>
      </v-container>
      <v-snackbar v-model="snackbar"> {{ snackMessage }} </v-snackbar>
  </v-app>
</template>


<script lang="ts">

import Vue from 'vue'
import FileSaver from 'file-saver'
import copy from 'copy-to-clipboard'
import PrismEditor from 'vue-prism-editor'

import TeamModal from './components/TeamModal.vue'
import Button from './components/Button.vue'
import { compileSemantic } from './service'
import { formatData } from './utils/lexical'
import getSelection from './utils/text-select'

type Data = {
  code: string,
  status: string,
  message: '',
  error: string,
  snackMessage: string,
  result: string,
  snackbar: boolean,
  snackTimeout: number
}

export default Vue.extend({
  name: 'App',
  components: {
      TeamModal,
      Button,
      PrismEditor
  },
  methods: {
      reset: function() {
          this.error = ""
          this.code = ""
          this.result = ""
      },
      newFile: function() { this.reset() },
      openFile: function() {
          const el = this.$refs['input-file']
          if (el instanceof Element) {
              (el as HTMLElement).click()
          }
      },
      saveFile: function() {
          if (this.code) {
              const file = new File([this.code], "result.joyc", { type: "text/plain;charset=utf-8"})
              this.snack("Salvando `result.joyC`")
              FileSaver.saveAs(file)
          } else {
              this.snack("Não existe nada para ser salvo!");
          }
      },
      copySelection: function() {
          copy(getSelection() || this.code);
          this.snack("Copiado!")
      },
      pasteContent: function() { this.snack("Ainda não foi feito :)") },
      cutSelection: function() {
          const selection = getSelection()
          copy(selection || this.code);
          if (!selection) {
            this.reset()
          }
          this.snack("Recortado!")
      },
      snack: function(message: string) {
          clearTimeout(this.snackTimeout)
          this.snackbar = true
          this.snackMessage = message;
          this.snackTimeout = setTimeout(() => { this.snackbar = false }, 3000)
      },
      compile: async function() {
          if (!this.code) {
              this.snack("Nenhum programa para compilar.")
          } else {
              try {
                  const { error, result } = await compileSemantic(this.code)
                  this.error = ""
                  this.result = ""
                  if (error) {
                      this.error = error.message
                  } else if(result) {
                      this.result = "Programa compilador com sucesso"
                      const file = new File([result], "result.joyc", { type: "text/plain;charset=utf-8"})
                      this.snack("Programa compilado dom sucesso, salvando `result.joyc`")
                      FileSaver.saveAs(file)
                  }
              } catch (err) {
                  this.snack("Houve algum problema ao chamar a api")
              }
          }
      }
  },

    mounted: function() {
        const el = this.$refs['input-file']
        if (el instanceof Element) {
            el.addEventListener('change', async ({ target }: any) => {
                const [file] = target.files
                this.code = await file.text()
            })
        }
    },
  data: (): Data => ({
      code: '',
      status: '',
      message: '',
      error: '',
      result: "",
      snackMessage: '',
      snackbar: false,
      snackTimeout: -1,
  }),
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
        padding: 0;
        border: 1px solid gray;
        min-width: 900px;
        min-height: 20vh;
        overflow: auto;
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
        > :first-child {
            min-height: 70px;
        }
        > :last-child {
            min-height: 15vh;
        }
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
        height: 70vh;
    }
    pre {
        height: 100%;
    }
    code {
        width: 100%;
        height: 100%;
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
