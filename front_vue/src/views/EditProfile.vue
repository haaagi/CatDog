<template>
  <div>
    <v-app id="inspire">
      <v-container>
        <v-card>
          <v-toolbar flat color="primary" dark>
            <v-toolbar-title>User Profile</v-toolbar-title>
          </v-toolbar>
          <v-tabs vertical>
            <v-tab>
              <v-icon left>mdi-account</v-icon>
              프로필 편집
            </v-tab>
            <v-tab>
              <v-icon left>mdi-lock</v-icon>
              비밀번호 변경
            </v-tab>
            <v-tab>
              <v-icon left>mdi-access-point</v-icon>
              기타
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

            <!-- 기타 -->
            <v-tab-item>
              <v-card flat>
                <v-card-text style="height: 600px;">
                  <p>
                    Fusce a quam. Phasellus nec sem in justo pellentesque facilisis. Nam eget dui.
                    Proin viverra, ligula sit amet ultrices semper, ligula arcu tristique sapien, a
                    accumsan nisi mauris ac eros. In dui magna, posuere eget, vestibulum et, tempor
                    auctor, justo.
                  </p>

                  <p class="mb-0">
                    Cras sagittis. Phasellus nec sem in justo pellentesque facilisis. Proin sapien
                    ipsum, porta a, auctor quis, euismod ut, mi. Donec quam felis, ultricies nec,
                    pellentesque eu, pretium quis, sem. Nam at tortor in tellus interdum sagittis.
                  </p>
                </v-card-text>
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
