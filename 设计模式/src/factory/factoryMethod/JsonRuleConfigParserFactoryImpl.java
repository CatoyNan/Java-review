package src.factory.factoryMethod;

import src.factory.simpleFactory.IRuleConfigParser;
import src.factory.simpleFactory.JsonlRuleConfigParser;

public class JsonRuleConfigParserFactoryImpl implements RuleConfigParserFactory {
    @Override
    public IRuleConfigParser create() {
        return new JsonlRuleConfigParser();
    }
}
