package com.company;

import java.util.Scanner;

public class IsJavaKeyword {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a word, and I will tell you if it is a Java keyword or not.");
        String word = scanner.nextLine();
        switch(word){
            case "abstract":
            case "continue":
            case "for":
            case "new":
            case "switch":
            case "assert":
            case "default":
            case "goto":
            case "package":
            case "synchronized":
            case "boolean":
            case "do":
            case "if":
            case "private":
            case "this":
            case "break":
            case "double":
            case "implements":
            case "protected":
            case "throw":
            case "byte":
            case "else":
            case "import":
            case "public":
            case "throws":
            case "case":
            case "enum":
            case "instanceof":
            case "return":
            case "transient":
            case "catch":
            case "extends":
            case "int":
            case "short":
            case "try":
            case "char":
            case "final":
            case "interface":
            case "static":
            case "void":
            case "class":
            case "finally":
            case "long":
            case "strictfp":
            case "volatile":
            case "const":
            case "float":
            case "native":
            case "super":
            case "while":
                System.out.println("Yes, this is a Java keyword.");
                break;
            default:
                System.out.println("No, this is not a Java keyword.");
        }
    }
}
