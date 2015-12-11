package sampson.string.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindNameEndState extends ResolverState {
    private final static Logger logger = LoggerFactory.getLogger(FindNameEndState.class);
    
    @Override
    public Logger getLogger() {
        // TODO Auto-generated method stub
        return this.logger;
    }
    
    @Override
    public void eatWhiteSpace(char ch) {
        /* Just skip this white space */
        getLogger().debug("Eat white space char for '{}'", FindNameEndState.class);
    }

    @Override
    public void eatNameSeparator(char ch) {
        /* Switch to FindValueState */
        getObjectResolver().switchToFindValueState();
    }
    
}
