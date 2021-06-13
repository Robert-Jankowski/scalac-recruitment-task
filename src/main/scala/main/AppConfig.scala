package main

import zio.config.ConfigDescriptor
import zio.config.magnolia.DeriveConfigDescriptor

case class AppConfig(in: String, out: String, threshold: Int)

object AppConfig {
  val descriptor: ConfigDescriptor[AppConfig] =
    DeriveConfigDescriptor.descriptor[AppConfig]

}
