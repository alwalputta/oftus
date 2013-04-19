<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

<%@include file="/WEB-INF/results/imports.jsp"%>

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
        <%@include file="/WEB-INF/results/header.jsp"%>

        <div class="new-element-modal">
            <div class="new-element">
                <div>Add New Bookmark</div>

                <s:form action="save_bookmark">
                    <s:select name="categoryId" list="%{#session.user.userCategories}" listKey="categoryId" listValue="categoryName" headerKey="-1" headerValue="Select Category..." key="new.categoryname-label"/>
                    <s:textfield name="bookmarkName" size="60" maxlength="200" key="new.bookmark-label"/>
                    <s:textfield name="hiperLink" size="60" maxlength="200" key="new.hiperlink-label"/>
                    <s:textarea name="description" rows="5" cols="60" key="new.bookmark-description-label"/>
                    <tr>
                        <td colspan="2" align="center">
                            <s:submit align="center" key="new.submit-bookmark-label" theme="simple"/>
                            <s:reset align="center" key="new.reset-label" theme="simple"/>
                        </td>
                    </tr>
                </s:form>
                <a href="new_bookmark_faq" class="new-element-links">Add Bookmark FAQ</a>
            </div>
        </div>
<%@include file="/WEB-INF/results/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>




