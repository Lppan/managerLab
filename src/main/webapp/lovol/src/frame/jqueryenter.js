// define(['jquery'], function(jquery) {
//     (function($) {
(function (root, factory) {
    if (typeof define === 'function' && define.amd) {
        // AMD. Make globaly available as well
        define(['jquery'], function (jquery) {
            return (root.jqueryenter = factory(jquery));
        });
    } else if (typeof module === 'object' && module.exports) {
        // Node / Browserify
        //isomorphic issue
        var jQuery = (typeof window != 'undefined') ? window.jQuery : undefined;
        if (!jQuery) {
            jQuery = require('jquery');
            if (!jQuery.fn) jQuery.fn = {};
        }
        module.exports = factory(jQuery);
    } else {
        // Browser globals
        root.jqueryenter = factory(root.jQuery||root.$);
    }
}(window, function($) {
    $.fn.extend({
        enterSearchFn: function() {
            'use strict';
            var curEle = $(this);
            //不是input,textarea
            if (this[0].tagName != 'INPUT' && this[0].tagName != 'TEXTAREA') {
                $.each(curEle.find('input[onenter]'), function(index, item) {
                    inputEnter.call(this);
                });
                $.each(curEle.find('textarea[onenter]'), function(index, item) {
                    textareaEnter.call(this);
                });
            } else if (curEle.length > 1) {
                curEle.each(function(index, item) {
                    if (item.tagName == "INPUT") {
                        inputEnter.call(this);
                    } else {
                        textareaEnter.call(this);
                    }
                });

            } else {
                if (this[0].tagName == 'INPUT') {
                    inputEnter.call(this);
                }
                if (this[0].tagName == 'TEXTAREA') {
                    textareaEnter.call(this);
                }
            }
            var inputEnterTimeOut;

            function inputEnter() {
                var self = this,
                    enterID = $(self).attr("onenter");
                if (self.isBindEnter) {
                    return;
                }
                //阻止输入焦点的默认事件,IE下连续触发bug
                $(self).parent().off('keydown').on('keydown', function(e) {
                    if (e.keyCode == 13) {
                        e.preventDefault();
                    }
                })
                $(self).off('keyup').on("keyup", function(e) {
                    if (e.keyCode == 13) {
                        if (enterID) {
                            $("#" + enterID).focus().click();
                        }
                    }
                });
                self.isBindEnter = true;
            }
            //textarea enter方法
            function textareaEnter() {
                var self = this,
                    enterID = $(self).attr("onenter");
                if (self.isBindEnter) {
                    return;
                }
                $(self).off('keyup').on("keyup", function(e) {
                    if (e.ctrlKey && (e.keyCode == 13 || e.keyCode == 10)) {
                        if (enterID) {
                            $("#" + enterID).focus().click();
                        }
                    }
                });
                self.isBindEnter = true;
            }
            return this;
        }
    })
    return $
}));
