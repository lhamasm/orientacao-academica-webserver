<%@ page import ="java.util.*" %>
<%@ page import ="orientacao.*" %>

<html>
<body>
<h2>
<%
Usuario user = (Usuario) request.getAttribute("usuario");
out.println(user.getNome());
%></h2>
</body>
</html>
