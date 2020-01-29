<template>
  <div>
      <ul class=list-group list-group-horizontal v-for="user in users" :key="user.id">
        <div v-if="getuserinfo.id!=user.id">
            {{user.username}}
            <li class="list-group-item" v-if="getuserinfo.id in user.stars">
                {{user.stars}}
                <!-- <div class="list-group-item"> -->
                <button @click="follow(user.id)" class="ui basic green button">팔로우 취소</button>
                <!-- </div> -->
            <li class="list-group-item" v-else>
                {{user.stars}}
                <!-- <div class="list-group-item"> -->
                <button @click="follow(user.id)" class="ui basic green button">팔로우</button>
                <!-- </div> -->
            </li>
        </div>
      </ul>
  </div>
</template>

<script>
const HOST = process.env.VUE_APP_SERVER_HOST;
const axios = require('axios');
import {
    mapGetters
} from 'vuex';
export default {
    name: 'UserList',
    data () {
        return {
            users: [],
            flag:0,

        }
    },
    computed: {
      ...mapGetters(['getuserinfo'])
    },
    methods: {
        follow (star_id) {
            this.flag = !this.flag
            const hash = sessionStorage.getItem('jwt');
            const options = {
            headers: {
                Authorization:'JWT ' + hash
            }
        }
            axios.get(HOST + 'api/accounts/userfollow/'+star_id + '/', options)
            .then(res=> {
                console.log(res)
                this.users = res.data
            })
            .catch(err => console.error(err))
            
        }

    },
    created () {
        const hash = sessionStorage.getItem('jwt');
        const options = {
        headers: {
            Authorization:'JWT ' + hash
        }
    }
        axios.post(HOST + 'api/accounts/userlist/',null , options)
        .then(res=> {
            console.log(res.data)
            this.users = res.data
            })
    }
}
</script>

<style>

</style>