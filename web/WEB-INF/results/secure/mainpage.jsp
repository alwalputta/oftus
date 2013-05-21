<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>
        <%@include file="/WEB-INF/results/open/imports.jsp"%>
        <script type="text/javascript">
            function loadData () {
            <s:iterator value="userCategories" id="category">
                    $('#<s:property value="#category.categoryId"/>').data("category", {colId:"<s:property value="#category.categoryId"/>", title:"<s:property value="#category.categoryName"/>", description:"<s:property value="#category.description"/>"});
                <s:iterator value="#category.bookmarks" id="bookmark">
                        $('#<s:property value="#bookmark.bookmarkId"/>').data("bookmark", {linkId:"<s:property value="#bookmark.bookmarkId"/>", title:"<s:property value="#bookmark.bookmarkName"/>", hiperLink:"<s:property value="#bookmark.hiperLink"/>", description:"<s:property value="#bookmark.description"/>"});
                </s:iterator>
            </s:iterator>
                }
        </script>
    </head>

    <body>
        <%@include file="/WEB-INF/results/open/header.jsp"%>
        <div class="mask"></div>
        <div class="message">
            <s:property value="%{message}"/>
        </div>
        <div class="loading">
            Page being loaded, please wait ...
        </div>

        <div class="mainTable">
            <div class="middle-column-element-import"></div>
            <ul id="middle-column-sortable">
                <s:iterator value="%{#session.user.userCategories}" id="category">
                    <li>
                        <div class="middle-column-element" id="<s:property value="#category.categoryId"/>" title="<s:property value="#category.description"/>">
                            <div class="middle-column-element-text">
                                <s:property value="#category.categoryName"/>
                            </div>
                            <div class="middle-column-element-edit-icons">
                                <s:url id="editCategory" action="edit_category">
                                    <s:param name="categoryId" value="#category.categoryId"></s:param>
                                </s:url>
                                <a href="<s:property value="#editCategory"/>">
                                    <img class="ui-icon ui-icon-pencil middle-column-element-edit-icon"/>
                                </a>
                                <s:url id="deleteCategory" action="delete_category_mainpage">
                                    <s:param name="categoryId" value="#category.categoryId"></s:param>
                                </s:url>
                                <a href="<s:property value="#deleteCategory"/>">
                                    <img class="ui-icon ui-icon-trash middle-column-element-delete-icon"/>
                                </a>
                                <img class="ui-icon ui-icon-plusthick middle-column-element-new-icon"/>
                            </div>
                            <ul id="middle-row-sortable" class="connectedSortable">
                                <s:iterator value="#category.bookmarks" id="bookmark">
                                    <li>
                                        <div class="middle-row-element" id="<s:property value="#bookmark.bookmarkId"/>" title="<s:property value="#bookmark.hiperLink"/><br><s:property value="#bookmark.description"/>">
                                            <img class="favicon" src="https://plus.google.com/_/favicon?domain=<s:property value="#bookmark.hiperLink"/>"></img>
                                            <div class="middle-row-element-text">
                                                <s:property value="#bookmark.bookmarkName"/>
                                            </div>
                                            <div class="middle-row-element-edit-icons">
                                                <s:url id="editBookmark" action="edit_bookmark" escapeAmp="false">
                                                    <s:param name="categoryId" value="#category.categoryId"></s:param>
                                                    <s:param name="bookmarkId" value="#bookmark.bookmarkId"></s:param>
                                                </s:url>
                                                <a href="<s:property value="#editBookmark"/>">
                                                    <img class="ui-icon ui-icon-pencil middle-row-element-edit-icon"/>
                                                </a>

                                                <s:url id="deleteBookmark" action="delete_bookmark_mainpage">
                                                    <s:param name="bookmarkId" value="#bookmark.bookmarkId"></s:param>
                                                </s:url>
                                                <a href="<s:property value="#deleteBookmark"/>">
                                                    <img class="ui-icon ui-icon-trash middle-row-element-delete-icon"/>
                                                </a>
                                                <img class="ui-icon ui-icon-scissors middle-row-element-bulk-delete-icon"/>
                                                <a href="add_bookmark?categoryId=<s:property value="#category.categoryId"/>">
                                                    <img class="ui-icon ui-icon-plusthick middle-row-element-new-icon"/>
                                                </a>
                                            </div>
                                        </div>
                                    </li>
                                </s:iterator>
                            </ul>

                            <div class="middle-row-new-element">
                                <a href="add_bookmark?categoryId=<s:property value="#category.categoryId"/>">
                                    <img class="ui-icon ui-icon-plusthick middle-row-new-element-icon-left"/>
                                    <img class="ui-icon ui-icon-plusthick middle-row-new-element-icon-left"/>
                                    + Add Bookmark +
                                    <img class="ui-icon ui-icon-plusthick middle-row-new-element-icon-right"/>
                                    <img class="ui-icon ui-icon-plusthick middle-row-new-element-icon-right"/>
                                </a>
                            </div>
                            <div class="middle-row-bottom-spacer"></div>
                        </div>
                    </li>
                </s:iterator>
            </ul>
        </div>
        <%@include file="/WEB-INF/results/open/footer.jsp"%>
        <script type="text/javascript" src="js/jquery-highlight-4.js"></script>
        <script type="text/javascript" src="js/myapp.js"></script>
    </body>
</html>
