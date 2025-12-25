function addIngredient() {
    const div = document.createElement("div");
    div.className = "ingredient";
    div.innerHTML = `
        <input type="text" placeholder="Ingrediente">
        <input type="number" placeholder="Quantidade">
    `;
    document.getElementById("ingredients").appendChild(div);
}

function cancel() {
    window.location.href = "dashboard.html";
}

function saveRecipe() {
    alert("Receita cadastrada (simulado)");
}


function searchRecipe(term) {
    const items = document.querySelectorAll("#recipeList li");
    items.forEach(i => {
        i.style.display = i.innerText.toLowerCase().includes(term.toLowerCase()) ? "" : "none";
    });
}

function openRecipe(name) {
    alert("Visualizando receita: " + name);
}

function goAdd() {
    window.location.href = "adicionar-receita.html";
}
