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
                <div class="page-title">Edit User Preferences</div>
                <div class="login-form">
                    UNDER CONSTRUCTION...
                    <br></br>
                    <s:a href="register_faq">Edit User Preferences FAQ</s:a>
                    </div>
                </div>

                <div class="login-register">
                    <div class="page-title">Upload Picture</div>
                    <div class="login-box">
                        <img class ="photo-box" src="<s:url action='ImageServlet'/>" alt="Welcome, <s:property value="%{#session.user.firstName}"/> <s:property value="%{#session.user.lastName}"/>"/>
                    <br></br>
                    <br></br>
                    <br></br>
                    <s:form action="upload_picture" method="post" enctype="multipart/form-data">
                        <s:file name="file" label="Chose Picture"/>
                        <tr>
                            <td colspan="2" align="center">
                                <s:submit name="upload" key="register.upload-label" theme="simple"/>
                            </td>
                        </tr>
                    </s:form>
                </div>
            </div>
        </div>
        <%@include file="/WEB-INF/results/open/footer.jsp"%>
        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>

