<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>

<!DOCTYPE html>
<html>

<head>
    <title>Formwork List</title>


</head>

<body>

<div id="wrapper">
    <div id="header">
        <h2> List of formworks </h2>
    </div>
</div>

<div id="container">

    <div id="content">

        <%--<input type="button" value="Add Formwork"--%>
               <%--onclick="window.location.href='addressForm'; return false;"--%>
               <%--class="add-button"--%>
        <%--/>--%>

        <table>
            <tr>
                <th>Formwork Name</th>
                <th>Create Date</th>
                <th>Info</th>
            </tr>

            <%--@elvariable id="formworks" type="java.util.List"--%>
            <c:forEach var="tempFormwork" items="${formworks}">

                <%--<c:url var="updateLink" value="/customer/showFormForUpdate">--%>
                <%--<c:param name="customerId" value="${tempCustomer.id}" />--%>
                <%--</c:url>--%>

                <%--<c:url var="deleteLink" value="/address/deleteAddress">--%>
                    <%--<c:param name="addressId" value="${tempAddress.id}" />--%>
                <%--</c:url>--%>
                <tr>
                    <td> ${tempFormwork.name} </td>
                    <td> ${tempFormwork.createDate} </td>
                    <td> ${tempFormwork.info} </td>
                        <%--<td>--%>
                        <%--<a href="${updateLink}">Update</a>--%>
                        <%--|--%>
                    <%--<td>--%>
                        <%--<a href="${deleteLink}"--%>
                           <%--onclick="if (!(confirm('Are you sure you want to delete this address?')))--%>
                                    <%--return false;">Delete</a>--%>
                    <%--</td>--%>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>