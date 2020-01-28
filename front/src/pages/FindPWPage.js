import React from 'react';
import AuthTemplate from '../components/auth/AuthTemplate';
import FindPWForm from '../containers/auth/FindPWForm';

const FindIDPage = () => {
  return (
    <AuthTemplate>
      <FindPWForm />
    </AuthTemplate>
  );
};

export default FindIDPage;
