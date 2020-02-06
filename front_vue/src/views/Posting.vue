<template>
  <div>
    <file-pond
      name="bin"
      ref="pond"
      allow-multiple="false"
      max-files="1"
      accepted-file-types="image/jpeg, image/png"
      v-bind:files="myFiles"
      v-on:init="handleFilePondInit"
      v-on:processfile="onload"
    />
  </div>
</template>

<script>
const axios = require('axios');
// Import Vue FilePond
import vueFilePond from 'vue-filepond';

// Import FilePond styles
import 'filepond/dist/filepond.min.css';

// Import FilePond plugins
// Please note that you need to install these plugins separately

// Import image preview plugin styles
import 'filepond-plugin-image-preview/dist/filepond-plugin-image-preview.min.css';

// Import image preview and file type validation plugins
import FilePondPluginFileValidateType from 'filepond-plugin-file-validate-type';
import FilePondPluginImagePreview from 'filepond-plugin-image-preview';

// Create component
const FilePond = vueFilePond(FilePondPluginFileValidateType, FilePondPluginImagePreview);

export default {
  name: 'app',
  data() {
    return {
      myFiles: [],
      // server: {
      //   url: `http://70.12.246.72:8080/api/user/profileimg/jbh@naver.com`,
      //   process: {
      //     method: 'POST',
      //     headers: {
      //       Authorization: 'JWT' + sessionStorage.getItem('jwt'),
      //     },
      //   },
      // },
    };
  },
  methods: {
    processHandler: (fieldName, file, metadata, load, error, progress) => {
      // set data
      const formData = new FormData();
      formData.append('file', file, file.name);

      // related to aborting the request
      const CancelToken = axios.CancelToken;
      const source = CancelToken.source();

      // the request itself
      axios({
        method: 'post',
        url: 'http://70.12.246.72:8080/api/user/profileimg/jbh@naver.com',
        data: formData,
        cancelToken: source.token,
        onUploadProgress: e => {
          // updating progress indicator
          progress(e.lengthComputable, e.loaded, e.total);
        },
      })
        .then(response => {
          // passing the file id to FilePond
          load(response.data.data.id);
        })
        .catch(thrown => {
          if (axios.isCancel(thrown)) {
            console.log('Request canceled', thrown.message);
          } else {
            // handle error
          }
        });
    },
  },
  components: {
    FilePond,
  },
};
</script>
