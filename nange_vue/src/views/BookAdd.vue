<template>

  <!--model是el-form子组件的prop属性，ruleFrom是父组件的属性，调用者的属性 -->
  <!--ref表示form的name-->
  <el-form  :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px"  class="demo-ruleForm" style="width: 500px;">

    <el-form-item label="图书名称" prop="name">
        <el-input v-model="ruleForm.name" aria-placeholder=""></el-input>
    </el-form-item>
    <el-form-item label="图书作者" prop="author" >
      <el-input v-model="ruleForm.author"></el-input>
    </el-form-item>
    <el-form-item label="出版社" prop="publish">
      <el-input v-model="ruleForm.publish"></el-input>
    </el-form-item>
    <el-form-item  label="图书页码" prop="pages">
      <el-input v-model.number="ruleForm.pages"></el-input>
    </el-form-item>
    <el-form-item  label="图书价格" prop="price">
      <el-input v-model="ruleForm.price" oninput="value=value.replace(/[^0-9.]/g,'')"></el-input>
    </el-form-item>

    <el-form-item>
      <el-col :span="12">
      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
      <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-col>
    </el-form-item>

  </el-form>
</template>

<script>


  import axios from "axios";
  export default {

    data() {
      return {
        ruleForm: {
          name: '图书名称01',
          author: '图书作者01',
          publish: '图书出版社01',
          pages : 100,
          price: 19.9
        },
        rules: {
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
          price: [
            { required: true, message: '请输入图书价格', trigger: 'blur' }
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.$message({
              message: '校验通过！',
              type: 'success'
            });

            axios.post(this.$store.state.webUrl+'/book/add',this.ruleForm).then(res=>{
              //console.log(res.data);
              this.$message({
                message: '添加'+this.ruleForm.name+'成功！',
                type: 'success'
              });
            })

          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      //清空表单
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }
    }
  }
</script>