package kakao.entity;

public class GameResult {
    public GameResult(int win, int lose, int taken) {
        this.win = win;
        this.lose = lose;
        this.taken = taken;
    }

    public int win;
    public int lose;
    public int taken;
}
