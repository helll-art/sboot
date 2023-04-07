<template>
  <div>

    <div style = "padding:10px 0">
      <el-input style = "width : 200px" placeholder = "请输入用户名" suffix-icon = "el-icon-user" v-model = "username"></el-input>
      <el-input style = "width : 200px" placeholder = "请输入地址" suffix-icon = "el-icon-office-building" v-model = "address"></el-input>
      <el-button class = "ml-5" type = "primary" @click = "load">搜索</el-button>
      <el-button type="success" @click = "reset">重置</el-button>
    </div>
    <div style = "padding : 10px 0">
      <el-button type = "primary" @click = "handleAdd">新增 <i class = "el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm

          class = "ml-5"
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除吗？"
          @confirm = "delBatch"
      >
        <el-button slot="reference" type = "danger" >批量删除 <i class = "el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
      <!--            <el-button type = "danger" @click ="delBatch" >批量删除 <i class = "el-icon-remove-outline"></i></el-button>-->
      <el-upload action="http://localhost:9091/user/import" :show-file-list = "false" accept = "xlsx"
                 :on-success="handleExcelImportSuccess"
      style = "display: inline-block">
        <el-button type = "primary" class = "ml-5">导入 <i class = "el-icon-bottom"></i></el-button>
      </el-upload>

      <el-button type = "primary" @click = "exp" class = "ml-5">导出 <i class = "el-icon-top"></i></el-button>
    </div>
    <el-table
        :data="tableData"
        style="width: 100%"
        border
        @selection-change = "handleSelectionChange"
        >
      <!--          <el-table :data="tableData"-->
      <!--                    :row-class-name="tableRowClassName(1)">-->
      <el-table-column type="selection" width="55" v-model="opts" ></el-table-column>
      <el-table-column prop="username" label="用户名" width="200"></el-table-column>
      <el-table-column prop="nickname" label="昵称" width="200"></el-table-column>
      <el-table-column prop="email" label="邮箱" width="220"></el-table-column>
      <el-table-column prop="phone" label="手机号" width="220"></el-table-column>
      <el-table-column prop="address" label="地址" ></el-table-column>
      <el-table-column prop="userstatus" label="职位">
        <template slot-scope="scope">
          <span  v-if="scope.row.userstatus === '1'">管理员</span>
          <span  v-else-if="scope.row.userstatus === '2'">经理</span>
          <span  v-else-if="scope.row.userstatus === '3'">监管人员</span>
        </template>
      </el-table-column>
      <el-table-column label = "操作" width = "240">
        <template v-slot="scope">
          <el-button type = "primary" @click = "handleEdit(scope.row)">编辑<i class = "el-icon-edit"></i></el-button>

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
            <el-button slot="reference" type = "danger">删除 <i class = "el-icon-remove-outline"></i></el-button>
          </el-popconfirm>

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
        <el-form-item label="用户名">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵称">
          <el-input v-model="form.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址">
          <el-input v-model="form.address" autocomplete="off"></el-input>
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
      username: "",
      address: "",
      dialogFormVisible: false,
      multipleSelection: [],
      form: {},
      total: 0,
      pageNum: 1,
      pageSize: 5,
      opts:[]
    }
  },
  created(){
    this.load()
  },
  methods:{
    status_change:function (row) {
      if(row.row.userstatus == "1"){
        return "warning-row"
      }else if(row.row.userstatus == "3"){
        return "success-row"
      }else {
        return "common-row"
      }
    },
    handleSizeChange(pageSize){
      console.log(pageSize)
      this.pageSize =pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },
    load(){
      this.request.get("/user/page",{
        params: {
          pageNum : this.pageNum,
          pageSize : this.pageSize,
          username : this.username,
          address : this.address,
        }
      }).then(res =>{
        console.log(res.data)
        console.log(this.username)
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    handleAdd(){
      this.dialogFormVisible = true
      this.form  = {}
    },
    handleDel(id){
      this.request.delete("/user/" + id).then(res => {
        if(res.data){
          this.$message.success("删除成功")
          this.load()
        }else{
          this.$message.error("删除失败")
        }
      })
    },
    handleSelectionChange(val){
      console.log(val)
      this.multipleSelection = val
    },
    delBatch(){
      let ids = this.multipleSelection.map(v => v.id)
      this.request.post("/user/del/batch" ,ids).then(res =>{
        console.log(ids);
        if(res.data){
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
      this.request.post("/user",this.form).then(res =>{
        if(res.data){
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
    exp(){
      window.open("http://localhost:9091/user/export");
    },
    handleExcelImportSuccess(){
      this.$message.success("导入成功");
      this.load();
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
  background: oldlace;
}
.el-table .success-row {
  background: #f0f9eb;
}
.el-table .common-row {
  background: #ffffff;
}
</style>
<!--
  created(){
  this.request.get("/user/username/" + this.user.username).then(res => {
  if(res.code === '200'){
    this.form = res.data;
    }

-->