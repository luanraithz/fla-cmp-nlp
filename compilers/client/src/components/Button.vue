<template>
    <v-col cols="sm">
        <v-btn
            v-on:click="onClick"
            color="primary"
        >
            <v-icon >mdi-{{ icon }}</v-icon>
            {{ label }} {{ shortcut && (control ? `[ctrl-${shortcut}]`: `[${shortcut}]`) }}
        </v-btn>
    </v-col>
</template>
<script lang="ts">
import Vue from 'vue';

export default Vue.extend({
    name: 'Button',
    props: {
        onClick: Function,
        label: String,
        icon: String,
        control: Boolean,
        shortcut: String,
    },
    methods: {
        keyUp(ev: any) {
            const { control, shortcut } = this.$props
            const { key, ctrlKey } = ev
            const hasPressedShortcutKey = shortcut && shortcut.toUpperCase() === key.toUpperCase()

            if (key !== 'Control' && hasPressedShortcutKey) {
                if (control) {
                    ctrlKey && this.$props.onClick()
                    ev.preventDefault()
                } else {
                    this.$props.onClick()
                }
            }
        }
    },
    created: function() {
        document.addEventListener('keydown', this.keyUp)
    },
    destroyed: function() {
        document.removeEventListener('keydown', this.keyUp)
    }
})

</script>

<style scoped>
button {
    width: 100%;
}

</style>
