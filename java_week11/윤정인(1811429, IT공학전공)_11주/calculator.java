import javax.swing.*;
import javax.swing.plaf.DesktopPaneUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.math.BigDecimal;
import java.util.*;

public class calculator {
    public static void main(String[] args) {
        new CalculatorFrame("Calculator: 윤정인");
    }
}
class CalculatorFrame extends Frame {
    TextArea display;//Display
   String[] value = {"1/x", "-/+", "<-", "CE",
		   			"%", "/", "*", "-",
		   			"7", "8", "9", "+",
		   			"4", "5", "6", "(",
		   			"1", "2", "3", ")",
		   			" ", "0", ".", "="};

    public CalculatorFrame(String title){
        super(title);//Calculator name
        setMainFrame();//Basic window settings
        setButton();//Set button
    }

    private void setMainFrame(){
        this.setLayout(null);//Set the window layout to be empty
        this.setBounds(400,200,720,520);//Set window size
        this.setResizable(false);//Whether the window can be resized
        this.setVisible(true);//Window visible
        //Close the window to listen for events
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
        //Set the display
        display = new TextArea("0",8,52,3);//Text area
        display.setBounds(20, 40, 490, 120);
        display.setEditable(false);
        display.setBackground(new Color(230, 230, 230));
        display.setFont(new Font("Youyuan", Font.BOLD, 15));
        this.add(display);
    }
    private void setButton(){
        for(int i=0; i<value.length;i++){
            addButton(value[i],20+122*(i%4), 165+48*(int)(Math.floor((i/4))));
        }
    }

