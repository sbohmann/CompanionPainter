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

    /**
     * @param targetOffset position in this image to coincide with (0,0) on the target
     */
    public void writeToBufferedImage(BufferedImage target, Position targetOffset) {
        if (!targetOffset.equals(Position.Zero)) {
            throw new UnsupportedOperationException("target offset not yet implemented");
        }
        long before = System.nanoTime();
        LayeredImageToBufferedImage imageToRaster = new LayeredImageToBufferedImage(blockMetrics, target, targetOffset);
        for (Layer layer : layers) {
            imageToRaster.writeLayer(layer);
        }
        long after = System.nanoTime();
        System.out.println("deltaT (ns): " + (after - before));
    }

    /**
     * @param targetOffset position in the zoomed view of this image to coincide with (0,0) on the target
     * @param zoomStep     minimum 0, maxmimum 31, zoom is 1 << zoomStep
     */
    public void writeToBufferedImage(BufferedImage target, Position targetOffset, int zoomStep) {
        if (zoomStep == 0) {
            writeToBufferedImage(target, targetOffset);
        } else if (zoomStep > 0 && zoomStep <= 30) {
            writeToBufferedImageWithZoom(target, targetOffset);
        } else {
            throw new IllegalArgumentException("zoomStep < 0 or > 30: " + zoomStep);
        }
    }

    private void writeToBufferedImageWithZoom(BufferedImage target, Position targetOffset) {
        throw new UnsupportedOperationException("zoom not yet implemented");
    }

    public LayerView getLayerView(int index) {
        return new LayerView(layers.get(index));
    }
}
