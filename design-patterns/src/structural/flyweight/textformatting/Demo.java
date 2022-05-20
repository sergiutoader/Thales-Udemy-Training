package structural.flyweight.textformatting;

import java.util.ArrayList;
import java.util.List;

class FormattedText {
    private String plainText;
    private boolean[] capitalize;

    public FormattedText(String plainText) {
        this.plainText = plainText;
        capitalize = new boolean[plainText.length()];
    }

    public void capitalize(int start, int end) {
        for (int i = start; i <= end; i++) {
            capitalize[i] = true;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            sb.append(
                    capitalize[i] ? Character.toUpperCase(c) : c
            );
        }
        return sb.toString();
    }
}

class BetterFormattedText {

    private final String plainText;
    private final List<TextRange> formatting = new ArrayList<>();

    public BetterFormattedText(String plainText) {
        this.plainText = plainText;
    }

    public TextRange getRange(int start, int end) {
        TextRange tr = new TextRange(start, end);
        formatting.add(tr);
        return tr;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            for (TextRange range : formatting) {
                if (range.covers(i)) {
                    c = Character.toUpperCase(c);
                    break;
                }
            }
            sb.append(c);
        }

        return sb.toString();
    }

    // FlyWeight
    public class TextRange {
        public int start, end;
        public boolean capitalize, bold, italic;

        public TextRange(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public boolean covers(int position) {
            return start <= position && position <= end;
        }
    }
}

public class Demo {
    public static void main(String[] args) {
        final FormattedText ft = new FormattedText("This is a brave new world");
        ft.capitalize(10, 15);
        System.out.println(ft);

        final BetterFormattedText bft = new BetterFormattedText("Make America Great Again");
        bft.getRange(13, 18).capitalize = true;
        System.out.println(bft);
    }
}
