<template>
    <div class="loginpage" style="background-image: linear-gradient(to top, #fbc2eb 0%, #a6c1ee 100%);">
        <el-tabs v-model="activeName" id="tabs">
            <!-- 手机登录tab -->
            <el-tab-pane label="手机登录" name="second" class="item">
                <el-form ref="form" :model="phoneForm" :rules="rules" label-width="80px">
                    <el-form-item label="手机" prop="phone">
                        <el-input v-model="phoneForm.phone" style="width: 200px"></el-input>
                    </el-form-item>
                    <el-form-item label="验证码" prop="verifyCode">
                        <el-input v-model="phoneForm.verifyCode" style="width: 200px"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="phoneLogin">登录</el-button>
                    </el-form-item>
                </el-form>
            </el-tab-pane>

        </el-tabs>
    </div>
</template>
  
<script>
import axios from "axios";
export default {
    data() {
        return {
            // 默认标签页的名称
            activeName: "second",// 手机登录表单
            phoneForm: {
                phone: "",
                verifyCode: "",
            },
            rules: {
                phone: [
                    { required: true, message: "请输入手机号", trigger: "blur" },
                    {
                        pattern: /^1[3456789]\d{9}$/,
                        message: "请输入正确的手机号",
                        trigger: "blur",
                    },
                ],
                verifyCode: [
                    { required: true, message: "请输入验证码", trigger: "blur" },
                ],
            },
        };
    },

    methods: {
        // 手机号登录
        phoneLogin() {
            this.$router.push("/backend-index");
            // axios
            //     .post(
            //         "/api/phone-login?testPhone=" +
            //         this.phoneForm.phone +
            //         "&verifyCode=" +
            //         this.phoneForm.verifyCode
            //     )
            //     .then((response) => {
            //         if (response.data.code == 200) {
            //             this.$message({
            //                 showClose: true,
            //                 message: response.data.msg,
            //                 type: "success",
            //             });
            //             localStorage.setItem(
            //                 "userInfo",
            //                 JSON.stringify(response.data.data)
            //             );
            //             this.$router.push({
            //                 path: "/index",
            //             });
            //         } else {
            //             this.$message({
            //                 showClose: true,
            //                 message: response.data.msg,
            //                 type: "error",
            //             });
            //         }
            //     });
        },

    },

};
</script>
  
<style scoped>
.item {
    text-align: center;
    padding: 30px 0;
}

#tabs {
    float: right;
    margin-right: 80px;
    margin-top: 100px;
    width: 320px;
    height: 320px;
}

.loginpage {
    background-size: cover;
    background-repeat: no-repeat;
    position: absolute;
    background-position: center;
    height: 100%;
    width: 100%;
}
</style>