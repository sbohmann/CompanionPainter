package at.yeoman.companion.painter.image;

import at.yeoman.companion.painter.Position;
import at.yeoman.companion.painter.Size;
import org.junit.Test;

import static org.junit.Assert.*;

public class BlockMetricsTest {
    @Test
    public void empty() {
        assertEmpty(new BlockMetrics(new Size(0, 0), new Position(0, 0)));
        assertEmptyProduct(new BlockMetrics(new Size(1, 0), new Position(0, 0)));
        assertEmptyProduct(new BlockMetrics(new Size(0, 1), new Position(0, 0)));
    }

    @Test
    public void singleBlock() {
        assertSize(1, 1, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(1024, 1024), new Position(0, 0)));

        assertSize(1, 1, Block.Width - 1, Block.Height, Block.Width - 1, Block.Height, new BlockMetrics(new Size(1023, 1024), new Position(0, 0)));
        assertSize(1, 1, Block.Width, Block.Height - 1, Block.Width, Block.Height - 1, new BlockMetrics(new Size(1024, 1023), new Position(0, 0)));

        assertSize(1, 1, Block.Width - 1, Block.Height, Block.Width - 1, Block.Height, new BlockMetrics(new Size(1023, 1024), new Position(1, 0)));
        assertSize(1, 1, Block.Width, Block.Height - 1, Block.Width, Block.Height - 1, new BlockMetrics(new Size(1024, 1023), new Position(0, 1)));
    }

    @Test
    public void overflow() {
        assertSize(2, 1, Block.Width - 1, Block.Height - 1, Block.Width - 1, 1, new BlockMetrics(new Size(1023, 1024), new Position(1, 1)));
        assertSize(1, 2, Block.Width - 1, Block.Height - 1, 1, Block.Height - 1, new BlockMetrics(new Size(1024, 1023), new Position(1, 1)));

        assertSize(2, 2, Block.Width - 2, Block.Height - 2, 1, 2, new BlockMetrics(new Size(1023, 1024), new Position(2, 2)));
        assertSize(2, 2, Block.Width - 2, Block.Height - 2, 2, 1, new BlockMetrics(new Size(1024, 1023), new Position(2, 2)));
    }

    @Test
    public void zeroOffset() {
        assertSize(3, 3, Block.Width, Block.Height, 952, 952, new BlockMetrics(new Size(3000, 3000), new Position(0, 0)));
        assertSize(3, 2, Block.Width, Block.Height, 976, 952, new BlockMetrics(new Size(2000, 3000), new Position(0, 0)));
        assertSize(2, 3, Block.Width, Block.Height, 952, 976, new BlockMetrics(new Size(3000, 2000), new Position(0, 0)));

        assertSize(3, 3, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(3 * 1024, 3 * 1024), new Position(0, 0)));
        assertSize(3, 2, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(2 * 1024, 3 * 1024), new Position(0, 0)));
        assertSize(2, 3, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(3 * 1024, 2 * 1024), new Position(0, 0)));

        assertSize(4, 4, Block.Width, Block.Height, 1, 1, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024 + 1), new Position(0, 0)));
        assertSize(4, 3, Block.Width, Block.Height, 1, 1, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024 + 1), new Position(0, 0)));
        assertSize(3, 4, Block.Width, Block.Height, 1, 1, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024 + 1), new Position(0, 0)));

        assertSize(4, 3, Block.Width, Block.Height, Block.Width, 1, new BlockMetrics(new Size(3 * 1024, 3 * 1024 + 1), new Position(0, 0)));
        assertSize(4, 2, Block.Width, Block.Height, Block.Width, 1, new BlockMetrics(new Size(2 * 1024, 3 * 1024 + 1), new Position(0, 0)));
        assertSize(3, 3, Block.Width, Block.Height, Block.Width, 1, new BlockMetrics(new Size(3 * 1024, 2 * 1024 + 1), new Position(0, 0)));

        assertSize(3, 4, Block.Width, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024), new Position(0, 0)));
        assertSize(3, 3, Block.Width, Block.Height, 1, Block.Height, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024), new Position(0, 0)));
        assertSize(2, 4, Block.Width, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024), new Position(0, 0)));
    }

    @Test
    public void rowOffsetOne() {
        assertSize(3, 3, Block.Width, Block.Height - 1, 952, 953, new BlockMetrics(new Size(3000, 3000), new Position(0, 1)));
        assertSize(3, 2, Block.Width, Block.Height - 1, 976, 953, new BlockMetrics(new Size(2000, 3000), new Position(0, 1)));
        assertSize(2, 3, Block.Width, Block.Height - 1, 952, 977, new BlockMetrics(new Size(3000, 2000), new Position(0, 1)));

        assertSize(4, 3, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(3 * 1024, 3 * 1024), new Position(0, 1)));
        assertSize(4, 2, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(2 * 1024, 3 * 1024), new Position(0, 1)));
        assertSize(3, 3, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(3 * 1024, 2 * 1024), new Position(0, 1)));

        assertSize(4, 4, Block.Width, Block.Height - 1, 1, 2, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024 + 1), new Position(0, 1)));
        assertSize(4, 3, Block.Width, Block.Height - 1, 1, 2, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024 + 1), new Position(0, 1)));
        assertSize(3, 4, Block.Width, Block.Height - 1, 1, 2, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024 + 1), new Position(0, 1)));

        assertSize(4, 3, Block.Width, Block.Height - 1, Block.Width, 2, new BlockMetrics(new Size(3 * 1024, 3 * 1024 + 1), new Position(0, 1)));
        assertSize(4, 2, Block.Width, Block.Height - 1, Block.Width, 2, new BlockMetrics(new Size(2 * 1024, 3 * 1024 + 1), new Position(0, 1)));
        assertSize(3, 3, Block.Width, Block.Height - 1, Block.Width, 2, new BlockMetrics(new Size(3 * 1024, 2 * 1024 + 1), new Position(0, 1)));

        assertSize(4, 4, Block.Width, Block.Height - 1, 1, 1, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024), new Position(0, 1)));
        assertSize(4, 3, Block.Width, Block.Height - 1, 1, 1, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024), new Position(0, 1)));
        assertSize(3, 4, Block.Width, Block.Height - 1, 1, 1, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024), new Position(0, 1)));
    }

    @Test
    public void columnOffsetOne() {
        assertSize(3, 3, Block.Width - 1, Block.Height, 953, 952, new BlockMetrics(new Size(3000, 3000), new Position(1, 0)));
        assertSize(3, 2, Block.Width - 1, Block.Height, 977, 952, new BlockMetrics(new Size(2000, 3000), new Position(1, 0)));
        assertSize(2, 3, Block.Width - 1, Block.Height, 953, 976, new BlockMetrics(new Size(3000, 2000), new Position(1, 0)));

        assertSize(3, 4, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024, 3 * 1024), new Position(1, 0)));
        assertSize(3, 3, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(2 * 1024, 3 * 1024), new Position(1, 0)));
        assertSize(2, 4, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024, 2 * 1024), new Position(1, 0)));

        assertSize(4, 4, Block.Width - 1, Block.Height, 2, 1, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024 + 1), new Position(1, 0)));
        assertSize(4, 3, Block.Width - 1, Block.Height, 2, 1, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024 + 1), new Position(1, 0)));
        assertSize(3, 4, Block.Width - 1, Block.Height, 2, 1, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024 + 1), new Position(1, 0)));

        assertSize(4, 4, Block.Width - 1, Block.Height, 1, 1, new BlockMetrics(new Size(3 * 1024, 3 * 1024 + 1), new Position(1, 0)));
        assertSize(4, 3, Block.Width - 1, Block.Height, 1, 1, new BlockMetrics(new Size(2 * 1024, 3 * 1024 + 1), new Position(1, 0)));
        assertSize(3, 4, Block.Width - 1, Block.Height, 1, 1, new BlockMetrics(new Size(3 * 1024, 2 * 1024 + 1), new Position(1, 0)));

        assertSize(3, 4, Block.Width - 1, Block.Height, 2, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024), new Position(1, 0)));
        assertSize(3, 3, Block.Width - 1, Block.Height, 2, Block.Height, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024), new Position(1, 0)));
        assertSize(2, 4, Block.Width - 1, Block.Height, 2, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024), new Position(1, 0)));
    }

    private void assertEmpty(BlockMetrics blockMetrics) {
        assertEquals("rows", 0, blockMetrics.getRows());
        assertEquals("column", 0, blockMetrics.getColumns());
    }

    private void assertEmptyProduct(BlockMetrics blockMetrics) {
        assertEquals("rows * columns", 0, blockMetrics.getRows() * blockMetrics.getColumns());
    }

    private void assertSize(
            int rows,
            int columns,
            int firstBlockWidth,
            int firstBlockHeight,
            int lastBlockWidth,
            int lastBlockHeight,
            BlockMetrics blockMetrics) {
        assertEquals("rows", rows, blockMetrics.getRows());
        assertEquals("columns", columns, blockMetrics.getColumns());
        assertEquals("first block width", firstBlockWidth, blockMetrics.blockWidth(0));
        assertEquals("first block height", firstBlockHeight, blockMetrics.blockHeight(0));
        assertEquals("last block width", lastBlockWidth, blockMetrics.blockWidth(columns - 1));
        assertEquals("last block height", lastBlockHeight, blockMetrics.blockHeight(rows - 1));
    }
}
