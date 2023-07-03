<template>
    <div> <el-button type="primary" slot="reference" @click="toAdd()">发布推荐 </el-button>
        <br><br>
        <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%">
            <el-table-column label="ID" width="120">
                <template slot-scope="scope">{{ scope.row.id }}</template>
            </el-table-column>
            <el-table-column prop="recommendContent" label="推荐内容" show-overflow-tooltip>
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
        </el-table>
        <!-- 编辑 -->
        <el-dialog title="编辑" :visible.sync="dialogVisible" width="30%">
            <el-form label-width="80px" size="small">
                <el-form-item label="ID">
                    <el-input v-model="form.id" autocomplete="off" disabled></el-input>
                </el-form-item>
                <el-form-item label="推荐内容">
                    <el-input v-model="form.recommendContent" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="save">确 定</el-button>
                </el-form-item>
            </el-form>
        </el-dialog>
        <!-- 添加 -->
        <el-dialog title="添加" :visible.sync="dialogVisible2" width="30%">
            <el-form label-width="80px" size="small">
                <el-form-item label="ID" style="display: none;">
                    <el-input v-model="form2.id" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="推荐内容">
                    <el-input v-model="form2.recommendContent" autocomplete="off"></el-input>
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
                recommendContent: ""
            },
            form2: {
                id: "",
                recommendContent: ""
            },

            value: true,
            tableData: [{
                id: '1',
                recommendContent: '王小虎',
            }, {
                id: '2',
                recommendContent: '王小虎',
            }, {
                id: '3',
                recommendContent: '王小虎',
            }, {
                id: '4',
                recommendContent: '王小虎',
            },],
            multipleSelection: []
        }
    },
    created() {
        this.init();
    },
    methods: {
        // ------------千篇一律-----------------------------------------------------------------------
        // 表格初始化
        init() {
            this.request({
                method: 'get',
                url: '/api/recommend/list',
            }).then(res => {
                this.tableData = res.data.data;
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
            this.form = row;
            console.log(row.id)
        },
        // 编辑保存
        save() {
            // console.log('编辑保存')
            this.request({
                method: 'put',
                url: '/api/recommend/update',
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
                url: '/api/recommend/add',
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
                url: "/api/recommend/delete/" + id
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
                url: "/api/hot-search/batch-delete",
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