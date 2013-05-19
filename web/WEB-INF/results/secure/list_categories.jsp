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

            <div class="login-login">
                User Categories::::: <a href="add_category?">Add Category</a>
                <table>
                    <tr class="row">
                        <!--td>Id</td-->
                        <td>Category Name</td>
                        <td>Description</td>
                        <td>Category Order</td>
                        <td>Category Status</td>
                        <td>Edit</td>
                        <td>Delete</td>
                    </tr>
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
                                <a href="<s:property value="#editCategory"/>">Edit</a>
                            </td>
                            <td><s:url id="deleteCategory" action="delete_category">
                                    <s:param name="categoryId" value="#category.categoryId"></s:param>
                                </s:url>
                                <a href="<s:property value="#deleteCategory"/>">Delete</a>
                            </td>
                        </tr>
                    </s:iterator>
                </table>
            </div>
            <div class="login-register">
                Note: This lists your categories.
                If you want to see the bookmarks, click <a href="list_bookmarks">Manage Bookmarks</a> link.
            </div>
        </div>
        <%@include file="/WEB-INF/results/open/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>

