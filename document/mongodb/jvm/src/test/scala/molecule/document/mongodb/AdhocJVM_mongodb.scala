package molecule.document.mongodb

import molecule.base.ast._
import molecule.base.error.{ExecutionError, ModelError, ValidationErrors}
import molecule.core.util.AggrUtils
import molecule.core.util.Executor._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.{TestSuiteArray_mongodb, TestSuite_mongodb}
import utest._
import scala.language.implicitConversions
import molecule.boilerplate.ast.Model._
import molecule.coreTests.dataModels.core.dsl.Refs.A
import molecule.document.mongodb.AdhocJVM_mongodb.{int2, int3, int4}
import scala.collection.immutable.Set
import scala.concurrent.Future
import scala.util.Random

//object AdhocJVM_mongodb extends TestSuite_mongodb with AggrUtils {
object AdhocJVM_mongodb extends TestSuiteArray_mongodb with AggrUtils {


  override lazy val tests = Tests {

    "types" - types { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Types._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)

      for {

//        id <- Ns.intSeq(List(int1)).save.i.transact.map(_.id)
//
//        // Add value to end of Seq
//        _ <- Ns(id).intSeq.add(int2, int3).update.i.transact
//        _ <- Ns.intSeq.query.get.map(_.head ==> List(int1, int2))


        id <- Ns.intMap(Map("a" -> int0)).save.i.transact.map(_.id)

        // Adding pair with existing key replaces the value
        _ <- Ns(id).intMap.add("a" -> int1).update.i.transact
        _ <- Ns.intMap.query.get.map(_.head ==> Map(pint1))


      } yield ()
    }


