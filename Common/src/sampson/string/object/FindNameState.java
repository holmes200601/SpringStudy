package sampson.string.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindNameState extends ResolverState {
	private final static Logger logger = LoggerFactory.getLogger(FindNameState.class);
	
	@Override
	public void eatCharacter(char ch) {
		/* Append character */
		appendCharToPropertyName(ch);
	}

	@Override
	public void eatNameSeparator(char ch) {
		/* Change to FindValueState */
		getObjectResolver().switchToFindValueState();
	}

	@Override
	public void eatNumberCharacter(char ch) {
	    /* Append character */
	    appendCharToPropertyName(ch);
	}
	
	@Override
	public void eatWhiteSpace(char ch) {
	    /* Switch to FinNameEndState */
	    getObjectResolver().switchToFindNameEndState();
	}
	
	@Override
	public Logger getLogger() {
		// TODO Auto-generated method stub
		return this.logger;
	}

}
