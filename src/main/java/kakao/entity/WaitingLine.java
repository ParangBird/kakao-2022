package kakao.entity;
public class WaitingLine {
    public int id;
    public int from;
    public WaitingLine(int id, int from){
        this.id = id; this.from = from;
    }

    @Override
    public String toString() {
        return "WaitingLine{" +
                "id=" + id +
                ", from=" + from +
                '}';
    }
}
