# Rents Map - Geohash Example

![Rents Map Screen Capture](demo.gif)

## Building

### Before You Start

To build you will need [Git](http://help.github.com/set-up-git-redirect) and [JDK 11](https://www.oracle.com/technetwork/java/javase/downloads/index.html) or later.

### Get the Source Code

```bash
git clone git@github.com:evgeniy-khist/geohash-example.git
cd geohash-example
```

### Build from the Command Line

This project uses a [Gradle](http://gradle.org/) build.
The instructions below use [Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html) from the root of the source tree.
The wrapper script serves as a cross-platform, self-contained bootstrap mechanism for the build system.

To compile, test and build JAR use:

```bash
./gradlew clean build -i
```

## Running Locally

To run the project locally use:

```bash
./gradlew clean bootRun
```

Open URL [http://localhost:8080/geohash-example/](http://localhost:8080/geohash-example/) in a browser.

## Contributing

This project follows [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html).

You may also be interested to import [intellij-java-google-style.xml](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml) IntelliJ IDEA code style XML.

## License

The Geohash Example is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).
