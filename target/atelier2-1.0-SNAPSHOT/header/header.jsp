<%@ page import="ma.fstt.entities.Client" %>
<head>
    <script src="https://cdn.tailwindcss.com"></script>

</head>
<header class="bg-gray-900 text-white shadow-md">
    <nav class="max-w-7xl mx-auto flex justify-between items-center px-6 py-4">

        <!-- Logo -->
        <div class="text-2xl font-semibold tracking-wide">
            <a href="${pageContext.request.contextPath}/home" class="hover:text-gray-300 transition-colors duration-200">
                Ecom
            </a>
        </div>

        <!-- Navigation links -->
        <ul class="flex space-x-8 text-sm font-medium">
            <li>
                <a href="${pageContext.request.contextPath}/home" class="hover:text-gray-300 transition-colors duration-200">
                    Accueil
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/panier" class="hover:text-gray-300 transition-colors duration-200">
                    Panier
                </a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/commandes" class="hover:text-gray-300 transition-colors duration-200">
                    Mes Commandes
                </a>
            </li>
        </ul>

        <!-- Session utilisateur -->
        <div class="flex items-center space-x-4">
            <%
                Client clientSession = (Client) session.getAttribute("client");
                if (clientSession != null) {
            %>
            <span class="text-sm text-gray-300">
          <%= clientSession.getEmail() %>
        </span>
            <a href="${pageContext.request.contextPath}/client?action=logout"
               class="bg-gray-800 hover:bg-gray-700 text-white text-sm font-medium px-3 py-1.5 rounded-md transition-all">
                Deconnexion
            </a>

            <% } else {
                response.sendRedirect("login.jsp");
            }
            %>
        </div>

    </nav>
</header>

<hr class="border-gray-800">
