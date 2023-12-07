package ir.sajjadyosefi.android.xTubeless.classes.example.lambda;

public class UserInLambldaExample {
    private String name;
    private int id;
    private float rank;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public UserInLambldaExample(String name, int id, float rank) {
        this.name = name;
        this.id = id;
        this.rank = rank;
    }
}
