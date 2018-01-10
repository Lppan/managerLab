<template>
	<div class="user">
		<el-dialog title="添加用户" :visible.sync="cteateUserDialog">
		  	<el-form :model="createUserForm" label-width="80px">
			    <el-form-item label="用户名">
			      <el-input v-model="createUserForm.userName" auto-complete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="角色">
			      <el-input v-model="createUserForm.type" auto-complete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="手机号">
			      <el-input v-model="createUserForm.phone" auto-complete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="立即开启">
				    <el-switch v-model="createUserForm.status"></el-switch>
				</el-form-item>
		  	</el-form>
		  <div slot="footer" class="dialog-footer">
		    <el-button @click="cteateUserDialog = false">取 消</el-button>
		    <el-button type="primary" @click="ConfirmCreate">确 定</el-button>
		  </div>
		</el-dialog>
		<el-dialog title="编辑用户" :visible.sync="editUserDialog">
		  	<el-form :model="editUserForm" ref="form" label-width="80px">
			    <el-form-item label="用户名"">
			      <el-input v-model="editUserForm.userName" auto-complete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="角色">
			      <el-input v-model="editUserForm.type" auto-complete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="手机号">
			      <el-input v-model="editUserForm.phone" auto-complete="off"></el-input>
			    </el-form-item>
			    <el-form-item label="立即开启">
				    <el-switch v-model="editUserForm.status"></el-switch>
				</el-form-item>
		  	</el-form>
		  <div slot="footer" class="dialog-footer">
		    <el-button @click="editUserDialog = false">取 消</el-button>
		    <el-button type="primary" @click="ConfirmEdit">确 定</el-button>
		  </div>
		</el-dialog>
		<div class="">
			<el-button type="primary" @click="cteateUserClick">创建用户</el-button>
      <el-button type="primary" @click="logout">创建用户</el-button>
		</div>
		 <el-table
    ref="multipleTable"
    :data="userList"
    tooltip-effect="dark"
    style="width: 100%">
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
  
  import { createUser , getUserList, setUser} from 'api/user';

  export default {
    data() {
      return {
      	cteateUserDialog: false,
      	editUserDialog: false,
      	deleteUserTips: false,
        pageMap: {
          page:1
        },
      	createUserForm:{
      		userName: '',
      		phone: '',
      		status: true,
      		type: 1,
      	},
      	editUserForm: {
      		
      	},
        userList: [],
        multipleSelection: [],
        dialogFormVisible: false,
        form: {
          name: ''
        }
      }
    },
    created() {
    	this.WillDeleteUser = null;

      this._getUserList();
    },
    methods: {
      _getUserList() {
        var params = {
          page: this.pageMap.page
        }
        getUserList(this,params)
          .then(res => {
             if(res.status == 0) {
                this.userList = res.data;
             }
          })
      },

    	// 添加用户
    	cteateUserClick() {
    		this.cteateUserDialog = 1;
    	},
      // 
      _logout() {
      
      },
    	// 确认添加用户
    	ConfirmCreate() {
        this.cteateUserDialog = false;
    		createUser(this, this.createUserForm)
          .then(res => {
            if(res.status == 0) {
                this.$message({
                    message: '用户添加成功',
                    type: 'success',
                    duration: 800,
                });
            }else {
                this.$message({
                    message: '网络错误，请刷新页面重新添加',
                    type: 'error',
                    duration: 800,
                });
            }
          })
    		
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
    		let params = this.editUserForm;
        setUser(this, params)
          .then(res => {
            if(res.status == 0) {
              console.log(res);
            }
          })
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