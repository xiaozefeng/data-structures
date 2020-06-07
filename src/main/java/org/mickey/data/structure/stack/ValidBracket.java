package org.mickey.data.structure.stack;

/**
 * @author mickey
 * @date 2020/6/3 18:06
 */
public class ValidBracket {

    public boolean isValid(String s) {
        if (s == null || "".equals(s))
            return false;
        Stack<Character> stack = new ArrayStack<>();
        char c1 = s.charAt(0);
        if (c1 != '[' && c1 != '{' && c1 != '(') return false;
        for (int i = 0;i <s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                if (c== '}' && top  !='{') return false;
                if (c== ']' && top  !='[') return false;
                if (c== ')' && top  !='(') return false;
            }

        }

        return stack.isEmpty();

    }

    public static void main(String[] args) {
        ValidBracket vb = new ValidBracket();
        System.out.println(vb.isValid("{}"));
        System.out.println(vb.isValid("([{}])"));
        System.out.println(vb.isValid("([{"));
        System.out.println(vb.isValid("}]){[("));
        System.out.println(vb.isValid("anv"));
        // System.out.println(vb.isValid("{}[]()["));
        // System.out.println(vb.isValid("{[(}])"));
    }
}
