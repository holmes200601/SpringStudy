package sampson.string.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindValueEndState extends ResolverState {
    private final static Logger logger = LoggerFactory.getLogger(FindValueEndState.class);
    
	@Override
	public void eatCharacter(char ch) {
		/* Append to Name */
	    appendCharToPropertyName(ch);
	    
	    /* Switch to FindNameState */
	    getObjectResolver().switchToFindNameState();
	}

	@Override
	public void eatWhiteSpace(char ch) {
		/* Do nothing */
	    getLogger().debug("Eat white space for '{}'", FindValueEndState.class);
	}

    @Override
    public Logger getLogger() {
        return this.logger;
    }

}
