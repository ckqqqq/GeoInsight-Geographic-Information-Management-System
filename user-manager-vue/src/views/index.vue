<!--这个是工具栏页面-->
<template>
  <div class="app">
    <el-container style="height: 700px; border: 1px solid #eee">
      <!-- 左侧导航栏 -->
        <el-aside width="200px"
                  style="background-color: rgb(238, 241, 246)">
            <el-menu router="router" :default-openeds="['0']">
                <el-submenu v-for="(item,index) in $router.options.routes"
                            :key="index"
                            :index="String(index)">
                    <template slot="title"><i :class="item.class"></i>{{item.name}}</template>
                    <el-menu-item v-for="(item1,index1) in item.children"
                                  :key="String(index1)"
                                  :index="item1.path"
                                  v-if="!(item1.hidden)"
                                  :class="$router.path==item1.path?'is-active':''">{{item1.name}}</el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>

        <!-- 顶部右侧工具栏 -->
        <el-container>
            <el-header style="text-align: right; font-size: 12px">
                <el-dropdown>
                    <i class="el-icon-setting"
                       style="margin-right: 15px"></i>
                    <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item>功能一</el-dropdown-item>
                        <el-dropdown-item>功能二</el-dropdown-item>
                        <el-dropdown-item>功能三</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
                <span>管理员</span>
            </el-header>

            <!-- 中间区域内容页面的切换 -->
            <el-main>
                <router-view />
            </el-main>
        </el-container>
    </el-container>
  </div>
</template>

<script>

export default {
}
</script>
