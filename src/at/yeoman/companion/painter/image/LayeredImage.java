package at.yeoman.companion.painter.image;

import at.yeoman.companion.painter.Position;
import at.yeoman.companion.painter.Size;

import java.awt.image.BufferedImage;
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

    public void writeToBufferedImage(BufferedImage target) {
        LayeredImageToBufferedImage imageToRaster = new LayeredImageToBufferedImage(blockMetrics, target);
        for (Layer layer : layers) {
            imageToRaster.writeLayer(layer);
        }
    }

    public LayerView getLayerView(int index) {
        return new LayerView(layers.get(index));
    }
}
