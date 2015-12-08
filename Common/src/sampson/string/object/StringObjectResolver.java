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
	private ResolverState 
	private List<NameValueProperty> result = new ArrayList<NameValueProperty>();
	
	public StringObjectResolver(String textStr) {
		this.inputStr = new String(textStr);
		this.propName = "";
		this.propValue = "";
	}
	
	public void resolve() {
		
	}
	
	public List<NameValueProperty> getResult() {
		
	}
	
	protected ResolverState getCurrentState() {
		return this.currentState;
	}
	
	protected void setState(ResolverState currentState) {
		this.currentState = currentState;
	}
	
	protected void resetState() {
		this.setState(new InitialState(getPropName(), getPropValue()));
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
}
