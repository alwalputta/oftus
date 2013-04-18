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
           
            function loadData () {
                $('#c1').data({colId:"1", title:"Builder"});

            <s:iterator value="userCategories" id="category">
                    $('#<s:property value="#category.categoryId"/>').data({colId:"<s:property value="#category.categoryId"/>", title:"<s:property value="#category.categoryName"/>"});
                <s:iterator value="#category.bookmarks" id="bookmark">
                        $('#<s:property value="#bookmark.bookmarkId"/>').data({linkId:"<s:property value="#bookmark.bookmarkId"/>", title:"<s:property value="#bookmark.bookmarkName"/>", hiperLink:"<s:property value="#bookmark.hiperLink"/>", description:"<s:property value="#bookmark.description"/>"});
                </s:iterator>
            </s:iterator>

                    $('#c1r1').data({linkId:"c1r1", title:"Braddock And Logan Homes", hiperLink:"http://www.braddockandloganhomes.com/", description:"test link"});
                }
        </script>
    </head>

    <body>

        <div class="new-element-modal">
            <div class="new-element">
                <div>Add New Bookmark</div>
                <s:form action="update_bookmark">
                    <s:hidden name="bookmarkId" value="%{#session.bookmark.bookmarkId}"/>
                    <s:select name="categoryId" list="%{#session.user.userCategories}" listKey="categoryId" listValue="categoryName" value="%{categoryId}" headerKey="-1" headerValue="Select Category..." key="new.categoryname-label"/>
                    <s:textfield name="bookmarkName" value="%{#session.bookmark.bookmarkName}" size="60" maxlength="200" key="new.bookmark-label"/>
                    <s:textfield name="hiperLink" value="%{#session.bookmark.description}" size="60" maxlength="200" key="new.hiperlink-label"/>
                    <s:textarea name="description" rows="5" cols="60" key="new.bookmark-description-label"/>
                    <tr>
                        <td colspan="2" align="center">
                            <s:submit align="center" key="new.submit-bookmark-label" theme="simple"/>
                            <s:reset align="center" key="new.reset-label" theme="simple"/>
                        </td>
                    </tr>
                </s:form>
                <s:a href="new_bookmark_faq" class="new-element-links">Edit Bookmark FAQ</s:a>
            </div>
        </div>

        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>




