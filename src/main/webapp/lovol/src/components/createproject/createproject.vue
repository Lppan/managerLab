<template>
	<div class="project-create">
		<el-form ref="form" :model="createform" label-width="120px">
		  	<el-form-item label="试验任务">
		    	<el-input v-model="createform.projectName"></el-input>
		  	</el-form-item>
		  	<el-form-item label="所属项目">
		    	<el-select v-model="createform.parentProjectName" placeholder="请选择活动区域">
		      		<el-option
				      	v-for="item in parentProjectNameOptions"
				      	:key="item.value"
				      	:label="item.label"
				      	:value="item.value">
				    </el-option>
		    	</el-select>
		  	</el-form-item>
		  	<el-form-item label="发动机编号">
		    	<el-input v-model="createform.machineNo"></el-input>
		  	</el-form-item>
		  	<el-form-item label="重要等级">
		    	<el-select v-model="createform.importanceLevel" placeholder="请选择活动区域">
		      		<el-option
				      	v-for="item in importantLever"
				      	:key="item.value"
				      	:label="item.label"
				      	:value="item.value">
				    </el-option>
		    	</el-select>
		  	</el-form-item>
		  	<el-form-item label="额定点功率">
		    	<el-input v-model="createform.powerRate"></el-input>
		  	</el-form-item>
		  	<el-form-item label="额定点功率转速">
		    	<el-input v-model="createform.rotateSpeed"></el-input>
		  	</el-form-item>
		  	<el-form-item label="额定点油耗">
		    	<el-input v-model="createform.oilConsumeSign"></el-input>
		  	</el-form-item>
		  	<el-form-item label="最大扭矩">
		    	<el-input v-model="createform.torsionSpace"></el-input>
		  	</el-form-item>
		  	<el-form-item label="最大扭矩转速">
		    	<el-input v-model="createform.speedPointTotal"></el-input>
		  	</el-form-item>

		  	<el-form-item label="低怠速">
		    	<el-input v-model="createform.speedPointTotalLimit"></el-input>
		  	</el-form-item>
		  	<el-form-item label="高怠速">
		    	<el-input v-model="createform.parentProjectName"></el-input>
		  	</el-form-item>
		  	<el-form-item label="低怠速带载">
		    	<el-input v-model="createform.speedPointTotalHight"></el-input>
		  	</el-form-item>
		  	<el-form-item label="MAP编号">
		    	<el-select v-model="createform.mapPicture" placeholder="请选择活动区域">
		      		<el-option label="1" value="1"></el-option>
		      		<el-option label="2" value="2"></el-option>
		    	</el-select>
		  	</el-form-item>
		  	<el-form-item label="ECU件号">
		    	<el-input v-model="createform.ECU"></el-input>
		  	</el-form-item>
		  	<el-form-item label="排放标准">
		    	<el-select v-model="createform.standard" placeholder="请选择活动区域">
		      		<el-option label="国三" value="1"></el-option>
		      		<el-option label="国四" value="2"></el-option>
		    	</el-select>
		  	</el-form-item>
		  	<el-form-item label="EGR">
		    	<el-select v-model="createform.egr" placeholder="请选择活动区域">
		      		<el-option label="内部" value="1"></el-option>
		      		<el-option label="外部" value="2"></el-option>
		      		<el-option label="无" value="2"></el-option>
		    	</el-select>
		  	</el-form-item>
		  	<el-form-item label="燃油系统">
		    	<el-select v-model="createform.fuelSystem" placeholder="请选择活动区域">
		      		<el-option label="机械泵" value="1"></el-option>
		      		<el-option label="单体泵" value="2"></el-option>
		      		<el-option label="共轨CB18" value="3"></el-option>
		      		<el-option label="共轨CB28" value="4"></el-option>
		    	</el-select>
		  	</el-form-item>

		  	<el-form-item label="进气方式">
		    	<el-select v-model="createform.intake" placeholder="请选择活动区域">
		      		<el-option label="自然吸气" value="1"></el-option>
		      		<el-option label="增压" value="2"></el-option>
		    	</el-select>
		  	</el-form-item>
		  	<el-form-item label="中冷">
		    	<el-select v-model="createform.midleCooling" placeholder="请选择活动区域">
		      		<el-option label="空中冷" value="1"></el-option>
		      		<el-option label="水中冷" value="2"></el-option>
		    	</el-select>
		  	</el-form-item>
		  	<el-form-item label="气门数">
		    	<el-select v-model="createform.valueNo" placeholder="请选择活动区域">
		      		<el-option label="2气门" value="1"></el-option>
		      		<el-option label="4气门" value="2"></el-option>
		    	</el-select>
		  	</el-form-item>
		  	<el-form-item label="缸径冲程缸数">
		    	<el-select v-model="createform.stoke" placeholder="请选择活动区域">
		      		<el-option label="4*100x127" value="1"></el-option>
		      		<el-option label="6*100x127" value="2"></el-option>
		      		<el-option label="6*105x130" value="2"></el-option>
		    	</el-select>
		  	</el-form-item>
		  	<el-form-item>
		    	<el-button type="primary" @click="confirmCreateProject">立即创建</el-button>
		   		<el-button @click="cancelCreateProject">取消</el-button>
		  	</el-form-item>
		</el-form>
	</div>
</template>
<script>
	
	import { createProject } from  'api/project';

	export default {
    data() {
      return {
        createform: {
  			projectName: '',
			parentProjectName: 222,
			projectContent: 222,
			projectPrincipal: '张三',   //项目负责人
			machineType: '4d160',
			machineNo:'hc503222',
			powerRate:160.5, //标定点功率 
			rotateSpeed: 2200,
			oilConsumeSign: 230.1,
			torsionSpace: 800,
			speedPointTotal:1600,
			importanceLevel:1,  //1, 重要
			planBeginTime:'2017-1-1',
			planEndTime:'2017-1-2',
			dais:2,  //  
			status:1,  // 1，待审批  
			isStoppage: 1,
			memo:'wangbadan'
        },
        parentProjectNameOptions: [
        	{
	          	value: '1',
	          	label: '王八蛋1号'
	        },
	        {
	          	value: '1',
	          	label: '王八蛋2号'
	        },
	        {
	          	value: '3',
	          	label: '王八蛋3号'
	        },
        ],
        importantLever: [
        	{
	          	value: '1',
	          	label: '重要'
	        },
	        {
	          	value: '1',
	          	label: '一般'
	        }
        ]
      }
    },
    methods: {
      confirmCreateProject() {
      	let params = this.createform;
        createProject(this, params)
        	.then(res => {
        		if(res.status == 0) {
        			this.$message({
                        message: '添加成功',
                        type: 'success',
                        duration: 1000,
                    });
                    setTimeout(()=>{
	                    this.$router.push({
			                path: '/frame/project',
			            })
                    })
        		}
        	})
      },
      cancelCreateProject() {
      		this.$router.push({
                path: '/frame/project',
                query: {
                    
                }
            })
      }
    }
  }
</script>
<style lang="scss">
	.project-create {
		max-width: 800px;
	}
</style>