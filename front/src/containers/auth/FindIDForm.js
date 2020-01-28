import React, { useEffect, useState } from 'react';
import { useSelector, useDispatch } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { changeField, initializeForm, findID } from '../../modules/auth';
import FindAccountForm from '../../components/auth/FindAccountForm';
import { check } from '../../modules/user';

const FindIDForm = ({ history }) => {
  const [error, setError] = useState(null);
  const dispatch = useDispatch();
  const { form, auth, authError, user } = useSelector(({ auth, user }) => ({
    form: auth.findID,
    auth: auth.auth,
    authError: auth.authError,
    user: user.user,
  }));
  // 인풋 변경 이벤트 핸들러
  const onChange = e => {
    const { value, name } = e.target;
    dispatch(
      changeField({
        form: 'findID',
        key: name,
        value,
      }),
    );
  };

  // 폼 등록 이벤트 핸들러
  const onSubmit = e => {
    e.preventDefault();
    const { username, userPhone } = form;
    // 하나라도 비어있다면
    if ([username, userPhone].includes('')) {
      setError('빈 칸을 모두 입력하세요.');
      return;
    }
    dispatch(findID({ username, userPhone }));
  };

  // 컴포넌트가 처음 렌더링 될 때 form 을 초기화함
  useEffect(() => {
    dispatch(initializeForm('findID'));
  }, [dispatch]);

  // 아이디찾기 성공 / 실패 처리
  useEffect(() => {
    if (authError) {
      // 기타 이유
      setError('회원가입 실패');
      return;
    }

    if (auth) {
      console.log('회원가입 성공');
      console.log(auth);
      dispatch(check());
    }
  }, [auth, authError, dispatch]);

  /*  // user 값이 잘 설정되었는지 확인
  useEffect(() => {
    if (user) {
      console.log('check API 성공');
      console.log(user);
     history.push('/'); // 홈 화면으로 이동
    }
  }, [history, user]); */

  return (
    <FindAccountForm
      type="findID"
      form={form}
      onChange={onChange}
      onSubmit={onSubmit}
      error={error}
    />
  );
};

export default withRouter(FindIDForm);
