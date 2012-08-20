/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gephi.legend.api.renderers;

import java.awt.*;
import java.awt.geom.AffineTransform;
import org.gephi.legend.builders.TextItemBuilder;
import org.gephi.legend.items.LegendItem;
import org.gephi.legend.items.LegendItem.Alignment;
import org.gephi.legend.items.TextItem;
import org.gephi.legend.manager.LegendManager;
import org.gephi.legend.properties.TextProperty;
import org.gephi.preview.api.*;
import org.gephi.preview.spi.ItemBuilder;
import org.gephi.preview.spi.Renderer;
import org.openide.util.NbBundle;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author edubecks
 */
@ServiceProvider(service = Renderer.class, position = 502)
public class TextItemRenderer extends LegendItemRenderer {

    @Override
    public String getDisplayName() {
        return NbBundle.getMessage(TextItemRenderer.class, "TextItemRenderer.name");
    }

    @Override
    protected void readOwnPropertiesAndValues(Item item, PreviewProperties properties) {

        Integer itemIndex = item.getData(LegendItem.ITEM_INDEX);

        body = properties.getStringValue(LegendManager.getProperty(TextProperty.OWN_PROPERTIES, itemIndex, TextProperty.TEXT_BODY));
        bodyFont = properties.getFontValue(LegendManager.getProperty(TextProperty.OWN_PROPERTIES, itemIndex, TextProperty.TEXT_BODY_FONT));
        bodyFontColor = properties.getColorValue(LegendManager.getProperty(TextProperty.OWN_PROPERTIES, itemIndex, TextProperty.TEXT_BODY_FONT_COLOR));
        bodyAlignment = (Alignment) properties.getValue(LegendManager.getProperty(TextProperty.OWN_PROPERTIES, itemIndex, TextProperty.TEXT_BODY_FONT_ALIGNMENT));
    }

    @Override
    public boolean isRendererForitem(Item item, PreviewProperties properties) {
        return item instanceof TextItem;
    }

    @Override
    public boolean needsItemBuilder(ItemBuilder itemBuilder, PreviewProperties properties) {
        return itemBuilder instanceof TextItemBuilder;
    }

    @Override
    protected void renderToGraphics(Graphics2D graphics2D, AffineTransform origin, Integer width, Integer height) {
        if (!body.isEmpty()) {
            graphics2D.setTransform(origin);

            float currentSize = computeVerticalTextSpaceUsed(graphics2D, body, bodyFont, width);
//            float currentSize = legendDrawText(graphics2D, body, bodyFont, bodyFontColor, 0, 0, width, height, bodyAlignment, computeSpace);
            while (currentSize > height) {
                bodyFont = new Font(bodyFont.getName(), bodyFont.getStyle(), bodyFont.getSize() - 1);
                currentSize = computeVerticalTextSpaceUsed(graphics2D, body, bodyFont, width);
//                currentSize = legendDrawText(graphics2D, body, bodyFont, bodyFontColor, 0, 0, width, height, bodyAlignment, computeSpace);
            }
            drawText(graphics2D, body, bodyFont, bodyFontColor, width, width, width, height, bodyAlignment);
        }
    }

    /**
        
     * Override this function to render the specified text in a different way
     *
     * @param graphics2D the Graphics object to draw to
     * @param text text to be displayed
     * @param font rendering font for the text
     * @param color rendering color for the text
     * @param x the x coordinate of the area containing the text
     * @param y the y coordinate of the area containing the text
     * @param width the width of the area containing the text
     * @param height the height of the area containing the text
     * @param alignment alignment of the text. It can be LEFT, RIGHT, CENTER and
     * JUSTIFIED
     */
    protected void drawText(Graphics2D graphics2D, String text, Font font, Color color, double x, double y, Integer width, Integer height, Alignment alignment) {
        legendDrawText(graphics2D, body, bodyFont, bodyFontColor, 0, 0, width, height, bodyAlignment);
    }

    // OWN PROPERTIES
    private String body;
    private Font bodyFont;
    private Color bodyFontColor;
    private Alignment bodyAlignment;
}
