assembly / assemblyMergeStrategy in assembly := {
  case PathList("org", "apache") => MergeStrategy.last
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

assemblyShadeRules in assembly := Seq(
  ShadeRule.rename("com.google.protobuf.**" -> "shadeproto.@1").inAll,
  ShadeRule.rename("scala.collection.compat.**" -> "shadecompat.@1").inAll
)

assemblyJarName := s"${name.value}_${scalaBinaryVersion.value}-${version.value}.jar"