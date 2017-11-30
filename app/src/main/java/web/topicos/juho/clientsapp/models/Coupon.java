package web.topicos.juho.clientsapp.models;

/**
 * Created by juho on 30/11/17.
 */

public class Coupon {
    int id;
    String code;
    String end_date;
    String description;

    public Coupon(int id, String code, String end_date, String description) {
        this.id = id;
        this.code = code;
        this.end_date = end_date;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String enf_date) {
        this.end_date = enf_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
