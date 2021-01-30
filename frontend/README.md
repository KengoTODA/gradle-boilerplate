## Frontend module

This subproject is responsible to generate HTML, JS and other assets for web application.

Unlike other subprojects, this subproject follows the layout of `npm` project.
It runs webpack to generates files to `/dist` dir.

Gradle will fill the gap between this subproject and others by [frontend-gradle-plugin](https://siouan.github.io/frontend-gradle-plugin/) by mapping the generated files by webpack to output of the Gradle task.
