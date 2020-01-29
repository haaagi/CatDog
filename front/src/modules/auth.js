import { createAction, handleActions } from 'redux-actions';
import produce from 'immer';

import { takeLatest } from 'redux-saga/effects';
import createRequestSaga, {
  createRequestActionTypes,
} from '../lib/createRequestSaga';
import * as authAPI from '../lib/api/auth';
import { Map, List } from 'immutable';

const CHANGE_FIELD = 'auth/CHANGE_FIELD';
const INITIALIZE_FORM = 'auth/INITIALIZE_FORM';

const [REGISTER, REGISTER_SUCCESS, REGISTER_FAILURE] = createRequestActionTypes(
  'auth/REGISTER',
);

const [LOGIN, LOGIN_SUCCESS, LOGIN_FAILURE] = createRequestActionTypes(
  'auth/LOGIN',
);

const [FINDID, FINID_SUCCESS, FINDID_FAILURE] = createRequestActionTypes(
  'auth/FINDID',
);

const [FINDPW, FINPW_SUCCESS, FINDPW_FAILURE] = createRequestActionTypes(
  'auth/FINDPW',
);

export const changeField = createAction(
  CHANGE_FIELD,
  ({ form, key, value }) => ({
    form, // register , login
    key, // username, password, passwordConfirm
    value, // 실제 바꾸려는 값
  }),
);
export const initializeForm = createAction(INITIALIZE_FORM, form => form); // register / login
export const register = createAction(REGISTER, ({ username, password }) => ({
  username,
  password,
}));
export const login = createAction(LOGIN, ({ username, password }) => ({
  username,
  password,
}));
export const findID = createAction(FINDID, ({ username, userPhone }) => ({
  username,
  userPhone,
}));
export const findPW = createAction(
  FINDPW,
  ({ username, userEmail, userPhone }) => ({
    username,
    userEmail,
    userPhone,
  }),
);

// saga 생성
const registerSaga = createRequestSaga(REGISTER, authAPI.register);
const loginSaga = createRequestSaga(LOGIN, authAPI.login);
const findIDSaga = createRequestSaga(FINDID, authAPI.findID);
const findPWSaga = createRequestSaga(FINDID, authAPI.findPW);

export function* authSaga() {
  yield takeLatest(REGISTER, registerSaga);
  yield takeLatest(LOGIN, loginSaga);
  yield takeLatest(FINDID, findIDSaga);
  yield takeLatest(FINDPW, findPWSaga);
}

const initialState = {
  register: {
    username: '',
    password: '',
    passwordConfirm: '',
<<<<<<< HEAD
=======
    likeDogCat: List([]),
    myDogCat: List([]),
>>>>>>> 0bba6194ddf3f8ae1895d02de77583f2d5d47ca3
  },
  login: {
    username: '',
    password: '',
  },
  findID: {
    username: '',
    userPhone: '',
  },
  findPW: {
    username: '',
    userEmail: '',
    userPhone: '',
  },
  auth: null,
  authError: null,
};

const auth = handleActions(
  {
    [CHANGE_FIELD]: (state, { payload: { form, key, value } }) =>
      produce(state, draft => {
        draft[form][key] = value; // 예: state.register.username을 바꾼다
      }),
    [INITIALIZE_FORM]: (state, { payload: form }) => ({
      ...state,  
      [form]: initialState[form],
      authError: null, // 폼 전환 시 회원 인증 에러 초기화
    }),
    // 회원가입 성공
    [REGISTER_SUCCESS]: (state, { payload: auth }) => ({
      ...state,
      authError: null,
      auth,
    }),
    // 회원가입 실패
    [REGISTER_FAILURE]: (state, { payload: error }) => ({
      ...state,
      authError: error,
    }),
    // 로그인 성공
    [LOGIN_SUCCESS]: (state, { payload: auth }) => ({
      ...state,
      authError: null,
      auth,
    }),
    // 로그인 실패
    [LOGIN_FAILURE]: (state, { payload: error }) => ({
      ...state,
      authError: error,
    }),
  },
  initialState,
);

export default auth;