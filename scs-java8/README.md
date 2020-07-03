# Provide a solution to the `site-clearing-simulation` challenge<br/> in Java8 using `TDD` (`T`est `D`riven `D`envelopment)

`Click` on list items to expand and<br/>
`Click` on hyperlinks for more information.

<details><summary>TODO</summary>

- Validate site clearing simulation test file input

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

<details><summary>First Test to read a site map text file</summary>

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
