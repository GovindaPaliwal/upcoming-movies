
package govinda.practic.com.layoutapp.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ItemImages {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("backdrops")
    @Expose
    private List<Backdrop> backdrops = new ArrayList<Backdrop>();
    @SerializedName("posters")
    @Expose
    private List<Poster> posters = new ArrayList<Poster>();

    /**
     * 
     * @return
     *     The id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The backdrops
     */
    public List<Backdrop> getBackdrops() {
        return backdrops;
    }

    /**
     * 
     * @param backdrops
     *     The backdrops
     */
    public void setBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;
    }

    /**
     * 
     * @return
     *     The posters
     */
    public List<Poster> getPosters() {
        return posters;
    }

    /**
     * 
     * @param posters
     *     The posters
     */
    public void setPosters(List<Poster> posters) {
        this.posters = posters;
    }

}
