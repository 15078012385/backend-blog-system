<template>
  <div>
    <div  class="demo-image__preview" style="background-color: #ffffff;width: 100%;">
      <el-image style="width: 200px; height: 200px; margin-left: 23px; margin-top: 15px" :src="item.img"
        :preview-src-list="[item.img]" v-for="item in imgData" :key="item.id">
      </el-image>
      <br>
      <br>
    </div>
    <el-pagination @current-change="handlePageChange" style="margin-top: 15px; text-align: center" background layout="prev, pager, next" :total="total">
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      total: 0,
      pageNum: 1,
      pageSize: 10,
      imgData: [],
    };
  },
  created() { this.init() },
  methods: {
    init() {
      this.request({
        method: 'get',
        url: '/api/img/page',
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          param: this.param,
        }
      }).then(res => {
        this.imgData = res.data.data.records;
       
      }).catch(err => {
        console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
      });
    },
    // 切换页码
    handlePageChange(pageNum) {
      this.pageNum = pageNum
      this.init();
    },
  }
};
</script>

<style>
.demo-image__preview {
  display: inline-block;
}
</style>