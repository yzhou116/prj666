//查询所有题库
<template>
   <div class="exam">
      <el-table
         :data="pagination.records"
         border
         :row-class-name="tableRowClassName"
      >
         <el-table-column
            fixed="left"
            prop="subject"
            label="Exam Name"
            width="180"
         ></el-table-column>
         <el-table-column
            prop="question"
            label="Question info"
            width="490"
         ></el-table-column>
         <el-table-column
            prop="section"
            label="Chapter"
            width="200"
         ></el-table-column>
         <el-table-column
            prop="type"
            label="Question Type"
            width="200"
         ></el-table-column>
         <el-table-column
            prop="score"
            label="Question Score"
            width="150"
         ></el-table-column>
         <el-table-column
            prop="level"
            label="Level of difficulty"
            width="133"
         ></el-table-column>
      </el-table>
      <el-pagination
         @size-change="handleSizeChange"
         @current-change="handleCurrentChange"
         :current-page="pagination.current"
         :page-sizes="[6, 10]"
         :page-size="pagination.size"
         layout="total, sizes, prev, pager, next, jumper"
         :total="pagination.total"
         class="page"
      ></el-pagination>
   </div>
</template>

<script>
export default {
   data() {
      return {
         pagination: {
            //分页后的考试信息
            current: 1, //当前页
            total: null, //记录条数
            size: 6, //每页条数
         },
      };
   },
   created() {
      this.getAnswerInfo();
   },
   methods: {
      getAnswerInfo() {
         //分页查询所有试卷信息
         let tokenStr = this.$session.get("jwt");
         const headers = {
            Authorization: "Bearer " + tokenStr,
         };
         var username = this.$cookies.get("cname");
         this.$axios(
            ///api/answers/${this.pagination.current}/${this.pagination.size}
            `http://localhost:8080/answers/${this.pagination.current}/${this.pagination.size}/${username}`,
            { headers }
         )
            .then((res) => {
               this.pagination = res.data.data;
               console.log(res);
            })
            .catch((error) => {});
      },
      //改变当前记录条数
      handleSizeChange(val) {
         this.pagination.size = val;
         this.getAnswerInfo();
      },
      //改变当前页码，重新发送请求
      handleCurrentChange(val) {
         this.pagination.current = val;
         this.getAnswerInfo();
      },
      tableRowClassName({ row, rowIndex }) {
         if (rowIndex % 2 == 0) {
            return "warning-row";
         } else {
            return "success-row";
         }
      },
   },
};
</script>
<style lang="less" scoped>
.exam {
   padding: 0px 40px;
   .page {
      margin-top: 20px;
      display: flex;
      justify-content: center;
      align-items: center;
   }
   .edit {
      margin-left: 20px;
   }
   .el-table tr {
      background-color: #dd5862 !important;
   }
}
.el-table .warning-row {
   background: #000 !important;
}

.el-table .success-row {
   background: #dd5862;
}
</style>
