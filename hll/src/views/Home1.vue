<!--需要安装依赖
  npm
-->
<template>
  <div>
    <el-row>
      <el-col :span = "12" ><div id = "main" style = "width : 500px ; height : 400px"></div></el-col>
      <el-col :span = "12"><div id = "pie" style = "width : 500px ; height : 400px"></div></el-col>
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
      series: [
        {
          data: [],
          // data:[2,1,2,2],
          type: 'line'
        }
      ]
    };

    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    this.request.get("/echarts/members").then(res =>{
      //填空
      //option.xAxis.data = res.data.x
      this.option.series.data = res.data;
      console.log("第一次打印" + res.data)
      // console.log(res.data.data);
      console.log("aaaaaaa")


      console.log(option.series.data)
      console.log("bbbbbbbbb")
      // console.log(option.series.data);
      // option.series[1].data = res.data.y
      // 数据准备完毕之后再set
      myChart.setOption(option);
      console.log("cccccc")
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
          center: ['50%', '50%'],
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