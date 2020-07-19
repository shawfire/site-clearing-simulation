package net.shawfire.scs;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SiteMap {

    private String[] siteMap;
    private int x = -1;
    private int y = 0;
    private Direction direction = Direction.EAST;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void incX(int n) {
        this.x += n;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void incY(int n) {
        this.y += n;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public String getCurrentSquareValue() {
        if (getX() < 0 || getY() < 0) {
            return null;
        }
        return siteMap[getY()].substring(getX(), getX()+1);
    }

    public void move(int n) {
        int count = 0;
        Direction direction = getDirection();
        while (count++ < n) {
            switch (direction) {
                case EAST:
                    incX(1);
                    break;
                case WEST:
                    incX(-1);
                    break;
                case SOUTH:
                    incY(1);
                    break;
                case NORTH:
                    incY(-1);
                    break;
                default:
                    throw new IllegalStateException("Unexpected direction: " + direction);
            }
        }
    }

    public void changeDirection(char command) {
        if (command != 'l' || command != 'r') {
            throw new IllegalArgumentException("Unexpected change in direction command: " + command);
        }
        if (command == 'r') {

        } else if (command == 'l') {

        }

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
        setSiteMap(siteMapList.stream().toArray(String[]::new));
        return getSiteMap();
    }

    public String[] readFromInputStream(String fileName)
            throws IOException {
        InputStream inputStream = SiteMap.class.getResourceAsStream(fileName);
        return readFromInputStream(inputStream);
    }
}
