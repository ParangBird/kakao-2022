package kakao.entity;

public class UserInfo {
    public int id;
    public int grade;
    public UserInfo(int id, int grade){
        this.id = id;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "id=" + id +
                ", grade=" + grade +
                '}';
    }
}
