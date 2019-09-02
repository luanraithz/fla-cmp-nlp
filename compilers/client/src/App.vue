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
                <Textarea :content="content">
                </Textarea>
            </v-row>
            <v-row>
                <div class="output">
                    Some
                    awesome
                    output
                </div>
            </v-row>
            <v-row>
                <div class="status">
                    Some cool status
                </div>
            </v-row>
      </v-container>
  </v-app>
</template>


<script lang="ts">

import Vue from 'vue';
import TeamModal from './components/TeamModal.vue';
import Button from './components/Button.vue';
import Textarea from './components/Textarea.vue';

export default Vue.extend({
  name: 'App',
  components: {
      TeamModal,
      Button,
      Textarea
  },
  methods: {
      newFile: () => { console.log('new file') },
      openFile: function() {
          this.$refs['input-file'].click()
      },
      saveFile: () => { console.log('save file') },
      copySelection: () => { console.log('copy file') },
      pasteContent: () => { console.log('paste file') },
      cutSelection: () => { console.log('cut file') },
      compile: () => {
          console.log('compiling')
      }
  },

    mounted: function() {
        this.$refs['input-file'].addEventListener('change', async ({ target }) => {
            const [file] = target.files
            this.content = await file.text()
        })
    },
  data: (): { content: string } => ({ content: '' }),
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

</style>
