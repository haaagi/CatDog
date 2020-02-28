import API from '../../plugins/api';
import router from '../../router';
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
  isStart: state => {
    if (state.start === 1) {
      return false;
    } else {
      return true;
    }
  },
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
    state.start = 0;
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
    sessionStorage.removeItem('email');
    sessionStorage.removeItem('nickname');
    router.push('/login');
    alert('로그아웃이 되었습니다');
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
        alert('이메일을 입력하세요');
        // commit('pushError', '이메일을 입력하세요');
        commit('setLoading', false);
      }
      // if (credentials.email)
      if (credentials.password.length < 8) {
        alert('비밀번호는 8자리 이상입니다.');
        // commit('pushError', 'password must be at least 8');
        commit('setLoading', false);
      } else {
        API.post('api/user/signin', credentials)
          .then(res => {
            console.log(res);
            if (res.data.accessToken.length >= 15) {
              console.log(res);
              sessionStorage.setItem('email', res.data.email);
              sessionStorage.setItem('nickname', res.data.nickname);
              console.log(res.data.accessToken);
              commit('setToken', res.data.accessToken);
              commit('setLoading', false);
              commit('isStart');
              router.push('/main');
              alert(
                '로그인이 되었습니다\n 처음 접속하신 분은 검색창에 강아지 종류나 강아지를 검색해 보세요 ^o^',
              );
            } else {
              alert('아이디 또는 패스워드가 틀렸습니다');
              commit('setLoading', false);
              commit('clearErrors');
              router.push('/login');
            }
          })
          .catch(err => {
            if (!err.response) {
              // no reponse
              alert('통신환경이 불안정합니다...');
              commit('pushError', 'Network Error..');
            } else if (err.response.status === 400) {
              alert('유요하지 않은 아이디나 비밀번호 입니다');
            } else if (err.response.status === 500) {
              alert('네트워크 에러입니다');
            } else {
              alert('통신환경이 불안정합니다...');
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
        alert('비밀번호와 비밀번호확인이 일치하지 않습니다.');
        commit('pushError', '비밀번호가 일치하지 않습니다.');
      } // 만약 비밀번호가 8글자 이하일 경우
      else if (userInput.password.length < 8) {
        alert('비밀번호는 8글자 이상으로 설정해주세요.');
      } else {
        API.post('api/user/signup', userInput)
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
    }
  },
};
export default {
  state,
  getters,
  mutations,
  actions,
};
