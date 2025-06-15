package pvp;

import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.Random;


//선공 기회는 랜덤, 턴제 게임, 공격 시 최대 50의 데미지를 입힐 수 있다
//damage가 강한 스킬일 수록 miss 발생 확률이 커진다, 스킬은 총 3가지(캐릭터 다양성은 나중에 추가)
//적의 공격 방식은 랜덤
class Skill {
    String name;
    int damage;
    int accuracy;

    Skill(String name, int damage, int accuracy) {
        this.name = name;
        this.damage = damage;
        this.accuracy = accuracy;
    }

    boolean hit() {
        return new java.util.Random().nextInt(100) < accuracy;
    }
}

class Character {
    String name;
    int hp;

    Character(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    void takeDamage(int damage) {
        hp = hp - damage;
    }

    void ultimate(Character target){
        //궁극기
    }

    void mainSkill(Character target){
        //주력기
    }

    void normalSkill(Character target){
        //확정기
    }

    boolean alive() {
        return hp > 0;
    }
}

class Mage extends Character {
    Skill meteor = new Skill("메테오", 200, 5);
    Skill fireball = new Skill("파이어볼", 80, 20);
    Skill rapidFire = new Skill("연속발사", 20, 100);

    Mage(String name) {
        super(name, 100);
    }

    @Override
    void ultimate(Character target) {
        System.out.println(name + "가 메테오를 시전합니다!");
        if (meteor.hit()) {
            target.takeDamage(meteor.damage);
            System.out.println("명중! " + target.name + "의 체력: " + target.hp);
        } else {
            System.out.println("실패!");
        }
    }

    @Override
    void mainSkill(Character target) {
        System.out.println(name + "가 파이어볼을 시전합니다!");
        if (fireball.hit()) {
            target.takeDamage(fireball.damage);
            System.out.println("명중! " + target.name + "의 체력: " + target.hp);
        } else {
            System.out.println("실패!");
        }
    }

    @Override
    void normalSkill(Character target) {
        System.out.println(name + "가 연속발사를 시전합니다!");
        target.takeDamage(rapidFire.damage);
        System.out.println("명중! " + target.name + "의 체력: " + target.hp);
    }
}

class SwordMaster extends Character {
    Skill swordDance = new Skill("환영검무", 60, 35);
    Skill drawSlash = new Skill("발도", 40, 70);
    Skill swordArt = new Skill("리귀검술", 17, 100);

    SwordMaster(String name) {
        super(name, 120);
    }

    @Override
    void ultimate(Character target) {
        System.out.println(name + "가 환영검무를 사용합니다!");
        if (swordDance.hit()) {
            target.takeDamage(swordDance.damage);
            System.out.println("명중! " + target.name + "의 체력: " + target.hp);
        } else {
            System.out.println("실패!");
        }
    }

    @Override
    void mainSkill(Character target) {
        System.out.println(name + "가 발도를 사용합니다!");
        if (drawSlash.hit()) {
            target.takeDamage(drawSlash.damage);
            System.out.println("명중! " + target.name + "의 체력: " + target.hp);
        } else {
            System.out.println("실패!");
        }
    }

    @Override
    void normalSkill(Character target) {
        System.out.println(name + "가 리귀검술을 사용합니다!");
        target.takeDamage(swordArt.damage);
        System.out.println("명중! " + target.name + "의 체력: " + target.hp);
    }
}

class gunner extends Character {
    Skill quantumBomb = new Skill("양자폭탄", 90, 10);
    Skill raserBazooka = new Skill("레이저바주카", 50, 40);
    Skill gatlingGun = new Skill("게틀링건", 15, 100);

    gunner(String name) {
        super(name, 120);
    }

    @Override
    void ultimate(Character target) {
        System.out.println(name + "가 양자폭탄을 발사합니다!");
        if (quantumBomb.hit()) {
            target.takeDamage(quantumBomb.damage);
            System.out.println("명중! " + target.name + "의 체력: " + target.hp);
        } else {
            System.out.println("실패!");
        }
    }

    @Override
    void mainSkill(Character target) {
        System.out.println(name + "가 레이저바주카를 발사합니다!");
        if (raserBazooka.hit()) {
            target.takeDamage(raserBazooka.damage);
            System.out.println("명중! " + target.name + "의 체력: " + target.hp);
        } else {
            System.out.println("실패!");
        }
    }

    @Override
    void normalSkill(Character target) {
        System.out.println(name + "가 게틀링건을 발사합니다!");
        target.takeDamage(gatlingGun.damage);
        System.out.println("명중! " + target.name + "의 체력: " + target.hp);
    }
}

class Priest extends Character {
    Skill judgmentMaul = new Skill("참회의 망치", 50, 60);
    Skill deflectingWall = new Skill("디플픽트 월", 35, 85);
    Skill bladePure = new Skill("순백의 칼날", 10, 100);

