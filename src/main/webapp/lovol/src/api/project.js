// 获取项目列表
export function getProjectList(that){
	let options = {
		type: 'POST',
		params: {"name":"1"},
		url:'/projectInfo/projectList',
		webType: 'managerLab',
	}		
	return that._httpRequest(options)
}

// 创建列表
export function createProject(that,data){
	if(!data) return;
	let options = {
		type: 'POST',
		params: data,
		url:'/projectInfo/addProjectInfo',
		webType: 'managerLab',
	}		
	return that._httpRequest(options)
}


// 通过
export function setProjectPass(that,data){
	if(!data) return;
	let options = {
		type: 'POST',
		params: data,
		url:'/projectInfo/pass',
		webType: 'managerLab',
	}		
	return that._httpRequest(options)
}

// 退回
export function setProjectBack(that,data){
	if(!data) return;
	let options = {
		type: 'POST',
		params: data,
		url:'/projectInfo/back',
		webType: 'managerLab',
	}		
	return that._httpRequest(options)
}