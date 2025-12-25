package com.mycompany.restlogixv2;

import com.mycompany.restlogixv2.entities.Inventory;
import com.mycompany.restlogixv2.entities.Recipe;
import com.mycompany.restlogixv2.entities.User;
import com.mycompany.restlogixv2.dto.LoginDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.mycompany.restlogixv2.service.InventoryService;
import com.mycompany.restlogixv2.service.RecipeService;
import com.mycompany.restlogixv2.service.AuthService;

import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    // Bean para rodar testes ao iniciar a aplicação

	/**
	 *
	 * @param authService
	 * @param inventoryService
	 * @param recipeService
	 * @return
	 */
    @Bean
    public CommandLineRunner run(AuthService authService,
                                 InventoryService inventoryService,
                                 RecipeService recipeService) {
        return args -> {
            System.out.println("=== RESLOGIX V2 - TESTES ===\n");

            try {
                // 1. Testar usuários
                System.out.println("1. 🗄️ TESTANDO USUÁRIOS");

                List<User> allUsers = authService.getAllUsers(); // precisa criar este método no AuthService
                System.out.println("   ✅ Usuários cadastrados: " + allUsers.size());

                // 2. Testar login
                System.out.println("\n2. ⚙️ TESTANDO LOGIN");
                try {
                    LoginDTO login = new LoginDTO("admin", "admin123");
                    User user = authService.login(login);
                    System.out.println("   ✅ Login realizado: " + user.getName());
                } catch (Exception e) {
                    System.out.println("   ⚠️ Login falhou: " + e.getMessage());
                }

                // 3. Testar estoque
                System.out.println("\n3. 🗃️ TESTANDO ESTOQUE");
                List<Inventory> items = inventoryService.getAllItems();
                System.out.println("   ✅ Itens em estoque: " + items.size());

                // 4. Testar receitas
                System.out.println("\n4. 🥘 TESTANDO RECEITAS");
                List<Recipe> recipes = recipeService.getAllRecipes();
                System.out.println("   ✅ Receitas cadastradas: " + recipes.size());

                System.out.println("\n✅ SISTEMA FUNCIONANDO!");

            } catch (Exception e) {
                System.err.println("\n❌ ERRO: " + e.getMessage());
            }
        };
    }
}
