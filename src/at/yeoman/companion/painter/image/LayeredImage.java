package at.yeoman.companion.painter.image;

import java.awt.image.WritableRaster;
import java.util.ArrayList;
import java.util.List;

public class LayeredImage {
    BlockMetrics blockMetrics;
    private List<Layer> layers;

    public LayeredImage(Size size, Position offset) {
        blockMetrics = new BlockMetrics(size, offset);
        Block.checkOffsetBounds(offset);
        layers = new ArrayList<>();
        insertLayer(0);
    }

    public LayeredImage(Size size) {
        this(size, Position.Zero);
    }

    public Size getSize() {
        return blockMetrics.getSize();
    }

    public Position getOffset() {
        return blockMetrics.getOffset();
    }

    public int getNumberOfLayers() {
        return layers.size();
    }

    public void insertLayer(int index) {
        if (index < 0 || index > layers.size()) {
            throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
        }
        layers.add(index, createLayer());
    }

    public void removeLayer(int index) {
        if (index < 0 || index >= layers.size()) {
            throw new IndexOutOfBoundsException(indexOutOfBoundsMessage(index));
        }
        layers.remove(index);
    }

    private String indexOutOfBoundsMessage(int index) {
        return "index: " + index + ", size: " + layers.size();
    }

    private Layer createLayer() {
        return new Layer(blockMetrics);
    }

    public void writeToRaster(WritableRaster raster) {
        LayeredImageToRaster imageToRaster = new LayeredImageToRaster(blockMetrics, raster);
        for (Layer layer : layers) {
            imageToRaster.writeLayer(layer);
        }
    }
}
