export function createUser(that,data){
	if(!data) return
	let options = {
		type: 'POST',
		params: data,
		url:'/system/user/addUser',
		webType: 'managerLab',
	}		
	return that._httpRequest(options)
}

export function getUserList(that,data){
	if(!data) return
	let options = {
		type: 'GET',
		params: data,
		url:'/system/user/getUserList',
		webType: 'managerLab',
	}		
	return that._httpRequest(options)
}

export function setUser(that,data){
	if(!data) return
	let options = {
		type: 'POST',
		params: data,
		url:'/system/user/updateUser',
		webType: 'managerLab',
	}		
	return that._httpRequest(options)
}

