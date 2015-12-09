package sampson.string.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InitialState extends ResolverState {
	private final static Logger logger = LoggerFactory.getLogger(InitialState.class);
	
	@Override
	public void eatCharacter(char ch) {
		/* Append the char to propName */
		appendCharToPropertyName(ch);
		
		/* Change state to FindNameState */
		getObjectResolver().switchToFindNameState();
	}

	@Override
	public void eatWhiteSpace(char ch) {
		/* Do nothing for white space char */
		logger.debug("Eat whitespace char '{}' for InitialState", ch);
	}

	@Override
	public Logger getLogger() {
		return this.logger;
	}

}
