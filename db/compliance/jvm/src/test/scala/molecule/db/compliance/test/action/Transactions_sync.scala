package molecule.db.compliance.test.action

import molecule.db.base.error.ValidationErrors
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.{Api_sync, Api_sync_transact}
import molecule.db.core.spi.{Conn, Spi_sync}
import molecule.db.compliance.domains.dsl.Types.*

// Translated from
// https://github.com/com-lihaoyi/scalasql/blob/main/scalasql/test/src/api/TransactionTests.scala

//trait Transactions_sync extends CoreTestSuite with Api_sync with Api_sync_transact { spi: Spi_sync =>
case class Transactions_sync(
  suite: Test,
  api: Api_sync_transact & Api_sync & Spi_sync & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "commit" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact
    Entity.int(count).query.get.head ==> 7

    Entity.int_.delete.transact
    Entity.int(count).query.get.head ==> 0
  }


  "Transact actions: simple" - types { implicit conn =>
    transact(
      Entity.int(1).save,
      Entity.int(2).save,
    )
    Entity.int.query.get ==> List(1, 2)
  }


  "Transact actions: mixed" - types { implicit conn =>
    transact(
      Entity.int(1).save, //         List(1)
      Entity.int.insert(2, 3), //    List(1, 2, 3)
      Entity(1).delete, //           List(2, 3)
      Entity(3).int.*(10).update, // List(2, 30)
    )
    Entity.int.a1.query.get ==> List(2, 30)
  }


  "Transact actions: validation 1" - validation { implicit conn =>
    import molecule.db.compliance.domains.dsl.Validation.*
    try {
      transact(
        Type.int(1).save, // not valid
        Type.int.insert(4, 5),
      )
    } catch {
      case ValidationErrors(errorMap) =>
        errorMap.head._2.head ==>
          s"""Type.int with value `1` doesn't satisfy validation:
             |_ > 2
             |""".stripMargin
    }

    // No data inserted/saved
    Type.int.query.get ==> List()
  }


  "Transact actions: validation 2" - validation { implicit conn =>
    import molecule.db.compliance.domains.dsl.Validation.*
    try {
      transact(
        Type.int.insert(4, 5),
        Type.int(2).save, // not valid
      )
    } catch {
      case ValidationErrors(errorMap) =>
        errorMap.head._2.head ==>
          s"""Type.int with value `2` doesn't satisfy validation:
             |_ > 2
             |""".stripMargin
    }

    // No data inserted/saved
    Type.int.query.get ==> List()
  }


  "Transact actions: validation 3" - validation { implicit conn =>
    import molecule.db.compliance.domains.dsl.Validation.*
    transact(
      Type.int.insert(4, 5),
      Type.int(3).save,
    )
    Type.int.a1.query.get ==> List(3, 4, 5)
  }


  "UnitOfWork: simple" - types { implicit conn =>
    unitOfWork {
      Entity.int(1).save.transact
      Entity.int(2).save.transact
    }
    Entity.int.a1.query.get ==> List(1, 2)
  }


  "UnitOfWork: mixed" - types { implicit conn =>
    unitOfWork {
      Entity.int(1).save.transact //         List(1)
      Entity.int.insert(2, 3).transact //    List(1, 2, 3)
      Entity(1).delete.transact //           List(2, 3)
      Entity(3).int.*(10).update.transact // List(2, 30)
    }
    Entity.int.a1.query.get ==> List(2, 30)
  }


  "UnitOfWork: mixed with queries" - types { implicit conn =>
    unitOfWork {
      Entity.int(1).save.transact
      Entity.int.query.get ==> List(1)

      Entity.int.insert(2, 3).transact
      Entity.int.a1.query.get ==> List(1, 2, 3)

      Entity(1).delete.transact
      Entity.int.a1.query.get ==> List(2, 3)

      Entity(3).int.*(10).update.transact
      Entity.int.a1.query.get ==> List(2, 30)
    }
    Entity.int.a1.query.get ==> List(2, 30)
  }


  "UnitOfWork: abort save" - types { implicit conn =>
    Entity.int(1).save.transact
    try {
      unitOfWork {
        Entity.int(2).save.transact
        Entity.int.a1.query.get ==> List(1, 2)
        throw new Exception()
      }
    } catch {
      case _: Exception => ()
    }
    Entity.int.query.get ==> List(1)
  }


  "UnitOfWork: abort insert" - types { implicit conn =>
    Entity.int(1).save.transact
    try {
      unitOfWork {
        Entity.int.insert(2, 3).transact
        Entity.int.a1.query.get ==> List(1, 2, 3)
        throw new Exception()
      }
    } catch {
      case _: Exception => ()
    }
    Entity.int.query.get ==> List(1)
  }


  "UnitOfWork: abort update" - types { implicit conn =>
    Entity.int(1).save.transact
    try {
      unitOfWork {
        Entity(1).int.*(10).update.transact
        Entity.int.query.get ==> List(10)
        throw new Exception()
      }
    } catch {
      case _: Exception => ()
    }
    Entity.int.query.get ==> List(1)
  }


  "UnitOfWork: abort delete" - types { implicit conn =>
    Entity.int.insert(1, 2).transact
    try {
      unitOfWork {
        Entity(1).delete.transact
        Entity.int.query.get ==> List(2)
        throw new Exception()
      }
    } catch {
      case _: Exception => ()
    }
    Entity.int.a1.query.get ==> List(1, 2)
  }


  "UnitOfWork: abort mixed" - types { implicit conn =>
    // Initial data
    Entity.int(1).save.transact

    try {
      unitOfWork {
        Entity.int(2).save.transact
        Entity.int.a1.query.get ==> List(1, 2)

        Entity.int.insert(3, 4).transact
        Entity.int.a1.query.get ==> List(1, 2, 3, 4)

        Entity(2).delete.transact
        Entity.int.a1.query.get ==> List(1, 3, 4)

        Entity(4).int.*(10).update.transact
        Entity.int.a1.query.get ==> List(1, 3, 40)

        throw new Exception()
      }
    } catch {
      case _: Exception => ()
    }

    // Initial data remains intact
    Entity.int.query.get ==> List(1)
  }


  "UnitOfWork: money transfer" - types { implicit conn =>
    // Initial balance in two bank accounts
    Entity.s("fromAccount").int(100).save.transact
    Entity.s("toAccount").int(50).save.transact

    try {
      unitOfWork {
        Entity.s_("fromAccount").int.-(200).update.transact
        Entity.s_("toAccount").int.+(200).update.transact

        // Check that fromAccount had sufficient funds
        if (Entity.s_("fromAccount").int.query.get.head < 0) {
          // Abort all transactions in this unit of work
          throw new Exception(
            "Insufficient funds in fromAccount..."
          )
        }
      }
    } catch {
      case e: Exception =>
        // Do something with failure...
        e.getMessage ==> "Insufficient funds in fromAccount..."
    }

    // No data transacted
    Entity.s.int.query.get ==> List(
      ("fromAccount", 100),
      ("toAccount", 50),
    )
  }


  // Transfer logic separated with unitOfWork
  def transfer(from: String, to: String, amount: Int)
              (implicit conn: Conn): Unit = {
    unitOfWork {
      Entity.s_(from).int.-(amount).update.transact
      Entity.s_(to).int.+(amount).update.transact

      val res         = Entity.s_(from).int.query.get
      val fromBalance = res.head
      // Check that fromAccount had sufficient funds
      if (fromBalance < 0) {
        // Abort all transactions in this unit of work
        throw new Exception(
          "Insufficient funds in fromAccount..."
        )
      }
    }
  }


  "UnitOfWork: money transfer2" - types { implicit conn =>
    // Initial balance in two bank accounts
    Entity.s("fromAccount").int(100).save.transact
    Entity.s("toAccount").int(50).save.transact

    try {
      transfer("fromAccount", "toAccount", 80)
    } catch {
      case e: Exception =>
        // Do something with failure...
        e.getMessage ==> "Insufficient funds in fromAccount..."
    }

    // Amount transferred
    Entity.s.int.a1.query.get ==> List(
      ("fromAccount", 20),
      ("toAccount", 130),
    )
  }


  "Savepoint: commit" - types { implicit conn =>
    unitOfWork {
      Entity.int.insert(1 to 4).transact
      Entity.int(count).query.get.head ==> 4
      savepoint { _ =>
        // Delete all
        Entity.int_.delete.transact
        Entity.int(count).query.get.head ==> 0
      }

      // All deleted
      Entity.int(count).query.get.head ==> 0
    }
  }

  // Without rollbacks, the above is the same as the following:
  "Savepoint: commit2" - types { implicit conn =>
    Entity.int.insert(1 to 4).transact
    Entity.int(count).query.get.head ==> 4

    // Delete all
    Entity.int_.delete.transact
    Entity.int(count).query.get.head ==> 0

    // All deleted
    Entity.int(count).query.get.head ==> 0
  }


  "Savepoint: rollback" - types { implicit conn =>
    unitOfWork {
      Entity.int.insert(1 to 4).transact
      Entity.int(count).query.get.head ==> 4

      savepoint { sp =>
        // Delete all
        Entity.int_.delete.transact
        Entity.int(count).query.get.head ==> 0

        // Roll back delete
        sp.rollback()
        Entity.int(count).query.get.head ==> 4
      }

      // Nothing deleted
      Entity.int(count).query.get.head ==> 4
    }
  }


  "Savepoint: rollback2" - types { implicit conn =>
    Entity.int.insert(1 to 4).transact
    Entity.int(count).query.get.head ==> 4

    unitOfWork {
      savepoint { sp =>
        // Delete all
        Entity.int_.delete.transact
        Entity.int(count).query.get.head ==> 0

        // Roll back delete
        sp.rollback()
        Entity.int(count).query.get.head ==> 4
      }
    }

    // Nothing deleted
    Entity.int(count).query.get.head ==> 4
  }


  "Savepoint: throw" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact
    Entity.int(count).query.get.head ==> 7

    unitOfWork {
      Entity.int_.<=(3).delete.transact
      Entity.int(count).query.get.head ==> 4

      try {
        savepoint { _ =>
          // Delete all
          Entity.int_.delete.transact
          Entity.int(count).query.get.head ==> 0

          // Roll back delete within savepoint body by throwing exception
          throw new Exception("handle error...")
        }
      } catch {
        case e: Exception => e.getMessage ==> "handle error..."
      }

      // 1-3 deleted withing uow
      Entity.int(count).query.get.head ==> 4
    }

    Entity.int(count).query.get.head ==> 4
  }


  "Savepoint: throw2" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact
    Entity.int(count).query.get.head ==> 7

    Entity.int_.<=(3).delete.transact
    Entity.int(count).query.get.head ==> 4

    try {
      unitOfWork {
        savepoint { _ =>
          // Delete all
          Entity.int_.delete.transact
          Entity.int(count).query.get.head ==> 0
          // Roll back delete by throwing exception
          throw new Exception("handle error...")
        }
      }
    } catch {
      case e: Exception => e.getMessage ==> "handle error..."
    }

    // 1-3 deleted in outer context
    Entity.int(count).query.get.head ==> 4
  }


  "Savepoint: throwDouble" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    try {
      unitOfWork {
        savepoint { outer =>
          Entity.int(count).query.get.head ==> 7

          Entity.int_.<=(3).delete.transact
          Entity.int(count).query.get.head ==> 4

          try {
            savepoint { inner =>
              // Delete all
              Entity.int_.delete.transact
              Entity.int(count).query.get.head ==> 0

              // Roll back delete by throwing exception
              throw new Exception("inner error...")
            }
          } catch {
            case inner: Exception =>
              Entity.int(count).query.get.head ==> 4
              // re-throw inner error to outer handler
              throw inner
          }
        }

        Entity.int(count).query.get.head ==> 4
      }
    } catch {
      case outer: Exception =>
        outer.getMessage ==> "inner error..."
    }

    // Nothing deleted
    Entity.int(count).query.get.head ==> 7
  }


  "Savepoint: rollbackDouble" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    unitOfWork {
      savepoint { outer =>
        Entity.int(count).query.get.head ==> 7

        Entity.int_.<=(3).delete.transact
        Entity.int(count).query.get.head ==> 4

        savepoint { inner =>
          // Delete all
          Entity.int_.delete.transact
          Entity.int(count).query.get.head ==> 0

          // Roll back outer savepoint delete
          outer.rollback()
          Entity.int(count).query.get.head ==> 7
        }

        // Nothing deleted
        Entity.int(count).query.get.head ==> 7
      }
    }

    // Nothing deleted
    Entity.int(count).query.get.head ==> 7
  }


  /*
    Only one transaction can be present at a time, but savepoints can be arbitrarily nested.
    Uncaught exceptions or explicit `.rollback()` calls would roll back changes done during
    the inner savepoint/transaction blocks, while leaving changes applied during outer
    savepoint/transaction blocks in-place
    */
  "Double savepoint: commit" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    unitOfWork {
      savepoint { _ =>
        Entity.int(count).query.get.head ==> 7

        Entity.int_.<=(2).delete.transact
        Entity.int(count).query.get.head ==> 5

        savepoint { _ =>
          Entity.int_.<=(4).delete.transact
          Entity.int(count).query.get.head ==> 3

          savepoint { _ =>
            Entity.int_.<=(6).delete.transact
            Entity.int(count).query.get.head ==> 1
          }
          Entity.int(count).query.get.head ==> 1
        }
        Entity.int(count).query.get.head ==> 1
      }
      Entity.int(count).query.get.head ==> 1
    }

    // 1-6 deleted
    Entity.int(count).query.get.head ==> 1
  }


  "throw: inner" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    unitOfWork {
      savepoint { _ =>
        Entity.int(count).query.get.head ==> 7

        Entity.int_.<=(2).delete.transact
        Entity.int(count).query.get.head ==> 5

        savepoint { _ =>
          Entity.int_.<=(4).delete.transact
          Entity.int(count).query.get.head ==> 3

          try {
            savepoint { _ =>
              Entity.int_.<=(6).delete.transact
              Entity.int(count).query.get.head ==> 1
              throw new Exception
            }
          } catch {
            case e: Exception => /*donothing*/
          }
        }
        Entity.int(count).query.get.head ==> 3
      }
      Entity.int(count).query.get.head ==> 3
    }

    // 1-4 deleted
    Entity.int(count).query.get.head ==> 3
  }


  "throw: middle" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    unitOfWork {
      savepoint { _ =>
        Entity.int(count).query.get.head ==> 7

        Entity.int_.<=(2).delete.transact
        Entity.int(count).query.get.head ==> 5

        try {
          savepoint { _ =>
            Entity.int_.<=(4).delete.transact
            Entity.int(count).query.get.head ==> 3

            savepoint { _ =>
              Entity.int_.<=(6).delete.transact
              Entity.int(count).query.get.head ==> 1
            }

            Entity.int(count).query.get.head ==> 1
            throw new Exception
          }
        } catch {
          case e: Exception => /*donothing*/
        }
      }
      Entity.int(count).query.get.head ==> 5
    }

    // 1-2 deleted
    Entity.int(count).query.get.head ==> 5
  }


  "throw: innerMiddle" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    unitOfWork {
      savepoint { _ =>
        Entity.int(count).query.get.head ==> 7

        Entity.int_.<=(2).delete.transact
        Entity.int(count).query.get.head ==> 5

        try {
          savepoint { _ =>
            Entity.int_.<=(4).delete.transact
            Entity.int(count).query.get.head ==> 3

            savepoint { _ =>
              Entity.int_.<=(6).delete.transact
              Entity.int(count).query.get.head ==> 1
              throw new Exception
            }
          }
        } catch {
          case e: Exception => /*donothing*/
        }
      }
      Entity.int(count).query.get.head ==> 5
    }

    // 1-2 deleted
    Entity.int(count).query.get.head ==> 5
  }


  "throw: innerMiddleOuter" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    try {
      unitOfWork {
        savepoint { _ =>
          Entity.int(count).query.get.head ==> 7

          Entity.int_.<=(2).delete.transact
          Entity.int(count).query.get.head ==> 5

          savepoint { _ =>
            Entity.int_.<=(4).delete.transact
            Entity.int(count).query.get.head ==> 3

            savepoint { _ =>
              Entity.int_.<=(6).delete.transact
              Entity.int(count).query.get.head ==> 1
              throw new Exception
            }
          }

          Entity.int(count).query.get.head ==> 5
        }
      }
    } catch {
      case e: Exception => /*donothing*/
    }

    // Nothing deleted
    Entity.int(count).query.get.head ==> 7
  }


  "rollback: inner" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    unitOfWork {
      savepoint { outer =>
        Entity.int(count).query.get.head ==> 7

        Entity.int_.<=(2).delete.transact
        Entity.int(count).query.get.head ==> 5

        savepoint { middle =>
          Entity.int_.<=(4).delete.transact
          Entity.int(count).query.get.head ==> 3

          savepoint { inner =>
            Entity.int_.<=(6).delete.transact
            Entity.int(count).query.get.head ==> 1
            inner.rollback()
          }
        }
        Entity.int(count).query.get.head ==> 3
      }
      Entity.int(count).query.get.head ==> 3
    }

    // 1-4 deleted
    Entity.int(count).query.get.head ==> 3
  }


  "rollback: middle" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    unitOfWork {
      savepoint { outer =>
        Entity.int(count).query.get.head ==> 7

        Entity.int_.<=(2).delete.transact
        Entity.int(count).query.get.head ==> 5

        savepoint { middle =>
          Entity.int_.<=(4).delete.transact
          Entity.int(count).query.get.head ==> 3

          savepoint { inner =>
            Entity.int_.<=(6).delete.transact
            Entity.int(count).query.get.head ==> 1
          }

          Entity.int(count).query.get.head ==> 1
          middle.rollback()
          Entity.int(count).query.get.head ==> 5
        }
      }
      Entity.int(count).query.get.head ==> 5
    }

    // 1-2 deleted
    Entity.int(count).query.get.head ==> 5
  }


  "rollback: innerMiddle" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    unitOfWork {
      savepoint { outer =>
        Entity.int(count).query.get.head ==> 7

        Entity.int_.<=(2).delete.transact
        Entity.int(count).query.get.head ==> 5

        savepoint { middle =>
          Entity.int_.<=(4).delete.transact
          Entity.int(count).query.get.head ==> 3

          savepoint { inner =>
            Entity.int_.<=(6).delete.transact
            Entity.int(count).query.get.head ==> 1
            middle.rollback()
            Entity.int(count).query.get.head ==> 5
          }

          Entity.int(count).query.get.head ==> 5
        }
      }
      Entity.int(count).query.get.head ==> 5
    }

    // 1-2 deleted
    Entity.int(count).query.get.head ==> 5
  }


  "rollback: middleOuter" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    unitOfWork {
      savepoint { outer =>
        Entity.int(count).query.get.head ==> 7

        Entity.int_.<=(2).delete.transact
        Entity.int(count).query.get.head ==> 5

        savepoint { middle =>
          Entity.int_.<=(4).delete.transact
          Entity.int(count).query.get.head ==> 3

          savepoint { inner =>
            Entity.int_.<=(6).delete.transact
            Entity.int(count).query.get.head ==> 1
          }

          Entity.int(count).query.get.head ==> 1
          outer.rollback()
          Entity.int(count).query.get.head ==> 7
        }
      }
      Entity.int(count).query.get.head ==> 7
    }

    // Nothing deleted
    Entity.int(count).query.get.head ==> 7
  }


  "rollback: innerMiddleOuter" - types { implicit conn =>
    Entity.int.insert(1 to 7).transact

    unitOfWork {
      savepoint { outer =>
        Entity.int(count).query.get.head ==> 7

        Entity.int_.<=(2).delete.transact
        Entity.int(count).query.get.head ==> 5

        savepoint { middle =>
          Entity.int_.<=(4).delete.transact
          Entity.int(count).query.get.head ==> 3

          savepoint { inner =>
            Entity.int_.<=(6).delete.transact
            Entity.int(count).query.get.head ==> 1
            outer.rollback()
            Entity.int(count).query.get.head ==> 7
          }

          Entity.int(count).query.get.head ==> 7
        }
      }
      Entity.int(count).query.get.head ==> 7
    }

    // Nothing deleted
    Entity.int(count).query.get.head ==> 7
  }
}