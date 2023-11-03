package textbookcatalogue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextbookCatalogue {
    String filename = "catalogue.txt";
    private TextbookList list;
    private HashMap<Integer, Integer> existingSkus;

    TextbookCatalogue() throws FileNotFoundException {
        list = new TextbookList();
        existingSkus = new HashMap<>();

        readList();
    }

    public TextbookList getList() { return list; }

    public void add(Textbook textbook) throws IllegalArgumentException {
        if (existingSkus.containsKey(textbook.getSku())) {
            throw new IllegalArgumentException("SKU already exists");
        }
        existingSkus.put(textbook.getSku(), list.size());
        list.add(textbook);
        saveTextbook(textbook);
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

        saveList();
    }

    public void saveList() {
        try (FileWriter writer = new FileWriter(new File(filename))) {
            writer.write("");
            for (Textbook tb : list) {
                writer.append(tb.toLog());
            }
        }
        catch (IOException error) {
            System.out.println("Could not save file: "+error.getMessage());
        }
    }

    private void saveTextbook(Textbook tb) {
        try (FileWriter writer = new FileWriter(new File(filename), true)) {
            writer.append(tb.toLog());
        }
        catch (IOException error) {
            System.out.println("Could not save file: "+error.getMessage());
        }
    }

    public void readList() {
        try (Scanner scanner = new Scanner(new File(filename))) {
            list.clear();
            existingSkus.clear();

            Pattern logEntryPattern = Pattern.compile("^(\\d+);'(.*)';(\\d+\\.\\d*);(\\d+)$");
            Matcher logMatcher = logEntryPattern.matcher("");

            int index = 0;

            while (scanner.hasNextLine()) {
                logMatcher.reset(scanner.nextLine());
                if (logMatcher.matches()) {
                    list.add(new Textbook(
                        logMatcher.group(1),
                        logMatcher.group(2),
                        logMatcher.group(3),
                        logMatcher.group(4)
                        ));
                    existingSkus.put(list.get(index).getSku(), index);
                    index++;
                }

            }
        }
        catch (FileNotFoundException error) {
            System.out.println("Failed to open/create log file");
        }
    }
}