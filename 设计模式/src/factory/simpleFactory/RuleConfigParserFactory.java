package src.factory.simpleFactory;

public class RuleConfigParserFactory {
    public static IRuleConfigParser create(String fileType) {
        IRuleConfigParser parser = null;
        if ("json".equals(fileType)) {
            parser = new JsonlRuleConfigParser();
        } else if ("yaml".equals(fileType)) {
            parser = new YamlRuleConfigParser();
        } else {
            throw new RuntimeException("");
        }
        return parser;
    }
}
