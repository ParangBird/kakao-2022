package kakao;

import kakao.entity.*;
import kakao.util.AuthKeyManager;
import kakao.util.Connection;
import kakao.util.JsonParser;

import java.util.ArrayList;

public class Main {
    public static Connection connection = Connection.getInstance();
    public static AuthKeyManager authKeyManager = AuthKeyManager.getAuthKeyManager();
    public static JsonParser jsonParser = new JsonParser();
    public static void solve1(){
        System.out.println("##########solve1 start##########");
        authKeyManager.createAuthKey(1);
        String authKey = authKeyManager.getAuthKey();
        System.out.println("authKey = " + authKey);
        int userGrade[] = new int[31];
        for(int i=1;i<=30;i++){
            userGrade[i] = 4500;
        }

        int time = 1;
        while(time <= 595){
            System.out.println("time = " + time);
            ArrayList<GameResult> gameResults = jsonParser.getGameResultArray(connection.gameResult());
            ArrayList<UserInfo> userInfos = jsonParser.getUserInfoArray(connection.userInfo());

            for(int i=0;i<gameResults.size();i++){
                int winId = gameResults.get(i).win;
                int loseId = gameResults.get(i).lose;
                userGrade[winId]++;
                userGrade[loseId]--;
            }
            ArrayList<WaitingLine> waitingLines = jsonParser.getWaitingLineArray(connection.waitingLine());
            ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();
            for(int i=0;i<waitingLines.size();i++){
                if(i != 0 && i % 2 == 0){
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(waitingLines.get(i-1).id);
                    pair.add(waitingLines.get(i).id);
                    pairs.add(pair);
                }
            }
            Match match = jsonParser.getMatch(connection.match(pairs));
            time = match.time;
            if(match.status.equals("finished"))
                break;
            ArrayList<Command> commands = new ArrayList<>();
            for(int i=1;i<=30;i++){
                commands.add(new Command(i, userGrade[i]));
            }
            connection.changeGrade(commands);
        }
        Score score = jsonParser.getScore(connection.score());
        System.out.println("score.toString() = " + score.toString());
        System.out.println("##########solve1 end##########");
    }

    public static void solve2(){
        System.out.println("##########solve2 start##########");
        authKeyManager.createAuthKey(2);
        String authKey = authKeyManager.getAuthKey();
        System.out.println("authKey = " + authKey);

        int userGrade[] = new int[901];
        for(int i=1;i<=900;i++){
            userGrade[i] = 4500;
        }

        int time = 1;
        while(time <= 595){
            System.out.println("time = " + time);
            ArrayList<GameResult> gameResults = jsonParser.getGameResultArray(connection.gameResult());
            ArrayList<UserInfo> userInfos = jsonParser.getUserInfoArray(connection.userInfo());

            for(int i=0;i<gameResults.size();i++){
                int winId = gameResults.get(i).win;
                int loseId = gameResults.get(i).lose;
                userGrade[winId]++;
                userGrade[loseId]--;
            }
            ArrayList<WaitingLine> waitingLines = jsonParser.getWaitingLineArray(connection.waitingLine());
            ArrayList<ArrayList<Integer>> pairs = new ArrayList<>();
            for(int i=0;i<waitingLines.size();i++){
                if(i != 0 && i % 2 == 0){
                    ArrayList<Integer> pair = new ArrayList<>();
                    pair.add(waitingLines.get(i-1).id);
                    pair.add(waitingLines.get(i).id);
                    pairs.add(pair);
                }
            }
            Match match = jsonParser.getMatch(connection.match(pairs));
            time = match.time;
            if(match.status.equals("finished"))
                break;
            ArrayList<Command> commands = new ArrayList<>();
            for(int i=1;i<=30;i++){
                commands.add(new Command(i, userGrade[i]));
            }
            connection.changeGrade(commands);
        }
        Score score = jsonParser.getScore(connection.score());
        System.out.println("score.toString() = " + score.toString());
        System.out.println("##########solve2 end##########");
    }

    public static void main(String[] args) {
        solve2();
        solve1();
    }
}
