package com.game;

public class Life {

    public static boolean[][] age(boolean[][] cells) {
        boolean[][] nextGeneration = new boolean[cells.length][cells[0].length];
        int n = cells.length;

        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                int adjacentLiveCount = getLiveAdjacentsCount(cells, y, x, n);
                nextGeneration[y][x] = shouldBeAlive(cells[y][x], adjacentLiveCount);
            }
        }

        return nextGeneration;
    }

    static boolean shouldBeAlive(boolean currentlyAlive, int adjacentLiveCount) {
        return (currentlyAlive && adjacentLiveCount >= 2 && adjacentLiveCount <= 3)
                || (!currentlyAlive && adjacentLiveCount == 3);
    }

    static int getLiveAdjacentsCount(boolean[][] cells, int y, int x, int n) {
        int liveCount = 0;

        int minY = Math.max(0, y - 1);
        int minX = Math.max(0, x - 1);

        int maxY = Math.min(y + 1, n - 1);
        int maxX = Math.min(x + 1, n - 1);

        for (int i = minY; i <= maxY; i++) {
            for (int j = minX; j <= maxX; j++) {
                if (!isCurrentCell(y, x, i, j) && cells[i][j])  {
                    liveCount++;
                }
            }
        }

        return liveCount;
    }

    static boolean isCurrentCell(int y, int x, int otherY, int otherX) {
        return y == otherY && x == otherX;
    }
}
