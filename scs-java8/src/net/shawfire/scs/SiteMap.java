package net.shawfire.scs;

import java.io.*;
import java.util.ArrayList;

/**
 * `SiteMap` is a class which contains a grid of `SquareType` (siteMap).
 * siteMap is a two dimensional array of SquareType where
 * siteMap[0][0] is the top left corner, where x = 0, y = 0 respectively.
 * A site map text file, that is found on the classpath can be used to
 * initialise the grid.
 * This class has the ability to display the siteMap and update the squares
 * as they are cleared.
 * This class provides access to each of the SquareType locations on the siteMap grid.
 */
public class SiteMap {

    private SquareType[][] siteMap;
    private int maxX;
    private int maxY;
    private int protectedTreeCount = 0;
    private int clearedSquareCount = 0;

    public void incProtectedTreeCount() {
        this.protectedTreeCount += 1;
    }

    public void incClearedSquareCount() {
        this.clearedSquareCount++;
    }

    public int getProtectedTreeCount() {
        return protectedTreeCount;
    }

    public int getClearedSquareCount() {
        return clearedSquareCount;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void clearSquare(int x, int y) {
        siteMap[x][y] = SquareType.CLEARED;
        incClearedSquareCount();
    }

    public SquareType getSquareValue(int x, int y) {
        if (x < 0 || x < 0) {
            return null;
        }
        return siteMap[x][y];
    }

    public int getUnclearedSquareCount() {
        return maxX * maxY - getProtectedTreeCount() - getClearedSquareCount();
    }

    /**
     * Return a siteMap formatted for display as a String
     *
     * @return the siteMap formatted for display as a String
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < getMaxY(); y++) {
            for (int x = 0; x < getMaxX(); x++) {
                // embed a space between each pair of characters
                if (x != 0) {
                    sb.append(' ');
                }
                sb.append(siteMap[x][y].getValue());
            }
            // each line of the site map separated by a new line
            sb.append('\n');
        }
        return sb.toString();
    }

    public void readFromInputStream(InputStream inputStream)
            throws IOException {
        ArrayList<String> siteMapList = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null && (line = line.trim()).length() != 0) {
                siteMapList.add(line);
            }
        }
        finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        maxX = siteMapList.get(0).length();
        maxY = siteMapList.size();
        siteMap = new SquareType[maxX][maxY];
        for (int y=0; y < maxY; y++) {
            String row = siteMapList.get(y);
            for (int x=0; x < maxX; x++) {
                siteMap[x][y] = SquareType.fromString(row.substring(x, x+1));
                if (siteMap[x][y] == SquareType.PRESERVE_TREE) {
                    incProtectedTreeCount();
                }
            }
        }
    }

    public void readFromInputStream(String fileName)
            throws IOException {
        InputStream inputStream = SiteMap.class.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException(String.format("File [%1$s] is not found.", fileName));
        }
        readFromInputStream(inputStream);
    }


}
