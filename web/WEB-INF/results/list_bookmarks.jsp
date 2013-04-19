<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>

<%@include file="/WEB-INF/results/imports.jsp"%>

        <style type="text/css">
        </style>

        <script type="text/javascript">
        </script>
    </head>


    <body>
        <%@include file="/WEB-INF/results/header.jsp"%>

        <div class="register-modal">
            <s:if test="hasActionErrors()">
                <div id="fieldErrors">
                    <s:actionerror/>
                </div>
            </s:if>

            <s:if test="hasActionMessages()">
                <div class="welcome">
                    <s:actionmessage/>
                </div>
            </s:if>

            <div class="register-register">
                <div>User Bookmarks</div>
                <s:form action="update_profile">

                    <s:hidden name="userid" value="%{#session.user.userId}"/>
                    <!--
                    <s:iterator value="%{#session.user.userCategories}" id="category">
                        AAAAA<s:property value="#category.categoryName"/>
                        <s:iterator value="#category.bookmarks" id="bookmark">
                            BBBBB<s:property value="#bookmark.bookmarkName"/>
                        </s:iterator>
                    </s:iterator>
                    -->
                    <tr>
                        <td>Id</td>
                        <td>Category Name</td>
                        <td>Description</td>
                        <td>Category Order</td>
                        <td>Category Status</td>
                        <td>Edit</td>
                        <td>Delete</td>
                    </tr>
                    <s:iterator value="%{#session.user.userCategories}" id="category">
                        <tr>
                            <td><s:property value="#category.categoryId"/></td>
                            <td><s:property value="#category.categoryName"/></td>
                            <td><s:property value="#category.description"/></td>
                            <td><s:property value="#category.categoryOrder"/></td>
                            <td><s:property value="#category.status"/></td>
                            <td><s:url id="editCategory" action="edit_category">
                                    <s:param name="categoryId" value="#category.categoryId"></s:param>
                                </s:url>
                                <a href="<s:property value="#editCategory"/>">Edit</a>
                            </td>
                            <td><s:url id="deleteCategory" action="delete_category">
                                    <s:param name="categoryId" value="#category.categoryId"></s:param>
                                </s:url>
                                <a href="<s:property value="#deleteCategory"/>">Delete</a>
                            </td>
                        </tr>
                        <tr>
                            <table>
                                <s:iterator value="#category.bookmarks" id="bookmark">
                                    <tr>
                                        <td>
                                            <s:property value="#bookmark.bookmarkId"/>
                                        </td>
                                        <td>
                                            <s:property value="#bookmark.bookmarkName"/>
                                        </td>
                                        <td>
                                            <s:property value="#bookmark.description"/>
                                        </td>
                                        <td>
                                            <s:url id="editBookmark" action="edit_bookmark">
                                                <s:param name="bookmarkId" value="#bookmark.bookmarkId"></s:param>
                                            </s:url>
                                            <a href="<s:property value="#editBookmark"/>">Edit</a>
                                        </td>
                                        <td><s:url id="deleteBookmark" action="delete_bookmark">
                                                <s:param name="bookmarkId" value="#bookmark.bookmarkId"></s:param>
                                            </s:url>
                                            <a href="<s:property value="#deleteBookmark"/>">Delete</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </table>
                        </tr>
                    </s:iterator>

                </s:form>
            </div>
        </div>
<%@include file="/WEB-INF/results/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>

