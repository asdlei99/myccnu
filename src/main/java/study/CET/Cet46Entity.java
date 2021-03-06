package study.CET;

import javax.persistence.*;

/**
 * Created by wuhaolin on 7/7/14.
 * :
 */
@Entity
@Table(name = "cet46", schema = "", catalog = "weixin")
@IdClass(Cet46EntityPK.class)
public class Cet46Entity implements Comparable<Cet46Entity> {
    private Float sumScore;
    private String listening;
    private String reading;
    private String essay;
    private String grade;
    private String name;
    private String xh;
    private String date;

    @Basic
    @Column(name = "sumScore", nullable = true, insertable = true, updatable = true, precision = 0)
    public Float getSumScore() {
        return sumScore;
    }

    public void setSumScore(Float sumScore) {
        this.sumScore = sumScore;
    }

    @Basic
    @Column(name = "listening", nullable = true, insertable = true, updatable = true, length = 255)
    public String getListening() {
        return listening;
    }

    public void setListening(String listening) {
        this.listening = listening;
    }

    @Basic
    @Column(name = "reading", nullable = true, insertable = true, updatable = true, length = 255)
    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    @Basic
    @Column(name = "essay", nullable = true, insertable = true, updatable = true, length = 255)
    public String getEssay() {
        return essay;
    }

    public void setEssay(String essay) {
        this.essay = essay;
    }

    @Id
    @Column(name = "grade", nullable = false, insertable = true, updatable = true, length = 10)
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "name", nullable = true, insertable = true, updatable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "xh", nullable = false, insertable = true, updatable = true, length = 15)
    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    @Id
    @Basic
    @Column(name = "date", nullable = false, insertable = true, updatable = true, length = 255)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cet46Entity that = (Cet46Entity) o;

        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (essay != null ? !essay.equals(that.essay) : that.essay != null) return false;
        if (grade != null ? !grade.equals(that.grade) : that.grade != null) return false;
        if (listening != null ? !listening.equals(that.listening) : that.listening != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (reading != null ? !reading.equals(that.reading) : that.reading != null) return false;
        if (sumScore != null ? !sumScore.equals(that.sumScore) : that.sumScore != null) return false;
        if (xh != null ? !xh.equals(that.xh) : that.xh != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sumScore != null ? sumScore.hashCode() : 0;
        result = 31 * result + (listening != null ? listening.hashCode() : 0);
        result = 31 * result + (reading != null ? reading.hashCode() : 0);
        result = 31 * result + (essay != null ? essay.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (xh != null ? xh.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public Cet46Entity(Float sumScore, String listening, String reading, String essay, String grade, String date) {
        this.sumScore = sumScore;
        this.listening = listening;
        this.reading = reading;
        this.essay = essay;
        this.grade = grade;
        this.date = date;
    }

    public Cet46Entity() {
    }

    /**
     * 正态分布函数
     *
     * @param u
     * @return
     */
    private static double calc(double u) {
        double y = Math.abs(u);
        double y2 = y * y;
        double z = Math.exp(-0.5 * y2) * 0.398942280401432678;
        double p = 0;
        int k = 28;
        double s = -1;
        double fj = k;

        if (y > 3) {
            //当y>3时
            for (int i = 1; i <= k; i++) {
                p = fj / (y + p);
                fj = fj - 1.0;
            }
            p = z / (y + p);
        } else {
            //当y<3时
            for (int i = 1; i <= k; i++) {
                p = fj * y2 / (2.0 * fj + 1.0 + s * p);
                s = -s;
                fj = fj - 1.0;
            }
            p = 0.5 - z * y / (1 - p);
        }
        if (u > 0) p = 1.0 - p;
        return p;
    }

    public boolean pass() {
        return this.sumScore >= 425;
    }

    /**
     * 获得排名
     */
    public int rank() {
        double bfb;
        if (sumScore > 500) {
            bfb = calc((sumScore - 500) / 70);
        } else {
            bfb = 1 - calc((500 - sumScore) / 70);
        }
        bfb = 1 - bfb;
        return (int) Math.floor(bfb * 3900);
    }

    @Override
    /**
     * 优先按照考试时间来排序,其次按照6,4级
     */
    public int compareTo(Cet46Entity o) {
        int re;
        re = -this.date.compareTo(o.date);
        if (re == 0) {
            re = -this.grade.compareTo(o.getGrade());
        }
        return re;
    }
}
