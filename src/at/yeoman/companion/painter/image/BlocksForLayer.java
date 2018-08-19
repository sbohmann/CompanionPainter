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
        for (int row = 0; row < rows; ++row) {
            for (int column = 0; column < columns; ++column) {
                blocks[blockIndex(row, column)] = createBlock(row, column);
            }
        }
    }

    private int blockIndex(int row, int column) {
        return row * columns + column;
    }

    private Block createBlock(int row, int column) {
        Size size = blockMetrics.blockSize(row, column);
        Position position = blockMetrics.blockPosition(row, column);
        return new Block(size, position);
    }
}
