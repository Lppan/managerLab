//vue 请求插件开发
export default {
    //适应$aitool_开头标记为工具方法的使用
    install: function (Vue, options) {
        // 添加工具方法
        // Vue.prototype._tool = {};
        //rolecontrol example business.pay.add
        Vue.prototype.$aitool_UserRoleControl = function (rolesymbol) {
            return false
        }
        // 2. 添加全局资源
        Vue.directive('aitool_roleCtrol', {
            deep : true,
            update (el, binding, vnode, oldVnode) {
                let that = this;
                let useraction = JSON.parse((localStorage.userInfo||'{}')).useractions||'';
                let allListRole = JSON.parse(localStorage.roleActions||'{}');;
                let findRoleId = function(rolelist,key) {
                    let role_row = "",_key = key.shift();
                    if(_key){
                        role_row = getRole(rolelist,_key);
                    }
                    if(role_row && role_row["children"] && key.length){
                       return findRoleId(role_row["children"],key)
                    }
                    return role_row||{};
                }
                let getAllChildRoleId = function(role) {
                    let children = role["children"]||[],_role_tree = '';
                    for (let i=0;i< children.length;i++) {
                        let value = children[i];
                      if(value){
                        _role_tree = _role_tree? _role_tree+','+value.id: value.id;
                        if(value["children"]){
                          let _child_tree = getAllChildRoleId(value);
                          _role_tree += ','+_child_tree;
                        }
                      }
                    }
                    return _role_tree
                }
                let getRole = function(rolelist,row_code) {
                    let role_row = rolelist.filter(function(item,index){
                        return (item.label||'').toLowerCase().replace(/\s+/gi,'') == (row_code||'').toLowerCase();
                    });
                    return role_row.length > 0 ? role_row[0]:"";
                }
                let role_action = findRoleId(allListRole,binding.value.split("."));
                let _all_user_tree = getAllChildRoleId(role_action).split(",");
                _all_user_tree.unshift(role_action.id);
                let user_role_action = (useraction||'').split(',');
                let hassame = user_role_action.filter(function(o){return _all_user_tree.indexOf(o)>-1 ? o : null})

                if(!hassame.length){
                    var _parentElement = el.parentNode;
                    if(_parentElement){
                        _parentElement.removeChild(el);
                    }
                };
            }
        })


        //自定义focus指令
        Vue.directive('focus', function(el, option) {
            var defClass = 'el-input',
                defTag = 'input';
            var value = option.value;
            if(typeof value === 'boolean') {
                value = {
                    cls: defClass,
                    tag: defTag,
                    foc: value
                };
            } else {
                value = {
                    cls: value.cls || defClass,
                    tag: value.tag || defTag,
                    foc: value.foc || false
                };
            }
            if(el.classList.contains(value.cls) && value.foc) {
                el.getElementsByTagName(value.tag)[0].focus();
            }
        });

        //扩展属性  图片加载失败显示默认图片
        Vue.prototype.successLoadImg = function(event) {
            // if (event.target.complete == true) {
            //     event.target.classList.remove("default-image");
            //     var imgParentNode = event.target.parentNode;
            //     if(imgParentNode.classList.contains('show-img')==true){
            //         imgParentNode.style.background = "#000";
            //     }
            // }
        };
        Vue.prototype.errorLoadImg = function(event) {
            // event.target.classList.add("default-image");
            event.target.src = require("assets/default.png")
        };

        //注册  数据格式话过滤器
        Vue.filter('numFormatThousand', function(val) {
            val = val.toString().replace(/\$|\,/g,'');
            if(isNaN(val)) {
              val = "0";  
            } 
            let sign = (val == (val = Math.abs(val)));
            val = Math.floor(val*100+0.50000000001);
            let cents = val%100;
            val = Math.floor(val/100).toString();
            if(cents<10) {
               cents = "0" + cents
            }
            for (var i = 0; i < Math.floor((val.length-(1+i))/3); i++) {
                val = val.substring(0,val.length-(4*i+3))+',' + val.substring(val.length-(4*i+3));
            }

            return (((sign)?'':'-') + val + '.' + cents);
        })
    }
};