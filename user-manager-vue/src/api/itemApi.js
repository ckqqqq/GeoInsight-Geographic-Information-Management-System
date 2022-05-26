import request from '../utils/request'

export default {

  // 添加用户接口
  addUser(user) {
    return request({
      url: '/item',
      method: 'post',
      data: user
    })
  },

  // 删除用户接口
  deleteUser(id) {
    return request({
      url: `/item/${id}`,
      method: 'delete'
    })
  },

  // 删除多个用户接口
  //传入一个vue的列表
  deleteMoreUser(ids) {
    return request({
      url: '/item',
      method: 'delete',
      data: ids
    })
  },

  // 编辑用户接口
  updateUser(user) {
    return request({
      url: `/item`,
      method: 'put',
      data: user
    })
  },

  // 查询用户列表接口
  findUserList(index, size, queryParam) {
    return request({
      url: `/item/${index}/${size}`,
      method: 'post',
      data: queryParam
    })
  },

  // 根据用户编号获取用户信息接口
  getUserInfo(id) {
    return request({
      url: `/item/${id}`,
      method: 'post'
    })
  }
}