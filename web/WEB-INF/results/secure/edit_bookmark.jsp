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
                <div class="page-title">Modify Bookmark</div>
                <div class="login-form">

                    <s:form action="update_bookmark">
                        <s:hidden name="bookmarkId" value="%{#session.bookmark.bookmarkId}"/>
                        <s:select name="categoryId" list="%{#session.user.userCategories}" listKey="categoryId" listValue="categoryName" value="%{categoryId}" headerKey="-1" headerValue="Select Category..." key="new.categoryname-label"/>
                        <s:textfield name="bookmarkName" value="%{#session.bookmark.bookmarkName}" size="60" maxlength="200" key="new.bookmark-label"/>
                        <s:textfield name="hiperLink" value="%{#session.bookmark.hiperLink}" size="60" maxlength="200" key="new.hiperlink-label"/>
                        <s:textarea name="description" value="%{#session.bookmark.description}" rows="5" cols="60" key="new.bookmark-description-label"/>
                        <tr>
                            <td></td>
                            <td colspan="2" align="center">
                                <s:submit align="center" key="new.submit-bookmark-label" theme="simple"/>
                                <s:reset align="center" key="new.reset-label" theme="simple"/>
                                <s:url id="deleteBookmark" action="delete_bookmark_mainpage">
                                    <s:param name="bookmarkId" value="%{#session.bookmark.bookmarkId}"></s:param>
                                </s:url>
                                <a href="<s:property value="#deleteBookmark"/>" class="form-button">Delete Bookmark</a>
                            </td>
                        </tr>
                    </s:form>
                    <s:a href="new_bookmark_faq" class="new-element-links">Edit Bookmark FAQ</s:a>
                </div>
            </div>
            <div class="login-register">
                <div class="page-title">Upload Picture</div>
                <div class="login-box">
                    <br>
                        <!--
                        Don't have an account yet? <s:a href="register_form?debug=xml">Create one</s:a>
                        -->
                        Don't have an account yet? <s:a href="register_form">Create one</s:a>
                        <br></br><br></br><br></br><br></br><br></br><br></br><br></br>
                </div>
            </div>
        </div>
        <%@include file="/WEB-INF/results/open/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>
    </body>
</html>

