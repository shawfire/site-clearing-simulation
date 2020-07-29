# Provide a solution to the `site-clearing-simulation` challenge<br/> in Java8 using `TDD` (`T`est `D`riven `D`envelopment)

`Click` on list items to expand and<br/>
`Click` on hyperlinks for more information.

<details><summary>TODO</summary>

- Add javadoc comments
- Check for invalid file
- Add logging
- Accept bulldozer directives
- Display cost summary.
- Add maven pom.xml file.
- Create Dockerfile for the solution.

</details>

<details><summary>Prerequisites i.e. java8 jdk</summary>

```bash
$ java -version
java version "1.8.0_251"
Java(TM) SE Runtime Environment (build 1.8.0_251-b08)
Java HotSpot(TM) 64-Bit Server VM (build 25.251-b08, mixed mode)
```

</details>

<details><summary>Project Setup</summary>

### Using IntelliJ IDE

- New Project -> Java -> project name: `scs-java8` (path: `~/dev/site-clearing-simulation`)
- Create a package `net.shawfire.scs` in the `src` folder
- Create new Java class `SiteMap`
- From the project directory create a directory called `test`
  - Right Click on the `test` folder -> Mark Directory as `Test Sources Root`
  - Create a package `net.shawfire.scs` in the `test` folder
- Add junit via maven to your project
  - `File` -> `Project Structure...` -> `Libraries` -> `+`
  - -> `From Maven...` -> `junit` -> `Search`
  - Choose the latest i.e. `junit:junit:4.13`
- Create test Java class `SiteMapTest` within the `net.shawfire.scs` package.
- First test in `SiteMapTest` is to read a text file from the `classpath`

  - the test can initially just call the `fail()` to test the `junit` setup.
  - test a passing test as well.

    ```java
    package net.shawfire.scs;

    import org.junit.Test;

    import java.io.IOException;

    import static org.junit.Assert.fail;
    import static org.junit.Assert.assertEquals;

    public class SiteMapTest {

        @Test
        public void testJunitSetupFail() {
            fail();
        }

        @Test
        public void testJunitSetupSuccess() {
            assertEquals(4, 2*2);
        }

    }
    ```

</details>

<details><summary>First Test to read a site map text file and check first line</summary>

- Create a folder `resources` in the `test` folder
  - Right Click on the `resources` folder -> Mark Directory as `Test Resources Root`
  - Within this file place the test file `test-site-map.txt`
- Then write the first iteration of the readFile

  - test in the `SiteMapTest` class method in `SiteMap` and

    ```java
    package net.shawfire.scs;

    import org.junit.Assert;
    import org.junit.Test;

    import java.io.IOException;

    import java.io.InputStream;
    import java.util.ArrayList;

    public class SiteMapTest {

        @Test
        public void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
            Class classPath = SiteMapTest.class;
            InputStream inputStream = classPath.getResourceAsStream("/test-site-map.txt");
            ArrayList<String> siteMap = new SiteMap().readFromInputStream(inputStream);

            // Check the first line of the test file is as expected
            Assert.assertEquals(siteMap.get(0), "ootooooooo");
        }

    }
    ```

  - the implementation in the `SiteMap` class.

    ```java
    package net.shawfire.scs;

    import java.io.BufferedReader;
    import java.io.IOException;
    import java.io.InputStream;
    import java.io.InputStreamReader;
    import java.util.ArrayList;

    public class SiteMap {
        public ArrayList<String> readFromInputStream(InputStream inputStream)
                throws IOException {
            ArrayList<String> siteMap = new ArrayList<>();
            try (BufferedReader br
                         = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = br.readLine()) != null && (line = line.trim()).length() != 0) {
                    siteMap.add(line);
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
            return siteMap;
        }
    }
    ```

</details>

<details><summary>Validate site map text file name command line argument</summary>

