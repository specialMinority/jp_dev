import java.util.Scanner;

public class Star {
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int n = sc.nextInt();
//        for (int i = 0; i <= n; i++) {
//            System.out.print(" * ");
//            for (int j = 1; j <= i; j++) {
//                System.out.print(" * ");
//            }
//            for (int j = 1; j <= i; j++) {
//                System.out.print(" * ");
//            }
//            System.out.print("\n");
//        } // 직각삼각형
//
//        System.out.println("\n");
//
//        int diamond = 5;
//        for (int a = 0; a <= diamond; a++) {
//            System.out.print(" * ");
//            for (int b = 1; b <= a; b++) {
//                System.out.print(" * ");
//            }
//            for (int c = 1; c <= a; c++) {
//                System.out.print(" * ");
//            }
//            System.out.println();
//        }
//        for (int q = diamond - 1; q >= 0; q--) {
//            for (int w = 0; w <= q; w++) {
//                System.out.print(" * ");
//            }
//            for (int e = 1; e <= q; e++) {
//                System.out.print(" * ");
//            }
//            System.out.print("\n");
//        }

//        for (int a = 1; a <= 1; a++) {
//            System.out.print(" ? ");
//            System.out.print(" ? ");
//            System.out.print(" * ");
//            System.out.print(" ? ");
//            System.out.print(" ? ");
//        }
//        System.out.println();
//        for (int a = 1; a <= 1; a++) {
//            System.out.print(" ? ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" ? ");
//        }
//        System.out.println();
//        for (int a = 1; a <= 1; a++) {
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//        }
//        System.out.println();
//        for (int a = 1; a <= 1; a++) {
//            System.out.print(" ? ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" ? ");
//        }
//        System.out.println();
//        for (int a = 1; a <= 1; a++) {
//            System.out.print(" ? ");
//            System.out.print(" ? ");
//            System.out.print(" * ");
//            System.out.print(" ? ");
//            System.out.print(" ? ");
//        }

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for (int 세로 = 0; 세로 < n; 세로++) {
            for (int 가로 = 세로; 가로 < n - 1; 가로++) {
                System.out.print("   ");
            }
            for (int 가로 = 세로; 가로 > -1; 가로--){ // 세로값이 0일 때는 print가 한번만 세로값이 1일 때는 print가 두 번 되고 반복문이 종료되어야 한다.
                System.out.print(" * ");
            }
            for (int 가로 = 세로; 가로 > 0; 가로--){ // 세로값이 0일 때는 print가 0번 1일 때는 1번 2일 때는 2번
                System.out.print(" * ");
            }
            System.out.println();
        }
        for (int 세로 = 0; 세로 < n - 1; 세로++){
            for (int 가로 = 세로; 가로 > -1; 가로--){
                System.out.print("   ");
            }
            for (int 가로 = 세로; 가로 < n - 1; 가로++){
                System.out.print(" * ");
            }
            for (int 가로 = 세로; 가로 < n - 2; 가로++){
                System.out.print(" * ");
            }
            System.out.println();
        }

//        System.out.println();
//        System.out.println();
//
//        for (int a = 1; a <= 1; a++) {
//            System.out.print(" ? ");
//            System.out.print(" ? ");
//            System.out.print(" * ");
//            System.out.print(" ? ");
//            System.out.print(" ? ");
//        }
//        System.out.println();
//        for (int a = 1; a <= 1; a++) {
//            System.out.print(" ? ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" ? ");
//        }
//        System.out.println();
//        for (int a = 1; a <= 1; a++) {
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//        }
//        System.out.println();
//        for (int a = 1; a <= 1; a++) {
//            System.out.print(" ? ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" * ");
//            System.out.print(" ? ");
//        }
//        System.out.println();
//        for (int a = 1; a <= 1; a++) {
//            System.out.print(" ? ");
//            System.out.print(" ? ");
//            System.out.print(" * ");
//            System.out.print(" ? ");
//            System.out.print(" ? ");
//        }
    }
}


//30 1번만 쓰기 해결, <직각삼각형, 마름모, 원, 별> 모든 모형은 어떤 숫자를 넣어도 모양이 유지되어야 함