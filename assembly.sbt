assembly / assemblyMergeStrategy in assembly := {
  case PathList("org", "apache") => MergeStrategy.last
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

assemblyJarName := s"${name.value}_${scalaBinaryVersion.value}-${version.value}.jar"