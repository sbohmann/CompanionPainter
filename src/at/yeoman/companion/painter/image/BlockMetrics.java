package at.yeoman.companion.painter.image;

class BlockMetrics {
    private final Size size;
    private final Position offset;
    private int rows;
    private int columns;

    BlockMetrics(Size size, Position offset) {
        this.size = size;
        this.offset = offset;
        calculateRowsAndColumns();
        checkResultingSize();
    }

    private void calculateRowsAndColumns() {
        rows = (size.height + offset.y + Block.Height - 1) / Block.Height;
        columns = (size.width + offset.x + Block.Width - 1) / Block.Width;
    }

    int getRows() {
        return rows;
    }

    int getColumns() {
        return columns;
    }

    Size get(int x, int y) {
        return new Size(width(x), height(y));
    }

    int width(int x) {
        checkWidthBounds(x);
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

    private void checkWidthBounds(int x) {
        if (x < 0 || x >= columns) {
            throw new IndexOutOfBoundsException("x: " + x + ", columns: " + columns);
        }
    }

    int height(int y) {
        checkHeightBounds(y);
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

    private void checkHeightBounds(int y) {
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
            calculatedWidth += width(x);
        }
        if (calculatedWidth != size.width){
            throw new RuntimeException("Logical error - calculated width: " + calculatedWidth + ", size: " + size);
        }
    }

    private void checkResultingHeight() {
        int calculatedHeight = 0;
        for (int y = 0; y < rows; ++y) {
            calculatedHeight += height(y);
        }
        if (calculatedHeight != size.height){
            throw new RuntimeException("Logical error - calculated height: " + calculatedHeight + ", size: " + size);
        }
    }
}
