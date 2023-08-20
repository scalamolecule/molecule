
- use zio-saga for transaction rollback?
https://github.com/VladKopanev/zio-saga/blob/master/examples/src/main/scala/com/vladkopanev/zio/saga/example/dao/SagaLogDao.scala

- Scala 3.2.1 sbt Test/compile creates errors while IntelliJ rebuilds project just fine. Seems like a classpath issue with sbt. But how does Intellij manage differently?

- 
- RethinkDb
- R2DBC
- Datascript (synchronous on JS?)






https://blog.datomic.com/2013/06/component-entities.html

"Components allow you to create substantial trees of data with nested maps, and then treat the entire tree as a single unit for lifecycle management (particularly retraction).  All nested items remain visible as first-class targets for query, so the shape of your data at transaction time does not dictate the shape of your queries.  This is a key value proposition of Datomic when compared to row, column, or document stores."