//菜单处理
$(function () {
    $("#side-menu").metisMenu();

    //固定菜单栏
    $(function () {
        $(".siderbar-collapse").slimScroll({
            height: '100%',
            railOpacity: 0.9,
            alwaysVisible:false
        });
    })


    $('#side-menu li').click(function () {
        if ($('body').hasClass('mini-navbar')){
            NavToggle();
        }
    })

    //菜单切换
    $('.navbar-minimlize').click(function () {
        $('body').toggleClass("mini-navbar");
        SmoothlyMenu();
    })

})





function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar')){
        $('#side-menu').hide();
        setTimeout(function () {
            $('#side-menu').fadeIn(500);
        },100)
    }else if ($('body').hasClass('fixed-sidebar')){
        $('#side-menu').hide();
        setTimeout(function () {
            $('#side-menu').fadeIn(500);
        },300);
    } else {
        $('#side-menu').removeAttr('style');
    }
}

function NavToggle(){
    $('.navbar-minimlize').trigger('click');
}

/**
 * iframe处理
 */
$(function () {
    //计算元素集合的总宽度
    function calSumWidth(elements){
        var width = 0;
        $(elements).each(function () {
            width += $(this).outerWidth(true);
        });
        return width;
    }

    //滚动到指定选项卡
    function scrollToTab(element) {
        var marginLeftVal = calSumWidth($(element).prevAll()),
            marginRightVal = calSumWidth($(element).nextAll());
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").outerWidth() < visibleWidth) {
            scrollVal = 0;
        } else if (marginRightVal <= (visibleWidth - $(element).outerWidth(true) - $(element).next().outerWidth(true))) {
            if ((visibleWidth - $(element).next().outerWidth(true)) > marginRightVal) {
                scrollVal = marginLeftVal;
                var tabElement = element;
                while ((scrollVal - $(tabElement).outerWidth()) > ($(".page-tabs-content").outerWidth() - visibleWidth)) {
                    scrollVal -= $(tabElement).prev().outerWidth();
                    tabElement = $(tabElement).prev();
                }
            }
        } else if (marginLeftVal > (visibleWidth - $(element).outerWidth(true) - $(element).prev().outerWidth(true))) {
            scrollVal = marginLeftVal - $(element).prev().outerWidth(true);
        }
        $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            },
            "fast");
    }

    //查看左侧隐藏的选项卡
    function scrollTabLeft() {
        var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").width() < visibleWidth) {
            return false;
        } else {
            var tabElement = $(".menuTab:first");
            var offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { //找到离当前tab最近的元素
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            offsetVal = 0;
            if (calSumWidth($(tabElement).prevAll()) > visibleWidth) {
                while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                    offsetVal += $(tabElement).outerWidth(true);
                    tabElement = $(tabElement).prev();
                }
                scrollVal = calSumWidth($(tabElement).prevAll());
            }
        }
        $('.page-tabs-content').animate({
                marginLeft: 0 - scrollVal + 'px'
            },
            "fast");
    }

    //查看右侧隐藏的选项卡
    function scrollTabRight() {
        var marginLeftVal = Math.abs(parseInt($('.page-tabs-content').css('margin-left')));
        // 可视区域非tab宽度
        var tabOuterWidth = calSumWidth($(".content-tabs").children().not(".menuTabs"));
        //可视区域tab宽度
        var visibleWidth = $(".content-tabs").outerWidth(true) - tabOuterWidth;
        //实际滚动宽度
        var scrollVal = 0;
        if ($(".page-tabs-content").width() < visibleWidth) {
            return false;
        } else {
            var tabElement = $(".menuTab:first");
            var offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) <= marginLeftVal) { //找到离当前tab最近的元素
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            offsetVal = 0;
            while ((offsetVal + $(tabElement).outerWidth(true)) < (visibleWidth) && tabElement.length > 0) {
                offsetVal += $(tabElement).outerWidth(true);
                tabElement = $(tabElement).next();
            }
            scrollVal = calSumWidth($(tabElement).prevAll());
            if (scrollVal > 0) {
                $('.page-tabs-content').animate({
                        marginLeft: 0 - scrollVal + 'px'
                    },
                    "fast");
            }
        }
    }


    //通过遍历给菜单加上data-index属性
    $(".menuItem").each(function (index) {
        if (!$(this).attr('data-index'));
        $(this).attr('data-index',index);
    })


    function menuItem() {
        //获取标识数据
        var dataUrl = $(this).attr('href')
        dataIndex = $(this).data('index')
        menuName = $.trim($(this).text())
        flag = true;
        if (dataUrl == undefined || $.trim(dataUrl).length == 0) return false;

        //选项卡已存在
        $('.menuTab').each(function () {
            if ($(this).data('id') == dataUrl){
                if (!$(this).hasClass('active')){
                    $(this).addClass('active').sibling('.menuTab').removeClass('active');
                    scrollToTab(this)
                    //显示tab对应的内容区
                    $('.mainContent .yht_iframe').each(function () {
                        if ($(this).data('id') == dataUrl){
                            $(this).show().sibling('.yht_iframe').hide();
                            return false;
                        }
                    });
                }
                flag =false;
                return false;
            }
        })


        //选项卡菜单不存在
        if(flag){
            var str = '<a href="javascript:;" class="active menuTab" data-id="' + dataUrl + '">' + menuName + '<i class="fa fa-times-circle"></i></a>';
            $('.menuTab').removeClass('active');
        }
        //添加选项卡对应的iframe
        var str1 = '<frame class="yht_iframe" name="iframe' + dataIndex + '" width="100%" height="100%" src="' + dataUrl + '" frameborder="0" data-id="' + dataUrl + '" seamless></frame>';
        $('.mainContent').find('iframe.yht_iframe').hide().parents('.mainContent').append(str1);

        //添加选项卡
        $('.menuTabs .page-tabs-content').append(str);
        scrollToTab($('.menuTab.active'));

        return false;
    }

    $('.menuItem').on('click',menuItem);



})


//iframe高度调整
var oIframe = window.top.document.getElementById("menuFrame");
var oBody = document.getElementsByTagName("body")[0];
oIframe.style.height = oBody.offsetHeight + 580 + 'px';
