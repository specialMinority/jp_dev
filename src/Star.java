public class Star {
    public static void main(String[] args) {

        for (int i = 0; i <= 30; i++) {
            System.out.print(" * ");
            for (int j = 1; j <= i; j++) {
                System.out.print(" * ");
            }
            System.out.print("\n");
        } // 직각삼각형
    }
}


//30 1번만 쓰기 해결, <직각삼각형, 마름모, 원, 별> 모든 모형은 어떤 숫자를 넣어도 모양이 유지되어야 함