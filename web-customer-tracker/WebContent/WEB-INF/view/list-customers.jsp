<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!doctype HTML>
<html>
<head>
	<title>List Customers</title>
	
	<!-- Reference our style sheet -->
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" /> 
	
</head>

<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer Relationship Manager</h2>
		</div>
	
	</div>

	<div id="container">
		<div id="content">
			<!-- Add button to Add Customer -->
			<input type="button" value="Add Customer"
				onclick="window.location.href='showFormForAdd'; return false"
				class="add-button" 
			/>
			
			<!-- Add search box to search a customer -->
			<form:form action="search" method="GET">
				Search customer:<input type="text" name="theSearchName" />
				<input type="submit" value="Search" class="add-button" />
			</form:form>
		
		    <!-- Add customer table here -->
		
			<table>
				<tr>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Email</th>
					<th>Action</th>
				</tr>
				
				<c:forEach var="tempCustomer" items="${customers}">
					
					<!--  construct an "update" link with customer id -->
					<c:url var="updateLink" value="/customer/showFormForUpdate">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					<!-- construct a "delete" link with customer id -->
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${tempCustomer.id}" />
					</c:url>
					
					<tr>
						<td>${tempCustomer.firstName} </td>
						<td>${tempCustomer.lastName} </td>
						<td>${tempCustomer.email}</td>
						<td><a href="${updateLink}">Update</a>
						     | 
						    <a href="${deleteLink}"
						       onclick="return confirm('Are you sure you want to delete this customer?')">Delete</a>
						</td>
					</tr>
				
				</c:forEach>	
			</table>		
		</div>	
	</div>
</body>
</html>