package API;

import API.Data.Collections;
import API.Data.Config;
import API.Data.Convert;
import API.Data.SendRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class APITest extends Config {

    SendRequest sendRequest = new SendRequest();

    @Epic("Swagger")

    @Feature(value = "Access to PetStore orders")

    @DisplayName("Return pet inventories by status")
    @Description("Результаты запросов")
    @Severity(SeverityLevel.CRITICAL)

    @Test
    public void getPetInventoriesByStatus() throws Exception {
        List<Object> response = sendRequest.getJson("https", "petstore.swagger.io",
                "v2/store/inventory", Config.getHeaders(), true);
        assertEquals((int) response.get(3), 200);
    }

    @Epic("Swagger")

    @Feature(value = "Everything about your Pets")

    @DisplayName("Pet torture =)")
    @Description("Результаты запросов")
    @Severity(SeverityLevel.CRITICAL)

    @Test
    public void manipulationPetInToStore() throws Exception {

        String petId = ""; // ID питомца

        System.out.println("\nСоздать питомца");
        List<Object> response = sendRequest.postJson("https", "petstore.swagger.io",
                "v2/pet", Config.getHeaders(), true, Collections.getPet().get(1));
        petId = Convert.getJsonO((String) response.get(6)).get("id").getAsString(); // получение ID
        assertEquals((int) response.get(3), 200);
        assertEquals(Convert.getJsonO((String) response.get(6)).get("name").getAsString(), "TestGun");

        System.out.println("\nНайти питомца");
        response = sendRequest.getJson("https", "petstore.swagger.io",
                "v2/pet/" + petId, Config.getHeaders(), true);
        assertEquals((int) response.get(3), 200);
        assertEquals(Convert.getJsonO((String) response.get(6)).get("name").getAsString(), "TestGun");

        System.out.println("\nПереименовать питомца");
        response = sendRequest.putJson("https", "petstore.swagger.io",
                "v2/pet", Config.getHeaders(), true, Collections.getPet().get(2));
        assertEquals((int) response.get(3), 200);
        assertEquals(Convert.getJsonO((String) response.get(6)).get("name").getAsString(), "TestGunRename");

        System.out.println("\nИзменить статус питомца");
        response = sendRequest.postJson("https", "petstore.swagger.io",
                "v2/pet/" + petId, Config.getHeaders(), true, "status", "sold");
        assertEquals((int) response.get(3), 200);
        assertEquals(Convert.getJsonO((String) response.get(6)).get("message").getAsString(), "998844770");

        System.out.println("\nТекущий статус питомца");
        response = sendRequest.getJson("https", "petstore.swagger.io",
                "v2/pet/" + petId, Config.getHeaders(), true);
        assertEquals((int) response.get(3), 200);
        assertEquals(Convert.getJsonO((String) response.get(6)).get("status").getAsString(), "sold");

        System.out.println("\nУдалить питомца");
        response = sendRequest.deleteJson("https", "petstore.swagger.io",
                "v2/pet/" + petId, Config.getHeaders(), true);
        assertEquals((int) response.get(3), 200);
        assertEquals(Convert.getJsonO((String) response.get(6)).get("message").getAsString(), "998844770");

        System.out.println("\nПитомец больше не существует");
        response = sendRequest.getJson("https", "petstore.swagger.io",
                "v2/pet/" + petId, Config.getHeaders(), true);
        assertEquals((int) response.get(3), 404);
        assertEquals(Convert.getJsonO((String) response.get(6)).get("message").getAsString(), "Pet not found");

    }

}
