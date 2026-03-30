import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class RpnCalculator {
    private static final Deque<Double> stack = new ArrayDeque<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Simple Java Calculator (shows RPN steps)");
        System.out.println("Use regular expressions like: 2 + 3 * 4");
        System.out.println("Or use stack tokens/commands: + - * / dup swap drop clear");
        System.out.println("Tip: use 'rpn 5 3 + 2 *' to run multiple RPN tokens on the main stack");
        System.out.println("Type 'exit' to quit.\n");

        while (true) {
            printStack();
            System.out.print("> ");
            String line = scanner.nextLine().trim();

            if (line.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (line.isEmpty()) {
                continue;
            }

            if (line.equalsIgnoreCase("help")) {
                printHelp();
                continue;
            }

            if (line.equalsIgnoreCase("stack")) {
                continue;
            }

            if (line.toLowerCase().startsWith("rpn ")) {
                String[] tokens = line.substring(4).trim().split("\\s+");
                if (tokens.length == 1 && tokens[0].isEmpty()) {
                    System.out.println("No RPN tokens provided.");
                } else {
                    runRpnTokensOnMainStack(tokens);
                }
                continue;
            }

            if (processSingleToken(line)) {
                continue;
            }

            evaluateExpressionLine(line);
        }

        scanner.close();
    }

    private static void printHelp() {
        System.out.println("Examples:");
        System.out.println("  2 + 3 * 4");
        System.out.println("  (2 + 3) * 4");
        System.out.println("  -5 + 2");
        System.out.println("  rpn 5 3 + 2 *");
        System.out.println("Single-token stack mode: numbers, + - * /, dup swap drop clear");
    }

    private static boolean processSingleToken(String token) {
        if (tryPushNumber(token)) {
            return true;
        }

        if (applyCommand(token)) {
            return true;
        }

        return false;
    }

    private static void evaluateExpressionLine(String expression) {
        try {
            List<String> tokens = tokenize(expression);
            tokens = addImplicitMultiplication(tokens);
            List<String> rpn = infixToRpn(tokens);

            System.out.println("RPN: " + String.join(" ", rpn));

            Double result = evaluateRpnTokens(rpn, true);
            if (result != null) {
                System.out.println("Result: " + format(result));
            }
        } catch (IllegalArgumentException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    private static List<String> addImplicitMultiplication(List<String> tokens) {
        List<String> normalized = new ArrayList<>();
        for (String token : tokens) {
            if (!normalized.isEmpty()) {
                String previous = normalized.get(normalized.size() - 1);
                if ((isNumber(previous) || ")".equals(previous)) && "(".equals(token)) {
                    normalized.add("*");
                } else if (")".equals(previous) && isNumber(token)) {
                    normalized.add("*");
                }
            }
            normalized.add(token);
        }
        return normalized;
    }

    private static List<String> tokenize(String expression) {
        List<String> tokens = new ArrayList<>();
        int index = 0;

        while (index < expression.length()) {
            char current = expression.charAt(index);

            if (Character.isWhitespace(current)) {
                index++;
                continue;
            }

            if (current == '(' || current == ')') {
                tokens.add(String.valueOf(current));
                index++;
                continue;
            }

            if (isOperatorChar(current)) {
                if (current == '-' && isUnaryMinus(tokens)) {
                    int start = index;
                    index++;
                    if (index >= expression.length() ||
                        !(Character.isDigit(expression.charAt(index)) || expression.charAt(index) == '.')) {
                        throw new IllegalArgumentException("Invalid unary minus usage.");
                    }
                    while (index < expression.length()) {
                        char numberChar = expression.charAt(index);
                        if (Character.isDigit(numberChar) || numberChar == '.') {
                            index++;
                        } else {
                            break;
                        }
                    }
                    tokens.add(expression.substring(start, index));
                } else {
                    tokens.add(String.valueOf(current));
                    index++;
                }
                continue;
            }

            if (Character.isDigit(current) || current == '.') {
                int start = index;
                while (index < expression.length()) {
                    char numberChar = expression.charAt(index);
                    if (Character.isDigit(numberChar) || numberChar == '.') {
                        index++;
                    } else {
                        break;
                    }
                }
                tokens.add(expression.substring(start, index));
                continue;
            }

            throw new IllegalArgumentException("Unexpected character: '" + current + "'");
        }

        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("Expression is empty.");
        }

        return tokens;
    }

    private static boolean isUnaryMinus(List<String> tokens) {
        if (tokens.isEmpty()) {
            return true;
        }
        String previous = tokens.get(tokens.size() - 1);
        return isOperator(previous) || "(".equals(previous);
    }

    private static List<String> infixToRpn(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Deque<String> operators = new ArrayDeque<>();

        for (String token : tokens) {
            if (isNumber(token)) {
                output.add(token);
            } else if (isOperator(token)) {
                while (!operators.isEmpty() && isOperator(operators.peek()) &&
                       precedence(operators.peek()) >= precedence(token)) {
                    output.add(operators.pop());
                }
                operators.push(token);
            } else if ("(".equals(token)) {
                operators.push(token);
            } else if (")".equals(token)) {
                boolean foundOpening = false;
                while (!operators.isEmpty()) {
                    String top = operators.pop();
                    if ("(".equals(top)) {
                        foundOpening = true;
                        break;
                    }
                    output.add(top);
                }
                if (!foundOpening) {
                    throw new IllegalArgumentException("Mismatched parentheses.");
                }
            } else {
                throw new IllegalArgumentException("Invalid token: " + token);
            }
        }

        while (!operators.isEmpty()) {
            String operator = operators.pop();
            if ("(".equals(operator) || ")".equals(operator)) {
                throw new IllegalArgumentException("Mismatched parentheses.");
            }
            output.add(operator);
        }

        return output;
    }

    private static void runRpnTokensOnMainStack(String[] tokens) {
        for (String token : tokens) {
            if (!processSingleToken(token)) {
                System.out.println("Unknown RPN token: " + token);
                return;
            }
        }
    }

    private static boolean tryPushNumber(String token) {
        if (!isNumber(token)) {
            return false;
        }
        stack.push(Double.parseDouble(token));
        return true;
    }

    private static boolean applyCommand(String token) {
        switch (token) {
            case "+":
                return applyBinary((a, b) -> a + b);
            case "-":
                return applyBinary((a, b) -> a - b);
            case "*":
                return applyBinary((a, b) -> a * b);
            case "/":
                return applyBinary((a, b) -> {
                    if (b == 0) {
                        throw new ArithmeticException("Cannot divide by zero");
                    }
                    return a / b;
                });
            case "dup":
                if (requireSize(1)) {
                    stack.push(stack.peek());
                }
                return true;
            case "swap":
                if (requireSize(2)) {
                    double first = stack.pop();
                    double second = stack.pop();
                    stack.push(first);
                    stack.push(second);
                }
                return true;
            case "drop":
                if (requireSize(1)) {
                    stack.pop();
                }
                return true;
            case "clear":
                stack.clear();
                return true;
            default:
                return false;
        }
    }

    private static Double evaluateRpnTokens(List<String> rpnTokens, boolean pushResultToMainStack) {
        Deque<Double> evalStack = new ArrayDeque<>();

        for (String token : rpnTokens) {
            if (isNumber(token)) {
                evalStack.push(Double.parseDouble(token));
                printEvalStep(token, evalStack);
                continue;
            }

            if (isOperator(token)) {
                if (evalStack.size() < 2) {
                    System.out.println("Error: malformed expression near operator '" + token + "'.");
                    return null;
                }
                double right = evalStack.pop();
                double left = evalStack.pop();
                double value;
                try {
                    value = applyOperator(left, right, token);
                } catch (ArithmeticException exception) {
                    System.out.println("Error: " + exception.getMessage());
                    return null;
                }
                evalStack.push(value);
                printEvalStep(token, evalStack);
                continue;
            }

            System.out.println("Error: invalid RPN token '" + token + "'.");
            return null;
        }

        if (evalStack.size() != 1) {
            System.out.println("Error: malformed expression.");
            return null;
        }

        Double result = evalStack.peek();
        if (pushResultToMainStack) {
            stack.push(result);
        }
        return result;
    }

    private static void printEvalStep(String token, Deque<Double> evalStack) {
        System.out.println("  token '" + token + "' -> eval stack (top -> bottom): " + asTopToBottom(evalStack));
    }

    private static String asTopToBottom(Deque<Double> deque) {
        List<String> values = new ArrayList<>();
        for (Double value : deque) {
            values.add(format(value));
        }
        return values.toString();
    }

    private static String format(double value) {
        if (Math.abs(value - Math.rint(value)) < 1e-10) {
            return String.valueOf((long) Math.rint(value));
        }
        return String.valueOf(value);
    }

    private static boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    private static boolean isOperatorChar(char character) {
        return character == '+' || character == '-' || character == '*' || character == '/';
    }

    private static boolean isOperator(String token) {
        return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
    }

    private static int precedence(String operator) {
        if ("+".equals(operator) || "-".equals(operator)) {
            return 1;
        }
        if ("*".equals(operator) || "/".equals(operator)) {
            return 2;
        }
        return -1;
    }

    private static double applyOperator(double left, double right, String operator) {
        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                if (right == 0) {
                    throw new ArithmeticException("Cannot divide by zero");
                }
                return left / right;
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    private interface BinaryOperation {
        double apply(double a, double b);
    }

    private static boolean applyBinary(BinaryOperation operation) {
        if (!requireSize(2)) {
            return true;
        }

        double right = stack.pop();
        double left = stack.pop();

        try {
            double result = operation.apply(left, right);
            stack.push(result);
        } catch (ArithmeticException exception) {
            System.out.println("Error: " + exception.getMessage());
            stack.push(left);
            stack.push(right);
        }

        return true;
    }

    private static boolean requireSize(int minSize) {
        if (stack.size() < minSize) {
            System.out.println("Need at least " + minSize + " value(s) on the stack.");
            return false;
        }
        return true;
    }

    private static void printStack() {
        System.out.println("Main stack (top -> bottom): " + asTopToBottom(stack));
    }
}
