<template>
  <div class="mod-home">
    <header id="title">
      自习室座位管理系统(当日预约情况)
    </header>
    <el-form
      :inline="true"
      :model="dataForm"
      @keyup.enter.native="getDataList()"
    >
      <el-form-item>
        <el-date-picker
          v-model="dataForm.valueTime"
          value-format="yyyy-MM-dd"
          type="date"
          placeholder="选择日期"
          @change="homeChange"
        >
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getList()"
          >查询</el-button
        >
      </el-form-item>
    </el-form>
    <el-table
      :data="dataList"
      border
      v-loading="dataListLoading"
      style="width: 100%;"
    >
      <el-table-column type="index" width="50"> </el-table-column>
      <el-table-column
        prop="floor"
        header-align="center"
        align="center"
        label="所属楼层"
      >
      </el-table-column>

      <el-table-column
        prop="roomName"
        header-align="center"
        align="center"
        label="自习室"
      >
      </el-table-column>
      <el-table-column
        prop="count"
        header-align="center"
        align="center"
        label="预约次数"
      >
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
function getNowTime() {
  var date = new Date();
  //年 getFullYear()：四位数字返回年份
  var year = date.getFullYear(); //getFullYear()代替getYear()
  //月 getMonth()：0 ~ 11
  var month = date.getMonth() + 1;
  //日 getDate()：(1 ~ 31)
  var day = date.getDate();
  //时 getHours()：(0 ~ 23)
  var hour = date.getHours();
  //分 getMinutes()： (0 ~ 59)
  var minute = date.getMinutes();
  //秒 getSeconds()：(0 ~ 59)
  var second = date.getSeconds();

  // var time = '当前时间是：' + year + '-' + addZero(month) + '-' + addZero(day) + ' ' + addZero(hour) + ':' + addZero(minute) + ':' + addZero(second);
  var time = year + "-" + addZero(month) + "-" + addZero(day);
  return time;
}
function addZero(s) {
  return s < 10 ? "0" + s : s;
}
export default {
  data() {
    return {
      dataList: [],
      dataListLoading: false,
      dataForm: {
        valueTime: getNowTime()
      }
    };
  },
  methods: {
    getList() {
      console.log(getNowTime());
      this.$http({
        url: this.$http.adornUrl("/basappointment/count"),
        method: "get",
        params: this.$http.adornParams({
          date: this.dataForm.valueTime || getNowTime()
        })
      }).then(({ data }) => {
        if (data && data.code === 0) {
          console.log(data.data);
          this.dataList = data.data;
        } else {
          this.$message.error(data.msg);
        }
      });
    },
    homeChange(row) {
      
    }
  },
  mounted() {
    this.getList();
  }
};
</script>

<style>
.mod-home {
  line-height: 1.5;
}
#title {
  width: 100%;
  text-align: center;
  color: #17b3a3;
  font-family: "STHupo";
  line-height: 70px;
  font-size: 50px;
  text-shadow: 5px 2px 6px #437a757c;
  padding-bottom: 40px;
}
</style>
