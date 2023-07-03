<template>
    <div>
        <div id="myChart" ref="myChart" style="display: inline-block;" :style="{ width: '50%', height: '600px' }"></div>
        <div id="myChart2" ref="myChart2" style="display: inline-block;" :style="{ width: '50%', height: '600px' }"></div>
    </div>
</template>
<script>
export default {
    name: 'Echarts',
    data() {
        return {
            msg: 'Welcome use Echarts'
        }
    },
    mounted() {
        this.drawLine();
    },
    methods: {
        drawLine() {
            // 基于准备好的dom，初始化echarts实例
            let myChart = this.$echarts.init(this.$refs.myChart);
            let myChart2 = this.$echarts.init(this.$refs.myChart2);
            this.request({
                url: '/api/data-echart',
                method: 'get'
            }).then(res => {
                // 绘制图表
                myChart.setOption({
                    title: {
                        text: '网站数据统计',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left'
                    },
                    series: [
                        {
                            name: 'Access From',
                            type: 'pie',
                            radius: '50%',
                            data: [
                                { value: res.data.data[0], name: '用户数量' },
                                { value: res.data.data[1], name: '公告数量' },
                                { value: res.data.data[2], name: '文章数量' },
                                { value: res.data.data[3], name: '图片数量' },
                            ],
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                });

                this.request({
                    method: "get",
                    url: "/api/log/get-echart-data",
                }).then(res => {
                    // console.log(res.data.data)
                    const data = [];
                    const data2 = [];
                    Object.entries(res.data.data).forEach(([time, count]) => {
                        data.push(time);
                        data2.push(count);
                    });
                    myChart2.setOption({
                        title: {
                            text: '接口调用量统计',
                        },
                        xAxis: {
                            type: 'category',
                            data: data.reverse()
                        },
                        yAxis: {
                            type: 'value'
                        },
                        series: [
                            {
                                data: data2.reverse(),
                                type: 'line',
                                smooth: true
                            }
                        ]
                    })

                })

            }).catch(err => {
                console.log(err)
            })

        }
    }
}
</script>
