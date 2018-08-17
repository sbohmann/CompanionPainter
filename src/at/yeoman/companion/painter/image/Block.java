package at.yeoman.companion.painter.image;

class Block {
    static final int Width = 1024;
    static final int Height = 1024;

    static void checkOffsetBounds(Position offset) {
        if (offset.x < 0 || offset.y < 0 || offset.x >= Block.Width || offset.y >= Block.Height) {
            throw new IllegalArgumentException("Offset out of bounds: " + offset);
        }
    }
}
