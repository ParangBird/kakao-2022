package kakao.util;

import kakao.entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// JSONObject 인 parameter -> 각각의 class 에 맞게 변환해서 리턴
public class JsonParser {
    public ArrayList<GameResult> getGameResultArray(JSONObject gameResultJson){
        ArrayList<GameResult> gameResults = new ArrayList<>();
        try{
            JSONArray gameResultJsonArray = gameResultJson.getJSONArray("game_result");
            for(int i=0;i<gameResultJsonArray.length();i++){
                JSONObject gameResultJsonI = gameResultJsonArray.getJSONObject(i);
                int win = gameResultJsonI.getInt("win");
                int lose = gameResultJsonI.getInt("lose");
                int taken = gameResultJsonI.getInt("taken");
                GameResult newGameResult = new GameResult(win, lose, taken);
                gameResults.add(newGameResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return gameResults;
    }

    public ArrayList<WaitingLine> getWaitingLineArray(JSONObject allWaitingLineJson){
        ArrayList<WaitingLine> waitingLineArrayList = new ArrayList<>();
        try {
            JSONArray waitingLineJsonArray = allWaitingLineJson.getJSONArray("waiting_line");
            for(int i=0;i<waitingLineJsonArray.length();i++){
                JSONObject waitingLineJson = waitingLineJsonArray.getJSONObject(i);
                int id = waitingLineJson.getInt("id");
                int from = waitingLineJson.getInt("from");
                WaitingLine newWaitingLine = new WaitingLine(id, from);
                waitingLineArrayList.add(newWaitingLine);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return waitingLineArrayList;
    }

    public ArrayList<UserInfo> getUserInfoArray(JSONObject allUserInfoJson){
        ArrayList<UserInfo> userInfoArrayList = new ArrayList<>();
        try {
            JSONArray allUserInfoJsonArray = allUserInfoJson.getJSONArray("user_info");
            for(int i=0;i<allUserInfoJsonArray.length();i++){
                JSONObject userInfoJson = allUserInfoJsonArray.getJSONObject(i);
                int id = userInfoJson.getInt("id");
                int grade = userInfoJson.getInt("grade");
                UserInfo newUserInfo = new UserInfo(id, grade);
                userInfoArrayList.add(newUserInfo);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return userInfoArrayList;
    }

    public Score getScore(JSONObject scoreJson){
        Score score = new Score();
        try{
            String status = scoreJson.getString("status");
            float efficiency_score = (float) scoreJson.getDouble("efficiency_score");
            float accuracy_score1 = (float) scoreJson.getDouble("accuracy_score1");
            float accuracy_score2 = (float) scoreJson.getDouble("accuracy_score2");
            float totalScore = (float) scoreJson.getDouble("score");
            score = new Score(status, efficiency_score, accuracy_score1, accuracy_score2, totalScore);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return score;
    }

    public Match getMatch(JSONObject jsonObject){
        String status = jsonObject.getString("status");
        int time = jsonObject.getInt("time");
        return new Match(status, time);
    }
}
