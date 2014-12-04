# Project Searchbird

Welcome to your searchbird project!  To make sure things are working
properly, you may want to:
    
1.  *start three service*  
    ```python
    ./sbt 'run -f config/development.scala -D shard=0'
    ./sbt 'run -f config/development.scala -D shard=1'
    ./sbt 'run -f config/development.scala'
    ```

2.  *connect to service*
    ```python
    ./console localhost 9000  
    ./console localhost 9001  
    ./console localhost 9999  
    ```
    
For detail information, view the [searchbird refer](https://twitter.github.io/scala_school/zh_cn/searchbird.html)
