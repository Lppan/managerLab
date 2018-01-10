<template>
	<div class="user">
		 <el-table
    ref="multipleTable"
    :data="tableData3"
    tooltip-effect="dark"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      	label="创建时间"
      	prop="createTime"
      	>
    </el-table-column>
    <el-table-column
      prop="userName"
      label="姓名"
      >
    </el-table-column>
    <el-table-column
      prop="phone"
      label="电话号码"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      prop="type"
      label="角色"
      show-overflow-tooltip>
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="180">
      <template slot-scope="scope">
        <el-button @click="editUser(scope.row)" type="text" size="small">编辑</el-button>
        <el-button @click="deleteUser(scope.row)" type="text" size="small">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

  	<!-- 删除用户 -->
  	<el-dialog
  title="警告"
  :visible.sync="deleteUserTips"
  width="30%"
  :before-close="handleClose">
  <span>确认删除该用户？删除后不可恢复！</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="CancelDelete">取 消</el-button>
    <el-button type="primary" @click="ConfirmDelete">确 定</el-button>
  </span>
</el-dialog>
	</div>
</template>

<script>
  export default {
    data() {
      return {
      	cteateUserDialog: false,
      	editUserDialog: false,
      	deleteUserTips: false,
      	createUserForm: {
      		userName: '',
      		phone: '',
      		status: true,
      		type: 1,
      	},
      	editUserForm: {
      		
      	},
        tableData3: [
	        {
	        	id:1,
	          	createTime: '1514369033000',
	          	phone: '18515423537',
	          	userName: '上海市普陀区金沙江路 1518 弄',
	          	status: true,
	          	type: '1',
	        },
	        {
	        	id:2,
	          	createTime: '1514369033000',
	          	phone: '18515423537',
	          	userName: '上海市普陀区金沙江路 1518 弄',
	          	status: true,
	          	type: '1',
	        },
	        {
	        	id:3,
	          	createTime: '1514369033000',
	          	phone: '18515423537',
	          	userName: '上海市普陀区金沙江路 1518 弄',
	          	status: true,
	          	type: '1',
	        },
	        {
	        	id:4,
	          	createTime: '1514369033000',
	          	phone: '18515423537',
	          	userName: '上海市普陀区金沙江路 1518 弄',
	          	status: true,
	          	type: '1',
	        }
	    ],
        multipleSelection: [],
        dialogFormVisible: false,
        form: {
          name: ''
        }
      }
    },
    created() {
    	this.WillDeleteUser = null;
    },
    methods: {
    	// 添加用户
    	cteateUser() {
    		this.cteateUserDialog = true;
    	},
    	// 确认添加用户
    	ConfirmCreate() {
    		alert(JSON.stringify(this.createUserForm));
    		this.cteateUserDialog = false;
    	},
    	// 删除用户
    	deleteUser(data) {
    		this.deleteUserTips = true;
    		this.WillDeleteUser = data;
    	},
    	// 编辑用户
    	editUser(data) {
    		this.editUserDialog = true;
    		this.editUserForm = data;
    	},
    	ConfirmEdit() {
    		let editUserFormData = this.editUserForm;
    		this.editUserDialog = false;
    	},
    	CancelDelete() {
    		this.deleteUserTips = false;
    		this.WillDeleteUser = null;
    	},

    	ConfirmDelete() {
    		let id  = this.WillDeleteUser.id;
    		let index = this.findeIndex(this.tableData3, id);
    		if(index != 'undefind'){
    			this.tableData3.splice(index,1);
    		}
    		this.deleteUserTips = false;
    	},
    	findeIndex(arr, id) {
    		for(var i=0; i< arr.length; i++) {
    			if(arr[i].id == id){
    				return i
    			}
    		}
    	}
    }
  }
</script>

<style lang="scss">
	
</style>

