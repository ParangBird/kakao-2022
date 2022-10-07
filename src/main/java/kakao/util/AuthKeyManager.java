package kakao.util;

import org.json.JSONObject;

public class AuthKeyManager {
    private static AuthKeyManager authKeyManager = null;
    private String authKey = "";
    public static AuthKeyManager getAuthKeyManager() {
        if(authKeyManager == null){
            authKeyManager = new AuthKeyManager();
        }
        return authKeyManager;
    }

    public String getAuthKey() {
        return authKey;
    }

    public synchronized void createAuthKey(int problemId){
        JSONObject startJson = new JSONObject(Connection.getInstance().start(problemId));
        authKey = startJson.getString("auth_key");
    }
}
