<!--这一页是第二页，是增添事件的页面-->
<template>
  <div class="app">
    <div class="form">
      <el-form :model="userList" :rules="rules" ref="userList"
               label-width="100px"
               class="demo-ruleForm">

        <el-form-item label="名称" prop="username">
          <el-input v-model="userList.username"></el-input>
        </el-form-item>

        <el-form-item label="类别" prop="typename">
          <el-input v-model="userList.typename"></el-input>
        </el-form-item>

          <el-form-item label="经度" prop="longitude">
<!--              <el-input v-model="userList.longitude" type="number" ps 如果添加type则使用number规则(可见文件末尾)></el-input>-->
              <el-input v-model="userList.longitude"></el-input>
          </el-form-item>

        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="userList.latitude" ></el-input>
        </el-form-item>



        <el-form-item label="状态" prop="">
          <!-- 单选框 -->
          <el-select v-model="userList.finalOption" placeholder="请选择状态">
            <el-option label="已处理" value="0"></el-option>
            <el-option label="未处理" value="1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="描述" prop="describe">
          <el-input type="textarea" v-model="userList.describe"></el-input>
        </el-form-item>
        <el-form-item>
          <!-- 提交 -->
          <el-button type="primary" @click="submitForm('userList')">提交</el-button>
          <!-- 清空表单 -->
          <el-button @click="resetForm('userList')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import user from '../api/itemApi'

export default {
  data() {
    return {
      // 页面状态 false->添加操作，true->编辑操作
      status: false,
      // 用户信息表单
      userList: {
        username: '',
        typename: '',
        latitude: '',
        longitude: '',
        finalOption: '',
        describe: '',
      },
      // 校验规则，详细看 element ui 官方教程
      rules: {
        username: [
          { required: true, message: '请输入名称', trigger: 'blur' },
        ],
        typename: [
          { required: true, message: '请输入类别', trigger: 'blur' },
        ],
       longitude: [
           { required: true, message: '请输入经度', trigger: 'blur' }
        ],
        latitude: [
          { required: true, message: '请输入纬度', trigger: 'blur' }
        ],

        finalOption: [
          { required: true, message: '请选择状态', trigger: 'blur' }
        ],
        describe: [
          { required: true, message: '请输入描述', trigger: 'blur' }
        ],
      },
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.status) {
            // 调用编辑用户接口
            user.updateUser(this.userList).then(_ => {
              this.$message({
                message: '编辑成功',
                type: 'success'
              })
              // 跳转到主页下（mainList.vue）
              this.$router.push('/')
            })
          }else{
            // 调用添加用户接口
            user.addUser(this.userList).then((_) => {
              this.$message({
                message: '添加成功',
                type: 'success'
              })
              // 跳转到主页下（mainList.vue）
              this.$router.push('/')
            })
          }
        }
      })
    },
    // 清空表单
    resetForm(formName) {
      this.$refs[formName].resetFields()
    },
    // 判断是编辑还是添加用户
    check() {
      if(this.$route.params && this.$route.params.id) {
        // 路径存在id参数，编辑状态
        this.status = true
        user.getUserInfo(this.$route.params.id).then( res => {
          // 通过传递过来的id查询用户数据，覆盖
          this.userList = res.data.userInfo
        })
      }else {
        // 未存在id参数，添加状态
        this.status = false
      }
    }
  },
  created() {
    // 页面未显示前就先判断状态
    this.check()
  },
  watch: {
    // 监听路由变化，不然会有bug
    $route(to, from) {
      this.check()
    }
  }
}
</script>

<style>
.form {
  width: 50%;
}
/* 清除倒数第三个框的上下效果 */
.form input::-webkit-outer-spin-button,
.form input::-webkit-inner-spin-button {
	-webkit-appearance: none;
}

.form input[type='number'] {
	-moz-appearance: textfield;
}
</style>