    "refs" - refs { implicit conn =>
      import molecule.coreTests.dataModels.core.dsl.Refs._
      implicit val tolerantDouble = tolerantDoubleEquality(toleranceDouble)
      for {

        _ <- A.i.B.iSet.insert((1, Set.empty[Int])).transact
        id <- A.iSet(Set(1)).OwnB.iSet(Set(2)).C.iSet(Set(3)).save.transact.map(_.id)

        // todo: Why do these two require/not require not-equal null?
        _ <- A.i.B.iSet_.query.get.map(_ ==> Nil)
        _ <- A.iSet.OwnB.iSet.C.iSet.query.get.map(_ ==> List((Set(1), Set(2), Set(3))))
        /*
            {
              "$lookup": {
                "from": "C",
                "localField": "ownB.c",
                "foreignField": "_id",
                "as": "ownB.c",
                "pipeline": [
                  {
                    "$match": {
                      "$and": [
        //                {        // specific example above solved without this
        //                  "ownB.iSet": {
        //                    "$ne": null
        //                  }
        //                },
                        {
                          "ownB.iSet": {
                            "$ne": []
                          }
                        }
                      ]
                    }
                  }
                ]
              }
            },
         */

        //        _ <- rawTransact(
        //          s"""{
        //             |  "_action": "insert",
        //             |  "_selfJoins": 0,
        //             |  "A": [
        //             |    {
        //             |      "i": 1,
        //             |      "b": "1"
        //             |    },
        //             |    {
        //             |      "i": 2,
        //             |      "b": "2"
        //             |    }
        //             |  ],
        //             |  "B": [
        //             |    {
        //             |      "_id": "1",
        //             |      "i": 10,
        //             |      "cc": ["3", "4"]
        //             |    },
        //             |    {
        //             |      "_id": "2",
        //             |      "i": 20,
        //             |      "cc": []
        //             |    }
        //             |  ],
        //             |  "C": [
        //             |    {
        //             |      "i": 1,
        //             |      "d": "5",
        //             |      "_id": "3"
        //             |    },
        //             |    {
        //             |      "i": 3,
        //             |      "d": "6",
        //             |      "_id": "4"
        //             |    }
        //             |  ],
        //             |  "D": [
        //             |    {
        //             |      "_id": "5",
        //             |      "i": 2
        //             |    },
        //             |    {
        //             |      "_id": "6",
        //             |      "i": 4
        //             |    }
        //             |  ]
        //             |}
        //             |
        //             |
        //             |""".stripMargin
        //        )
        //
        //        _ <- A.id("1", "2").i.a1.B.i.Cc.*?(C.i.a1.D.i).query.i.get.map(_ ==> List(
        //          ("1", 1, 10, List((1, 2), (3, 4))),
        //          ("2", 2, 20, Nil),
        //        ))

        //        _ <- A.id(a1, a2).i.a1.B.i.Cc.*?(C.i.a1.D.i).query.i.get.map(_ ==> List(
        //          (a1, 1, 10, List((1, 2), (3, 4))),
        //          (a2, 2, 20, Nil),
        //        ))

      } yield ()
    }
    /*
    /Users/mg/Library/Java/JavaVirtualMachines/openjdk-20.0.1/Contents/Home/bin/java "-javaagent:/Users/mg/Applications/IntelliJ IDEA Community Edition.app/Contents/lib/idea_rt.jar=52449:/Users/mg/Applications/IntelliJ IDEA Community Edition.app/Contents/bin" -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath "/Users/mg/Library/Application Support/JetBrains/IdeaIC2023.3/plugins/Scala/lib/runners.jar:/Users/mg/molecule/molecule/molecule/document/mongodb/jvm/target/scala-2.12/test-classes:/Users/mg/molecule/molecule/molecule/document/mongodb/jvm/target/scala-2.12/classes:/Users/mg/molecule/molecule/molecule/base/shared/target/test-classes:/Users/mg/molecule/molecule/molecule/base/shared/target/classes:/Users/mg/molecule/molecule/molecule/base/jvm/target/scala-2.12/classes:/Users/mg/molecule/molecule/molecule/boilerplate/shared/target/test-classes:/Users/mg/molecule/molecule/molecule/boilerplate/shared/target/classes:/Users/mg/molecule/molecule/molecule/boilerplate/jvm/target/scala-2.12/test-classes:/Users/mg/molecule/molecule/molecule/boilerplate/jvm/target/scala-2.12/classes:/Users/mg/molecule/molecule/molecule/core/shared/target/test-classes:/Users/mg/molecule/molecule/molecule/core/shared/target/classes:/Users/mg/molecule/molecule/molecule/core/jvm/target/scala-2.12/test-classes:/Users/mg/molecule/molecule/molecule/core/jvm/target/scala-2.12/classes:/Users/mg/molecule/molecule/molecule/coreTests/shared/target/test-classes:/Users/mg/molecule/molecule/molecule/coreTests/shared/target/classes:/Users/mg/molecule/molecule/molecule/coreTests/jvm/target/scala-2.12/test-classes:/Users/mg/molecule/molecule/molecule/coreTests/jvm/target/scala-2.12/classes:/Users/mg/molecule/molecule/molecule/document/mongodb/shared/target/test-classes:/Users/mg/molecule/molecule/molecule/document/mongodb/shared/target/classes:/Users/mg/molecule/molecule/molecule/coreTests/jvm/lib/2.12/molecule-coreTests-jvm-sources.jar:/Users/mg/molecule/molecule/molecule/coreTests/jvm/lib/2.12/molecule-coreTests-jvm.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/ch/megard/akka-http-cors_2.12/1.2.0/akka-http-cors_2.12-1.2.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-classic/1.5.0/logback-classic-1.5.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/ch/qos/logback/logback-core/1.5.0/logback-core-1.5.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/fasterxml/jackson/core/jackson-annotations/2.10.3/jackson-annotations-2.10.3.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/docker-java/docker-java-api/3.3.5/docker-java-api-3.3.5.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/docker-java/docker-java-transport-zerodep/3.3.5/docker-java-transport-zerodep-3.3.5.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/github/docker-java/docker-java-transport/3.3.5/docker-java-transport-3.3.5.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/sourcecode_2.12/0.3.1/sourcecode_2.12-0.3.1.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/lihaoyi/utest_2.12/0.8.2/utest_2.12-0.8.2.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/outr/moduload_2.12/1.1.7/moduload_2.12-1.1.7.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/outr/perfolation_2.12/1.2.9/perfolation_2.12-1.2.9.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/outr/scribe_2.12/3.13.0/scribe_2.12-3.13.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-actor-typed_2.12/2.8.3/akka-actor-typed_2.12-2.8.3.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-actor_2.12/2.8.3/akka-actor_2.12-2.8.3.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-http-core_2.12/10.2.10/akka-http-core_2.12-10.2.10.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-http_2.12/10.2.10/akka-http_2.12-10.2.10.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-parsing_2.12/10.2.10/akka-parsing_2.12-10.2.10.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-protobuf-v3_2.12/2.8.3/akka-protobuf-v3_2.12-2.8.3.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-slf4j_2.12/2.8.3/akka-slf4j_2.12-2.8.3.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/akka/akka-stream_2.12/2.8.3/akka-stream_2.12-2.8.3.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/config/1.4.2/config-1.4.2.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/com/typesafe/ssl-config-core_2.12/0.6.1/ssl-config-core_2.12-0.6.1.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/dev/zio/izumi-reflect-thirdparty-boopickle-shaded_2.12/2.3.8/izumi-reflect-thirdparty-boopickle-shaded_2.12-2.3.8.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/dev/zio/izumi-reflect_2.12/2.3.8/izumi-reflect_2.12-2.3.8.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/dev/zio/zio-internal-macros_2.12/2.0.15/zio-internal-macros_2.12-2.0.15.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/dev/zio/zio-stacktracer_2.12/2.0.15/zio-stacktracer_2.12-2.0.15.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/dev/zio/zio-streams_2.12/2.0.15/zio-streams_2.12-2.0.15.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/dev/zio/zio-test-sbt_2.12/2.0.15/zio-test-sbt_2.12-2.0.15.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/dev/zio/zio-test_2.12/2.0.15/zio-test_2.12-2.0.15.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/dev/zio/zio_2.12/2.0.15/zio_2.12-2.0.15.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/github/cquiroz/scala-java-time_2.12/2.5.0/scala-java-time_2.12-2.5.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/io/suzaku/boopickle_2.12/1.4.0/boopickle_2.12-1.4.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/junit/junit/4.13.2/junit-4.13.2.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/net/java/dev/jna/jna/5.13.0/jna-5.13.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/apache/commons/commons-compress/1.24.0/commons-compress-1.24.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/hamcrest/hamcrest-core/1.3/hamcrest-core-1.3.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/jetbrains/annotations/17.0.0/annotations-17.0.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/mongodb/bson-record-codec/4.11.1/bson-record-codec-4.11.1.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/mongodb/bson/4.11.1/bson-4.11.1.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/mongodb/mongodb-driver-core/4.11.1/mongodb-driver-core-4.11.1.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/mongodb/mongodb-driver-sync/4.11.1/mongodb-driver-sync-4.11.1.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/portable-scala/portable-scala-reflect_2.12/1.1.2/portable-scala-reflect_2.12-1.1.2.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/reactivestreams/reactive-streams/1.0.4/reactive-streams-1.0.4.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/rnorth/duct-tape/duct-tape/1.0.8/duct-tape-1.0.8.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-collection-compat_2.12/2.11.0/scala-collection-compat_2.12-2.11.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-lang/modules/scala-java8-compat_2.12/0.8.0/scala-java8-compat_2.12-0.8.0.jar:/Users/mg/.sbt/boot/scala-2.12.18/lib/scala-library.jar:/Users/mg/.sbt/boot/scala-2.12.18/lib/scala-reflect.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scala-sbt/test-interface/1.0/test-interface-1.0.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/scalactic/scalactic_2.12/3.2.18/scalactic_2.12-3.2.18.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-api/2.0.12/slf4j-api-2.0.12.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/slf4j/slf4j-nop/1.7.36/slf4j-nop-1.7.36.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/testcontainers/mongodb/1.19.6/mongodb-1.19.6.jar:/Users/mg/Library/Caches/Coursier/v1/https/repo1.maven.org/maven2/org/testcontainers/testcontainers/1.19.6/testcontainers-1.19.6.jar" org.jetbrains.plugins.scala.testingSupport.uTest.UTestRunner -s molecule.document.mongodb.AdhocJVM_mongodb -testName tests\refs -showProgressMessages true
    Testing started at 01.26 ...




    ========================================
    INSERT:
    AttrOneManInt("A", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 1))
    Ref("A", "b", "B", CardOne, false, Seq(0, 6, 1))
    AttrOneManInt("B", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(1, 22))
    Nested(
      Ref("B", "cc", "C", CardSet, false, Seq(1, 32, 2)),
      List(
        AttrOneManInt("C", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(2, 37)),
        Ref("C", "d", "D", CardOne, false, Seq(2, 41, 3)),
        AttrOneManInt("D", "i", V, Seq(), None, None, Nil, Nil, None, None, Seq(3, 46))))

    {
      "_action": "insert",
      "_selfJoins": 0,
      "D": [
        {
          "_id": {
            "$oid": "65d937c9c97ee252691b4396"
          },
          "i": 2
        },
        {
          "_id": {
            "$oid": "65d937c9c97ee252691b4398"
          },
          "i": 4
        }
      ],
      "A": [
        {
          "i": 1,
          "b": {
            "$oid": "65d937c9c97ee252691b4395"
          }
        },
        {
          "i": 2,
          "b": {
            "$oid": "65d937c9c97ee252691b439a"
          }
        }
      ],
      "C": [
        {
          "i": 1,
          "d": {
            "$oid": "65d937c9c97ee252691b4396"
          },
          "_id": "65d937c9c97ee252691b4397"
        },
        {
          "i": 3,
          "d": {
            "$oid": "65d937c9c97ee252691b4398"
          },
          "_id": "65d937c9c97ee252691b4399"
        }
      ],
      "B": [
        {
          "_id": {
            "$oid": "65d937c9c97ee252691b4395"
          },
          "i": 10,
          "cc": [
            "65d937c9c97ee252691b4397",
            "65d937c9c97ee252691b4399"
          ]
        },
        {
          "_id": {
            "$oid": "65d937c9c97ee252691b439a"
          },
          "i": 20,
          "cc": []
        }
      ]
    }

    (1,10,List((1,2), (3,4)))
    (2,20,List())
    ----------------------------------------



      "A": [
        {
          "i": 1,
          "b": {
            "$oid": "65d937c9c97ee252691b4395"
          }
        },
        {
          "i": 2,
          "b": {
            "$oid": "65d937c9c97ee252691b439a"
          }
        }
      ],
      "B": [
        {
          "_id": {
            "$oid": "65d937c9c97ee252691b4395"
          },
          "i": 10,
          "cc": [
            "65d937c9c97ee252691b4397",
            "65d937c9c97ee252691b4399"
          ]
        },
        {
          "_id": {
            "$oid": "65d937c9c97ee252691b439a"
          },
          "i": 20,
          "cc": []
        }
      ]
      "C": [
        {
          "i": 1,
          "d": {
            "$oid": "65d937c9c97ee252691b4396"
          },
          "_id": "65d937c9c97ee252691b4397"
        },
        {
          "i": 3,
          "d": {
            "$oid": "65d937c9c97ee252691b4398"
          },
          "_id": "65d937c9c97ee252691b4399"
        }
      ],
      "D": [
        {
          "_id": {
            "$oid": "65d937c9c97ee252691b4396"
          },
          "i": 2
        },
        {
          "_id": {
            "$oid": "65d937c9c97ee252691b4398"
          },
          "i": 4
        }
      ],















    ========================================
    QUERY:
    {
      "collection": "A",
      "pipeline": [
        {
          "$match": {
            "$and": [
              {
                "$or": [
                  {
                    "_id": {
                      "$oid": "65d937c9c97ee252691b439c"
                    }
                  },
                  {
                    "_id": {
                      "$oid": "65d937c9c97ee252691b439e"
                    }
                  }
                ]
              },
              {
                "i": {
                  "$ne": null
                }
              }
            ]
          }
        },
        {
          "$lookup": {
            "from": "B",
            "localField": "b",
            "foreignField": "_id",
            "as": "b",
            "pipeline": [
              {
                "$lookup": {
                  "from": "C",
                  "localField": "cc",
                  "foreignField": "_id",
                  "as": "cc",
                  "pipeline": [
                    {
                      "$match": {
                        "i": {
                          "$ne": null
                        }
                      }
                    },
                    {
                      "$lookup": {
                        "from": "D",
                        "localField": "d",
                        "foreignField": "_id",
                        "as": "d",
                        "pipeline": [
                          {
                            "$match": {
                              "i": {
                                "$ne": null
                              }
                            }
                          }
                        ]
                      }
                    },
                    {
                      "$match": {
                        "d": {
                          "$ne": []
                        }
                      }
                    },
                    {
                      "$addFields": {
                        "d": {
                          "$first": "$d"
                        }
                      }
                    },
                    {
                      "$project": {
                        "_id": 0,
                        "i": 1,
                        "d": {
                          "i": 1
                        }
                      }
                    },
                    {
                      "$sort": {
                        "i": 1
                      }
                    }
                  ]
                }
              },
              {
                "$addFields": {
                  "b": {
                    "cc": "$cc"
                  }
                }
              },
              {
                "$match": {
                  "i": {
                    "$ne": null
                  }
                }
              }
            ]
          }
        },
        {
          "$match": {
            "b": {
              "$ne": []
            }
          }
        },
        {
          "$addFields": {
            "b": {
              "$first": "$b"
            }
          }
        },
        {
          "$project": {
            "_id": 1,
            "i": 1,
            "b": {
              "i": 1,
              "cc": 1
            }
          }
        },
        {
          "$sort": {
            "i": 1
          }
        }
      ]
    }
    ----------------------------------------



    assertion failed: ==> assertion failed: List() != List((65d937c9c97ee252691b439c,1,10,List((1,2), (3,4))), (65d937c9c97ee252691b439e,2,20,List()))
    java.lang.AssertionError: assertion failed: ==> assertion failed: List() != List((65d937c9c97ee252691b439c,1,10,List((1,2), (3,4))), (65d937c9c97ee252691b439e,2,20,List()))
      at scala.Predef$.assert(Predef.scala:223)
      at utest.asserts.Asserts$ArrowAssert.$eq$eq$greater(Asserts.scala:90)
      at molecule.document.mongodb.AdhocJVM_mongodb$.$anonfun$tests$12(AdhocJVM_mongodb.scala:65)
      at molecule.document.mongodb.AdhocJVM_mongodb$.$anonfun$tests$12$adapted(AdhocJVM_mongodb.scala:65)
      at scala.util.Success.$anonfun$map$1(Try.scala:255)
      at scala.util.Success.map(Try.scala:213)
      at scala.concurrent.Future.$anonfun$map$1(Future.scala:292)
      at scala.concurrent.impl.Promise.liftedTree1$1(Promise.scala:33)
      at scala.concurrent.impl.Promise.$anonfun$transform$1(Promise.scala:33)
      at scala.concurrent.impl.CallbackRunnable.run(Promise.scala:64)
      at molecule.core.util.Executor$.$anonfun$global$1(Executor.scala:12)
      at scala.concurrent.impl.ExecutionContextImpl.execute(ExecutionContextImpl.scala:24)
      at scala.concurrent.impl.CallbackRunnable.executeWithValue(Promise.scala:72)
      at scala.concurrent.impl.Promise$DefaultPromise.dispatchOrAddCallback(Promise.scala:316)
      at scala.concurrent.impl.Promise$DefaultPromise.onComplete(Promise.scala:307)
      at scala.concurrent.impl.Promise.transform(Promise.scala:33)
      at scala.concurrent.impl.Promise.transform$(Promise.scala:31)
      at scala.concurrent.impl.Promise$DefaultPromise.transform(Promise.scala:187)
      at scala.concurrent.Future.map(Future.scala:292)
      at scala.concurrent.Future.map$(Future.scala:292)
      at scala.concurrent.impl.Promise$DefaultPromise.map(Promise.scala:187)
      at molecule.document.mongodb.AdhocJVM_mongodb$.$anonfun$tests$11(AdhocJVM_mongodb.scala:65)
      at scala.concurrent.Future.$anonfun$flatMap$1(Future.scala:307)
      at scala.concurrent.impl.Promise.$anonfun$transformWith$1(Promise.scala:41)
      at scala.concurrent.impl.CallbackRunnable.run(Promise.scala:64)
      at molecule.core.util.Executor$.$anonfun$global$1(Executor.scala:12)
      at scala.concurrent.impl.ExecutionContextImpl.execute(ExecutionContextImpl.scala:24)
      at scala.concurrent.impl.CallbackRunnable.executeWithValue(Promise.scala:72)
      at scala.concurrent.impl.Promise$DefaultPromise.dispatchOrAddCallback(Promise.scala:316)
      at scala.concurrent.impl.Promise$DefaultPromise.onComplete(Promise.scala:307)
      at scala.concurrent.impl.Promise.transformWith(Promise.scala:40)
      at scala.concurrent.impl.Promise.transformWith$(Promise.scala:38)
      at scala.concurrent.impl.Promise$DefaultPromise.transformWith(Promise.scala:187)
      at scala.concurrent.Future.flatMap(Future.scala:306)
      at scala.concurrent.Future.flatMap$(Future.scala:306)
      at scala.concurrent.impl.Promise$DefaultPromise.flatMap(Promise.scala:187)
      at molecule.document.mongodb.AdhocJVM_mongodb$.$anonfun$tests$8(AdhocJVM_mongodb.scala:60)
      at molecule.document.mongodb.setup.TestSuite_mongodb.inMem(TestSuite_mongodb.scala:25)
      at molecule.document.mongodb.setup.TestSuite_mongodb.inMem$(TestSuite_mongodb.scala:18)
      at molecule.document.mongodb.AdhocJVM_mongodb$.inMem(AdhocJVM_mongodb.scala:17)
      at molecule.coreTests.setup.CoreTestSuiteBase.refs(CoreTestSuiteBase.scala:22)
      at molecule.coreTests.setup.CoreTestSuiteBase.refs$(CoreTestSuiteBase.scala:22)
      at molecule.document.mongodb.AdhocJVM_mongodb$.refs(AdhocJVM_mongodb.scala:17)
      at molecule.document.mongodb.AdhocJVM_mongodb$.$anonfun$tests$7(AdhocJVM_mongodb.scala:41)
      at utest.framework.StackMarker$.dropOutside(StackMarker.scala:13)
      at utest.framework.TestCallTree.run(Model.scala:45)
      at utest.framework.TestCallTree.run(Model.scala:43)
      at utest.TestRunner$.$anonfun$runAsync$5(TestRunner.scala:74)
      at utest.framework.Executor.utestWrap(Executor.scala:12)
      at utest.framework.Executor.utestWrap$(Executor.scala:10)
      at utest.TestSuite.utestWrap(TestSuite.scala:12)
      at utest.TestRunner$.$anonfun$runAsync$4(TestRunner.scala:71)
      at utest.framework.StackMarker$.dropOutside(StackMarker.scala:13)
      at utest.TestRunner$.$anonfun$runAsync$2(TestRunner.scala:71)
      at utest.TestRunner$.evaluateFutureTree(TestRunner.scala:171)
      at utest.TestRunner$.$anonfun$evaluateFutureTree$2(TestRunner.scala:174)
      at scala.concurrent.Future$.$anonfun$traverse$1(Future.scala:850)
      at scala.collection.IndexedSeqOptimized.foldLeft(IndexedSeqOptimized.scala:60)
      at scala.collection.IndexedSeqOptimized.foldLeft$(IndexedSeqOptimized.scala:68)
      at scala.collection.mutable.ArrayBuffer.foldLeft(ArrayBuffer.scala:49)
      at scala.concurrent.Future$.traverse(Future.scala:850)
      at utest.TestRunner$.evaluateFutureTree(TestRunner.scala:174)
      at utest.TestRunner$.runAsync(TestRunner.scala:99)
      at java.base/jdk.internal.reflect.DirectMethodHandleAccessor.invoke(DirectMethodHandleAccessor.java:104)
      at java.base/java.lang.reflect.Method.invoke(Method.java:578)
      at org.jetbrains.plugins.scala.testingSupport.uTest.UTestSuiteRunner.runAsync_Scala_2_13(UTestSuiteRunner.java:209)
      at org.jetbrains.plugins.scala.testingSupport.uTest.UTestSuiteRunner.runAsync(UTestSuiteRunner.java:181)
      at org.jetbrains.plugins.scala.testingSupport.uTest.UTestSuiteRunner.runAsync(UTestSuiteRunner.java:162)
      at org.jetbrains.plugins.scala.testingSupport.uTest.UTestSuiteRunner.doRunTestSuite(UTestSuiteRunner.java:100)
      at org.jetbrains.plugins.scala.testingSupport.uTest.UTestSuiteRunner.runTestSuite(UTestSuiteRunner.java:57)
      at org.jetbrains.plugins.scala.testingSupport.uTest.UTestSuiteRunner.runTestSuites(UTestSuiteRunner.java:45)
      at org.jetbrains.plugins.scala.testingSupport.uTest.UTestRunner.main(UTestRunner.java:12)



    Process finished with exit code 0

     */

