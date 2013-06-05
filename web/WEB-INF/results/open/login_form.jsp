<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <s:head/>

        <style type="text/css">
        </style>

        <%@include file="/WEB-INF/results/open/imports.jsp"%>

    </head>

    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
    %>

    <body>

        <!--h1><s:text name="welcome"/></h1>

        <p>
        <s:url id="localeEN" namespace="/" action="" >
            <s:param name="request_locale" >en</s:param>
        </s:url>
        <s:url id="localeDE" namespace="/" action="" >
            <s:param name="request_locale" >de</s:param>
        </s:url>
        <s:a href="%{localeEN}" >English</s:a>
        <s:a href="%{localeDE}" >German</s:a>
        </p-->

        <%@include file="/WEB-INF/results/open/header_basic.jsp"%>

        <div class="message">
            <s:property value="%{message}"/>
            Please enter username and password to access your world.
        </div>

        <div class="login-modal">

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

            <div class="mainTable">

                <div class="login-login">
                    <div class="page-title">Login</div>
                    <br></br>
                    <div class="login-form">
                        <s:form action="login">
                            <s:textfield name="username" size="30" maxlength="200" key="index.username-label" title="Please enter your username" cssClass="label"/>
                            <s:password name="password" size="30" maxlength="200" key="index.password-label" title="Please enter your password"/>
                            <s:checkbox name="rememberMe" key="index.rememberme-label" title="Click to be remembered on this machine"/>
                            <tr>
                                <td width="50%"></td>
                                <td width="50%" align="left">
                                    <s:submit align="center" key="index.submit-label" theme="simple" title="Click to Save"/>
                                    <s:reset align="center" key="index.reset-label" theme="simple" title="Click to Reset"/>
                                </td>
                            </tr>
                        </s:form>
                        <s:a href="forgot_password_form">Forgot Password?</s:a> |
                        <s:a href="login_faq">Login FAQ</s:a>
                            <br></br>
                        </div>
                    </div>
                    <div class="login-register">
                        <div class="login-box">
                            <br>
                                <!--
                                Don't have an account yet? <s:a href="register_form?debug=xml">Create one</s:a>
                                -->
                                <br></br><br></br>
                                Don't have an account yet?<br></br><s:a href="register_form">Create one</s:a>
                                <br></br><br></br><br></br><br></br>
                        </div>
                    </div>
                </div>
            </div>
        <%@include file="/WEB-INF/results/open/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>
    </body>
</html>
