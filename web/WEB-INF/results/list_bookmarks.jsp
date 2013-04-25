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
                <div>User Bookmarks
                    <a href="add_category?">Add Category</a>
                    <a href="add_bookmark?">Add Bookmark</a>
                </div>
                <table summary="List of all bookmarks">
                    <thead>
                        <tr>
                            <th class="name">Id</td>
                                <th class="name">Category Name</th>
                                <th class="name">Description</th>
                                <th class="name">Category Status</th>
                                <th class="name">Edit</th>
                                <th class="name">Delete</th>
                        </tr>
                    </thead>

                    <tbody>
                        <s:iterator value="%{#session.user.userCategories}" id="category">
                            <tr>
                                <th><s:property value="#category.categoryId"/></th>
                                <th><s:property value="#category.categoryName"/></th>
                                <th><s:property value="#category.description"/></th>
                                <th><s:property value="#category.status"/></th>
                                <th><s:url id="editCategory" action="edit_category_list">
                                        <s:param name="categoryId" value="#category.categoryId"></s:param>
                                    </s:url>
                                    <a href="<s:property value="#editCategory"/>">Edit</a>
                                </th>
                                <th><s:url id="deleteCategory" action="delete_category_list">
                                        <s:param name="categoryId" value="#category.categoryId"></s:param>
                                    </s:url>
                                    <a href="<s:property value="#deleteCategory"/>">Delete</a>
                                </th>
                            </tr>
                            <tr colspan="5" width="100%">
                                <s:iterator value="#category.bookmarks" id="bookmark">
                                    <tr>
                                        <th class="start"><s:property value="#bookmark.bookmarkId"/></th>
                                        <td><s:property value="#bookmark.bookmarkName"/></td>
                                        <td><s:property value="#bookmark.description"/></td>
                                        <td><s:property value="#bookmark.status"/></td>
                                        <td>
                                            <s:url id="editBookmark" action="edit_bookmark_list" escapeAmp="false">
                                                <s:param name="categoryId" value="#category.categoryId"></s:param>
                                                <s:param name="bookmarkId" value="#bookmark.bookmarkId"></s:param>
                                            </s:url>
                                            <a href="<s:property value="#editBookmark"/>">Edit</a>
                                        </td>
                                        <td>
                                            <s:url id="deleteBookmark" action="delete_bookmark_list">
                                                <s:param name="bookmarkId" value="#bookmark.bookmarkId"></s:param>
                                            </s:url>
                                            <a href="<s:property value="#deleteBookmark"/>">Delete</a>
                                        </td>
                                    </tr>
                                </s:iterator>
                        </tbody>
                    </s:iterator>
                    <tr><td><hr size="100%"></td></tr>
                </table>
            </div>
        </div>

        <%@include file="/WEB-INF/results/footer.jsp"%>

        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>
