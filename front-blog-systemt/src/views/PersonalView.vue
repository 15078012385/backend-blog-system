<template>
    <div style=" background-color: #ffffff;text-align: center;">
        <el-form ref="form" :model="form" label-width="80px">
            <el-form-item label="头像">
                <el-image style="width: 170px; height:170px; margin-left: 23px; margin-top: 15px" :src="form.avatar"
                    :preview-src-list="[form.avatar]">
                </el-image>
            </el-form-item>
            <el-form-item label="微信ID">
                <el-input v-model="form.wxId" style="width: 500px;" disabled></el-input>
            </el-form-item>
            <el-form-item label="微信名">
                <el-input v-model="form.wxName" style="width: 500px;" disabled></el-input>
            </el-form-item>
            <el-button type="primary" @click="toSomethingManage()">发布庆师点滴</el-button>
            <el-button type="primary" @click="toImgManage()">上传庆师美照</el-button>
            <el-button size="small" type="danger" style="float: left; " @click="logout()">退出登录</el-button>
            <br><br>
        </el-form>
    </div>
</template>

<script>
export default {
    created() {
        if (localStorage.getItem("userInfo") == null) {
            this.$router.push("/index")
        }
        if (localStorage.getItem("userInfo") != null) {
            this.form = JSON.parse(localStorage.getItem("userInfo"));
        }
    },
    data() {
        return {
            form: {
                wxId: "",
                wxName: "",
                avatar:"",
            },
            url: "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2Ffc2d272d-3899-4af7-a6b0-bcb640b9cdd2%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1684407683&t=0314323950aee3bfc754e8d1e5c2e973",
            srcList: [
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fsafe-img.xhscdn.com%2Fbw1%2Ffc2d272d-3899-4af7-a6b0-bcb640b9cdd2%3FimageView2%2F2%2Fw%2F1080%2Fformat%2Fjpg&refer=http%3A%2F%2Fsafe-img.xhscdn.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1684407683&t=0314323950aee3bfc754e8d1e5c2e973",
            ],
        }
    },
    methods: {
        toSomethingManage() {
            this.$router.push({ path: '/something-manage' });
        },
        toImgManage() {
            this.$router.push({ path: '/img-manage' });
        },
        logout() {
            localStorage.removeItem("userInfo");
            this.$router.push("/index")
            this.$router.go(0);
        },
    }
}
</script>

<style></style>