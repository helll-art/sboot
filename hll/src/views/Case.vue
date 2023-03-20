<template>
  <div>

    <div style = "padding:10px 0">
      <el-input style = "width : 200px" placeholder = "请输入用户名" suffix-icon = "el-icon-user" v-model = "name"></el-input>
      <el-cascader style = "height: auto" placeholder = "请选择身体状况"
                   v-model="value"
                   :options="options"
                   @change="handleChange" ></el-cascader>
      <!--      <el-input style = "width : 200px" placeholder = "请输入地址" suffix-icon = "el-icon-office-building" v-model = "address"></el-input>-->
      <el-button class = "ml-5" type = "primary" @click = "load">搜 索</el-button>
      <el-button type="success" @click = "reset">重 置</el-button>
    </div>
    <div style = "padding : 10px 0">
      <el-button type = "primary" @click = "handleAdd">新 增 <i class = "el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class = "ml-5"
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除吗？"
          @confirm = "delBatch"
      >
        <el-button slot="reference" type = "danger">批 量 删 除 <i class = "el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <!--            <el-button type = "danger" @click ="delBatch" >批量删除 <i class = "el-icon-remove-outline"></i></el-button>-->
      <el-button type = "primary" class = "ml-5">导 入 <i class = "el-icon-bottom"></i></el-button>
      <el-button type = "primary" >导 出 <i class = "el-icon-top"></i></el-button>
    </div>
    <el-table
        :data="tableData"
        style="width: 100%"
        border

    >
      <!--          <el-table :data="tableData"-->
      <!--                    :row-class-name="tableRowClassName(1)">-->
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="name" label="姓名" width="120"></el-table-column>
      <el-table-column prop="gender" label="性别" width="100">
        <template slot-scope="scope">
          <span  v-if="scope.row.gender == 1">男</span>
          <span  v-else-if="scope.row.gender == 2">女</span>

        </template>
      </el-table-column>
      <el-table-column prop="birthday" label="生日" width="110" :formatter="formatDate"></el-table-column>
      <el-table-column prop="phone" label="手机号" width="220"></el-table-column>
      <el-table-column prop="blood" label="血型" width="110"></el-table-column>
      <el-table-column prop="allergic" label="过敏" width="220"></el-table-column>
      <el-table-column prop="edesc" label="描述">
        <template slot-scope="scope">
          <span  v-if="scope.row.status === '1'">正常</span>
          <span  v-else-if="scope.row.status === '2'">{{scope.row.edesc}}</span>
        </template>
      </el-table-column>
      <el-table-column prop="request" label="近期需求" width="220"></el-table-column>
      <el-table-column label = "操作" width = "260">
        <template v-slot="scope">
          <el-button type = "primary" @click = "handleEdit(scope.row)">编 辑 <i class = "el-icon-edit"></i></el-button>


          <!--                  <el-button type = "danger"  @click = "handleDel(scope.row.id)">删除<i class = "el-icon-remove-outline"></i></el-button>-->
          <el-popconfirm
              class = "ml-5"
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm = "handleDel(scope.row.id)"
          >
            <el-button slot="reference" type = "danger">删 除 <i class = "el-icon-remove-outline"></i></el-button>

          </el-popconfirm>
          <el-button type = "warning" class = "ml-5" >呼 叫 <i class = "el-icon-phone"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style = "padding : 10px 0">

      <el-pagination
          @size-change = "handleSizeChange"
          @current-change = "handleCurrentChange"
          :current-page="pageNum"
          :page-sizes = "[2,5,10,20]"
          :page-size="pageSize"
          layout = "total , sizes , prev,pager,next,jumper"
          :total = "total">
      </el-pagination>
    </div>
    <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width = "30%" >
      <el-form :model="form" label-width="80px" size = "small">
        <el-form-item label="姓名">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input v-model="form.age" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="病情">
          <el-input v-model="form.edesc" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-input v-model="form.gender" autocomplete="off">

          </el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="身体状况">
          <el-input v-model="form.status" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="handleSave">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "User",
  data(){
    return {
      headerBg: 'headerBg',
      tableData: [],
      value: [],
      options: [{
        value: '1',
        label: '健康',
      },{
        value: '2',
        label: '较差'
      }],

      name: "",
      address: "",
      dialogFormVisible: false,
      multipleSelection: [],
      form: {},
      total: 0,
      pageNum: 1,
      pageSize: 5,
    }
  },
  created(){
    this.load()
  },
  methods:{
    formatDate(row, column) {
      // 获取单元格数据
      let data = row[column.property]
      if(data == null) {
        return null
      }
      let dt = new Date(data)
      return dt.getFullYear() + '-' + (dt.getMonth() + 1) + '-' + dt.getDate()
    },

    status_change:function (row) {
      if(row.row.status == "1"){
        return "common-row"
      }else {
        return "warning-row"
      }
    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize =pageSize
      this.load()
    },
    handleChange(value) {
      value : this.status,
          this.load()

    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    load(){
      this.request.get("/elder/page",{
        params: {
          // pageNum : this.pageNum,
          // pageSize : this.pageSize,
          // name : this.name,
          // address : this.address,
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          status : this.status,
          name: this.name,
        }
      }).then(res =>{
        console.log(res)
        console.log(this.name)
        this.tableData = res.records
        this.total = res.total
      })
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form  = {}
    },
    handleDel(id){
      this.request.delete("/elder/" + id).then(res => {
        if(res){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val){
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/elder/del/batch" ,ids).then(res =>{
        if(res){
          this.$message.success("批量删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    handleEdit(row){
      this.form = row
      this.dialogFormVisible = true
    },
    handleSave(){
      this.request.post("/elder",this.form).then(res =>{
        if(res){
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        }else{
          this.$message.error("保存失败")
        }
      })
    },
    reset(){
      this.username = ""
      this.address = ""
      this.load()
    },
  }
}
</script>

<style>
.el-header {
  background-color: #FFF;
  color: #333;
  line-height: 60px;
}
.el-aside {
  color: #333;
}
/*.headerBg{*/
/*  background-color : #784b4b !important;*/
/*}*/
.el-table .warning-row {

  background: #f8c7c7;
}
.el-table .success-row {
  background: #f0f9eb;

}
.el-table .common-row {
  background: #ffffff;
}
</style>