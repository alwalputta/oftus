$(document).ready(function() {

    //Message while the page is still being loaded.
    //comented temporarily
    // show_page_loading_message();
    
    categories = [];
    column_element = null;
    max_row_element_id = 0;
    active_column_element = null;
    active_row_element = null;


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
 
// show_login_window();

//check if mac
// if(navigator.userAgent.indexOf("Mac OS X") != -1) {
// alert(MAC.OS);
// }

//alert ('bbb');

// var count = $('#middle-column-sortable').length;//count=1----:first .middle-row-element
//    var column_count = $('.middle-column-element').length;
//    alert (column_count);
//    var row_count = $('.middle-row-element').length;
//    alert (row_count);
//
//    $('div.middle-column-element').eq(0).css('color','red'); //works
//    var column_object = $('div.middle-column-element').eq(0);
//    
    


//        obj_p.css('color','black');
//        alert('parent:'+obj_p.text()); //prints all the elements of this div
//        alert ('header:'+i+':'+$('.middle-column-element-heading',obj_p).text());
//        alert ('child count:'+$('.middle-row-element',obj_p).length);


//=========
//Golden code - works
//    $('div.middle-row-element', column_object).each(function (){
//        alert ('child:'+$(this).text());
//    });
//=========
 
 
//    counter1 = 0;
//    counter2 = 0;
//    $('div.middle-column-element').each(function (){
//        counter1++;
//        alert ('parent:'+$(this).text());
//        $(this).children('div.middle-row-element').each(function() {
//            alert ('child:'+$(this).text());
//            counter2++;
//        });
//    });
    
//    loadData();

// jQuery.each (column_object, function(){
// $(this).css('color','red');
// alert ($(this).text());
// });

// alert ($(document.activeElement).hide());

//update_categories();

//    $('.middle-row-element').each(function (){
//        alert ($(this).data('colId'));
//    });

});


$('input[type=button]').hover(function(){
    $(this).css('border','3px solid red');
},
function(){
    $(this).css('border','1px solid black');
});



$('#login').click(function(){
    show_login_window();
});



$('.middle-column-element-heading').dblclick(function(){
    var element = $(this).parent('.middle-column-element');
    inline_edit_column_element(element);
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
    var element = $(this);
    // var $this = $(this); //save reference to element for use laster
    // setTimeout(function(){ //break the callstack to let the event finish
    // alert(e.clipboardData.getData('text/plain')); //read the value of the input field 
    // },0);
    // alert ('2222:'+$this.val());
    show_add_element_window('paste', element); //command+v on mac
})
);



//URL name Edit
$('.middle-row-element').dblclick(function(){
    var self = $(this);
    set_div_dimensions();
    inline_edit_row_element (self);
});


/*
 *Commented, moved the div to a new jsp
$('.middle-row-new-element').click(function(){
    var element = $(this);
    column_element = element.parent('middle-column-element');
    show_add_element_window('new', element);
});
*/


//$('.new-category-modal').click(function(){
//    var element = $(this);
//    show_add_category_window('edit', element);
//});



