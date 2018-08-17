package at.yeoman.companion.painter.image;

import java.util.ArrayList;
import java.util.List;

public class LayeredImage {
    private Size size;
    private Position offset;
    private List<Layer> layers;

    public LayeredImage(Size size, Position offset) {
        this.size = size;
        this.offset = offset;
        Block.checkOffsetBounds(offset);
        layers = new ArrayList<>();
    }

    public void insertLayer(int index) {
        if (index < 0 || index > layers.size()) {
            throw new IndexOutOfBoundsException(Integer.toString(index));
        }
        layers.add(index, createLayer());
    }

    private Layer createLayer() {
        return new Layer(size, offset);
    }
}
