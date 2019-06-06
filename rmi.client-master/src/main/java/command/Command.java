package command;

public enum Command {
    CMD_PING("^#(p|ping):?"),
    CMD_ECHO("^#(ec|echo):?\\s(([A-Za-z0-9_\\s])?)+;"),
    CMD_GEN("^#(g|gen|generation):?\\s((p|path|pathToFile)(-|\\s)(.)+)\\s((n|name|nameFile)(-|\\s)(.)+)+;"),
    CMD_PROCESS("^#(pr|process):?\\s((r|rand|randomFile)(-|\\s)(.)+)\\s((s|sort|sortFile)(-|\\s)(.)+)+;");

    private final String regex;

    Command(final String regex) {
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }
}
