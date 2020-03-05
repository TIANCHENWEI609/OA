package pojo;

import lombok.Data;

@Data
public class Depart {
    private long id;

    private String name;

    private String createtime;

    private long del;

    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public long getDel() {
        return del;
    }

    public void setDel(Integer del) {
        this.del = del;
    }
}