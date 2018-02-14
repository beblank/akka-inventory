lazy val akkaHttpVersion    = "10.0.11"
lazy val akkaVersion        = "2.5.9"
lazy val scalaTestVersion   = "3.0.1"
lazy val slickVersion       = "3.2.1"
lazy val mysqlVersion       = "6.0.6"
lazy val flywayVersion      = "5.0.7"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.adit",
      scalaVersion    := "2.12.4"
    )),
    name := "Inventory",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-xml"        % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,

      "com.typesafe.slick" %% "slick"                 % slickVersion,
      "com.typesafe.slick" %% "slick-hikaricp"        % slickVersion,
      "mysql"              %  "mysql-connector-java"  % mysqlVersion,
      "org.flywaydb"       %  "flyway-core"           % flywayVersion,

      "com.typesafe.akka" %% "akka-http-testkit"    % akkaHttpVersion   % Test,
      "com.typesafe.akka" %% "akka-testkit"         % akkaVersion       % Test,
      "com.typesafe.akka" %% "akka-stream-testkit"  % akkaVersion       % Test,
      "org.scalatest"     %% "scalatest"            % scalaTestVersion  % Test
    )
  )
