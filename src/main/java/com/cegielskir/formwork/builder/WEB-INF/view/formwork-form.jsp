<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <title>Save Formwork</title>

</head>

<body>

<%--<div id="wrapper">--%>
<%--<div id="header">--%>
<%--<h2>Address Manager</h2>--%>
<%--</div>--%>
<%--</div>--%>

<div id="container">
    <h3>Save Formwork</h3>

    <%--@elvariable id="formwork" type="com.cegielskir.formwork.builder.entity.Formwork"--%>
    <form:form action="saveFormwork" modelAttribute="formwork" method="POST">

        <form:hidden path="id" />

        <table>
            <tbody>

            <tr>
                <td><label>Name:</label></td>
                <td><form:input path="name" /></td>
            </tr>

            <tr>
                <td><label>Create Date:</label></td>
                <td><form:input path="createDate" /></td>
            </tr>

            <tr>
                <td><label>Info:</label></td>
                <td><form:input path="info" /></td>
            </tr>


            <tr>
                <td><label></label></td>
                <td><input type="submit" value="Save" class="save" /></td>
            </tr>

            </tbody>
        </table>
    </form:form>

    <div style="clear; both;"></div>

    <p>
        <a href="${pageContext.request.contextPath}/address/list">Back to List</a>
    </p>

</div>
</body>
</html>