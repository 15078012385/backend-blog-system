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
      <el-table-column label="头像">
        <template slot-scope="scope">
          <el-image style="width: 100px; height: 50px" :src="scope.row.avatar"
            :preview-src-list="[scope.row.avatar]"></el-image>
        </template>
      </el-table-column>
      <el-table-column prop="wxId" label="微信ID" show-overflow-tooltip>
      </el-table-column>
      <el-table-column prop="wxName" label="微信名" show-overflow-tooltip>
      </el-table-column>
      <el-table-column prop="createTime" label="创建时间" show-overflow-tooltip>
      </el-table-column>
      <el-table-column label="是否禁用" show-overflow-tooltip>
        <template slot-scope="scope">
          <el-switch v-model="scope.row.availableStatus" :active-value="0" :inactive-value="1" active-color="#13ce66"
            inactive-color="#ff4949" @change="handleAvailableChange(scope.row)">
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column label="身份" prop="identity" show-overflow-tooltip>
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
      <el-form label-width="80px" size="small">
        <el-form-item label="头像">
          <el-upload class="avatar-uploader" action="http://111.230.56.200:9999/api/file/upload"
            :on-success="handleAvatarSuccess">
            <img v-if="form.avatar" :src="form.avatar" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="ID">
          <el-input v-model="form.id" autocomplete="off" disabled></el-input>
        </el-form-item>
        <el-form-item label="微信名">
          <el-input v-model="form.wxName" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="save">确 定</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {

      param: "",
      dialogVisible: false,
      value1: true,
      value2: false,
      total: 0,
      pageNum: 1,
      pageSize: 10,
      form: {
        id: '',
        avatar: '',
        wxName: ''
      },
      form2: {
        id: "",
        availableStatus: ""
      },
      tableData: [{
        "id": 10,
        "avatar": "https://img1.baidu.com/it/u=288160336,1031975834&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "wxId": "0d150d9A-e249-F238-E7ef-bdDeffb01Bc7",
        "wxName": "孙军",
        "createTime": "1984-05-17 15:51:51",
        "availableStatus": "0",
        "identity": "普通用户"
      },
      {
        "id": 26,
        "avatar": "https://img1.baidu.com/it/u=288160336,1031975834&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "wxId": "6563E31F-3F97-c29F-829D-3FA98bCe2a2B",
        "wxName": "钱秀英",
        "createTime": "2002-03-25 06:26:51",
        "availableStatus": "0",
        "identity": "普通用户"
      },
      {
        "id": 89,
        "avatar": "https://img1.baidu.com/it/u=288160336,1031975834&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "wxId": "722D0339-b1Af-7415-fE26-66d6Ea94E0EE",
        "wxName": "常洋",
        "createTime": "1981-03-22 00:03:08",
        "availableStatus": "0",
        "identity": "普通用户"
      },
      {
        "id": 69,
        "avatar": "https://img1.baidu.com/it/u=288160336,1031975834&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "wxId": "DFda4E1b-CD67-C3Bb-dBa1-6Ff7a4a97b3D",
        "wxName": "谭强",
        "createTime": "2014-09-04 19:33:36",
        "availableStatus": "0",
        "identity": "普通用户"
      },
      {
        "id": 74,
        "avatar": "https://img1.baidu.com/it/u=288160336,1031975834&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "wxId": "6FA18ded-F75E-41ee-199F-7e8FAcD42B1e",
        "wxName": "文秀兰",
        "createTime": "2005-01-29 22:28:54",
        "availableStatus": "0",
        "identity": "普通用户"
      },
      {
        "id": 31,
        "avatar": "https://img1.baidu.com/it/u=288160336,1031975834&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "wxId": "cEA2665e-Ed1b-9B21-9EAf-47e78Fd81feb",
        "wxName": "姚秀英",
        "createTime": "1970-05-30 17:40:50",
        "availableStatus": "0",
        "identity": "普通用户"
      },
      {
        "id": 60,
        "avatar": "https://img1.baidu.com/it/u=288160336,1031975834&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "wxId": "eEc7e89D-fc83-6bDF-9f6f-b24beCadbC53",
        "wxName": "孙娟",
        "createTime": "2022-05-12 01:47:29",
        "availableStatus": "0",
        "identity": "普通用户"
      },
      {
        "id": 61,
        "avatar": "https://img1.baidu.com/it/u=288160336,1031975834&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "wxId": "edF4777A-B383-cBEf-5EfA-Eeb4f31c67Af",
        "wxName": "萧超",
        "createTime": "1983-05-02 16:32:18",
        "availableStatus": "0",
        "identity": "普通用户"
      },
      {
        "id": 6,
        "avatar": "https://img1.baidu.com/it/u=288160336,1031975834&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "wxId": "8DC55B53-c588-c3f7-22bD-CFB4abDd3d97",
        "wxName": "何芳",
        "createTime": "2017-11-03 01:15:01",
        "availableStatus": "0",
        "identity": "普通用户"
      },
      {
        "id": 15,
        "avatar": "https://img1.baidu.com/it/u=288160336,1031975834&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500",
        "wxId": "8c56c28c-5aeD-b4d9-05EB-f54def40662A",
        "wxName": "任强",
        "createTime": "1983-09-18 17:06:05",
        "availableStatus": "0",
        "identity": "普通用户"
      }],
      multipleSelection: []
    }
  },
  created() { this.init() },
  methods: {
    handlePageChange(pageNum) {
      console.log(pageNum)
    },
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
    handleAvatarSuccess(resp) {
      // console.log( resp)
      if (resp.length > 0) {
        this.form.avatar = resp;
        this.$message.success(`上传成功`);
      }
    },
    // ------------千篇一律-----------------------------------------------------------------------
    // 表格初始化
    init() {
      this.request({
        method: 'get',
        url: '/api/user/page',
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
      console.log(row)
    },
    // 编辑保存
    save() {
      // console.log('编辑保存')
      this.request({
        method: 'put',
        url: '/api/user/update',
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
        url: "/api/user/delete/" + id
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
        url: "/api/user/batch-delete",
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
    handleAvailableChange(row) {
      // console.log(row)
      this.request({
        method: 'put',
        url: '/api/user/update',
        data: JSON.stringify(row),
      }).then(res => {
        if (res.data.code == 200) {
          if (row.availableStatus == 0) {
            this.$message({
              message: '解封成功',
              type: 'success'
            });
          } else {
            this.$message({
              message: '封禁成功',
              type: 'error'
            });
          }
          this.init();
        } else {
          this.$message({
            message: '操作失败',
            type: 'error'
          });
        }
      }).catch(err => {
        console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
      });
    }
  }
}
</script>

<style>
.avatar-uploader {
  text-align: center;
  padding-bottom: 10px;
}

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 138px;
  height: 138px;
  line-height: 138px;
  text-align: center;
}

.avatar {
  width: 138px;
  height: 138px;
  display: block;
}
</style>