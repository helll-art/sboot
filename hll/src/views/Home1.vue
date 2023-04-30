<!--需要安装依赖
  npm
-->
<template>
  <div>
    <el-row :gutter = "10" style = "margin-bottom: 140px;">
      <el-col :span = "6">
        <el-card style = "color : #409EFF">
          <div >用户总数</div>
          <div style = "padding : 10px 0 ; text-align: center ; font-weight : bold;">
            100
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card style = "color : #F56C6C">
          <div >销售总量</div>
          <div style = "padding : 10px 0 ; text-align: center ; font-weight : bold;">
            100000
          </div>
        </el-card>
      </el-col>
      <el-col :span = "6">
        <el-card style = "color : #67C23A">
          <div >收益总额</div>
          <div style = "padding : 10px 0 ; text-align: center ; font-weight : bold;">
            300000
          </div>
        </el-card>
      </el-col>
      <el-col :span = "6">
        <el-card style = "color : #E6A23C">
          <div > <i class = "el-icon-s-shop" style = "font-size: 20px"/> 门店总数</div>
          <div style = "padding : 10px 0 ; text-align: center ; font-weight : bold;">
            20
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span = "12" ><div id = "main" style = "width : 500px ; height : 400px"></div></el-col>
      <el-col :span = "12" ><div id = "pie" style = "width : 500px ; height : 400px"></div></el-col>
    </el-row>

  </div>
</template>

<script>
import * as echarts from 'echarts';
export default {
  name: "Home",
  data(){
    return {
    }
  },

  mounted(){//页面元素渲染之后再出发
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var option = {
      title : {
        text : "个季度会员数量统计",
        subtext : "趋势图",
        left : 'center'
      },
      xAxis: {
        type: 'category',
        data: ["第一季度","第二季度","第三季度","第四季度"]
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
          name :'星巴克',
          data: [],
          // data:[2,1,2,2],
          type: 'bar'
        },
        {
          name :'瑞幸',
          data: [],

          type: 'bar'
        }
      ]
    };

    this.request.get("/echarts/members").then(res =>{

      //填空
      //option.xAxis.data = res.data.x
      option.series[0].data = res.data
      option.series[1].data = [5,6,7,8]
      // 数据准备完毕之后再set
      myChart.setOption(option);

    })

    //饼图 p18 58:00
    var pieOption = {
      legend: {
        top: 'bottom'
      },
      toolbox: {
        show: true,
        feature: {
          mark: { show: true },
          dataView: { show: true, readOnly: false },
          restore: { show: true },
          saveAsImage: { show: true }
        }
      },
      series: [
        {
          name: 'Nightingale Chart',
          type: 'pie',
          radius: [50, 250],
          center: ['50%', '60%'],
          roseType: 'area',
          itemStyle: {
            borderRadius: 8
          },
          data: [
            { value: 40, name: 'rose 1' },
            { value: 38, name: 'rose 2' },
            { value: 32, name: 'rose 3' },
            { value: 30, name: 'rose 4' },
            { value: 28, name: 'rose 5' },
            { value: 26, name: 'rose 6' },
            { value: 22, name: 'rose 7' },
            { value: 18, name: 'rose 8' }
          ]
        }
      ]
    };
    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);

    pieChart.setOption(pieOption);

  }
}
</script>

<style scoped>

</style>