package textbookcatalogue;

import java.util.HashMap;

public class TextbookCatalogue {
    private TextbookList list;
    private HashMap<Integer, Integer> existingSkus;

    TextbookCatalogue() {
        list = new TextbookList();
        existingSkus = new HashMap<>();
    }

    public TextbookList getList() { return list; }

    public void add(Textbook textbook) throws IllegalArgumentException {
        if (existingSkus.containsKey(textbook.getSku())) {
            throw new IllegalArgumentException("SKU already exists");
        }
        existingSkus.put(textbook.getSku(), list.size());
        list.add(textbook);
    }

    public Textbook get(int sku) throws IllegalArgumentException {
        Integer index = existingSkus.get(sku);
        if (index == null) {
            throw new IllegalArgumentException("SKU does not exist");
        }
            return list.get(index);
    }

    public void remove(int sku) {
       Integer index = existingSkus.get(sku);
        if (index == null) {
            throw new IllegalArgumentException("SKU does not exist");
        }
        list.doRemove(index);
        existingSkus.remove(sku);
        for (int i=index; i<list.size(); i++) {
            existingSkus.replace(list.get(i).getSku(), i);
        }
    }
}