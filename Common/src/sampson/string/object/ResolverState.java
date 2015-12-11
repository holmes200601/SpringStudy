package sampson.string.object;

import org.slf4j.Logger;

public abstract class ResolverState {
	private StringObjectResolver objectResolver;
	
	public void eatCharacter(char ch) {
		getLogger().error("Eat character '{}' for '{}'", ch, getClass().getSimpleName());;
		throw new RuntimeException("Should not eat character for " + getClass().getSimpleName());
	}
	
	public void eatWhiteSpace(char ch) {
		getLogger().error("Eat character '{}' for '{}'", ch, getClass().getSimpleName());;
		throw new RuntimeException("Should not eat whitespace for " + getClass().getSimpleName());
	}
	
	public void eatNameSeparator(char ch) {
		getLogger().error("Eat character '{}' for '{}'", ch, getClass().getSimpleName());;
		throw new RuntimeException("Should not eat name separator for " + getClass().getSimpleName());
	}
	
	public void eatPropertySeparator(char ch) {
		getLogger().error("Eat character '{}' for '{}'", ch, getClass().getSimpleName());;
		throw new RuntimeException("Should not eat property separator for " + getClass().getSimpleName());
	}
	
	public void eatSubPropertyBeginer(char ch) {
		getLogger().error("Eat character '{}' for '{}'", ch, getClass().getSimpleName());;
		throw new RuntimeException("Should not eat sub-property beginer for " + getClass().getSimpleName());
	}
	
	public void eatSubPropertyEnder(char ch) {
		getLogger().error("Eat character '{}' for '{}'", ch, getClass().getSimpleName());
		throw new RuntimeException("Should not eat sub-property ender for " + getClass().getSimpleName());
	}
	
	public void eatUnknownCharacter(char ch) {
		getLogger().error("Eat character '{}' for '{}'", ch, getClass().getSimpleName());;
		throw new RuntimeException("Should not eat unknown character for " + getClass().getSimpleName());
	}
	
	public void eatNumberCharacter(char ch) {
	    getLogger().error("Eat character '{}' for '{}'", ch, getClass().getSimpleName());
	    throw new RuntimeException("Should not eat unknown character for " + getClass().getSimpleName());
	}
	
	public void setObjectResolver(StringObjectResolver objResolver) {
		this.objectResolver = objResolver;
	}
	
	public StringObjectResolver getObjectResolver() {
		return this.objectResolver;
	}
	
	public void appendCharToPropertyName(char ch) {
		StringBuilder strBuilder = new StringBuilder(getObjectResolver().getPropName());
		strBuilder.append(ch);
		getObjectResolver().setPropName(strBuilder.toString());
	}
	
	public void appendCharToPropertyValue(char ch) {
		StringBuilder strBuilder = new StringBuilder(getObjectResolver().getPropValue());
		strBuilder.append(ch);
		getObjectResolver().setPropValue(strBuilder.toString());
	}
	
	public abstract Logger getLogger();
}
