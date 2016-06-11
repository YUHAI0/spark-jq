val binaryVersion = "2.11"

val commonSettings = Seq(
  organization := "org.sjq",
  version := "0.1.0",
  scalaVersion := (binaryVersion + ".5"),
  scalaBinaryVersion := binaryVersion,
  libraryDependencies ++= Seq(
    "org.apache.spark" % ("spark-core_" + binaryVersion) % "1.6.1",
    "com.chuusai" % ("shapeless_" + binaryVersion) % "2.3.0",
    "org.scalatest" % ("scalatest_" + binaryVersion) % "2.2.6",
    "com.alibaba" % "fastjson" % "1.2.5"
  ),
  isSnapshot := true
)

lazy val core = (project in file("core")).
  settings(commonSettings: _*).
  settings(
    name := "spark-jq-core"
  )



