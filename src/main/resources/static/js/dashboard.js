function navigate(page) {
    const routes = {
        dashboard: "dashboard.html",
        estoque: "estoque.html",
        receitas: "receitas.html",
        insumos: "insumos.html"
    };

    if (routes[page]) {
        window.location.href = routes[page];
    }
}

function logout() {
    window.location.href = "login.html";
}

// Carrega dados reais do backend
document.addEventListener("DOMContentLoaded", () => {
    fetch("http://localhost:8080/api/inventory")
        .then(response => response.json())
        .then(data => {
            // Total de insumos
            document.getElementById("totalInsumos").innerText = data.length;

            // Itens em falta (lowStock = true)
            const itensFalta = data.filter(item => item.lowStock === true).length;
            document.getElementById("itensFalta").innerText = itensFalta;

            // Receitas ainda é mock (até integrar a API)
            document.getElementById("totalReceitas").innerText = 12;
        })
        .catch(error => {
            console.error("Erro ao carregar dashboard:", error);
        });
});
