package at.yeoman.companion.painter.image;

import at.yeoman.companion.painter.Position;
import at.yeoman.companion.painter.Size;

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

    Position blockPosition(int row, int column) {
        return new Position(blockX(column), blockY(row));
    }

    int blockX(int column) {
        checkColumnBounds(column);
        if (column == 0) {
            return 0;
        } else {
            return (column * Block.Width - offset.x);
        }
    }

    int blockY(int row) {
        checkRowBounds(row);
        if (row == 0) {
            return 0;
        } else {
            return (row * Block.Height - offset.x);
        }
    }

    Size blockSize(int row, int column) {
        return new Size(blockWidth(column), blockHeight(row));
    }

    int blockHeight(int row) {
        checkRowBounds(row);
        int result;
        if (row == rows - 1) {
            result = (size.height + offset.y) - (rows - 1) * Block.Height;
        } else {
            result = Block.Height;
        }
        if (row == 0) {
            result -= offset.y;
        }
        return result;
    }

    int blockWidth(int column) {
        checkColumnBounds(column);
        int result;
        if (column == columns - 1) {
            result = (size.width + offset.x) - (columns - 1) * Block.Width;
        } else {
            result = Block.Width;
        }
        if (column == 0) {
            result -= offset.x;
        }
        return result;
    }

    private void checkRowBounds(int row) {
        if (row < 0 || row >= rows) {
            throw new IndexOutOfBoundsException("row: " + row + ", rows: " + rows);
        }
    }

    private void checkColumnBounds(int column) {
        if (column < 0 || column >= columns) {
            throw new IndexOutOfBoundsException("column: " + column + ", columns: " + columns);
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
        if (calculatedWidth != size.width) {
            throw new RuntimeException("Logical error - calculated width: " + calculatedWidth + ", size: " + size);
        }
    }

    private void checkResultingHeight() {
        int calculatedHeight = 0;
        for (int y = 0; y < rows; ++y) {
            calculatedHeight += blockHeight(y);
        }
        if (calculatedHeight != size.height) {
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
