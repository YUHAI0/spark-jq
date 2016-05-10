lazy val commonSettings = Seq(
  organization := "org.sjq",
  version := "0.0.1",
  scalaVersion := "2.10.5",
  scalaBinaryVersion := "2.10",
  libraryDependencies ++= Seq(
    "org.apache.spark" % "spark-core_2.10" % "1.6.1",
    "com.chuusai" % "shapeless_2.10" % "2.3.0",
    "org.scalatest" % "scalatest_2.10" % "2.2.6",
    "com.alibaba" % "fastjson" % "1.2.5"
  ),
  isSnapshot := true
)

lazy val core = (project in file("core")).
  settings(commonSettings: _*).
  settings(
    name := "spark-jq-core"
  )



