public class CalculatorLogic {

    // Simple BODMAS evaluator using two stacks
    public static String evaluate(String expr) {
        try {
            return Double.toString(eval(expr));
        } catch (Exception e) {
            return "Error";
        }
    }

    private static double eval(String s) {
        java.util.Stack<Double> numbers = new java.util.Stack<>();
        java.util.Stack<Character> ops = new java.util.Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ' ') continue;

            // Number
            if (Character.isDigit(c) || c == '.') {
                StringBuilder sb = new StringBuilder();
                while (i < s.length() &&
                       (Character.isDigit(s.charAt(i)) || s.charAt(i) == '.')) {
                    sb.append(s.charAt(i)); i++;
                }
                i--;
                numbers.push(Double.parseDouble(sb.toString()));
            }

            // Left bracket
            else if (c == '(') {
                ops.push(c);
            }

            // Right bracket
            else if (c == ')') {
                while (ops.peek() != '(') {
                    numbers.push(apply(ops.pop(), numbers.pop(), numbers.pop()));
                }
                ops.pop(); // remove '('
            }

            // Operator
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && hasPriority(c, ops.peek())) {
                    numbers.push(apply(ops.pop(), numbers.pop(), numbers.pop()));
                }
                ops.push(c);
            }
        }

        while (!ops.isEmpty()) {
            numbers.push(apply(ops.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    // Operator precedence
    private static boolean hasPriority(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        return true;
    }

    // Apply operator
    private static double apply(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b;
        }
        return 0;
    }
}
