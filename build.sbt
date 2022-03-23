name := "kafka-spark-usages"

version := "0.0.1"

scalaVersion := "2.12.13"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-sql" % "3.2.0" % "provided",
    "org.scalatest" %% "scalatest" % "3.0.1" % "test",
)

mainClass in (Compile, run) := Some("com.domain.Main")

javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled")

//assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)