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
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class GroceryController {
    /**
     *  ----------------ROUTES----------------------
     */

    /**
     * @route /all
     * @return list of existing grocery items
     */
    @GetMapping("/all")
    @ResponseBody
    List<GroceryItem> getGroceries() {
        List<GroceryItem> items = dbGetAll();
        return items;
    }

    /**
     * @route /id/{id}
     * @param String id
     * @return item with matching id
     */
    @GetMapping("/id/{id}")
    @ResponseBody
    GroceryItem getItem(@PathVariable String id) {
        GroceryItem item = dbGetItem(id);
        return item;
    }

    /**
     * @route /available/{id}/{amount}
     * @param String id of item
     * @param String amount of the item
     * @return Boolean if item can be purchased
     */
    @GetMapping("/available/{id}/{amount}")
    @ResponseBody
    Boolean available(@PathVariable String id, @PathVariable int amount) {
        boolean isAvailable = dbAvailable(id, amount);
        return isAvailable;
    }

    /**
     * @route /purchase
     * @param String [] ids of the items to be purchased
     * @return Boolean purchased, whether or not the transaction was succesful
     */
    @PostMapping("/purchase")
    @ResponseBody
    Boolean purchase(@RequestParam(value = "ids") String[] ids) {
        boolean purchased = dbPurchase(ids);
        return purchased;
    }

    /**
     * Helper functions
     */

    /**
     * Function that make HTTPRequest to the grocdb route: /getAll
     * @return list of existing grocery items
     */
    private List<GroceryItem> dbGetAll() {
        String url = "http://localhost:8081/getAll";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<GroceryItem>> result = restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<List<GroceryItem>>() {
                });
        return result.getBody();
    }

    /**
     * Function that make HTTPRequest to the grocdb route: /id/{id}
     * @return GroceryItem item with matching id
     */
    private GroceryItem dbGetItem(String id) {
        String url = "http://localhost:8081/id/" + id;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GroceryItem> result = restTemplate.exchange(
                url,
                HttpMethod.GET, null,
                new ParameterizedTypeReference<GroceryItem>() {
                });
        return result.getBody();
    }

    /**
     * Function that make HTTPRequest to the grocdb route: /available/{id}/{amount}
     * @return boolean available
     */
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

    /**
     * Function that make HTTPRequest to the grocdb route: /purchase
     * @param String [] ids
     * @return boolean succesful purchase
     */
    private boolean dbPurchase(String[] ids) {
        String url = "http://localhost:8081/purchase";
        // Create the request body as a MultiValueMap
        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        for (int i = 0; i < ids.length; i++) {
            body.add("ids", ids[i]);
        }
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