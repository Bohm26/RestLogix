document.getElementById("loginForm").addEventListener("submit", function (event) {
    event.preventDefault();

    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    const errorMessage = document.getElementById("errorMessage");

    errorMessage.textContent = "";

    if (username === "" || password === "") {
        errorMessage.textContent = "Preencha todos os campos.";
        return;
    }

    // Simulação de autenticação (mock)
    if (username === "admin" && password === "1234") {
        alert("Login realizado com sucesso!");
        window.location.href = "dashboard.html";
    } else {
        errorMessage.textContent = "Usuário ou senha inválidos.";
    }
});
