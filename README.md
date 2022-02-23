# rm-maven-cucumber-plugin

Ein einfaches Maven-Plugin zum erstellen eines einseitigen Berichts für Cucumber.

## Erzeugen des Berichts als Teil der Projektberichte

Um den Cucumber-Bericht als Teil der Projekt-Seite zu generieren, füge folgenden Abschnitt in den `<reporting>` Deiner POM ein:

```xml
<project>
  ...
  <reporting>
    <plugins>
      <plugin>
        <groupId>de.rene_majewski.maven</groupId>
        <artifactId>rm-cucumber-report-plugin</artifactId>
        <version>0.1</version>
        <configuration>
          <inputJsonFile>target/cucumber.json</inputJsonFile>
        </configuration>
      </plugin>
    </plugins>
  </reporting>
  ...
</project>
```

Den erstellten Bericht findest Du unt _${basedir}/target/site/cucumber-report.html_.
