# Provide a solution to the `site-clearing-simulation` challenge<br/> in Java8 using `TDD` (`T`est `D`riven `D`envelopment)

`Click` on list items to expand and<br/>
`Click` on hyperlinks for more information.

## Building, Testing and Execution Instructions

<details><summary>Prerequisites on a mac</summary>

- Java 8 sdk
- Maven 3

```bash
$ java -version
java version "1.8.0_251"
Java(TM) SE Runtime Environment (build 1.8.0_251-b08)
Java HotSpot(TM) 64-Bit Server VM (build 25.251-b08, mixed mode)

$ export JAVA_HOME=`/usr/libexec/java_home -v "1.8*"`

$ mvn -v
Apache Maven 3.6.3 (cecedd343002696d0abb50b32b541b8a6ba2883f)
Maven home: /usr/local/Cellar/maven/3.6.3_1/libexec
Java version: 1.8.0_261, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk1.8.0_261.jdk/Contents/Home/jre
Default locale: en_AU, platform encoding: UTF-8
OS name: "mac os x", version: "10.15.4", arch: "x86_64", family: "mac"
```

- References
  - [Download latest java 8 sdk (jdk-8u261-macosx-x64.dmg)](https://www.oracle.com/au/java/technologies/javase/javase-jdk8-downloads.html)
  - [Installing Apache Maven](https://maven.apache.org/install.html)

</details>

<details><summary>Get repo, build, package, test</summary>

- Clone repository
- Maven package to build, package and test

```bash
$ git clone https://github.com/shawfire/site-clearing-simulation.git

$ cd site-clearing-simulation/scs-java8

$ mvn clean package
[INFO] Scanning for projects...
[INFO]
[INFO] ---------------------< net.shawfire.scs:scs-java8 >---------------------
[INFO] Building scs-java8 1.0
[INFO] --------------------------------[ jar ]---------------------------------
[INFO]
[INFO] --- maven-clean-plugin:2.5:clean (default-clean) @ scs-java8 ---
[INFO] Deleting /Users/johnshaw/dev/site-clearing-simulation/scs-java8/target
[INFO]
[INFO] --- maven-resources-plugin:2.6:resources (default-resources) @ scs-java8 ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 1 resource
[INFO]
[INFO] --- maven-compiler-plugin:3.1:compile (default-compile) @ scs-java8 ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 17 source files to /Users/johnshaw/dev/site-clearing-simulation/scs-java8/target/classes
[INFO]
[INFO] --- maven-resources-plugin:2.6:testResources (default-testResources) @ scs-java8 ---
[INFO] Using 'UTF-8' encoding to copy filtered resources.
[INFO] Copying 4 resources
[INFO]
[INFO] --- maven-compiler-plugin:3.1:testCompile (default-testCompile) @ scs-java8 ---
[INFO] Changes detected - recompiling the module!
[INFO] Compiling 9 source files to /Users/johnshaw/dev/site-clearing-simulation/scs-java8/target/test-classes
[INFO]
[INFO] --- maven-surefire-plugin:2.12.4:test (default-test) @ scs-java8 ---
[INFO] Surefire report directory: /Users/johnshaw/dev/site-clearing-simulation/scs-java8/target/surefire-reports

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running net.shawfire.scs.AppTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.241 sec
Running net.shawfire.scs.CostsTest
Tests run: 3, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec
Running net.shawfire.scs.CommandPojoTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec
Running net.shawfire.scs.SiteMapTest
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.001 sec
Running net.shawfire.scs.CommandTest
Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 sec
Running net.shawfire.scs.BulldozerTest
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.004 sec
Running net.shawfire.scs.DirectionTest
Tests run: 2, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0 sec

Results :

Tests run: 21, Failures: 0, Errors: 0, Skipped: 0

[INFO]
[INFO] --- maven-jar-plugin:2.4:jar (default-jar) @ scs-java8 ---
[INFO] Building jar: /Users/johnshaw/dev/site-clearing-simulation/scs-java8/target/scs-java8-1.0.jar
[INFO]
[INFO] --- maven-shade-plugin:3.2.0:shade (default) @ scs-java8 ---
[INFO] Replacing original artifact with shaded artifact.
[INFO] Replacing /Users/johnshaw/dev/site-clearing-simulation/scs-java8/target/scs-java8-1.0.jar with /Users/johnshaw/dev/site-clearing-simulation/scs-java8/target/scs-java8-1.0-shaded.jar
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.212 s
[INFO] Finished at: 2020-08-03T18:08:15+10:00
[INFO] ------------------------------------------------------------------------
$
```

</details>

<details><summary>Execute packaged jar - successful simulation</summary>

- Execute the package jar file

```bash
$ java -jar target/scs-java8-1.0.jar /test-site-map.txt

Welcome to the site clearing simulator.

This is a map of the site (read from file: /test-site-map.txt):

o o t o o o o o o o
o o o o o o o T o o
r r r o o o o T o o
r r r r o o o o o o
r r r r r t o o o o

The bulldozer is currently located at the Northern edge of the site, immediately to the West of the site, and facing East.

(l)eft, (r)ight, (a)dvance <n>, (q)uit: a 4
(l)eft, (r)ight, (a)dvance <n>, (q)uit: r
(l)eft, (r)ight, (a)dvance <n>, (q)uit: Advance 4
(l)eft, (r)ight, (a)dvance <n>, (q)uit: Left
(l)eft, (r)ight, (a)dvance <n>, (q)uit: advance 2
(l)eft, (r)ight, (a)dvance <n>, (q)uit: a 4
(l)eft, (r)ight, (a)dvance <n>, (q)uit: l
(l)eft, (r)ight, (a)dvance <n>, (q)uit: q

The simulation has ended at your request. These are the commands you issued:

advance 4, turn right, advance 4, turn left, advance 2, advance 4, turn left, quit

The costs for this land clearing operation were:

Item                           Quantity      Cost
communication overhead                7         7
fuel usage                           19        19
uncleared squares                    34       102
destruction of protected tree         0         0
paint damage to bulldozer             1         2
----
Total                                         130

Thankyou for using the Aconex site clearing simulator.

$

```

</details>

## Documenting the approach taken

<details><summary>Javadoc maven plugin</summary>

- Open the javadoc index.html file up on your local browser
  - ./site-clearing-simulation/scs-java8/target/site/apidocs/index.html
    - [index.html](./scs-java8/target/site/apidocs/index.html)

```bash
$ mvn javadoc:javadoc
```

- Reference
  - [Generate Javadocs As Part Of Project Reports](https://maven.apache.org/plugins/maven-javadoc-plugin/usage.html)

</details>

<details><summary>Approach</summary>

- Used a `TDD` (`T`est `D`riven `D`esign `A`pproach) within IntelliJ IDEA
- Used `Java 8`, `Junit 4` and `Mockito` (Used to mock the `input` and `output` `streams`)
- Used Maven directory structure and a `pom.xml` to support command line build, test, package and run.
- Each each Source file has a `javadoc` comment which explains what part that file plays in the solution.
- Each feature and capability of the App has a corresponding test due to the `TDD` approach that has been followed.
</details>

<details><summary>Design Brief</summary>

- The main `App` class for the site clearing:
  - Expects a the site map text file as the one parameter on the command line.
  - The site map text file is found by looking in the `classpath`.
  - For the purpose of testing a site map text file (`/test-site-map.txt`) is provided in the `resources` directory.
  - After validating the arguments; an instance of the `SiteMap` is created. The
    - `SiteMap` reads the site map text file provided as the first parameter.
  - An instance of the `Bulldozer` class is then instantiated with the `siteMap` created above.
    - The `bulldozer` accepts commands form the input stream, and executes each command as it is received.
    - Once the simulation is complete, the bulldozer displays all the `costs` (using a `Costs` class) it incurred during the simulation.
- The `Costs` class enables incrementing quantities of on `ItemType` `enum` `class`.
  - The `Costs` class also displays a summary table of `ItemType` `quantities` and `costs`.
- The `ItemType` `enum` contains all the categories for which `quantities` and `costs` are calculated.
  - The item type `descriptions` are used as labels in the cost summary table.
  - This class also contains the information to display the `costs table` in the `correct format`, with aligned columns.
- `ItemCostLookup` provides a lookup for `ItemType` costs per individual item.
  - To get the `cost` the `quantity` is multiplied by the lookup amount for an `ItemType`.
- `SquareType` `enum` provides the type that is used to store the state of each location on the `SiteMap`.
  - Whether the square has been `CLEARED`, is `ROCKY`, has a removable tree (`TREE_REMOVAL`), contains a protected tree (`PRESERVE_TREE`) and or is `PLAIN`.
- `CommandType` is an enum class for Command Types.
  - The `essential first character` of each command is associated with it's description.
  - This class supports:
    - the display of the command's `description`,
    - Parsing a command to it's equivalent `one letter unique prefix`,
    - Provides a `regular expression` that validate if a line contains only one valid command.
- The `CommandPojo` caters for the `Advance` command, which also has a corresponding `amount`.
  - Each command has an `Optional` `amount`.
  - This class enables a Command to be compared, displayed or stored in a Map lookup.
- The `ChangeInDirection` `enum` representing a change in direction (`LEFT` or `RIGHT`) for the `bulldozer`.
  - These value map to the underlying direction commands in the `CommandType` enum.
- The `Direction` enum provides a mechanism to change the direction `left` or `right` in the four different direction orientations namely: `EAST`, `SOUTH`, `WEST`, and `NORTH`
- The `Command` class is used to read a command from the input stream.
  - If the command is an invalid command; another command will be requested.
  - Only one command is processed per line received.
  - Any additional spaces before, embedding or suffixing a command are ignored.
  - Only the first letter of the command is used and the case is ignored
  - (for example: `q` `Quit` are equivalent and will stop the simulation).
  - As the `Advance` command also has a corresponding amount the `CommandPojo` is returned which supports an `Optional` `amount`.

</details>

<details><summary>References</summary>

- [junit4 docs](https://junit.org/junit4/)
- [Mockito docs](https://site.mockito.org)
- [javadoc comments](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)
  </details>

## Testing Edge cases

<details><summary>Execute packaged jar - Remove Protected Tree - failed simulation</summary>

```bash
$ java -jar target/scs-java8-1.0.jar /test-site-map.txt

Welcome to the site clearing simulator.

This is a map of the site (read from file: /test-site-map.txt):

o o t o o o o o o o
o o o o o o o T o o
r r r o o o o T o o
r r r r o o o o o o
r r r r r t o o o o

The bulldozer is currently located at the Northern edge of the site, immediately to the West of the site, and facing East.

(l)eft, (r)ight, (a)dvance <n>, (q)uit: a 1
(l)eft, (r)ight, (a)dvance <n>, (q)uit: r
(l)eft, (r)ight, (a)dvance <n>, (q)uit: a 1
(l)eft, (r)ight, (a)dvance <n>, (q)uit: l
(l)eft, (r)ight, (a)dvance <n>, (q)uit: a 7

Simulation ended prematurely. Reason - Attempt to move bulldozer to protected square type: PRESERVE_TREE

These are the commands you issued:

advance 1, turn right, advance 1, turn left, advance 7

The costs for this land clearing operation were:

Item                           Quantity      Cost
communication overhead                5         5
fuel usage                            8         8
uncleared squares                    40       120
destruction of protected tree         1        10
paint damage to bulldozer             0         0
----
Total                                         143

Thankyou for using the Aconex site clearing simulator.

$
```

</details>

<details><summary>Execute packaged jar - Move bulldozer beyond site boundary - failed simulation</summary>

```bash
$ java -jar target/scs-java8-1.0.jar /test-site-map.txt

Welcome to the site clearing simulator.

This is a map of the site (read from file: /test-site-map.txt):

o o t o o o o o o o
o o o o o o o T o o
r r r o o o o T o o
r r r r o o o o o o
r r r r r t o o o o

The bulldozer is currently located at the Northern edge of the site, immediately to the West of the site, and facing East.

(l)eft, (r)ight, (a)dvance <n>, (q)uit: Advance 11

Simulation ended prematurely. Reason - Attempt to drive bulldozer out of site bearing: EAST

These are the commands you issued:

advance 11

The costs for this land clearing operation were:

Item                           Quantity      Cost
communication overhead                1         1
fuel usage                           11        11
uncleared squares                    38       114
destruction of protected tree         0         0
paint damage to bulldozer             1         2
----
Total                                         128

Thankyou for using the Aconex site clearing simulator.

$
```

</details>
