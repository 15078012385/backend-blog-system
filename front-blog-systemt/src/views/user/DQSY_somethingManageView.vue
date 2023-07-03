<template>
    <div>
        <!-- 搜索 -->
        <div style="text-align: center;">
            <el-input placeholder="请输入搜索内容" v-model="param" style="width: 400px;"></el-input>
            <el-button type="primary" style="margin-left: 10px;" @click="search()">搜索</el-button>
        </div>
        <!-- 操作 -->
        <el-button type="primary" @click="toAdd()">发布文章</el-button>
        <el-popconfirm confirm-button-text='确定' style="margin-left: 10px;" cancel-button-text='我再想想' icon="el-icon-info"
            icon-color="red" title="您确定批量删除这些数据吗？" @confirm="batchDel()">
            <el-button type="danger" slot="reference">批量删除 </el-button>
        </el-popconfirm>
        <br><br>
        <!-- 表格数据 -->
        <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column label="ID" width="120">
                <template slot-scope="scope">{{ scope.row.id }}</template>
            </el-table-column>
            <el-table-column prop="articleTitle" label="标题" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="publishTime" label="发布时间" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="updateTime" label="修改时间" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="操作" show-overflow-tooltip>
                <template slot-scope="scope">
                    <el-button type="warning" size="small" @click="toEdit(scope.row.id)"
                        style="margin-right: 10px;">编辑</el-button>
                    <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
                        icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
                        <el-button size="small" type="danger" slot="reference">删除 </el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页导航条 -->
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
            value: true,
            form: {
                id: '',
                wxName: "",
            },
            tableData: [{
                "id": 70,
                "publishUser": "贺强",
                "articleTitle": "新疆维吾尔自治区 克拉玛依市 乌尔禾区",
                "articleIntroduction": "BeEebe6f-866f-316B-c656-32c6686f4d5C",
                "articleContent": "B5f4BD67-ED81-fcec-cF73-dbeD68A7cfEA",
                "publishTime": "1984-09-27 20:27:14",
                "updateTime": "1971-04-14 12:35:04",
                "views": 17672,
                "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
            },
            {
                "id": 37,
                "publishUser": "白杰",
                "articleTitle": "山西省 忻州市 神池县",
                "articleIntroduction": "2F61bC2e-dB4c-FF24-9DA8-b5dF6d13aECf",
                "articleContent": "e52f15Cb-2d2A-eAfA-2321-cC31AF89dad9",
                "publishTime": "2022-11-14 01:50:39",
                "updateTime": "1979-02-03 01:52:55",
                "views": 6115,
                "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
            },
            {
                "id": 53,
                "publishUser": "戴勇",
                "articleTitle": "福建省 莆田市 涵江区",
                "articleIntroduction": "Ef61EAf7-fBc7-1b7c-831f-e0fA31b81C8D",
                "articleContent": "Efd2e8DB-d5ee-bDCF-b575-9e9d83CD53AE",
                "publishTime": "1996-01-21 16:52:14",
                "updateTime": "2017-08-20 02:03:37",
                "views": 25281,
                "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
            },
            {
                "id": 78,
                "publishUser": "杜勇",
                "articleTitle": "重庆 重庆市 大渡口区",
                "articleIntroduction": "445debBD-C60A-7EBa-f11B-0EaBA3dbE70E",
                "articleContent": "70EC56c3-3854-D55C-D16f-DA3B87A952c8",
                "publishTime": "2015-07-28 18:59:26",
                "updateTime": "1983-05-22 09:06:27",
                "views": 87534,
                "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
            },
            {
                "id": 77,
                "publishUser": "田芳",
                "articleTitle": "湖北省 荆州市 石首市",
                "articleIntroduction": "0DB82841-8fF5-bF88-CAB4-44F37D75E5A2",
                "articleContent": "4cecfEbC-6FbB-C9DC-f6d9-eBfD997BD4cb",
                "publishTime": "2022-03-31 20:10:46",
                "updateTime": "1988-02-08 05:22:52",
                "views": 44223,
                "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
            },
            {
                "id": 70,
                "publishUser": "孙勇",
                "articleTitle": "浙江省 绍兴市 嵊州市",
                "articleIntroduction": "eBe1074f-824B-AEAc-1AB0-DEFEfeBCA5Fd",
                "articleContent": "Fee7DAc4-C6ED-12B7-3deF-0BBC3FeAE54d",
                "publishTime": "2010-05-05 14:53:58",
                "updateTime": "1996-07-25 05:30:16",
                "views": 58266,
                "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
            },
            {
                "id": 86,
                "publishUser": "龚强",
                "articleTitle": "青海省 玉树藏族自治州 称多县",
                "articleIntroduction": "B6bF0b9d-e02c-9819-814D-2Da35D43FdC1",
                "articleContent": "AFf6A9B4-2c92-5FAD-eBC8-c2FD8E4Cb33e",
                "publishTime": "1980-08-24 21:43:32",
                "updateTime": "2011-07-24 22:30:27",
                "views": 14613,
                "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
            },
            {
                "id": 52,
                "publishUser": "吴静",
                "articleTitle": "山东省 青岛市 胶州市",
                "articleIntroduction": "77eACFe1-6D3c-A0EF-F624-6a47E53Ac6CD",
                "articleContent": "3C2Ea27e-EA2f-fD6E-e5dB-eeBeBd43133A",
                "publishTime": "1978-03-09 07:04:41",
                "updateTime": "1971-01-11 11:00:06",
                "views": 23312,
                "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
            },
            {
                "id": 63,
                "publishUser": "彭勇",
                "articleTitle": "浙江省 衢州市 开化县",
                "articleIntroduction": "7B73FD1A-3B76-cE0e-c4b5-A1Cce9d0A7FD",
                "articleContent": "2E52Afb4-3EAd-34af-3D15-FD63cE0cFd67",
                "publishTime": "1970-07-15 11:25:28",
                "updateTime": "2011-11-14 10:24:53",
                "views": 39637,
                "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
            },
            {
                "id": 90,
                "publishUser": "韩洋",
                "articleTitle": "上海 上海市 静安区",
                "articleIntroduction": "EeA9e1AB-fb3d-B9dc-3D7E-7b951Dd426FF",
                "articleContent": "D5C1AFd3-7BB0-Ec8e-c358-C0E821C28ac4",
                "publishTime": "1996-07-10 20:45:20",
                "updateTime": "2022-02-07 18:58:25",
                "views": 75321,
                "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889"
            }],
            multipleSelection: []
        }
    },
    created() {
        this.form = localStorage.getItem("userInfo") ? this.userInfo = JSON.parse(localStorage.getItem("userInfo")) : this.$router.push("/");
        this.init()
    },
    methods: {
        toggleSelection(rows) {
            if (rows) {
                rows.forEach(row => {
                    this.$refs.multipleTable.toggleRowSelection(row);
                });
            } else {
                this.$refs.multipleTable.clearSelection();
            }
        },
        handleSelectionChange(val) {
            this.multipleSelection = val;
        },
        toAdd() {
            this.$router.push('/markdown')
        },
        toEdit(id) {
            this.$router.push({
                path: '/markdown',
                query: {
                    id
                }
            })
        },
        // ------------千篇一律-----------------------------------------------------------------------
        // 表格初始化
        init() {
            this.request({
                method: 'get',
                url: '/api/article/page-user',
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    param: this.form.wxName,
                }
            }).then(res => {
                this.total = res.data.data.total;
                this.tableData = res.data.data.records;
            }).catch(err => {
                console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
            });
        },
        // 切换页码
        handlePageChange(pageNum) {
            this.pageNum = pageNum
            this.init();
        },
        // id删除
        del(id) {
            this.request({
                method: "delete",
                url: "/api/article/delete/" + id
            }).then(res => {
                this.$message({
                    message: '删除成功',
                    type: 'success'
                });
                this.init();
            }).catch(err => {
                console.log(err)
            })
        },
        // 批量删除
        batchDel() {
            // console.log(this.multipleSelection)
            let ids = this.multipleSelection.map(v => v.id)  // [{}, {}, {}] => [1,2,3]
            this.request({
                method: "delete",
                url: "/api/article/batch-delete",
                data: JSON.stringify(ids)
            }).then(res => {
                this.$message({
                    message: '删除成功',
                    type: 'success'
                });
                this.init();
            }).catch(err => {
                console.log(err)
            })
            console.log(ids)
        },
        // 搜索
        search() {
            this.init();
        },
    }
}
</script>
  
<style></style>