import React, { useState } from "react";
import LockOutlinedIcon from "@material-ui/icons/LockOutlined";
import Typography from "@material-ui/core/Typography";
import { Button, ButtonGroup } from "@material-ui/core";
import FindID from "./FindID";
import FindPWD from "./FindPWD";

const FindAccount = () => {
  const [toggle, setToggle] = useState("FindID");

  const handleClick = e => {
    setToggle(e);
  };

  return (
    <div>
      <LockOutlinedIcon />
      <Typography>Sign up</Typography>

      <ButtonGroup>
        <Button onClick={() => handleClick("FindID")}>아이디 찾기</Button>
        <Button onClick={() => handleClick("FindPWD")}>비밀번호 찾기</Button>
      </ButtonGroup>
      {toggle === "FindID" ? <FindID /> : <FindPWD />}
    </div>
  );
};

export default FindAccount;
