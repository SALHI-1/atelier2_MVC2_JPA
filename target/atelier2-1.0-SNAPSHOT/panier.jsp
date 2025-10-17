<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 17/10/2025
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ma.fstt.entities.Produit" %>
<%@ page import="java.util.Map" %>
<%@ page import="ma.fstt.entities.Panier" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Mon Panier</h2>

<%
    Panier panier = (Panier) request.getAttribute("panier");
    if (panier != null && !panier.getProduits().isEmpty()) {
%>
<table border="1">
    <tr>
        <th>Produit</th>
        <th>Quantité</th>
    </tr>
    <%
        for (Map.Entry<Produit, Integer> entry : panier.getProduits().entrySet()) {
            Produit p = entry.getKey();
            Integer qte = entry.getValue();
    %>
    <tr>
        <td><%= p.getNom() %></td>
        <td><%= qte %></td>
        <td>
            <form action="panier" method="post" style="display:inline;">
                <input type="hidden" name="action" value="remove">
                <input type="hidden" name="productId" value="<%= p.getId() %>">
                <button type="submit">Supprimer</button>
            </form>
        </td>
    </tr>
    <% } %>
</table>
<%
} else {
%>
<p>Le panier est vide.</p>
<% } %>


</body>
</html>
