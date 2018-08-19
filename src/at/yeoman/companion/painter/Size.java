package at.yeoman.companion.painter;

import java.util.Objects;

public final class Size {
    public final int width;
    public final int height;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
        if (width < 0 || height < 0) {
            throw new IllegalArgumentException("width: " + width + ", height: " + height);
        }
    }

    public void checkBounds(int x, int y) {
        if (!contains(x, y)) {
            throw new IllegalArgumentException("x: " + x + ", y: " + y + ", width: " + width + ", height: " + height);
        }
    }

    public boolean contains(int x, int y) {
        return x >= 0 && y >= 0 && x < width && y < height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Size size = (Size) o;
        return width == size.width &&
                height == size.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "Size{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}
