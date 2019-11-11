//菜单处理
$(function () {
    $("#side-menu").metisMenu();

    //固定菜单栏
    $(function () {
        $(".sidebar-collapse").slimScroll({
            height: '100%',
            railOpacity: 0.9,
            alwaysVisible:false
        });
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

    //通过遍历给菜单加上data-index属性
    $(".menuItem").each(function (index) {
        if (!$(this).attr('data-index'));
        $(this).attr('data-index',index);
    })
})


