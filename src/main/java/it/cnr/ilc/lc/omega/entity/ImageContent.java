package it.cnr.ilc.lc.omega.entity;

import javax.persistence.Entity;

/**
 *
 * @author angelo
 */
@Entity
public class ImageContent extends Content {

    private Byte[] image;

    ImageContent() {
        super("image/*");
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

}
