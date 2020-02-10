const axios = require('axios');
import router from '../../router';
const HOST = process.env.VUE_APP_SERVER_HOST;
const state = {
  token: null,
  errors: [],
  loading: false,
  userinfo: [],
  start: 0,
};
const getters = {
  isLoggedIn: state => {
    if (state.token === null) {
      return false;
    } else {
      return true;
    }
  },
  getErrors: state => state.errors,
  isLoading: state => state.loading,
  getuserinfo: state => state.userinfo,
  isStart: state => state.start,
  // isStart: () => {
  //   if (!localStorage.getItem('start')) {
  //     return 0;
  //   } else {
  //     return 1;
  //   }
  // },
  // isStart: state => {
  //   if (state.start === false) {
  //     return false;
  //   } else {
  //     return true;
  //   }
  // },
};
const mutations = {
  setLoading: (state, flag) => (state.loading = flag),
  setToken: (state, token) => {
    state.token = token;
    sessionStorage.setItem('jwt', token);
  },
  isToken: state => {
    state.token = sessionStorage.getItem('jwt');
  },
  pushError: (state, error) => state.errors.push(error),
  setuserinfo: (state, info) => (state.userinfo = info),
  clearErrors: state => (state.errors = []),
  setStart: state => {
    state.start = localStorage.getItem('start');
  },
};
const actions = {
  callStart: ({ commit }) => {
    localStorage.setItem('start', true);
    commit('setStart');
  },
  chkStart: ({ commit }) => {
    commit('setStart');
  },
  chkLogin: ({ commit }) => {
    commit('isToken');
  },
  logout: ({ commit }) => {
    commit('setToken', null);
    sessionStorage.removeItem('jwt');
    router.push('/login');
  },
  pushError: ({ commit }, error) => {
    commit('pushError', error);
  },
  login: ({ commit, getters }, credentials) => {
    if (getters.isLoggedIn) {
      router.push('/main');
    } else {
      // 로그인이 안됐다면
      commit('clearErrors');
      commit('setLoading', true);
      if (!credentials.email) {
        commit('pushError', '이메일을 입력하세요');
        commit('setLoading', false);
      }
      // if (credentials.email)
      if (credentials.password.length < 8) {
        commit('pushError', 'password must be at least 8');
        commit('setLoading', false);
      } else {
        axios
          .post(HOST + 'api/user/signin', credentials)
          .then(res => {
            console.log(res);
            sessionStorage.setItem('email', res.data.email);
            sessionStorage.setItem('nickname', res.data.nickname);
            console.log(res.data.accessToken);
            commit('setToken', res.data.accessToken);
            commit('setLoading', false);
            router.push('/main');
          })
          // .then(token => {
          // const hash = sessionStorage.getItem('jwt');
          // const options = {
          //   headers: {
          //     Authorization: 'JWT ' + hash,
          //   },
          // };
          // axios.post(HOST + 'api/accounts/userinfo/', null, options).then(res => {
          //   console.log(res);
          //   commit('setuserinfo', res.data);
          // });
          // })
          .catch(err => {
            if (!err.response) {
              // no reponse
              commit('pushError', 'Network Error..');
            } else if (err.response.status === 400) {
              commit('pushError', 'Invalid username or password');
            } else if (err.response.status === 500) {
              commit('pushError', 'Internal Server error. Please try again later');
            } else {
              commit('pushError', 'Some error occured');
            }
            commit('setLoading', false);
          });
      }
    }
  },
  initialLogin: ({ commit }) => {
    const token = sessionStorage.getItem('jwt');
    if (token) {
      commit('setToken', token);
    }
  },
  signup: ({ commit, getters, dispatch }, userInput) => {
    if (getters.isLoggedIn) {
      router.push('/home');
    } else {
      if (userInput.passwordConfirmation != userInput.password) {
        commit('pushError', '비밀번호가 일치하지 않습니다.');
      }
      axios
        .post(HOST + 'api/v1/user', userInput)
        .then(res => {
          console.log(res);
          if (res.status === 200) {
            const credentials = {
              email: userInput.email,
              password: userInput.password,
            };
            dispatch('login', credentials);
          } else {
            router.push('/signup');
          }
        })
        .catch(err => {
          commit('pushError', err.response);
        });
    }
  },
};
export default {
  state,
  getters,
  mutations,
  actions,
};
