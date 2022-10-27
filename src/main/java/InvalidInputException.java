public class InvalidInputException extends RuntimeException{
    private String input;

    public String getInput() {
        return input;
    }

    public InvalidInputException(String input, String message) {
        super(message);
        this.input = input;
    }

    @Override
    public String toString() {
        return "InvalidInputException{" +
                "input='" + input + '\'' +
                getMessage() +
                '}';
    }
}
