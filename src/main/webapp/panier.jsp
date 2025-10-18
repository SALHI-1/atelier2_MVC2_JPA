<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="ma.fstt.entities.Produit" %>
<%@ page import="java.util.Map" %>
<%@ page import="ma.fstt.entities.Panier" %>
<%@ page import="ma.fstt.entities.Client" %>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mon Panier - Ma Boutique</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 text-gray-800 min-h-screen flex flex-col">

<%@ include file="/header/header.jsp" %>

<!-- Contenu principal -->
<main class="flex-grow max-w-6xl mx-auto px-6 py-10">

    <h1 class="text-3xl font-bold text-center text-gray-900 mb-10">🛒 Mon Panier</h1>

    <%
        Panier panier = (Panier) request.getAttribute("panier");
        if (panier != null && !panier.getProduits().isEmpty()) {
    %>

    <!-- Tableau des produits -->
    <div class="overflow-x-auto bg-white rounded-2xl shadow-md">
        <table class="min-w-full divide-y divide-gray-200">
            <thead class="bg-gray-900 text-white">
            <tr>
                <th class="px-6 py-3 text-left text-sm font-semibold uppercase">Produit</th>
                <th class="px-6 py-3 text-left text-sm font-semibold uppercase">Quantité</th>
                <th class="px-6 py-3 text-left text-sm font-semibold uppercase">Prix</th>
                <th class="px-6 py-3 text-center text-sm font-semibold uppercase">Action</th>
            </tr>
            </thead>
            <tbody class="divide-y divide-gray-100">
            <%
                double total = 0.0;
                for (Map.Entry<Produit, Integer> entry : panier.getProduits().entrySet()) {
                    Produit p = entry.getKey();
                    Integer qte = entry.getValue();
                    double prixLigne = p.getPrix() * qte;
                    total += prixLigne;
            %>
            <tr class="hover:bg-gray-50 transition-colors">
                <td class="px-6 py-4 font-medium text-gray-900"><%= p.getNom() %></td>
                <td class="px-6 py-4 text-gray-700"><%= qte %></td>
                <td class="px-6 py-4 text-gray-700"><%= String.format("%.2f", prixLigne) %> DH</td>
                <td class="px-6 py-4 text-center">
                    <form action="panier" method="post" class="inline">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="productId" value="<%= p.getId() %>">
                        <button type="submit"
                                class="bg-red-600 hover:bg-red-700 text-white text-sm font-medium py-1.5 px-3 rounded-lg transition-all">
                            Supprimer
                        </button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>

            <!-- Ligne total -->
            <tfoot class="bg-gray-100">
            <tr>
                <td colspan="2"></td>
                <td class="px-6 py-4 text-right font-semibold text-gray-900">Total :</td>
                <td class="px-6 py-4 font-bold text-green-700 text-lg"><%= String.format("%.2f", total) %> DH</td>
            </tr>
            </tfoot>
        </table>
    </div>


    <!-- Boutons d’action -->
    <div class="flex justify-end space-x-4 mt-8">
        <form action="panier" method="post">
            <input type="hidden" name="action" value="clean">
            <button type="submit"
                    class="bg-gray-300 hover:bg-gray-400 text-gray-900 font-medium py-2 px-4 rounded-lg transition-all">
                Vider le panier
            </button>
        </form>

        <form action="panier" method="post">
            <input type="hidden" name="action" value="validate">
            <button type="submit"
                    class="bg-green-600 hover:bg-green-700 text-white font-medium py-2 px-4 rounded-lg transition-all">
                Valider la commande ✅
            </button>
        </form>
    </div>

    <% } else { %>

    <!-- Panier vide -->
    <div class="text-center py-20">
        <h2 class="text-2xl text-gray-600 mb-3">Votre panier est vide 🛍️</h2>
        <a href="${pageContext.request.contextPath}/home"
           class="inline-block mt-4 bg-gray-900 hover:bg-gray-800 text-white font-medium py-2 px-4 rounded-lg transition-all">
            Retour à la boutique
        </a>
    </div>

    <% } %>
</main>


</body>
</html>
