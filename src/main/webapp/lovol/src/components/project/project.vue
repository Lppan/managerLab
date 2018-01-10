<template>
	<div class="project">

	<!-- 头部搜索部分 -->
        <div class="ai_search">
           	<el-form :inline="true" :model="searchForm"  >
				  	<el-form-item label="试验任务">
				    	<el-input size="small" v-model="searchForm.projectName"></el-input>
				  	</el-form-item>

				  	<el-form-item label="所属项目">
				    	<el-select size="small" v-model="searchForm.parentProjectName" placeholder="请选择活动区域">
				      		<el-option
						      	v-for="item in parentProjectNameOptions"
						      	:key="item.value"
						      	:label="item.label"
						      	:value="item.value">
						    </el-option>
				    	</el-select>
				  	</el-form-item>

				  	<el-form-item label="机型负责人">
				    	<el-input size="small" v-model="searchForm.projectPrincipal"></el-input>
				  	</el-form-item>

				  	<el-form-item label="机型号">
				    	<el-input size="small" v-model="searchForm.machineType"></el-input>
				  	</el-form-item>

				  	<el-form-item label="发动机编号">
				    	<el-input size="small" v-model="searchForm.machineNo"></el-input>
				  	</el-form-item>

				  	<el-form-item label="状态">
				    	<el-select size="small" v-model="searchForm.importanceLevel" placeholder="请选择活动区域">
				      		<el-option
						      	v-for="item in projectSataus"
						      	:key="item.value"
						      	:label="item.label"
						      	:value="item.value">
						    </el-option>
				    	</el-select>
				  	</el-form-item>
				  	<el-form-item label="状态">
					  	<el-date-picker
					  		size="small"
						    v-model="value7"
						    type="daterange"
						    align="right"
						    unlink-panels
						    range-separator="至"
						    start-placeholder="开始日期"
						    end-placeholder="结束日期"
						    :picker-options="pickerOptions2">
	   					 </el-date-picker>
   					</el-form-item>
           	</el-form> 
        </div>

        <div class="">
			<el-button type="primary" @click="searchProject">查询</el-button>
		</div>

        <div class="">
			<el-button type="primary" @click="cteateProject">创建项目</el-button>
		</div>

	<!-- 退回 -->
	<el-dialog title="退回提示" :visible.sync="backProjectDialog">
		  	<el-form :model="backForm" label-width="80px">
			    <el-form-item label="活动形式">
				    <el-input type="textarea" v-model="backForm.memo"></el-input>
				</el-form-item>
		  	</el-form>
		  <div slot="footer" class="dialog-footer">
		    <el-button @click="backProjectDialog = false">取 消</el-button>
		    <el-button type="primary" @click="ConfirmBackProject">确 定</el-button>
		  </div>
		</el-dialog>

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
      	prop="machineNoList"
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
      	label="创建时间"
      	prop="createTime"
      	>
    </el-table-column>
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
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="180">
      <template slot-scope="scope">
        <el-button  type="text" size="small" @click="goToDetail(scope.row)">查看详情</el-button>
        <el-button  type="text" size="small" @click="_setProjectPass(scope.row, 'pass')">通过</el-button>
        <el-button  type="text" size="small" @click="_setProjectBack(scope.row, 'back')">退回</el-button>
      </template>
    </el-table-column>
  </el-table>
	</div>
</template>

<script>
	import { getProjectList, setProjectPass, setProjectBack } from 'api/project';
	export default {
		data() {
			return {
				backForm: {
					memo: ''
				},
				backProjectDialog: false,
				projectlist:[
					
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
		created() {
			this._getProjectList();
			let backid = '';
		},
		methods: {

			ConfirmBackProject() {
				this.backProjectDialog = false;
				let params = {
					"id": this.backid,
					"memo" : this.backForm.memo
				}
					setProjectBack(this, params)
						.then(res => {
							if(res.status == 0) {
								this.$message({
			                        message: '退回成功',
			                        type: 'success',
			                        duration: 1000,
			                    });
			                    setTimeout(()=>{
			                    	window.location.reload();
			                    },1000)
							} else {
								this.$message({
			                        message: '退回失败',
			                        type: 'error',
			                        duration: 1000,
			                    });
							}
						})

			},
			// 项目通过
			_setProjectPass(data) {
				console.log(data);
				let id = data.id;
				let params = {
					"id": id
				}
				setProjectPass(this,params)
					.then(res => {
						console.log(res)
					})
			},
			_setProjectBack(data) {
				this.backProjectDialog = true;
				this.backid = data.id
			},

			// 查看详情
			goToDetail(data) {
				alert('查看详情')
			},
			// 创建项目
			cteateProject(){
				this.$router.push({
                    path: '/frame/createproject',
                    query: {
                        
                    }
                })
			},
			// 获取项目列表
			_getProjectList() {
				getProjectList(this)
					.then(res => {
						if(res.status == 0) {
							this.projectlist = res.data;
						}
					})
			},

			searchProject() {

			}
		}
	}
</script>


<style lang="scss">

</style>