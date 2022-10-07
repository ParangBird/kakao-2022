package kakao.util;

import kakao.entity.Command;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import static kakao.util.Constants.*;

// HTTP Request 를 JSON 메시지로 만들고 받아온 Response 메시지를 JSON 으로 만들어서 return
public class Connection {
    private static Connection instance  = null;
    private AuthKeyManager authKeyManager = AuthKeyManager.getAuthKeyManager();
    public static Connection getInstance() {
        if(instance == null){
            instance = new Connection();
        }
        return instance;
    }

    public String start(int problemId){
        HttpsURLConnection connection = null;
        try {
            URL url = new URL(BASE_URL + START_URL);
            connection = (HttpsURLConnection) url.openConnection();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("problem", problemId);
            connection.setRequestMethod(METHOD_POST);
            connection.setRequestProperty(X_AUTH_TOKEN_HEADER, X_AUTH_TOKEN);
            connection.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_JSON);
            connection.setDoOutput(true);
            connection.getOutputStream().write(jsonObject.toString().getBytes());

            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while((line = bf.readLine()) != null){
                sb.append(line);
            }
            bf.close();
            return sb.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }

    public JSONObject waitingLine(){
        HttpsURLConnection conn = null;
        JSONObject responseJson = null;
        try {
            URL url = new URL(BASE_URL + WAITING_URL);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(METHOD_GET);
            conn.setRequestProperty(AUTHORIZATION, authKeyManager.getAuthKey());
            conn.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_JSON);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            responseJson = new JSONObject(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }

    public JSONObject gameResult(){
        HttpsURLConnection conn = null;
        JSONObject responseJson = null;
        try {
            URL url = new URL(BASE_URL + RESULT_URL);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(METHOD_GET);
            conn.setRequestProperty(AUTHORIZATION, authKeyManager.getAuthKey());
            conn.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_JSON);

            int responseCode = conn.getResponseCode();

            if(responseCode != 200){
                System.out.println("responseCode = " + responseCode);
                return null;
            }

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            responseJson = new JSONObject(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }

    public JSONObject userInfo(){
        HttpsURLConnection conn = null;
        JSONObject responseJson = null;
        try {
            URL url = new URL(BASE_URL + USERINFO_URL);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestProperty(AUTHORIZATION, AuthKeyManager.getAuthKeyManager().getAuthKey());
            conn.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_JSON);
            conn.setRequestMethod(METHOD_GET);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            responseJson = new JSONObject(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }

        return responseJson;
    }

    public JSONObject match(ArrayList<ArrayList<Integer>> pairArrayList){
        HttpsURLConnection conn = null;
        JSONObject responseJson = null;
        try {
            URL url = new URL(BASE_URL + MATCH_URL);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(METHOD_PUT);
            conn.setRequestProperty(AUTHORIZATION, authKeyManager.getAuthKey());
            conn.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_JSON);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            JSONObject pairs = new JSONObject();
            pairs.put("pairs", pairArrayList);
            conn.getOutputStream().write(pairs.toString().getBytes("utf-8"));

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = bufferedReader.readLine()) != null){
                sb.append(line);
            }
            responseJson = new JSONObject(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }

    public JSONObject changeGrade(ArrayList<Command> commandArray){
        HttpsURLConnection conn = null;
        JSONObject responseJson = null;
        try {
            URL url = new URL(BASE_URL + CHANGE_URL);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod(METHOD_PUT);
            conn.setRequestProperty(AUTHORIZATION, authKeyManager.getAuthKey());
            conn.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_JSON);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            JSONObject commands = new JSONObject();
            JSONArray command = new JSONArray();
            for(Command cmd : commandArray){
                JSONObject c = new JSONObject();
                c.put("id", cmd.id);
                c.put("grade", cmd.grade);
                command.put(c);
            }
            commands.put("commands", command);
            //System.out.println("commands.toString() = " + commands.toString());
            conn.getOutputStream().write(commands.toString().getBytes());
            BufferedReader bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while((line = bf.readLine()) != null){
                sb.append(line);
            }
            bf.close();
            responseJson = new JSONObject(sb.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }

    public JSONObject score(){
        HttpsURLConnection conn = null;
        JSONObject responseJson = null;
        try {
          URL url = new URL(BASE_URL + SCORE_URL);
          conn = (HttpsURLConnection) url.openConnection();
          conn.setRequestMethod(METHOD_GET);
          conn.setRequestProperty(AUTHORIZATION, authKeyManager.getAuthKey());
          conn.setRequestProperty(CONTENT_TYPE, CONTENT_TYPE_JSON);

          BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
          StringBuffer sb = new StringBuffer();
          String line = "";
          while ((line = bufferedReader.readLine()) != null){
              sb.append(line);
          }
          responseJson = new JSONObject(sb.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
        return responseJson;
    }
}
