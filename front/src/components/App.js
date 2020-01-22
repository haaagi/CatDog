import React, { Component } from 'react';
import AppTemplate from './AppTemplate';
import TodosContainer from 'containers/TodosContainer';
import CounterContainer from 'containers/CounterContainer';

class App extends Component {
  render() {
    return (
      <AppTemplate counter={<CounterContainer />} todos={<TodosContainer />} />
    );
  }
}

export default App;
