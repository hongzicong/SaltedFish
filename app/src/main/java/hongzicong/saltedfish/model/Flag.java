package hongzicong.saltedfish.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Flag {

    @Id(autoincrement = true)
    private Long id;
    private String name;
    private boolean isProb;

    @Generated(hash = 1091581722)
    public Flag(Long id, String name, boolean isProb) {
        this.id = id;
        this.name = name;
        this.isProb = isProb;
    }
    @Generated(hash = 325057191)
    public Flag() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public boolean getIsProb() {
        return this.isProb;
    }
    public void setIsProb(boolean isProb) {
        this.isProb = isProb;
    }


}
