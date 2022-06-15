import request from '../utils/request'
import axios from "axios";
//这个是封装好的request
export default {
  fileUpload(file){
    axios({
      methods: 'post',
      url: 'http://127.0.0.1:8081/item/file/upload',
      headers:{
        'Content-Type' : 'multipart/form-data'
      },
      params:{
        'file':file
      }
    })
  },

  // fileUpload(file){
  //   this.axios.post(
  //       'http://127.0.0.1:8081/item/file/upload',
  //       {
  //         params:{
  //           'file':file
  //         },
  //         headers:{
  //           'Content-Type': 'multipart/form-data'
  //         }
  //       }
  //   ).then(function(response){
  //     console.log(response)
  //   })
  // },
  // 添加用户接口
  addItem(Item) {
    console.log("addItem")
    return request({
      url: '/item',
      method: 'post',
      data: Item
    })
  },

  // 删除用户接口
  deleteItem(id) {
    console.log("deleteItem")
    return request({
      url: `/item/${id}`,
      method: 'delete'
    })
  },


  // 删除多个用户接口
  //传入一个vue的列表
  deleteMoreItem(ids) {
    console.log("deleteMoreItem")
    return request({
      url: '/item',
      method: 'delete',
      data: ids
    })
  },

  // 编辑用户接口
  updateItem(Item) {
    console.log("updateItem")
    return request({
      url: `/item`,
      method: 'put',
      data: Item
    })
  },

  // 查询用户列表接口
  findItemList(index, size, queryParam) {
    console.log("findItemList")
    return request({
      url: `/item/${index}/${size}`,
      method: 'post',
      data: queryParam
    })
  },

  // 根据用户编号获取用户信息接口
  getItemInfo(id) {
    console.log("getItemInfo")
    return request({
      url: `/item/${id}`,
      method: 'post'
    })
  }
}
