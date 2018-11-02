package Group.groceryservice.api;

import Group.groceryservice.api.entities.GroceryItem;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class GroceryController {

    @GetMapping("/all")
    @ResponseBody
    List<GroceryItem> getGroceries () {
        List<GroceryItem> items = dbGetAll();
        System.out.println(items.toString());
        return items;
    }

    private List<GroceryItem> dbGetAll () {
        String url = "http://localhost:8081/getAll";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<GroceryItem>> result = restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<GroceryItem>>() {
                });
        return result.getBody();
    }

    @GetMapping ("/id/{id}")
    @ResponseBody
    GroceryItem getItem(@PathVariable String id) {
        GroceryItem item = dbGetItem(id);
        return item;
    }

    private GroceryItem dbGetItem (String id) {
        String url = "http://localhost:8081/id/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GroceryItem> result = restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<GroceryItem>() {
                });
        return result.getBody();
    }

    @GetMapping ("/available/{id}/{amount}")
    @ResponseBody
    Boolean available(@PathVariable String id, @PathVariable int amount) {
        boolean isAvailable = dbAvailable(id, amount);
        return isAvailable;
    }


    private boolean dbAvailable(String id, int amount) {
        String url = "http://localhost:8081/available/" + id + "/" + amount;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> result = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Boolean>() {
                });
        return result.getBody();
    }

    @PostMapping ("/purchase")
    @ResponseBody
    Boolean purchase(@RequestParam String id, @RequestParam int amount) {
        boolean purchased = dbPurchase(id, amount);
        return purchased;
    }

    private boolean dbPurchase(String id, int amount) {
        String url = "http://localhost:8081/purchase";
        // Create the request body as a MultiValueMap
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();

        body.add("id", id);
        body.add("amount", Integer.toString(amount));

        // Note the body object as first parameter!
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(body, null);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> result = restTemplate.exchange(
                url,
                HttpMethod.POST,
                httpEntity,
                new ParameterizedTypeReference<Boolean>() {
                });
        return result.getBody();
    }




}