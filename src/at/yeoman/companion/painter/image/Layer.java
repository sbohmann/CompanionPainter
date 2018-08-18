package at.yeoman.companion.painter.image;

class Layer {
    private final BlockMetrics blockMetrics;
    private int rows;
    private int columns;
    private Block[] blocks;

    Layer(BlockMetrics blockMetrics) {
        this.blockMetrics = blockMetrics;
        blocks = new BlocksForLayer(blockMetrics).create();
    }

    Block block(int x, int y) {
        return blocks[y * columns + x];
    }
}
