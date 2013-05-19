<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

        <%@include file="/WEB-INF/results/open/imports.jsp"%>

        <script type="text/javascript">
        </script>
    </head>


    <body>

        <%@include file="/WEB-INF/results/open/header.jsp"%>

        <div class="message">
            <s:property value="#message"/>
        </div>
        <div class="mainTable">
            <div class="login-login">
                <div>Modify Category</div>

                <s:form action="update_category">
                    <s:hidden name="categoryId" value="%{#session.category.categoryId}"/>
                    <s:textfield name="categoryName" value="%{#session.category.categoryName}" size="60" maxlength="200" key="new.categoryname-label"/>
                    <s:textarea name="description" value="%{#session.category.description}" rows="5" cols="60" key="new.category-description-label"/>
                    <tr>
                        <td colspan="2" align="center">
                            <s:submit align="center" key="new.submit-category-label" theme="simple" cssClass="form-button"/>
                            <s:reset align="center" key="new.reset-label" theme="simple" cssClass="form-button"/>
                            <s:url id="deleteCategory" action="delete_category_mainpage">
                                <s:param name="categoryId" value="%{#session.category.categoryId}"></s:param>
                            </s:url>
                            <a href="<s:property value="#deleteCategory"/>" class="form-button">Delete Category</a>
                        </td>
                    </tr>
                </s:form>
                <s:a href="#new_category_faq" class="new-element-links">Add/Edit Category FAQ</s:a>
                </div>
            </div>
        <%@include file="/WEB-INF/results/open/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>

