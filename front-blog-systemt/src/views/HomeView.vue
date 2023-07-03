<template>
  <div>
    <!-- 导航条 -->
    <div style="width: 100%; height: 61px; background-color: #ffffff">
      <el-menu :default-active="currentPath" class="el-menu-demo" mode="horizontal" @select="handleSelect"
        background-color="#ffffff" text-color="black" active-text-color="#3498db" style="width: 1140px; margin: 0 auto">
        <el-menu-item index="/index">
          <img src="../assets/1.gif" alt="" i />
          首页</el-menu-item>
        <el-menu-item index="/something">
          <img src="../assets/6.gif" alt="" />
          庆师点滴
        </el-menu-item>
        <el-menu-item index="/img">
          <img src="../assets/5.gif" alt="" />
          庆师美照</el-menu-item>
        <el-menu-item index="/notice">
          <img src="../assets/7.gif" alt="" />
          系统公告</el-menu-item>
        <!-- <el-input v-model="input" placeholder="请输入搜索内容" style="width: 400px; margin-top: 15px; margin-left: 10px"
          @keydown.enter.native="search()"></el-input> -->
        <el-button v-if="userInfo == null" size="medium" type="primary" style="float: right; margin-top: 10px" plain
          @click="toLogin()">登录</el-button>
        <el-menu-item v-else index="/personal" style="float: right;">
          个人中心<img src="../assets/4.gif" alt="" />
        </el-menu-item>
      </el-menu>
    </div>
    <!-- 路由页面 -->
    <router-view style="width: 1140px; margin: 0 auto; margin-top: 15px" />
    <!-- 回到顶部、滚动滚轮后可看到 -->
    <el-backtop></el-backtop>

    <!-- 页脚 -->
    <div style="width: 100%; height: 61px;background-color: #ffffff;margin-top: 15px;">
      <h3 style="text-align: center; line-height: 61px; color: #96bfec">
        版权所有©dingding.com联系我们
      </h3>
    </div>

    <!-- 登录弹框 -->
    <el-dialog title="扫码登录" :visible.sync="dialogVisible" width="40%" style="text-align: center">
      <hr />
      <br />
      <img :src="imageBase64" width="50%" height="50%" style="border: 2px solid #ccc; border-radius: 2%" />
      <br />
      会话有效期：<span v-text="sconds" style="color: red"></span>
      <br />
      <h2>请使用微信扫码登录</h2>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "IndexView",
  data() {
    return {
      pageNum: 1,
      pageSize: 10,
      userInfo: localStorage.getItem("userInfo") ? JSON.parse(localStorage.getItem("userInfo")) : null,
      currentPath: localStorage.getItem("currentPath") ? localStorage.getItem("currentPath") : "/index",
      sconds: 60,
      input: "",
      dialogVisible: false,
      // 定时器对象
      timer: null,
      // 二维码的id
      qrCodeId: "",
      // 二维码的base64编码
      imageBase64: "",

    };
  },
  mounted() {
    // 判断是否有用户信息
    if (localStorage.getItem("userInfo")) {
      this.userInfo = JSON.parse(localStorage.getItem("userInfo"));
      return;
    }

    if (localStorage.getItem("currentPath") == "/index") {
      // 显示登录弹框
      this.dialogVisible = true;
      // 发送获取二维码的请求
      this.request("/api/qr-login/generation-qrcode").then((response) => {
        this.qrCodeId = response.data.msg;
        console.log("会话ID:" + this.qrCodeId);
        // 将字节数组转换为base64编码的字符串
        this.imageBase64 = response.data.data;
      });
      // 调用轮询方法
      this.getLoginStatus();
      setInterval(() => {
        if (this.sconds == 0) {
          this.sconds = "会话过期，请刷新二维码。";
        }
        this.sconds = this.sconds - 1;
      }, 1000);
    }



  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
      if (key == "/personal") {
        if (localStorage.getItem("userInfo")) {
          this.$router.push({ path: key });
        } else {
          this.$message.error("请先登录");
        }
      } else {
        localStorage.setItem("currentPath", key);
        this.$router.push({ path: key });
      }

    },
    search() {
      if (this.input.length > 20) {
        this.$message.error("搜索内容过长");
        this.input = "";
      } else {
        // console.log(this.input);
        localStorage.setItem("currentPath", '/something');
        this.$router.push({ path: "/something", query: { input: this.input } });
      }
    },
    // 每隔一段时间发送请求，获取二维码的状态
    getLoginStatus() {
      this.timer = setInterval(() => {
        this.request("/api/qr-login/get-login-status?tempUserId=" + this.qrCodeId)
          .then((response) => {
            if (response.data.msg == "当前会话过期，请刷新二维码。") {
              // 清除定时器
              clearInterval(this.timer);
              this.$message({
                showClose: true,
                message: "当前会话过期，请刷新二维码。",
                type: "error",
              });
            } else if (response.data.msg == "待确认登录") {
              // console.log("持续轮询登录状态，待确认登录");
            } else if (response.data.msg == "登录成功") {
              // 清除定时器
              clearInterval(this.timer);
              // 关闭弹出框
              this.dialogVisible = false;
              this.$message({
                showClose: true,
                message: "登录成功",
                type: "success",
              });
              // this.$router.go(0);
              // 将用户信息保存到本地存储中
              localStorage.setItem(
                "userInfo",
                JSON.stringify(response.data.data)
              );
              this.userInfo = response.data.data;
            } else if (response.data.msg == "您已被封禁,请联系管理员。") {
              // 清除定时器
              clearInterval(this.timer);
              // 关闭弹出框
              this.dialogVisible = false;
              this.$message({
                showClose: true,
                message: "您已被封禁,请联系管理员。",
                type: "error",
              });
            }
          });
      }, 2000);
    },
    toLogin() {
      this.dialogVisible = true;
      // 发送获取二维码的请求
      this.request("/api/qr-login/generation-qrcode").then((response) => {
        this.qrCodeId = response.data.msg;
        console.log("会话ID:" + this.qrCodeId);
        // 将字节数组转换为base64编码的字符串
        this.imageBase64 = response.data.data;
      });
     // 调用轮询方法
     this.getLoginStatus();
      setInterval(() => {
        if (this.sconds == 0) {
          this.sconds = "会话过期，请刷新二维码。";
        }
        this.sconds = this.sconds - 1;
      }, 1000);
    }
  },
  watch: {

  }
};
</script>
