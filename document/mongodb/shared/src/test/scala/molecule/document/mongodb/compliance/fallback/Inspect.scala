package molecule.document.mongodb.compliance.fallback

import molecule.core.util.Executor._
import molecule.coreTests.dataModels.core.dsl.Types._
import molecule.document.mongodb.async._
import molecule.document.mongodb.setup.TestSuite_mongodb
import utest._
import scala.language.implicitConversions

object Inspect extends TestSuite_mongodb {

  override lazy val tests = Tests {

    "Query" - {

      "Inspect without fetching" - types { implicit conn =>
        for {
          _ <- Ns.string("a").int(1).save.transact
          _ <- Ns.string.int.query.inspect.map(_ ==> ()) // returns Unit
          /*
          ========================================
          QUERY:
          {
            "collection": "Ns",
            "pipeline": [
              {
                "$match": {
                  "$and": [
                    {
                      "string": {
                        "$ne": null
                      }
                    },
                    {
                      "int": {
                        "$ne": null
                      }
                    }
                  ]
                }
              },
              {
                "$project": {
                  "_id": 0,
                  "string": 1,
                  "int": 1
                }
              }
            ]
          }
          ----------------------------------------
          */
        } yield ()
      }

      "Inspect and query" - types { implicit conn =>
        for {
          _ <- Ns.string("a").int(1).save.transact
          _ <- Ns.string.int.query.i.get.map(_ ==> List(("a", 1)))
          /*
          ========================================
          QUERY:
          {
            "collection": "Ns",
            "pipeline": [
              {
                "$match": {
                  "$and": [
                    {
                      "string": {
                        "$ne": null
                      }
                    },
                    {
                      "int": {
                        "$ne": null
                      }
                    }
                  ]
                }
              },
              {
                "$project": {
                  "_id": 0,
                  "string": 1,
                  "int": 1
                }
              }
            ]
          }
          ----------------------------------------
          */
        } yield ()
      }

      "Inspect more complex query" - types { implicit conn =>
        for {
          _ <- Ns.i.int(avg).a1.Refs.*(Ref.string("foo")).query.inspect
          /*
          ========================================
          QUERY:
          {
            "collection": "Ns",
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
                  "from": "Ref",
                  "localField": "refs",
                  "foreignField": "_id",
                  "as": "refs",
                  "pipeline": [
                    {
                      "$match": {
                        "string": "foo"
                      }
                    },
                    {
                      "$project": {
                        "_id": 0,
                        "string": 1
                      }
                    }
                  ]
                }
              },
              {
                "$match": {
                  "refs": {
                    "$ne": []
                  }
                }
              },
              {
                "$group": {
                  "_id": {
                    "i": "$i",
                    "refs_string": "$refs.string",
                    "int": "$int"
                  }
                }
              },
              {
                "$group": {
                  "_id": {
                    "i": "$_id.i",
                    "refs_string": "$_id.refs_string"
                  },
                  "int": {
                    "$avg": "$_id.int"
                  }
                }
              },
              {
                "$addFields": {
                  "i": "$_id.i",
                  "refs.string": "$_id.refs_string"
                }
              },
              {
                "$project": {
                  "_id": 0,
                  "i": 1,
                  "int": 1,
                  "refs": 1
                }
              },
              {
                "$sort": {
                  "int": 1
                }
              }
            ]
          }
          ----------------------------------------
          */
        } yield ()
      }
    }


    "Save" - {

      "Inspect without saving" - types { implicit conn =>
        for {
          _ <- Ns.string("a").int(1).save.inspect
          /*
          ========================================
          SAVE:
          AttrOneManString("Ns", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 6))

          {
            "_action": "insert",
            "_selfJoins": 0,
            "_refIdss": [],
            "Ns": [
              {
                "string": "a",
                "int": 1
              }
            ]
          }
          ----------------------------------------
          */
          // (values are visible in the model elements)

          // Save action was inspected without saving
          _ <- Ns.string.int.query.get.map(_ ==> Nil)
        } yield ()
      }

      "Inspect and save" - types { implicit conn =>
        for {
          _ <- Ns.string("a").int(1).save.i.transact
          /*
          ========================================
          SAVE:
          AttrOneManString("Ns", "string", Eq, Seq("a"), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", Eq, Seq(1), None, None, Nil, Nil, None, None, Seq(0, 6))

          {
            "_action": "insert",
            "_selfJoins": 0,
            "_refIdss": [],
            "Ns": [
              {
                "string": "a",
                "int": 1
              }
            ]
          }
          ----------------------------------------
          */
          // (values are visible in the model elements)

          // Save action was inspected and data saved
          _ <- Ns.string.int.query.get.map(_ ==> List(("a", 1)))
        } yield ()
      }
    }


    "Insert" - {

      "Inspect without inserting" - types { implicit conn =>
        for {
          _ <- Ns.string.int.insert(("a", 1), ("b", 2)).inspect
          /*
          ========================================
          INSERT:
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 6))

          {
            "_action": "insert",
            "_selfJoins": 0,
            "_refIdss": [],
            "Ns": [
              {
                "string": "a",
                "int": 1
              },
              {
                "string": "b",
                "int": 2
              }
            ]
          }

          (a,1)
          (b,2)
          ----------------------------------------
          */

          // Insert action was inspected without inserting
          _ <- Ns.string.int.query.get.map(_ ==> Nil)
        } yield ()
      }

      "Inspect and insert" - types { implicit conn =>
        for {
          _ <- Ns.string.int.insert(("a", 1), ("b", 2)).i.transact
          /*
          ========================================
          INSERT:
          AttrOneManString("Ns", "string", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 5))
          AttrOneManInt("Ns", "int", V, Seq(), None, None, Nil, Nil, None, None, Seq(0, 6))

          {
            "_action": "insert",
            "_selfJoins": 0,
            "_refIdss": [],
            "Ns": [
              {
                "string": "a",
                "int": 1
              },
              {
                "string": "b",
                "int": 2
              }
            ]
          }

          (a,1)
          (b,2)
          ----------------------------------------
          */

          // Insert action was inspected and data inserted
          _ <- Ns.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
        } yield ()
      }
    }


    "Update" - {

      "Inspect without updating" - types { implicit conn =>
        for {
          id <- Ns.string("a").int(1).save.transact.map(_.id)
          _ <- Ns(id).string("ZZZ").update.inspect
          /*
          ========================================
          UPDATE:
          AttrOneTacID("Ns", "id", Eq, Seq("65ad12b576016a5938a8f394"), None, None, Nil, Nil, None, None, Seq(0, 0), false)
          AttrOneManString("Ns", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 5))

          {
            "_action": "update",
            "Ns": {
              "filter": {
                "_id": {
                  "$in": [
                    {
                      "$oid": "65ad12b576016a5938a8f394"
                    }
                  ]
                }
              },
              "update": {
                "$set": {
                  "string": "ZZZ"
                }
              }
            }
          }
          ----------------------------------------
          */
          // (values are visible in the model elements)

          // Update was inspected without updating
          _ <- Ns.string.int.query.get.map(_ ==> List(("a", 1)))
        } yield ()
      }

      "Inspect and update" - types { implicit conn =>
        for {
          id <- Ns.string("a").int(1).save.transact.map(_.id)
          _ <- Ns(id).string("ZZZ").update.i.transact
          /*
          ========================================
          UPDATE:
          AttrOneTacID("Ns", "id", Eq, Seq("65ad131b9d0dec030270b707"), None, None, Nil, Nil, None, None, Seq(0, 0), false)
          AttrOneManString("Ns", "string", Eq, Seq("ZZZ"), None, None, Nil, Nil, None, None, Seq(0, 5))

          {
            "_action": "update",
            "Ns": {
              "filter": {
                "_id": {
                  "$in": [
                    {
                      "$oid": "65ad131b9d0dec030270b707"
                    }
                  ]
                }
              },
              "update": {
                "$set": {
                  "string": "ZZZ"
                }
              }
            }
          }
          ----------------------------------------
          */
          // (values are visible in the model elements)

          // Update was inspected and date updated
          _ <- Ns.string.int.query.get.map(_ ==> List(("ZZZ", 1)))
        } yield ()
      }

      "Inspect more complex update" - types { implicit conn =>
        for {
          id <- Ns.ints(Set(3, 4)).save.transact.map(_.id)
          _ <- Ns(id).ints.swap(3 -> 6, 4 -> 7).update.inspect
          /*
          ========================================
          UPDATE:
          AttrOneTacID("Ns", "id", Eq, Seq("65ad132fdfde2741c7764bd1"), None, None, Nil, Nil, None, None, Seq(0, 0), false)
          AttrSetManInt("Ns", "ints", Swap, Seq(Set(3), Set(4), Set(6), Set(7)), None, None, Nil, Nil, None, None, Seq(0, 30))

          {
            "_action": "update",
            "Ns": {
              "filter": {
                "_id": {
                  "$in": [
                    {
                      "$oid": "65ad132fdfde2741c7764bd1"
                    }
                  ]
                }
              },
              "update": {
                "$set": {
                  "ints.$[ints0]": 6,
                  "ints.$[ints1]": 7
                }
              },
              "arrayFilters": [
                {
                  "ints0": 3
                },
                {
                  "ints1": 4
                }
              ]
            }
          }
          ----------------------------------------
          */
          // (values are visible in the model elements)
        } yield ()
      }
    }


    "Delete" - {

      "Inspect without deleting" - types { implicit conn =>
        for {
          List(a, b) <- Ns.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)
          _ <- Ns(a).delete.inspect

          // Deletions make sure not to orphan possible joins involving the deleted ids
          /*
          ========================================
          DELETE:
          AttrOneTacID("Ns", "id", Eq, Seq("65ad12de59065d3f05ca1356"), None, None, Nil, Nil, None, None, Seq(0, 0), false)

          {
            "_action": "delete",
            "_ids": [
              {
                "$oid": "65ad12de59065d3f05ca1356"
              }
            ],
            "Ns": {
              "_id": {
                "$in": [
                  {
                    "$oid": "65ad12de59065d3f05ca1356"
                  }
                ]
              }
            }
          }
          ----------------------------------------
          */

          // Deletion was inspected without deleting
          _ <- Ns.string.int.a1.query.get.map(_ ==> List(("a", 1), ("b", 2)))
        } yield ()
      }

      "Inspect and delete" - types { implicit conn =>
        for {
          List(a, b) <- Ns.string.int.insert(("a", 1), ("b", 2)).transact.map(_.ids)
          _ <- Ns(a).delete.i.transact
          /*
          ========================================
          DELETE:
          AttrOneTacID("Ns", "id", Eq, Seq("65ad12f28edd6f78bd4ac47e"), None, None, Nil, Nil, None, None, Seq(0, 0), false)

          {
            "_action": "delete",
            "_ids": [
              {
                "$oid": "65ad12f28edd6f78bd4ac47e"
              }
            ],
            "Ns": {
              "_id": {
                "$in": [
                  {
                    "$oid": "65ad12f28edd6f78bd4ac47e"
                  }
                ]
              }
            }
          }
          ----------------------------------------
          */

          // Deletion was inspected and date deleted
          _ <- Ns.string.int.query.get.map(_ ==> List(("b", 2)))
        } yield ()
      }
    }
  }
}