package com.webservice.WebService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;

/**
 * controller class.
 */
@RestController
public class AdditionAndMultiplication {
    /**
     * result function. This function gets the mathematical operation and the numbers the user chose.
     * @param operation = mathematical operation
     * @param number1 = the first number
     * @param number2 = the second number
     * @return result = the operation result if it exists
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{operation}/{number1}/{number2}")
    public String result(@PathVariable String operation, @PathVariable String number1, @PathVariable String number2) {
        //allocate 2 arrays of integers at the size of the strings the user chose
        int[] copyOfNum1 = new int[number1.length()];
        int[] copyOfNum2 = new int[number2.length()];
        //copy the content of the string to the first number array
        for (int i = 0; i < number1.length(); i++) {
            // check if every letter in the string can be casted to integer
            // if not return the user am message that he has to choose 2 numbers.
            if(number1.charAt(i)<'0' || number1.charAt(i)>'9'){
                return "You must choose 2 numbers";
            }
            // if the entire string can be casted to integers copy the content of the string to the first number array
            copyOfNum1[i] = Integer.parseInt(String.valueOf(number1.charAt(i)));
        }
        //copy the content of the string to the second number array
        for (int j = 0; j < number2.length(); j++) {
            // check if every letter in the string can be casted to integer
            // if not return the user am message that he has to choose 2 numbers.
            if(number2.charAt(j)<'0' || number2.charAt(j)>'9'){
                return "You must choose 2 numbers";
            }
            // if the entire string can be casted to integers copy the content of the string to the second number array
            copyOfNum2[j] = Integer.parseInt(String.valueOf(number2.charAt(j)));
        }
        int[] resultNum;
        // if the user chose to add the 2 numbers
        if(operation.equals("addition")){
            resultNum = sum(copyOfNum1,copyOfNum2);
        }
        // if the user chose to multiply the 2 numbers
        else if (operation.equals("multiplication")){
            resultNum = multiply(copyOfNum1, copyOfNum2);

        }
        else {
            //if the user chose another operation
            return "No such operation";
        }
        //convert the result array to a string.
        String result = Arrays.toString(resultNum);
        result = result.replaceAll(", ", "");
        result = result.substring(1, result.length() - 1);
        return result;
    }

    /**
     * sum function. This function gets 2 array of numbers and adds them.
     * @param firstNum =  the first number presented as an array
     * @param secondNum = the second number presented as an array
     * @return sum = the sum of the 2 numbers presented as an array.
     */
    public int[] sum(int[] firstNum, int[] secondNum) {
        int tempSum;
        int[] newNum;
        // firstBigger is set to true- assuming the first array is longer than the second array.
        // if the second array is longer it will be set to false.
        boolean firstBigger = true;
        int[] sum;
        if(firstNum.length>secondNum.length){
            // if the first array's length is bigger than the second array's length, pad the second array with zeros at the start.
            // in order to add them comfortably. newNum is the second array after padding.
            newNum = paddingWithZeros(firstNum,secondNum);
        }
        else if(firstNum.length<secondNum.length){
            // if the second array's length is bigger than the first array's length, pad the first array with zeros at the start.
            // in order to add them comfortably. newNum is the first array after padding.
            newNum = paddingWithZeros(secondNum,firstNum);
            //set firstBigger to false since the second array is longer than the furst array.
            firstBigger = false;
        }
        else {
            // if the length of the 2 arrays is equal.
            newNum = secondNum;
        }
        sum = new int[newNum.length + 1];
        int placeInSum = 0;
        // going thorough the 2 arrays and add them
        for (int i = newNum.length - 1; i >= 0; i--) {
            // if firstBigger is true the newNum array is the second array.
            if(firstBigger){
                tempSum = firstNum[i] + newNum[i];
            }
            else {
                // if firstBigger is false the newNum array is the first array.
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

        // reverse the sum array.
        sum = reverseArray(sum);
        if (sum[0] == 0) {
            sum = Arrays.copyOfRange(sum, 1, sum.length);
        }
        return sum;

    }


    /**
     * multiply function. This function gets 2 array of numbers and multiplies them.
     * @param firstNum =  the first number presented as an array
     * @param secondNum = the second number presented as an array
     * @return sum = the sum of the 2 numbers presented as an array.
     */
    public int[] multiply(int[] firstNum, int[] secondNum) {
        int len1 = firstNum.length;
        int len2 = secondNum.length;
        int mult[];
        // check what should be the size of the mult array.
        if (firstNum[0] * secondNum[0] >= 10) {
            mult = new int[len1 + len2];
        } else {
            mult = new int[len1 + len2 - 1];
        }
        // posNum1 and posNum2 are used to find the positions in the multiply result.
        int posNum1 = 0;
        int posNum2;

        // going thorough the first array
        for (int i = len1 - 1; i >= 0; i--) {
            int addAbove = 0;
            int num1 = firstNum[i];
            //shift position to left after every multiplication of a digit in the second number.
            posNum2 = 0;
            // going thorough the second array
            for (int j = len2 - 1; j >= 0; j--) {
                // Take current digit of second number
                int num2 = secondNum[j];

                // Multiply with current digit of first number and add result to previously stored result and the above number.
                int sum = num1 * num2 + mult[posNum1 + posNum2] + addAbove;
                // add above for next itaration
                addAbove = sum / 10;
                // Store result
                mult[posNum1 + posNum2] = sum % 10;
                posNum2++;
            }

            // store the addAbove in next cell
            if (addAbove > 0)
                mult[posNum1 + posNum2] += addAbove;

            // shift position to left after every multiplication of a digit in the first number.
            posNum1++;
        }
        // if the multiplication result is zero should return 0.
        int countZeros=0;
        while(countZeros<=mult.length-1 && mult[countZeros]==0){
            countZeros++;
        }
        if(countZeros==mult.length){
            return new int[1];
        }
        // reverse the mult array.
        mult = reverseArray(mult);

        return mult;
    }

    /**
     * padding with zeros function. This function gets 2 array of numbers and pads the smaller one with zeros at the beginning.
     * @param biggerNum =  the longer array
     * @param smallerNum = the shorter array
     * @return newNum = the smaller array after padding
     */
    public int[] paddingWithZeros(int[] biggerNum, int[] smallerNum) {
        //allocate the new array with the size of the big array.
        int[] newNum = new int[biggerNum.length];
        // the number of zeros to pad equals to the difference between the length of the 2 arrays.
        int howManyToPad = biggerNum.length - smallerNum.length;
        int j = 0;
            for (int i = 0; i < biggerNum.length; i++) {
                if (i < howManyToPad) {
                    //pad with zeros
                    newNum[i] = 0;
                } else {
                    //after padding copy the smaller array to the continuation of the new array
                    newNum[i] = smallerNum[j];
                    j++;
                }
            }
            return newNum;
    }

    /**
     * reverse array function.
     * @param result =  the original array
     * @return returnedResult = the array after reversing it.
     */
    public int[] reverseArray(int[]result){
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
