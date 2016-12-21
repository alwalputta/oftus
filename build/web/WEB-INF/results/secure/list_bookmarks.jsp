<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>
        <%@include file="/WEB-INF/results/open/imports.jsp"%>

        <style type="text/css">
        </style>

        <script type="text/javascript">
        </script>
    </head>


    <body>
        <%@include file="/WEB-INF/results/open/header.jsp"%>

        <div class="message">
            Listed below are all your bookmarks organized by category.
            You can add, modify and delete them by clicking appropriate links.
            <s:property value="%{message}"/>
        </div>
        <div class="mainTable">
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

            <s:fielderror/>

            <div class="login-login">
                <div class="page-title">User Bookmarks</div>
                <div class="login-form">
                    <a href="add_category_list?source=list">Add Category</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="add_bookmark_list?source=list">Add Bookmark</a>
                    <table summary="List of all bookmarks">
                        <thead>
                            <tr class="row">
                                <!--th class="name">Id</th-->
                                <th class="name">Category Name</th>
                                <th class="name">Description</th>
                                <th class="name">Category Status</th>
                                <th class="name">Edit</th>
                                <th class="name">Delete</th>
                            </tr>
                        </thead>

                        <tbody>
                            <s:iterator value="%{#session.user.userCategories}" id="category">
                                <tr class="rowc">
                                    <!--th><s:property value="#category.categoryId"/></th-->
                                    <th><s:property value="#category.categoryName"/></th>
                                    <th><s:property value="#category.description"/></th>
                                    <th><s:property value="#category.status"/></th>
                                    <th>
                                        <s:url id="editCategory" action="edit_category_list?source=list" escapeAmp="false">
                                            <s:param name="categoryId" value="#category.categoryId"></s:param>
                                        </s:url>
                                        <a href="<s:property value="#editCategory"/>">
                                            <img class="ui-icon ui-icon-pencil middle-column-element-edit-icon"/>
                                        </a>
                                    </th>
                                    <th>
                                        <s:url id="deleteCategory" action="delete_category_list?source=list" escapeAmp="false">
                                            <s:param name="categoryId" value="#category.categoryId"></s:param>
                                        </s:url>
                                        <a href="<s:property value="#deleteCategory"/>">
                                            <img class="ui-icon ui-icon-trash middle-column-element-delete-icon"/>
                                        </a>
                                    </th>
                                </tr>
                                <s:iterator value="#category.bookmarks" id="bookmark">
                                    <tr class="rowb">
                                        <!--th class="start"><s:property value="#bookmark.bookmarkId"/></th-->
                                        <td><s:property value="#bookmark.bookmarkName"/></td>
                                        <td><s:property value="#bookmark.description"/></td>
                                        <td><s:property value="#bookmark.status"/></td>
                                        <td>
                                            <s:url id="editBookmark" action="edit_bookmark_list?source=list" escapeAmp="false">
                                                <s:param name="categoryId" value="#category.categoryId"></s:param>
                                                <s:param name="bookmarkId" value="#bookmark.bookmarkId"></s:param>
                                            </s:url>
                                            <a href="<s:property value="#editBookmark"/>">
                                                <img class="ui-icon ui-icon-pencil middle-row-element-edit-icon"/>
                                            </a>
                                        </td>
                                        <td>
                                            <s:url id="deleteBookmark" action="delete_bookmark_list?source=list" escapeAmp="false">
                                                <s:param name="bookmarkId" value="#bookmark.bookmarkId"></s:param>
                                            </s:url>
                                            <a href="<s:property value="#deleteBookmark"/>">
                                                <img class="ui-icon ui-icon-trash middle-row-element-delete-icon"/>
                                            </a>
                                        </td>
                                    </tr>
                                </s:iterator>
                            </tbody>
                        </s:iterator>
                    </table>
                </div>
            </div>
            <div class="login-register">
                <div class="page-title">Your Bookmarks</div>
                <div class="login-box">
                    Note: This lists all your Bookmarks.
                    If you want to see the categories, click <a href="list_categories">Manage Categories</a> link.
                </div>
            </div>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
            <br></br>
        </div>
        <%@include file="/WEB-INF/results/open/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>
    </body>
</html>
