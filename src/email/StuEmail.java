package email;

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class StuEmail {


    public static void main(String[] args) {
        List<String> registeredEmails = new ArrayList<>();
        while(true){
            String code=generateRandomCode();
            String fourCode=generateRandomfourCode(code);


            User user = new User();
            System.out.println("\n请输入用户名：");
            Scanner sc=new Scanner(System.in);
            String name=sc.next();
            User.name=name;
            if ("0000".equals(name)) {
                System.out.println("程序结束，已注册邮箱列表：");
                for (String email : registeredEmails) {
                    System.out.println(email);
                }
                break;
            }
            String email=inputEmail();


            if (verifyEmail(fourCode, email,name)) {
                user.email = email;
                registeredEmails.add(email);
                System.out.println("注册成功！");
                System.out.println("用户名: " + user.name+"邮箱: " + user.email);
            } else {
                System.out.println("注册失败，请检查邮箱格式。");
            }
        }
    }

    public static String generateRandomCode(){
        //随机生成十位
        Random random1 = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            char code = (char) (random1.nextInt(26) + 'a'); // 生成随机小写字母
            stringBuilder.append(code);
        }
        return stringBuilder.toString();
    }

    public static String generateRandomfourCode(String code){
        //生成四位验证码
        int beginIndex;
        Random random2=new Random(3);
        beginIndex= random2.nextInt(7)+1;
        int endIndex=beginIndex+4;
        String fourCode=code.substring(beginIndex,endIndex);
        System.out.print("验证码是："+fourCode);
        return fourCode;
    }
    public static String inputEmail(){
        //输入邮箱
        System.out.print("\n请输入注册信息：用户名 验证码 邮箱 年龄\n");
        Scanner sc=new Scanner(System.in);
        String  email=sc.nextLine();
        return email;
    }

    public static boolean verifyEmail(String fourCode,String email,String name){
        String regex =  User.name + fourCode + "usstguy@usst\\.edu\\.cn+(1[0-9]|[2-9][0-9]|100)$";
        return email.matches(regex);}
}

class User{
    static String name;
    String email;
}
