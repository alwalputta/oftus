<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>

        <title>Development</title>

        <link rel="shortcut icon" type="image/x-icon" class="header-icon" href="images/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="css/myapp.css"/>
        <link rel="stylesheet" type="text/css" media="all" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/sunny/jquery-ui.css"/>

        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.highlight-3.js"></script>
        <script type="text/javascript" src="js/myapp.js"></script>


        <!--script src="http://code.jquery.com/jquery-1.8.3.js"></script-->
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

        <!--
        This combination works for date picker
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.2/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="http://code.jquery.com/ui/1.10.2/jquery-ui.js"></script>
        -->

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <style type="text/css">
        </style>

        <script type="text/javascript">
        </script>
    </head>


    <body>

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
                <div>User Categories</div>
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
                    </s:iterator>

                </s:form>
            </div>
        </div>

        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>

