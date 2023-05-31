<template>
  <div>
    <el-row :gutter = "10" style = "margin-bottom: 140px;">
      <el-col :span = "6">
        <el-card style = "color : #409EFF">
          <div >用户总数</div>
          <div style = "padding : 10px 0 ; text-align: center ; font-weight : bold;">
            15
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style = "color : #F56C6C">
          <div >销售总量</div>
          <div style = "padding : 10px 0 ; text-align: center ; font-weight : bold;">
            109806.5
          </div>
        </el-card>
      </el-col>
      <el-col :span = "6">
        <el-card style = "color : #67C23A">
          <div >收益总额</div>
          <div style = "padding : 10px 0 ; text-align: center ; font-weight : bold;">
            109806.5
          </div>
        </el-card>
      </el-col>
      <el-col :span = "6">
        <el-card style = "color : #E6A23C">
          <div > <i class = "el-icon-s-shop" style = "font-size: 20px"/> 公司总数</div>
          <div style = "padding : 10px 0 ; text-align: center ; font-weight : bold;">
            12
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span = "8" ><div id = "main" style = "width : 600px ; height : 500px"></div></el-col>
      <template>
        <el-col :span = "8"><div id = "Body" style = "width : 600px ; height : 500px"></div></el-col>
        <el-col :span = "8" ><div id = "Pie" style = "width : 600px ; height : 500px"></div></el-col>
      </template>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts';

