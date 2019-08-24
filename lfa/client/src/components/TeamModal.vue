<template>
</template>
<script lang="ts">
import Vue from 'vue';
import { FormattedOutput } from '../App.vue';

export default Vue.extend({
    name: 'TeamModal',
    props: {
        data: []
    },
    data: (): { visible: boolean } => ({
        visible: false
    })
});
</script>

