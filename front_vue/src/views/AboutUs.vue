<template>
  <v-container>
    <v-layout>
      <div class="container-fluid box">
        <div class="row ux">
          <div class="col-12 top">
            <h1>Cat & Dog</h1>
            <p>
              우리들의 반려견을 위한 SNS <br /><br />
              #강아지 #좋아요 #선팔하면맞팔
            </p>
          </div>

          <div class="d-flex justify-content-center" style="width:20%">
            <h3>Hyuk Jin</h3>
            <h3>Kwon</h3>
          </div>

          <div class="d-flex justify-content-center" style="width:20%">
            <h3>daehee</h3>
            <h3>Lim</h3>
          </div>
          <div class="d-flex justify-content-center" style="width:20%">
            <h3 class="cm">ByeongHak</h3>
            <h3>Jeong</h3>
          </div>
          <div class=" d-flex justify-content-center" style="width:20%">
            <h3 class="wd">kiyeon</h3>
            <h3>Han</h3>
          </div>
          <div class="d-flex justify-content-center" style="width:20%">
            <h3 class="gd">sukyung</h3>
            <h3>Hong</h3>
          </div>
        </div>
        <div class="row full-screen no-gutters">
          <div class="hover-screen" style="width:20%;">
            <h2 style="margin-left: -70px;">Backend</h2>
          </div>

          <div class=" hover-screen" style="width:20%">
            <h2 style="margin-left: -70px;">Backend</h2>
          </div>
          <div class="hover-screen" style="width:20%">
            <h2 style="margin-left: -70px;">
              Frontend
            </h2>
          </div>
          <div class="hover-screen" style="width:20%">
            <h2 style="margin-left: -70px;">
              Frontend
            </h2>
          </div>

          <div class="hover-screen" style="width:20%">
            <h2 style="margin-left: -70px;">Frontend</h2>
          </div>
        </div>
        <div
          class="row no-gutters team-inf d-flex justify-content-center align-items-center"
          id="team-inf"
        >
          <span class="close-icon" id="close">X</span>
          <div class="col-5 img-container">
            <img
              src="https://images.pexels.com/photos/888956/pexels-photo-888956.jpeg?auto=compress&cs=tinysrgb&h=750&w=1260"
              alt=""
              class="img-fluid"
            />
          </div>
          <div class="col-4 team-inf-container">
            <h3>AYAKKABI</h3>
            <p class="text">
              Lorem ipsum dolor sit amet consectetur, adipisicing elit. Saepe, commodi.
            </p>
          </div>
        </div>
      </div>
    </v-layout>
  </v-container>
</template>

<script>
export default {};
</script>
<style scoped>
/* @import url('https://fonts.googleapis.com/css?family=Lato:300,400|Poppins:300,400,800&display=swap'); */

a {
  color: white;
}

.box {
  padding: 0;
  position: relative;
  background-size: 400% 400%;
  background-image: linear-gradient(-45deg, #eff3f5, #ffc107, #b9d47a, #a8d673, #ffa000);
  transition-duration: 0.5s;
  animation: Gradient 5s ease infinite;
  opacity: 0.9;
  overflow: hidden;
  /* font-family: 'Poppins'; */
}
@keyframes Gradient {
  0% {
    background-position: 0% 50%;
  }
  50% {
    background-position: 100% 50%;
  }
  100% {
    background-position: 0% 50%;
  }
}
.ux {
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 5;
}
.top {
  padding: 5rem 10rem;
  box-sizing: border-box;
}
.img-icon {
  width: 100px;
  height: 100px;
}
h1,
h2,
p,
h3 {
  color: white;
}
h1 {
  font-size: 5rem;
  text-transform: uppercase;
}
p {
  font-size: 1.5rem;
  padding-left: 1rem;
  opacity: 0.6;
}
h2 {
  font-size: 0.8rem;
}
h3 {
  text-align: center;

  text-transform: uppercase;
}
.full-screen {
  height: 100vh;
  position: relative;
}
.hover-screen {
  cursor: pointer;
  opacity: 0;
  transition-duration: 0.5s;
  position: relative;
  z-index: 10;
}
.hover-screen > h2 {
  color: white(26, 25, 25);
  font-size: 5rem;
  transform: rotateZ(-90deg) translateX(-150%) translateY(-25%);
  transition-duration: 0.6s;
}
.hover-screen:hover {
  opacity: 1;
}
.hover-screen:hover > h2 {
  transform: rotateZ(-90deg) translateX(-50%) translateY(-25%);
}
.hover-screen:nth-child(1) {
  background: url(https://i.imgur.com/LmHQBn0.jpg);
  background-size: cover;
  background-position: 85% 0;
}
.hover-screen:nth-child(2) {
  background: url(https://i.imgur.com/o0RI65O.jpg);
  background-size: cover;
  background-position: 50% 0;
}
.hover-screen:nth-child(3) {
  background: url(https://i.imgur.com/e980vFx.jpg);
  background-size: cover;
  background-repeat: no-repeat;
  background-position: 50% 0;
}

.hover-screen:nth-child(4) {
  background: url(https://i.imgur.com/8UENLu3.jpg);
  background-size: cover;
  background-position: 85% 0;
}
.hover-screen:nth-child(5) {
  background: url(https://i.imgur.com/YTdLfKu.png);
  background-size: cover;
  background-position: 85% 0;
}
.team-inf {
  width: 100%;
  height: 100vh;
  position: absolute;
  top: 0;
  left: 0;
  background-color: white;
  z-index: 20;
  transform: translate(0, 100%);
  transition-duration: 0.5s;
}
.img-container {
  padding: 1.2rem;
  box-sizing: border-box;
  overflow: hidden;
}
.close-icon {
  position: absolute;
  top: 1.2rem;
  left: 2.5rem;
  font-size: 3rem;
  cursor: pointer;
  transition-duration: 0.5s;
}
.close-icon:hover {
  transform: scale(1.2);
}
.team-inf-container > h3 {
  padding-left: 5rem;
  box-sizing: border-box;
  margin-bottom: 2rem;
  color: white;
}
.text {
  color: white;
  padding-left: 5rem;
}
</style>
