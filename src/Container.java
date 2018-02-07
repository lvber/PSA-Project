/**
 * Created by lvbowen on 7/2/18.
 */
public class Container {
    private int id;
    private int colorCode;

    public Container(int id, int colorCode) {
        this.id = id;
        this.colorCode = colorCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColorCode() {
        return colorCode;
    }

    public void setColorCode(int colorCode) {
        this.colorCode = colorCode;
    }
}
