package textbookcatalogue;

import java.util.ArrayList;

import javafx.collections.ModifiableObservableListBase;

public class TextbookList extends ModifiableObservableListBase<Textbook> {
    private ArrayList<Textbook> textbooks;

    public TextbookList() {
        textbooks = new ArrayList<>();
    }

    @Override
    public Textbook get(int index) {
        return textbooks.get(index);
    }

    @Override
    public int size() {
        return textbooks.size();
    }

    @Override
    protected void doAdd(int index, Textbook element) {
        textbooks.add(index, element);
    }

    @Override
    protected Textbook doSet(int index, Textbook element) {
        return textbooks.set(index, element);
    }

    @Override
    protected Textbook doRemove(int index) {
        return textbooks.remove(index);
    }
}