    //    "unique" - unique { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Uniques._
    //      for {
    //
    //        _ <- Uniques.int.insert(1, 2, 3).transact
    //
    //        c1 <- Uniques.int.a1.query.from("").limit(2).get.map { case (List(1, 2), c, true) => c }
    //
    //        // Turning around with first cursor leads nowhere
    //        _ <- Uniques.int.a1.query.from(c1).limit(-2).get.map { case (Nil, _, false) => () }
    //
    //      } yield ()
    //    }
    //
    //
    //    "validation" - validation { implicit conn =>
    //      import molecule.coreTests.dataModels.core.dsl.Validation._
    //
    //      for {
    //
    //        id <- Type.intSeq(Seq(4)).save.transact.map(_.id)
    //        _ <- Type(id).intSeq(Seq(1, 2, 2)).update.transact
    //          .map(_ ==> "Unexpected success").recover {
    //            case ValidationErrors(errorMap) =>
    //              errorMap.head._2 ==> Seq(
    //                s"""Type.intSeq with value `1` doesn't satisfy validation:
    //                   |_ > 3
    //                   |""".stripMargin,
    //                s"""Type.intSeq with value `2` doesn't satisfy validation:
    //                   |_ > 3
    //                   |""".stripMargin
    //              )
    //          }
    //
    //      } yield ()
    //    }
  }
}
