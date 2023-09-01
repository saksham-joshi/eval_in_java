
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
import java.util.HashMap;

class evaluater {

    HashMap<Character, Integer> preference = new HashMap<>();

    evaluater() {

        preference.put('^', 3);

        preference.put('/', 2);
        preference.put('*', 2);

        preference.put('+', 1);
        preference.put('-', 1);
    }

    public String evaluate(String str) {
        str = str.replace("÷", "/").replace("x", "*").replace("+-", "-").replace("-+", "-").replace("×","*") .strip();
        try {
            Queue<String> exp = postfix(str);
            System.out.println(exp);
            Stack<Double> stk = new Stack<>();

            int len = exp.size();
            for (int i = 0; i < len; i++) {

                String temp = exp.remove();

                if (is_number(temp)) {
                    stk.push(Double.parseDouble(temp));
                } else if (is_operator(temp)) {

                    double d2 = stk.pop();
                    double d1 = stk.pop();

                    switch (temp) {

                        case "+":
                            stk.push(d1 + d2);
                            break;

                        case "-":
                            stk.push(d1 - d2);
                            break;

                        case "*":
                            stk.push(d1 * d2);
                            break;

                        case "/":
                            stk.push(d1 / d2);
                            break;

                        case "^":
                            stk.push(Math.pow(d1, d2));
                            break;

                        default:
                            return "";
                    }
                }
            }
            return String.valueOf(stk.pop());
        } catch (Exception e) {
            return "";
        }
    }

    private Queue<String> postfix(String str) {

        Stack<Character> stk = new Stack<>();
        Queue<String> exp = new LinkedList<String>();

        for (int i = 0; i < str.length(); i++) {

            Character ch = str.charAt(i);

            if (is_number(ch) || (ch == '-' && (i == 0 || (i != 0 && is_operator(str.charAt(i - 1)))) ) ) {
                String x = extract_number_till_operator(str, i);
                exp.add(x);
                i += x.length()-1;

            }
            else if (ch == '('){
                stk.push(ch);
            }
            else if (is_operator(ch)) {
                try{
                    while (preference.get(ch) <= preference.get(stk.lastElement())) {
                        exp.add(String.valueOf(stk.pop()));
                    }
                }
                catch(Exception e){  }
                stk.push(ch);
            }
            else if(ch == ')'){
                try{
                    while(stk.lastElement() != '('){
                        exp.add(String.valueOf(stk.pop()));
                    }
                    stk.pop();
                }
                catch(Exception e){ }
            }

        }
        try {
            while (true) {
                exp.add(String.valueOf(stk.pop()));
            }
        } catch (Exception e) {
        }
        return exp;
    }

    private String extract_number_till_operator(String str , int index){
        String fin = String.valueOf(str.charAt(index++));
        try{
            for(int i=index ; i < str.length() ; i++){
                if(is_number(str.charAt(i))){
                    fin += String.valueOf(str.charAt(i));
                    continue;
                }
                break;
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return fin;
    }

    private boolean is_operator(Character ch) {
        if (ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^' || ch == '(') {
            return true;
        }
        return false;
    }

    private boolean is_operator(String ch) {
        if (ch.length() == 1) {
            return is_operator(ch.charAt(0));
        }
        return false;
    }

    private boolean is_number(Character ch) {
        if (ch == '0' || ch == '1' || ch == '2' || ch == '3' || ch == '4' || ch == '5' || ch == '6' || ch == '7'
                || ch == '8' || ch == '9' || ch == '.') {
            return true;
        }
        return false;
    }

    private boolean is_number(String str) {
        try {
            Double.parseDouble(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
