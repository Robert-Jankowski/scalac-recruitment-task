package main

import zio._
import zio.console._
import java.io.File
import javax.imageio.ImageIO
import scala.util.Try
import java.awt.image.BufferedImage
import java.awt.Color
import java.nio.file.Paths

object helpers {

  def listOfFiles(directory: String): Task[List[File]] =
    ZIO.fromTry(Try(new File(directory).listFiles().toList))

  def openImage(file: File): Task[BufferedImage] =
    ZIO.fromTry(Try(ImageIO.read(file)))

  def lightnessFromRGB(pixel: Color): Double = {
    val red = pixel.getRed
    val green = pixel.getGreen
    val blue = pixel.getBlue

    ((red max green max blue) + (red min green min blue)) / 2
  }

  def rateImage(image: BufferedImage, threshold: Int): (String, Int) = {
    val width = image.getWidth()
    val height = image.getHeight()
    val pixels = width * height

    val allPixels = for {
      x <- 0 to width - 1
      y <- 0 to height - 1
    } yield (x, y)

    val sum = allPixels.foldLeft(0.0)((acc, pixel) =>
      lightnessFromRGB(new Color(image.getRGB(pixel._1, pixel._2))) + acc
    )

    val rating = ((sum / (pixels * 255)) * 100).toInt
    val fit = rating match {
      case x if (x > threshold) => "bright"
      case _                    => "dark"
    }
    (fit, rating)
  }

  def createFileName(file: File, fit: String, rating: Int): String = {
    val fileToString = Paths.get(file.getPath()).getFileName.toString()
    val extension = fileToString.split("\\.").last
    val name = fileToString.split("\\.").head
    name + "_" + fit + "_" + rating.toString() + "." + extension
  }

  def getExtension(file: File): String =
    Paths.get(file.getPath()).getFileName.toString().split("\\.").last

  def saveImage(
      image: BufferedImage,
      name: String,
      format: String
  ): Task[Boolean] =
    ZIO.fromTry(Try(ImageIO.write(image, format, new File(name))))

  def processImage(appConfig: AppConfig)(
      file: File
  ): RIO[Console, Either[Throwable, Boolean]] =
    (for {
      image <- openImage(file)
      (fit, rating) = rateImage(image, appConfig.threshold)
      _ <- saveImage(
        image,
        appConfig.out + "/" + createFileName(file, fit, rating),
        getExtension(file)
      )
    } yield true).either
}
