<%@ page import="ma.fstt.entities.Produit" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.fstt.entities.Client" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Accueil - Ma Boutique</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 text-gray-800 min-h-screen flex flex-col">

<%@ include file="/header/header.jsp" %>

<!-- Contenu principal -->
<main class="flex-grow max-w-7xl mx-auto px-6 py-10">

    <h1 class="text-4xl font-bold text-center text-gray-900 mb-4">Accueil</h1>
    <h2 class="text-2xl font-semibold text-center text-gray-700 mb-10">Nos Produits</h2>

    <%
        List<Produit> products = (List<Produit>) request.getAttribute("products");

        if (products != null && !products.isEmpty()) {
    %>

    <!-- Liste des produits -->
    <div class="grid grid-cols-1 sm:grid-cols-2 md:grid-cols-3 lg:grid-cols-4 gap-8">
        <%
            for (Produit p : products) {
        %>
        <div class="bg-white rounded-2xl shadow-md hover:shadow-xl transition-shadow duration-300 overflow-hidden text-center">
            <div class="p-6">
                <h3 class="text-lg font-semibold text-gray-900 mb-2"><%= p.getNom() %></h3>
                <p class="text-gray-600 mb-4"><%= p.getPrix() %> DH</p>

                <form action="panier" method="post" class="mt-3">
                    <input type="hidden" name="productId" value="<%= p.getId() %>"/>
                    <input type="hidden" name="action" value="add"/>

                    <button type="submit"
                            class="w-full bg-green-600 hover:bg-green-700 text-white font-medium py-2 px-4 rounded-lg transition-all">
                        Ajouter au panier
                    </button>
                </form>
            </div>
        </div>
        <%
            }
        %>
    </div>

    <% } else { %>

    <!-- Aucun produit -->
    <div class="text-center py-20">
        <h2 class="text-xl text-gray-600">Aucun produit disponible pour le moment 😔</h2>
    </div>

    <% } %>
</main>


</body>
</html>