```java
package net.shawfire.scs;

public class App {
    private String fileName = null;
    public static String MustPassFileName = "Must pass site map text file argument";
    public static String FileNameLabel = "Entered site file text file name is: ";

    private static SysOutDelegate sysOutDelegate = (val) -> System.out.println(val);

    public static void main(String[] args) {
        /* Validate the number of parameters */
        if (args.length != 1) {
            usage();
            return;
        }

        App app = new App(args[0]);

        sysOutDelegate.println(app.getSiteMapHeading());
    }

    public App(String fileName) {
        this.fileName = fileName;
    }

    private static void usage() {
        sysOutDelegate.println(MustPassFileName);
    }

    public String getString() {
        return FileNameLabel + fileName;
    }

    protected static void setSysOutDelegate(SysOutDelegate val) {
        sysOutDelegate = val;
    }
}
```

```java
package net.shawfire.scs;

@FunctionalInterface
public interface SysOutDelegate {
    void println(String val);
}
```

```java
package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AppTest {

    String lastSysOutmessage;

    @Before
    public void injectLastSysOutDelegate() {
        App.setSysOutDelegate((val) -> lastSysOutmessage = val);
    }

    @Test
    public void givenTwoInputParameter_shouldAskForOne() throws Exception {
        App.main(new String[] {"a", "b"});

        Assert.assertEquals(lastSysOutmessage, Constants.MustPassFileName);
    }

    @Test
    public void givenOneInputParameters_shouldReadBack() throws Exception {
        App.main(new String[] {"a"});

        Assert.assertEquals(lastSysOutmessage, App.FileNameLabel + "a");
    }

}
```

</details>

<details><summary>Check all lines of the site map text file are read correctly</summary>

```java
package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

public class SiteMapTest {

    @Test
    public void givenFileNameAsAbsolutePath_whenUsingClasspath_thenFileData() throws IOException {
        Class classPath = SiteMapTest.class;
        InputStream inputStream = classPath.getResourceAsStream("/test-site-map.txt");
        String[] siteMap = new SiteMap().readFromInputStream(inputStream);

        // Check the all lines in the test file are as expected
        String[] expectedSiteMap = {
                "ootooooooo",
                "oooooooToo",
                "rrrooooToo",
                "rrrroooooo",
                "rrrrrtoooo"
        };
        for (int i = 0; i < expectedSiteMap.length; i++) {
            Assert.assertEquals(String.format("line %1$s: ", i), siteMap[i], expectedSiteMap[i]);
        }
    }

}
```

```java
package net.shawfire.scs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SiteMap {
    public String[] readFromInputStream(InputStream inputStream)
            throws IOException {
        ArrayList<String> siteMap = new ArrayList<>();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null && (line = line.trim()).length() != 0) {
                siteMap.add(line);
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
        return siteMap.stream().toArray(String[]::new);
    }
}
```

</details>

<details><summary>Check for an exception and a string in the output</summary>

```java
package net.shawfire.scs;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

public class AppTest {

    StringBuffer outputMessage = new StringBuffer();

    private void assertOutputContainsString(String str) {
        MatcherAssert.assertThat(outputMessage.toString(), CoreMatchers.containsString(str));
    }

    @Before
    public void injectLastSysOutDelegate() {
        App.setSysOutDelegate((val) -> outputMessage.append(val).toString());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void givenTwoInputParameter_shouldThrowException() throws Exception {
        App.main(new String[] { "a", "b" });
    }

    @Test
    public void givenTwoInputParameter_shouldAskForOne() {

        try {
            App.main(new String[] { "a", "b" });

        } catch (java.lang.IllegalArgumentException e) {
            assertOutputContainsString(Constants.MustPassFileName);
        }
    }

    @Test
    public void givenOneInputParameters_shouldReadBack() throws Exception {
        String fileName = "testFileName.txt";
        App.main(new String[] { fileName });

        assertOutputContainsString(String.format(Constants.SiteMapLabel, fileName));
    }

    @Test
    public void givenAValidMapFile_shouldDisplayMapLabel() throws Exception {
        String fileName = "test-site-map.txt";
        App.main(new String[] { fileName });
        assertOutputContainsString(Constants.AppHeadingLabel);
    }

}
```

