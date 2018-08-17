package at.yeoman.companion.painter.image;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlockMetricsTest {
    @Test
    public void zeroOffset() {
        assertSize(3, 3, Block.Width, Block.Height, 952, 952, new BlockMetrics(new Size(3000, 3000), new Position(0, 0)));
        assertSize(3, 2, Block.Width, Block.Height, 976, 952, new BlockMetrics(new Size(2000, 3000), new Position(0, 0)));
        assertSize(2, 3, Block.Width, Block.Height, 952, 976, new BlockMetrics(new Size(3000, 2000), new Position(0, 0)));

        assertSize(3, 3, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(3 * 1024, 3 * 1024), new Position(0, 0)));
        assertSize(3, 2, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(2 * 1024, 3 * 1024), new Position(0, 0)));
        assertSize(2, 3, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(3 * 1024, 2 * 1024), new Position(0, 0)));

        assertSize(4, 4, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024 + 1), new Position(0, 0)));
        assertSize(4, 3, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024 + 1), new Position(0, 0)));
        assertSize(3, 4, Block.Width, Block.Height,Block.Width, Block.Height,  new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024 + 1), new Position(0, 0)));

        assertSize(4, 3, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(3 * 1024, 3 * 1024 + 1), new Position(0, 0)));
        assertSize(4, 2, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(2 * 1024, 3 * 1024 + 1), new Position(0, 0)));
        assertSize(3, 3, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(3 * 1024, 2 * 1024 + 1), new Position(0, 0)));

        assertSize(3, 4, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024), new Position(0, 0)));
        assertSize(3, 3, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024), new Position(0, 0)));
        assertSize(2, 4, Block.Width, Block.Height, Block.Width, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024), new Position(0, 0)));
    }

    @Test
    public void rowOffsetOne() {
        assertSize(3, 3, Block.Width, Block.Height - 1, 952, 951, new BlockMetrics(new Size(3000, 3000), new Position(0, 1)));
        assertSize(3, 2, Block.Width, Block.Height - 1, 976, 951, new BlockMetrics(new Size(2000, 3000), new Position(0, 1)));
        assertSize(2, 3, Block.Width, Block.Height - 1, 952, 975, new BlockMetrics(new Size(3000, 2000), new Position(0, 1)));

        assertSize(4, 3, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(3 * 1024, 3 * 1024), new Position(0, 1)));
        assertSize(4, 2, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(2 * 1024, 3 * 1024), new Position(0, 1)));
        assertSize(3, 3, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(3 * 1024, 2 * 1024), new Position(0, 1)));

        assertSize(4, 4, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024 + 1), new Position(0, 1)));
        assertSize(4, 3, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024 + 1), new Position(0, 1)));
        assertSize(3, 4, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024 + 1), new Position(0, 1)));

        assertSize(4, 3, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(3 * 1024, 3 * 1024 + 1), new Position(0, 1)));
        assertSize(4, 2, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(2 * 1024, 3 * 1024 + 1), new Position(0, 1)));
        assertSize(3, 3, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(3 * 1024, 2 * 1024 + 1), new Position(0, 1)));

        assertSize(4, 4, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024), new Position(0, 1)));
        assertSize(4, 3, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024), new Position(0, 1)));
        assertSize(3, 4, Block.Width, Block.Height - 1, Block.Width, 1, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024), new Position(0, 1)));
    }

    @Test
    public void columnOffsetOne() {
        assertSize(3, 3, Block.Width - 1, Block.Height, 951, 952, new BlockMetrics(new Size(3000, 3000), new Position(1, 0)));
        assertSize(3, 2, Block.Width - 1, Block.Height, 975, 952, new BlockMetrics(new Size(2000, 3000), new Position(1, 0)));
        assertSize(2, 3, Block.Width - 1, Block.Height, 951, 976, new BlockMetrics(new Size(3000, 2000), new Position(1, 0)));

        assertSize(3, 4, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024, 3 * 1024), new Position(1, 0)));
        assertSize(3, 3, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(2 * 1024, 3 * 1024), new Position(1, 0)));
        assertSize(2, 4, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024, 2 * 1024), new Position(1, 0)));

        assertSize(4, 4, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024 + 1), new Position(1, 0)));
        assertSize(4, 3, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024 + 1), new Position(1, 0)));
        assertSize(3, 4, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024 + 1), new Position(1, 0)));

        assertSize(4, 4, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024, 3 * 1024 + 1), new Position(1, 0)));
        assertSize(4, 3, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(2 * 1024, 3 * 1024 + 1), new Position(1, 0)));
        assertSize(3, 4, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024, 2 * 1024 + 1), new Position(1, 0)));

        assertSize(3, 4, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 3 * 1024), new Position(1, 0)));
        assertSize(3, 3, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(2 * 1024 + 1, 3 * 1024), new Position(1, 0)));
        assertSize(2, 4, Block.Width - 1, Block.Height, 1, Block.Height, new BlockMetrics(new Size(3 * 1024 + 1, 2 * 1024), new Position(1, 0)));
    }

    private void assertSize(
            int rows,
            int columns,
            int firstBlockWidth,
            int fistBlockHeight,
            int lastBlockWidth,
            int lastBlockHeight,
            BlockMetrics blockMetrics) {
        assertEquals(rows, blockMetrics.getRows());
        assertEquals(columns, blockMetrics.getColumns());
        assertEquals(firstBlockWidth, blockMetrics.width(0));
        assertEquals(fistBlockHeight, blockMetrics.height(0));
        assertEquals(lastBlockWidth, blockMetrics.width(columns - 1));
        assertEquals(lastBlockHeight, blockMetrics.height(rows - 1));
    }
}
