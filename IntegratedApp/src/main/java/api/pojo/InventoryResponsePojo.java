package api.pojo;

import java.util.Map;

public class InventoryResponsePojo {

    private Map<String, Integer> inventory;

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }
}
