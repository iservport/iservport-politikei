organization := "org.helianto"

lazy val root = (project in file("."))
  .enablePlugins(JavaServerAppPackaging)
  .enablePlugins(DockerPlugin)
  .settings(
    name := "iservport-politikei",
    version := "0.4.0.RELEASE",
    mainClass in Compile := Some("org.helianto.politikei.Application"),
    dockerBaseImage := "azul/zulu-openjdk:8",
    dockerUpdateLatest := true,
    dockerExposedPorts := Seq(8090),
    dockerExposedVolumes := Seq("/opt/data"),
    dockerRepository := Some("iservport"),
    libraryDependencies ++= Seq(
      "org.helianto"            %% "helianto-material-skin"       % "1.1.3.RELEASE",
      "org.springframework.boot" % "spring-boot-starter-actuator" % "1.4.1.RELEASE"
    )
  )

resolvers  ++= Seq(
  "Helianto Releases"  at "s3://maven.helianto.org/release",
  "Helianto Snapshots" at "s3://maven.helianto.org/snapshot"
)

scalaVersion := "2.11.8"

sbtVersion := "0.13.9"

licenses in ThisBuild := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0"))
