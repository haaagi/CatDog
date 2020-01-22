<<<<<<< HEAD
import React from 'react';
import { Route } from 'react-router-dom';
import PostListPage from './pages/PostListPage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import WritePage from './pages/WritePage';
import PostPage from './pages/PostPage';

const App = () => {
  return (
    <>
      <Route component={PostListPage} path={['/@:username', '/']} exact />
      <Route component={LoginPage} path="/login" />
      <Route component={RegisterPage} path="/register" />
      <Route component={WritePage} path="/write" />
      <Route component={PostPage} path="/@:username/:postId" />
    </>
  );
};
=======
import React, {Component} from 'react';

import { Route, Router } from 'react-router-dom';
import { Home, SignIn } from './pages';

import Dashboard from './components/Dashboard'

import Container from '@material-ui/core/Container';

const App = () => {
  return (
    <div>
        <Dashboard />


        <Container component="main" maxWidth="xs">
          <Route exact path="/" component={Home}/>
          <Route exact path="/signin" component={SignIn} />
        </Container>

    </div>
  )
}


>>>>>>> develop
export default App;
