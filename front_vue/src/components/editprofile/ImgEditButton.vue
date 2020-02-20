<template>
  <div>
    <!-- 프로필 사진 수정 버튼 -->
    <div class="text-center">
      <v-dialog v-model="dialog" width="500">
        <template v-slot:activator="{ on }">
          <v-btn text small style="color: rgb(34, 136, 150);" dark v-on="on">
            프로필 사진 변경
          </v-btn>
        </template>

        <v-card>
          <v-card-title class="headline grey lighten-2" primary-title>
            프로필 사진 변경
          </v-card-title>

          <!-- 프로필사진버튼 리스트 -->
          <v-card-text>
            <v-list dense>
              <v-list-item-group color="primary">
                <v-list-item @click="sheet = !sheet">
                  <v-list-item-icon>
                    <v-icon>mdi-image</v-icon>
                  </v-list-item-icon>
                  <v-list-item-content>
                    <v-list-item-title>사진변경하기</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>

                <!-- 사진 업로드 바텀 부분 -->
                <v-bottom-sheet v-model="sheet" inset>
                  <v-sheet class="text-center" height="200px">
                    <v-btn class="mt-6" text color="red" @click="sheet = !sheet">close</v-btn>

                    <v-container>
                      <div class="">
                        <v-file-input
                          accept="image/png, image/jpeg, image/bmp"
                          placeholder="Pick an avatar"
                          prepend-icon="mdi-camera"
                          label="Avatar"
                          @change="onSave($event)"
                        >
                        </v-file-input>
                      </div>

                      <v-btn color="primary" text @click="onSubmit">submit</v-btn>
                    </v-container>
                  </v-sheet>
                </v-bottom-sheet>

                <v-list-item @click="onClickDelete">
                  <v-list-item-icon>
                    <v-icon>mdi-delete</v-icon>
                  </v-list-item-icon>
                  <v-list-item-content>
                    <v-list-item-title>사진삭제하기</v-list-item-title>
                  </v-list-item-content>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-card-text>

          <v-divider></v-divider>

          <v-card-actions>
            <v-spacer></v-spacer>

            <v-btn style="color: rgb(34, 136, 150);" text @click="dialog = false">
              close
            </v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </div>
  </div>
</template>

<script>
import API from '../../plugins/api';

export default {
  name: 'ImgEditButton',
  data: () => ({
    // 프로필 사진 수정 버튼 관련 data
    dialog: false,
    sheet: false,

    profileFile: '', // 임그루에 담긴 파일
  }),
  methods: {
    onSubmit() {
      const userNickname = sessionStorage.getItem('nickname');
      sessionStorage.setItem('profileFile', this.profileFile);
      console.log(userNickname);
      this.dialog = false;
    },
    onSave(event) {
      console.log(event);
      let fd = new FormData();
      fd.append('image', event);
      for (let key of fd.entries()) {
        console.log(key[0] + ' ' + key[1]);
      }
      API.post('https://api.imgur.com/3/image', fd, {
        headers: {
          // 'Access-Control-Allow-Origin': '*',
          Authorization: 'Client-ID 1001abddfee2596',
        },
      }).then(res => {
        console.log(res);
        console.log(res.data.data.link);
        this.profileFile = res.data.data.link;
        console.log(this.profileFile);
      });
    },
    onClickDelete() {
      console.log('삭제하기');
      const userNickname = sessionStorage.getItem('nickname');

      API.put('auth/user/update/' + userNickname, { img: '' }).then(res => {
        console.log(res.data.img);
      });
      this.dialog = false;
    },
  },
};
</script>

<style scoped></style>
