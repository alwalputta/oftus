<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <title>Welcome to HomeStuff!</title>
        <s:head/>

        <style type="text/css">
            .errors {
                background-color:#FFCCCC;
                border:1px solid #CC0000;
                width:400px;
                margin-bottom:8px;
            }
            .errors li{ 
                list-style: none; 
            }
        </style>

        <link rel="shortcut icon" type="image/x-icon" class="header-icon" href="images/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="css/myapp.css"/>
        <link rel="stylesheet" type="text/css" media="all" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/sunny/jquery-ui.css"/>

        <script type="text/javascript" src="js/jquery-ui-1.8.18.custom.min.js"></script>
        <script type="text/javascript" src="js/jquery.highlight-3.js"></script>
        <script type="text/javascript" src="js/constants.js"></script>

        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

    </head>

    <%
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        session.setAttribute("user", "palwal");
        session.setAttribute("password", "password");
    %>

    <body>

        <!--h1><s:text name="welcome" /></h1>

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

            <div class="login-login">
                <div>Login:</div>

                <s:form action="login">
                    <s:textfield name="username" size="25" maxlength="200" key="index.username-label"/>
                    <s:password name="password" size="25" maxlength="200" key="index.password-label"/>
                    <s:checkbox name="rememberme" key="index.rememberme-label"/>

                    <tr>
                        <td colspan="2" align="center">
                            <s:submit align="center" key="index.submit-label" theme="simple"/>
                            <s:reset align="center" key="index.reset-label" theme="simple"/>
                        </td>
                    </tr>
                </s:form>
                <s:a href="forgot_password_form">Forgot Password?</s:a> |
                <s:a href="login_faq">Login FAQ</s:a> |
                <s:a href="test">Test</s:a> |
                <s:a href="test2">Test</s:a> |
                <s:a href="test3">Test</s:a>
                </div>
                <div class="login-register">
                    <br></br>
    <!--                Don't have an account yet? <s:a href="register_form?debug=xml">Create one</s:a>
                    -->
                    Don't have an account yet? <s:a href="register_form">Create one</s:a>
            </div>
        </div>

        <script type="text/javascript" src="js/myapp.js"></script>

    </body>

</html>


