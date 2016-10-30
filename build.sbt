organization := "org.helianto"

lazy val root = (project in file("."))
  .enablePlugins(JavaServerAppPackaging)
  .enablePlugins(DockerPlugin)
  .settings(
    name := "iservport-politikei",
    version := "0.2.0.RELEASE",
    mainClass in (Compile) := Some("org.helianto.politikei.Application"),
    dockerBaseImage := "azul/zulu-openjdk:8",
    dockerUpdateLatest := true,
    dockerExposedPorts := Seq(8088),
    dockerExposedVolumes := Seq("/opt/data"),
    dockerRepository := Some("iservport"),
    libraryDependencies ++= Seq(
      "org.springframework.boot"           % "spring-boot-starter-web"      % "1.4.0.RELEASE",
      "org.springframework.boot"           % "spring-boot-starter-data-jpa" % "1.4.0.RELEASE",
      "org.springframework.security.oauth" % "spring-security-oauth2"       % "2.0.11.RELEASE",
      "org.springframework.security"       % "spring-security-jwt"          % "1.0.5.RELEASE",
      "com.h2database"                     % "h2"                % "1.4.192",
      "commons-io"                         % "commons-io"        % "2.4",
      "org.scalatest"                     %% "scalatest"         % "3.0.0"   % "test",
      "org.mockito"                        % "mockito-all"       % "1.10.19" % "test",
      "org.slf4j"                          % "slf4j-simple"      % "1.7.14"
    )
  )

scalaVersion := "2.11.8"

sbtVersion := "0.13.9"

licenses in ThisBuild := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
