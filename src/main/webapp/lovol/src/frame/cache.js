define(['configbase','jquery'], function(BaseConfig,$) {
    'use strict';
    /**
     * @property    {Object}
     * 实例化缓存对象层级架构集合
     * @private
     */
    var insMapTemp = {};

    /**
     * @method
     * 创建实例化对象缓存结构
     * @private
     * @param   {string}    scope       作用区域
     * @param   {String[]}  [subBox]    子区域集合。使用时会对数组中每个值遍历其子区域
     */
    function createInstanceByScope(scope, subBox) {
        insMapTemp[scope] = {
            // 实例对象集合
            instances: [],
            // xhr对象集合
            xhr: {},
            // 子元素标示数组
            subBox: subBox || []
        };
    }
    createInstanceByScope(BaseConfig.contentId);
    createInstanceByScope('homeAside', ['homeAsideSub']);
    createInstanceByScope('homeAsideSub');
    /**
     * @method
     * 验证是否有作用区域，如果没有，则进行创建
     * @private
     */
    function validateScope(scope) {
        if (!scope) {
            return;
        }
        if (!insMapTemp[scope]) {
            createInstanceByScope(scope);
        }
    }

    // 缓存xhr对象的前置key
    var xhrPrefixKey = 'xhr';
    return {
        /**
         * @method
         * 获取查看保存的所有实例与xhr对象
         */
        getAllInstances: function() {
            return insMapTemp;
        },
        pauseXhr: function() {
            for (var i in insMapTemp) {
                for (var t in insMapTemp[i].xhr) {
                    var tempObj = insMapTemp[i].xhr[t]
                    insMapTemp[i].xhr[t].instance.abort();
                    insMapTemp[i].xhr[t] = $.extend(tempObj, { paused: true })
                }
            }
        },
        sendXhr: function() {
            for (var i in insMapTemp) {
                for (var t in insMapTemp[i].xhr) {
                    if (insMapTemp[i].xhr[t].paused) {
                        insMapTemp[i].xhr[t].instance = $.ajax(insMapTemp[i].xhr[t].obj)
                    }
                }
            }
        },
        /**
         * @method
         * 将实例根据作用区域添加到 {@link Feeler.Instance#insMapTemp} 的 instances 集合中
         * @param   {Object}    obj         要添加的实例化对象
         * @param   {string}    [scope]     作用区域
         */
        addInstance: function(obj, scope) {
            validateScope(scope);
            scope = scope || BaseConfig.CONTENT_BOX_ID;
            insMapTemp[scope].instances.push(obj);
        },
        /**
         * @method
         * 将XMLHttpRequest对象对象根据作用区域添加到 {@link Feeler.Instance#insMapTemp} 的 xhr 集合中
         * @param   {Object}    xhr         要添加的 XMLHttpRequest对象
         * @param   {string}    [scope]     作用区域。如果没有此参数则不进行添加
         */
        addXhr: function(xhr, ajaxObj, id, scope) {
            if (scope) {
                validateScope(scope);
                insMapTemp[scope].xhr[xhrPrefixKey + id] = { instance: xhr, obj: ajaxObj, paused: false };
            }
        },

        /**
         * @method
         * 移除在 {@link Feeler.Instance#insMapTemp} 的 xhr 属性上缓存的XMLHttpRequest对象
         * @param   {string}    id      存储的xhr对象索引
         * @param   {string}    scope   作用区域。如果没有此参数则不做任何操作
         */
        removeXhr: function(id, scope) {
            if (!scope || typeof id === 'undefined') {
                return;
            }
            delete insMapTemp[scope].xhr[xhrPrefixKey + id];
        },
        /**
         * @method
         * 在 {@link Feeler.Instance#insMapTemp} 中根据作用区域遍历，执行注销事件，消除对象与引用
         * @param   {string}    [scope]     作用区域
         */
        destroy: function(scope) {
            scope = scope || BaseConfig.contentId;
            // 递归销毁
            function deepDestroy(item) {
                if (!item) {
                    return;
                }
                var subBox = item.subBox,
                    len, i;
                if (subBox.length > 0) {
                    len = subBox.length;
                    for (i = 0; i < len; i++) {
                        // 递归调用
                        deepDestroy(insMapTemp[subBox[i]]);
                    }
                }
                var insLen = item.instances.length,
                    insIdx, insItem, xhrProp, xhrItem;
                // 中断依然存在的XHR请求
                for (xhrProp in item.xhr) {
                    xhrItem = item.xhr[xhrProp];
                    if (xhrItem.instance) {
                        if (xhrItem.instance.abort) {
                            xhrItem.instance.abort();
                        }
                    }
                    // 移除XHR对象
                    delete item.xhr[xhrProp];
                }
                // 执行注销事件
                for (insIdx = 0; insIdx < insLen; insIdx++) {
                    insItem = item.instances[insIdx];
                    if (typeof insItem.destroy === 'function') {
                        insItem.destroy();
                    }
                }
                // 解除引用
                item.instances = [];
                item.xhr = {};
            }
            // 销毁对象
            deepDestroy(insMapTemp[scope]);
        }
    };

});
