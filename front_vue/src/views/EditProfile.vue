<template>
  <div>
    <v-app id="inspire">
      <v-container style="margin-top: 40px;">
        <v-card>
          <v-toolbar flat color="amber" dark>
            <v-toolbar-title>User Profile</v-toolbar-title>
          </v-toolbar>
          <v-tabs vertical color="amber">
            <v-tab>
              <v-icon left>mdi-account</v-icon>
              프로필 편집
            </v-tab>
            <v-tab>
              <v-icon left>mdi-lock</v-icon>
              비밀번호 변경
            </v-tab>

            <!-- 프로필 편집 -->
            <v-tab-item align="center">
              <v-card flat>
                <EditDetail />
              </v-card>
            </v-tab-item>

            <!-- 비밀번호 변경 -->
            <v-tab-item align="center">
              <v-card flat>
                <EditPassword />
              </v-card>
            </v-tab-item>
          </v-tabs>
        </v-card>
      </v-container>
    </v-app>
  </div>
</template>

<script>
import EditDetail from '../components/editprofile/EditDetail';
import EditPassword from '../components/editprofile/EditPassword';

export default {
  name: 'EditProfile',
  components: {
    EditDetail,
    EditPassword,
  },
  data: () => ({
    valid: false,
  }),
};
</script>

<style scoped></style>
