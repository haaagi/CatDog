<template>
    <div class="login-form">
        <div v-if="isLoading" class="spinner-border" role="status">
            <span class="sr-only">Loading</span>
        </div>

        <form v-else class="login-input" @submit.prevent="login(credentials)">
            <div v-if="getErrors.length" class="error-list alert alert-danger">
                <p>아래의 오류를 해결해 주세요</p>
                <ul>
                    <li v-for="(error, idx) in getErrors" :key="idx">
                        {{ error }}
                    </li>
                </ul>
            </div>

            <div class="form-group">
                <label for="username">ID</label>
                <input v-model="credentials.username" type="text" class="form-control" id="username"
                    placeholder="아이디를 입력해주세요">
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input v-model="credentials.password" type="password" class="form-control" id="password"
                    placeholder="비밀번호를 입력해주세요">
            </div>

            <button class="btn btn-primary" @click.prevent="login(credentials)">로그인</button>
        </form>
    </div>
</template>

<script>
    import {
        mapGetters,
        mapActions
    } from 'vuex';
    export default {
        name: 'LoginForm',
        data() {
            return {
                credentials: {
                    username: '',
                    password: '',
                }
            }
        },
        methods: {
            ...mapActions(['login']),
        },
        computed: {
            ...mapGetters(['getErrors', 'isLoading']),
        }
    }
</script>

<style>

</style>