export default {
  name: "Home",
  data(){
    return {
      userId : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).id: "",
      userName : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).nickname: "",
      role : localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")).userstatus: ""
    }
  },

  mounted(){//页面元素渲染之后再出发
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option = {
      title : {

        subtext : "趋势图",
        left : 'center'
      },
      xAxis: {
        type: 'category',
        data: []
      },
      yAxis: {
        type: 'value'
      },
      tooltip:{
        trigger:'item'
      },
      legend:{
        orient: 'vertical',
        left : 'left'
      },
      series: [
        {
          name :'',
          data: [],
          // data:[2,1,2,2],
          type: 'bar'
        },
        {
          name :'其他',
          data: [],

          type: 'bar'
        }
      ]
    };
    if(this.role === '1'){
      this.request.get("/echarts/cost/" + this.userId).then(res =>{
        console.log(this.userId)
        console.log(res)
        var chartDomBody = document.getElementById('Body');
        var myChartBody = echarts.init(chartDomBody);
        var optionBody;
        optionBody = {
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              // Use axis to trigger tooltip
              type: 'shadow' // 'shadow' as default; can also be 'line' or 'shadow'
            }
          },
          legend: {},
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'value'
          },
          yAxis: {
            type: 'category',
            data: ['第一季度', '第二季度', '第三季度', '第四季度']
          },
          series: [
            {
              name: '饮食花费',
              type: 'bar',
              stack: 'total',
              label: {
                show: true
              },
              emphasis: {
                focus: 'series'
              },
              data: [320, 302, 301, 334, 390, 330, 320]
            },
            {
              name: '住宿花费',
              type: 'bar',
              stack: 'total',
              label: {
                show: true
              },
              emphasis: {
                focus: 'series'
              },
              data: [1200, 1200, 1200, 1200, 1200, 1200, 1200]
            },
            {
              name: '事故花费',
              type: 'bar',
              stack: 'total',
              label: {
                show: true
              },
              emphasis: {
                focus: 'series'
              },
              data: [220, 182, 191, 234, 290, 330, 310]
            },
            {
              name: '护理花费',
              type: 'bar',
              stack: 'total',
              label: {
                show: true
              },
              emphasis: {
                focus: 'series'
              },
              data: [800, 912, 1201, 1054, 890, 730, 610]
            },
            {
              name: '药品花费',
              type: 'bar',
              stack: 'total',
              label: {
                show: true
              },
              emphasis: {
                focus: 'series'
              },
              data: [1040, 1096, 1093, 1022, 1290, 1330, 1320]
            }
          ]
        };
        optionBody && myChartBody.setOption(optionBody);

        option.title.text = res.data.companyName + "的销售总览", option.xAxis.data[0] = "饮食花费"
        option.xAxis.data[1] = "住宿花费"
        option.xAxis.data[2] = "事故花费"
        option.xAxis.data[3] = "护理花费"
        option.xAxis.data[4] = "药品花费"
        option.xAxis.data[4] = "总计"
        option.series[0].name = res.data.companyName
        //填空
        //option.xAxis.data = res.data.x
        option.series[0].data[0] = res.data.dishCost
        option.series[0].data[1] = res.data.dormCost
        option.series[0].data[2] = res.data.reportCost
        option.series[0].data[3] = res.data.nursingCost
        option.series[0].data[4] = res.data.drugCost
        option.series[0].data[5] = res.data.total
        option.series[1].data = [5,6,7,8]
        // 数据准备完毕之后再set
        myChart.setOption(option);
      })
    }

    if(this.role === '4'){
      this.request.get("/echarts/cost/" + this.userId).then(res =>{
        console.log(this.userId)
        console.log(res)

        var chartDomPie = document.getElementById('Pie');
        var myChartPie = echarts.init(chartDomPie);
        var optionPie;

        const gaugeData = [
          {
            value: res.data.healthy.sportHealthy,
            name: '运动指数',
            title: {
              offsetCenter: ['0%', '-30%']
            },
            detail: {
              valueAnimation: true,
              offsetCenter: ['0%', '-16%']
            }
          },
          {
            value: res.data.healthy.drugHealthy,
            name: '用药指数',
            title: {
              offsetCenter: ['0%', '-60%']
            },
            detail: {
              valueAnimation: true,
              offsetCenter: ['0%', '-46%']
            }
          },
          {
            value: res.data.healthy.dishHealthy,
            name: '饮食指数',
            title: {
              offsetCenter: ['0%', '0%']
            },
            detail: {
              valueAnimation: true,
              offsetCenter: ['0%', '14%']
            }
          },
          {
            value: res.data.healthy.synHealthy,
            name: '健康指数',
            title: {
              offsetCenter: ['0%', '30%']
            },
            detail: {
              valueAnimation: true,
              offsetCenter: ['0%', '44%']
            }
          }
        ];
        optionPie = {
          series: [
            {
              type: 'gauge',
              startAngle: 90,
              endAngle: -270,
              pointer: {
                show: false
              },
              progress: {
                show: true,
                overlap: false,
                roundCap: true,
                clip: false,
                itemStyle: {
                  borderWidth: 2,
                  borderColor: '#464646'
                }
              },
              axisLine: {
                lineStyle: {
                  width: 40
                }
              },
              splitLine: {
                show: false,
                distance: 0,
                length: 10
              },
              axisTick: {
                show: false
              },
              axisLabel: {
                show: false,
                distance: 50
              },
              data: gaugeData,
              title: {
                fontSize: 20
              },
              detail: {
                width: 50,
                height: 14,
                fontSize: 16,
                color: 'inherit',
                borderColor: 'inherit',
                borderRadius: 20,
                borderWidth: 1,
                formatter: '{value}%'
              }
            }
          ]
        };
        optionPie && myChartPie.setOption(optionPie);
        option.title.text = this.userName + "的花费总览",
            option.xAxis.data[0] = "饮食花费"
        option.xAxis.data[1] = "住宿花费"
        option.xAxis.data[2] = "事故花费"
        option.xAxis.data[3] = "护理花费"
        option.xAxis.data[4] = "药品花费"
        option.xAxis.data[5] = "总计"
        option.series[0].name = res.data.companyName
        //填空
        //option.xAxis.data = res.data.x
        option.series[0].data[0] = res.data.dishCost
        option.series[0].data[1] = res.data.dormCost
        option.series[0].data[2] = res.data.reportCost
        option.series[0].data[3] = res.data.nursingCost
        option.series[0].data[4] = res.data.drugCost
        option.series[0].data[5] = res.data.total
        option.series[1].data = [5,6,7,8]
        // 数据准备完毕之后再set
        myChart.setOption(option);
      })
    }

  }
}
</script>

<style scoped>

</style>