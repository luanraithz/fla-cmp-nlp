<template>
  <v-app>
        <v-container fluid>
            <v-row>
                <v-textarea
                    name="language"
                    >
                </v-textarea>
            </v-row>
            <v-row>
                <v-btn>
                    Action 1
                </v-btn>
                <v-btn>
                    Action 2
                </v-btn>
                <v-btn>
                    Action 3
                </v-btn>
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

export default Vue.extend({
  name: 'App',
  components: {
    HelloWorld,
    Table
  },
  methods: {
    clear: () => {
        this.result = []
        this.content = ""
    }
  },
  data: () => ({
    result: ['hey'],
    content: ''
  }),
});
</script>
