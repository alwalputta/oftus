$(document).ready(function() {
    //Message while the page is still being loaded.
//    show_page_loading_message();
 
    element_id = 0;
    category_from = 0;
    category_to = 0;

    search_box_default_text = "Enter Your Search ...";
    $('#search-input').val(search_box_default_text);

    set_sortable();
 
    //menu dropdowns
    $('#menu > li').bind('mouseover', menu_open);
    $('#menu > li').bind('click', menu_open)
    $('#menu > li').bind('mouseout', menu_timer);

    set_div_dimensions();
    // $('.middle-row-element:even').css('background-color','lightgrey');

    //locking the page, golden code to mask the page, make non-ediatble
    // $('.mask').fadeIn(300);
//    $('.loading').fadeIn(300);
    $('.message').fadeOut(10000);
    $("tr.rowc:even").css("background-color", "#grey");
    $("tr.rowb:even").css("background-color", "#ABDCFF");
});

$(function() {
    $(this).tooltip({
        track: true
    });
    $(this).tooltip({
        tooltipClass: "tooltip"
    });
});
  
$(".message").hover(function() {
    $(this).stop().fadeOut();
    $(this).fadeIn();
}, function() {
    $(this).fadeIn();
    $(this).stop().fadeOut(10000);
});
  
$('input[type=button]').hover(function(){
    $(this).css('border','3px solid red');
}, function(){
    $(this).css('border','1px solid black');
});

//control v
$(document).keydown(function(e){
    var element = $(this);
    if (e.ctrlKey && e.keyCode == 86) {
    //        show_add_element_window('copy_v', element); //ctrl + v on mac
    //Add something to call add new element action
    } else if (e.ctrlKey && e.keyCode == 70) { //ctrl + f on mac
        $('#search-input').focus();
    }
});

$(document).bind('paste', (function(e){
    // var $this = $(this); //save reference to element for use laster
    // setTimeout(function(){ //break the callstack to let the event finish
    // alert(e.clipboardData.getData('text/plain')); //read the value of the input field 
    // },0);
    $('.new-element-modal input[name=hiperLink]').focus();
})
);

$(document).keyup(function(e) {
    if (e.keyCode == 27) { //esc key
        $('.mask').hide();
//        $('.loading').hide();
        $('.message').hide();
        $('.middle-column-element-edit-icons').hide();
        $('.middle-row-element-edit-icons').hide();
        $('div').removeClass('middle-row-element-selected');
        $('div').removeClass('middle-row-element-dropped');
 
        set_div_dimensions();
        menu_close();

        var search_text = $('#search-input').val();
        if ((search_text).length > 0) {
            search_and_hide("");
            $('#search-input').val(search_box_default_text);
        }
    } 
});

$('#search-input').keydown(function(e) {
    if($('#search-input').val() == search_box_default_text) {
        $('#search-input').val("");
    }
});

// Search
$('#search-input').keyup(function(event) {
    var search_text = $('#search-input').val();
    search_and_hide(search_text);
});

$('.ui-icon').hover(function(){
    $(this).toggleClass('ui-state-highlight');
});

//open the page
$(document).click(function(){
    menu_close();
    //Fades in the page when clicked on the document/page
    // $('.mask').fadeIn(300);
    // Commented for time being, uncomment as needed
    $('.mask').hide();
});

//putting default value when no text is entered in the search box
var Input = $('#search-input');
Input.focus(function() {
    if(Input.val() == search_box_default_text) {
        Input.val("");
    }
}).blur(function(){
    if(Input.val().length == 0) {
        Input.val(search_box_default_text);
    }
});

$('.middle-column-element-text').mouseenter(function(){
    $('.middle-column-element-edit-icons').hide();
    $('.middle-row-element-edit-icons').hide();
    var element = $(this).parent('.middle-column-element');
    var position = element.position();
    var top = parseInt($('.dockingBarTop').css('height').replace('px',''));
    var left = position.left;
    var width = parseInt(element.css('width').replace('px',''));

    element.children('.middle-column-element-edit-icons').show();
    element.children('.middle-column-element-edit-icons').css('top', top+10);
    element.children('.middle-column-element-edit-icons').css('left', (left+width-100));
    element.children('.middle-column-element-edit-icons').css('width', 50);
});

//Commented temporarily
//$('.middle-column-element-text').mouseout(function(){
//    var element = $(this).parent('.middle-column-element');
//    element.children('.middle-column-element-edit-icons').hide();
//});

$('.middle-row-element-text').mouseenter(function(){
    $('.middle-column-element-edit-icons').hide();
    $('.middle-row-element-edit-icons').hide();
    var element = $(this).parent('.middle-row-element');
    var position = element.position();
    var top = position.top + parseInt($('.dockingBarTop').css('height').replace('px',''));
    var left = element.closest('.middle-column-element').position().left; 
    var width = parseInt(element.css('width').replace('px',''));

    element.children('.middle-row-element-edit-icons').show();
    element.children('.middle-row-element-edit-icons').css('top', top+10);
    element.children('.middle-row-element-edit-icons').css('left', (left+width-100));
    element.children('.middle-row-element-edit-icons').css('width', 70);
});

