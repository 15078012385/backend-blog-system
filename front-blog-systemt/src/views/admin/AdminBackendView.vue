<template>
    <div>
        <el-container style="height: 100vh">
            <el-aside width="200px">
                <el-row class="tac">
                    <el-col :span="24">
                        <el-menu :default-active="currentPath" class="el-menu-vertical-demo" @open="handleOpen"
                            @close="handleClose" background-color="#222f3e" text-color="#fff" active-text-color="#ffd04b"
                            style="height: 100vh">
                            <h1 style="color: white; margin-top: 20px">
                                <img src="../../assets/8.gif" width="60px" style="border-radius: 15%" />
                            </h1>
                            <el-menu-item index="/backend-index" @click="changeMenu('/backend-index')">
                                <i class="el-icon-house"></i>
                                <span slot="title">首页</span>
                            </el-menu-item>
                            <el-menu-item index="/user-manage" @click="changeMenu('/user-manage')">
                                <i class="el-icon-s-custom"></i>
                                <span slot="title">用户管理</span>
                            </el-menu-item>
                            <el-menu-item index="/article-manage" @click="changeMenu('/article-manage')">
                                <i class="el-icon-edit-outline"></i>
                                <span slot="title">文章管理</span>
                            </el-menu-item>
                            <el-menu-item index="/img-manage-admin" @click="changeMenu('/img-manage-admin')">
                                <i class="el-icon-picture-outline"></i>
                                <span slot="title">图片管理</span>
                            </el-menu-item>
                            <el-menu-item index="/rotation-manage" @click="changeMenu('/rotation-manage')">
                                <i class="el-icon-loading"></i>
                                <span slot="title">轮播管理</span>
                            </el-menu-item>
                            <el-menu-item index="/remomend-manage" @click="changeMenu('/remomend-manage')">
                                <i class="el-icon-copy-document"></i>
                                <span slot="title">推荐内容</span>
                            </el-menu-item>
                            <el-menu-item index="/hot-search-manage" @click="changeMenu('/hot-search-manage')">
                                <i class="el-icon-trophy-1 "></i>
                                <span slot="title">热搜管理</span>
                            </el-menu-item>
                            <el-menu-item index="/notice-manage" @click="changeMenu('/notice-manage')">
                                <i class="el-icon-sort"></i>
                                <span slot="title">系统公告</span>
                            </el-menu-item>
                            <el-menu-item index="/log" @click="changeMenu('/log')">
                                <i class="el-icon-alarm-clock"></i>
                                <span slot="title">系统日志</span>
                            </el-menu-item>
                        </el-menu>
                    </el-col>
                </el-row>
            </el-aside>
            <el-container>
                <el-header>
                    <el-button type="success" style="float: right;margin-top: 10px;"
                        @click="changeMenu('/admin-login')">返回</el-button>
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
    name: "IndexView",
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
    data() {
        return {
            currentPath: "/backend-index",
        }
    }
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