<template>
   <div class="part">
      <div class="box" ref="box"></div>
      <div v-if="isNull">
         <span>该门考试还没人参考哦,请提醒学生参加考试。</span>
      </div>
   </div>
</template>

<script>
export default {
   data() {
      return {
         isNull: false, //是否有成绩标志位
         name: null,
         category: {
            //保存分数段
            "90分及以上": 0,
            "80-89分": 0,
            "70-79分": 0,
            "60-69分": 0,
            "60分以下": 0,
         },
      };
   },
   created() {
      this.getScoreInfo();
   },
   methods: {
      getScoreInfo() {
         let examCode = this.$route.query.examCode;
         this.name = this.$route.query.source;
         ///api/scores/${examCode}
         let tokenStr = this.$session.get("jwt");
         const headers = {
            Authorization: "Bearer " + tokenStr,
         };
         this.$axios(`http://localhost:8080/scores/${examCode}`, {
            headers,
         }).then((res) => {
            let data = res.data.data;
            if (data.length > 0) {
               let box = this.$refs["box"];
               let charts = this.$echarts.init(box);
               data.forEach((element) => {
                  switch (element.etScore / 10) {
                     case 10:
                     case 9:
                        this.category["Above 90"]++;
                        break;
                     case 8:
                        this.category["80-89"]++;
                        break;
                     case 7:
                        this.category["70-79"]++;
                        break;
                     case 6:
                        this.category["60-69"]++;
                        break;
                     default:
                        this.category["Below 60"]++;
                  }
               });
               let option = {
                  title: {
                     text: `${this.name}Score Chart`,
                     subtext: "Score Pie chart",
                     x: "center",
                  },
                  tooltip: {
                     trigger: "item",
                     formatter: "{a}：{b} <br/> {c}人 ({d}%)",
                  },
                  legend: {
                     orient: "vertical",
                     left: "left",
                     data: ["Above 90", "80-89", "70-79", "60-69", "Below 60"],
                  },
                  series: [
                     {
                        name: "Score",
                        type: "pie",
                        radius: "35%",
                        center: ["50%", "35%"],
                        data: [
                           {
                              value: this.category["Above 90"],
                              name: "Above 90",
                           },
                           { value: this.category["80-89"], name: "80-89" },
                           { value: this.category["70-79"], name: "70-79" },
                           { value: this.category["60-69"], name: "60-69" },
                           {
                              value: this.category["Below 60"],
                              name: "Below 60",
                           },
                        ],
                        itemStyle: {
                           emphasis: {
                              shadowBlur: 10,
                              shadowOffsetX: 0,
                              shadowColor: "rgba(0, 0, 0, 0.5)",
                           },
                        },
                     },
                  ],
               };
               charts.setOption(option);
            } else {
               this.isNull = true;
            }
         });
      },
   },
};
</script>

<style lang="less" scoped>
.part {
   .box {
      width: 800px;
      height: 800px;
      margin-left: 40px;
   }
}
</style>
