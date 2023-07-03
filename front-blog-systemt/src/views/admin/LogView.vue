<template>
    <div>
        <el-table :data="tableData" border style="width: 100%">
            <!-- <el-table-column prop="id" label="ID" width="80" sortable></el-table-column> -->
            <el-table-column prop="accessTime" label="访问时间">
                <template slot-scope="scope">
                    <el-tag type="success">{{ scope.row.accessTime }}</el-tag>
                </template>
            </el-table-column>
            <!-- <el-table-column prop="url" :show-overflow-tooltip="true" label="接口地址" width="400">
                <template slot-scope="scope">
                    <el-tag>{{ scope.row.accessApi }}</el-tag>
                </template>
            </el-table-column> -->
            <el-table-column prop="accessApi" :show-overflow-tooltip="true" label="接口地址">
                <template slot-scope="scope">
                    <el-popover placement="top" width="400" trigger="hover">
                        <!-- 完整内容 -->
                        <p style="background-image: linear-gradient(120deg, #84fab0 0%, #8fd3f4 100%);">
                            {{ scope.row.accessApi }}</p>
                        <!--  简略内容 -->
                        <div slot="reference" class="name-wrapper">
                            {{ scope.row.accessApi.substring(0, 20) }}...
                        </div>
                    </el-popover>
                </template>
            </el-table-column>

            <el-table-column prop="timeConsume" :show-overflow-tooltip="true" label="耗时ms">
            </el-table-column>
            <el-table-column prop="accessParams" :show-overflow-tooltip="true" label="入参">
                <template slot-scope="scope">
                    <el-popover placement="top" width="400" trigger="hover">
                        <!-- 完整内容 -->
                        <p style="background-image: linear-gradient(120deg, #84fab0 0%, #8fd3f4 100%);">
                            {{ scope.row.accessParams }}</p>
                        <!--  简略内容 -->
                        <div slot="reference" class="name-wrapper">
                            {{ scope.row.accessParams.substring(0, 20) }}...
                        </div>
                    </el-popover>
                </template>
            </el-table-column>

            <el-table-column prop="respData" :show-overflow-tooltip="true" label="出参">
                <template slot-scope="scope">
                    <el-popover placement="top" width="400" trigger="hover">
                        <!-- 完整内容 -->
                        <p style="background-image: linear-gradient(120deg, #84fab0 0%, #8fd3f4 100%);">
                            {{ scope.row.respData }}</p>
                        <!--  简略内容 -->
                        <div slot="reference" class="name-wrapper">
                            {{ scope.row.respData.substring(0, 20) }}...
                        </div>
                    </el-popover>
                </template>
            </el-table-column>
            <el-table-column prop="url" label="访问IP">
                <template slot-scope="scope">
                    <el-tag>{{ scope.row.accessIp }}</el-tag>
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
    created: function () {
        this.init();
    },
    data() {
        return {
            total: 0,
            pageNum: 1,
            pageSize: 10,
            tableData: [{
                "id": 13,
                "accessTime": "1970-08-30 08:47:39",
                "accessApi": "http://enrspux.ro/ibnfbwr",
                "accessParams": "sr",
                "respData": "Df3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898eDf3c16b1-Cc6c-f1be-0ACf-DFBBA4e1898e",
                "accessIp": "21.231.17.148"
            },
            {
                "id": 25,
                "accessTime": "1996-12-30 10:42:23",
                "accessApi": "http://birtfwhc.is/ygfdnwn",
                "accessParams": "ws",
                "respData": "4321fA7e-7BCe-fb2F-dfEE-3BCb431De9E2",
                "accessIp": "47.175.71.190"
            },
            {
                "id": 17,
                "accessTime": "2018-04-18 14:29:41",
                "accessApi": "http://bwnovfse.yu/penhclgtl",
                "accessParams": "se",
                "respData": "D1E7FcAE-4ad3-81Ce-66Fc-1ddbF897FeaC",
                "accessIp": "69.236.150.71"
            },
            {
                "id": 39,
                "accessTime": "1994-06-10 20:16:25",
                "accessApi": "http://svvmvj.bv/bpzksgvlbm",
                "accessParams": "lu",
                "respData": "ED29e335-6Cf9-C3b5-AA8d-D9EDAFBcfd09",
                "accessIp": "137.177.66.120"
            },
            {
                "id": 55,
                "accessTime": "2016-11-12 14:52:26",
                "accessApi": "http://eflkhfoe.vc/zjihtifcy",
                "accessParams": "pro",
                "respData": "1df4Bed2-7e0b-C774-d1Db-CD7f9aFe0f06",
                "accessIp": "151.164.220.154"
            },
            {
                "id": 89,
                "accessTime": "1970-09-27 20:37:24",
                "accessApi": "http://cuupbwnl.sk/hxfecvsk",
                "accessParams": "fk",
                "respData": "dFB973FB-a535-8C2C-7D67-cFbf26eDBCC2",
                "accessIp": "53.154.240.123"
            },
            {
                "id": 73,
                "accessTime": "1992-09-20 12:40:39",
                "accessApi": "http://fdmrkkgo.kg/scrwn",
                "accessParams": "int",
                "respData": "81C7f3C2-3c11-34F4-CbE7-F7e116Af0B11",
                "accessIp": "171.79.191.234"
            },
            {
                "id": 55,
                "accessTime": "1971-08-28 18:01:12",
                "accessApi": "http://htpdurg.gn/wuvyuvih",
                "accessParams": "et",
                "respData": "884ebfFB-96FC-6b04-88AA-D19F83f1183B",
                "accessIp": "181.172.215.100"
            },
            {
                "id": 35,
                "accessTime": "1971-12-02 14:29:58",
                "accessApi": "http://mhxgcknfyx.org.cn/mfgggwlkd",
                "accessParams": "ky",
                "respData": "D42522CF-3A1d-5Cc2-8Ef1-eCA569aA17CC",
                "accessIp": "173.74.73.202"
            },
            {
                "id": 43,
                "accessTime": "1979-05-22 23:56:32",
                "accessApi": "http://ipvwfydxpj.do/mewroo",
                "accessParams": "pf",
                "respData": "F1b45cCC-3e70-77EB-94Fb-7E926fce3Eb9",
                "accessIp": "57.74.112.135"
            }]
        }
    },
    methods: {
        showAllText(text) {
            console.log(text)
        },
        init() {
            this.request({
                method: 'get',
                url: '/api/log/page',
                params: {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                }
            }).then(res => {
                this.total = res.data.data.total;
                this.tableData = res.data.data.records;
            }).catch(err => {
                console.log("======>找不到服务器，/(ㄒoㄒ)/~~");
                this.$message({
                    message: '抱歉，无法连接服务器。/(ㄒoㄒ)/~~',
                    type: 'error'
                })
            });
        },
        handlePageChange(pageNum) {
            this.pageNum = pageNum
            this.init()
        },
    }

}
</script>

<style></style>