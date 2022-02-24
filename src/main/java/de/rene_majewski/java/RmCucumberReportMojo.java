/**
 * Copyright (C) 2022 René Majewski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package de.rene_majewski.java;


import java.io.File;
import java.io.FileReader;
import java.util.Locale;
import com.google.gson.Gson;
import org.apache.maven.doxia.sink.Sink;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.apache.maven.reporting.AbstractMavenReport;
import org.apache.maven.reporting.MavenReportException;
import de.rene_majewski.java.cucumber_import.CucumberReport;
import de.rene_majewski.java.report.Report;

/**
 * Hauptklasse des Mojo.
 *
 * @since 0.1.0
 * @author René Majewski
 */
@Mojo(
  name = "report",
  defaultPhase = LifecyclePhase.SITE,
  requiresDependencyResolution = ResolutionScope.RUNTIME,
  requiresProject = true,
  threadSafe = true
)
public class RmCucumberReportMojo extends AbstractMavenReport {
  @Parameter(defaultValue = "${project}", readonly = true)
  private MavenProject project;

  @Parameter(defaultValue = "target/cucumber.json", property = "inputJsonFile", required = true)
  private File inputJsonFile;

  @Override
  public String getDescription(Locale arg0) {
    return "Cucumber-Report";
  }

  @Override
  public String getName(Locale arg0) {
    return "Cucumber";
  }

  @Override
  public String getOutputName() {
    return "cucumber";
  }

  @Override
  protected void executeReport(Locale locale) throws MavenReportException {
    // Cucumber-Daten einlesen
    if (!inputJsonFile.exists() || !inputJsonFile.isFile()) {
      throw new MavenReportException(inputJsonFile.toString() + " not exists");
    }

    CucumberReport data[] = null;

    try {
      final Gson gson = new Gson();
      data = gson.fromJson(new FileReader(inputJsonFile), CucumberReport[].class);
    } catch (Exception e) {
      getLog().error("Error parsing file " + inputJsonFile.toString());
      getLog().error(e);
    }

    // Baum erstellen
    Report report = new Report(data);

    // Report erstellen
    Sink sink = getSink();
    if (sink == null) {
      throw new MavenReportException("Could not get Doxia sink");
    }

    // Report title
    sink.head();
    sink.title();
    sink.text(getDescription(locale));
    sink.title_();
    sink.head_();

    // Body-Abschnitt
    sink.body();

    // Erzeugung der Report-Seiten
    report.writeToReport(sink);

    // Body-Abschnitt beenden
    sink.body_();
  }
}
