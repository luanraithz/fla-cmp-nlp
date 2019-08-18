<template>
    <div>
        <div
            v-for="d in data"
            >
            {{ d }}
        </div>
    </div>
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

