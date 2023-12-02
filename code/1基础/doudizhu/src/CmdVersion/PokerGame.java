package CmdVersion;

import java.util.*;

public class PokerGame {
    // 1.准备牌
    // 牌盒,始终是同一副牌盒(默认一次只开一盘游戏)
    //  numToPoker存储的是牌的序号对牌的映射
    static HashMap<Integer, String> numToPoker = new HashMap<>();
    static ArrayList<Integer> pokerBox = new ArrayList<>();

    // 初始化牌盒，随着类加载而加载，只加载一次
    static {
        // "♦", "♣", "♥", "♠"
        // "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"
        String[] color = {"♦", "♣", "♥", "♠"};
        String[] number = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

        // 牌的序号
        int serialNumber = 1;
        for (String n : number) {
            for (String c : color) {
                pokerBox.add(serialNumber);
                numToPoker.put(serialNumber, n + c);
                serialNumber++;
            }
        }
        pokerBox.add(serialNumber);
        numToPoker.put(serialNumber, "小王");
        serialNumber++;
        pokerBox.add(serialNumber);
        numToPoker.put(serialNumber, "大王");
        serialNumber++;
    }

    public PokerGame() {
        System.out.println("==========开始游戏==========");

        // 2.洗牌
        shufflePoker();

        // 3.发牌: 3个人+底牌
        TreeSet<Integer> lord = new TreeSet<>();
        TreeSet<Integer> player1 = new TreeSet<>();
        TreeSet<Integer> player2 = new TreeSet<>();
        TreeSet<Integer> player3 = new TreeSet<>();
        for (int i = 0; i < pokerBox.size(); i++) {
            int serialNum = pokerBox.get(i);
            // 底牌
            if (i <= 2) {
                lord.add(serialNum);
                continue;
            }
            // 分别为3个人发牌
            if (i % 3 == 0) {
                player1.add(serialNum);
            } else if (i % 3 == 1) {
                player2.add(serialNum);
            } else {
                player3.add(serialNum);
            }
        }

        // 4.看牌
        lookPoker("lord", lord);
        lookPoker("aaa", player1);
        lookPoker("bbb", player2);
        lookPoker("ccc", player3);
    }

    // 洗牌
    public void shufflePoker() {
        Collections.shuffle(pokerBox);
    }

    // 看牌
    public void lookPoker(String name, TreeSet<Integer> ts) {
        System.out.println(name + ": ");
        StringJoiner sj = new StringJoiner(", ", "[", "]");
        // 遍历牌中的所有序号，用序号取找牌
        for (Integer serialNumber : ts) {
            sj.add(numToPoker.get(serialNumber));
        }
        System.out.println(sj);
    }
}