    private void addButton(String val, int x, int y){
        Button button = new Button(val);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setBounds(x,y,117,43);
        button.setFont(new Font("Standard Kaiti", Font.BOLD, 15));
        //Add click event
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String[] exceptMessages={"NaN", "Infinity"};
                Button button = (Button)e.getSource();//Get the clicked button object
                String value = button.getLabel();//The value of the button
                String expression = display.getText();//Get the expression of the display
                int len = expression.length();//Length of expression
                switch(value){
                    case "CE":
                        display.setText("0");
                        break;
                    case "<-":
                        boolean flag = false;
                        for(int i=0;i<exceptMessages.length;i++){
                            if(!expression.equals(exceptMessages[i]))
                                continue;
                            flag = true;
                            break;
                        }
                        if(len==1) expression="0";
                        else if(flag==true){
                            //There is infinity, NaN and other data
                            expression="0";
                        } else if(len>0 && expression.lastIndexOf(" ")!=len-1){
                            expression=expression.substring(0, len-1);
                        } else if(len>0 && expression.substring(len-4, len-3).matches("[0-9]+")){
                            expression = expression.substring(0, len-3);
                        } else if(len>0){
                            expression = expression.substring(0, len-2);
                        }
                        display.setText(expression);
                        break;
                    case "-/+":
                    case "1/x":
                        String num = "0";
                        if(expression.lastIndexOf(" ")!=len-1 && (hasNums(expression, "+")>=1
                                || hasNums(expression, "-")>=1 || hasNums(expression, "*")>=1
                                || hasNums(expression, "/")>=1)){
                            //If there is another operation symbol in front of the input symbol, that is, the operation is not the first operation, then only a group of numbers close to it will be operated
                            num = expression.substring(expression.lastIndexOf(" "), len);
                            double number1 = Double.parseDouble(num);
                            expression = expression.substring(0, expression.lastIndexOf(" "));
                            if(value.equals("-/+")) {
                                number1 = 0 - number1;
                            }else{
                                number1 = 1/number1;
                                BigDecimal b = new BigDecimal(number1);
                                number1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            }
                            expression += " " + number1;
                        }else{//When it is the first operation
                            double number1 = Double.parseDouble(expression);
                            if(value.equals("-/+")) {
                                number1 = 0 - number1;
                            }else{
                                number1 = 1/number1;
                                BigDecimal b = new BigDecimal(number1);
                                number1 = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                            }
                            expression = "" + number1;
                        }

                        display.setText(expression);
                        break;
                    case "%":
                    case "+":
                    case "-":
                    case "*":
                    case "/":
                        if(expression.lastIndexOf(" ")==len-1 && !expression.substring(len-2, len-1).equals("(")
                                && !expression.substring(len-2, len-1).equals(")")){ //If the front is not parentheses but other symbols, remove this symbol and become a new symbol
                            expression = expression.substring(0, len-3);
                        } else if(len>1 && expression.substring(len-2, len-1).equals("(")){ //If the front is a left parenthesis, add 0 before the symbol
                            expression+="0";
                        }
                        expression+=" "+value+" ";
                        display.setText(expression);
                        break;
                    case "(":
                    case ")":
                        if(value.equals(")") && hasNums(expression, "(")<=hasNums(expression, ")")) break;
                        else if(value.equals(")") && expression.lastIndexOf(" ")==len-1 && expression.lastIndexOf("(")!=len-2){
                            //Like 2 + (5 +, automatically add a number
                            expression=autoAdd(expression);
                            len+=1;
                        }
                        if(expression.equals("0")) expression="";
                        else if(value=="(" && expression.lastIndexOf(" ")!=len-1) expression+=" *";//5*(2+3) add an * by default
                        if(expression.lastIndexOf(" ")==len-1) expression=expression.substring(0, len-1); //Clear the preceding spaces
                        if(len>1 && value.equals("(") && expression.lastIndexOf(")")==len-2){
                            //Like (a+b)*(c+d) plus one in the middle*
                            expression+=" *";
                            len+=2;
                        }
                        expression+=" "+value+" ";
                        display.setText(expression);
                        break;
                    case ".":
                        if(expression.lastIndexOf(")")!=-1 && expression.lastIndexOf(")")==len-2) expression+="* 0"+value;//The brackets automatically become * method
                        else if(expression.lastIndexOf(" ")==len-1) expression+="0"+value;
                        else if(expression.lastIndexOf(".")!=-1 && expression.lastIndexOf(".")>expression.lastIndexOf(" ")) break;//Enter 2 consecutively.
                        else expression+=value;
                        display.setText(expression);
                        break;
                    case "=":
                        if(hasNums(expression, "(")>hasNums(expression,")")){
                            // missing gapNum brackets
                            expression+=expression.lastIndexOf(" ")==len-1?"+ 0":" + 0";
                            int gapNum=hasNums(expression,"(")-hasNums(expression,")");
                            for(int i=0;i<gapNum;i++){
                                expression+=" ) ";
                            }

                        }
                        else if(expression.lastIndexOf(" ")==len-1 && expression.lastIndexOf(")")!=len-2){
                            //When the last one is an operator, 0 or 1 is added after the expression by default
                            expression=autoAdd(expression);
                        }
                        cal(expression);
                        break;
                    default:
                        //excptMessages
                        if(expression.substring(len-1).matches("[a-zA-Z]+")){
                            expression="0";
                        }
                        //digital
                        if(expression.equals("0")) expression="";
                        if(expression.lastIndexOf(" ")==len-1 && expression.lastIndexOf(")")==len-2) expression+="* ";
                        expression+=value;
                        display.setText(expression);
                }
            }
        });
        this.add(button);
    }

    // calculate
    private void cal(String str){
        //Get new expression
        LinkedList<String> expre=getNewExpre(str.split(" "));
//        for(int i=0;i<expre.size();i++){
//            System.out.println(expre.get(i)+",");
//        }
//        System.out.println();
        for(var i=0;i<expre.size();i++){
            var val=expre.get(i);
            switch(val){
                case "-":
                case "+":
                case "*":
                case "/":
                case "%":
                case "^":
                    expre.set(i-2,String.valueOf(operation2(expre.get(i-2), expre.get(i-1), val)));
                    expre.remove(i-1);
                    expre.remove(i-1);
                    i-=2;
                    break;
                default:
                    break;
            }
        }

        String result = expre.get(0);
        display.setText(result);//Return to zero
    }


    //Get new expression
    private LinkedList<String> getNewExpre(String[] expression){
//        for(int i=0;i<expression.length;i++){
//            System.out.println(expression[i]+",");
//        }
//        System.out.println("flag");
        LinkedList<String> symbols=new LinkedList<>();//Stack of symbols
        LinkedList<String> newExpre=new LinkedList<>();//Store the stack of new expressions
        for(int i=0;i<expression.length;i++){
            String val=expression[i];
            if(val.equals("")) continue;
//            System.out.println("position1" + " " + i +" " + symbols);
//            System.out.println("position2" + " " + i +" " + newExpre);
            switch(val){
                case "(":symbols.add(val); break;
                case ")":
                    boolean isOK=true;
                    while(isOK){
                        String _symbol=symbols.pollLast();
                        if(_symbol.equals("(")) isOK=false;
                        else newExpre.add(_symbol);
                    };
                    break;
                case "-/+":
                case "%":
                case "+":
                case "-":
                case "*":
                case "/":
                    if(symbols.size()==0){//If the symbol stack is empty, then push to the stack
                        symbols.add(val);
                    } else if(compareSymbols(val,symbols.get(symbols.size()-1))){//If the priority of the stack is higher than the top symbol, then the stack
                        symbols.add(val);
                    } else {
                        while(symbols.size()>0 && !compareSymbols(val,symbols.get(symbols.size()-1))){//Otherwise, all pops out of the stack until it encounters a priority lower than itself
                            newExpre.add(symbols.pollLast());
                        }
                        symbols.add(val);
                    }
                    break;
                default:
                    newExpre.add(val);
            }
        }
        while (symbols.size()>0){
            newExpre.add(symbols.pollLast());
        }
        return newExpre;
    }

    //Comparison operator priority
    private boolean compareSymbols(String newSymbol, String existSymbol){
        if(getPriority(newSymbol)>getPriority(existSymbol)){
            return true;
        }
        else{
            return false;
        }
    }
    //The priority of the operator
    private int getPriority(String string){
        int order;
        switch(string){
            case "+":
            case "-":
                order = 1;
                break;
            case "%":
            case "*":
            case "/":
                order = 2;
                break;
            default:
                order = -1;
                break;
        }
        return order;
    }

    //Calculation method
    //Binocular operation
    private int open = 0;// Determine whether the input is correct
    private double operation2(String str1, String str2, String str3){
        double result=0;
        double num1,num2;
        num1 = Double.parseDouble(str1);
        num2 = Double.parseDouble(str2);
        switch (str3){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
            case "%":
                result = num1 % num2;
                break;
            case "^":
                result = Math.pow(num1, num2);
                break;
        }
        return result;
    }


    // Determine how many vals are in str
    private int hasNums(String str, String val){
        int nums = 0;
        while(str.indexOf(val)!=-1){
            nums+=1;
            str=str.substring(str.indexOf(val)+1);
        }
        return nums;
    }

    //Common errors in expressions, automatically add 0 or 1 at the end
    private String autoAdd(String expression){
        String symbol=expression.substring(expression.length()-2,expression.length()-1);
        switch(symbol){
            case "+":
            case "-":
                expression+="0";
                break;
            case "!":
                break;
            case "*":
            default:
                expression+="1";
        }
        return expression;
    }
}

