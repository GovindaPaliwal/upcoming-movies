
package govinda.practic.com.layoutapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class Dates {

    @SerializedName("maximum")
    @Expose
    private String maximum;
    @SerializedName("minimum")
    @Expose
    private String minimum;

    /**
     * 
     * @return
     *     The maximum
     */
    public String getMaximum() {
        return maximum;
    }

    /**
     * 
     * @param maximum
     *     The maximum
     */
    public void setMaximum(String maximum) {
        this.maximum = maximum;
    }

    public Dates withMaximum(String maximum) {
        this.maximum = maximum;
        return this;
    }

    /**
     * 
     * @return
     *     The minimum
     */
    public String getMinimum() {
        return minimum;
    }

    /**
     * 
     * @param minimum
     *     The minimum
     */
    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public Dates withMinimum(String minimum) {
        this.minimum = minimum;
        return this;
    }



}
