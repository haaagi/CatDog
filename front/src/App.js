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


export default App;
