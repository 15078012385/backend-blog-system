<template>
    <div>
        <el-container style="height: 100vh">
            <el-aside width="200px">
                <el-row class="tac">
                    <el-col :span="24">
                        <el-menu :default-active="currentPath" class="el-menu-vertical-demo" @open="handleOpen"
                            @close="handleClose" background-color="#222f3e" text-color="#fff" active-text-color="#ffd04b"
                            style="height: 100vh;">
                            <h1 style="color: white; margin-top: 20px">
                                <img src="../../assets/1.gif" width="60px" style="border-radius: 15%" />
                            </h1>
                            <el-menu-item index="/something-manage" @click="changeMenu('/something-manage')">
                                <i class="el-icon-edit-outline"></i>
                                <span slot="title">文章管理</span>
                            </el-menu-item>
                            <el-menu-item index="/img-manage" @click="changeMenu('/img-manage')">
                                <i class="el-icon-picture-outline"></i>
                                <span slot="title">图片管理</span>
                            </el-menu-item>
                            <el-menu-item index="/msg" @click="changeMenu('/msg')">
                                <i class="el-icon-s-comment"></i>
                                <span slot="title">消息</span>
                            </el-menu-item>
                        </el-menu>
                    </el-col>
                </el-row>
            </el-aside>
            <el-container>
                <el-header>
                    <el-button type="success" style="float: right;margin-top: 10px;" @click="changeMenu('/')">返回</el-button>
                </el-header>
                <el-main>
                    <router-view />
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
export default {
    watch: {
        // 监听路由变化
        $route: {
            immediate: true, // 立即执行
            handler(to, from) {
                // to 和 from 都是路由对象，可以在这里做一些操作
                localStorage.setItem("currentPath", to.path);
                this.currentPath = to.path;
            },
        },
    },
    data() {
        return {
            currentPath: "/something-manage",
        }
    },
    methods: {
        handleOpen(key, keyPath) {
            console.log(key, keyPath);
        },
        handleClose(key, keyPath) {
            console.log(key, keyPath);
        },
        changeMenu(path) {
            localStorage.setItem("currentPath", path);
            this.$router.push(path);
        },
    },

}
</script>

<style scoped>
.el-header {
    background-color: #579ef0;
    color: #304153;
    line-height: 60px;
}

.el-dropdown-link {
    cursor: pointer;
    color: #e5ebf1;
}

.el-icon-arrow-down {
    font-size: 12px;
}

.el-aside {
    text-align: center;
    line-height: 50px;
}

.el-main {
    background-color: #e9eef3;
    color: #333;
}

body>.el-container {
    margin-bottom: 40px;
}
</style>