lazy val commonSettings = Seq(
  organization := "com.magicsoho",
  version := "0.1.0",
  scalaVersion := "2.11.5",
  libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-core" % "1.6.1",
    "com.chuusai" %% "shapeless" % "2.3.0",
    "org.scalatest" %% "scalatest" % "2.2.6",
    "com.alibaba" % "fastjson" % "1.2.5"
  ),
  isSnapshot := true
)

lazy val sjq = project.
  settings(commonSettings: _*).
  settings(
    name := "spark-jq"
  )



