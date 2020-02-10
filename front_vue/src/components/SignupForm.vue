<template>
  <v-container>
    <v-app>
      <v-row justify="center" align="center">
        <v-card elevation="24" max-width="474" color="#FFEC19" class="px-6 pb-2">
          <v-card-title class="title py-8 pl-10 mb-3">
            <div class="font-weight-thin display-1">
              CatDog SignUp
            </div>
            <v-spacer></v-spacer>
            <v-btn text icon>
              <v-icon>close</v-icon>
            </v-btn>
          </v-card-title>
          <div v-if="isLoading" class="spinner-border" role="status">
            <span class="sr-only">Loading</span>
          </div>
          <v-card-text v-else @submit.prevent="login(credentials)" class="ml-12 pa-0">
            <div v-if="getErrors.length" class="error-list alert alert-danger">
              <p>아래의 오류를 해결해 주세요</p>
              <ul>
                <li v-for="(error, idx) in getErrors" :key="idx">
                  {{ error }}
                </li>
              </ul>
            </div>
            <v-card elevation="24" color="white" width="456" class="pa-6">
              <v-avatar size="100" class="mb-3 mt-n12">
                <img :src="images.sample" />
              </v-avatar>
              <form>
                <v-text-field
                  v-model="userInput.email"
                  :rules="rules.emailRules"
                  label="E-mail"
                  class="mt-4"
                  placeholder="e-mail"
                  required
                ></v-text-field>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      v-model="userInput.password"
                      type="password"
                      label="password"
                      placeholder="비밀번호"
                    ></v-text-field>
                    <v-text-field
                      v-model="userInput.passwordConfirmation"
                      :rules="rules.passwordRules"
                      type="password"
                      label="passwordConfirmation"
                      placeholder="비밀번호 확인"
                    ></v-text-field>
                    <v-text-field
                      v-model="userInput.name"
                      type="text"
                      label="name"
                      placeholder="이름"
                    ></v-text-field>
                    <v-text-field
                      v-model="userInput.phoneNumber"
                      :rules="rules.phoneRules"
                      type="text"
                      label="phoneNumber"
                      placeholder="전화번호"
                    ></v-text-field>
                    <v-text-field
                      v-model="userInput.nickname"
                      type="text"
                      label="nickname"
                      placeholder="별명"
                    ></v-text-field>
                    <!-- 여기서 부터 생일  -->
                    <v-dialog
                      ref="dialog"
                      v-model="modal"
                      :return-value.sync="userInput.birthday"
                      persistent
                      width="290px"
                    >
                      <template v-slot:activator="{ on }">
                        <v-text-field
                          v-model="userInput.birthday"
                          label="생일을 입력해주세요"
                          prepend-icon="event"
                          readonly
                          v-on="on"
                        ></v-text-field>
                      </template>
                      <v-date-picker v-model="userInput.birthday" scrollable>
                        <v-spacer></v-spacer>
                        <v-btn text color="primary" @click="modal = false">취소</v-btn>
                        <v-btn text color="primary" @click="$refs.dialog.save(userInput.birthday)"
                          >OK</v-btn
                        >
                      </v-date-picker>
                    </v-dialog>
                    <!-- 생일 코드 끝 -->
                    <!-- my pet 시작 -->
                    <v-container fluid>
                      <v-combobox
                        v-model="userInput.myPet"
                        :items="items"
                        label="나의 반려견"
                        multiple
                        chips
                      >
                        <template v-slot:selection="data">
                          <v-chip
                            :key="JSON.stringify(data.item)"
                            v-bind="data.attrs"
                            :input-value="data.selected"
                            :disabled="data.disabled"
                            @click:close="data.parent.selectItem(data.item)"
                            @click="addMyPet(item)"
                          >
                            <v-avatar
                              class="accent white--text"
                              left
                              v-text="data.item.slice(0, 1).toUpperCase()"
                            ></v-avatar>
                            {{ data.item }}
                          </v-chip>
                        </template>
                      </v-combobox>
                    </v-container>
                    <!-- my pet 끝 -->
                    <v-container fluid>
                      <v-combobox
                        v-model="userInput.likePet"
                        :items="items"
                        label="관심있는 반려견"
                        multiple
                        chips
                      >
                        <template v-slot:selection="data">
                          <v-chip
                            :key="JSON.stringify(data.item)"
                            v-bind="data.attrs"
                            :input-value="data.selected"
                            :disabled="data.disabled"
                            @click:close="data.parent.selectItem(data.item)"
                            @click="addLikePet(item)"
                          >
                            <v-avatar
                              class="accent white--text"
                              left
                              v-text="data.item.slice(0, 1).toUpperCase()"
                            ></v-avatar>
                            {{ data.item }}
                          </v-chip>
                        </template>
                      </v-combobox>
                    </v-container>
                  </v-col>
                </v-row>
              </form>
            </v-card>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-btn text class="font-weight-bold" large @click.prevent="signup(userInput)"
              >회원가입</v-btn
            >
            <v-btn to="/login" text class="font-weight-bold" large>로그인</v-btn>
          </v-card-actions>
        </v-card>
      </v-row>
    </v-app>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';

export default {
  name: 'SignupForm',
  data() {
    return {
      images: {
        sample: require('../assets/logo.png'),
      },
      modal: false,
      valid: true,
      rules: {
        emailRules: [
          v => !!v || 'E-mail을 입력해주세요',
          v => /.+@.+\..+/.test(v) || '적합한 이메일 형식이 아닙니다.',
        ],
        // passwordRules: [v => v.equals(this.password) || '비밀번호가 일치하지 않습니다.'],
        phoneRules: [
          v => !!v || '핸드폰 번호를 입력해주세요',
          v => /^\d{3}-\d{3,4}-\d{4}$/.test(v) || '적합한 핸드폰 번호 형식이 아닙니다.',
        ],
      },
      items: [
        '포메라니안',
        '스피츠',
        '말티즈',
        '푸들',
        '비숑',
        '슈나우저',
        '진돗개',
        '시츄',
        '요크셔테리어',
        '비글',
        '불독',
      ],
      userInput: {
        email: '',
        password: '',
        passwordConfirmation: '',
        name: '',
        phoneNumber: '',
        nickname: '',
        birthday: new Date().toISOString().substr(0, 10),
        // picture:'',
        myPet: [],
        likePet: [],
      },
    };
  },
  methods: {
    ...mapActions(['signup']),
    addMyPet(pet) {
      this.userInput.myPet.push(pet);
    },
    addLikePet(pet) {
      this.userInput.likePet.push(pet);
    },
  },
  computed: {
    ...mapGetters(['getErrors', 'isLoading']),
    // addPet() {
    //   return () => this.userInput.myPet.push();
    // },
  },
};
</script>

<style></style>
