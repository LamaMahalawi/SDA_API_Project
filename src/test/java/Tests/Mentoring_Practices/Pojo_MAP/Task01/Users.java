package Tests.Mentoring_Practices.Pojo_MAP.Task01;

public class Users {
    private String name;
    private String job;

    // ✅ Default Constructor (ضروري لـ deserialization)
    public Users() {
    }

    // ✅ Parameterized Constructor
    public Users(String name, String job) {
        this.name = name;
        this.job = job;
    }

    // ✅ Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    // ✅ Optional - for debugging
    @Override
    public String toString() {
        return "Users{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
