<template>
<div>
  <b-navbar toggleable="lg" type="light" variant="light">
      <router-link to="/home" class="nav-link" role="menuitem" target="_self">Home</router-link>
    
    <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

    <b-collapse id="nav-collapse" is-nav>
      <b-navbar-nav>
      </b-navbar-nav>

      <!-- Right aligned nav items -->
      <b-navbar-nav class="ml-auto">

        <b-nav-item-dropdown right>
          <!-- Using 'button-content' slot -->
          <template v-slot:button-content>
            <em>User</em>
          </template>
            <div v-if="isLoggedIn">
              <router-link to="/userdetail" class="dropdown-item" role="menuitem" target="_self">userdetail</router-link>
              
              <router-link to="/userlist" class="dropdown-item" role="menuitem" target="_self">User list</router-link>
              <b-dropdown-item href="#" @click.prevent="logout">Log Out</b-dropdown-item>

            </div>
            <div v-else>
              <!-- <a role="menuitem" target="_self" href="#" class="dropdown-item">Login</a> -->

              <router-link to="/login" class="dropdown-item" role="menuitem" target="_self"> Login</router-link>
              <router-link to="/signup" class="dropdown-item" role="menuitem" target="_self">Sign up</router-link>
              
            </div>
        </b-nav-item-dropdown>
      </b-navbar-nav>
    </b-collapse>
  </b-navbar>
</div>


</template>

<script>
import { mapActions, mapGetters } from 'vuex';
// import router from '../router'
export default {
  name: 'Navbar',
  methods: {
    ...mapActions(['login', 'logout']),
  },
  computed: {
    ...mapGetters(['isLoggedIn']),
  }, 

  }
</script>

<style>

</style>