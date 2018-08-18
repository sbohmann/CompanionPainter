package at.yeoman.companion.painter.image;

class BlockMetrics {
    private final Size size;
    private final Position offset;
    private int rows;
    private int columns;

    BlockMetrics(Size size, Position offset) {
        this.size = size;
        this.offset = offset;
        Block.checkOffsetBounds(offset);
        checkSizeAndOffset();
        calculateRowsAndColumns();
        checkResultingSize();
    }

    private void checkSizeAndOffset() {
        if (offset.x >= size.width || offset.y >= size.height) {
            throw new IllegalArgumentException("size: " + size + ", offset: " + offset);
        }
    }

    private void calculateRowsAndColumns() {
        rows = (size.height + offset.y + Block.Height - 1) / Block.Height;
        columns = (size.width + offset.x + Block.Width - 1) / Block.Width;
    }

    Size getSize() {
        return size;
    }

    Position getOffset() {
        return offset;
    }

    int getRows() {
        return rows;
    }

    int getColumns() {
        return columns;
    }

    public Position blockPosition(int x, int y) {
        return new Position(blockX(x), blockY(y));
    }

    int blockX(int x) {
        checkXBounds(x);
        if (x == 0) {
            return 0;
        } else {
            return (x * Block.Width - offset.x);
        }
    }

    int blockY(int y) {
        checkYBounds(y);
        if (y == 0) {
            return 0;
        } else {
            return (y * Block.Height - offset.x);
        }
    }

    Size blockSize(int x, int y) {
        return new Size(blockWidth(x), blockHeight(y));
    }

    int blockWidth(int x) {
        checkXBounds(x);
        int result;
        if (x == columns - 1) {
            result = (size.width + offset.x) - (columns - 1) * Block.Width;
        } else {
            result = Block.Width;
        }
        if (x == 0) {
            result -= offset.x;
        }
        return result;
    }

    int blockHeight(int y) {
        checkYBounds(y);
        int result;
        if (y == rows - 1) {
            result = (size.height + offset.y) - (rows - 1) * Block.Height;
        } else {
            result = Block.Height;
        }
        if (y == 0) {
            result -= offset.y;
        }
        return result;
    }

    private void checkXBounds(int x) {
        if (x < 0 || x >= columns) {
            throw new IndexOutOfBoundsException("x: " + x + ", columns: " + columns);
        }
    }

    private void checkYBounds(int y) {
        if (y < 0 || y >= rows) {
            throw new IndexOutOfBoundsException("y: " + y + ", rows: " + rows);
        }
    }

    private void checkResultingSize() {
        checkResultingWidth();
        checkResultingHeight();
    }

    private void checkResultingWidth() {
        int calculatedWidth = 0;
        for (int x = 0; x < columns; ++x) {
            calculatedWidth += blockWidth(x);
        }
        if (calculatedWidth != size.width){
            throw new RuntimeException("Logical error - calculated width: " + calculatedWidth + ", size: " + size);
        }
    }

    private void checkResultingHeight() {
        int calculatedHeight = 0;
        for (int y = 0; y < rows; ++y) {
            calculatedHeight += blockHeight(y);
        }
        if (calculatedHeight != size.height){
            throw new RuntimeException("Logical error - calculated height: " + calculatedHeight + ", size: " + size);
        }
    }

    @Override
    public String toString() {
        return "BlockMetrics{" +
                "size=" + size +
                ", offset=" + offset +
                ", rows=" + rows +
                ", columns=" + columns +
                '}';
    }
}
