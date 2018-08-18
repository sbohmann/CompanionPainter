package at.yeoman.companion.painter.image;

class BlocksForLayer {
    private final BlockMetrics blockMetrics;
    private int rows;
    private int columns;
    private Block[] blocks;

    BlocksForLayer(BlockMetrics blockMetrics) {
        this.blockMetrics = blockMetrics;
    }

    Block[] create() {
        createArray();
        createBlocks();
        return blocks;
    }

    private void createArray() {
        rows = blockMetrics.getRows();
        columns = blockMetrics.getColumns();
        blocks = new Block[rows * columns];
    }

    private void createBlocks() {
        for (int y = 0; y < rows; ++y) {
            for (int x = 0; x < columns; ++x) {
                blocks[blockIndex(x, y)] = createBlock(x, y);
            }
        }
    }

    private int blockIndex(int x, int y) {
        return y * columns + x;
    }

    private Block createBlock(int x, int y) {
        Size size = blockMetrics.blockSize(x, y);
        return new Block(size);
    }
}
