<template>
  <div>
    <!-- 搜索 -->
    <div style="text-align: center;">
      <el-input placeholder="请输入搜索内容" v-model="param" style="width: 400px;"></el-input>
      <el-button type="primary" style="margin-left: 10px;" @click="search()">搜索</el-button>
    </div>
    <el-popconfirm confirm-button-text='确定' style="margin-left: 10px;" cancel-button-text='我再想想' icon="el-icon-info"
      icon-color="red" title="您确定批量删除这些数据吗？" @confirm="batchDel()">
      <el-button type="danger" slot="reference">批量删除 </el-button>
    </el-popconfirm><br><br>
    <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column label="ID" width="120">
        <template slot-scope="scope">{{ scope.row.id }}</template>
      </el-table-column>
      <el-table-column prop="hotSearchContent" label="内容" show-overflow-tooltip>
      </el-table-column>
      <el-table-column prop="searchCount" label="热搜指数" show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="操作" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-button type="warning" size="small" @click="toEdit(scope.row)" style="margin-right: 10px;">编辑</el-button>
          <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
            icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button size="small" type="danger" slot="reference">删除 </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table> <el-pagination @current-change="handlePageChange" style="margin-top: 15px; text-align: center" background
      layout="prev, pager, next" :total="total">
    </el-pagination>
    <!-- 编辑 -->
    <el-dialog title="编辑" :visible.sync="dialogVisible" width="30%">
      <el-form :model="form" label-width="80px">
        <el-form-item label="内容">
          <el-input v-model="form.hotSearchContent"></el-input>
        </el-form-item>
        <el-form-item label="热搜指数">
          <el-input v-model="form.searchCount"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="save()">确 定</el-button>
      </span>
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
      value: true,
      form: {
        id: "",
        hotSearchContent: "",
        searchCount: ""
      },
      param: '',
      tableData: [{
        "id": 92,
        "hotSearchContent": "龚勇",
        "searchCount": 7924
      },
      {
        "id": 100,
        "hotSearchContent": "吕静",
        "searchCount": 9749
      },
      {
        "id": 75,
        "hotSearchContent": "杨军",
        "searchCount": 1552
      },
      {
        "id": 83,
        "hotSearchContent": "魏秀兰",
        "searchCount": 5766
      },
      {
        "id": 86,
        "hotSearchContent": "崔刚",
        "searchCount": 3973
      },
      {
        "id": 32,
        "hotSearchContent": "范涛",
        "searchCount": 5656
      },
      {
        "id": 42,
        "hotSearchContent": "魏芳",
        "searchCount": 3336
      },
      {
        "id": 88,
        "hotSearchContent": "萧丽",
        "searchCount": 5703
      },
      {
        "id": 26,
        "hotSearchContent": "郭艳",
        "searchCount": 4777
      },
      {
        "id": 28,
        "hotSearchContent": "田勇",
        "searchCount": 9457
      }],
      multipleSelection: []
    }
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
        url: '/api/hot-search/page',
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
      this.form = row;
      console.log(row.id)
    },
    // 编辑保存
    save() {
      // console.log('编辑保存')
      this.request({
        method: 'put',
        url: '/api/hot-search/update',
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
        url: "/api/hot-search/delete/" + id
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