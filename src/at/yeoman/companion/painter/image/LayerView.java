package at.yeoman.companion.painter.image;

public class LayerView {
    private final BlockMetrics blockMetrics;
    private final Layer layer;

    public LayerView(BlockMetrics blockMetrics, Layer layer) {
        this.blockMetrics = blockMetrics;
        this.layer = layer;
    }

    public int get(int x, int y) {
        Block block  = layer.blockForPixel(x, y);
        int relativeX = x - block.position.x;
        int relativeY = y - block.position.y;
        return block.data[relativeY + block.size.width +relativeX];
    }
}
