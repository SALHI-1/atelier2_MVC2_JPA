<%@ page import="ma.fstt.entities.Produit" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        h1, h2 {
            text-align: center;
            color: #333;
        }

        .products-container {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            gap: 20px;
            padding: 20px;
        }

        .product-card {
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 2px 5px rgba(0,0,0,0.1);
            padding: 15px 20px;
            width: 200px;
            text-align: center;
            transition: transform 0.2s, box-shadow 0.2s;
        }

        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
        }

        .product-card p {
            margin: 10px 0;
            font-size: 16px;
        }

        .add-to-cart-btn {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 15px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 14px;
            transition: background-color 0.2s;
        }

        .add-to-cart-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
<h1>Home Page</h1>
<h2>Produits</h2>

<%
    List<Produit> products = (List<Produit>) request.getAttribute("products");

    if (products != null && !products.isEmpty()) {
%>
<div class="products-container">
    <%
        for (Produit p : products) {
    %>
    <div class="product-card">
        <p><strong><%= p.getNom() %></strong></p>
        <p>Prix : <%= p.getPrix() %> DH</p>
        <form action="panier" method="post">
            <input type="hidden" name="productId" value="<%= p.getId() %>"/>
            <input type="hidden" name="action" value="add" >
            <button type="submit" class="add-to-cart-btn">Ajouter au panier</button>
        </form>
    </div>
    <%
        }
    %>
</div>
<%
} else {
%>
<h2 style="text-align:center;">La liste des produits est vide</h2>
<%
    }
%>

</body>
</html>
