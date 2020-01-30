<template>
    <div class="signup-form">
        <div v-if="isLoading" class="spinner-border" role="status">
            <span class="sr-only">Loading</span>
        </div>

        <form v-else class="signup-input" @submit.prevent="signup(credentials)">
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
                <input v-model="userInput.username" type="text" class="form-control" id="username"
                    placeholder="아이디를 입력해주세요">
            </div>

            <div class="form-group">
                <label for="password">Password</label>
                <input v-model="userInput.password" type="password" class="form-control" id="password"
                    placeholder="비밀번호를 입력해주세요">
            </div>

            <div class="form-group">
                <label for="passwordConfirmation">PasswordConfirmation</label>
                <input v-model="userInput.passwordConfirmation" type="password" class="form-control" id="passwordConfirmation"
                    placeholder="비밀번호를 확인해주세요">
            </div>

            <div class="form-group">
                <label for="name">Name</label>
                <input v-model="userInput.name" type="text" class="form-control" id="name"
                    placeholder="이름을 입력해주세요">
            </div>

            <div class="form-group">
                <label for="nickname">닉네임</label>
                <input v-model="userInput.nickname" type="text" class="form-control" id="nickname"
                    placeholder="별명을 입력하세요">
            </div>
            
            <div class="form-group">
                <label for="birthday">생일</label>
                <input v-model="userInput.birthday" type="date" class="form-control" id="birthday"
                    placeholder="생일을 입력하세요">
            </div>
            <div class="row">
                <button class="btn btn-primary" @click.prevent="signup(userInput)">회원가입</button>
            </div>
            
        </form>
    </div>

</template>

<script>
    import {
        mapGetters,
        mapActions
    } from 'vuex';

    export default {
        name: 'SignupForm',
        data() {
            return {
                userInput: {
                    email: '',
                    password: '',
                    passwordConfirmation:'',
                    name:'',
                    nickname:'',
                    birthday:'', 
                    // picture:'',
                    likePet: [],
                    myPet: [],
                }
            }
        },
        methods: {
            ...mapActions(['signup']),
        },
        computed: {
            ...mapGetters(['getErrors','isLoading']),
        }

    }
</script>

<style>

</style>