import axios from 'axios';

const client = axios.create();


  
  
  // API 주소를 다른 곳으로 사용함
  client.defaults.baseURL = 'http://70.12.247.57:8080/';

  // 헤더 설정
  // client.defaults.headers.common['Authorization'] = 'Bearer token-value';

  // 인터셉터 설정
  axios.interceptors.request.use(function (config) {
    // 요청 전에 로딩 오버레이 띄우기
    return config;
  }, function (error) {
    // 에라 나면 로딩 끄기
    return Promise.reject(error);
  });
// 응답 인터셉터
axios.interceptors.response.use(function (response) {
    console.log('success');
    return response;
  }, function (error) {
    // 응답 에러 시에도 로딩 끄기
    return Promise.reject(error);
  });


export default client;
