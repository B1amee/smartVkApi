package project.models;

import java.util.List;

public class VkLikes {

    private long count;
    private List<Long> items;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "VkLikes{" +
                "count=" + count +
                ", items=" + items +
                '}';
    }
}
