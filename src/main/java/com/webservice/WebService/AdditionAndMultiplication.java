package com.webservice.WebService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.util.Arrays;
import java.util.logging.Logger;

import static com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer.Vanilla.std;

@RestController
public class AdditionAndMultiplication {

    @RequestMapping(method = RequestMethod.GET, path = "/{operation}/{number1}/{number2}")
    public String index(@PathVariable String operation,@PathVariable String number1, @PathVariable String number2) {
        int[] copyOfNum1 = new int[number1.length()];
        int[] copyOfNum2 = new int[number2.length()];
        for (int i = 0; i < number1.length(); i++) {
            if(number1.charAt(i)<'0' || number1.charAt(i)>'9'){
                return "You must choose 2 numbers";
            }
            copyOfNum1[i] = Integer.parseInt(String.valueOf(number1.charAt(i)));
        }
        for (int j = 0; j < number2.length(); j++) {
            if(number2.charAt(j)<'0' || number2.charAt(j)>'9'){
                return "You must choose 2 numbers";
            }
            copyOfNum2[j] = Integer.parseInt(String.valueOf(number2.charAt(j)));
        }
        int[] sumNum;
        if(operation.equals("addition")){
            sumNum = sum(copyOfNum1,copyOfNum2);
        }
        else if (operation.equals("multiplication")){
            sumNum = multiply(copyOfNum1, copyOfNum2);

        }
        else {
            return "No such operation";
        }
        String sum = Arrays.toString(sumNum);
        sum = sum.replaceAll(", ", "");
        sum = sum.substring(1, sum.length() - 1);
        return sum;

    }

    public int[] sum(int[] firstNum, int[] secondNum) {
        int tempSum;
        int[] newNum;
        boolean firstBigger = true;
        int[] sum;

        if(firstNum.length>secondNum.length){
            newNum = paddingWithZeros(firstNum,secondNum);
            firstBigger = true;
        }
        else if(firstNum.length<secondNum.length){
            newNum = paddingWithZeros(secondNum,firstNum);
            firstBigger = false;
        }
        else {
            newNum = secondNum;
        }
        sum = new int[newNum.length + 1];
        int placeInSum = 0;
        for (int i = newNum.length - 1; i >= 0; i--) {
            if(firstBigger){
                tempSum = firstNum[i] + newNum[i];
            }
            else {
                tempSum = newNum[i] + secondNum[i];
            }
            if (tempSum < 10) {
                sum[placeInSum] = tempSum;
            } else {
                sum[placeInSum] = tempSum % 10;
                if (i > 0) {
                    if(firstBigger) {
                        firstNum[i - 1] += 1;
                    }
                    else {
                        newNum[i-1]+=1;
                    }
                } else {
                    sum[placeInSum + 1] = 1;
                }
            }
            placeInSum++;
        }

        sum = reverseAraay(sum);
        if (sum[0] == 0) {
            sum = Arrays.copyOfRange(sum, 1, sum.length);
        }
        return sum;

    }


    public int[] multiply(int[] firstNum, int[] secondNum) {
        int len1 = firstNum.length;
        int len2 = secondNum.length;
        int result[];
        if (firstNum[0] * secondNum[0] >= 10) {
            result = new int[len1 + len2];
        } else {
            result = new int[len1 + len2 - 1];
        }
        // Below two indexes are used to
        // find positions in result.
        int posNum1 = 0;
        int posNum2 = 0;

        // Go from right to left in num1
        for (int i = len1 - 1; i >= 0; i--) {
            int addAbove = 0;
            int num1 = firstNum[i];

            // To shift position to left after every
            // multipliccharAtion of a digit in num2
            posNum2 = 0;

            // Go from right to left in num2
            for (int j = len2 - 1; j >= 0; j--) {
                // Take current digit of second number
                int num2 = secondNum[j];

                // Multiply with current digit of first number
                // and add result to previously stored result
                // charAt current position.
                int sum = num1 * num2 + result[posNum1 + posNum2] + addAbove;

                // Carry for next itercharAtion
                addAbove = sum / 10;

                // Store result
                result[posNum1 + posNum2] = sum % 10;

                posNum2++;
            }

            // store carry in next cell
            if (addAbove > 0)
                result[posNum1 + posNum2] += addAbove;

            // To shift position to left after every
            // multipliccharAtion of a digit in num1.
            posNum1++;
        }
        int countZeros=0;
        while(countZeros<=result.length-1 && result[countZeros]==0){
            countZeros++;
        }
        if(countZeros==result.length){

            return new int[1];
        }

        result = reverseAraay(result);

        return result;
    }

    public int[] paddingWithZeros(int[] biggerNum, int[] smallerNum) {
        int[] newNum = new int[biggerNum.length];
        int howManyToPad = biggerNum.length - smallerNum.length;
        int j = 0;
            for (int i = 0; i < biggerNum.length; i++) {
                if (i < howManyToPad) {
                    newNum[i] = 0;
                } else {
                    newNum[i] = smallerNum[j];
                    j++;
                }
            }
            return newNum;
    }

    public int[] reverseAraay(int[]result){
        int i = result.length - 1;
        int j = 0;
        int[] returnedResult = new int[result.length];
        while (i >= 0) {
            returnedResult[j] = (result[i--]);
            j++;
        }
        return returnedResult;
    }
}
