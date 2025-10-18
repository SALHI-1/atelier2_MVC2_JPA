<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="ma.fstt.entities.Commande" %>
<%@ page import="ma.fstt.entities.LignesCommande" %>
<%@ page import="ma.fstt.entities.Client" %>

<%
    Client client = (Client) request.getSession().getAttribute("client");

    if (client == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Commande> commandes = (List<Commande>) request.getAttribute("commandes");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mes Commandes - Ma Boutique</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>

<body class="bg-gray-100 text-gray-800 min-h-screen flex flex-col">

<%@ include file="/header/header.jsp" %>

<!-- Contenu principal -->
<main class="flex-grow max-w-6xl mx-auto px-6 py-10">

    <h1 class="text-3xl font-bold text-center text-gray-900 mb-10">📦 Mes Commandes</h1>

    <%
        if (commandes == null || commandes.isEmpty()) {
    %>
    <div class="text-center py-20">
        <p class="text-xl text-gray-600">Vous n'avez encore passé aucune commande 😔</p>
        <a href="${pageContext.request.contextPath}/home"
           class="inline-block mt-6 bg-gray-900 hover:bg-gray-800 text-white font-medium py-2 px-4 rounded-lg transition-all">
            Parcourir les produits
        </a>
    </div>

    <% } else { %>

    <!-- Liste des commandes -->
    <div class="space-y-8">
        <%
            for (Commande commande : commandes) {
        %>
        <div class="bg-white rounded-2xl shadow-md p-6">
            <div class="flex justify-between items-center border-b border-gray-200 pb-3 mb-4">
                <h2 class="text-lg font-semibold text-gray-900">
                    Commande #<%= commande.getId() %>
                </h2>
                <span class="px-3 py-1 rounded-full text-sm font-medium
                        <%= "Livrée".equalsIgnoreCase(commande.getStatut())
                                ? "bg-green-100 text-green-700"
                                : "bg-yellow-100 text-yellow-700" %>">
                        <%= commande.getStatut() %>
                    </span>
            </div>

            <p class="text-sm text-gray-600 mb-4">
                🗓️ Date : <%= commande.getDateCommande() %>
            </p>

            <!-- Tableau des produits -->
            <div class="overflow-x-auto">
                <table class="min-w-full divide-y divide-gray-200 text-sm text-gray-700">
                    <thead class="bg-gray-900 text-white">
                    <tr>
                        <th class="px-6 py-3 text-left font-semibold uppercase">Produit</th>
                        <th class="px-6 py-3 text-left font-semibold uppercase">Quantité</th>
                        <th class="px-6 py-3 text-left font-semibold uppercase">Sous-total</th>
                    </tr>
                    </thead>
                    <tbody class="divide-y divide-gray-100">
                    <%
                        for (LignesCommande ligne : commande.getLignesCommande()) {
                    %>
                    <tr class="hover:bg-gray-50 transition-colors">
                        <td class="px-6 py-4"><%= ligne.getProduit().getNom() %></td>
                        <td class="px-6 py-4"><%= ligne.getQuantite() %></td>
                        <td class="px-6 py-4"><%= ligne.getSousTotal() %> DH</td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <p class="text-right text-lg font-semibold text-gray-900 mt-4">
                💰 Total : <%= commande.getTotalePrix() %> DH
            </p>
        </div>
        <% } %>
    </div>

    <% } %>
</main>



</body>
</html>
