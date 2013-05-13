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

        <%@include file="/WEB-INF/results/open/header.jsp"%>

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

            <div class="register-login">
                <div>Register User:</div>
                <s:form action="create_profile">
                    <s:textfield name="username" size="25" maxlength="200" key="register.username-label"/>
                    <s:password name="password" size="25" key="register.password-label"/>
                    <s:password name="password2" size="25" key="register.password2-label"/>
                    <s:textfield name="emailAddress" size="35" maxlength="200" key="register.email-label"/>
                    <s:textfield name="phoneNumber" size="35" maxlength="200" key="register.phone-label"/>

                    <s:textfield name="firstname" size="30" maxlength="200" key="register.firstname-label"/>
                    <s:textfield name="middlename" size="30" maxlength="200" key="register.middlename-label"/>
                    <s:textfield name="lastname" size="30" maxlength="200" key="register.lastname-label"/>
                    <s:radio name="gender" list="%{#session.genders}" listKey="genderCode" listValue="genderName" key="register.gender-label"/>
                    <s:textfield name="DOB" id="datepicker" key="register.DOB-label"/>

                    <s:textfield name="address1" size="50" maxlength="200" key="register.address1-label"/>
                    <s:textfield name="address2" size="50" maxlength="200" key="register.address2-label"/>
                    <s:textfield name="city" size="30" maxlength="200" key="register.city-label"/>
                    <s:select name="state"  list="%{#session.states}" headerKey="-1" headerValue="Select State..." key="register.state-label" listKey="stateCode" listValue="stateName"/>
                    <s:textfield name="zip" size="10" maxlength="20" key="register.zip-label"/>
                    <tr>
                        <td colspan="2" align="center">
                            <s:submit name="register" key="register.register-label" theme="simple"/>
                            <s:reset name="cancel" key="register.cancel-label" theme="simple"/>
                            <s:submit name="close" key="register.close-label" theme="simple"/>
                        </td>
                    </tr>

                </s:form>
                <s:a href="register_faq">Register FAQ</s:a>
                </div>

                <div class="login-register">
                    <br></br>Already have an account? <a href="index">Login now</a>
                </div>

            </div>
        <%@include file="/WEB-INF/results/open/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>

