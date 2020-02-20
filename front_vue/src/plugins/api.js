import axios from 'axios';

export default axios.create({
  //   baseURL: 'http://70.12.247.99:8080',
  baseURL: 'http://13.125.92.171:8080', //회원가입용  url
  //   baseURL : 'http://localhost:8080'
  //   withCredentials: false,
  //   headers: {
  //     Accept: 'application/json',
  //     'Content-Type': 'application/json',
  //     token: window.sessionStorage.getItem('jwt-auth-token'),
  //     user_id: window.sessionStorage.getItem('user_id'),
  //   },
});