```java
package net.shawfire.scs;

public class App {
    private String fileName = null;
    public static String MustPassFileName = "Must pass only site map text file argument";
    public static String AppHeadingLabel = "\nWelcome to the site clearing simulator.\n";
    public static String SiteMapLabel = "\nThis is a map of the site (read from file: %1$s):\n";

    private static SysOutDelegate sysOutDelegate = (val) -> System.out.println(val);

    public static void main(String[] args) {
        /* Validate the number of parameters */
        if (args.length != 1) {
            usage();
            throw new java.lang.IllegalArgumentException(
                    String.format("Expected 1 argument but received: %1d", args.length));
        }

        App app = new App(args[0]);

        sysOutDelegate.println(app.AppHeadingLabel);
        sysOutDelegate.println(app.getSiteMapHeading());
    }

    public App(String fileName) {
        this.fileName = fileName;
    }

    private static void usage() {
        sysOutDelegate.println(MustPassFileName);
    }

    public String getSiteMapHeading() {
        return String.format(SiteMapLabel, fileName);
    }

    protected static void setSysOutDelegate(SysOutDelegate val) {
        sysOutDelegate = val;
    }
}
```

References: [Using Hamcrest for testing - Tutorial](https://www.vogella.com/tutorials/Hamcrest/article.html)

</details>

<details><summary>Use Mockito to validate output</summary>

```java
package net.shawfire.scs;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.fail;
import org.mockito.Mockito;

import java.io.PrintStream;


public class AppTest {

    PrintStream stdout = Mockito.mock(PrintStream.class);

    private void assertStdoutContains(String str) {
        Mockito.verify(stdout).println(Mockito.contains(str));
    }

    @Before
    public void injectLastSysOutDelegate() {
        System.setOut(stdout);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void givenTwoInputParameter_shouldThrowException() throws Exception {
        App.main(new String[] { "a", "b" });
    }

    @Test
    public void givenTwoInputParameter_shouldAskForOne() {

        try {
            App.main(new String[] { "a", "b" });

        } catch (java.lang.IllegalArgumentException e) {
            assertStdoutContains(Constants.MustPassFileName);
            Assert.assertEquals(String.format(Constants.ExpectedOneArgGotNMsg, 2), e.getMessage());
        }
    }

    @Test
    public void givenAValidMapFile_shouldDisplayMapLabel() throws Exception {
        String fileName = "test-site-map.txt";
        App.main(new String[] { fileName });
        assertStdoutContains(Constants.AppHeadingLabel);
    }

    @Test
    public void givenOneInputParameters_shouldReadBack() throws Exception {
        String fileName = "testFileName.txt";
        App.main(new String[] { fileName });

        assertStdoutContains(String.format(Constants.SiteMapLabel, fileName));
    }

}
```

```java
package net.shawfire.scs;

public class App {
    private String fileName = null;
    public static String MustPassFileName = "Must pass only site map text file argument";
    public static String AppHeadingLabel = "\nWelcome to the site clearing simulator.\n";
    public static String SiteMapLabel = "\nThis is a map of the site (read from file: %1$s):\n";
    public static String ExpectedOneArgGotNMsg = "Expected 1 argument but received: %1d";

    private static SysOutDelegate sysOutDelegate = (val) -> System.out.println(val);

    public static void main(String[] args) {
        /* Validate the number of parameters */
        if (args.length != 1) {
            usage();
            throw new java.lang.IllegalArgumentException(
                    String.format(ExpectedOneArgGotNMsg, args.length));
        }

        App app = new App(args[0]);

        sysOutDelegate.println(app.AppHeadingLabel);
        sysOutDelegate.println(app.getSiteMapHeading());
    }

    public App(String fileName) {
        this.fileName = fileName;
    }

    private static void usage() {
        sysOutDelegate.println(MustPassFileName);
    }

    public String getSiteMapHeading() {
        return String.format(SiteMapLabel, fileName);
    }

    protected static void setSysOutDelegate(SysOutDelegate val) {
        sysOutDelegate = val;
    }
}
```

</details>

<details><summary>Read and validate user commands</summary>

```java
package net.shawfire.scs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class App {
    private String fileName = null;
    public static String MustPassFileName = "Must pass only site map text file argument";
    public static String AppHeadingLabel = "\nWelcome to the site clearing simulator.";
    public static String SiteMapLabel = "\nThis is a map of the site (read from file: %1$s):\n";
    public static String ExpectedOneArgGotNMsg = "Expected 1 argument but received: %1d";
    public static String InitialBulldozerPositionMsg = "The bulldozer is currently located at the Northern edge of the site, immediately to the West of the site, and facing East.";
    public static String MovementPrompt = "(l)eft, (r)ight, (a)dvance <n>, (q)uit: ";
    public static String CommandsEnteredHeading = "The simulation has ended at your request. These are the commands you issued:";
    public static String CostsHeading = "The costs for this land clearing operation were:";
    public static String ItemColumnHeading = "Item";
    public static String QuantityColumnHeading = "Item";
    public static String CostColumnHeading = "Item";
    public static String CommunicationItemLabel = "communication overhead";
    public static String FuelItemLabel = "fuel usage";
    public static String UnclearedSquaresItemLabel = "uncleared squares";
    public static String DestructionItemLabel = "destruction of protected tree";
    public static String PaintDamageItemLabel = "paint damage to bulldozer";
    public static String TotalSeparator = "----";
    public static String TotalLabel = "Total";
    public static String ThankYouMsg = "\nThankyou for using the Aconex site clearing simulator.\n";

    private static SysOutDelegate sysOutDelegate = (val) -> System.out.println(val);
    //Enter data using BufferReader
    private static BufferedReader reader =
            new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        /* Validate the number of parameters */
        if (args.length != 1) {
            usage();
            throw new java.lang.IllegalArgumentException(
                    String.format(ExpectedOneArgGotNMsg, args.length));
        }
        App app = new App(args[0]);

        sysOutDelegate.println(app.AppHeadingLabel);
        sysOutDelegate.println(app.getSiteMapHeading());

        SiteMap siteMap = new SiteMap();
        siteMap.readFromInputStream(app.getFileName());
        sysOutDelegate.println(siteMap.toString());

        sysOutDelegate.println(app.InitialBulldozerPositionMsg);

        ArrayList<String> commandList = new ArrayList<>();
        String input;
        do {
            System.out.print(app.MovementPrompt);
            // Reading data using readLine
            input = reader.readLine();
            commandList.add(input);
            sysOutDelegate.println(input);
        } while (!input.equals("q"));

        sysOutDelegate.println(app.CommandsEnteredHeading);

        String[] commandArray = commandList.stream().toArray(String[]::new);
        for (int i = 0; i < commandArray.length; i++) {
            sysOutDelegate.println(commandArray[i]);
            String[] commands = commandArray[i].trim().split(" +");
            for (String command : commands) {
                System.out.println(String.format("match: %1$s", command));
                if (command.matches("^[alrq]?$")) {
                    System.out.println(String.format("string command: %1$s", command));
                } else if (command.matches("^[0-9]+$")) {
                    System.out.println(String.format("integer command: %1$s", command));
                } else {
                    System.out.println(String.format("invalid command: %1$s", command));
                }
            }
        }

        sysOutDelegate.println(app.ThankYouMsg);

    }

    public App(String fileName) {
        setFileName(fileName);
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private static void usage() {
        sysOutDelegate.println(MustPassFileName);
    }

    public String getSiteMapHeading() {
        return String.format(SiteMapLabel, fileName);
    }

    protected static void setSysOutDelegate(SysOutDelegate val) {
        sysOutDelegate = val;
    }
}
```

</details>

<details><summary>References</summary>

- [junit4 docs](https://junit.org/junit4/)
- [Mockito docs](https://site.mockito.org)
- [Mockito user guide](https://docs.google.com/document/d/15mJ2Qrldx-J14ubTEnBj7nYN2FB8ap7xOn8GRAi24_A/edit)
- [javadoc comments](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)
- [Apache commons lang](https://commons.apache.org/proper/commons-lang/)
- [java commons lang3 doco](https://commons.apache.org/proper/commons-lang/javadocs/api-release/index.html)
</details>
