package com.mycompany.reslogixv2;

import domain.*;
import dto.LoginDTO;
import repository.impl.*;
import service.*;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		System.out.println("=== RESLOGIX V2 - TESTES ===\n");

		try {
			// 1. Testar repositórios
			System.out.println("1. 🗄️ TESTANDO REPOSITÓRIOS");

			UserRepositoryImpl userRepo = new UserRepositoryImpl();
			System.out.println("   ✅ UserRepository: " + userRepo.findAll().size() + " usuários");

			InventoryRepositoryImpl invRepo = new InventoryRepositoryImpl();
			System.out.println("   ✅ InventoryRepository: " + invRepo.findAll().size() + " itens");

			RecipeRepositoryImpl recipeRepo = new RecipeRepositoryImpl();
			System.out.println("   ✅ RecipeRepository: " + recipeRepo.findAll().size() + " receitas");

			// 2. Testar serviços
			System.out.println("\n2. ⚙️ TESTANDO SERVIÇOS");

			AuthService authService = new AuthService(userRepo);
			InventoryService invService = new InventoryService(invRepo);
			RecipeService recipeService = new RecipeService(recipeRepo);

			// Teste login
			try {
				LoginDTO login = new LoginDTO("admin", "admin123");
				User user = authService.login(login);
				System.out.println("   ✅ Login: " + user.getName());
			} catch (Exception e) {
				System.out.println("   ⚠️  Login: " + e.getMessage());
			}

			// Listar itens
			List<Inventory> items = invService.getAllItems();
			System.out.println("   ✅ Itens em estoque: " + items.size());

			// Listar receitas
			List<Recipe> recipes = recipeService.getAllRecipes();
			System.out.println("   ✅ Receitas: " + recipes.size());

			System.out.println("\n✅ SISTEMA FUNCIONANDO!");

		} catch (Exception e) {
			System.err.println("\n❌ ERRO: " + e.getMessage());
			e.printStackTrace();
		}
	}
}
