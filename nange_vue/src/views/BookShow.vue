<template>

  <!--右边部分-->

    <el-main style="margin-top: 0px; padding-top: 0px;">
      <el-table :data="books" style="width: 100%" border stripe>
        <el-table-column prop="id" label="编号" ></el-table-column>
        <el-table-column prop="name" label="图书名称" min-width="100px"></el-table-column>
        <el-table-column prop="author" label="作者" ></el-table-column>
        <el-table-column prop="publish" label="出版社" min-width="150px"></el-table-column>
        <el-table-column prop="pages" label="页数"></el-table-column>
        <el-table-column prop="price" label="价格" ></el-table-column>
        <el-table-column prop="bookcaseid" label="bookcaseid" ></el-table-column>
        <el-table-column prop="abled" label="abled" ></el-table-column>
        <el-table-column fixed="right" label="操作" width="150px" >
          <template slot-scope="scope">
              <el-button @click="handleUpdate(scope.row)"
                         type="primary" size="small" icon="el-icon-edit">
              </el-button>
              <el-button type="primary" size="small" @click="handleDelete(scope.row)" icon="el-icon-delete"></el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--页码-->
      <el-pagination background
                     layout="prev, pager, next"
                     style="margin-top: 10px;"
                     :total="totalNum"
                     :page-size="page_size"
                     @current-change="changePage"
      ></el-pagination>

      <!--修改对话框-->
      <el-dialog title="收货地址" :visible.sync="dialogFormVisible">

        <el-form :model="bookForm" :rules="rules" ref="ruleForm">
          <el-form-item label="图书id" :label-width="formLabelWidth" prop="id">
            <el-input v-model="bookForm.id" autocomplete="off" readonly></el-input>
          </el-form-item>
          <el-form-item label="图书名称" :label-width="formLabelWidth" prop="name">
            <el-input v-model="bookForm.name" autocomplete="off" ></el-input>
          </el-form-item>
          <el-form-item label="图书作者" :label-width="formLabelWidth" prop="author">
            <el-input v-model="bookForm.author" autocomplete="off" ></el-input>
          </el-form-item>
          <el-form-item label="图书出版社" :label-width="formLabelWidth" prop="publish">
            <el-input v-model="bookForm.publish" autocomplete="off" ></el-input>
          </el-form-item>
          <el-form-item label="图书页数" :label-width="formLabelWidth" prop="pages">
            <el-input v-model.number="bookForm.pages" autocomplete="off" ></el-input>
          </el-form-item>
          <el-form-item label="图书价格" :label-width="formLabelWidth" prop="price">
            <el-input v-model="bookForm.price" autocomplete="off"  oninput="value=value.replace(/[^0-9.]/g,'')"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submitForm('ruleForm')">确 定</el-button>
        </div>
      </el-dialog>

    </el-main>


</template>

<script>

  import axios from "axios";

  export default {
    name: "Book",
    data: function(){
      return{
        //一页的总条数
        page_size: 8,
        //总数
        totalNum: null,
        books: null,


        //对话框显示与否
        dialogFormVisible: false,
        //对话框宽度
        formLabelWidth: '100px',
        //用于修改book，绑定book表单
        bookForm: {
          id: null,
          name: null,
          author: null,
          publish: null,
          pages : null,
          price: null
        },
        rules: {
          id:[],
          name: [
            { required: true, message: '请输入图书名称', trigger: 'blur' },
            { max: 20, message: '长度在小于 20 个字符', trigger: 'blur' }
          ],
          author: [
            { required: true, message: '请输入图书作者', trigger: 'blur' },
            { max: 20, message: '长度在小于 20 个字符', trigger: 'blur' }
          ],
          publish: [
            { required: true, message: '请输入图书出版社', trigger: 'blur' },
            { max: 20, message: '长度在小于 20 个字符', trigger: 'blur' }
          ],
          pages: [
            { required: true, message: '请输入图书页码', trigger: 'blur' },
            { type: 'number', message: '图书页码必须为数字值' }
          ],
          price: [{ required: true, message: '请输入图书价格', trigger: 'blur' }],
        }
      }
    },
    methods:{

      //修改指定图书，首先拿到book实体，放入dialog中，然后提交表单修改
      handleUpdate: function(row){
        this.dialogFormVisible = true;
         this.bookForm = row; //赋值
      },
      handleDelete: function(row){
        var bookid  = row.id;
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          axios.delete(this.$store.state.webUrl+"/book/delete/"+row.id).then(res=>{
              if(res.data.code == 200){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                window.location.reload();
              }else{
                this.$message({
                  type: 'error',
                  message: '删除失败!'
                });
              }
          })
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },


      //校验表单，验证成功后，post提交修改表单
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$message({
              message: '校验通过！',
              type: 'success'
            });
            axios.post(this.$store.state.webUrl+'/book/update',this.bookForm).then(res=>{
              if(res.data.code==200){
                this.$message({
                  message: '修改成功！',
                  type: 'success'
                });
                this.dialogFormVisible = false;
              }else{
                this.$message({
                  message: '修改失败,输入的内容不符合要求！',
                  type: 'error'
                });
              }
            })


          } else {
            this.$message({
              message: '校验失败！',
              type: 'error'
            });
            return false;
          }
        });
      },

      //默认参数为当前页码

      changePage: function (currentPage) {
        const _this = this;
        axios.get(this.$store.state.webUrl+'/book/get/'+currentPage+'/8').then(function(res){
          //console.log(res.data);
          _this.books = res.data.list;
          _this.totalNum = res.data.total;
        })
      }

    },

    created() {
     const _this = this;
      axios.get(this.$store.state.webUrl+'/book/get/1/8').then(function(res){
        _this.books = res.data.list;
        _this.totalNum = res.data.total;
      })
    }
  }
</script>

<style>

</style>