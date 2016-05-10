lazy val commonSettings = Seq(
  organization := "org.sjq",
  version := "0.0.1",
  scalaVersion := "2.11.8",
  libraryDependencies ++= Seq(
    "org.apache.spark" % "spark-core_2.11" % "1.6.1",
    "com.alibaba" % "fastjson" % "1.2.5",
    "com.chuusai" % "shapeless_2.11" % "2.3.0",
    "org.scalatest" % "scalatest_2.11" % "2.2.6"
  ),
  isSnapshot := true
)

lazy val core = (project in file("core")).
  settings(commonSettings: _*).
  settings(
    name := "spark-jq-core"
  )



