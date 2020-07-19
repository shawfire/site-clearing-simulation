package net.shawfire.scs;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SiteMap {

    private String[] siteMap;

    public int getMaxX() {
        if (getMaxY() <= 0) {
            return 0;
        }
        return siteMap[0].length();
    }

    public int getMaxY() {
        if (siteMap == null) {
            return 0;
        }
        return siteMap.length;
    }

    public String getCurrentSquareValue(int x, int y) {
        if (x < 0 || x < 0) {
            return null;
        }
        return siteMap[y].substring(x, x+1);
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
