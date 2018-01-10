<template>
	<div class="login">
		<div class="form-container">
            <el-form :model="userForm">
                <!--logo-->
                <el-form-item>
                    <div class="logo"></div>
                </el-form-item>
                <!--user name-->
                <el-form-item>
                    <el-input v-model="userForm.userName" placeholder="请输入用户名">
                        <span slot="prepend" class="icon-user"></span>
                    </el-input>
                </el-form-item>
                <!--password-->
                <el-form-item>
                    <el-input v-model="userForm.password" type="password" placeholder="请输入登录密码">
                        <span slot="prepend" class="icon-lock"></span>
                    </el-input>
                </el-form-item>
                <!--remember me-->
                <el-form-item>
                    <el-checkbox v-model="userForm.remeberMe">记住我</el-checkbox>
                </el-form-item>
                <!--login button-->
                <el-form-item>
                    <el-button id='submitBtn' @click="submitForm()" type='primary' class="btn-submit" size='large'>登陆</el-button>
                </el-form-item>
            </el-form>
        </div>
	</div>
</template>

<script>
    import { login } from 'api/login';
	export default {
		data(){
			return {
                userForm: {
                    userName: '',
                    password: '',
                    remeberMe: false
                }
			}
		},
		methods: {
            submitForm () {
                let params = {
                    userName: this.userForm.userName,
                    password: this.userForm.password
                }
                console.log(params)

                this._login(params);
            },
			_login(params) {
            	login(this, params).
                    then(res=> {
                       if(res.status == 0) {
                            this.$router.push({ 
                                path: '/frame/user'
                            })
                       }else {
                            this.$message({
                                message: res.message,
                                type: 'error',
                                duration: 1500,
                            });
                       }
                    })       
			}
		}
	}
</script>

<style lang="scss" scoped rel="stylesheet/scss">
	.login {
        width: 100vw;
        height: 100vh;
        position: relative;
        overflow: hidden;
        background: url("./login-bg.png") no-repeat center;
        .form-container {
            background: rgba(255, 255, 255, 0.8);
            width: 430px;
            /*height: 406px;*/
            height: 377px;
            /*padding: 40px;*/
            padding: 40px 40px 0;
            position: absolute;
            left: 0;
            right: 0;
            top: 0;
            bottom: 0;
            margin: auto;
            border-radius: 15px;
            &>img {
                position: absolute;
                &:nth-of-type(1) {
                    left: -98px;
                    top: 84px;
                }
                &:nth-of-type(2) {
                    right: -109px;
                    bottom: 65px;
                }
            }
            .el-form {
                .logo {
                    height: 50px;
                    text-align: center;
                    img {
                        display: inline;
                        width: 100%;
                    }
                }
                .icon-user{
                	font-size: 18px;
                }
                .icon-lock{
                	font-size: 18px;
                }
                .verificationcode-container {
                    .verificationcode-input-box {
                        width: 230px;
                        float: left;
                        input {
                            padding-left: 20px;
                        }
                    }
                    .verificationcode-img-box {
                        float: right;
                        img {
                            max-height: 34px;
                        }
                    }
                }
                .el-checkbox {
                    .el-checkbox__input {
                        &.is-checked {
                            .el-checkbox__inner {
                                background: #7c7de4;
                                border-color: #7c7de4;
                            }
                        }
                        &.is-focus {
                            .el-checkbox__inner {
                                border-color: #7c7de4;
                            }
                        }
                    }
                    .el-checkbox__label {
                        font-size: 14px;
                        color: #eeeeee;
                    }
                }
                .btn-submit {
                    width: 100%;
                    font-size: 18px;
                }
            }
        }
    }
</style>