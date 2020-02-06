<template>
  <div>
    <v-container>
      <p class="font-weight-black headline">
        <v-icon large color="green darken-2">mdi-gamepad-round-outline </v-icon> HEALTH
      </p>
      <p class="font-weight-black ">반려동물의 예방접종 기간 관리 페이지</p>
    </v-container>

    <v-container>
      <v-row>
        <v-layout row wrap="" justify-center="">
          <!-- 아이콘 파트 -->

          <!-- grid 수정하기!! -->
          <v-col align="center">
            <v-btn class="mx-2" fab dark x-large color="red">
              <v-icon dark>mdi-hospital-box</v-icon>
            </v-btn>
            <p class="font-weight-black headline" style="margin-top: 20px;">
              종합 7종 백신(DA2PPL)
            </p>
            <p>기간: 2020.02.02 ~ 2020.02.20</p>
          </v-col>

          <v-col align="center">
            <v-btn class="mx-2" fab dark x-large color="red">
              <v-icon dark>mdi-hospital-box</v-icon>
            </v-btn>
            <p class="font-weight-black" style="margin-top: 20px;">종합 7종 백신(DA2PPL)</p>
          </v-col>

          <v-col align="center">
            <v-btn class="mx-2" fab dark x-large color="red">
              <v-icon dark>mdi-hospital-box</v-icon>
            </v-btn>
            <p class="font-weight-black" style="margin-top: 20px;">종합 7종 백신(DA2PPL)</p>
          </v-col>
        </v-layout>

        <v-layout>
          <!-- 메뉴 -->
          <v-card class="mx-auto" max-width="auto" tile>
            <v-list flat>
              <v-subheader>예방접종 Menu </v-subheader>
              <v-list-item-group v-model="item" color="primary">
                <v-list-item v-for="(item, i) in items" :key="i">
                  <v-dialog v-model="dialog" persistent max-width="600px">
                    <template v-slot:activator="{ on }">
                      <v-btn text color="" v-on="on" :key="item"
                        ><v-icon>{{ item.icon }}</v-icon> {{ item.text }}</v-btn
                      >
                    </template>
                    <v-card>
                      <v-card-title>
                        <span class="headline">예방접종 일정 설정하기</span>
                      </v-card-title>
                      <v-card-text>
                        <v-container>
                          <v-row>
                            <v-col cols="12" sm="6" md="4">
                              <p :key="item">{{ item.text }}</p>
                            </v-col>
                            <v-col>
                              <v-text>
                                예방접종에 대한 간단한 설명쯔~~~~~~
                              </v-text>
                            </v-col>
                          </v-row>

                          <!-- 날짜 input -->
                          <v-menu
                            ref="menu"
                            v-model="menu"
                            :close-on-content-click="false"
                            :return-value.sync="date"
                            transition="scale-transition"
                            offset-y
                            min-width="290px"
                          >
                            <template v-slot:activator="{ on }">
                              <v-text-field
                                v-model="date"
                                label="Picker in menu"
                                prepend-icon="event"
                                readonly
                                v-on="on"
                              ></v-text-field>
                            </template>
                            <v-date-picker v-model="date" no-title scrollable>
                              <v-spacer></v-spacer>
                              <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                              <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
                            </v-date-picker>
                          </v-menu>
                        </v-container>
                      </v-card-text>
                      <v-card-actions>
                        <v-spacer></v-spacer>
                        <v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
                        <v-btn color="blue darken-1" text @click="dialog = false">Save</v-btn>
                      </v-card-actions>
                    </v-card>
                  </v-dialog>
                </v-list-item>
              </v-list-item-group>
            </v-list>
          </v-card>
          <!--  -->
        </v-layout>

        <!-- 나머지 버튼들 -->
        <div>
          <!-- <v-btn class="ma-2" text icon color="red lighten-2">
                  <v-icon>mdi-stomach</v-icon>
                </v-btn>
                <v-btn class="ma-2" text icon color="red lighten-2">
                  <v-icon>mdi-gamepad-round</v-icon>
                </v-btn>
                <v-btn class="ma-2" text icon color="red lighten-2">
                  <v-icon>mdi-allergy</v-icon>
                </v-btn>
                <v-btn class="ma-2" text icon color="red lighten-2">
                  <v-icon>mdi-alpha-c-circle</v-icon>
                </v-btn>
                <v-btn class="ma-2" text icon color="red lighten-2">
                  <v-icon>mdi-bacteria</v-icon>
                </v-btn>
                <v-btn class="ma-2" text icon color="red lighten-2">
                  <v-icon>mdi-brain</v-icon>
                </v-btn>
                <v-btn class="ma-2" text icon color="red lighten-2">
                  <v-icon>mdi-dog-side</v-icon>
                </v-btn>
                <v-btn class="ma-2" text icon color="red lighten-2">
                  <v-icon>mdi-dog</v-icon>
                </v-btn>
                <v-btn class="ma-2" text icon color="red lighten-2">
                  <v-icon>mdi-tooth-outline</v-icon>
                </v-btn>
                <v-btn class="ma-2" text icon color="red lighten-2">
                  <v-icon>mdi-toilet</v-icon>
                </v-btn> -->
        </div>
      </v-row>
    </v-container>
  </div>
</template>

<script>
export default {
  components: {},
  data: () => ({
    // 메뉴
    item: 1,
    items: [
      { text: '코로나 장염 예방접종', icon: 'mdi-dog' },
      { text: 'Audience', icon: 'mdi-account' },
      { text: 'Conversions', icon: 'mdi-flag' },
    ],

    dialog: false,

    // 캘린더
    date: new Date().toISOString().substr(0, 10),
    menu: false,
  }),
  methods: {},
};
</script>

<style scoped></style>
