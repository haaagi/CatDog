<template>
  <div class="home">
    <div>
      <div v-continer>
          <Navbar />
      </div>
    
      <div v-container>
 <Header />
      </div>
           
    </div>



  </div>
</template>

<script>
  import Navbar from '../components/Navbar'
  import Header from '../components/Header'

  export default {
    name: 'home',
    components: {
      Navbar,
      Header,
    }
  }
</script>