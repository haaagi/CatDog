<template>
  <v-app id="keep">
    <v-app-bar app clipped-left hide-on-scroll color="amber">
      <v-app-bar-nav-icon @click="drawer = !drawer" />
      <v-toolbar-title style="width: 300px; padding-left: 0px;">
        <v-img src="../assets/logo2.png" alt="logo" max-height="50" contain />
      </v-toolbar-title>
      <v-spacer /><v-spacer />
      <v-text-field
        style="width: 300px;"
        placeholder="Search..."
        single-line
        color="black"
        prepend-inner-icon="search"
        hide-details
      ></v-text-field>
      <v-spacer />
      <v-btn icon> <v-icon>apps</v-icon> </v-btn>
      <v-btn icon> <v-icon>chat_bubble</v-icon> </v-btn>
      <v-btn icon> <v-icon>mdi-bell</v-icon> </v-btn>
      <v-btn icon> <v-icon>mdi-login</v-icon> </v-btn>
      <v-btn icon> <v-icon>mdi-logout</v-icon> </v-btn>
    </v-app-bar>

    <v-navigation-drawer v-model="drawer" app clipped color="grey lighten-4">
      <v-list dense class="grey lighten-4">
        <template v-for="(item, i) in items">
          <v-row v-if="item.heading" :key="i" align="center">
            <v-col cols="6">
              <v-subheader v-if="item.heading">
                {{ item.heading }}
              </v-subheader>
            </v-col>
            <v-col cols="6" class="text-right">
              <v-btn small text>edit</v-btn>
            </v-col>
          </v-row>
          <v-divider v-else-if="item.divider" :key="i" dark class="my-4" />
          <v-list-item v-else :key="i" link>
            <v-list-item-action>
              <v-icon>{{ item.icon }}</v-icon>
            </v-list-item-action>
            <v-list-item-content>
              <v-list-item-title class="grey--text">
                {{ item.text }}
              </v-list-item-title>
            </v-list-item-content>
          </v-list-item>
        </template>
      </v-list>
    </v-navigation-drawer>

    <v-content style="height:2000px">
      <h1>main</h1>
    </v-content>
    <v-content>
      <v-footer absolute color="amber" class="white--text">
        <v-card flat tile class="amber lighten-1 white--text text-center">
          <v-card-text class="white--text pt-0">
            Phasellus feugiat arcu sapien, et iaculis ipsum elementum sit amet. Mauris cursus
            commodo interdum. Praesent ut risus eget metus luctus accumsan id ultrices nunc. Sed at
            orci sed massa consectetur dignissim a sit amet dui. Duis commodo vitae velit et
            faucibus. Morbi vehicula lacinia malesuada. Nulla placerat augue vel ipsum ultrices,
            cursus iaculis dui sollicitudin. Vestibulum eu ipsum vel diam elementum tempor vel ut
            orci. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus
            mus.
          </v-card-text>

          <v-divider />

          <v-card-text class="white--text">
            <strong>Vuetify</strong>
          </v-card-text>
        </v-card>
      </v-footer>
    </v-content>
  </v-app>
</template>

<script>
import board from '../views/Board.vue';
export default {
  props: {
    source: String,
  },
  data: () => ({
    drawer: null,
    items: [
      { icon: 'lightbulb_outline', text: 'Notes' },
      { icon: 'touch_app', text: 'Reminders' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'add', text: 'Create new label' },
      { divider: true },
      { icon: 'archive', text: 'Archive' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' },
      { icon: 'phonelink', text: 'App downloads' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' },
      { icon: 'phonelink', text: 'App downloads' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' },
      { icon: 'phonelink', text: 'App downloads' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' },
      { icon: 'phonelink', text: 'App downloads' },
      { icon: 'delete', text: 'Trash' },
      { divider: true },
      { heading: 'Labels' },
      { icon: 'settings', text: 'Settings' },
      { icon: 'chat_bubble', text: 'Trash' },
      { icon: 'help', text: 'Help' },
      { icon: 'phonelink', text: 'App downloads' },
      { icon: 'keyboard', text: 'Keyboard shortcuts' },
    ],
  }),
  comments: { board },
};
</script>

<style>
#keep .v-navigation-drawer__border {
  display: none;
}
</style>
