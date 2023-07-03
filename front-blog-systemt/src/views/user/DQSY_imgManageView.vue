<template>
  <div>
    <!-- 上传美照 -->
    <el-upload class="upload-demo" action="http://111.230.56.200:9999/api/file/upload" :on-success="handleSuccessful" multiple>
      <el-button type="primary">上传美照</el-button>
      <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb。</div>
    </el-upload>
    <br>
    <!-- 批量删除 -->
    <el-popconfirm confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info" icon-color="red"
      title="您确定批量删除这些数据吗？" @confirm="batchDel()">
      <el-button type="danger" slot="reference">批量删除 </el-button>
    </el-popconfirm>
    <br><br>
    <!-- table表格 -->
    <el-table ref="multipleTable" :data="tableData" tooltip-effect="dark" style="width: 100%"
      @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55">
      </el-table-column>
      <el-table-column label="ID" width="120">
        <template slot-scope="scope">{{ scope.row.id }}</template>
      </el-table-column>
      <el-table-column label="图片">
        <template slot-scope="scope">
          <el-image style="width: 100px; height: 50px" :src="scope.row.img" :preview-src-list="[scope.row.img]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column label="上传时间">
        <template slot-scope="scope">
          <el-tag type="success">{{ scope.row.uploadTime }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="操作" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-popconfirm class="ml-5" confirm-button-text='确定' cancel-button-text='我再想想' icon="el-icon-info"
            icon-color="red" title="您确定删除吗？" @confirm="del(scope.row.id)">
            <el-button size="small" type="danger" slot="reference">删除 </el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页导航 -->
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
      form: {
        id: '',
        uploadUser: JSON.parse(localStorage.getItem("userInfo")).wxName
      },
      value: true,
      tableData: [{
        "id": 91,
        "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889",
        "uploadUser": "宋平",
        "uploadTime": "2013-10-09 15:27:24"
      },
      {
        "id": 40,
        "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889",
        "uploadUser": "姜霞",
        "uploadTime": "2021-08-20 16:46:44"
      },
      {
        "id": 79,
        "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889",
        "uploadUser": "孔秀英",
        "uploadTime": "2003-11-18 17:07:34"
      },
      {
        "id": 37,
        "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889",
        "uploadUser": "乔涛",
        "uploadTime": "2001-07-21 18:06:18"
      },
      {
        "id": 55,
        "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889",
        "uploadUser": "丁艳",
        "uploadTime": "1999-03-24 12:26:54"
      },
      {
        "id": 84,
        "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889",
        "uploadUser": "董明",
        "uploadTime": "1970-08-20 20:47:50"
      },
      {
        "id": 25,
        "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889",
        "uploadUser": "郝静",
        "uploadTime": "2016-11-22 11:31:30"
      },
      {
        "id": 45,
        "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889",
        "uploadUser": "秦秀英",
        "uploadTime": "2018-10-03 19:24:22"
      },
      {
        "id": 100,
        "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889",
        "uploadUser": "高涛",
        "uploadTime": "2014-08-26 10:11:57"
      },
      {
        "id": 42,
        "img": "https://img1.baidu.com/it/u=1390383800,428797916&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=889",
        "uploadUser": "曹磊",
        "uploadTime": "1997-10-20 20:11:26"
      }],
      multipleSelection: []
    }
  },
  created() {
    // this.form = localStorage.getItem("userInfo") ? this.userInfo = JSON.parse(localStorage.getItem("userInfo")) : this.$router.push("/");
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
    // 上传首图成功回调
    handleSuccessful(resp) {
      // console.log( resp)
      if (resp.length > 0) {
        this.form.img = resp;
        this.request({
          method: 'post',
          url: '/api/img/add',
          data: JSON.stringify(this.form),
        }).then(res => {
          this.$message.success(`上传成功`);
          this.init();
        }).catch(err => {
          console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
        })
      }
    },
    // ------------千篇一律-----------------------------------------------------------------------
    // 表格初始化
    init() {
      this.request({
        method: 'get',
        url: '/api/img/page-user',
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          param: JSON.parse(localStorage.getItem("userInfo")).wxName,
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
        url: "/api/img/delete/" + id
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
        url: "/api/img/batch-delete",
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