<template>
	<div class="waitpass-detail">
		<!-- 项目概要 -->
		
		
		

		<el-button @click="back" type="text" size="small">退回</el-button>
        <el-button @click="pass" type="text" size="small">通过</el-button>
	</div>
</template>
<script>
	export default {
		data(){
			return {

			}
		},
		methods: {
			back() {
				alert('退回')
			},
			pass() {
				alert('通过')
			}
		}
	}
</script>
<style lang="scss">

</style>
