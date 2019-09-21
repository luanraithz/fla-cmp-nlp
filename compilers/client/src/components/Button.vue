<template>
    <v-col cols="sm">
        <v-btn
            v-on:click="onClick"
            color="primary"
            :height="70"
        >
            <div>
                <v-icon >mdi-{{ icon }}</v-icon>
                <span>{{ label }} {{ shortcut && (control ? `[ctrl-${shortcut}]`: `[${shortcut}]`) }}</span>
            </div>
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
                } else if (key === "F9" ){
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
    box-sizing: border-box;
    padding: 0 5px !important;
}
span {
    font-size: 9px;
}
div {
    width: 100%;
    height: 100%;
    padding: 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}

</style>
