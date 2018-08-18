package at.yeoman.companion.painter.image;

import java.awt.image.WritableRaster;

class LayeredImageToRaster {
    private BlockMetrics blockMetrics;
    private final WritableRaster raster;

    LayeredImageToRaster(BlockMetrics blockMetrics, WritableRaster raster) {
        this.blockMetrics = blockMetrics;
        this.raster = raster;
    }

    void writeLayer(Layer layer) {
        for (int y = 0; y < blockMetrics.getRows(); ++y) {
            for (int x = 0; x < blockMetrics.getColumns(); ++x) {
                writeBlock(x, y, layer.block(x, y));
            }
        }
    }

    private void writeBlock(int x, int y, Block block) {
        Position position = blockMetrics.blockPosition(x, y);
        raster.setDataElements(position.x, position.y + y, block.size.width, block.size.height, block.data);
    }
}
