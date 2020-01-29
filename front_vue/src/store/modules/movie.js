// const axios = require('axios');
import router from '../../router';


const state = {
    movieid: 0,
};


const getters = {
    getMovieid: state => state.movieid
};

const mutations = {
    setMovieid: (state, movieid) => {
        state.movieid = movieid
        sessionStorage.setItem('movie', movieid);
    },
};

const actions = {
    // pushtoMovie: ({ commit }, movie) => {
    //     // commit('setMovie', movie)
    //     const movieid = movie.id
    //     commit('setMovieid', movieid)
    // },

    getmoviedetail: ({ commit }, movieid) => {
        axios.get(HOST + 'api/movie_detail/' + this.movie.id + '/', options)
        .then(res => {
            commit('setMovieid', res.data)
        })
    }
}

export default {
    state, getters, mutations, actions
}
