var config = require('./base')
import axios from 'axios';
var urlPrefix = {
    /**
     * @property    {Object}    prefix
     * URL前缀
     * @property    {string}    prefix.root     根目录
     */
    prefix: {
        // 根目录
        // php/public/index.php/
        root: getRequestPrefix('doapi'),
        managerLab: getRequestPrefix('managerLab'),
    },
    /**
     * @property    {string}    suffix
     * URL后缀
     */
    suffix: '' //.json
};


/**
 * @method
 * 获取请求URL前缀
 * @private
 * @param   {string}    path    前缀路径
 * @param   {string}    root    根目录
 *
 * @return  {string}    基于网站根目录的前缀路径
 */
function getRequestPrefix(path, root) {
    var rootPath = '';
    root = root || location.protocol + "//" + location.hostname + ":" + location.port + '/';
    return root + (rootPath ? rootPath + '/' : '') + (path ? path + '/' : '');
}


/**
 * @method
 * 获取Ajax完整URL。
 * @private
 * @param   {string}    url                 请求基准URL
 * @param   {string}    [webType='root']     请求类型
 * @param   {string}    [suffix='json']     后缀名称
 *
 * @return  {string}    请求的完整URL
 */
function getAjaxUrl(url, webType, suffix) {
    //绝对地址直接返回
    if (url.indexOf('http://') != -1) {
        return url;
    }
    // 目标地址的后缀名与URL的后缀名比对，若一致则直接返回，不进行二次封装
    if (suffix && url.lastIndexOf('.' + suffix) === url.length - ('.' + suffix).length) {
        return url;
    }
    var prefix = urlPrefix.prefix;
    webType = webType || 'root';
    suffix = suffix || urlPrefix.suffix;
    if (url.indexOf('/') === 0) {
        url = url.slice(1);
    }
    return prefix[webType] + url + suffix;
}

function sessionTimeout(that, code) {
    // 会话是否过期
    if (/*code == '104' || */ code == '999') {
        console.warn('Code is ' + code + ',Please Call youself');
        localStorage.removeItem("UUIDSEESION");
        // 跳转到登陆页面
        that.$router.push("/login");
        return true;
    }
}


//vue 请求插件开发
export default {
    install: function (Vue, options) {
        /**
         * [getLoading 创建请求loading]
         * @param  {[type]} type [description]
         * @param  {[that]} that [当前组件对象]
         * @return {[instance]}  [返回loading实例]
         */
        var getLoading = function(type,that){
            var _option = {"text": "Loading..."},_isloading = false;
            if(type == 'full'){
                _option["fullscreen"] = true;
                _isloading = true;
            }else if(type == 'body'){
                _option["body"] = true;
                _isloading = true;
            }else if(type == 'component'){
                _option["target"] = that.$el;
                _isloading = true;
            }else{

            }
            if(_isloading){
                return that.$loading(_option);
            }
            return null;
        }
        // 添加实例方法
        // Vue.prototype._tool = {};
        Vue.prototype._httpRequest = function (options, callback) {
            var that = this;
            var _timeout = config.requestTimeout * 60 * 1000;
            var _isloading = options.isloading? true:false;//
            options.type = options.type ? options.type.toUpperCase() : 'POST';
            options.params = typeof options.params === 'undefined' ? {} : options.params;
            //缺少并发处理 TODO
            let loadingInstance = getLoading(options.loadingtype,this);
            return new Promise(function (resolve, reject) {
                var CancelToken = axios.CancelToken;
                var source = CancelToken.source();
                axios({
                    method: options.type,
                    url: getAjaxUrl(options.url, options.webType, options.dataType),
                    // url:'./index.php/home/'+ options.webType + "/" + options.url,
                    timeout: _timeout,
                    // headers: {'Content-Type':'application/x-www-form-urlencoded;charset=UTF-8'},
                    headers: {'Content-Type':'application/json;charset=UTF-8'},
                    responseType: 'json',
                    data: options.params
                }).then(function (response) {
                    const _data = response.data;
                    loadingInstance ? loadingInstance.close() : null;
                    if (!_data) {
                        // 错误回调
                        reject(_data||'');
                    }
                    if (_data.errorcode) {
                        // 检查会话是否过期
                        if (sessionTimeout(that, _data.errorcode)) {
                            reject(_data);
                        }
                    }
                    resolve(_data);
                }).catch(error => {
                    //网络异常，及请求中断
                    //todo 需分别处理
                    if (error.code == 'ECONNABORTED' && error.message.indexOf('timeout') >= 0) {
                        that.$message({
                            message: 'Request timeout',
                            type: 'error',
                            iconClass: '',
                            duration: 5 * 1000
                        });
                    } else {
                        that.$message({
                            message: '发生异常错误,请刷新页面重试',
                            type: 'error',
                            iconClass: '',
                            duration: 5 * 1000
                        });
                    }

                    loadingInstance ? loadingInstance.close() : null;
                    reject(error);
                });
            })
        }
        // 添加实例方法
        Vue.prototype._httpGetUrl = function (url, webType, suffix) {
            return getAjaxUrl(url, webType, suffix);
        }
        //获取codemap映射
        Vue.prototype._httpErrorCodeMap = function (code) {
            return config.errorCodeMap[code];
        }
        //获取codemap映射
        Vue.prototype._httpCdnPath = function (code) {
            return config.cdnBaseUrl;
        }
        // 1. 添加全局方法或属性
        // Vue.prototype.httploading = false;
        Vue.mixin({
            data(){
                return {
                    httploading:false
                };
            }
        })
    }
};

//todo  待定
const getWebSocket = function (url, webType, suffix) {
    var _url = getAjaxUrl(url, webType, suffix);
    var ws = new WebSocket("ws://ip:port?token=xxxxxxxx");
    ws.onopen = function () {
        ws.send("发送数据");
        console.log('发送数据');
    };
    ws.onmessage = function (evt) {
        var received_msg = evt.data;
        console.log('接收消息' + received_msg);
    };
    ws.onclose = function () {
        // 关闭 websocket
        console.log('关闭');
    };
    return getAjaxUrl(url, webType, suffix);
}
