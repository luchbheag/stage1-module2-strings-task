package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import com.epam.mjc.MethodSignature.Argument;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        StringTokenizer tokenizer = new StringTokenizer(signatureString, "()");
        StringTokenizer methodSignatureWithoutArguments = new StringTokenizer(tokenizer.nextToken(), " ");
        String argumentsAsString = tokenizer.hasMoreTokens() ? tokenizer.nextToken() : "";
        String modifier = methodSignatureWithoutArguments.countTokens() == 3 ? methodSignatureWithoutArguments.nextToken() : "";
        String returnType = methodSignatureWithoutArguments.nextToken();
        String name = methodSignatureWithoutArguments.nextToken();

        MethodSignature methodSignature = argumentsAsString.isEmpty() ? new MethodSignature(name) : new MethodSignature(name, getArgumentsFromString(argumentsAsString));
        methodSignature.setReturnType(returnType);
        if (!modifier.isEmpty())
            methodSignature.setAccessModifier(modifier);

        return methodSignature;
    }

    public List<Argument> getArgumentsFromString(String argumentsAsString) {
        List<Argument> arguments = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(argumentsAsString, ", ");
        while (tokenizer.hasMoreTokens()) {
            String type = tokenizer.nextToken();
            String name = tokenizer.nextToken();
            Argument argument = new Argument(type, name);
            arguments.add(argument);
        }
        return arguments;
    }
}
