package at.yeoman.companion.painter.image;

class Layer {
    private final BlockMetrics blockMetrics;
    private Block[] blocks;

    Layer(BlockMetrics blockMetrics) {
        this.blockMetrics = blockMetrics;
        blocks = new BlocksForLayer(blockMetrics).create();
    }

    Block blockForPixel(int x, int y) {
        blockMetrics.getSize().checkBounds(x, y);
        int row = (y + blockMetrics.getOffset().y) / Block.Height;
        int column = (x + blockMetrics.getOffset().x) / Block.Width;
        return block(row, column);
    }

    Block block(int row, int column) {
        return blocks[blockIndex(row, column)];
    }

    private int blockIndex(int row, int column) {
        return row * blockMetrics.getColumns() + column;
    }
}
