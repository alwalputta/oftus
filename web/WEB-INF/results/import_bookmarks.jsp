<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

        <%@include file="/WEB-INF/results/imports.jsp"%>

        <style type="text/css">
        </style>
    </head>


    <body>
        <%@include file="/WEB-INF/results/header.jsp"%>

        <div class="register-modal">
            Import Bookmarks....
            <div class="register-register">
                <s:form action="fileUpload" method="post" enctype="multipart/form-data" >
                    <s:file name="userImage" label="Excel File" />
                    <s:submit />
                </s:form>
            </div>
        </div>
    </body>
</html>
