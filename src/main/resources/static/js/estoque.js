function goTo(page) {
    window.location.href = page;
}

function logout() {
    window.location.href = "login.html";
}

function addItem() {
    alert("Funcionalidade de adicionar insumo (em breve)");
}

function searchItem() {
    const term = document.getElementById("search").value.toLowerCase();
    const rows = document.querySelectorAll("#inventoryTable tr");

    rows.forEach(row => {
        const name = row.children[0].innerText.toLowerCase();
        row.style.display = name.includes(term) ? "" : "none";
    });
}

// Carrega estoque do backend
document.addEventListener("DOMContentLoaded", () => {
    fetch("http://localhost:8080/api/inventory")
        .then(response => response.json())
        .then(data => {
            const table = document.getElementById("inventoryTable");
            table.innerHTML = "";

            data.forEach(item => {
                const row = document.createElement("tr");

                row.innerHTML = `
                    <td>${item.name}</td>
                    <td>${item.quantity}</td>
                    <td>${item.unit}</td>
                    <td>
                        <button onclick="editItem(${item.id})">Editar</button>
                    </td>
                `;

                table.appendChild(row);
            });
        })
        .catch(error => {
            console.error("Erro ao carregar estoque:", error);
        });
});

function editItem(id) {
    alert("Editar item ID: " + id);
}
