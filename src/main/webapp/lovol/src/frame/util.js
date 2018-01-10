/**
 * 工具类
 **/


var $ = require('jquery');
require("jquery-highlight")
var util = {
    errorCodeMap: {
        '100': '用户不可用',
        '101': '没有此用户',
        '102': '密码错误',
        '103': '验证码错误',
        '104': '无权限',
        '105': '操作失败',
        '999': '登录超时'
    },
    customModal: function(opt) {
        this.customNotice();
        this.baseTipsObj && this.baseTipsObj.close();
        this.basejBoxObj && this.basejBoxObj.close();
        if (!opt) {
            return;
        }
        var defaultOpt = {
            title: '',
            content: 'Input Error',
            // closeButton: 'box',
            maxHeight: window.innerHeight - 80
        }
        defaultOpt = _.extend({}, defaultOpt, opt);
        if (this.basejBoxObj) {
            this.basejBoxObj.setTitle(defaultOpt.title);
            this.basejBoxObj.setContent(defaultOpt.content);
            this.basejBoxObj.setWidth(defaultOpt.width);
            this.basejBoxObj.setHeight(defaultOpt.height);
            this.basejBoxObj.position();
        } else {
            this.basejBoxObj = new jBox('Modal', defaultOpt);
        }
        this.basejBoxObj.open();
        return this.basejBoxObj;
    },
    customNotice: function(opt) {
        this.basejBoxObj && this.basejBoxObj.close();
        this.baseTipsObj && this.baseTipsObj.close();
        if (!opt) {
            return;
        }
        var loading = {
            width: 200,
            height: 50,
            content: '<div class="jBox-spinner"></div>',
            autoClose: false,
            overlay: true,
            closeOnEsc: false,
            closeOnClick: false,
            closeButton: false
        }
        var defaultOpt = {
            content: 'Wait 5 Seconds',
            autoClose: 2000,
            position: {
                x: 'center',
                y: 'center'
            }
        }
        defaultOpt = $.extend({}, defaultOpt, opt);
        if (opt == 'loading') {
            defaultOpt = $.extend({}, defaultOpt, loading);
        }
        this.baseTipsObj = new jBox('Notice', defaultOpt);
    },
    /**
     * @method
     * 日期格式化
     * @param   {Object/number} date        日期对象或毫秒
     * @param   {string}        [format]    格式化字符串
     * @param   {number}        [range]     返回值根据此参数向前或向后计算日期，以“天”为单位
     *
     * @return  {string}    根据格式化字符串格式化之后的日期
     */
    formatDate: function(date, format, range, utc) {
        var ms, sourceDate = date;
        if (date) {
            date = new Date(date);
            if (isNaN(date - 0)) {
                ms = Number(sourceDate);
                if (!isNaN(ms)) {
                    date = new Date(ms);
                } else {
                    date = new Date();
                }
            }
        } else if (typeof date === 'undefined' || date === null) {
            return '-';
        } else {
            date = new Date();
        }
        // 数据容错
        if (typeof format === 'number') {
            range = format;
            format = null;
        }
        format = format || 'YYYY/MM/DD hh:mm:ss';
        if (typeof range === 'number') {
            date = new Date(date - 0 + (range * 24 * 60 * 60 * 1000));
        }
        // 补充0
        function prefixZero(num) {
            if (num < 10) {
                return 0 + num;
            }
            return num;
        }

        var year = date.getFullYear() + '',
            month = date.getMonth() + 1 + '',
            day = date.getDate() + '',
            hours = date.getHours() + '',
            minutes = date.getMinutes() + '',
            seconds = date.getSeconds() + '',
            //utc time
            pY = /Y+/.exec(format), // 匹配年份
            pM = /M+/.exec(format), // 匹配月份
            pD = /D+/.exec(format), // 匹配日期
            ph = /h+/.exec(format), // 匹配小时
            pm = /m+/.exec(format), // 匹配分钟
            ps = /s+/.exec(format); // 匹配秒
        if (!utc) {
            year = date.getUTCFullYear() + '',
                month = date.getUTCMonth() + 1 + '',
                day = date.getUTCDate() + '',
                hours = date.getUTCHours() + '',
                minutes = date.getUTCMinutes() + '',
                seconds = date.getUTCSeconds() + '';
        }
        // 年份替换
        if (pY) {
            if (pY[0].length === 2) {
                year = year.slice(2);
            }
            format = format.replace(pY[0], year);
        }
        // 月份替换
        if (pM) {
            if (pM[0].length === 2) {
                month = prefixZero(month);
            }
            format = format.replace(pM[0], month);
        }
        // 日期替换
        if (pD) {
            if (pD[0].length === 2) {
                day = prefixZero(day);
            }
            format = format.replace(pD[0], day);
        }
        // 小时替换
        if (ph) {
            if (ph[0].length === 2) {
                hours = prefixZero(hours);
            }
            format = format.replace(ph[0], hours);
        }
        // 分钟替换
        if (pm) {
            if (pm[0].length === 2) {
                minutes = prefixZero(minutes);
            }
            format = format.replace(pm[0], minutes);
        }
        // 秒数替换
        if (ps) {
            if (ps[0].length === 2) {
                seconds = prefixZero(seconds);
            }
            format = format.replace(ps[0], seconds);
        }
        return format;
    },
    //customDataTable
    customDataTable: function(opt) {
            var that = this;
            var tableTEMP;
            //列选择生成
            var createCulumnSelect = function(target, api, oSettings) {
                if ($('#'+target).data('isbinded')) {
                    return;
                }
                var _checkArr = [];
                var getChkArr = function() {
                    var chkArr = [0];
                    var noChkArr = [];
                    $.each($('#'+target).find('input'), function(e, ele) {
                        if ($(this).prop('checked')) {
                            chkArr.push($(ele).val());
                        } else {
                            noChkArr.push($(ele).val());
                        }
                    })
                    return {
                        chkArr: chkArr,
                        noChkArr: noChkArr
                    };
                }
                // var temp = $('#columnSelectTMP').html();
                var columnsArr = oSettings.aoColumns;
                // var html = Feeler.template(temp)({
                //    columnsArr: columnsArr
                // });

                // $(document).off('click.dropdown.data-api').on('click.dropdown.data-api','', function(e) {
                    // var _checkvalue = $('#'+target).data("nochecked")||[];
                    // $('#'+target).find('input').prop('checked',true);
                    // $.each($('#'+target).find('input'), function(e, ele) {
                    //     if (_checkvalue.indexOf($(this).val()) < 0) {
                    //         $(this).prop('checked',true)
                    //     } else {
                    //         $(this).prop('checked',false)
                    //     }
                    // })
                // })
                $('#'+target).siblings(".cn_btn2.btn_a").off("click.checkbox").on("click.checkbox",'',function(){
                    var _checkvalue = $('#'+target).data("nochecked")||[];
                    $('#'+target).find('input').prop('checked',true);
                    $.each($('#'+target).find('input'), function(e, ele) {
                        if (_checkvalue.indexOf($(this).val()) < 0) {
                            $(this).prop('checked',true)
                        } else {
                            $(this).prop('checked',false)
                        }
                    })
                })
                $('#'+target).data('isbinded', 1).off('click').on('click',function(e) {
                    e.stopPropagation();
                });
                $('#'+target).find('.co_s_save').unbind().bind('click', function() {
                    $(document).trigger('click');
                    var arrMap = getChkArr();
                    $('#'+target).data("nochecked",arrMap.noChkArr)
                    tableTEMP.api().columns(arrMap.chkArr).visible(true);
                    tableTEMP.api().columns(arrMap.noChkArr).visible(false);
                    oSettings.columnsChange && oSettings.columnsChange();
                    /*$.each(arr, function(e, ele) {
                        tableTEMP.api().column(Number(ele.idx)).visible(ele.visible, false);
                    })*/
                })
                $('#'+target).find('.co_s_cancel').unbind().bind('click', function() {
                    $(document).trigger('click');
                })
            }

            var o = {
                cusAutoHeight: false, //表格自动高度
                hasFoot: false, //是否包含表格尾部
                columnSelectTarget: false, //是否开启列选择
                order: [],
                //data: dataSet,
                scrollY: true, //是否允许垂直滚动 ['200px']
                scrollX: true, //是否允许水平滚动
                scrollCollapse: true,
                autoWidth: true, //是否自动宽度
                paging: true, //是否允许表格分页
                pagingType: "simple_numbers", //翻页类型选择[numbers | simple | simple_numbers | full | full_numbers ]
                pageLength: 5, //默认每页数据数量
                processing: true, //是否显示正在处理的状态
                search: '', //初始化时的表格的过滤（搜索）条件
                searching: false, //是否允许开启本地搜索
                searchDelay: 300, //设定搜索的间隔时间
                deferRender: true, //延迟渲染,以提高初始化的速度
                info: false, //是否显示表格的信息
                ordering: true, //是否允许排序
                orderMulti: false, //是否允许多列排序
                lengthChange: true, //是否允许最终用户改变表格每页显示的记录数
                stateSave: false, //是否保存表格状态,再次加载页面时还原状态
                orderClasses: true, //排序列添加class实现高亮
                lengthMenu: [
                    [5, 10, 20, 50, 100], //, -1 //value
                    [5, 10, 20, 50, 100] //, "All" //name
                ], //显示的记录数选项值
                dom: '<"datatable_tp"f><"tb_cont"tr><"datatable_ft"ipl>', // 'l' - Length changing |  'f' - Filtering | 'r' - Processing |  't' - table! |  'i' - Information |  'p' - Pagination | 'B'  -Button //展示顺序
                // buttons: ['csv'],
                language: {
                    zeroRecords: "Nothing found - sorry", //当结果为空时，显示的信息
                    infoEmpty: "No records available", //当表格没有数据时，汇总地方显示的字符串
                    lengthMenu: "Display _MENU_ records", //选择每页数据提示
                    search: "search... :", //搜索框前缀
                    searchPlaceholder: "Search...", //搜索框Placeholder
                    loadingRecords: "Loading...", //加载数据时的提示信息 - 当 Ajax请求数据时显示
                    processing: "Processing...", //当table处理用户处理信息时，显示的信息字符串
                    //翻页配置
                    paginate: {
                        first: "First",
                        previous: 'Previous',
                        next: 'Next',
                        last: "Last"
                    },
                    info: 'Showing _START_ to _END_ of _TOTAL_ entries'
                },
                columns: [],
                columnDefs: [
                    /*{
                        "targets": [11, 15 ,'nosort_class'],
                        "visible": false,
                        "searchable": false
                        "orderable": false
                    }*/
                ], //指定一列或者几列属性
                /**
                 * @method
                 * 表格每次重绘回调函数
                 * @ param {Obj}     oSettings    Datatables的设置对象
                 **/
                drawCallback: function(oSettings) {
                    var api = this.api();
                    var data = api.data();
                    if (!opt.hasFoot) {
                        $(oSettings.nTFoot).remove();
                    }
                    /*
                    oSettings.nScrollBody
                    oSettings.nTable
                    oSettings.nTableWrapper
                    oSettings.nTHead
                    oSettings.nTBody
                    oSettings.nTFoot
                    */
                    //$(oSettings.nTableWrapper).find('.dataTables_filter').hide();
                    var pagingDom = $(oSettings.nTableWrapper).find('.dataTables_paginate');
                    var filterDom = $(oSettings.nTableWrapper).find('.dataTables_filter');
                    var tableEle = $(oSettings.nTable);
                    var nTBodyTd = $(oSettings.nTBody).find('tr td:gt(1)');
                    if (!data.length) {
                        pagingDom.hide();
                    } else {
                        pagingDom.show();
                    }
                    if (opt.searchingHide) {
                        filterDom.hide();
                    }
                    //自定义搜索
                    // tableEle.off('search.dt').on('search.dt', function(evt, obj) {
                    //     var searchIpt = $('#searchVal');
                    //     if (searchIpt) {
                    //         $(tableEle).removeHighlight();
                    //         $(tableEle).highlight(searchIpt.val());
                    //     }
                    // });
                    var _batchSet = $('#batchSet');

                    //自动高度
                    if (opt.cusAutoHeight) {
                        //表格高度自适应
                        $(window).resize(function() {
                            $(oSettings.nScrollBody).height($(window).height() - 300);
                        })
                        $(window).resize();
                    }
                    //列选择
                    if (opt.columnSelectTarget) {
                        createCulumnSelect(opt.columnSelectTarget, api, oSettings);
                    }
                },
                /**
                 * @method
                 * tfoot的回调函数
                 * @ param {Dom}     tfoot  table的表脚
                 * @ param {Array}   data   table的所有数据对象
                 **/
                footerCallback: function(tfoot, data, start, end, display) {
                    //console.log(tfoot)
                },
                /**
                 * @method
                 * 表格行(Row)绘制的回调函数
                 * @ param {Dom}      row        已经被创建的tr元素
                 * @ param {Array}    data       包含这行的数据对象
                 * @ param {Number}   index  内部存储的数据索引
                 **/
                rowCallback: function(row, data, index) {
                    //console.log(index)
                },
                /**
                 * @method
                 * 当表格完成加载绘制完成后执行此方法
                 * @ param {Obj}      oSettings   datatables的设置对象
                 * @ param {Array}    json        ajax选项来获取数据，则得到服务器返回的数据，否则是 undefined
                 **/
                initComplete: function(oSettings, json) {
                    //console.log(json)
                }
            }
            if (opt.fnServerData) {
                opt.serverSide = true;
            }
            //继承使用时的回调
            var cacheOptDraw = opt.drawCallback;
            var defaultDrarwCallback = o.drawCallback;
            if (cacheOptDraw) {
                opt.drawCallback = function(set) {
                    defaultDrarwCallback && defaultDrarwCallback.call(this, set);
                    cacheOptDraw && cacheOptDraw.call(this, set);
                }
            }
             o = $.extend({}, o, opt);
            //生成dom
            var randomTableID = 'DT_' + (new Date().getTime());
            var tableThead = ['<thead><tr>'];
            var tableBody = ['<tbody></tbody>'];
            var tableFoot = ['<tfoot><tr>'];
            $.each(o.columns, function(e, ele) {
                tableThead.push('<th>' + ele.title + '</th>');
                tableFoot.push('<th>' + ele.title + '</th>');
            })
            tableThead.push('</thead>');
            tableFoot.push('</tfoot>');
            var tableHtml = '<table class="table table-striped table-bordered table-hover" style="border:0 none;" cellspacing="0" id="' + randomTableID + '" width="100%">' + tableThead.join('') + tableBody.join('') + tableFoot.join('') + '</table>';
            $('#' + o.targetId).html(tableHtml);
            //绑定table
            tableTEMP = $('#' + randomTableID).dataTable(o);
            window.tableTEMP = tableTEMP;
            return tableTEMP;
        },
    //添加总计行
    /**
     * @ oSettings     tableSeting
     * @ firstIdx      第一个汇总字段
     * @ totalRowData  汇总数据
     **/
    addTotalRowFn: function(oSettings, totalRowData) {
        var that = this;
        totalRowData = totalRowData || {};
        //所有foot中的th
        var nTable = $(oSettings.nTFoot.childNodes[0].childNodes);
        var i = 0;
        //标记已汇总状态
        var isSumFlag = 0;
        //所有列数据配置
        var columnsArr = oSettings.aoColumns;
        var leftHiddenColumnsLen = 0;
        $(oSettings.nTFoot.childNodes[0]).attr('class', 'total_row');
        //功能未完善
        $.each(columnsArr, function(e, ele) {
            if (ele.columnSelect === false) {
                i++;
                leftHiddenColumnsLen++;
                nTable.eq(i).html('');
                return true;
            }
            //隐藏的列的跳过处理
            if (ele.bVisible === false) {
                return true;
            }
            //有汇总数据
            // console.log(ele.data, totalRowData[ele.data])
            if (typeof(totalRowData[ele.data]) !== 'undefined') {
                //当前列的前一列(不是第一列)修改文本及样式
                if ((i > leftHiddenColumnsLen) && !isSumFlag) {
                    nTable.eq(i - 1).addClass('tb_ta_rt').html('Total');
                }
                //填充数据
                nTable.eq(i).html(ele.render(totalRowData[ele.data]));
                //标记已汇总状态
                isSumFlag = 1;
            } else {
                nTable.eq(i).html('');
            }
            i++;
        })
    },
    //清理datatable遗留table对象
    clearDataTableCache: function() {
        if (!$.fn.dataTable) {
            return;
        }
        var arr = $.fn.dataTable.tables({
            api: true
        }); //所有table实例
        arr.destroy();
    },
    LS: function() {
        var tool = {
            set: function(key, value) {
                //在iPhone/iPad上有时设置setItem()时会出现诡异的QUOTA_EXCEEDED_ERR错误
                //这时一般在setItem之前，先removeItem()就ok了
                if (this.get(key) !== null) {
                    this.remove(key);
                }
                localStorage.setItem(key, value);
            },
            //查询不存在的key时，有的浏览器返回undefined，这里统一返回null
            get: function(key) {
                var v = localStorage.getItem(key);
                return v === undefined ? null : v;
            },
            remove: function(key) {
                localStorage.removeItem(key);
            },
            clear: function() {
                localStorage.clear();
            }
        }
    },
    exportDataForm: function(opt) {
            var FormEleOld = document.getElementById('ExportDataFormID');
            var FormEle = FormEleOld || document.createElement("form");
            // var urlPrefix = Feeler.Config.Url.prefix.download;
            if (!FormEleOld) {
                FormEle.id = 'ExportDataFormID';
            }
            FormEle.target = opt.target;
            FormEle.action = opt.action;
            FormEle.method = opt.method;
            for (var key in opt.params) {
                if (opt.params.hasOwnProperty(key)) {
                    var input = document.createElement("input");
                    input.type = "hidden";
                    input.name = key;
                    input.value = opt.params[key];
                    FormEle.appendChild(input);
                }
            }
            if (!FormEleOld) {
                document.body.appendChild(FormEle);
            }
            FormEle.submit();
        },
}

module.exports = util;
