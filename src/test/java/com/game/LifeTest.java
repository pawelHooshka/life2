package com.game;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

@RunWith(JUnitParamsRunner.class)
public class LifeTest {

    @Test
    public void testShouldBeAlive() {
        assertTrue(Life.shouldBeAlive(true, 2));
        assertTrue(Life.shouldBeAlive(true, 3));

        assertFalse(Life.shouldBeAlive(true, 1));
        assertFalse(Life.shouldBeAlive(true, 4));

        assertTrue(Life.shouldBeAlive(false, 3));

        assertFalse(Life.shouldBeAlive(false, 0));
        assertFalse(Life.shouldBeAlive(false, 1));
        assertFalse(Life.shouldBeAlive(false, 2));
        assertFalse(Life.shouldBeAlive(false, 4));
    }

    @Test
    @Parameters(method = "testCellsCenter")
    public void testGetLiveAdjacentsCountCenter(boolean[][] cells) {
        assertThat(Life.getLiveAdjacentsCount(cells, 2, 2, 5), is(0));

        cells[1][1] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 2, 2, 5), is(1));

        cells[3][3] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 2, 2, 5), is(2));

        cells[3][1] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 2, 2, 5), is(3));

        cells[1][3] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 2, 2, 5), is(4));

        cells[1][2] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 2, 2, 5), is(5));

        cells[3][2] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 2, 2, 5), is(6));

        cells[2][1] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 2, 2, 5), is(7));

        cells[2][3] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 2, 2, 5), is(8));
    }

    @Test
    @Parameters(method = "testCellsCorner")
    public void testGetLiveAdjacentsCountCorner(boolean[][] cells) {
        assertThat(Life.getLiveAdjacentsCount(cells, 0, 0, 5), is(0));

        cells[0][1] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 0, 0, 5), is(1));

        cells[1][0] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 0, 0, 5), is(2));

        cells[1][1] = true;
        assertThat(Life.getLiveAdjacentsCount(cells, 0, 0, 5), is(3));
    }

    @Test
    @Parameters(method = "testCellsCornerAllOthersAlive")
    public void testGetLiveAdjacentsCountCornerAllOthersAlive(boolean[][] cells) {
        assertThat(Life.getLiveAdjacentsCount(cells, 0, 0, 5), is(3));
    }

    @Test
    @Parameters(method = "testCellsCenterAllOthersAlive")
    public void testGetLiveAdjacentsCountCenterAllOthersAlive(boolean[][] cells) {
        assertThat(Life.getLiveAdjacentsCount(cells, 2, 2, 5), is(8));
    }

    @Test
    public void testBlinkerPattern() {
        boolean[][] cells = new boolean[5][5];
        cells[2][1] = true;
        cells[2][2] = true;
        cells[2][3] = true;

        boolean[][] nextGeneration = Life.age(cells);

        boolean[][] expectedGeneration = new boolean[5][5];
        expectedGeneration[1][2] = true;
        expectedGeneration[2][2] = true;
        expectedGeneration[3][2] = true;

        assertArrayEquals(expectedGeneration[0], nextGeneration[0]);
        assertArrayEquals(expectedGeneration[1], nextGeneration[1]);
        assertArrayEquals(expectedGeneration[2], nextGeneration[2]);
    }

    private Object[] testCellsCenter() {
        return testAllDeadOneAliveAndAllDead(2, 2);
    }

    private Object[] testCellsCenterAllOthersAlive() {
        return testCellsAllAliveAndAllAliveOneDead(2, 2);
    }

    private Object[] testCellsCorner() {
        return testAllDeadOneAliveAndAllDead(0, 0);
    }

    private Object[] testCellsCornerAllOthersAlive() {
        return testCellsAllAliveAndAllAliveOneDead(0, 0);
    }

    private Object[] testAllDeadOneAliveAndAllDead(int y, int x) {
        boolean[][] cellsCornerAlive = new boolean[5][5];
        boolean[][] cellsCornerDead = new boolean[5][5];
        cellsCornerAlive[y][x] = true;

        return new Object[] {
                cellsCornerAlive,
                cellsCornerDead
        };
    }

    private Object[] testCellsAllAliveAndAllAliveOneDead(int y, int x) {
        boolean[][] cellsSelectedAlive = new boolean[5][5];
        boolean[][] cellsSelectedDead = new boolean[5][5];

        populateTrue(cellsSelectedAlive);
        populateTrue(cellsSelectedDead);

        cellsSelectedDead[y][x] = false;

        return new Object[] {
                cellsSelectedAlive,
                cellsSelectedDead
        };
    }

    private void populateTrue(boolean[][] cells) {
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = true;
            }
        }
    }
}
