<template>
    <v-simple-table>
        <thead>
            <tr>
                <th class="text-left">Name</th>
                <th class="text-left">Calories</th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="d in data">
                <td> {{ d }} </td>
                <td> {{ d }} </td>
            </tr>
        </tbody>
    </v-simple-table>
</template>
<script>
import Vue from 'vue';

export default Vue.extend({
    name: 'Table',
    props: {
        data: []
    },
});
</script>

