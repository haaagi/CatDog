<template>
  <!-- <div class="card">
  <h5 class="card-header">{{ getuserinfo.username }}</h5>
  <div class="card-body">
    <div class="card-text">
      <p>나이 : {{ getuserinfo.age }} </p>
      <p>성별 : {{getuserinfo.age}}</p>
      <p>KAKAO ID : {{ getuserinfo.kakao_id }}</p>

    </div>

  </div>
</div> -->

<div>

<div class="card1">
  <!-- <img src="http://www.pngmart.com/files/5/Pyramids-PNG-HD.png" alt=""> -->
  
  <div class="textin">
    <h3>{{ getuserinfo.username }}</h3>
  <p>나이 : {{ getuserinfo.age }} </p>
      <p>성별 : {{getuserinfo.age}}</p>
      <p>KAKAO ID : {{ getuserinfo.kakao_id }}</p>

    </div>
</div>

<div class="footer">
</div>
</div>


</template>

<script>
  import {
    mapGetters
  } from 'vuex';
  export default {
    name: 'UserDetail',
    computed: {
      ...mapGetters(['getuserinfo'])
    }
  }
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css?family=Exo:700');
@import url('https://fonts.googleapis.com/css?family=Abel');
body {
  background-color: #dfe6e9;
  -webkit-transform: perspective(900px);
  -webkit-transform-style: preserve-3d;
}
.title{
  width: 100%;
  text-align: center;
}
.title h1{
  font-size:50px;
  font-family: 'Exo', sans-serif;
}
.card1 {
  text-align:center;
  position: absolute;
  left: 100px;
  width: 600px;
  height: 350px;
  margin-top: 10px;
  margin-bottom: 10px;
  background: linear-gradient(rgb(225,150,58),rgb(227,144,91));
  transition:.6s;
  
  transform: rotatex(75deg) translatey(-200px) translatez(-100px);
  box-shadow: 0px 20px 60px rgba(0,0,0, 0.5);
}
.card1:hover{
  transform: rotatex(0deg);
  transform: rotatez(0deg);
  transition:.6s;
  box-shadow: 0px 0px 20px rgba(0, 0, 0, 0.3);
}
.card1 img{
  transform: translateY(15px);
  width:200px;
  height:120px;
}
h3{
  font-size: 40px;
  font-family: 'Abel', sans-serif;
  color:rgb(255,255,255);
  text-shadow: 0 0 2px rgb(255,255,255);
  transform: translatey(10px);
}

p{
   font-size: 20px;
  font-family: 'Abel', sans-serif;
  color: white;
  text-align:center;
  width:220px;
  transform: translatex(12px);
}



.footer{
  position: absolute;
  top: 500px;
  marging: 10px;
  width: 100%;
   text-align: center;
}
.footer h2{
  font-size:18px;
  font-family: 'Exo', sans-serif;
  
}
.textin {
  padding-top: 10ex;
}
</style>