$(document).keyup(function(e) {
    if (e.keyCode == 27) { //esc key
 
        $('.mask').remove();
        $('.loading').hide();
        //$('.login-modal').hide();
        $('.register-modal').hide();
        $('.forgot-password-modal').hide();
        $('.new-category-modal').hide();
/*        $('.new-element-modal').hide();*/
        $('.edit-element-modal').hide();
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
// $('.login-modal').css("display", "none");
// $('.register-modal').css("display", "none");
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



$('.register-login').click(function(){
    show_login_window();
});


$('.login-register').click(function(){
    show_register_window();
});


$('.forgot_password').click(function(){
    show_forgot_password_window();
});


$('.middle-column-element-heading').hover(function(ev){

    var element = $(this).parent('.middle-column-element');
    var position = element.position();

    var top = parseInt($('.dockingBarTop').css('height').replace('px',''));
    var left = position.left;
    var width = parseInt(element.css('width').replace('px',''));

    $('.middle-column-element-edit-icons').show();
    $('.middle-column-element-edit-icons').css('top', top);
    $('.middle-column-element-edit-icons').css('left', (left+width-30));
    
    active_column_element = element;
});


//$('.middle-column-element').on('click', '.middle-column-element-delete-icon', function(ev){
//    
//    alert ('inclock delete column icon.');
//    var decision = confirm ('Are you sure you want to delete the category?');
//    if (decision) {
//        $(this).parent('.middle-column-element').remove();
//        set_div_dimensions();
//    }
//    ev.stopPropagation();
//});



$('.middle-column-element-edit-icon').click(function(ev){

    alert ('edit column icon.');
    //    var element = $(this).parent('.middle-column-element');
    inline_edit_column_element (active_column_element);
    ev.stopPropagation();
});




$('.middle-column-element-delete-icon').click(function(ev){
    
    alert ('delete column icon.');
    var decision = confirm ('Are you sure you want to delete the category?');
    if (decision) {
        $(this).parent('.middle-column-element').remove();
        set_div_dimensions();
    }
    ev.stopPropagation();
});



$('.middle-row-element-edit-icon').click(function(ev){

    alert ('edit row icon.');
    inline_edit_row_element (active_row_element);
    ev.stopPropagation();
});




$('.middle-row-element-delete-icon').click(function(ev){
    
    alert ('delete row icon.');
    var decision = confirm ('Are you sure you want to delete the Link?');
    if (decision) {
        active_row_element.remove();
        set_div_dimensions();
    }
    ev.stopPropagation();
});





$('.middle-row-element-text').hover(function(ev){

    var element = $(this).parent('.middle-row-element');
    var position = element.position();

    var top = position.top + parseInt($('.dockingBarTop').css('height').replace('px',''));
    
    var left = element.closest('.middle-column-element').position().left;
    
    var width = parseInt(element.css('width').replace('px',''));

    //    alert ('top:' + top);
    //    alert ('left:' + left);
    //    alert ('width:' + width);

    $('.middle-row-element-edit-icons').show();
    $('.middle-row-element-edit-icons').css('top', top);
    $('.middle-row-element-edit-icons').css('left', (left+width-30));

    active_row_element = element;
});




$('.favicon').click(function(){
    alert ('Your favorite icon.');
});





function set_sortable(){
    //Draggability of the columns
    // $("#middle-column-sortable").draggable({containment: '.mainTable'});

    $("#middle-column-sortable").sortable();
    $("#middle-column-sortable").disableSelection();

    //draggability of elements across the columns
    $("#middle-row-sortable, #middle-row-sortable").sortable({
        connectWith: ".connectedSortable"
    }).disableSelection();
}



function search_and_hide (search_text) {
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




function load_bottom_bar(option) {
    alert (option);
}





function set_div_dimensions() {
    // set the top of the middle mainTable
    $('.mainTable').css('top', $('.dockingBarTop').css('height'));
    $('.bottom-row-item').css('height', $('.dockingBarBottom').css('height'));
    $('.middle-row-bottom-spacer').css('height', $('.dockingBarBottom').css('height'));
    
    var window_width = $(window).width();
    var number_of_columns = $('.middle-column-element').length;
    $('.middle-column-element').css('width', ((window_width/number_of_columns)-(number_of_columns-1)) + 'px');

    
//    var window_height = $(window).height();
//    alert ('window_height'+window_height);
//    var middle_window_height = window_height - (parseInt($('.dockingBarBottom').css('height').replace('px','')) - parseInt($('.dockingBarTop').css('height').replace('px','')));
//    var middle_window_bottom = window_height - parseInt($('.dockingBarBottom').css('height').replace('px',''));
//
//    alert ('bbbb'+middle_window_height);
//    alert ('cccc'+middle_window_bottom);
//    
//    $('#middle-row-sortable').css('height',middle_window_height+'px');
//    $('#middle-row-sortable').css('bottom',middle_window_bottom+'px');


//    var max_child_nodes = 0;
//    for (var i=0; i<$('.middle-column-element').length; i++){
//        var obj_p = $('div.middle-column-element').eq(i);
//        
//        if ($('.middle-row-element',obj_p).length > max_child_nodes) {
//            max_child_nodes = $('.middle-row-element',obj_p).length;
//        }
//    }
// 
//    var child_element_height = $('.middle-row-element').css('height').replace('px','');
//    var new_element_height = $('.middle-row-new-element').css('height').replace('px','');
//    var total_child_element_height = max_child_nodes * parseInt(child_element_height) + parseInt(new_element_height);
//
//    var middle_window_height = parseInt($('#middle-row-sortable').css('height').replace('px', ''));
//
//    alert ($(window).height());
//    alert ('total_child_element_height:' + total_child_element_height);
//
//    if (total_child_element_height > middle_window_height) {
//        $('#middle-row-sortable').css('height', (total_child_element_height-100) + 'px'); //100 = 80 top div + 20 bottom div
//    }

}


function show_page_loading_message () {
    $('.loading').css("display", "block");
    $('.loading').css("visibility", "visible");
    $('.loading').css("top", $(window).height()/2-$('.loading').height()/2-100);
    $('.loading').css("left", $(window).width()/2-$('.loading').width()/2);

    $('.loading input:first').focus();
}


function show_login_window () {
        
    $('.register-modal').css("display", "none");
    $('.forgot-password-modal').css("display", "none");
    $('.login-modal').css("display", "block");
    $('.login-modal').css("visibility", "visible");
    $('.login-modal').css("top", $(window).height()/2-$('.login-modal').height()/2-100);
    $('.login-modal').css("left", $(window).width()/2-$('.login-modal').width()/2);
 
    $('.login-modal input:first').focus();
}


function show_register_window () {

    $('.login-modal').css("display", "none");
    $('.forgot-password-modal').css("display", "none");
    $('.register-modal').css("display", "block");
    $('.register-modal').css("visibility", "visible");
    $('.register-modal').css("top", $(window).height()/2-$('.register-modal').height()/2-100);
    $('.register-modal').css("left", $(window).width()/2-$('.register-modal').width()/2);
 
    $('.register-modal input:first').focus();
}



function show_forgot_password_window () {
    $('.login-modal').css("display", "none");
    $('.register-modal').css("display", "none");
    $('.forgot-password-modal').css("display", "block");
    $('.forgot-password-modal').css("visibility", "visible");
    $('.forgot-password-modal').css("top", $(window).height()/2-$('.register-modal').height()/2-50);
    $('.forgot-password-modal').css("left", $(window).width()/2-$('.register-modal').width()/2);
 
    $('.forgot-password-modal input:first').focus();
}

//Delete if not used
function show_add_element_window(action, element) {
    //    alert ('cccc'+column_element.html());
    $('.mask').fadeIn(300);
    alert('1111');
    $('.register-modal').css("display", "none");
    $('.forgot-password-modal').css("display", "none");

    $('.new-element-modal').css("display", "block");
    $('.new-element-modal').css("visibility", "visible");
    $('.new-element-modal').css("top", $(window).height()/2-$('.new-element-modal').height()/2-50);
    $('.new-element-modal').css("left", $(window).width()/2-$('.new-element-modal').width()/2);
    alert('11112222');
    if (action == 'ctrl_v' || action == 'paste') {
        $('.new-element-modal input[name=hiperLink]').focus();
    } else {
        $('.new-element-modal input[name=bookmarkname]').focus();
    }

    alert('1111222333');
    column_element = element.parent('.middle-column-element');
    column_id = column_element.attr('id');
    
    if(column_id != null || trim(column_id) != '') {
        $("#add_bookmark_categoryId").val(column_id).attr('selected', true);
    }


// $('.new-element-modal input:first').trigger('paste',[9]);

// setTimeout(function(){ //break the callstack to let the event finish
//// alert(e.clipboardData.getData('text/plain')); //read the value of the input field 
// },0);

// $('.new-element-modal input[name=group-name]').focus();
//    alert ('bbbbbbb' + column_element.text());

//THIS IS WORKING CODE
//alert ('VVVVV:'+$('.middle-row-new-element').index(element));
///XXXXXX
}



//function show_add_category_window(categoryId) {
//    alert ('ccccccc:' + categoryId);
////    $('.mask').fadeIn(300);
//
////    $('.new-category-modal').css("display", "block");
////    $('.new-category-modal').css("visibility", "visible");
//    $('.new-category-modal').css("top", $(window).height()/2-$('.new-category-modal').height()/2-50);
//    alert('4444');
//    $('.new-category-modal').css("left", $(window).width()/2-$('.new-category-modal').width()/2);
//    alert('5555');
//    if (action == 'ctrl_v' || action == 'paste') {
//        $('.new-category-modal input[name=categoryname]').focus();
//    } else {
//        $('.new-category-modal input[name=categoryname]').focus();
//    }
//    alert('6666');
//}


function inline_edit_row_element (element) {
    element.css('background-color','grey');

    var linkId = element.data('linkId');
    var title = element.data('title');
    var hiperLink = element.data('hiperLink');
    var description = element.data('description');


    alert ('2222' + element.data('edit_status'));

    var position = element.position();

    var top = position.top + parseInt($('.dockingBarTop').css('height').replace('px',''));
    
    var left = element.closest('.middle-column-element').position().left;
    
    var width = parseInt(element.css('width').replace('px',''));

    alert ('top:' + top);
    alert ('left:' + left);
    alert ('width:' + width);

    $('.inline-row-element-edit').show();
    $('.inline-row-element-edit').css('top', top);
    $('.inline-row-element-edit').css('left', (left+width-200));

}



$('.middle-row-element').on('click', '#inline-save', function(ev){

    alert ('inline edit row save.');
    var element = $(this).parent('.middle-row-element');
    var title = $('inline-row-element').val();
    var hiperLink = $('inline-row-hiperLink').val();
    var description = $('inline-row-description').val();
    
    element.data('title', title);
    element.data('hiperLink', hiperLink);
    element.data('description', description);
    
    element.children('.middle-row-element-text').show();
    $('#inline-element-edit').hide();

    //    if (element.data('edit_status') == 'E') {
    element.data('edit_status', 'C');
//    }
});


function update_categories() {
    categories = new Array();
    //    alert ($('.middle-column-element-heading').text());
    $('.middle-column-element-heading').each(function(){
        var text = $.trim($(this).text());
        //        alert (text);
        categories.push(text);
    });
}


$(function() {
    update_categories();
    $("#group-name").autocomplete({
        //        source: jQuery.makeArray($('.middle-column-element-heading').text())
        source: categories
    })
});




$('#save-element.new-element-button').click(function(){
    alert ('111111');
    var group_name = $('input[name=group-name]').val();
    var element_name = $('input[name=element-name]').val();
    var url = $('#url').val();
    var description = $('#description').val();
    
    alert ('group:'+group_name);
    alert ('element'+element_name);
    //    var last_row_element = column_element.parent('.middle-column-element').find('li:last');
    //    var last_row_element_html = last_row_element.html().toString();
    //    alert ('row element:' + last_row_element_html);
    
    var html = '<li><div class ="middle-row-element" title="' + element_name + '">' +
    '<img class="favicon" src="images/favicon.ico"/>' +
    '<img class="ui-icon ui-icon-trash middle-row-element-delete-icon"></img>' +
    '<img class="ui-icon ui-icon-pencil middle-row-element-edit-icon"></img>' +
    '<div class="middle-row-element-text" >' + element_name + '</div></div></li>';
 
    //WORKING CODE
    //    aa = column_element.parent('.middle-column-element').text();
    //    alert ('AAAAAA::'+ aa);
    
    //    alert ('aaa:'+column_element.html());
    column_element.parent('.middle-column-element').find('li:last').after(html);
//    column_element.parent('.middle-column-element').find('li:last').after(last_row_element_html);
//    alert ('11111111:' + last_row_element_html);
    
//    column_element.parent('.middle-column-element').find('li:last .middle-row-element').attr('title', element_name);
//    column_element.parent('.middle-column-element').find('li:last .middle-row-element-text').text(element_name);
//    
//    alert ('CCCCC:' + column_element.parent('.middle-column-element').find('li:last').html());

//    bb = column_element.parent('.middle-column-element').text();
//    alert ('BBBBBB::'+ bb);
});


function inline_edit_column_element (element) {
    var text = $.trim(element.children('.middle-column-element-heading').text());
    alert (text);
    
    var position = element.position();

    var top = parseInt($('.dockingBarTop').css('height').replace('px',''));
    var left = position.left;
    var width = parseInt(element.css('width').replace('px',''));

    //    alert ('top:' + top);
    //    alert ('left:' + left);
    //    alert ('width:' + width);

    $('.inline-column-element-edit').show();
    $('.inline-column-element-edit').css('top', top);
    $('.inline-column-element-edit').css('left', left);        
    $('.inline-column-element-edit').css('width', width);
}

//$('.middle-column-element').on('blur', '.inline-column-edit-input', function(event) {
//    var new_text = $.trim($(this).val());
//    //    alert ('aaaaaaa'+new_text);
//    var element = $(this).parent('.middle-column-element');
//    element.children('.middle-column-element-heading').show();
//    //    alert ('bbbbbbb');
//    if (new_text == ''){
//        new_text = element.data('title');
//    } else {
//        element.data('title', new_text);        
//    }
//    //    alert ('bbbbbbb'+new_text);
//    element.children('.middle-column-element-heading').text(new_text);
//    $(this).hide();
//})
//.on('keypress', '.inline-column-edit-input', function(e){
//    if (e.keyCode == 13) { //enter key
//        var new_text = $.trim($(this).val());
//        var element = $(this).parent('.middle-column-element');
//        element.children('.middle-column-element-heading').show();
//        //    alert ('bbbbbbb');
//        if (new_text == ''){
//            new_text = element.data('title');
//        } else {
//            element.data('title', new_text);        
//        }
//        //    alert ('bbbbbbb'+new_text);
//        element.children('.middle-column-element-heading').text(new_text);
//        $(this).hide();
//    }
//})
//.on('keydown', '.inline-column-edit-input', function(e){
//    if (e.keyCode == 27) { //esc key
//        var new_text = $.trim($(this).val());
//        var element = $(this).parent('.middle-column-element');
//        element.children('.middle-column-element-heading').show();
//        //    alert ('bbbbbbb');
//        if (new_text == ''){
//            new_text = element.data('title');
//        } else {
//            element.data('title', new_text);        
//        }
//        //    alert ('bbbbbbb'+new_text);
//        element.children('.middle-column-element-heading').text(new_text);
//        $(this).hide();
//    }
//});




//$(document).ready(function() {   
//    alert ('aaa');
//    var xml = "<rss version='2.0'><channel><login>RSS Title</login></channel></rss>",
//    xmlDoc = $.parseXML (xml),
//    $xml = $(xmlDoc),
//    $login = $xml.find("login");
//    
//    alert ('cccccccc');
//    alert ('xxxx' + $login.text ());

//    if (window.XMLHttpRequest) {
//        // code for IE7+, Firefox, Chrome, Opera, Safari
//        xmlhttp=new XMLHttpRequest();
//    }
//
//    xmlhttp.open("GET","HomeStuff.xml",false);
//    xmlhttp.send();
//    xmlDoc=xmlhttp.responseXML;
//alert ('cccccccc');
//alert ('11:'+xmlDoc.getElementsByTagName("name")[0].childNodes[0].nodeValue);
//});



function parseXML(xml){
    //    alert(xml);

    //YAHOO<
    //    $(xml).find('category[category-id="1"]').children('element-details').each(function(){
    //        var abc = $(this);
    //        alert('element:' + abc.text());
    //    //    });
    //    $(xml).find('category:eq(1)').children('element-details:eq(1)').each(function(){
    //        var abc = $(this);
    //        alert('element:' + abc.text());
    ////        alert($(xml).find('category').index(abc.parent('category')));
    //    });
    //>YAHOO


    $(xml).find('category:eq(1)').children('element-details:eq(1)').each(function(){
        var abc = $(this);
        alert('element:' + abc.text());
    //        alert($(xml).find('category').index(abc.parent('category')));
    });

    $(xml).find('category:eq(1)').children('element-details:eq(1)').append('<element-details><link-id>11111</link-id><hyper-link>http://email.aaaaa.com</hyper-link> <display-name>Cisco Mail</display-name><make-visible>true</make-visible></element-details>')
    $(xml).find('category:eq(1)').children('element-details:eq(1)').append('<element-details><link-id>22222</link-id><hyper-link>http://email.aaaaa.com</hyper-link> <display-name>Cisco Mail</display-name><make-visible>true</make-visible></element-details>')
    $(xml).find('category:eq(1)').children('element-details:eq(1)').append('<element-details><link-id>33333</link-id><hyper-link>http://email.aaaaa.com</hyper-link> <display-name>Cisco Mail</display-name><make-visible>true</make-visible></element-details>')
    $(xml).find('category:eq(1)').children('element-details:eq(1)').append('<element-details><link-id>44444</link-id><hyper-link>http://email.aaaaa.com</hyper-link> <display-name>Cisco Mail</display-name><make-visible>true</make-visible></element-details>')
    $(xml).find('category:eq(1)').children('element-details:eq(1)').append('<element-details><link-id>55555</link-id><hyper-link>http://email.aaaaa.com</hyper-link> <display-name>Cisco Mail</display-name><make-visible>true</make-visible></element-details>')
    $(xml).find('category:eq(1)').children('element-details:eq(1)').append('<element-details><link-id>66666</link-id><hyper-link>http://email.aaaaa.com</hyper-link> <display-name>Cisco Mail</display-name><make-visible>true</make-visible></element-details>')
    alert ($(xml).find('category:eq(0)').text());
    alert ($(xml).find('category:eq(1)').text());
    alert ($(xml).find('category:eq(2)').text());
    alert ($(xml).find('category:eq(1)').children('element-details:eq(1)').next().text());

}

//only for testing, we dont need single click function

//$('.middle-column-element-heading').click(function(){
//    var element = $(this).parent('.middle-column-element');
//    alert (element.data('colId'));
//    element.data('colId','200');
//    alert (element.data('colId'));
//});



$('.middle-row-element-text').click(function(ev){
    var element = $(this).parent('.middle-row-element');
    var linkID = element.data('linkId');
    var link = element.data('hiperLink');
    alert (linkID);
    alert (link);
    window.open(link, '_self', 'fullscreen=yes, location=yes');

    //    $('.middle-column-element').each(function (){
    //        alert ($(this).data('title'));
    //    });
    ev.stopPropagation();
});


function forgot_password() {
    alert ('aaaa');
    alert (document.forms[3].name);
    document.forms[1].submit();
    return false;
}

function login_faq() {
    document.forms[2].submit();
    return false;
}

function create_account() {
    document.forms[3].submit();
    return false;
}

function login() {
    alert ('aaaa');
    document.forms[1].submit();
}

$(window).load(function() {
    $('.loading').remove();
});

