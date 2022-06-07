<!--这一页是那个用户信息页面，是第三页-->
<template>
  <div class="app">
    <div :class="index > 0 ? 'info' : ''" v-for="(item, index) in userList" :key="index">
      <el-descriptions class="margin-top"
                       :title="item.typename"
                       :column="3"
                       size="medium"
                       border>

        <!-- 名称 -->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-edit-outline"></i>
            名称
          </template>
           {{ item.itemname }}
        </el-descriptions-item>

        <!-- 经度 -->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-edit-outline"></i>
            经度
          </template>
           {{ item.longitude }}
        </el-descriptions-item>

        <!-- 状态 -->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-check"></i>
            状态
          </template>
          {{ item.finalOption === '0' ? '已处理' : '未处理' }}
        </el-descriptions-item>

        <!-- 时间 -->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            时间
          </template>
          {{ item.createTime }}
        </el-descriptions-item>

        <!-- 描述 -->
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-edit-outline"></i>
            描述
          </template>
        {{ item.describe }}
        </el-descriptions-item>
      </el-descriptions>
    </div>

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
// 引入接口
import user from '../api/itemApi'

export default {
  data() {
    return {
      // 用户信息列表
      userList: [],
      // 分页
      pageParam: {
        index: 1,
        size: 4,
        total: 0,
      },
    }
  },
  methods: {
    // 切页
    handleCurrentChange(index) {
      this.pageParam.index = index
      this.getUserList()
    },
    getUserList() {
      // 发送请求，获取用户信息列表
      user.findUserList(this.pageParam.index, this.pageParam.size).then((res) => {
        // 赋值
        this.userList = res.data.userList
        // 总数赋值
        this.pageParam.total = res.data.total
      })
    }
  },
  created() {
    this.getUserList()
  },
}
</script>

<style>
.info {
  margin-top: 20px;
}
</style>