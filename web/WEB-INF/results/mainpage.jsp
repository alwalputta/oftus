<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

        <%@include file="/WEB-INF/results/imports.jsp"%>

        <script type="text/javascript">
            function loadData () {
            <s:iterator value="userCategories" id="category">
                    $('#C<s:property value="#category.categoryId"/>').data({colId:"<s:property value="#category.categoryId"/>", title:"<s:property value="#category.categoryName"/>", description:"<s:property value="#category.description:"/>"});
                <s:iterator value="#category.bookmarks" id="bookmark">
                        $('#R<s:property value="#bookmark.bookmarkId"/>').data({linkId:"<s:property value="#bookmark.bookmarkId"/>", title:"<s:property value="#bookmark.bookmarkName"/>", hiperLink:"<s:property value="#bookmark.hiperLink"/>", description:"<s:property value="#bookmark.description"/>"});
                </s:iterator>
            </s:iterator>
                }
        </script>
    </head>


    <body>

        <%@include file="/WEB-INF/results/header.jsp"%>

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


        <div class="mainTable">
            <div class="middle-column-element-import"></div>
            <ul id="middle-column-sortable">

                <s:iterator value="%{#session.user.userCategories}" id="category">
                    <li>
                        <div class="middle-column-element" id="<s:property value="#category.categoryId"/>">
                            <div class="middle-column-element-heading">
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
                            </div>
                            <ul id="middle-row-sortable" class="connectedSortable">
                                <s:iterator value="#category.bookmarks" id="bookmark">
                                    <li>
                                        <div class="middle-row-element" id="<s:property value="#bookmark.bookmarkId"/>">
                                            <img class="favicon" src="images/favicon.ico"/>
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

                                            </div>
                                        </div>
                                    </li>
                                </s:iterator>
                            </ul>

                            <div class="middle-row-new-element">
                                <span><a href="add_bookmark?categoryId=<s:property value="#category.categoryId"/>">
                                        + Add Bookmark +
                                    </a>
                                </span>
                            </div>
                            <div class="middle-row-bottom-spacer"></div>

                        </div>
                    </li>
                </s:iterator>

            </ul>
        </div>

        <%@include file="/WEB-INF/results/footer.jsp"%>

        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>

        