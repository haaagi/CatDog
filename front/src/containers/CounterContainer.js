import React, { Component } from 'react';
import Counter from 'components/Counter';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import * as counterActions from 'store/modules/counter';

class CounterContainer extends Component {
  handleIncrement = () => {
    this.props.increment();
  };

  handleDecrement = () => {
    this.props.decrement();
  };

  render() {
    const { handleIncrement, handleDecrement } = this;
    const { number } = this.props;

    return (
      <Counter
        onIncrement={handleIncrement}
        onDecrement={handleDecrement}
        number={number}
      />
    );
  }
}

// 컴포넌트를 리덕스와 연동 할 떄에는 connect 를 사용합니다.
// connect() 의 결과는, 컴포넌트에 props 를 넣어주는 함수를 반환합니다.
export default connect(
  // props 값으로 넣어 줄 상태를 정의해줍니다.
  state => ({
    number: state.counter.number,
  }),
  // props 값으로 넣어 줄 액션 함수들을 정의해줍니다
  dispatch => bindActionCreators(counterActions, dispatch),
)(CounterContainer);
