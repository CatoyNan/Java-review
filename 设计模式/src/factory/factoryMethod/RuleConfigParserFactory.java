package src.factory.factoryMethod;

import src.factory.simpleFactory.IRuleConfigParser;
import src.factory.simpleFactory.JsonlRuleConfigParser;
import src.factory.simpleFactory.YamlRuleConfigParser;

public interface RuleConfigParserFactory {
    IRuleConfigParser create();
}
