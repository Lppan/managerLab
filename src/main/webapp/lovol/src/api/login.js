export function login(that,data){
	if(!data) return
	let options = {
		type: 'POST',
		params: data,
		url:'system/login',
		webType: 'managerLab',
	}		
	return that._httpRequest(options)
}

export function logout(that,data){
	if(!data) return
	let options = {
		type: 'POST',
		params: data,
		url:'system/logout',
		webType: 'managerLab',
	}		
	return that._httpRequest(options)
}

