# Project Searchbird

Welcome to your searchbird project!  To make sure things are working
properly, you may want to:

1.  generate the service from template  
    scala-bootstrapper searchbird
    
2.  start three service  
    ./sbt 'run -f config/development.scala -D shard=0'  
    ./sbt 'run -f config/development.scala -D shard=1'  
    ./sbt 'run -f config/development.scala'  

3.  connect to service  
    ./console localhost 9000  
    ./console localhost 9001  
    ./console localhost 9999  
    
    
For detail information, view the [searchbird refer](https://twitter.github.io/scala_school/zh_cn/searchbird.html)