//Commented temporarily
////$('.middle-row-element-text').mouseout(function(){
//    var element = $(this).parent('.middle-row-element');
//    element.children('.middle-row-element-edit-icons').hide();
//});

$('.middle-column-element-text').dblclick(function(){
    // alert ('column');
    targetUrl = 'edit_category?categoryId='+$(this).parent('.middle-column-element').attr('id');
    window.location=targetUrl;
});

$('.middle-row-element').dblclick(function(){
    // alert ('row1');
    targetUrl = 'edit_bookmark?bookmarkId='+$(this).attr('id');
    targetUrl = targetUrl + '&categoryId='+$(this).parents('.middle-column-element').attr('id');
    window.location = targetUrl;
});

$('.middle-row-element-text').click(function(){
    loadData();
    element = $(this);
    bookmark_id = element.parents('.middle-row-element').attr('id');
    // alert ('bookmark_id:' + bookmark_id);
    // alert ('url:' + $('#' + bookmark_id).data("bookmark").hiperLink);
    targetUrl = 'open_bookmark?bookmarkId='+bookmark_id;
    // alert (targetUrl);
    // $.ajax(targetUrl);
    window.location = targetUrl;
// window.location=$('#' + bookmark_id).data("bookmark").hiperLink;
});

$('.favicon').click(function(){
    //alert ('Your favorite icon.');
    $(this).parent('.middle-row-element').toggleClass('middle-row-element-selected');
    $('div').removeClass('middle-row-element-dropped');
});

$('.middle-row-element-bulk-delete-icon').click(function(){
    //    alert ('middle-row-element-bulk-delete-icon');
    var bookmarks = "";
    var targetUrl = "delete_bookmarks?bookmarks=";
    if ($('.middle-row-element-selected').length > 0) {
        $('.middle-row-element-selected').each(function(){
            var element = $(this);
            //            alert ('CCCCCC3000:'+element.attr('id'));
            bookmarks = bookmarks + element.attr('id') + ":";
            $(this).hide();
        });
        targetUrl = targetUrl + bookmarks;
        //        alert('AAAA' + targetUrl);
        $.ajax(targetUrl);
    }
});

$('.middle-column-element-new-icon').click(function(){
    var targetUrl = "add_category?source=mainPage";
    //        alert('AAAA' + targetUrl);
    window.location = targetUrl;
});

//Load images after the page load is complete
$(window).load(function () {
//    $('.loading').hide();
    $.each(document.images, function() {
        //alert ('image');
        var this_image = this;
        var src = $(this_image).attr('src') || '';
        if(!src.length > 0){
            //alert ('image');
            //this_image.src = options.loading; // show loading
            var lsrc = $(this_image).attr('lsrc') || '';
            if(lsrc.length > 0) {
                var img = new Image();
                img.src = lsrc;
                $(img).load(function() {
                    this_image.src = this.src;
                });
            }
        }
    });
});

function set_sortable(){
    //Draggability of the columns
    // $("#middle-column-sortable").draggable({containment: '.mainTable'});
    $('#middle-column-sortable').sortable({
        start: function(event, ui) {
        // alert ('start1');
        },
        update: function(event, ui) {
        // alert ('update1');
        },
        stop: function(event, ui) {
            // alert ('stop1');
            update_column_order();
        },
        receive: function(event, ui) {
        //Run this code whenever an item is dragged and dropped into this list
        // alert('receive just joined this list1');
        },
        remove: function(event, ui) {
        //Run this code whenever an item is dragged and dropped out of this list
        // alert('remove just left this list1');
        }
    }).disableSelection();

    //draggability of elements across the columns
    $('#middle-row-sortable, #middle-row-sortable').sortable({
        connectWith: '.connectedSortable',
        start: function(event, ui) {
            // alert ('start1:' + $(ui.item).html());
            // $('.middle-row-element-selected').each(function(){
            // alert ('aaaa'+$(this).html());
            // });
            $('.middle-row-element-selected').appendTo(ui.item);
        },
        update: function(event, ui) {
        // alert ('update function.');
        },
        receive: function(event, ui) {
            //Run this code whenever an item is dragged and dropped into this list
            //alert('receive.just joined this list ....');
            element_id = $(ui.item).children().attr('id');
            //alert ('receive1:' + element_id2);

            parent = $(this).parents('.middle-column-element');
            //alert ('receive2:' + parent2.attr('id'));
 
            category_to = parent.attr('id');
            targetUrl = 'targetUrl' + element_id;
        // alert ('receive URL:' + targetUrl);
        },
        remove: function(event, ui) {
            //Run this code whenever an item is dragged and dropped out of this list
            //alert ('remove1: just left this list');
            // alert ('remove3:' + $(ui.item).text());
            // alert ('remove4:' + $(ui.item).html());
            // alert ('remove5:' + $(ui.item).children().attr('id'));
 
            element_id = $(ui.item).children().attr('id');
            //alert ('remove6:' + element_id);
 
            parent = $(this).parents('.middle-column-element');
            //alert ('remove7:' + parent.attr('id'));
 
            category_from = parent.attr('id');
            targetUrl = 'targetUrl' + element_id;
        // alert ('remove URL:' + targetUrl);
        // move_bookmark($(this), ui);
        },
        stop: function(event, ui) {
            // alert ('stop:' + $(this).html());
            //alert ('stop.element_id' + element_id);
            //alert ('stop.category_from' + category_from);
            //alert ('stop.category_to' + category_to);
 
            if (category_from != "0") {
                move_bookmark(element_id, category_to);
            // update_bookmark_order($(this));
            }
            // ui.item.after(ui.item.find('li'));

            if ($('.middle-row-element-selected').length > 0) {
                $('.middle-row-element-selected').each(function(){
                    var element = $(this);
                    // alert ('CCCCCC3000:'+element.attr('id'));
                    move_bookmark(element.attr('id'), category_to);
                    // update_bookmark_order($(this));
                    $(this).removeClass('middle-row-element-selected');
                // $(this).addClass('middle-row-element-dropped');
                });
            }
            update_bookmark_order($(this));
        }
    }).disableSelection();
}

