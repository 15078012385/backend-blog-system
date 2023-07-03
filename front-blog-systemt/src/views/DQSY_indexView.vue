<template>
  <div>
    <!-- 轮播图 -->
    <el-carousel :interval="4000" type="card" height="270px" style="width: 1000px; margin: 0 auto">
      <el-carousel-item v-for="item in rotationData" :key="item.id">
        <img :src="item.img" width="100%" alt="">
      </el-carousel-item>
    </el-carousel>
    <!-- 主题内容 -->
    <div style="background-color: #ffffff">
      <!-- 推荐内容 -->
      <div>
        <h1 style="text-align: center;padding-top: 20px;">推荐内容</h1>
        <p v-for="item in recommendData" :key="item.id" style="margin: 20px;">
          {{ item.recommendContent }}
        </p>
      </div>
      <el-divider></el-divider>
      <!--  庆师热搜榜 -->
      <div style="margin: 20px;height: 450px;">
        <div style="float: left;width:400px ;">
          <h4>庆师热搜榜</h4>
          <ul>
            <li v-for="item in hotSearchData" :key="item.id" style=" list-style-type:none;">
              <el-tag type="" style="margin-top: 20px; width: 380px;">
                话题：{{ item.hotSearchContent }}

              </el-tag>
              <el-tag type="danger" style="float: right;margin-right: 20px;">热搜指数：{{ item.searchCount }}</el-tag>
            </li>
          </ul>
        </div>
        <div style="float: right;width:700px ;">
          <h4 style="float: right;">庆师赛事</h4>
          <img src="https://image.diyiyou.com/game/202007/15/1594780790_2.jpg" width="700px" height="420px"
            alt="" />
        </div>

      </div>
      <el-divider></el-divider>
      <!-- 赞助商logo -->
      <div style="margin: 20px;">
        <h5>网站赞助商</h5>
        <img v-for="item in 5" :key="item" src="../assets/1.gif" alt="" />
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    this.initHotSearch();
    this.initRecommend();
    this.initRotation();
  },
  data() {
    return {
      hotSearchData: [],
      recommendData: [],
      rotationData: [],
      pageNum: 1,
      pageSize: 5,
      param: '',
    }
  },
  methods: {
    // 表格初始化
    initRotation() {
      this.request({
        method: 'get',
        url: '/api/rotation/page',
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          param: this.param,
        }
      }).then(res => {
        this.rotationData = res.data.data.records;
      }).catch(err => {
        console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
      });
    },
    initRecommend() {
      this.request({
        method: 'get',
        url: '/api/recommend/list',
      }).then(res => {
        this.recommendData = res.data.data;
      }).catch(err => {
        console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
      });
    },
    initHotSearch() {
      this.request({
        method: 'get',
        url: '/api/hot-search/page',
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          param: this.param,
        }
      }).then(res => {
        this.hotSearchData = res.data.data.records;
        // console.log(this.hotSearchData);
      }).catch(err => {
        console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
      });
    },
  }
};
</script>
<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>