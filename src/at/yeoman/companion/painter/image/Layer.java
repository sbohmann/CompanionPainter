package at.yeoman.companion.painter.image;

public class Layer {
    private Size size;
    private Position offset;
    private int rows;
    private int columns;
    private Block[] blocks;

    public Layer(Size size, Position offset) {
        this.size = size;
        this.offset = offset;
        Block.checkOffsetBounds(offset);
        checkSizeAndOffset();
        blocks = new BlocksForLayer(size, offset).create();
    }

    private void checkSizeAndOffset() {
        if (offset.x >= size.width || offset.y >= size.height) {
            throw new IllegalArgumentException("size: " + size + ", offset: " + offset);
        }
    }

    private Block bock(int x, int y) {
        return blocks[y * columns + x];
    }
}
