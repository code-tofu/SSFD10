package ibf2022.batch2.ssf.frontcontroller.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.util.Arrays;

import org.springframework.stereotype.Service;

@Service
public class CaptchaService {

    public static String[] generateCaptcha(){
        String[] captchaArr = new String[3];
        SecureRandom sR = new SecureRandom();
        captchaArr[0] = Integer.toString(sR.nextInt(49) + 1);
        switch(sR.nextInt(4)){
            case 1: 
                captchaArr[1] = "-";
                break;
            case 2: 
                captchaArr[1] = "/";
                break;                
            case 3: 
                captchaArr[1] = "*";
                break;                 
            default: //case 0
                captchaArr[1] = "+";
        }
        captchaArr[2] = Integer.toString(sR.nextInt(49) + 1);

        // IN CASE ZERO INCLUSIVE
        // while(captchaArr[1].equals("/") && captchaArr[2].equals('0')){
        //     captchaArr[2] = Integer.toString(sR.nextInt(50));
        // }
        System.out.println(Arrays.toString(captchaArr));
        return captchaArr;
    }

    public static boolean evaluateCaptcha(String[] captchaArr, BigDecimal input){
        BigDecimal answer = new BigDecimal("0.00");
        switch(captchaArr[1]){
            case "+": 
                answer = BigDecimal.valueOf(Double.parseDouble(captchaArr[0]) + Double.parseDouble(captchaArr[2]));
                break;  
            case "-": 
                answer = BigDecimal.valueOf(Double.parseDouble(captchaArr[0]) - Double.parseDouble(captchaArr[2]));
                break;
            case "/": 
                answer = BigDecimal.valueOf(Double.parseDouble(captchaArr[0]) / Double.parseDouble(captchaArr[2])).setScale(2, RoundingMode.HALF_UP);
                break;                
            case "*": 
                answer = BigDecimal.valueOf(Double.parseDouble(captchaArr[0]) * Double.parseDouble(captchaArr[2]));
                break;  
            default: //case 0
                return false;
        }

            //CONSOLE CHECK
            System.out.println(answer);
            System.out.println(input);

        if(answer.compareTo(input) == 0) return true;
        return false;
    }
    
}
