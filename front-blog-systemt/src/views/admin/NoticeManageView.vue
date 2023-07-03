<template>
    <div>
        <!-- 搜索 -->
        <div style="text-align: center;">
            <el-input placeholder="请输入搜索内容" v-model="param" style="width: 400px;"></el-input>
            <el-button type="primary" style="margin-left: 10px;" @click="search()">搜索</el-button>
        </div>
        <el-button type="primary" slot="reference" @click="toAdd()">发布公告 </el-button>
        <br><br>
        <el-popconfirm confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info" icon-color="red"
            title="您确定批量删除这些数据吗？" @confirm="batchDel()">
            <el-button type="danger" slot="reference">批量删除 </el-button>
        </el-popconfirm>
        <br><br>
        <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55">
            </el-table-column>
            <el-table-column label="ID" width="120">
                <template slot-scope="scope">{{ scope.row.id }}</template>
            </el-table-column>
            <el-table-column prop="noticeTime" label="日期" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="noticeContent" label="内容" show-overflow-tooltip>
            </el-table-column>
            <el-table-column prop="publishUser" label="用户" show-overflow-tooltip>
            </el-table-column>
            <el-table-column label="操作" show-overflow-tooltip>
                <template slot-scope="scope">
                    <el-button type="warning" size="small" @click="toEdit(scope.row)"
                        style="margin-right: 10px;">编辑</el-button>
                    <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
                        icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
                        <el-button size="small" type="danger" slot="reference">删除 </el-button>
                    </el-popconfirm>
                </template>
            </el-table-column>
        </el-table> <el-pagination @current-change="handlePageChange" style="margin-top: 15px; text-align: center"
            background layout="prev, pager, next" :total="total">
        </el-pagination>
        <!-- 编辑 -->
        <el-dialog title="编辑" :visible.sync="dialogVisible" width="30%">
            <el-form label-width="80px" size="small">
                <el-form-item label="ID">
                    <el-input v-model="form.id" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="日期">
                    <el-input v-model="form.noticeTime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="内容">
                    <el-input v-model="form.noticeContent" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="发布人">
                    <el-input v-model="form.publishUser" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="save">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <!--添加 -->
        <el-dialog title="添加" :visible.sync="dialogVisible2" width="30%">
            <el-form label-width="80px" size="small">
                <el-form-item label="日期">
                    <el-input v-model="form2.noticeTime" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="内容">
                    <el-input v-model="form2.noticeContent" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="发布人">
                    <el-input v-model="form2.publishUser" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="save2">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
    </div>
</template>
  
<script>
export default {
    created() {
        this.init();
    },
    data() {
        return {
            total: 0,
            pageNum: 1,
            pageSize: 10,
            dialogVisible: false,
            dialogVisible2: false,
            value: true,
            form: {
                id: "",
                noticeContent: "",
                publishUser: "",
                noticeTime: ""
            },
            form2: {
                noticeContent: "",
                publishUser: "",
                noticeTime: ""
            },
            param: '',
            tableData: [{
                "id": 72,
                "publishUser": "林勇",
                "noticeContent": "3AD44F9F-47a4-6FC6-edb2-43D7bE8c4Cb7",
                "noticeTime": "1974-04-21 21:50:32"
            },
            {
                "id": 21,
                "publishUser": "汤超",
                "noticeContent": "bfA3C664-1e3C-bf69-bb1C-a5d6567A1EAb",
                "noticeTime": "1975-10-05 00:13:30"
            },
            {
                "id": 16,
                "publishUser": "程杰",
                "noticeContent": "a38ACAd1-325E-3DEE-Fbc7-aF135cF198bc",
                "noticeTime": "1985-05-09 13:21:31"
            },
            {
                "id": 15,
                "publishUser": "戴军",
                "noticeContent": "de91ceA5-F8EB-fA1F-E4d5-2e2Fb821CFEe",
                "noticeTime": "1976-03-15 10:19:44"
            },
            {
                "id": 46,
                "publishUser": "王平",
                "noticeContent": "D835C173-B68a-07DE-CDBB-13BefecCca70",
                "noticeTime": "1998-04-08 20:45:49"
            },
            {
                "id": 76,
                "publishUser": "蔡杰",
                "noticeContent": "aecbACbE-4B1F-BDCe-5cF8-d785DEC350C3",
                "noticeTime": "1970-01-28 17:09:11"
            },
            {
                "id": 84,
                "publishUser": "萧娜",
                "noticeContent": "7e7d92d8-CB23-9cDc-dEBF-bc079423DF78",
                "noticeTime": "2015-11-23 03:42:39"
            },
            {
                "id": 34,
                "publishUser": "龚磊",
                "noticeContent": "63bE7eFf-CCCE-e8AB-7ABd-c73c8077d58f",
                "noticeTime": "2008-07-31 02:54:36"
            },
            {
                "id": 66,
                "publishUser": "康平",
                "noticeContent": "69f26AB4-FC54-8FaF-88b9-1DAe0d89B2e3",
                "noticeTime": "2021-11-12 00:02:22"
            },
            {
                "id": 5,
                "publishUser": "萧勇",
                "noticeContent": "ebbA77cb-34E1-0E72-cAa8-aCb35dD92fBC",
                "noticeTime": "1987-10-12 08:37:20"
            }],
            multipleSelection: []
        }
    },
    created(){
        this.init();
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
        // ------------千篇一律-----------------------------------------------------------------------
        // 表格初始化
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
        // 打开新增
        toAdd() {
            this.dialogVisible2 = true;
        },
        // 打开编辑
        toEdit(row) {
            this.dialogVisible = true;
            this.form = null;
            this.form = row;
            console.log(row.id)
        },
        // 编辑保存
        save() {
            // console.log('编辑保存')
            this.request({
                method: 'put',
                url: '/api/notice/update',
                data: JSON.stringify(this.form),
            }).then(res => {
                if (res.data.code == 200) {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    this.init();
                } else {
                    this.$message({
                        message: '修改失败',
                        type: 'error'
                    });
                }
            }).catch(err => {
                console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
            });
            this.dialogVisible = false;
        },
        // 新增保存
        save2() {
            this.request({
                method: 'post',
                url: '/api/notice/add',
                data: JSON.stringify(this.form2),
            }).then(res => {
                if (res.data.code == 200) {
                    this.$message({
                        message: '修改成功',
                        type: 'success'
                    });
                    // 清空表单
                    this.form2 = {
                        publishUser: '',
                        noticeContent: '',
                        noticeTime: ''
                    }
                    this.init();
                } else {
                    this.$message({
                        message: '修改失败',
                        type: 'error'
                    });
                }
            }).catch(err => {
                console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
            });

            this.dialogVisible2 = false;
        },
        // id删除
        del(id) {
            this.request({
                method: "delete",
                url: "/api/notice/delete/" + id
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
                url: "/api/notice/batch-delete",
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