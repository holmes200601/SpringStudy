package sampson.string.object;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindComplexValueState extends ResolverState {
    private final static Logger logger = LoggerFactory.getLogger(FindComplexValueState.class);
    
    private long subPropertyNum = 0;
    
	@Override
	public void eatCharacter(char ch) {
		/* Append to propValue */
	    this.appendCharToPropertyValue(ch);
	}

	@Override
	public void eatWhiteSpace(char ch) {
		/* Do nothing */

	}

	@Override
	public void eatNameSeparator(char ch) {
		this.appendCharToPropertyValue(ch);
	}

	@Override
	public void eatPropertySeparator(char ch) {
	    this.appendCharToPropertyValue(ch);
	}

	@Override
	public void eatSubPropertyBeginer(char ch) {
	    this.increaseSubPropertyNum();
	    this.appendCharToPropertyValue(ch);
	}

	@Override
	public void eatSubPropertyEnder(char ch) {
	    this.decreaseSubPropertyNum();
	    if (this.subPropertyNum > 0) {
	        this.appendCharToPropertyValue(ch);
	    } else {
	        this.getObjectResolver().switchToFindComplexValueEndState();
	    }
	}

	@Override
	public void eatUnknownCharacter(char ch) {
		/* Do nothing */

	}
	
	@Override
	public void eatNumberCharacter(char ch) {
	    /* Append to propvalue */
	    this.appendCharToPropertyValue(ch);
	}

    @Override
    public Logger getLogger() {
        return this.logger;
    }
    
    public void increaseSubPropertyNum() {
        if (this.subPropertyNum < 0) {
            logger.error("Unmatched pair {} found.");
            throw new RuntimeException("Unmatched pair {} found.");
        }
        
        this.subPropertyNum++;
    }
    
    public void decreaseSubPropertyNum() {
        if (this.subPropertyNum <= 0) {
            logger.error("Unmatched pair {} found.");
            throw new RuntimeException("Unmatched pair {} found.");
        }
        
        this.subPropertyNum--;
    }

}
