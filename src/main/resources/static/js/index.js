$(function () {
    $("#side-menu").metisMenu();
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


