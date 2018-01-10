<template>
	<div class="frame">
		<div class="sider-bar">
			<el-menu
      default-active="1"
      class="el-menu-vertical-demo"
  		>
      <router-link to="/frame/user">
      	<el-submenu index="1">
	        <template slot="title">
	          <i class="el-icon-location"></i>
	          <span>用户管理</span>
	        </template>
      	</el-submenu>
      </router-link >
      <router-link to="/frame/project">
      	<el-submenu index="2">
	        <template slot="title">
	          <i class="el-icon-location"></i>
	          <span>项目管理</span>
	        </template>
      	</el-submenu>
      </router-link >
      <router-link to="/frame/waitpass">
      	<el-submenu index="3">
	        <template slot="title">
	          <i class="el-icon-location"></i>
	          <span>审核管理</span>
	        </template>
      	</el-submenu>
      </router-link >
      <router-link to="/frame/labs">
      	<el-submenu index="3">
	        <template slot="title">
	          <i class="el-icon-location"></i>
	          <span>实验室管理</span>
	        </template>
      	</el-submenu>
      </router-link >
    </el-menu>
		</div>
		<div class="main">
			 <router-view></router-view>
		</div>
	</div>
</template>

<style lang="scss" scoped rel="stylesheet/scss">
	.top {
		position: fixed;
		left:0;
		top:0;
		width:100%;
		height: 60px;
		background: black;
		z-index: 9000;
	}
	.sider-bar {
		position: fixed;
		left:0 ;
		width: 200px;
		top:60px;
		bottom: 0;
		background: green;
		z-index: 8000;
		overflow: scroll;
	}
	.main {
		padding: 80px 15px 15px;
		margin-left: 200px;
	}
</style>

<script>
	import LabHeader from 'components/base/header/header'
	export default {
		data(){
			return {

			}
		},
		methods: {
			
		},
		components: {
			LabHeader
		}
	}
</script>

