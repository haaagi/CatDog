<template>
  <div>
    <v-card-text style="height: 600px;">
      <v-col cols="12" md="4">
        <v-avatar size="80">
          <img src="https://cdn.vuetifyjs.com/images/john.jpg" alt="nickname" />
        </v-avatar>

        <p class="font-weight-black title" style="margin-bottom: 1px; margin-top: 5px;">
          Nick Name
        </p>
        <ImgEditButton />
      </v-col>

      <v-layout row wrap justify-center>
        <v-flex lg5>
          <v-col>
            <v-text-field
              v-model="nickname"
              :rules="nameRules"
              :counter="20"
              label="Nick Name"
              required
            >
            </v-text-field>
          </v-col>

          <v-col>
            <v-text-field v-model="phoneNumber" :rules="phoneRules" label="Phone Number" required>
            </v-text-field>
          </v-col>
        </v-flex>

        <v-flex lg5>
          <v-col>
            <v-container fluid>
              <v-row>
                <v-col cols="12">
                  <v-combobox
                    v-model="likePet"
                    :items="dogs"
                    label="내가 좋아하는 강아지"
                    multiple
                    chips
                  ></v-combobox>
                </v-col>
              </v-row>
            </v-container>
          </v-col>

          <v-col>
            <v-container fluid>
              <v-row>
                <v-col cols="12">
                  <v-combobox
                    v-model="myPet"
                    :items="dogs"
                    label="내가 키우는 강아지"
                    multiple
                    chips
                  ></v-combobox>
                </v-col>
              </v-row>
            </v-container>
          </v-col>
        </v-flex>
      </v-layout>

      <v-btn class="mr-4" :click="save">save</v-btn>
      <v-btn>cancle</v-btn>
    </v-card-text>
  </div>
</template>

<script>
import ImgEditButton from './ImgEditButton';

export default {
  name: 'EditDetail',
  components: {
    ImgEditButton,
  },
  data: () => ({
    nickname: '',
    // nick name 유효성 검사
    nameRules: [
      v => !!v || 'Name is required',
      v => v.length <= 10 || 'Name must be less than 10 characters',
    ],

    img: '',

    phoneNumber: '',
    // phone number 유효성 검사
    phoneRules: [
      v => !!v || '핸드폰 번호를 입력해주세요',
      v => /^\d{3}-\d{3,4}-\d{4}$/.test(v) || '적합한 핸드폰 번호 형식이 아닙니다.',
    ],

    // email 유효성 검사
    // email: '',
    // emailRules: [v => !!v || 'E-mail is required', v => /.+@.+/.test(v) || 'E-mail must be valid'],

    likePet: ['푸들'],
    myPet: ['포메라니안'],
    dogs: ['시츄', '말티즈', '포메라니안', '푸들'],
  }),
  methods: {},
};
</script>

<style scoped></style>
