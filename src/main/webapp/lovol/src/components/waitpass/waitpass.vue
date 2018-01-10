<template>
	<div class="project">

	<!-- 项目列表 -->
		<el-table
    ref="multipleTable"
    :data="projectlist"
    tooltip-effect="dark"
    style="width: 100%"
    >
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      	label="试验任务"
      	prop="projectName"
      	>
    </el-table-column>
    <el-table-column
      	label="所属项目"
      	prop="parentProjectName"
      	>
    </el-table-column>
    <el-table-column
      	label="机型负责人"
      	prop="projectPrincipal"
      	>
    </el-table-column>
    <el-table-column
      	label="机型号"
      	prop="machineType"
      	>
    </el-table-column>
    <el-table-column
      	label="发动机编号"
      	prop="machineNo"
      	>
    </el-table-column>
    <el-table-column
      	label="重要等级"
      	prop="importanceLevel"
      	>
    </el-table-column>
    <el-table-column
      	label="起始时间"
      	prop="planBeginTime"
      	>
    </el-table-column>
    <el-table-column
      	label="结束时间"
      	prop="planEndTime"
      	>
    </el-table-column>
    <el-table-column
      	label="台架"
      	prop="dais"
      	>
    </el-table-column>
    <el-table-column
      	label="状态"
      	prop="status"
      	>
    </el-table-column>
    <el-table-column
      	label="创建时间"
      	prop="createTime"
      	>
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="180">
      <template slot-scope="scope">
        <el-button  type="text" size="small" @click="goToDetail(scope.row)">查看详情</el-button>
        <el-button  type="text" size="small" @click="goTopass(scope.row)">通过</el-button>
      </template>v
    </el-table-column>
  </el-table>
	</div>
</template>

<script>
	export default {
		data() {
			return {
				projectlist:[
					{
						projectName: '6d国四排放升级',
						parentProjectName: '国四排放四阶段',
						projectPrincipal: '张三',   //项目负责人
						machineType: '4d160',
						machineNo:'hc503222',
						importanceLevel:1,  //1, 重要
						planBeginTime:'2017-1-1',
						planEndTime:'2017-1-2',
						dais:2,  //  台架
						status:1,  // 1，待审批  
						createTime:2017-1-1
					},
					{
						projectName: '6d国四排放升级',
						parentProjectName: '国四排放四阶段',
						projectPrincipal: '张三',   //项目负责人
						machineType: '4d160',
						machineNo:'hc503222',
						importanceLevel:1,  //1, 重要
						planBeginTime:'2017-1-1',
						planEndTime:'2017-1-2',
						dais:2,  //  台架
						status:1,  // 1，待审批  
						createTime:2017-1-1
					},
					{
						projectName: '6d国四排放升级',
						parentProjectName: '国四排放四阶段',
						projectPrincipal: '张三',   //项目负责人
						machineType: '4d160',
						machineNo:'hc503222',
						importanceLevel:1,  //1, 重要
						planBeginTime:'2017-1-1',
						planEndTime:'2017-1-2',
						dais:2,  //  台架
						status:1,  // 1，待审批  
						createTime:2017-1-1
					},
					{
						projectName: '6d国四排放升级',
						parentProjectName: '国四排放四阶段',
						projectPrincipal: '张三',   //项目负责人
						machineType: '4d160',
						machineNo:'hc503222',
						importanceLevel:1,  //1, 重要
						planBeginTime:'2017-1-1',
						planEndTime:'2017-1-2',
						dais:2,  //  台架
						status:1,  // 1，待审批  
						createTime:2017-1-1
					},
					{
						projectName: '6d国四排放升级',
						parentProjectName: '国四排放四阶段',
						projectPrincipal: '张三',   //项目负责人
						machineType: '4d160',
						machineNo:'hc503222',
						importanceLevel:1,  //1, 重要
						planBeginTime:'2017-1-1',
						planEndTime:'2017-1-2',
						dais:2,  //  台架
						status:1,  // 1，待审批  
						createTime:2017-1-1
					}
				],
				searchForm: {
					projectName: '6d国四排放升级',
					parentProjectName: '国四排放四阶段',
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
					projectCycle:'7',
					operationInstruction:'http://repucljlsj.com/path',
					propertyCharacterList:'http://repucljlsj.com/path',
					machineNoList:'http://repucljlsj.com/path',
					dais:2,  //  
					status:1,  // 1，待审批  
					isStoppage: 1,  
					createTime:2017-1-1,
					updateTime:2017-1-1,
					memo:'wangbadan'
				},

				pickerOptions2: {
          			shortcuts: [
          				{
				            text: '最近一周',
				            onClick(picker) {
				              	const end = new Date();
				              	const start = new Date();
				              	start.setTime(start.getTime() - 3600 * 1000 * 24 * 7);
				              	picker.$emit('pick', [start, end]);
				            }
          				},
          				{
				            text: '最近一个月',
				            onClick(picker) {
				            const end = new Date();
				            const start = new Date();
				            start.setTime(start.getTime() - 3600 * 1000 * 24 * 30);
				            picker.$emit('pick', [start, end]);
				        }
          			}, {
			            text: '最近三个月',
			            onClick(picker) {
			              const end = new Date();
			              const start = new Date();
			              start.setTime(start.getTime() - 3600 * 1000 * 24 * 90);
			              picker.$emit('pick', [start, end]);
			            }
          			}
          			]
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
        	],
        	projectSataus: [
        		{
		          	value: '1',
		          	label: '待审批'
		        },
		        {
		          	value: '2',
		          	label: '退回'
		        },
		        {
		          	value: '3',
		          	label: '待下发'
		        },
		        {
		          	value: '4',
		          	label: '正在进行'
		        },
		        {
		          	value: '5',
		          	label: '故障中'
		        },
		        {
		          	value: '6',
		          	label: '已完结'
		        }
        	],

        		value6: '',
        		value7:'',
			}
		},
		methods: {
			// 查看详情
			goToDetail(data) {
				this.$router.push({
                    path: '/frame/waitpassdetail',
                    query: {
                        id: 1,
                    }
                })
			},
			// 创建项目
			cteateProject(){
				this.$router.push({
                    path: '/frame/createproject',
                    query: {
                        
                    }
                })
			},
			searchProject() {
				alert('我要查询')
			},
			goTopass(data) {
				// 点击通过
			}
		}
	}
</script>


<style lang="scss">

</style>

