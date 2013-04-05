<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

        <title>Oftus&reg; - Development</title>

        <link rel="shortcut icon" type="image/x-icon" class="header-icon" href="images/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="css/myapp.css"/>
        <link rel="stylesheet" type="text/css" media="all" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/sunny/jquery-ui.css"/>

        <script type="text/javascript" src="js/jquery.highlight-3.js"></script>
        <script type="text/javascript" src="js/constants.js"></script>

        <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <script type="text/javascript">
            
            /*
             *$('#c2').data ({colId:"c2", position:2, title: "Column 2"});
              $('#c2r1').data ({linkId:"c2r1", position:1, hiperLink:"www.yahoo.com", title: "Yahoo!", comments:"my favotite for news"});
             */
           
            function loadData () {
                //alert ('aaaaa');
                //columns
                $('#c1').data({colId:"1", title:"Builder"});

            <s:iterator value="userCategories" id="category">
                    $('#<s:property value="#category.categoryId"/>').data({colId:"<s:property value="#category.categoryId"/>", title:"<s:property value="#category.categoryName"/>"});
                <s:iterator value="#category.bookmarks" id="bookmark">
                        $('#<s:property value="#bookmark.bookmarkId"/>').data({linkId:"<s:property value="#bookmark.bookmarkId"/>", title:"<s:property value="#bookmark.bookmarkName"/>", hiperLink:"<s:property value="#bookmark.hiperLink"/>", description:"<s:property value="#bookmark.description"/>"});
                </s:iterator>
            </s:iterator>

                    //$('#c2r1').data({linkId:"c2r1", hiperLink:"http://www.yahoo.com", title: "Yahoo!", comments:"my favotite for news"});
                    //rows
                    $('#c1r1').data({linkId:"c1r1", title:"Braddock And Logan Homes", hiperLink:"http://www.braddockandloganhomes.com/", description:"test link"});
                }
        </script>
    </head>


    <body>

        <div class="dockingBarTop">
            <div class="top-column-element-left">
                <div class="company-logo">
                    <img id="logo" src="images/bukmarcs.png"/>
                    OFTUS<sup>&reg;</sup>
                </div>
                <div class="company-logo-punchline">
                    Entire World. One Link.
                </div>
            </div>
            <div class="top-column-element-middle">
                Buy Samsung TV Today!
            </div>

            <div class="top-column-element-right">

                <img class ="photo-box" src="images/user.jpg" alt="Welcome, Alwal Putta"/>
                <ul id="menu">
                    <li>
                        <s:if test="%{#session.user.firstName} == null">
                            <a href="login">Login</a>
                        </s:if>
                        <s:else>
                            <a href="login">
                                Hi, <s:property value="%{#session.user.firstName}"/>
                            </a>
                        </s:else>
                        <ul>
                            <li><a href="welcome">Welcome!</a></li>
                            <li><a href="edit_profile">Manage Profile</a></li>
                            <li><a href="edit_preferences">Manage Preferences</a></li>
                            <li><a href="refer_friends">Refer Friends</a></li>
                        </ul>
                    </li>
                    <li><a href="options">Options</a>
                        <ul>
                            <li><a href="list_categories">Manage Categories</a></li>
                            <li><a href="add_bookmark">Manage Bookmarks</a></li>
                            <li><a href="import_bookmarks">Import Bookmarks</a></li>
                            <li><a href="industry_trends">Industry Trends</a></li>
                            <li><a href="personal_reports">Personal Reports</a></li>
                            <li><a href="our_recommendations">Our Recommendations</a></li>
                        </ul>
                    </li>
                    <li><a href="help">Help</a> 
                        <ul>
                            <li><a href="release_notes">Release Notes</a></li>
                            <li><a href="quick_guide">Quick Guide</a></li>
                            <li><a href="user_guide">User Guide</a></li>
                        </ul>
                    </li>
                    <li><a href="logout">Logout</a>
                        <ul>
                            <li>
                                <a href="logout">LogouT</a>
                            </li>
                        </ul>
                    </li>
                </ul>
                <br></br>
                <form id="searchForm">
                    <fieldset title="Search Form" id="search-form-fieldset">
                        <div class="search-box">
                            <input type="text" id="search-input" name="search-input"/>
                            <input type="button" id="search-button" name="search-button"/>
                        </div>
                    </fieldset>
                </form>
            </div>
        </div>


        <div class="mask"></div>

        <div class="message"></div>

        <div class="loading">Page being loaded, please wait ...</div>

        <div class="middle-column-element-edit-icons">
            <img class="ui-icon ui-icon-pencil middle-column-element-edit-icon"/>
            <img class="ui-icon ui-icon-trash middle-column-element-delete-icon"/>
        </div>

        <div class="middle-row-element-edit-icons">
            <img class="ui-icon ui-icon-pencil middle-row-element-edit-icon"/>
            <img class="ui-icon ui-icon-trash middle-row-element-delete-icon"/>
        </div>

        <div class="inline-column-element-edit">
            <input type="text" maxlength="100" class="inline-column-edit-input"/>
            <input type="button" value="Save"/>
            <input type="reset" value="Reset"/>
            <input type="button" value="Cancel"/>
        </div>

        <div class="inline-row-element-edit">
            edit row.
            <input type="input" class="inline-row-edit-input" id="inline-row-title" value=""/>
            <input type="input" class="inline-row-edit-input" id="inline-row-hiperLink" value=""/>
            <textarea cols="3" class="inline-row-edit-text" id="inline-row-description"></textarea>
            <input type="button" id="inline-save" value="Save"></input>
            <input type="reset" id="inline-save" value="Reset"></input>
            <input type="button" id="inline-save" value="Close"></input>
        </div>


        <div class="edit-element-modal">
            <div class="edit-element">
                <div>Edit Bookmark:</div>
                <form title="Midify Element" id="edit-element_form">
                    <fieldset>
                        <legend>Edit Element</legend>
                        <input type="text" name="element-name" id="element-name" size="60" maxlength="200"/>
                        <br></br>
                        <input type="text" name="url" id="url" size="60" maxlength="200"/>
                        <br></br>
                        <textarea cols="60" rows="4" id="description" name="description"></textarea>
                        <br></br>
                        <input type="submit" name="save" id="save-element" value="Save" class="edit-element-button"/>
                        <input type="reset" name="cancel" value="Cancel" class="edit-element-button"/>
                    </fieldset>
                </form>
            </div>
        </div>


        <div class="new-element-modal">
            <div class="new-element">
                <div>Add New Bookmark</div>

                <s:form action="addBookmark">
                    <s:select name="categoryId" list="%{#session.user.userCategories}" headerKey="-1" headerValue="Select Category..." key="new.categoryname-label" listKey="categoryId" listValue="categoryName"/>
                    <s:textfield name="bookmarkname" size="60" maxlength="200" key="new.bookmark-label"/>
                    <s:textfield name="hiperLink" size="60" maxlength="200" key="new.hiperlink-label"/>
                    <s:textarea name="description" rows="5" cols="60" key="new.bookmark-description-label"/>
                    <tr>
                        <td colspan="2" align="center">
                            <s:submit align="center" key="new.submit-label" theme="simple"/>
                            <s:reset align="center" key="new.reset-label" theme="simple"/>
                        </td>
                    </tr>
                </s:form>
                <a href="#new_bookmark_faq" class="new-element-links">Add/Edit Bookmark FAQ</a>
            </div>
        </div>


        <div class="mainTable">
            <div class="middle-column-element-import"></div>
            <ul id="middle-column-sortable">

                <s:iterator value="%{#session.user.userCategories}" id="category">
                    <li>
                        <div class="middle-column-element" id="<s:property value="#category.categoryId"/>">
                            <div class="middle-column-element-heading">
                                <s:property value="#category.categoryName"/>
                            </div>
                            <ul id="middle-row-sortable" class="connectedSortable">
                                <s:iterator value="#category.bookmarks" id="bookmark">
                                    <li>
                                        <div class ="middle-row-element" id="<s:property value="#bookmark.bookmarkId"/>">
                                            <img class="favicon" src="images/favicon.ico"/>
                                            <div class="middle-row-element-text">
                                                <s:property value="#bookmark.bookmarkName"/>
                                            </div> 
                                        </div>
                                    </li>
                                </s:iterator>
                            </ul>

                            <div class="middle-row-new-element">
                                <span> <a href="#">
                                        + Add bookmark +
                                    </a>
                                </span>
                            </div>
                            <div class="middle-row-bottom-spacer"></div>

                        </div>
                    </li>
                </s:iterator>

            </ul>
        </div>

        <div class="dockingBarBottom">
            <!--ul class="bottom-row-item"-->
            <div class="bottom-row-item" onclick="load_bottom_bar('1')">
                About Links Oasis &reg;
            </div>
            <div class="bottom-row-item" onclick="load_bottom_bar('2')">EULA</div>
            <div class="bottom-row-item" onclick="load_bottom_bar('3')">User Guide/Help</div>
            <div class="bottom-row-item" onclick="load_bottom_bar('4')"><a href="#">Feedback</a></div>
            <div class="bottom-row-item" onclick="load_bottom_bar('5')"><a href="#">Refer Friends</a></div>
        </div>

        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>




