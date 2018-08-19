package at.yeoman.companion.painter.image;

import at.yeoman.companion.painter.Position;
import at.yeoman.companion.painter.Size;

class Block {
    static final int Width = 128;
    static final int Height = 128;

    final Size size;
    final Position position;
    final int[] data;

    Block(Size size, Position position) {
        this.size = size;
        this.position = position;
        if (size.width < 1 || size.height < 1) {
            throw new IllegalArgumentException("width [" + size.width + "] or height [" + size.height + "] < 1");
        }
        data = new int[size.width * size.height];
    }

    static void checkOffsetBounds(Position offset) {
        if (offset.x < 0 || offset.y < 0 || offset.x >= Block.Width || offset.y >= Block.Height) {
            throw new IllegalArgumentException("Offset out of bounds: " + offset);
        }
    }
}
