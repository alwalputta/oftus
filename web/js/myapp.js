$(document).ready(function() {

    //Message while the page is still being loaded.
    //comented temporarily
    // show_page_loading_message();
    
    categories = [];
    column_element = null;
    max_row_element_id = 0;

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
    var element = $(this);
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

    //    alert ('top:' + top);
    //    alert ('left:' + left);
    //    alert ('width:' + width);
    
    element.children('.middle-row-element-edit-icons').show();
    element.children('.middle-row-element-edit-icons').css('top', top);
    element.children('.middle-row-element-edit-icons').css('left', (left+width-30));
});

$('.middle-row-element').dblclick(function(){
    targetUrl = 'edit_bookmark?bookmarkId='+$(this).attr('id');
    targetUrl = targetUrl + '&categoryId='+$(this).parents('.middle-column-element').attr('id');
    window.location=targetUrl;
});

$('.middle-column-element').dblclick(function(){
    targetUrl = 'edit_category?categoryId='+$(this).attr('id');
    window.location=targetUrl;
});

$('.middle-row-element-text').mouseout(function(){
    var element = $(this).parent('.middle-row-element');
    element.children('.middle-row-element-edit-icons').hide();
});

$('.favicon').click(function(){
    alert ('Your favorite icon.');
});

function set_sortable(){
    //Draggability of the columns
    // $("#middle-column-sortable").draggable({containment: '.mainTable'});
    $("#middle-column-sortable").sortable({
        start: function(event, ui){
            alert ('start1');
        },
        update: function(event, ui){
            alert ('update1');
        },
        stop: function(event, ui){
            alert ('stop1');
        },
        receive: function(event, ui) {
            //Run this code whenever an item is dragged and dropped into this list
            alert("receive just joined this list1");
        },
        remove: function(event, ui){
            //Run this code whenever an item is dragged and dropped out of this list
            alert("remove just left this list1");	
        }
    }).disableSelection();

    //draggability of elements across the columns
    $("#middle-row-sortable, #middle-row-sortable").sortable({
        connectWith: ".connectedSortable",
        start: function(event, ui){
            alert ('start');
        },
        update: function(event, ui){
            alert ('update');
        },
        stop: function(event, ui){
            alert ('stop');
        },
        receive: function(event, ui) {
            //Run this code whenever an item is dragged and dropped into this list
            alert("receive just joined this list");
        },
        remove: function(event, ui){
            //Run this code whenever an item is dragged and dropped out of this list
            alert("remove just left this list");	
        }
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
    $('.loading').css("display", "block");
    $('.loading').css("visibility", "visible");
    $('.loading').css("top", $(window).height()/2-$('.loading').height()/2-100);
    $('.loading').css("left", $(window).width()/2-$('.loading').width()/2);

    $('.loading input:first').focus();
}

$(window).load(function() {
    $('.loading').remove();
});

