<template>
    <div style="background-color: #ffffff">
        <!-- <div style="margin: 20px;" v-html="form.articleContent"></div> -->
        <!-- <VueMarkdown style="padding: 50px;" v-highlight class="markdown-body" :source="form.articleContent"></VueMarkdown> -->
        <div class="markdown-body">
            <VueMarkdown :source="form.articleContent" style="padding: 50px;" v-highlight></VueMarkdown>
        </div>

        <div style="text-align: center;padding-bottom: 50px;"> <el-button type="success" @click="lick()"
                round>点赞</el-button></div>
    </div>
</template>

<script>
import VueMarkdown from 'vue-markdown'
export default {
    components: {
        VueMarkdown // 注入组件
    },
    created() {
        if (localStorage.getItem("userInfo") == null) {
            this.$message({
                message: '请先登录',
                type: 'error'
            });
            return
        }
        localStorage.setItem('currentPath', '/article')
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
            likeInfo: {
                articleId: this.$route.query.id,
                articleTitle: this.$route.query.articleTitle,
                articleUser: this.$route.query.articleUser,
                likeUser: JSON.parse(localStorage.getItem("userInfo")).wxName,
            },
            form: {
                id: '',
                articleContent: "",
                articleIntroduction: "",
                articleTitle: "",
                firstImg: "",
                publishTime: "",
                publishUser: "",
                updateTime: "",
                views: "",
            },
        }
    },
    computed: {
        lick() {
            // console.log(JSON.stringify(this.likeInfo))
            return function () {
                this.request({
                    url: "/api/like/add",
                    method: "post",
                    data: JSON.stringify(this.likeInfo)
                }).then(resp => {

                    console.log(resp.data.code == 200 ? "点赞成功" : "点赞失败")
                    if (resp.data.code == 200) {
                        this.$message({
                            message: '点赞成功',
                            type: 'success'
                        });
                    } else if (resp.data.code == 500) {
                        this.$message({
                            message: resp.data.msg,
                            type: 'error'
                        });
                    }
                }).catch(
                    err => {
                        console.log(err)
                    }
                )
            }
        }
    }

}
</script>

<style></style>