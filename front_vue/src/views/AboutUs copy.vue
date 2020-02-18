<template>
  <div class="container-fluid box">
    <div class="row ux">
      <div class="col-12 top">
        <h1>Cat Dog</h1>
        <p>
          우리들의 반려견을 위한 SNS <br />
          #강아지 #고양이 #선팔하면맞팔
        </p>
      </div>
      <div class="col-2 d-flex justify-content-center">
        <h3>programmer</h3>
      </div>
      <div class="col-3 d-flex justify-content-center">
        <h3>programmer</h3>
      </div>
      <div class="col-2 d-flex justify-content-center">
        <h3 class="cm">community manager</h3>
      </div>
      <div class="col-3 d-flex justify-content-center">
        <h3 class="wd">web developer</h3>
      </div>
      <div class="col-2 d-flex justify-content-center">
        <h3 class="gd">graphic design</h3>
      </div>
    </div>
    <div class="row full-screen no-gutters">
      <div class="col-2 hover-screen">
        <h2>Nombre NOMBRE</h2>
      </div>
      <div class="col-3 hover-screen">
        <h2>Nombre NOMBRE</h2>
      </div>
      <div class="col-2 hover-screen">
        <h2>Nombre</h2>
      </div>
      <div class="col-3 hover-screen">
        <h2>Nombre</h2>
      </div>
      <div class="col-2 hover-screen">
        <h2>Nombre</h2>
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
</template>

<script>
export default {};
</script>
<style scoped>
.box {
  padding: 0;
  position: relative;
  background-size: 400% 400%;
  background-image: linear-gradient(-45deg, #ceedfa, #ffe082, #a2ca45, #c5e1a5, #d3b42c);
  transition-duration: 0.5s;
  animation: Gradient 5s ease infinite;
  opacity: 0.9;
  overflow: hidden;
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
  background: url(https://i.imgur.com/8lZhrRF.jpg);
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
  background: url(https://i.imgur.com/4o5TgbF.png);
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
  color: black;
}
.text {
  color: black;
  padding-left: 5rem;
}
</style>
