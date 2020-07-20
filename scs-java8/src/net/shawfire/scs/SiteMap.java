package net.shawfire.scs;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;

public class SiteMap {

    private String[] siteMap;
    private String[][] siteMap2;
    private SquareType[][] siteMap3;
    private int maxX;
    private int maxY;

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void clearSquare(int x, int y) {
        siteMap3[x][y] = SquareType.CLEARED;
    }

    public String getCurrentSquareValueOld(int x, int y) {
        if (x < 0 || x < 0) {
            return null;
        }
        return siteMap[y].substring(x, x+1);
    }

    public String getCurrentSquareValueOld2(int x, int y) {
        if (x < 0 || x < 0) {
            return null;
        }
        return siteMap2[x][y];
    }

    public SquareType getSquareValue(int x, int y) {
        if (x < 0 || x < 0) {
            return null;
        }
        return siteMap3[x][y];
    }

    /**
     * Return a siteMap formatted for display as a String
     *
     * @return the siteMap formatted for display as a String
     */
    public String toString() {
        // embed a space between each pair of characters
        String[] siteMapArray = getSiteMap();
        String[] newSiteMapArray = new String[siteMapArray.length];
        for (int i = 0; i < siteMapArray.length; i++) {
            newSiteMapArray[i] = siteMapArray[i].replace("", " ").trim();
        }
        // return each line of the site map separated by a new line
        return StringUtils.join(newSiteMapArray, "\n");
    }

    public String[] getSiteMap() {
        return siteMap;
    }

    public void setSiteMap(String[] siteMap) {
        this.siteMap = siteMap;
    }

    public String[] readFromInputStream(InputStream inputStream)
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
        siteMap2 = new String[maxX][maxY];
        siteMap3 = new SquareType[maxX][maxY];
        for (int y=0; y < maxY; y++) {
            String row = siteMapList.get(y);
            for (int x=0; x < maxX; x++) {
                siteMap2[x][y] = row.substring(x, x+1);
                siteMap3[x][y] = SquareType.fromString(row.substring(x, x+1));
            }
        }
        setSiteMap(siteMapList.stream().toArray(String[]::new));
        return getSiteMap();
    }

    public String[] readFromInputStream(String fileName)
            throws IOException {
        InputStream inputStream = SiteMap.class.getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new FileNotFoundException(String.format("File [%1$s] is not found.", fileName));
        }
        return readFromInputStream(inputStream);
    }


}
