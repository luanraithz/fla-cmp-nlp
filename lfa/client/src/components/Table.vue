<template>
    <v-simple-table>
        <thead>
            <tr>
                <th class="text-left">Linha</th>
                <th class="text-left">Resultado</th>
                <th class="text-left">Sequência</th>
                <th class="text-left">Reconhecimento</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="d in data">
                <td> {{ d.line }} </td>
                <td> {{ d.result }} </td>
                <td> {{ d.content }} </td>
                <td> {{ d.path }} </td>
            </tr>
        </tbody>
    </v-simple-table>
</template>
<script lang="ts">
import Vue from 'vue';
import { FormattedOutput } from '../App.vue';

export default Vue.extend({
    name: 'Table',
    props: {
        data: []
    },
});
</script>

