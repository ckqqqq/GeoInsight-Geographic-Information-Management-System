<!--这一页是第一页，是表单页面-->
<template>
  <div class="app">
    <!-- 查询条件 -->
    <div>
     <el-form :inline="true" :model="queryParam" class="demo-form-inline" ref="queryParam">

        <el-form-item label="名称" prop="itemname">
          <el-input v-model="queryParam.itemname" placeholder="输入名称"></el-input>
        </el-form-item>

        <el-form-item label="信息来源" prop="typename">
          <el-input v-model="queryParam.typename" placeholder="请输入信息来源"></el-input>
        </el-form-item>

        <el-form-item label="经度" prop="longitude">
          <el-input v-model="queryParam.longitude" placeholder="时间"></el-input>
        </el-form-item>

        <el-form-item label="状态" prop="finalOption">
          <el-select v-model="queryParam.finalOption" placeholder="选择状态">
<!--            <el-option label="已处理" value="0"></el-option>-->
<!--            <el-option label="未处理" value="1"></el-option>-->
          </el-select>
        </el-form-item>

        <el-form-item label="时间" prop="time">
          <el-date-picker v-model="queryParam.time"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="选择时间">
          </el-date-picker>
        </el-form-item>

        <el-form-item>
          <el-button type="primary"
                     icon="el-icon-search"
                     @click="submitSearch()">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-refresh"
                     @click="clearForm('queryParam')">重置</el-button>
        </el-form-item>
        <el-button v-if="more"
                   type="danger"
                   icon="el-icon-delete"
                   @click="deleteMore()">一键删除</el-button>
      </el-form>
    </div>

    <!-- 用户信息列表 -->
    <el-table ref="multipleTable"
              :data="ItemList"
              border
              @selection-change="handleSelectionChange"
              style="width: 100%">

      <el-table-column type="selection"
                       width="55">
      </el-table-column>

      <!-- 编号 -->
      <el-table-column label="编号"
                       width="200">
        <template slot-scope="scope">
          <span>{{ scope.row.id }}</span>
        </template>
      </el-table-column>

      <!-- 名称 -->
      <el-table-column label="名称"
                       width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.itemname }}</span>
        </template>
      </el-table-column>

      <!-- 信息来源 -->
      <el-table-column label="信息来源"
                       width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.typename }}</span>
        </template>
      </el-table-column>

      <!-- 纬度 -->
      <el-table-column label="状态"
                       width="60">
        <template slot-scope="scope">
          <span>{{ scope.row.finalOption }}</span>
        </template>
      </el-table-column>

      <!-- 经度 -->
      <el-table-column label="经度"
                       width="150">
        <template slot-scope="scope">
          <span>{{ scope.row.longitude }}</span>
        </template>
      </el-table-column>

      <!-- 纬度 -->
      <el-table-column label="纬度"
                       width="80">
        <template slot-scope="scope">
          <span>{{ scope.row.latitude }}</span>
        </template>
      </el-table-column>

      <!-- 时间 -->
      <el-table-column label="时间"
                       width="100">
        <template slot-scope="scope">
          <span>{{ scope.row.gmtCreate }}</span>
        </template>
      </el-table-column>

      <!-- 提议 -->
      <el-table-column label="描述"
                       width="auto">
        <template slot-scope="scope">
          <span>{{ scope.row.describe }}</span>
        </template>
      </el-table-column>

      <el-table-column align="center"
                       label="操作"
                       width="300">
        <template slot-scope="scope">
            <!-- 文件下载按钮 -->
                <el-button size="mini"
                           type="primary"
                           icon="el-icon-edit"
                           @click="fileDownload(scope.row.finalOption)"
                >文件</el-button>
          <!-- 编辑按钮 -->
          <router-link :to="'/editItem/' + scope.row.id">
            <el-button size="mini"
                       type="warning"
                       icon="el-icon-edit"
                       >编辑</el-button>
          </router-link>
          <!-- 删除按钮 -->
          <el-button
                     size="mini"
                     type="danger"
                     icon="el-icon-delete"
                     @click="deleteItem(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页 -->
    <div class="block"
         style="text-align:center;margin-top:10px;">
      <el-pagination background
                     @current-change="handleCurrentChange"
                     :current-page.sync="pageParam.index"
                     :page-size="pageParam.size"
                     :total="pageParam.total"
                     layout="prev, pager, next, jumper">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import Item from '../api/itemApi'
import axios from "axios";

export default {
  data() {
    return {
      // 分页
      pageParam: {
        index: 1,
        size: 10,
        total: 0,
      },
      // 数据
      ItemList: [],
      // 一键删除
      more: false, // 删除按钮显示状态
      select: [], // 选中的数据
      // 查询参数对象
      queryParam: {
        itemname: '',
        typename: '',
        longitude: '',
        finalOption: '',
        time: '',
      },
    }
  },
  methods: {
      fileDownload(fileId){
          var my_url="http://127.0.0.1:8081/item/file/download?id="+fileId
          console.log("fileDownload")
          console.log(fileId)
          axios({
              method: 'get',
              url: my_url,
              headers:{
                  'Content-Type' : 'multipart/form-data'
              }

          }).then((response)=>{
              console.log(response)
              const url = window.URL.createObjectURL(new Blob([response.data]));
              const link = document.createElement('a');
              link.href = url;
              link.setAttribute('download', ''); //or any other extension
              document.body.appendChild(link);
              link.click();
          })
      },
    // 提交查询
    submitSearch() {
      // 调用下面的查询方法
      this.findItemList()
    },
    // 清空查询参数
    clearForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 每选中一个就会执行的方法
    handleSelectionChange(val) {
      if (val.length === 0) {
        // 选中0个，隐藏按钮
        this.more = false
      } else {
        // 选中1个以上，显示一键删除按钮
        this.more = true
      }
      this.select = val
    },
    // 一键删除多个用户
    deleteMore() {
      this.$confirm('此操作将删除多个表项，是否继续？', '提示', {
        confirmButtonText: '确定',
        type: 'warning',
      }).then(() => {
        let ids = []
        // 取出用户ID
        this.select.forEach((item) => {
          ids.push(item.id)
        })
        // 调用删除多个用户的接口
        Item.deleteMoreItem(ids).then((_) => {
          this.$notify({
            title: '删除成功',
            type: 'success',
          })
          this.findItemList()
        })
      })
    },
    // 切页
    handleCurrentChange(index) {
      this.pageParam.index = index
      this.findItemList()
    },
    // 查询用户列表
    findItemList() {
      Item.findItemList(this.pageParam.index, this.pageParam.size, this.queryParam).then((res) => {
        this.ItemList = res.data.itemList
        this.pageParam.total = res.data.total
      })
    },
    // 删除单个用户
    deleteItem(id) {
      this.$confirm('此操作将删除改表项, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      }).then(() => {
        // 调用删除接口
        Item.deleteItem(id).then((_) => {
          this.$message({
            type: 'success',
            message: '删除成功!',
          })
          this.findItemList()
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除',
        })
      })
    },
  },
  created() {
    this.findItemList()
  },
}
</script>