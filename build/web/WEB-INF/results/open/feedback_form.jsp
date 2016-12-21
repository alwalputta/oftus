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
            $(function() {
                $("#datepicker").datepicker({dateFormat:"yy/mm/dd"});
            });
        </script>
    </head>


    <body>

        <%@include file="/WEB-INF/results/open/header_basic.jsp"%>

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
                <div class="page-title">Send Feedback</div>
                <div class="login-form">

                    <s:form action="save_feedback">
                        <s:textfield name="name" size="25" maxlength="200" key="feedback.name-label"/>
                        <s:textfield name="email" size="35" maxlength="200" key="feedback.email-label"/>
                        <s:textfield name="phone" size="35" maxlength="200" key="feedback.phone-label"/>
                        <s:select name="country"  list="%{countries}" headerKey="-1" headerValue="Select Country..." key="feedback.country-label" listKey="countryCode" listValue="countryName"/>
                        <s:textfield name="issueType" size="30" maxlength="200" key="feedback.issueType-label"/>
                        <s:textarea name="description" row="10" cols="50" key="feedback.description-label"/>

                        <tr>
                            <td></td>
                            <td colspan="2" align="center">
                                <s:submit name="register" key="feedback.register-label" theme="simple"/>
                                <s:reset name="cancel" key="feedback.cancel-label" theme="simple"/>
                            </td>
                        </tr>

                    </s:form>
                    <s:a href="feedback_faq">Feedback FAQ</s:a>
                    </div>
                </div>

                <div class="login-register">
                    <div class="page-title">Suggestion</div>
                    <div class="login-box">
                        <br></br>Already have an account? <a href="index">Login now</a>
                    </div>
                </div>
            </div>
        <%@include file="/WEB-INF/results/open/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>
    </body>
</html>

