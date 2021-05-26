<template>
  <el-container style="height: 800px; border: 1px solid #eee">
      <!--菜单栏-->
      <el-aside width="200px"style="border-top: 1px solid #eee;" >
        <el-menu :default-openeds="['1', '2']"   router>
          <el-submenu index="1">
            <!--对应submenu菜单名， i标签是设置图标-->
            <template slot="title"><i class="el-icon-message"></i>图书管理</template>
            <!-- el-menu-item 不可再展开的子节点-->
            <!--index就是router-view的to-->
            <el-menu-item index="/BookShow" :class="$route.path=='/BookShow'?'is-active':''">图书查询</el-menu-item>
            <el-menu-item index="/BookAdd" >添加图书</el-menu-item>
            <el-menu-item index="/Page1-3" >选项1-3</el-menu-item>
            <!--三级菜单-->
            <el-submenu index="1-4">
              <template slot="title"><i class="el-icon-setting"></i>选项1-4</template>
              <el-menu-item index="/Page1-4-1">选项1-4-1</el-menu-item>
            </el-submenu>
          </el-submenu>

          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>导航二</template>
            <el-menu-item index="/Page2-1">选项2-1</el-menu-item>
          </el-submenu>

        </el-menu>
      </el-aside>
      <!--右边部分, 根据导航栏，映射右边不同的html-->

    <el-container>
      <el-header style="text-align: right; font-size: 13px; height: 40px; margin-top: 5px;">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>查看</el-dropdown-item>
            <el-dropdown-item>新增</el-dropdown-item>
            <el-dropdown-item>删除</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>UserName</span>
      </el-header>

      <el-main style="padding: 0px;">
        <router-view/>
      </el-main>
    </el-container>


  </el-container>
</template>

<script>
  export default {
    name: "Index"
  }
</script>

<style scoped>

</style>