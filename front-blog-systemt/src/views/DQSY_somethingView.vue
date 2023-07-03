<template>
  <div>
    <!-- vue循环 -->
    <el-input v-model="param" placeholder="请输入搜索内容" style="width: 100%;" @keydown.enter.native="search()"></el-input>
    <el-card class="box-card" v-for="item in somethingData" :key="item.id">
      <div slot="header" class="clearfix">
        <el-link type="primary"
        @click.prevent="jump(item)">{{
            item.articleTitle }}</el-link>
      </div>
      <div class="container">
        <el-row :gutter="20">
          <el-col :span="8">
            <img :src="item.firstImg" height="200px" alt="">
          </el-col>
          <el-col :span="16">
            <div style="height: 100px;">
              <h4>{{ item.publishTime }}</h4>
              <el-link v-if="item.articleIntroduction.length < 100" style="text-indent: 40px;" type="primary"
                @click.prevent="jump(item)">
                {{ item.articleIntroduction }}
              </el-link>
              <el-link v-else style="text-indent: 40px;" type="primary" @click.prevent="jump(item)">
                {{ item.articleIntroduction.substring(0, 100) + '...' }}
              </el-link>
            </div>
            <div style="height: 100px;">
              <div style="float: right;text-align: center;">
                <img width="100px" style="border-radius: 20%;" :src="item.avatar" alt="">
                <br>
                {{ item.publishUser }}
              </div>
              <div>
                <span><i class="el-icon-view"></i> {{ item.views }}</span>
              </div>
            </div>

          </el-col>
        </el-row>
      </div>

    </el-card>
    <el-pagination @current-change="handlePageChange" style="margin-top: 15px; text-align: center" background
      layout="prev, pager, next" :total="total">
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
      param: '', // 搜索内容
      somethingData: [],
    };
  },
  mounted() {
    this.init();
  },
  methods: {
    jump(item) {
      this.$router.push({
        path: '/article',
        query: {
          id: item.id,
          articleUser: item.publishUser,
          articleTitle: item.articleTitle
        }
      })
    },
    init() {
      this.request({
        method: 'get',
        url: '/api/article/page',
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          param: this.param,
        }
      }).then(res => {
        this.total = res.data.data.total;
        this.somethingData = res.data.data.records;
      }).catch(err => {
        console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
      });
    },
    // 切换页码
    handlePageChange(pageNum) {
      this.pageNum = pageNum
      this.init();
    },
    search() {
      if (this.param.length > 20) {
        this.$message.error("搜索内容过长");
        this.param = "";
      } else {
        this.init();
        this.$message({
          message: '搜索成功',
          type: 'success'
        })
      }
    },

  }
};
</script>

<style>
.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both;
}

.box-card {
  margin-top: 15px;
}

/* 

*/
.el-row {
  margin-bottom: 20px;

  &:last-child {
    margin-bottom: 0;
  }
}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
