package sampson.string.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindComplexValueEndState extends ResolverState {
    private final static Logger logger = LoggerFactory.getLogger(FindComplexValueEndState.class);

	@Override
	public void eatWhiteSpace(char ch) {
		/* Do nothing */

	}

	@Override
	public void eatPropertySeparator(char ch) {
		/* Switch to FindValueEndState */
	    this.getObjectResolver().switchToFindValueEndState();
	}

    @Override
    public Logger getLogger() {
        return this.logger;
    }

}
