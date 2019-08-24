<template>
    <div class="table">
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
                    <td :title="d.line"> {{ d.line }} </td>
                    <td :title="d.result"> {{ d.result }} </td>
                    <td :title="d.content"> {{ d.content }} </td>
                    <td :title="d.path"> {{ d.path }} </td>
                </tr>
            </tbody>
        </v-simple-table>
    </div>
</template>
<script lang="ts">
import Vue from 'vue';
import { Prop } from 'vue/types/options';
import { FormattedOutput } from '../App.vue';

type Props = { data: FormattedOutput[] }

export default Vue.extend({
    name: 'Table',
    props: {
        data: Array as Prop<FormattedOutput[]>
    }
});
</script>
<style>
    .table {
        max-height: 55vh;
        overflow: auto;
    }
</style>
