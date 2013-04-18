<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
    <head>

        <title>Oftus&reg; - Development</title>

        <link rel="shortcut icon" type="image/x-icon" class="header-icon" href="images/favicon.ico"/>
        <link rel="stylesheet" type="text/css" href="css/myapp.css"/>
        <link rel="stylesheet" type="text/css" media="all" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.18/themes/sunny/jquery-ui.css"/>

        <script type="text/javascript" src="js/jquery.highlight-3.js"></script>
        <script type="text/javascript" src="js/constants.js"></script>

        <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
        <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

        <script type="text/javascript">
        </script>
    </head>



    <body>

        <div class="new-category-modal">
            <div class="new-category">
                <div>Add New Category</div>

                <s:form action="update_category">
                    <s:hidden name="categoryId" value="%{#session.category.categoryId}"/>
                    <s:textfield name="categoryName" value="%{#session.category.categoryName}" size="60" maxlength="200" key="new.categoryname-label"/>
                    <s:textarea name="description" value="%{#session.category.description}" rows="5" cols="60" key="new.category-description-label"/>
                    <tr>
                        <td colspan="2" align="center">
                            <s:submit align="center" key="new.submit-category-label" theme="simple"/>
                            <s:reset align="center" key="new.reset-label" theme="simple"/>
                        </td>
                    </tr>
                </s:form>
                <s:a href="#new_category_faq" class="new-element-links">Add/Edit Category FAQ</s:a>
            </div>
        </div>

        <script type="text/javascript" src="js/myapp.js"></script>

    </body>
</html>

