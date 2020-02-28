<template>
  <div>
    <v-container>
      <p class="font-weight-black headline">
        <v-icon large color="green darken-2">mdi-gamepad-round-outline </v-icon> HEALTH
      </p>
      <p class="font-weight-black ">반려동물의 예방접종 기간 관리 페이지</p>

      <v-btn text icon color="green" @click="onClickAddBtn">
        <v-icon>mdi-pencil</v-icon>
      </v-btn>
      <div v-if="addBtn">
        <!-- 추가 메뉴 창 -->
        <div>
          <v-card max-width="" class="mx-auto" outlined>
            <v-row>
              <!-- 웹 제공 예방접종들  -->
              <v-card-text v-for="prevention in preventions" :key="prevention">
                <v-btn class="ma-2" tile outlined color="success" @click="onClickPrev(prevention)">
                  <v-icon left>mdi-dog</v-icon> {{ prevention }}
                </v-btn>
              </v-card-text>
            </v-row>

            <!-- 사용자 추가 입력 폼 -->
            <v-card-text>
              <v-text-field
                label="예방접종명"
                :value="addPrev"
                :key="addPrev"
                required
              ></v-text-field>
            </v-card-text>

            <v-card-actions>
              <v-btn text color="" @click="onClickAddPrev(addPrev)">
                ADD
              </v-btn>
              <v-btn text color="">
                CANCLE
              </v-btn>
            </v-card-actions>
          </v-card>
        </div>
      </div>
    </v-container>

    <v-container>
      <v-row>
        <v-layout row wrap="" justify-center="">
          <!-- <div v-for="prev in pickedPrevs" :key="prev">
            {{ prev }}
          </div> -->
          <!-- 아이콘 파트 -->
          <v-col align="center" v-for="prev in pickedPrevs" :key="prev">
            <v-btn class="mx-2" fab dark x-large color="red">
              <v-icon dark>mdi-hospital-box</v-icon>
            </v-btn>
            <p class="font-weight-black headline" style="margin-top: 20px;">
              {{ prev }}
            </p>
            <p>기간:</p>
          </v-col>
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
  data() {
    return {
      addBtn: false, // 추가 버튼 클릭여부

      preventions: ['종합 7종 백신(DA2PPL)', '코로나 장염 예방접종', '광견병 예방접종'],
      pickedPrevs: [], // 사용자가 선택한
    };
  },
  methods: {
    onClickAddBtn() {
      this.addBtn = !this.addBtn;
      console.log(this.addBtn);
    },
    // 예방접종 추가하기
    onClickPrev(prevention) {
      if (this.pickedPrevs.includes(prevention)) {
        return;
      } else {
        this.pickedPrevs.push(prevention);
        console.log(this.pickedPrevs);
      }
    },
    onClickAddPrev(addPrev) {
      this.pickedPrevs.push(addPrev);
    },
    onClickCancle() {},
  },
};
</script>

<style scoped></style>
