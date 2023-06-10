package src.factory.factoryMethod;

import src.factory.simpleFactory.IRuleConfigParser;
import src.factory.simpleFactory.YamlRuleConfigParser;

public class YamlRuleConfigParserFactoryImpl implements RuleConfigParserFactory {
    @Override
    public IRuleConfigParser create() {
        return new YamlRuleConfigParser();
    }
}
