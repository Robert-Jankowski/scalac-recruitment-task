import Dependencies._

ThisBuild / scalaVersion := "2.13.5"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "com.example"
ThisBuild / organizationName := "example"

lazy val root = (project in file("."))
  .settings(
    name := "scalac-recruitment-task",
    libraryDependencies += zio,
    libraryDependencies += zioConfig,
    libraryDependencies += zioConfigMagnolia
  )
