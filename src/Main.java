import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws Exception {


        boolean IsRomanNum1 = false;
        boolean IsRomanNum2 = false;
        Scanner in = new Scanner(System.in);
        System.out.print("Input a expression: ");
        String exp = in.nextLine();
        String[] nums = split(exp);

/*        for (String j : nums){
            System.out.println(j);
        }*/

        for (Object s : RomeNums.values())
        {


            if (nums[0].equals(s.toString()))
            {
                IsRomanNum1 = true;
                nums[0] = String.valueOf(RomeNums.valueOf(nums[0]).toInt());

            }

            if (nums[2].equals(s.toString()))
            {
                IsRomanNum2 = true;
                nums[2] = String.valueOf(RomeNums.valueOf(nums[2]).toInt());

            }
        }
        if ((!IsRomanNum1 && IsRomanNum2) || (IsRomanNum1 && !IsRomanNum2)) throw new Exception("используются одновременно разные системы счисления ");


        if(nums.length > 3) throw new Exception("Недопустимое выражение");

        Byte num1=0, num2=0;
        String operator = nums[1];

        try {
            num1 = Byte.valueOf(nums[0]);
            num2 = Byte.valueOf(nums[2]);
        } catch (NumberFormatException e) {
            System.err.println("Неправильный формат строки!");
        }

        boolean MoreThenTen = (num1 > 10 || num2 > 10);

        if(MoreThenTen) throw new Exception("Число больше допустимого");

        int result = operation(num1, num2, operator);

        if ((IsRomanNum1 && IsRomanNum2) && (result < 1) ) throw new Exception("в римской системе нет отрицательных чисел");

        if (IsRomanNum1 && IsRomanNum2){
            System.out.println((replace(result)));
        }else{
            System.out.println(result);
        }



    }


    public static String[] split(String exp) {
        String[] nums = exp.split(" ");
        return nums;
    }


    public static int operation(int num1, int num2, String operator) throws Exception{
        switch (operator){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1*num2;
            case "/":
                return num1/num2;
            default:
                throw new Exception("Знак не опознан");

        }
    }


    public static StringBuilder replace(int result) {
        int one = 0, five = 0, ten = 0;
        StringBuilder RomeResult = new StringBuilder();

        ten = result / 10;



      for (int i = 0; i < ten; i++){

          if (ten >= 5){
              if (ten == 10){
                  RomeResult.append("C");
                  break;
              }
              if (ten == 9){
                  ten -= 9;
                  RomeResult.append("XC");
                  continue;
              }
              ten-= 5;
              RomeResult.append("L");


          }

          if (ten == 4){
              ten-=4;
              RomeResult.append("XL");
              continue;
          }
          if (ten >= 1){
              RomeResult.append("X");
          }

      }


      one = result % 10;

      for(int i = 0; i<one; i++){
            if (one >= 5){
                if (one == 9){
                    one -= 9;
                    RomeResult.append("IX");
                    continue;
                }
                one-= 5;
                RomeResult.append("V");


            }

            if (one == 4){
                one-=4;
                RomeResult.append("IV");
                continue;
            }
            if (one >= 1){
                RomeResult.append("I");
            }

        }










    return RomeResult;
    }




}

