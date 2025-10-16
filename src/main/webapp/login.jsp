<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 16/10/2025
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <script>

        window.onload=function() {
            const email = prompt("Email : ")
            const password = prompt("Password :")


            if (email && password) {

                const form = document.createElement("form");
                form.method = "POST"
                form.action = "login"

                const inputEmail = document.createElement("input");
                inputEmail.type = "hidden";
                inputEmail.name = "email"
                inputEmail.value = email
                form.appendChild(inputEmail);

                const inputMDP = document.createElement("input");
                inputMDP.type = "hidden";
                inputMDP.name = "mdp"
                inputMDP.value = password
                form.appendChild(inputMDP);

                document.body.appendChild(form);
                form.submit();
            } else {
                alert("Veuillez remplir touts les champs!")
            }
        }
    </script>

</head>
<body>


<h1>
    login page
</h1>

<% if (request.getAttribute("error") != null) { %>
<p style="color:red;"><%= request.getAttribute("error") %></p>
<% } %>
</body>
</html>
