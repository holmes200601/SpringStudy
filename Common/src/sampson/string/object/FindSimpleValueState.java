package sampson.string.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindSimpleValueState extends ResolverState {
    private final static Logger logger = LoggerFactory.getLogger(FindSimpleValueState.class);
    
	@Override
	public void eatCharacter(char ch) {
		/* Append this char */
	    appendCharToPropertyValue(ch);
	}

	@Override
	public void eatWhiteSpace(char ch) {
		// Do nothing
	    getLogger().debug("Eat white space char for '{}'", FindSimpleValueState.class);
	}

	@Override
	public void eatPropertySeparator(char ch) {
		/* Switch to FindValueEndState */
	    getObjectResolver().switchToFindValueEndState();	    
	}
	
	@Override
	public void eatNumberCharacter(char ch) {
	    /* Append this char */
	    appendCharToPropertyValue(ch);
	}

    @Override
    public Logger getLogger() {
        // TODO Auto-generated method stub
        return this.logger;
    }

}
