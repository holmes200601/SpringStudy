package sampson.string.object;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringObjectResolver {
	private final static Logger logger = LoggerFactory.getLogger(StringObjectResolver.class);

	private String inputStr;
	private String propName;
	private String propValue;
	private ResolverState currentState;
	private ResolverState initialState = new InitialState();
	private ResolverState findValueState = new FindValueState();
	private ResolverState findValueEndState = new FindValueEndState();
	private ResolverState findNameState = new FindNameState();
	private ResolverState findSimpleValueState = new FindSimpleValueState();
	private ResolverState findComplexValueState = new FindComplexValueState();
	private ResolverState findComplexValueEndState = new FindComplexValueEndState();
	private List<NameValueProperty> result = new ArrayList<NameValueProperty>();
	
	public StringObjectResolver(String textStr) {
		this.inputStr = new String(textStr);
		this.setCurrentState(initialState);
		this.initialState.setObjectResolver(this);
		this.findValueState.setObjectResolver(this);
		this.findValueEndState.setObjectResolver(this);
		this.findNameState.setObjectResolver(this);
		this.findSimpleValueState.setObjectResolver(this);
		this.findComplexValueEndState.setObjectResolver(this);
		this.findComplexValueState.setObjectResolver(this);
	}
	
	public void resolve() {
	    propName = "";
	    propValue = "";
	    result.clear();
	    
		for (int i = 0; i < inputStr.length(); ++i) {
		    char ch = inputStr.charAt(i);
		    
		    switch (charType(ch)) {
		    case CHAR_CHAR:
		        this.getCurrentState().eatCharacter(ch);
		        break;
		    case NAMESPE_CHAR:
		        this.getCurrentState().eatNameSeparator(ch);
		        break;
		    case PROPSPE_CHAR:
		        this.getCurrentState().eatPropertySeparator(ch);
		        break;
		    case SUBBEGIN_CHAR:
		        this.getCurrentState().eatSubPropertyBeginer(ch);
		        break;
		    case SUBENDER_CHAR:
		        this.getCurrentState().eatSubPropertyEnder(ch);
		        break;
		    case UNKNOWN_CHAR:
		        this.getCurrentState().eatUnknownCharacter(ch);
		        break;
		    default:
		        logger.error("Unknown character type '{}' when resolving string '{}'", charType(ch).toString(), inputStr);
		        break;
		    }
		}
	}
	
	public List<NameValueProperty> getResult() {
	    if (this.getCurrentState() != this.findValueEndState) {
	        this.resolve();
	    }
	    
		return this.result;
	}
	
	protected ResolverState getCurrentState() {
		return this.currentState;
	}
	
	protected void setCurrentState(ResolverState currentState) {
		this.currentState = currentState;
	}
	
	protected String getPropName() {
		return this.propName;
	}
	
	protected void setPropName(String propName) {
		this.propName = propName;
	}
	
	protected String getPropValue() {
		return this.propValue;
	}
	
	protected void setPropValue(String propValue) {
		this.propValue = propValue;
	}
	
	protected enum CharTypeEnum {
	    CHAR_CHAR, NAMESPE_CHAR, PROPSPE_CHAR, SUBBEGIN_CHAR, SUBENDER_CHAR, UNKNOWN_CHAR
	}
	
	
	protected CharTypeEnum charType(char ch) {
	    CharTypeEnum result = CharTypeEnum.UNKNOWN_CHAR;
	    
	    if (isCharChar(ch)) {
	        result = CharTypeEnum.CHAR_CHAR;
	    } else if (isNameSeparator(ch)) {
	        result = CharTypeEnum.NAMESPE_CHAR;
	    } else if (isPropSeparator(ch)) {
	        result = CharTypeEnum.PROPSPE_CHAR;
	    } else if (isSubPropBeginer(ch)) {
	        result = CharTypeEnum.SUBBEGIN_CHAR;
	    } else if (isSubPropEnder(ch)) {
	        result = CharTypeEnum.SUBENDER_CHAR;
	    } else {
	        result = CharTypeEnum.UNKNOWN_CHAR;
	    }
	        
	   return result;
	}
	
	protected boolean isCharChar(char ch) {
	    return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
	}
	
	protected boolean isNameSeparator(char ch) {
	    return ch == ':';
	}
	
	protected boolean isPropSeparator(char ch) {
	    return ch == ';';
	}
	
	protected boolean isSubPropBeginer(char ch) {
	    return ch == '{';
	}
	
	protected boolean isSubPropEnder(char ch) {
	    return ch == ';';
	}
	
}
