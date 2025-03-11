rootProject.name = "newsfeed-platform"
include("api")
include("api:newsfeed-searcher")
findProject(":api:newsfeed-searcher")?.name = "newsfeed-searcher"
include("api:newsfeed-ingestor")
findProject(":api:newsfeed-ingestor")?.name = "newsfeed-ingestor"
include("api:newsfeed-parser")
findProject(":api:newsfeed-parser")?.name = "newsfeed-parser"
include("libraries")
include("libraries:logging-util")
findProject(":libraries:logging-util")?.name = "logging-util"
include("infrastructure")
