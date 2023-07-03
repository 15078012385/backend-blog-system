<template>
    <div>
        <!-- 文章标题 -->
        <el-input placeholder="请输入文章标题" v-model="form.articleTitle" style="width: 400px;margin-bottom: 10px;"></el-input>
        <el-divider></el-divider>
        <!-- 文章标题 -->
        <el-input type="textarea" :rows="2" placeholder="请输入文章简介" v-model="form.articleIntroduction">
        </el-input>
        <el-divider></el-divider>
        <!-- md编辑器 -->
        <mavon-editor ref=md style="height: 400px" v-model="form.articleContent" @imgAdd="$imgAdd"></mavon-editor>
        <br>
        <!-- 上传首图 -->
        <el-upload v-if="form.firstImg" class="upload-demo" action="http://111.230.56.200:9999/api/file/upload"
            :on-success="handleSuccessful" multiple :limit="1" :on-exceed="handleExceed" :on-remove="handleRemove"
            :before-remove="beforeRemove">
            <el-button size="small" type="primary">更新首图</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb。</div>
        </el-upload>

        <el-upload v-else class="upload-demo" action="http://111.230.56.200:9999/api/file/upload" :on-success="handleSuccessful"
            multiple :limit="1" :on-exceed="handleExceed" :on-remove="handleRemove" :before-remove="beforeRemove">
            <el-button size="small" type="primary">上传首图</el-button>
            <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，且不超过500kb。</div>
        </el-upload>

        <!-- 操作按钮 -->
        <div style="float: right;">
            <el-button type="danger" size="small" @click="cancle('/something-manage')">取消</el-button>
            <el-button type="primary" size="small" @click="submit()">确认</el-button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    created() {
        if (this.$route.query.id == null || this.$route.query.id == '') {
            return;
        }
        this.request({
            url: "/api/article/getById/" + this.$route.query.id,
            method: "get",
        }).then(resp => {
            this.form = resp.data.data;
        }).catch(
            err => {
                console.log(err)
            }
        )
    },
    data() {
        return {
            //  用articleId来判断是新增还是修改
            form: {
                id: '',
                articleContent: "",
                articleIntroduction: "",
                articleTitle: "",
                firstImg: "",
                publishTime: "",
                publishUser: JSON.parse(localStorage.getItem("userInfo")).wxName,
                updateTime: "",
                views: "",
                avatar: JSON.parse(localStorage.getItem("userInfo")).avatar,
            },
        }
    },
    methods: {
        // 删除首图
        handleRemove(file, fileList) {
            console.log(file, fileList);
            this.form.firstImg = "";
        },
        // 上传首图成功回调
        handleSuccessful(resp) {
            // console.log(resp);
            this.form.firstImg = resp;
        },
        // 上传首图超出限制回调
        handleExceed(files, fileList) {
            this.$message.warning(`当前限制选择 1 个文件.`);
        },
        // 删除前置回调
        beforeRemove(file, fileList) {
            return this.$confirm(`确定移除 ${file.name}？`);
        },
        // 提交
        submit() {
            console.log(JSON.parse(JSON.stringify(this.form)));
            if (this.form.id == '') {

                this.request({
                    method: "post",
                    url: "/api/article/add",
                    data: JSON.parse(JSON.stringify(this.form))
                }).then(resp => {
                    console.log(resp);
                    this.$message.success("新增成功");
                    this.$router.push("/something-manage");
                }).catch(err => {
                    console.log(err);
                })
            } else {
                this.request({
                    method: "put",
                    url: "/api/article/update",
                    data: JSON.parse(JSON.stringify(this.form))
                }).then(resp => {
                    console.log(resp);
                    this.$message.success("更新成功");
                    this.$router.push("/something-manage");
                }).catch(err => {
                    console.log(err);
                })
            }
        },
        // 取消
        cancle(path) {
            this.$router.push(path);
        },
        // md编辑器上传图片
        $imgAdd(pos, $file) {
            const imgData = new FormData();
            imgData.append('file', $file);
            axios({
                method: 'post',
                url: 'http://111.230.56.200:9999/api/file/upload',
                data: imgData,
                headers: {
                    'Content-Type': 'multipart/form-data'
                }
            }).then((res) => {
                // console.log(res.data);
                this.$refs.md.$img2Url(pos, res.data);
            }).catch((err) => {
                console.log(err)
            })
        },

    }
}
</script>

<style></style>