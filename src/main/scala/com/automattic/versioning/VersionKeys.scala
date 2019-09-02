package com.automattic.versioning

import sbt._
import complete.DefaultParsers._

trait VersionKeys {
  object upgradeParser {
    lazy val increment = Space ~> (patch | minor | major | patchBeta | minorBeta | majorBeta)
    lazy val patch = token("patch" ^^^ IncrementMode.Patch)
    lazy val minor = token("minor" ^^^ IncrementMode.Minor)
    lazy val major = token("major" ^^^ IncrementMode.Major)
    lazy val patchBeta = token("patch-beta" ^^^ IncrementMode.PatchBeta)
    lazy val minorBeta = token("minor-beta" ^^^ IncrementMode.MinorBeta)
    lazy val majorBeta = token("major-beta" ^^^ IncrementMode.MajorBeta)
  }
  val versionFile = settingKey[File]("Location of a VERSION file")
  val isClient = settingKey[Boolean]("Whether this version is a client version")
  val beforeUpgrade = settingKey[Option[(String, String) => Unit]](
    "Execute a command before upgrading the version"
  )
  val afterUpgrade = settingKey[Option[(String, String) => Unit]](
    "Execute a command after upgrading the version"
  )
  val replaceVersions =
    settingKey[Map[File, (String, String)]]("Update files with version numbers")
}
