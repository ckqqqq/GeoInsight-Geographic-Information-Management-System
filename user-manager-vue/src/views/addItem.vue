<!--这一页是第二页，是增添事件的页面-->
<template>
  <div class="app">
    <div class="form">
      <el-form :model="ItemList" :rules="rules" ref="ItemList"
               label-width="100px"
               class="demo-ruleForm">
          <el-form-item label="地理信息编码" prop="id">
              <el-input v-model="ItemList.id"></el-input>
          </el-form-item>
        <el-form-item label="名称" prop="itemname">
          <el-input v-model="ItemList.itemname"></el-input>
        </el-form-item>

        <el-form-item label="类别" prop="typename">
          <el-input v-model="ItemList.typename"></el-input>
        </el-form-item>

          <el-form-item label="经度" prop="longitude">
<!--              <el-input v-model="ItemList.longitude" type="number" ps 如果添加type则使用number规则(可见文件末尾)></el-input>-->
              <el-input v-model="ItemList.longitude"></el-input>
          </el-form-item>

        <el-form-item label="纬度" prop="latitude">
          <el-input v-model="ItemList.latitude" ></el-input>
        </el-form-item>



        <el-form-item label="状态" prop="Status">
          <!-- 单选框 -->
          <el-select v-model="ItemList.finalOption" placeholder="请选择状态">
            <el-option label="已处理" value="0"></el-option>
            <el-option label="未处理" value="1"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="描述" prop="describe">
          <el-input type="textarea" v-model="ItemList.describe"></el-input>
        </el-form-item>
          <el-form-item label="文件" prop="file">
              <el-input icon="el-icon-document-copy"  type="file" id="fileinput" v-model="fileName" ></el-input>
<!--              <div style="border:1px solid #ccc;border-radius: 4px;">-->
<!--                  自己写了个漂亮的按钮，点这个按钮触发下面这个input框，实现选文件-->
<!--                  弹出选择文件的窗口-->
<!--                  <el-button icon="el-icon-document-copy" type="primary" size="small" style="margin-left: 15px;" @click="checkFile">选择文件</el-button>-->
<!--                  <span>    {{fileName}}</span>-->
<!--                  放文件名的-->
<!--                  <input type="file" id="fileinput" style="display: none;" @change="checkFileSure"></input>-->


<!--                  实际上传文件的input框在这-->
<!--              </div>-->

          </el-form-item>
          <el-form-item>

          </el-form-item>
<!--          <el-form-item>-->
<!--              <el-upload-->
<!--                      accept=".xlsx, .xls"-->
<!--                      :file-list="fileList"-->
<!--                      :action="actionUrl"-->
<!--                      :data="info"-->
<!--                      :before-upload="beforeUploadFile"-->
<!--                      :on-exceed="exceedFile"-->
<!--                      :on-success="uploadSuccess"-->
<!--                      :show-file-list="isShowFile"-->
<!--                      multiple-->
<!--                      :limit="limitNum"-->


        <el-form-item>
          <!-- 提交 -->
          <el-button type="primary" @click="submitForm('ItemList')">提交</el-button>
          <!-- 清空表单 -->
          <el-button @click="resetForm('ItemList')">重置</el-button>
        </el-form-item>


      </el-form>
    </div>
  </div>
</template>

<script>
import Item from '../api/itemApi'

export default {
  data() {
    return {
        fileName: '',
      // 页面状态 false->添加操作，true->编辑操作
      status: false,
      // 用户信息表单
      ItemList: {
          id:'',
          itemname: '',
          typename: '',
          latitude: '',
          longitude: '',
          finalOption: '',
          describe: '',
      },
      // 校验规则，详细看 element ui 官方教程
      rules: {
          id: [
              { required: true, message: '请输入id', trigger: 'blur' },
          ],
            fileName: [
                { required: true, message: '请输入文件', trigger: 'blur' },
            ],
        itemname: [
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
      checkFile () {
          document.querySelector('#fileinput').click()
      },
      // 选择的文件
      // !!!!!!!!!!!!!!!!!!!!注意。如果使用的input框。文件选择后的名字不是value值
      // 而是name值 document.querySelector('#fileinput').files[0].name
      checkFileSure (val) {
          console.log(document.querySelector('#fileinput').files[0])
          this.fileName = document.querySelector('#fileinput').files[0].name
          console.log("文件上传成功")
      },
    submitForm(formName) {

        // 漂亮按钮点击事件

      this.$refs[formName].validate((valid) => {
        if (valid) {
          if(this.status) {
            // 调用编辑用户接口
            Item.updateItem(this.ItemList).then(_ => {
              this.$message({
                message: '编辑成功',
                type: 'success'
              })
              // 跳转到主页下（mainList.vue）
              this.$router.push('/')
            })
          }else{
            // 调用添加用户接口
            Item.addItem(this.ItemList).then((_) => {
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
        Item.getItemInfo(this.$route.params.id).then( res => {
          // 通过传递过来的id查询用户数据，覆盖
          this.ItemList = res.data.ItemInfo
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