crossScalaVersions := Seq("2.10.5", "2.11.5")

lazy val sjq = project.settings(
  organization := "com.magicsoho",
  name := "spark-jq",
  isSnapshot := true,
  publishMavenStyle := true,
  libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "1.6.1",
    "com.chuusai" %% "shapeless" % "2.3.0",
    "org.scalatest" %% "scalatest" % "2.2.6",
    "com.alibaba" % "fastjson" % "1.2.5"),
  version := {
    if (isSnapshot.value)
      "0.1-SNAPSHOT"
    else
      "0.1.1"
  },
  publishTo :=  {
    val nexus = "https://oss.sonatype.org"
    println("VERSION " + version.value)
    if (isSnapshot.value)
      Some("snapshots" at nexus + "/content/repositories/snapshots")
    else
      Some("releases"  at nexus + "/service/local/staging/deploy/maven2")
  },
  pomExtra :=
    <licenses>
      <license>
        <name>MIT</name>
        <url>http://github.com/yuhai1023/spark-jq/LICENSE</url>
        <distribution>repo</distribution>
      </license>
    </licenses>
)

