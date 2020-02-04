<template>
  <v-app>
    <v-app-bar app clipped-left hide-on-scroll color="amber">
      <v-app-bar-nav-icon @click="drawer = !drawer" />
      <v-toolbar-title style="width: 300px; padding-left: 0px;">
        <v-img src="../../assets/logo2.png" alt="logo" max-height="50" contain />
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
  </v-app>
</template>

<script>
export default {
  name: 'Header',
  data: () => ({
    drawer: null,
  }),
};
</script>

<style scoped></style>
