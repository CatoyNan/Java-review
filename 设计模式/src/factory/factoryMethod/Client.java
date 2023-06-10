package src.factory.factoryMethod;

import src.factory.simpleFactory.IRuleConfigParser;

public class Client {
    public static void main(String[] args) {
        String fileType = "json";
        String source = "{}";
        RuleConfigParserFactory factory;
        if ("json".equals(fileType)) {
            factory = new JsonRuleConfigParserFactoryImpl();
        } else if ("yaml".equals(fileType)) {
            factory= new YamlRuleConfigParserFactoryImpl();
        } else {
            throw new RuntimeException();
        }
        IRuleConfigParser parser = factory.create();
        String parse = parser.parse(source);
    }
}
