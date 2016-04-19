package sampson.string.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindValueState extends ResolverState {
    private final static Logger logger = LoggerFactory.getLogger(FindValueState.class);

    @Override
    public void eatCharacter(char ch) {
        /* Append the char to property value */
        appendCharToPropertyValue(ch);

        /* Switch to FindSimpleValueState */
        getObjectResolver().switchToFindSimpleValueState();
    }

    @Override
    public void eatWhiteSpace(char ch) {
        getLogger().debug("Eat white space char '{}' in '{}'", ch, getClass().getSimpleName());
    }

    @Override
    public void eatSubPropertyBeginer(char ch) {
        /* Switch to FindComplexValueState */
        getObjectResolver().switchToFindComplexValueState();
    }

    @Override
    public void eatNumberCharacter(char ch) {
        /* Append the char to property value */
        appendCharToPropertyValue(ch);

        /* Switch to FindSimpleValueState */
        getObjectResolver().switchToFindSimpleValueState();
    }

    @Override
    public Logger getLogger() {
        // TODO Auto-generated method stub
        return this.logger;
    }

}
