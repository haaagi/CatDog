<template>
  <div id="app">
    <v-app id="inspire">
      <v-navigation-drawer stateless value="true" dark :mini-variant.sync="mini">
        <v-list>
          <v-list-title>
            <v-img
              src="https://app.hello-vi.com/hello/images/top_logo.png"
              height="60"
              contain
            ></v-img>
          </v-list-title>
          <v-list-title>
            <v-list-tile-action>
              <v-btn icon @click.stop="mini = !mini">
                <v-icon v-on:click="hidden = isHidden">chevron_left</v-icon>
              </v-btn>
            </v-list-tile-action>
          </v-list-title>

          <br />
          <v-list-tile>
            <v-list-tile-action>
              <v-icon>home</v-icon>
            </v-list-tile-action>
            <v-list-tile-title>Home</v-list-tile-title>
          </v-list-tile>
          <v-list-group dark prepend-icon="folder" :value="false" mini>
            <template v-slot:activator>
              <v-list-tile>
                <v-list-tile-title>Projects</v-list-tile-title>
              </v-list-tile>
            </template>
            <v-list-group no-action sub-group value="true">
              <template v-slot:activator>
                <v-list-tile>
                  <v-list-tile-title>Personal</v-list-tile-title>
                </v-list-tile>
              </template>
              <v-list-tile v-for="(personal, i) in personal" :key="i">
                <v-list-tile-title v-text="personal[0]"></v-list-tile-title>
              </v-list-tile>
            </v-list-group>
            <v-list-group no-action sub-group value="true">
              <template v-slot:activator>
                <v-list-tile>
                  <v-list-tile-title>Client</v-list-tile-title>
                </v-list-tile>
              </template>
              <v-list-title v-for="(clients, i) in clients" :key="i">
                <v-list-tile-title v-text="clients[0]"></v-list-tile-title>
              </v-list-title>
            </v-list-group>
          </v-list-group>

          <v-list-tile>
            <v-list-tile-action>
              <v-icon>share</v-icon>
            </v-list-tile-action>
            <v-list-tile-title>Share</v-list-tile-title>
          </v-list-tile>
          <v-list-tile>
            <v-list-tile-action>
              <v-icon>list</v-icon>
            </v-list-tile-action>
            <v-list-tile-title>Comment History</v-list-tile-title>
          </v-list-tile>
        </v-list>
      </v-navigation-drawer>
    </v-app>
  </div>
</template>
<script>
export default {
  name: 'Banner2',
  data() {
    return {
      mini: false,
      personal: [
        ['Project Alpha 01'],
        ['Project Beta 02'],
        ['ui testing'],
        ['project testing name'],
      ],
      clients: [['client Project 1'], ['Noise'], ['Rokesuta']],
    };
  },
};
</script>
<style scoped>
.v-list__group__header .v-list__group__header__prepend-icon {
  color: #9b3685 !important;
}
</style>
