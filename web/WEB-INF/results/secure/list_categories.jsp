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
                <div class="page-title">User Categories</div>
                <div class="login-form">
                    <a href="add_category?">Add Category</a>
                    <table summary="List of all categories">
                        <thead>
                            <tr class="row">
                                <!--td>Id</td-->
                                <th>Category Name</th>
                                <th>Description</th>
                                <th>Category Order</th>
                                <th>Category Status</th>
                                <th>Edit</th>
                                <th>Delete</th>
                            </tr>
                        </thead>

                        <tbody>
                            <s:iterator value="%{#session.user.userCategories}" id="category">
                                <tr class="rowc">
                                    <!--td><s:property value="#category.categoryId"/></td-->
                                    <td><s:property value="#category.categoryName"/></td>
                                    <td><s:property value="#category.description"/></td>
                                    <td><s:property value="#category.categoryOrder"/></td>
                                    <td><s:property value="#category.status"/></td>
                                    <td><s:url id="editCategory" action="edit_category">
                                            <s:param name="categoryId" value="#category.categoryId"></s:param>
                                        </s:url>
                                        <a href="<s:property value="#editCategory"/>">
                                            <img class="ui-icon ui-icon-pencil middle-column-element-edit-icon"/>
                                        </a>
                                    </td>
                                    <td><s:url id="deleteCategory" action="delete_category">
                                            <s:param name="categoryId" value="#category.categoryId"></s:param>
                                        </s:url>
                                        <a href="<s:property value="#deleteCategory"/>">
                                            <img class="ui-icon ui-icon-trash middle-column-element-delete-icon"/>
                                        </a>
                                    </td>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="login-register">
                <div class="page-title">Your Categories</div>
                <div class="login-box">
                    Note: This lists your categories.
                    If you want to see the bookmarks, click <a href="list_bookmarks">Manage Bookmarks</a> link.
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
        </div>
        <%@include file="/WEB-INF/results/open/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>
