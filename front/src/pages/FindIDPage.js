import React from 'react';
import AuthTemplate from '../components/auth/AuthTemplate';
import FindIDForm from '../containers/auth/FindIDForm';

const FindIDPage = () => {
  return (
    <AuthTemplate>
      <FindIDForm />
    </AuthTemplate>
  );
};

export default FindIDPage;
