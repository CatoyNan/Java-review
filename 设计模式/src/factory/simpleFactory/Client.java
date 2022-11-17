package src.factory.simpleFactory;

public class Client {
    public static void main(String[] args) {
        String fileType = "json";
        String source = "{}";
        IRuleConfigParser parser = RuleConfigParserFactory.create(fileType);
        String parse = parser.parse(source);
    }
}
