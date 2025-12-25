function goTo(page) {
    window.location.href = page;
}

function logout() {
    window.location.href = "login.html";
}

function saveInsumo(event) {
    event.preventDefault();

    const insumo = {
        name: document.getElementById("name").value,
        description: document.getElementById("description").value,
        quantity: parseFloat(document.getElementById("quantity").value),
        unit: document.getElementById("unit").value,
        category: document.getElementById("category").value,
        minimumStock: parseInt(document.getElementById("minimumStock").value),
        expiryDate: document.getElementById("expiryDate").value
    };

    fetch("http://localhost:8080/api/inventory", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(insumo)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao salvar insumo");
        }
        return response.json();
    })
    .then(data => {
        alert("Insumo cadastrado com sucesso!");
        console.log(data);

        // Limpa formulário
        document.querySelector(".insumo-form").reset();
    })
    .catch(error => {
        console.error(error);
        alert("Erro ao cadastrar insumo.");
    });
}
