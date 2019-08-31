<template>
    <div class="wrapper">
        <v-row>
            <div class="index">
                <span v-for="(_, i) in getLines()">
                    {{ i + 1 }}
                </span>
            </div>
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
</template>
<script lang="ts">
import Vue from 'vue';

export default Vue.extend({
    name: 'Textarea',
    props: {
        content: String,
    },
    methods: {
        getLines(): string[] {
            return this.$props.content.split('\n') || 20
        }
    }
})

</script>

<style scoped>
.wrapper {
    width: 100%;
    overflow: hidden;
    padding: 20px;
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
    width: calc(100% - 50px);

}

.content > div {
    padding-top: 0;
    margin-top: 0;
}
textarea {
    width: 100%;
    height: 100%;
    line-height: 17px;
}
</style>

