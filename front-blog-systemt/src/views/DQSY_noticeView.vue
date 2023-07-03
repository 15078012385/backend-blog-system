<template>
  <div>
    <div class="block">
      <el-timeline>
        <el-timeline-item :timestamp="item.noticeTime" placement="top" :key="item.id" v-for="item in noticeData">
          <el-card>
            <el-tag type="danger" style="">{{ item.publishUser }}</el-tag><br><br>
            
            <el-tag type="success"><h3>{{ item.noticeContent }}</h3></el-tag>
          </el-card>
        </el-timeline-item>

      </el-timeline>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    this.init();
  },
  data() {
    return {
      pageNum: 1,
      pageSize: 10,
      param: '',
      total: 0,
      noticeData: [],
    }
  },
  methods: {
    init() {
      this.request({
        method: 'get',
        url: '/api/notice/page',
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          param: this.param,
        }
      }).then(res => {
        this.noticeData = res.data.data.records;
      }).catch(err => {
        console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
      });
    },
  }
};
</script>

<style></style>