function update_column_order(){
    column_order = "";
    targetUrl = "update_category_order?categoryOrder=";
    $('.middle-column-element').each(function(){
        column_order = column_order + $(this).attr('id') + ':';
    });
    //alert ('category_order:'+column_order);
    targetUrl = targetUrl + column_order;
    $.ajax(targetUrl);
}

function update_bookmark_order(element){
    bookmark_order = "";
    targetUrl = "update_bookmark_order?bookmarkOrder=";
    parent = element.parents('.middle-column-element');
    parent.find('.middle-row-element').each(function(){
        bookmark_order = bookmark_order + $(this).attr('id') + ':';
    });
    targetUrl = targetUrl + bookmark_order;
    // alert ('bookmark order url:' + targetUrl);
    $.ajax(targetUrl);
}


function move_bookmark(element_id, category_to) {
    bookmark_order = "";
    targetUrl = "move_bookmark?bookmarkId=" + element_id;
    targetUrl = targetUrl + "&categoryId=" + category_to;

    //alert ('URL:' + targetUrl);
    $.ajax(targetUrl);
}

function search_and_hide (search_text){
    // var search_text = $('#search-input').val();
    var rg = new RegExp(search_text, 'i');
    $('.middle-row-element-text').each(function() {
        if ((search_text).length > 0) {
            $(this).removeHighlight().highlight(search_text);
        } else {
            $(this).removeHighlight();
        }

        if($.trim($(this).html()).search(rg) == -1) {
            $(this).parent('.middle-row-element').css('display', 'none');
        }
        else {
            $(this).parent('.middle-row-element').css('display', '');
        }
    });
}

//Menu
var timeout = 1000;
var closetimer = 0;
var menuitem = 0;

function menu_open() {
    menu_canceltimer();
    menu_close();
    menuitem = $(this).find('ul').css('visibility', 'visible');
}

function menu_close() {
    if(menuitem) {
        menuitem.css('visibility', 'hidden');
    }
}

function menu_timer() {
    closetimer = window.setTimeout(menu_close, timeout);
}

function menu_canceltimer() {
    if(closetimer) {
        window.clearTimeout(closetimer);
        closetimer = null;
    }
}
//document.onclick = menu_close;

function set_div_dimensions() {
//    $('.loading').css('top', $('.dockingBarTop').css('height'));
    $('.mainTable').css('top', $('.dockingBarTop').css('height'));
    $('.login-modal').css('top', $('.dockingBarTop').css('height'));
    $('.new-category-modal').css('top', $('.dockingBarTop').css('height'));
    $('.new-element-modal').css('top', $('.dockingBarTop').css('height'));
    $('.register-modal').css('top', $('.dockingBarTop').css('height'));
    $('.register-register').css('top', $('.dockingBarTop').css('height'));

    var window_width = $(window).width();

    $('.bottom-row-item').css('height', $('.dockingBarBottom').css('height'));
    $('.middle-row-bottom-spacer').css('height', $('.dockingBarBottom').css('height'));

    var number_of_columns = $('.middle-column-element').length;
    $('.middle-column-element').css('width', ((window_width/number_of_columns)-5) + 'px');
}

function show_page_loading_message () {
    $('.loading').css('display', 'block');
    $('.loading').css('visibility', 'visible');
    $('.loading').css('left', $(window).width()/2-$('.loading').width()/2);
}

