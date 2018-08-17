package at.yeoman.companion.painter.image;

public class BlockMetrics {
    private final Size size;
    private final Position offset;
    private int rows;
    private int columns;

    public BlockMetrics(Size size, Position offset) {
        this.size = size;
        this.offset = offset;
        calculateRowsAndColumns();
    }

    private void calculateRowsAndColumns() {
        rows = (size.height + offset.y + Block.Height - 1) / Block.Height;
        columns = (size.width + offset.x + Block.Width - 1) / Block.Width;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    Size get(int x, int y) {
        return new Size(width(x), height(y));
    }

    int width(int x) {
        int result = Block.Width;
        if (x == columns - 1) {
            result = (size.width + offset.x) % Block.Width;
        }
        if (x == 0) {
            result -= offset.x;
        }
        return result;
    }

    int height(int y) {
        int result = Block.Height;
        if (y == rows - 1) {
            result = (size.height + offset.y) % Block.Height;
        }
        if (y == 0) {
            result -= offset.y;
        }
        return result;
    }
}
