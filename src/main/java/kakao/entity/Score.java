package kakao.entity;

public class Score {
    public String status;
    public float efficiency_score;
    public float accuracy_score1;
    public float accuracy_score2;
    public float score;
    public Score(){

    }
    public Score(String status, float efficiency_score, float accuracy_score1, float accuracy_score2, float score) {
        this.status = status;
        this.efficiency_score = efficiency_score;
        this.accuracy_score1 = accuracy_score1;
        this.accuracy_score2 = accuracy_score2;
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" +
                "status='" + status + '\'' +
                ", efficiency_score=" + efficiency_score +
                ", accuracy_score1=" + accuracy_score1 +
                ", accuracy_score2=" + accuracy_score2 +
                ", score=" + score +
                '}';
    }
}
