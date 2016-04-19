package sampson.string.object;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import restaurant.utils.StringUtil;

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
    private ResolverState findNameEndState = new FindNameEndState();
    private ResolverState findSimpleValueState = new FindSimpleValueState();
    private ResolverState findComplexValueState = new FindComplexValueState();
    private ResolverState findComplexValueEndState = new FindComplexValueEndState();
    private List<NameValueProperty> result = new ArrayList<NameValueProperty>();

    public StringObjectResolver(String textStr) {
        this.inputStr = new String(textStr);

        this.initialState.setObjectResolver(this);
        this.findValueState.setObjectResolver(this);
        this.findValueEndState.setObjectResolver(this);
        this.findNameState.setObjectResolver(this);
        this.findSimpleValueState.setObjectResolver(this);
        this.findComplexValueEndState.setObjectResolver(this);
        this.findComplexValueState.setObjectResolver(this);
        this.setCurrentState(initialState);
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
            case NUMBER_CHAR:
                this.getCurrentState().eatNumberCharacter(ch);
                break;
            default:
                logger.error("Unknown character type '{}' when resolving string '{}'", charType(ch).toString(),
                        inputStr);
                break;
            }
        }

        if (this.getCurrentState() != this.getFindValueEndState()) {
            logger.error("Final status is '{}', not '{}'", this.getCurrentState(), this.getFindValueEndState());
            throw new RuntimeException("Final status is not right");
        }
    }

    public List<NameValueProperty> getResult() {
        if (this.getCurrentState() != this.findValueEndState) {
            this.resolve();
        }

        return this.result;
    }

    protected void switchToInitialState() {
        this.setCurrentState(this.getInitialState());
    }

    protected void switchToFindValueState() {
        if (StringUtil.isNullString(this.propName)) {
            logger.error("No property name was found.");
            throw new RuntimeException("No property name was found.");
        }

        if (getCurrentState() != getFindNameState() && getCurrentState() != getFindNameEndState()) {
            logger.error("Can't switch from '{}' to '{}", getCurrentState().toString(), getFindValueState().toString());
            throw new RuntimeException("Can't switch to 'FindValueState'");
        }

        this.setCurrentState(this.getFindValueState());
    }

    protected void switchToFindValueEndState() {
        if (getCurrentState() != getFindSimpleValueState() && getCurrentState() != getFindComplexValueEndState()) {
            logger.error("Can't switch from '{}' to '{}", getCurrentState().toString(), getFindValueState().toString());
            throw new RuntimeException("Can't switch to 'FindValueEndState'");
        }

        this.pushNameValuePair();
        this.setCurrentState(this.getFindValueEndState());
    }

    protected void switchToFindNameState() {
        this.setCurrentState(this.getFindNameState());
    }

    protected void switchToFindSimpleValueState() {
        /* Check property name is valid */
        if (!checkValidPropName()) {
            logger.error("Property name '{}' is not valid before switching to '{}'.", getPropName(),
                    getFindSimpleValueState().toString());
            throw new RuntimeException("Can't switch to 'FindSimpleValueState'");
        }
        this.setCurrentState(this.getFindSimpleValueState());
    }

    protected void switchToFindComplexValueState() {
        if (this.getCurrentState() != this.getFindValueState()) {
            logger.error("Can't switch from '{}' to '{}'", getCurrentState(), getFindComplexValueState().toString());
            throw new RuntimeException("Can't switch to 'FindComplexValueState'.");
        }

        ((FindComplexValueState) getFindComplexValueState()).increaseSubPropertyNum();
        this.setCurrentState(this.getFindComplexValueState());
    }

    protected void switchToFindComplexValueEndState() {
        this.setCurrentState(this.getFindComplexValueEndState());
    }

    protected void switchToFindNameEndState() {
        if (this.getCurrentState() != this.getFindNameState()) {
            logger.error("Can't switch from '{}' to '{}'", getCurrentState(), getFindComplexValueState().toString());
            throw new RuntimeException("Can't switch to 'FindNameState'.");
        }
        this.setCurrentState(this.getFindNameEndState());
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
        CHAR_CHAR, NUMBER_CHAR, NAMESPE_CHAR, PROPSPE_CHAR, SUBBEGIN_CHAR, SUBENDER_CHAR, UNKNOWN_CHAR
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
        } else if (isNumberChar(ch)) {
            result = CharTypeEnum.NUMBER_CHAR;
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
        return ch == '}';
    }

    protected boolean isNumberChar(char ch) {
        return (ch >= '0' && ch <= '9');
    }

    protected void pushNameValuePair() {
        if (StringUtil.isNullString(this.propName) || StringUtil.isNullString(this.propValue)) {
            logger.error("Invalid propName-propValue pair found. {'{}'-'{}'}", this.propName, this.propValue);
            throw new RuntimeException("Invalid propName-propValue pair found.");
        }

        this.result.add(new NameValueProperty(this.propName, this.propValue));
        this.propName = "";
        this.propValue = "";
    }

    /* private method */
    private ResolverState getFindComplexValueState() {
        return findComplexValueState;
    }

    private ResolverState getInitialState() {
        return initialState;
    }

    private ResolverState getFindValueState() {
        return findValueState;
    }

    private ResolverState getFindValueEndState() {
        return findValueEndState;
    }

    private ResolverState getFindNameState() {
        return findNameState;
    }

    private ResolverState getFindSimpleValueState() {
        return findSimpleValueState;
    }

    private ResolverState getFindComplexValueEndState() {
        return findComplexValueEndState;
    }

    private ResolverState getFindNameEndState() {
        return findNameEndState;
    }

    private boolean checkValidPropName() {
        return (!StringUtil.isNullString(getPropName()));
    }

    private boolean checkValidPropValue() {
        return (!StringUtil.isNullString(getPropValue()));
    }

}
