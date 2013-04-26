<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
    <head>

        <%@include file="/WEB-INF/results/imports.jsp"%>

        <style type="text/css">
        </style>

        <script type="text/javascript">
            $(function() {
                $("#datepicker").datepicker({dateFormat:"yy/mm/dd"});
            });
            
        </script>
    </head>
    <body>
        <h2>Struts2 File Upload Example</h2>
        User Image: <s:property value="content"/>
        <br/>
        Content Type: <s:property value="contentType"/>
        <br/>
        File Name: <s:property value="fileName"/>
        <br/>
        Uploaded Image:
        <br/>
        <img src="<s:property value="fileName"/>"/>
    </body>
</html>