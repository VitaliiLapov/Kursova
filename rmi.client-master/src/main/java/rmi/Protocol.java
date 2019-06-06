package rmi;

public class Protocol {

    private boolean isCommand = false;

    public String textParser(String textFromLable) {
        StringBuffer stringBuffer = new StringBuffer("");
        String text = null;

        if (textFromLable.charAt(0) == ' ') {
            for (int i = 1; i < textFromLable.length(); i++) {
                if (textFromLable.charAt(i) == ' ' && textFromLable.charAt(i + 1) == '#') {
                    for (int j = i + 1; j < textFromLable.length(); j++) {
                        stringBuffer.append(textFromLable.charAt(j));
                    }
                    text = stringBuffer.toString();
                    isCommand = true;
                    return text;
                }
            }
            if (text == null) {
                return textFromLable;
            }
        } else if (textFromLable.charAt(0) == '#') {
            isCommand = true;
            return textFromLable;
        } else {
            return textFromLable;
        }
        System.out.println(text);
        return null;
    }

    public String parserCommandToGetSinglePostArgument(String command) {
        if (isCommand) {
            String arg = command.substring(command.indexOf(":") + 1, command.indexOf(";"));
            return arg;
        } else return command;
    }

    public String[] parserCommandToGetPluralPostArgument(String command) {
        String arguments = parserCommandToGetSinglePostArgument(command);
        String[] strings = arguments.split("(\\w)-");
        strings[1] = strings[1].substring(0, strings[1].length()-1);
        return strings;
    }
}

