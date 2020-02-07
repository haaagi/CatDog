<template>
  <v-app>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="600px">
        <template v-slot:activator="{ on }">
          <v-btn color="primary" dark v-on="on">Open Dialog</v-btn>
        </template>
        <v-card>
          <v-card-title>
            <span class="headline">User Profile</span>
          </v-card-title>
          <v-card-text>
            <v-container>
              <v-layout>
                <v-flex>
                  <v-file-input
                    type="file"
                    id="file"
                    show-size
                    accept="image/*"
                    @change="onFileSelected"
                    counter
                    chips
                    multiple
                    label="사진을 올려주세요"
                    ref="imageFile"
                    v-model="imageFile"
                  ></v-file-input>
                </v-flex>
                <v-flex>
                  <v-btn color="primary" text @click="onFileSelected">test</v-btn>
                </v-flex>
              </v-layout>
              <v-row>
                <v-col cols="12">
                  <v-textarea
                    name="input-7-1"
                    filled
                    label="Label"
                    auto-grow
                    value="The Woodman set to work at once, and so sharp was his axe that the tree was soon chopped nearly through."
                  ></v-textarea>
                </v-col>
                <v-col cols="12">
                  <v-text-field label="hashtag" type="text"></v-text-field>
                </v-col>
              </v-row>
            </v-container>
            <small>*indicates required field</small>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
            <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-app>
  <!-- <v-app>
    <v-layout>
      <v-flex>
        <v-file-input
          show-size
          counter
          chips
          multiple
          label="Arquivo Geral"
          ref="myfile"
          v-model="imageFile"
        ></v-file-input>
      </v-flex>
      <v-flex>
        <v-btn color="primary" text @click="submitFiles">test</v-btn>
      </v-flex>
    </v-layout>
  </v-app> -->
</template>

<script>
const axios = require('axios');
export default {
  name: 'Posting',
  data() {
    return {
      dialog: false,
      imageFile: '',
      postingFile: {
        imageName: '',
        content: '',
        hashTag: '',
      },
    };
  },
  methods: {
    onFileSelected() {
      this.imageFile = this.$refs.imageFile.files[0];
      const fd = new FormData();
      fd.append('file', this.imageFile);
      const HOST = process.env.VUE_APP_SERVER_HOST;
      // const hash = sessionStorage.getItem('jwt');
      const user = sessionStorage.getItem('email');
      // const options = {
      //   headers: {
      //     Authorization: 'JWT ' + hash,
      //   },
      // };
      axios.post(HOST + 'api/user/profileimg/' + user, fd).then(res => {
        console.log(res);
      });
    },
  },
};
</script>
