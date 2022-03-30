name := "hw_1_spark_dev"

version := "0.1"

scalaVersion := "2.13.8"


libraryDependencies += "io.circe" %% "circe-core" % "0.14.1"
libraryDependencies += "io.circe" %% "circe-generic" % "0.14.1"
libraryDependencies += "io.circe" %% "circe-parser" % "0.14.1"



//lazy val app = (project in file("app"))
//  .settings(
//    assembly / mainClass := Some("com.example.Main"),
//  )
//
//lazy val utils = (project in file("utils"))
//  .settings(
//    assembly / assemblyJarName := "utils.jar",
//  )