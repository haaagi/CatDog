<template>
  <v-container>
    <v-app>
      <v-row justify="center" align="center">
        <v-card elevation="24" max-width="474" color="#FFEC19" class="px-6 pb-2">
          <v-card-title class="title py-8 pl-10 mb-3">
            <div class="font-weight-thin display-1">
              CatDog Login
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
                  v-model="email"
                  :rules="emailRules"
                  label="E-mail"
                  class="mt-4"
                  placeholder="e-mail"
                  required
                ></v-text-field>
                <v-row>
                  <v-col cols="12">
                    <v-text-field
                      v-model="credentials.password"
                      type="password"
                      label="password"
                      placeholder="password"
                    ></v-text-field>
                  </v-col>
                </v-row>
              </form>
            </v-card>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <v-spacer></v-spacer>
            <v-btn text class="font-weight-bold" large @click.prevent="login(credentials)"
              >로그인</v-btn
            >
            <v-btn to="/signup" text class="font-weight-bold" large>회원가입</v-btn>
          </v-card-actions>
        </v-card>
      </v-row>
    </v-app>
  </v-container>
</template>

<script>
import { mapGetters, mapActions } from 'vuex';
export default {
  name: 'LoginForm',

  data() {
    return {
      images: {
        sample: require('../assets/logo.png'),
      },
      valid: true,
      emailRules: [
        v => !!v || 'E-mail을 입력해주세요',
        v => /.+@.+\..+/.test(v) || '적합한 이메일 형식이 아닙니다.',
      ],
      credentials: {
        email: '',
        password: '',
      },
    };
  },

  methods: {
    ...mapActions(['login']),
    // submit () {
    //   this.$validator.validateAll()
    // },
    validate() {
      if (this.$refs.form.validate()) {
        this.snackbar = true;
      }
    },
    clear() {
      this.email = '';
      this.password = '';
    },
  },
  computed: {
    ...mapGetters(['getErrors', 'isLoading']),
  },
};
</script>

<style scoped></style>
