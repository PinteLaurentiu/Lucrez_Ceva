package lucrez.ceva.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EmailTemplate {
    private final List<Object> contentArguments;
    private final List<Object> subjectArguments;
    private final String contentFormat;
    private final String subjectFormat;

    public String getSubject(Object... arguments) {
        return completeTemplate(subjectFormat, subjectArguments, arguments);
    }

    public String getContent(Object... arguments) {
        return completeTemplate(contentFormat, contentArguments, arguments);
    }

    private String completeTemplate(String format, List<Object> staticArguments, Object[] variableArguments) {
        Object[] args = new Object[staticArguments.size() + variableArguments.length];
        for (int i = 0, staticArgumentsSize = staticArguments.size(); i < staticArgumentsSize; i++)
            args[i] = staticArguments.get(i);
        for (int i = 0, length = variableArguments.length; i < length; i++)
            args[i + staticArguments.size()] = variableArguments[i];
        return String.format(format, args);
    }
}
