package at.yeoman.companion.painter.image;

public class Block {
    public static final int Width = 1024;
    public static final int Height = 1024;

    public static void checkOffsetBounds(Position offset) {
        if (offset.x < 0 || offset.y < 0 || offset.x >= Block.Width || offset.y >= Block.Height) {
            throw new IllegalArgumentException("Offset out of bounds: " + offset);
        }
    }
}
