package at.yeoman.companion.painter.image;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

class BufferedImageToLayeredImage {
    private BlockMetrics blockMetrics;
    private final WritableRaster raster;

    BufferedImageToLayeredImage(BlockMetrics blockMetrics, BufferedImage source) {
        checkSourceSize(blockMetrics, source);
        this.blockMetrics = blockMetrics;
        this.raster = source.getRaster();
    }

    private void checkSourceSize(BlockMetrics blockMetrics, BufferedImage target) {
        if (target.getWidth() != blockMetrics.getSize().width || target.getHeight() != blockMetrics.getSize().height) {
            throw new IllegalArgumentException("Target size mismatch - target size: " + target.getWidth() + " * " +
                    target.getHeight() + ", source size: " + blockMetrics.getSize());
        }
    }

    void writeLayer(Layer layer) {
        for (int row = 0; row < blockMetrics.getRows(); ++row) {
            for (int column = 0; column < blockMetrics.getColumns(); ++column) {
                writeBlock(row, column, layer.block(row, column));
            }
        }
    }

    private void writeBlock(int row, int column, Block block) {
        Position position = blockMetrics.blockPosition(row, column);
        raster.getDataElements(position.x, position.y, block.size.width, block.size.height, block.data);
    }
}
