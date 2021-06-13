package main

import zio._
import zio.console._
import zio.config._
import main.AppConfig
import helpers._

object Main extends zio.App {

  def run(args: List[String]) =
    appLogic.provideLayer(configLayer ++ Console.live).exitCode

  val appLogic =
    for {
      appConfig <- getConfig[AppConfig]
      files <- listOfFiles(appConfig.in)
      _ <- ZIO.foreachPar(files)(processImage(appConfig))
    } yield ()

  val configLayer =
    ZConfig.fromPropertiesFile(
      "src/main/scala/main/config.conf",
      AppConfig.descriptor
    )
  val app = appLogic.run

}
