package org.mickey.data.structure.leetcode;


import java.util.Stack;

/**
 * @author mickey
 * @date 2020/6/9 10:14
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s == null) return false;
        if ("".equals(s)) return true;

        char c1 = s.charAt(0);
        if (c1 != '[' && c1!='(' && c1!='{') return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[' || c=='{' || c=='(')
                stack.push(c);
            else {
                if (stack.isEmpty()) return false;

                char topChar = stack.pop();
                if (c == ']' && topChar != '[')
                    return false;
                if (c == '}' && topChar != '{')
                    return false;
                if (c==')' && topChar !='(')
                    return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        ValidParentheses vb = new ValidParentheses();
        System.out.println(vb.isValid("{}"));
        System.out.println(vb.isValid("([{}])"));
        System.out.println(vb.isValid("([{"));
        System.out.println(vb.isValid("}]){[("));
        System.out.println(vb.isValid("anv"));
        System.out.println(vb.isValid("]]]"));
    }

}
