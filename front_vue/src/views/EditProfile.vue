<template>
  <div>
    <v-app id="inspire">
      <v-container>
        <v-card>
          <v-toolbar flat color="primary" dark>
            <v-toolbar-title>User Profile</v-toolbar-title>
          </v-toolbar>
          <v-tabs vertical>
            <v-tab>
              <v-icon left>mdi-account</v-icon>
              프로필 편집
            </v-tab>
            <v-tab>
              <v-icon left>mdi-lock</v-icon>
              비밀번호 변경
            </v-tab>
            <v-tab>
              <v-icon left>mdi-access-point</v-icon>
              기타
            </v-tab>

            <!-- 프로필 편집 -->
            <v-tab-item align="center">
              <v-card flat>
                <v-card-text style="height: 600px;">
                  <v-col cols="12" md="4">

                    <v-avatar>
                      <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="John">
                    </v-avatar>
                    username

                    <!-- 프로필 사진 수정 버튼 -->
                    <div class="text-center">
                      <v-dialog v-model="dialog" width="500">
                        <template v-slot:activator="{ on }">
                          <v-btn color="red lighten-2" dark v-on="on">
                            프로필 사진 변경
                          </v-btn>
                        </template>

                        <v-card>
                          <v-card-title class="headline grey lighten-2" primary-title>
                            프로필 사진 변경 </v-card-title>

                          <v-card-text>
                            <v-list dense>

                              <v-list-item-group v-model="item" color="primary">
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
                                      <v-file-input :rules="rules" accept="image/png, image/jpeg, image/bmp"
                                        placeholder="Pick an avatar" prepend-icon="mdi-camera" label="Avatar">
                                      </v-file-input>
                                    </div>
                                    <v-btn color="primary" text @click="submit">submit</v-btn>
                                    </v-container>
                                  </v-sheet>
                                </v-bottom-sheet>


                                <v-list-item>
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
                            <v-btn color="primary" text @click="dialog = false">
                              cancle
                            </v-btn>
                          </v-card-actions>
                        </v-card>
                      </v-dialog>
                    </div>
                  </v-col>

                  <v-col cols="12" md="4">
                    <v-text-field v-model="firstname" :rules="nameRules" :counter="20" label="First name" required>
                    </v-text-field>
                  </v-col>

                  <v-col cols="12" md="4">
                    <v-text-field v-model="lastname" :rules="nameRules" :counter="10" label="Last name" required>
                    </v-text-field>
                  </v-col>

                  <v-col cols="12" md="4">
                    <v-text-field v-model="email" :rules="emailRules" label="E-mail" required></v-text-field>
                  </v-col>


                  <v-btn class="mr-4" @click="submit">save</v-btn>
                  <v-btn>cancle</v-btn>
                </v-card-text>
              </v-card>
            </v-tab-item>

            <!-- 비밀번호 변경 -->
            <v-tab-item align="center">
              <v-card flat>
                <v-card-text style="height: 600px;">

                  <v-col cols="12" md="4">
                    <v-text-field v-model="pastPw" label="현재 비밀번호" required type="password">
                    </v-text-field>
                  </v-col>

                  <v-col cols="12" md="4">
                    <v-text-field v-model="newPw" :rules="newPwRules" :counter="20" label="새 비밀번호" required
                      type="password">
                    </v-text-field>
                  </v-col>

                  <v-col cols="12" md="4">
                    <v-text-field v-model="checkPw" :rules="checkPwRules" label="새 비밀번호 확인" required type="password">
                    </v-text-field>
                  </v-col>

                  <v-btn class="mr-4" @click="submit">change</v-btn>

                </v-card-text>

              </v-card>
            </v-tab-item>

            <!-- 기타 -->
            <v-tab-item>
              <v-card flat>
                <v-card-text style="height: 600px;">
                  <p>
                    Fusce a quam. Phasellus nec sem in justo pellentesque facilisis. Nam eget dui.
                    Proin viverra, ligula sit amet ultrices semper, ligula arcu tristique sapien, a
                    accumsan nisi mauris ac eros. In dui magna, posuere eget, vestibulum et, tempor
                    auctor, justo.
                  </p>

                  <p class="mb-0">
                    Cras sagittis. Phasellus nec sem in justo pellentesque facilisis. Proin sapien
                    ipsum, porta a, auctor quis, euismod ut, mi. Donec quam felis, ultricies nec,
                    pellentesque eu, pretium quis, sem. Nam at tortor in tellus interdum sagittis.
                  </p>
                </v-card-text>
              </v-card>
            </v-tab-item>
          </v-tabs>
        </v-card>
      </v-container>
    </v-app>
  </div>
</template>

<script>
  export default {
    data: () => ({
      valid: false,
      firstname: '',
      lastname: '',

      // user name 유효성 검사
      nameRules: [
        v => !!v || 'Name is required',
        v => v.length <= 10 || 'Name must be less than 10 characters',
      ],

      // email 유효성 검사
      email: '',
      emailRules: [
        v => !!v || 'E-mail is required',
        v => /.+@.+/.test(v) || 'E-mail must be valid',
      ],

      // 이전 비밀번호
      pastPw: '',

      // 새 비밀번호 pw 유효성 검사 
      newPw: '',
      newPwRules: [
        v => !!v || '비밀번호를 다시 설정해주세요',
        v => /^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/.test(v) || '비밀번호를 다시 설정해주세요',
        v => v.length <= 20 || '20자 이하로 하거라',
      ],

      // 새 비밀번호 확인 
      checkPw: '',
      checkPwRules: [
        // 병학오빠한테 코드가 있겠지?...
        // v => newPw === checkPw || 'ㄴㄴ 다시 입력 ㄱㄱ ',
      ],

      // 프로필 사진 수정 버튼 관련 data
      dialog: false,
      sheet: false,



    }),

  }
</script>

<style scoped>


</style>