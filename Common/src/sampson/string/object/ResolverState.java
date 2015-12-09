package sampson.string.object;

public interface ResolverState {
	public void eatCharacter(char ch);
	public void eatWhiteSpace(char ch);
	public void eatNameSeparator(char ch);
	public void eatPropertySeparator(char ch);
	public void eatSubPropertyBeginer(char ch);
	public void eatSubPropertyEnder(char ch);
	public void eatUnknownCharacter(char ch);
	public void setObjectResolver(StringObjectResolver objResolver);
}
