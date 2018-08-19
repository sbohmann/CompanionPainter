package at.yeoman.companion.painter.image;

public class LayerView {
    private final Layer layer;

    public LayerView(Layer layer) {
        this.layer = layer;
    }

    public int get(int x, int y) {
        Block block = layer.blockForPixel(x, y);
        int relativeX = x - block.position.x;
        int relativeY = y - block.position.y;
        return block.data[relativeY * block.size.width + relativeX];
    }

    public void put(int x, int y, int color) {
        Block block = layer.blockForPixel(x, y);
        int relativeX = x - block.position.x;
        int relativeY = y - block.position.y;
        block.data[relativeY * block.size.width + relativeX] = color;
    }
}