    Priest(String name) {
        super(name, 150);
    }

    @Override
    void ultimate(Character target) {
        System.out.println(name + "가 참회의 망치를 시전합니다!");
        if (judgmentMaul.hit()) {
            target.takeDamage(judgmentMaul.damage);
            System.out.println("명중! " + target.name + "의 체력: " + target.hp);
        } else {
            System.out.println("실패!");
        }
    }

    @Override
    void mainSkill(Character target) {
        System.out.println(name + "가 디플렉트 월을 시전합니다!");
        if (deflectingWall.hit()) {
            target.takeDamage(deflectingWall.damage);
            System.out.println("명중! " + target.name + "의 체력: " + target.hp);
        } else {
            System.out.println("실패!");
        }
    }

    @Override
    void normalSkill(Character target) {
        System.out.println(name + "가 순백의 칼날을 시전합니다!");
        target.takeDamage(bladePure.damage);
        System.out.println("명중! " + target.name + "의 체력: " + target.hp);
    }
}

public class Game {
    public static void main(String[] args) {
        System.out.println("마법사: 데미지가 매우 강하지만 명중률이 낮고 체력이 낮습니다.");
        System.out.println("거너: 데미지는 강한 편이지만 명중률이 다소 낮고 체력은 낮은 편입니다.");
        System.out.println("소드마스터: 데미지, 명중률, 체력의 밸런스가 좋습니다.");
        System.out.println("프리스트: 데미지가 약하지만 명중률이 높고 체력이 높습니다.");

        Random rand = new Random();

        int turn = 0;
        // 0 또는 1을 무작위로 생성

        // 적 캐릭터 무작위 생성
        Character enemy;
        int enemyCharacter = rand.nextInt(4);
        if (enemyCharacter == 0) {
            enemy = new Mage("enemy");
        } else if (enemyCharacter == 1) {
            enemy = new gunner("enemy");
        } else if (enemyCharacter == 2) {
            enemy = new Priest("enemy");
        } else {
            enemy = new SwordMaster("enemy");
        }

        Scanner sc = new Scanner(System.in);
        // 사용자 캐릭터 선택
        System.out.println();
        System.out.println("캐릭터를 고르세요!");
        System.out.println("1. 마법사  2. 거너  3. 프리스트  4. 소드마스터");
        String selected = sc.nextLine();

        Character player;
        if (selected.equals("1")) {
            player = new Mage("player");
        } else if (selected.equals("2")) {
            player = new gunner("player");
        } else if (selected.equals("3")) {
            player = new Priest("player");
        } else {
            player = new SwordMaster("player");
        }
        boolean playerTurn = rand.nextBoolean();
        System.out.println(playerTurn ? "플레이어가 선공합니다!" : "적이 선공합니다!");
        while (player.alive() && enemy.alive()) {
            if (playerTurn) {
                System.out.println();
                System.out.println("플레이어가 공격할 차례입니다!");
                System.out.println("공격 방식을 정해주세요");
                if (player instanceof Mage) {
                    System.out.println("1. 메테오(damage:200) 2. 파이어볼(damage:80)  3. 연속발사(damage:20)");
                } else if (player instanceof gunner) {
                    System.out.println("1. 양자폭탄(damage:90) 2. 레이저바주카(damage:50)  3. 게틀링건(damage:15)");
                } else if (player instanceof Priest) {
                    System.out.println("1. 참회의 망치(damage:50) 2. 디플렉트 월(damage:35)  3. 순백의 칼날(damage:10)");
                } else if (player instanceof SwordMaster) {
                    System.out.println("1. 환영검무(damage:60) 2. 발도(damage:40)  3. 리귀검술(damage:17)");
                }
                Scanner s = new Scanner(System.in);
                int choice = sc.nextInt();
                if (choice == 1) {
                    player.ultimate(enemy);
                } else if (choice == 2) {
                    player.mainSkill(enemy);
                } else if (choice == 3) {
                    player.normalSkill(enemy);
                }
            } else {
                int enemyAttack = rand.nextInt(3);
                System.out.println();
                System.out.println("적이 공격할 차례입니다!");
                if (enemyAttack == 0) {
                    enemy.ultimate(player);
                } else if (enemyAttack == 1) {
                    enemy.mainSkill(player);
                } else {
                    enemy.normalSkill(player);
                }
            }
            playerTurn = !playerTurn;
        }
        if (!player.alive()) {
            System.out.println();
            System.out.println("플레이어 패배!");
        } else if (!enemy.alive()) {
            System.out.println();
            System.out.println("플레이어 승리!");
        }
    }
}
