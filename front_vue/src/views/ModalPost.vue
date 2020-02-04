/* eslint-disable vue/no-unused-vars */

<template>
  <v-row justify="center">
    <v-btn color="primary" dark @click.stop="dialog = true">Open</v-btn>

    <v-dialog v-model="dialog">
      <v-card>
        <v-row>
          <v-col>
            <v-img src="https://cdn.vuetifyjs.com/images/cards/mountain.jpg"></v-img>
          </v-col>

          <v-col>
            <v-list-item>
              <v-list-item-avatar color="grey"></v-list-item-avatar>
              <v-list-item-content>
                <v-list-item-title class="headline">팔로워의 아이디</v-list-item-title>
                <v-list-item-subtitle>개시일</v-list-item-subtitle>
              </v-list-item-content>
            </v-list-item>

            <v-card-text>
              Visit ten places on our planet that are undergoing the biggest changes today.Visit ten
              places on our planet that are undergoing the biggest changes today.Visit ten places on
              our planet that are undergoing the biggest changes today.Visit ten places on our
              planet that are undergoing the biggest changes today.Visit ten places on our planet
              that are undergoing the biggest changes today.Visit ten places on our planet that are
              undergoing the biggest changes today.Visit ten places on our planet that are
              undergoing the biggest changes today.Visit ten places on our planet that are
              undergoing the biggest changes today.
            </v-card-text>

            <v-card-actions>
              <v-btn text color="deep-purple accent-4">Read</v-btn>
              <v-btn text color="deep-purple accent-4">Bookmark</v-btn>
              <v-spacer></v-spacer>
              <v-btn icon>
                <v-icon>mdi-heart</v-icon>
              </v-btn>
              <v-btn icon>
                <v-icon>mdi-share-variant</v-icon>
              </v-btn>
            </v-card-actions>
            <hr />

            <v-list three-line v-for="(item, index) in items" :key="index">
              <v-list-item-avatar>
                <v-img :src="item.avatar"></v-img>
              </v-list-item-avatar>

              <v-list-item-content>
                <v-list-item-title v-html="item.title"></v-list-item-title>
                <v-list-item-subtitle v-html="item.subtitle"></v-list-item-subtitle>
              </v-list-item-content>
            </v-list>
          </v-col>
        </v-row>
      </v-card>
    </v-dialog>
  </v-row>
</template>

<script>
export default {
  name: 'modal',
  data() {
    return {
      dialog: false,
      items: [
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'Brunch this weekend?',
          subtitle:
            "I'll be in your neighborhood doing errands this weekend. Do you want to hang out?",
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'Summer BBQ <span class="grey--text text--lighten-1">4</span>',
          subtitle: " Wish I could come, but I'm out of town this weekend.",
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          title: 'Oui oui',
          subtitle: 'Do you have Paris recommendations? Have you ever been?',
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/4.jpg',
          title: 'Birthday gift',
          subtitle: 'Have any ideas about what we should get Heidi for her birthday?',
        },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
          title: 'Recipe to try',
          subtitle: 'We should eat this: Grate, Squash, Corn, and tomatillo Tacos.',
        },
      ],
    };
  },
};
</script>
<style></style>
