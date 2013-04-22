$(document).ready(function() {

    //Message while the page is still being loaded.
    //comented temporarily
    // show_page_loading_message();
    
    categories = [];
    max_row_element_id = 0;
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
    $('.middle-row-element:even').css('background-color','lightgrey');

    //locking the page, golden code to mask the page, make non-ediatble
    //    $('.mask').fadeIn(300);
    $('.loading').fadeIn(300);

//check if mac
// if(navigator.userAgent.indexOf("Mac OS X") != -1) {
// alert(MAC.OS);
// }
});

$('input[type=button]').hover(function(){
    $(this).css('border','3px solid red');
},
function(){
    $(this).css('border','1px solid black');
});

//control v
$(document).keydown(function(e){
    var element = $(this);
    if (e.ctrlKey && e.keyCode == 86) {
        show_add_element_window('copy_v', element); //ctrl + v on mac
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
        $('.mask').remove();
        $('.loading').hide();
        $('.middle-column-element-edit-icons').hide();
        $('.middle-row-element-edit-icons').hide();
        $('.inline-column-element-edit').hide();
        $('.inline-row-element-edit').hide();
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

//open the page
$(document).click(function(){
    menu_close();
    //Fades in the page when clicked on the document/page
    //    $('.mask').fadeIn(300);

    // Commented for time being, uncomment as needed
    $('.mask').remove();
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

$('.middle-column-element-heading').mouseenter(function(){
    var element = $(this).parent('.middle-column-element');
    var position = element.position();

    var top = parseInt($('.dockingBarTop').css('height').replace('px',''));
    var left = position.left;
    var width = parseInt(element.css('width').replace('px',''));

    element.children('.middle-column-element-edit-icons').show();
    element.children('.middle-column-element-edit-icons').css('top', top);
    element.children('.middle-column-element-edit-icons').css('left', (left+width-30));
});

$('.middle-column-element-heading').mouseout(function(){
    var element = $(this).parent('.middle-column-element');
    element.children('.middle-column-element-edit-icons').hide();
});

$('.middle-row-element-text').mouseenter(function(){
    var element = $(this).parent('.middle-row-element');
    var position = element.position();

    var top = position.top + parseInt($('.dockingBarTop').css('height').replace('px',''));
    var left = element.closest('.middle-column-element').position().left;    
    var width = parseInt(element.css('width').replace('px',''));

    element.children('.middle-row-element-edit-icons').show();
    element.children('.middle-row-element-edit-icons').css('top', top);
    element.children('.middle-row-element-edit-icons').css('left', (left+width-30));
});

$('.middle-row-element-text').mouseout(function(){
    var element = $(this).parent('.middle-row-element');
    element.children('.middle-row-element-edit-icons').hide();
});

$('.middle-column-element-heading').dblclick(function(){
    //    alert ('column');
    targetUrl = 'edit_category?categoryId='+$(this).parent('.middle-column-element').attr('id');
    window.location=targetUrl;
});

$('.middle-row-element').dblclick(function(){
    //    alert ('row1');
    targetUrl = 'edit_bookmark?bookmarkId='+$(this).attr('id');
    targetUrl = targetUrl + '&categoryId='+$(this).parents('.middle-column-element').attr('id');
    window.location=targetUrl;
});

$('.favicon').click(function(){
    alert ('Your favorite icon.');
});

function set_sortable(){
    //Draggability of the columns
    // $("#middle-column-sortable").draggable({containment: '.mainTable'});
    $('#middle-column-sortable').sortable({
        start: function(event, ui) {
//            alert ('start1');
        },
        update: function(event, ui) {
//            alert ('update1');
        },
        stop: function(event, ui) {
//            alert ('stop1');
            update_column_order();
        },
        receive: function(event, ui) {
            //Run this code whenever an item is dragged and dropped into this list
//            alert('receive just joined this list1');
        },
        remove: function(event, ui) {
            //Run this code whenever an item is dragged and dropped out of this list
//            alert('remove just left this list1');
        }
    }).disableSelection();

    //draggability of elements across the columns
    $('#middle-row-sortable, #middle-row-sortable').sortable({
        connectWith: '.connectedSortable',
        start: function(event, ui) {
//            alert ('start');
        },
        update: function(event, ui) {
//            alert ('update');
        },
        stop: function(event, ui) {
            //            alert ('stop:' + $(this).html());
            alert ('stop.element_id' + element_id);
            alert ('stop.category_from' + category_from);
            alert ('stop.category_to' + category_to);
            
            if (category_from == "0") {
                alert ('bookmark order if statement');
                update_bookmark_order($(this));
            } else {
                move_bookmark(element_id, category_to);
            }
        },
        receive: function(event, ui) {
            //Run this code whenever an item is dragged and dropped into this list
            alert('receive.just joined this list ....');
            element_id2 = $(ui.item).children().attr('id');
            alert ('receive1:' + element_id2);
  
            parent2 = $(this).parents('.middle-column-element');
            alert ('receive2:' + parent2.attr('id'));
    
            category_to = parent2.attr('id');
            targetUrl = 'targetUrl' + element_id2;
        //            alert ('URL:' + targetUrl);
        },
        remove: function(event, ui) {
            //Run this code whenever an item is dragged and dropped out of this list
            alert ('remove1: just left this list');
            //            alert ('remove3:' + $(ui.item).text());
            //            alert ('remove4:' + $(ui.item).html());
            //            alert ('remove5:' + $(ui.item).children().attr('id'));
            
            element_id = $(ui.item).children().attr('id');
            alert ('remove6:' + element_id);
  
            parent = $(this).parents('.middle-column-element');
            alert ('remove7:' + parent.attr('id'));
    
            category_from = parent.attr('id');
            targetUrl = 'targetUrl' + element_id;
        //            alert ('URL:' + targetUrl);
        //  move_bookmark($(this), ui);
        }
    }).disableSelection();
}

function update_column_order(){
    column_order = "";
    targetUrl = "update_category_order?categoryOrder=";
    $('.middle-column-element').each(function(){
        column_order = column_order + $(this).attr('id') + ':';
    });
    alert ('category_order:'+column_order);
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
    alert ('url:' + targetUrl);
    $.ajax(targetUrl);
}


function move_bookmark(element_id, category_to) {
    bookmark_order = "";
    targetUrl = "move_bookmark?bookmarkId=" + element_id;
    targetUrl = targetUrl + "&categoryId=" + category_to;
    
    alert ('URL:' + targetUrl);
    
    $.ajax(targetUrl);
}

function search_and_hide (search_text){
    // var search_text = $('#search-input').val();
    var rg = new RegExp(search_text,'i');
 
    // $('.mainTable #middle-column-sortable .middle-column-element #middle-row-element').each(function() {
    $('.middle-row-element-text').each(function() {
        if ((search_text).length > 0) {
            $(this).parent().removeHighlight().highlight(search_text);
        } else {
            $(this).parent().removeHighlight();
        }

        if($.trim($(this).html()).search(rg) == -1) {
            $(this).parent().css('display', 'none');
        }
        else {
            $(this).parent().css('display', '');
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
    // set the top of the middle mainTable
    $('.mainTable').css('top', $('.dockingBarTop').css('height'));
    $('.login-modal').css('top', $('.dockingBarTop').css('height'));
    $('.new-category-modal').css('top', $('.dockingBarTop').css('height'));
    $('.new-element-modal').css('top', $('.dockingBarTop').css('height'));
    $('.register-modal').css('top', $('.dockingBarTop').css('height'));
    $('.register-register').css('top', $('.dockingBarTop').css('height'));
    $('.bottom-row-item').css('height', $('.dockingBarBottom').css('height'));
    $('.middle-row-bottom-spacer').css('height', $('.dockingBarBottom').css('height'));
    
    var window_width = $(window).width();
    var number_of_columns = $('.middle-column-element').length;
    $('.middle-column-element').css('width', ((window_width/number_of_columns)-5) + 'px');
}

function show_page_loading_message () {
    $('.loading').css('display', 'block');
    $('.loading').css('visibility', 'visible');
    $('.loading').css('top', $(window).height()/2-$('.loading').height()/2-100);
    $('.loading').css('left', $(window).width()/2-$('.loading').width()/2);

    $('.loading input:first').focus();
}

$(window).load(function() {
    $('.loading').remove();
});

