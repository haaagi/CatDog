import client from './client';

// 로그인
export const login = ({ username, password }) =>
  client.post('/api/auth/login', { username, password });

// 회원가입
export const register = ({ username, password }) =>
  client.post('/api/auth/register', { username, password });

// 로그인 상태 확인
export const check = () => client.get('/api/auth/check');

//아이디 찾기
export const findID = ({ username, userPhone }) =>
  client.post('api/auth/findID', { username, userPhone });

//비밀번호 찾기
export const findPW = ({ username, userEmail, userPhone }) =>
  client.post('api/auth/findPW', { username, userEmail, userPhone });
