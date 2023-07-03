<template>
    <div>
        <el-table :data="tableData" border style="width: 100%">
            <el-table-column prop="id" label="ID" width="80" sortable></el-table-column>
            <el-table-column label="时间">
                <template slot-scope="scope">
                    <el-tag>{{ scope.row.likeTime }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="点赞文章">
                <template slot-scope="scope">
                    <el-tag type="success">{{ scope.row.articleTitle }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="点赞人" width="400">
                <template slot-scope="scope">
                    <el-tag>{{ scope.row.likeUser }}</el-tag>
                </template>
            </el-table-column>
        </el-table>
        <el-pagination @current-change="handlePageChange" style="margin-top: 15px; text-align: center" background
            layout="prev, pager, next" :total="total">
        </el-pagination>
    </div>
</template>

<script>
export default {
    data() {
        return {
            tableData: [{
                "id": 13,
                "articleUser": "钉钉",
                "articleId": "275",
                "articleTitle": "公关部",
                "likeUser": "邓宇宁",
                "likeTime": "2023-03-05T10:54:26.000+00:00"
            }, {
                "id": 13,
                "articleUser": "钉钉",
                "articleId": "275",
                "articleTitle": "公关部",
                "likeUser": "邓宇宁",
                "likeTime": "2023-03-05T10:54:26.000+00:00"
            }, {
                "id": 13,
                "articleUser": "钉钉",
                "articleId": "275",
                "articleTitle": "公关部",
                "likeUser": "邓宇宁",
                "likeTime": "2023-03-05T10:54:26.000+00:00"
            }, {
                "id": 13,
                "articleUser": "钉钉",
                "articleId": "275",
                "articleTitle": "公关部",
                "likeUser": "邓宇宁",
                "likeTime": "2023-03-05T10:54:26.000+00:00"
            }, {
                "id": 13,
                "articleUser": "钉钉",
                "articleId": "275",
                "articleTitle": "公关部",
                "likeUser": "邓宇宁",
                "likeTime": "2023-03-05T10:54:26.000+00:00"
            }, {
                "id": 13,
                "articleUser": "钉钉",
                "articleId": "275",
                "articleTitle": "公关部",
                "likeUser": "邓宇宁",
                "likeTime": "2023-03-05T10:54:26.000+00:00"
            }, {
                "id": 13,
                "articleUser": "钉钉",
                "articleId": "275",
                "articleTitle": "公关部",
                "likeUser": "邓宇宁",
                "likeTime": "2023-03-05T10:54:26.000+00:00"
            }, {
                "id": 13,
                "articleUser": "钉钉",
                "articleId": "275",
                "articleTitle": "公关部",
                "likeUser": "邓宇宁",
                "likeTime": "2023-03-05T10:54:26.000+00:00"
            }],
            total: 0,
            pageNum: 1,
            pageSize: 10,
            // 当前登录信息，从localstorage中获取
            articleUser: JSON.parse(localStorage.getItem("userInfo")).wxName,
        }
    },
    created() {
        this.form = localStorage.getItem("userInfo") ? this.userInfo = JSON.parse(localStorage.getItem("userInfo")) : this.$router.push("/");
        this.init();
    },
    methods: {
        // 翻页
        handlePageChange(pageNum) {
            this.pageNum = pageNum;
            this.init();
        },
        // 初始化
        init() {
            // 封装后的axios
            this.request({
                method: 'get',
                url: '/api/like/page-by-user',
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    articleUser: this.articleUser
                }
            }).then(res => {
                this.total = res.data.data.total;
                this.tableData = res.data.data.records;
            }).catch(err => {
                console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
            });
            // axios完整写法
            // axios({
            //     method: 'get',
            //     url: 'http://localhost:9999/api/like/page-by-user',
            //     params: {
            //         pageNum: this.pageNum,
            //         pageSize: this.pageSize,
            //         articleUser: this.articleUser
            //     }
            // }).then(res => {
            //     // console.log(res.data.data);
            //     this.total = res.data.data.total;
            //     this.tableData = res.data.data.records;
            // }).catch(err => {
            //     console.log("找不到服务器，/(ㄒoㄒ)/~~");
            // })
        },

    }
}
</script>

<style></style>