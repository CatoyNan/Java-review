package src.factory.noFactory;

public class Client {
    public static void main(String[] args) {
        String fileType = "json";
        String source = "{}";
        IRuleConfigParser parser = null;
        if ("json".equals(fileType)) {
            parser = new JsonlRuleConfigParser();
        } else if ("yaml".equals(fileType)) {
            parser = new YamlRuleConfigParser();
        } else {
            throw new RuntimeException("");
        }
        String parse = parser.parse(source);
    }
}
