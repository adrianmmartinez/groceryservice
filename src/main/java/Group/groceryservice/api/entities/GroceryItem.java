package Group.groceryservice.api.entities;

public class GroceryItem {

    private String id;
    private String name;
    private String desc;
    private String price;
    private String type;
    private int items;
    private String img;

    public GroceryItem() {
        super();
    }

    public GroceryItem(String id, String name, String desc, String price, String type, int items) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.type = type;
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
