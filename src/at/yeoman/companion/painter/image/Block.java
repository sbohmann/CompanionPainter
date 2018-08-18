package at.yeoman.companion.painter.image;

import java.util.Random;

class Block {
    static final int Width = 1024;
    static final int Height = 1024;

    final Size size;
    final int[] data;

    Block(Size size) {
        this.size = size;
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
