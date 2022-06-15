import Vue from 'vue'
import VueRouter from 'vue-router'
import index from '../views/index.vue'

Vue.use(VueRouter)
//注意: router似乎不能热修改
//数据映射
const routes = [
  {
		path: "/",
		name: "数据管理",
		component: index,
    class: 'el-icon-s-custom',
	  //第一页
		children: [
			{
				path: "/",
				name: "数据列表",
				component: () => import("../views/mainList.vue")
			},
			//第二页
      {
				path: "/addItem",
				name: "增添表项",
				component: () => import("../views/addItem.vue")
			},
      {
				path: "/editItem/:id",
				name: "编辑事件",
				component: () => import("../views/addItem.vue"),
        hidden: true //隐式路由 这个我不知道什么意思，哦哦哦就是以后要加的页面
	  },
			//第三页
			// {
			// 	path: "/itemInfo",
			// 	name: "详细信息",
			// 	component: () => import("../views/itemInfoList.vue")
			// },
			//第三页
			{
				path: "/map",
				name: "地图展示",
				component: () => import("../views/map.vue")
			}
			//你们的工作是加页码，我目前前端只有三页你们能不能写一个有gis的页面，
			//你们目前vue学的咋样
		],
	},
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
