/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.gephi.legend.plugin.items;

import org.gephi.legend.api.LegendProperty;
import org.gephi.legend.spi.LegendItem;
import org.gephi.preview.api.PreviewProperty;


/**
 *
 * @author edubecks
 */
public class ImageItem extends AbstractItem implements LegendItem {

        //BODY
    public static final String IMAGE_URL = "image";
    public static final String LEGEND_TYPE= "Image Item";

    public ImageItem(Object source) {
        super(source, LEGEND_TYPE);
    }

    @Override
    public String toString() {
        return (((PreviewProperty[]) this.getData(LegendItem.PROPERTIES))[LegendProperty.LABEL].getValue());
    }
}