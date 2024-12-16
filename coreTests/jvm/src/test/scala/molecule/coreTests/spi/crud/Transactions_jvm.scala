package molecule.coreTests.spi.crud

import molecule.base.error._
import molecule.core.api.Api_sync
import molecule.core.spi.{Conn, Spi_sync}
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._


// Translated from
// https://github.com/com-lihaoyi/scalasql/blob/main/scalasql/test/src/api/TransactionTests.scala

trait Transactions_jvm extends CoreTestSuite with Api_sync { spi: Spi_sync =>

  override lazy val tests = Tests {

    "commit" - types { implicit conn =>
      Ns.int.insert(1 to 7).transact
      Ns.int(count).query.get.head ==> 7

      Ns.int_.delete.transact
      Ns.int(count).query.get.head ==> 0
    }


    "transact actions" - {

      "simple" - types { implicit conn =>
        transact(
          Ns.int(1).save,
          Ns.int(2).save,
        )
        Ns.int.query.get ==> List(1, 2)
      }


      "mixed" - types { implicit conn =>
        transact(
          Ns.int(1).save, //         List(1)
          Ns.int.insert(2, 3), //    List(1, 2, 3)
          Ns(1).delete, //           List(2, 3)
          Ns(3).int.*(10).update, // List(2, 30)
        )
        Ns.int.query.get ==> List(2, 30)
      }


      "validation 1" - validation { implicit conn =>
        import molecule.coreTests.dataModels.dsl.Validation._
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


      "validation 2" - validation { implicit conn =>
        import molecule.coreTests.dataModels.dsl.Validation._
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


      "validation 3" - validation { implicit conn =>
        import molecule.coreTests.dataModels.dsl.Validation._
        transact(
          Type.int.insert(4, 5),
          Type.int(3).save,
        )
        Type.int.a1.query.get ==> List(3, 4, 5)
      }
    }


    "unitOfWork" - {

      "simple" - types { implicit conn =>
        unitOfWork {
          Ns.int(1).save.transact
          Ns.int(2).save.transact
        }
        Ns.int.query.get ==> List(1, 2)
      }


      "mixed" - types { implicit conn =>
        unitOfWork {
          Ns.int(1).save.transact //         List(1)
          Ns.int.insert(2, 3).transact //    List(1, 2, 3)
          Ns(1).delete.transact //           List(2, 3)
          Ns(3).int.*(10).update.transact // List(2, 30)
        }
        Ns.int.query.get ==> List(2, 30)
      }


      "mixed, unified actions" - types { implicit conn =>
        unitOfWork {
          Ns.int(1).save.transact
          Ns.int.insert(2, 3).transact
          Ns(1).delete.transact
          Ns(3).int.*(10).update.transact
        }
        Ns.int.query.get ==> List(2, 30)
      }


      "mixed, with queries" - types { implicit conn =>
        unitOfWork {
          Ns.int(1).save.transact
          Ns.int.query.get ==> List(1)

          Ns.int.insert(2, 3).transact
          Ns.int.query.get ==> List(1, 2, 3)

          Ns(1).delete.transact
          Ns.int.query.get ==> List(2, 3)

          Ns(3).int.*(10).update.transact
          Ns.int.query.get ==> List(2, 30)
        }
        Ns.int.query.get ==> List(2, 30)
      }


      "money transfer" - types { implicit conn =>
        // Initial balance in two bank accounts
        Ns.s("fromAccount").int(100).save.transact
        Ns.s("toAccount").int(50).save.transact

        try {
          unitOfWork {
            Ns.s_("fromAccount").int.-(200).update.transact
            Ns.s_("toAccount").int.+(200).update.transact

            val res         = Ns.s_("fromAccount").int.query.get
            //                    .map { res =>
            val fromBalance = res.head
            // Check that fromAccount had sufficient funds
            if (fromBalance < 0) {
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
        Ns.s.int.query.get ==> List(
          ("fromAccount", 100),
          ("toAccount", 50),
        )
      }


      // Transfer logic separated with unitOfWork
      def transfer(from: String, to: String, amount: Int)
                  (implicit conn: Conn): Unit = {
        unitOfWork {
          Ns.s_(from).int.-(amount).update.transact
          Ns.s_(to).int.+(amount).update.transact

          val res         = Ns.s_(from).int.query.get
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


      "money transfer2" - types { implicit conn =>
        // Initial balance in two bank accounts
        Ns.s("fromAccount").int(100).save.transact
        Ns.s("toAccount").int(50).save.transact

        try {
          transfer("fromAccount", "toAccount", 80)
        } catch {
          case e: Exception =>
            // Do something with failure...
            e.getMessage ==> "Insufficient funds in fromAccount..."
        }

        // Amount transferred
        Ns.s.int.a1.query.get ==> List(
          ("fromAccount", 20),
          ("toAccount", 130),
        )
      }
    }


    "savepoint" - {

      "commit" - types { implicit conn =>
        unitOfWork {
          Ns.int.insert(1 to 4).transact
          Ns.int(count).query.get.head ==> 4
          savepoint { _ =>
            // Delete all
            Ns.int_.delete.transact
            Ns.int(count).query.get.head ==> 0
          }

          // All deleted
          Ns.int(count).query.get.head ==> 0
        }
      }

      // Without rollbacks, the above is the same as the following:
      "commit2" - types { implicit conn =>
        Ns.int.insert(1 to 4).transact
        Ns.int(count).query.get.head ==> 4

        // Delete all
        Ns.int_.delete.transact
        Ns.int(count).query.get.head ==> 0

        // All deleted
        Ns.int(count).query.get.head ==> 0
      }


      "rollback" - types { implicit conn =>
        unitOfWork {
          Ns.int.insert(1 to 4).transact
          Ns.int(count).query.get.head ==> 4

          savepoint { sp =>
            // Delete all
            Ns.int_.delete.transact
            Ns.int(count).query.get.head ==> 0

            // Roll back delete
            sp.rollback()
            Ns.int(count).query.get.head ==> 4
          }

          // Nothing deleted
          Ns.int(count).query.get.head ==> 4
        }
      }


      "rollback2" - types { implicit conn =>
        Ns.int.insert(1 to 4).transact
        Ns.int(count).query.get.head ==> 4

        unitOfWork {
          savepoint { sp =>
            // Delete all
            Ns.int_.delete.transact
            Ns.int(count).query.get.head ==> 0

            // Roll back delete
            sp.rollback()
            Ns.int(count).query.get.head ==> 4
          }
        }

        // Nothing deleted
        Ns.int(count).query.get.head ==> 4
      }


      "throw" - types { implicit conn =>
        Ns.int.insert(1 to 7).transact
        Ns.int(count).query.get.head ==> 7

        unitOfWork {
          Ns.int_.<=(3).delete.transact
          Ns.int(count).query.get.head ==> 4

          try {
            savepoint { _ =>
              // Delete all
              Ns.int_.delete.transact
              Ns.int(count).query.get.head ==> 0

              // Roll back delete within savepoint body by throwing exception
              throw new Exception("handle error...")
            }
          } catch {
            case e: Exception => e.getMessage ==> "handle error..."
          }

          // 1-3 deleted withing uow
          Ns.int(count).query.get.head ==> 4
        }
      }


      "throw2" - types { implicit conn =>
        Ns.int.insert(1 to 7).transact
        Ns.int(count).query.get.head ==> 7

        Ns.int_.<=(3).delete.transact
        Ns.int(count).query.get.head ==> 4

        try {
          unitOfWork {
            savepoint { _ =>
              // Delete all
              Ns.int_.delete.transact
              Ns.int(count).query.get.head ==> 0
              // Roll back delete by throwing exception
              throw new Exception("handle error...")
            }
          }
        } catch {
          case e: Exception => e.getMessage ==> "handle error..."
        }

        // 1-3 deleted in outer context
        Ns.int(count).query.get.head ==> 4
      }


      "throwDouble" - types { implicit conn =>
        Ns.int.insert(1 to 7).transact

        try {
          unitOfWork {
            savepoint { outer =>
              Ns.int(count).query.get.head ==> 7

              Ns.int_.<=(3).delete.transact
              Ns.int(count).query.get.head ==> 4

              try {
                savepoint { inner =>
                  // Delete all
                  Ns.int_.delete.transact
                  Ns.int(count).query.get.head ==> 0

                  // Roll back delete by throwing exception
                  throw new Exception("inner error...")
                }
              } catch {
                case inner: Exception =>
                  Ns.int(count).query.get.head ==> 4
                  // re-throw inner error to outer handler
                  throw inner
              }
            }

            Ns.int(count).query.get.head ==> 4
          }
        } catch {
          case outer: Exception =>
            outer.getMessage ==> "inner error..."
        }

        // Nothing deleted
        Ns.int(count).query.get.head ==> 7
      }


      "rollbackDouble" - types { implicit conn =>
        Ns.int.insert(1 to 7).transact

        unitOfWork {
          savepoint { outer =>
            Ns.int(count).query.get.head ==> 7

            Ns.int_.<=(3).delete.transact
            Ns.int(count).query.get.head ==> 4

            savepoint { inner =>
              // Delete all
              Ns.int_.delete.transact
              Ns.int(count).query.get.head ==> 0

              // Roll back outer savepoint delete
              outer.rollback()
              Ns.int(count).query.get.head ==> 7
            }

            // Nothing deleted
            Ns.int(count).query.get.head ==> 7
          }
        }

        // Nothing deleted
        Ns.int(count).query.get.head ==> 7
      }
    }


    "doubleSavepoint" - {
      /*
        Only one transaction can be present at a time, but savepoints can be arbitrarily nested.
        Uncaught exceptions or explicit `.rollback()` calls would roll back changes done during
        the inner savepoint/transaction blocks, while leaving changes applied during outer
        savepoint/transaction blocks in-place
        */
      "commit" - types { implicit conn =>
        Ns.int.insert(1 to 7).transact

        unitOfWork {
          savepoint { _ =>
            Ns.int(count).query.get.head ==> 7

            Ns.int_.<=(2).delete.transact
            Ns.int(count).query.get.head ==> 5

            savepoint { _ =>
              Ns.int_.<=(4).delete.transact
              Ns.int(count).query.get.head ==> 3

              savepoint { _ =>
                Ns.int_.<=(6).delete.transact
                Ns.int(count).query.get.head ==> 1
              }
              Ns.int(count).query.get.head ==> 1
            }
            Ns.int(count).query.get.head ==> 1
          }
          Ns.int(count).query.get.head ==> 1
        }

        // 1-6 deleted
        Ns.int(count).query.get.head ==> 1
      }


      "throw" - {

        "inner" - types { implicit conn =>
          Ns.int.insert(1 to 7).transact

          unitOfWork {
            savepoint { _ =>
              Ns.int(count).query.get.head ==> 7

              Ns.int_.<=(2).delete.transact
              Ns.int(count).query.get.head ==> 5

              savepoint { _ =>
                Ns.int_.<=(4).delete.transact
                Ns.int(count).query.get.head ==> 3

                try {
                  savepoint { _ =>
                    Ns.int_.<=(6).delete.transact
                    Ns.int(count).query.get.head ==> 1
                    throw new Exception
                  }
                } catch {
                  case e: Exception => /*donothing*/
                }
              }
              Ns.int(count).query.get.head ==> 3
            }
            Ns.int(count).query.get.head ==> 3
          }

          // 1-4 deleted
          Ns.int(count).query.get.head ==> 3
        }


        "middle" - types { implicit conn =>
          Ns.int.insert(1 to 7).transact

          unitOfWork {
            savepoint { _ =>
              Ns.int(count).query.get.head ==> 7

              Ns.int_.<=(2).delete.transact
              Ns.int(count).query.get.head ==> 5

              try {
                savepoint { _ =>
                  Ns.int_.<=(4).delete.transact
                  Ns.int(count).query.get.head ==> 3

                  savepoint { _ =>
                    Ns.int_.<=(6).delete.transact
                    Ns.int(count).query.get.head ==> 1
                  }

                  Ns.int(count).query.get.head ==> 1
                  throw new Exception
                }
              } catch {
                case e: Exception => /*donothing*/
              }
            }
            Ns.int(count).query.get.head ==> 5
          }

          // 1-2 deleted
          Ns.int(count).query.get.head ==> 5
        }


        "innerMiddle" - types { implicit conn =>
          Ns.int.insert(1 to 7).transact

          unitOfWork {
            savepoint { _ =>
              Ns.int(count).query.get.head ==> 7

              Ns.int_.<=(2).delete.transact
              Ns.int(count).query.get.head ==> 5

              try {
                savepoint { _ =>
                  Ns.int_.<=(4).delete.transact
                  Ns.int(count).query.get.head ==> 3

                  savepoint { _ =>
                    Ns.int_.<=(6).delete.transact
                    Ns.int(count).query.get.head ==> 1
                    throw new Exception
                  }
                }
              } catch {
                case e: Exception => /*donothing*/
              }
            }
            Ns.int(count).query.get.head ==> 5
          }

          // 1-2 deleted
          Ns.int(count).query.get.head ==> 5
        }


        "innerMiddleOuter" - types { implicit conn =>
          Ns.int.insert(1 to 7).transact

          try {
            unitOfWork {
              savepoint { _ =>
                Ns.int(count).query.get.head ==> 7

                Ns.int_.<=(2).delete.transact
                Ns.int(count).query.get.head ==> 5

                savepoint { _ =>
                  Ns.int_.<=(4).delete.transact
                  Ns.int(count).query.get.head ==> 3

                  savepoint { _ =>
                    Ns.int_.<=(6).delete.transact
                    Ns.int(count).query.get.head ==> 1
                    throw new Exception
                  }
                }

                Ns.int(count).query.get.head ==> 5
              }
            }
          } catch {
            case e: Exception => /*donothing*/
          }

          // Nothing deleted
          Ns.int(count).query.get.head ==> 7
        }
      }
    }


    "rollback" - {

      "inner" - types { implicit conn =>
        Ns.int.insert(1 to 7).transact

        unitOfWork {
          savepoint { outer =>
            Ns.int(count).query.get.head ==> 7

            Ns.int_.<=(2).delete.transact
            Ns.int(count).query.get.head ==> 5

            savepoint { middle =>
              Ns.int_.<=(4).delete.transact
              Ns.int(count).query.get.head ==> 3

              savepoint { inner =>
                Ns.int_.<=(6).delete.transact
                Ns.int(count).query.get.head ==> 1
                inner.rollback()
              }
            }
            Ns.int(count).query.get.head ==> 3
          }
          Ns.int(count).query.get.head ==> 3
        }

        // 1-4 deleted
        Ns.int(count).query.get.head ==> 3
      }


      "middle" - types { implicit conn =>
        Ns.int.insert(1 to 7).transact

        unitOfWork {
          savepoint { outer =>
            Ns.int(count).query.get.head ==> 7

            Ns.int_.<=(2).delete.transact
            Ns.int(count).query.get.head ==> 5

            savepoint { middle =>
              Ns.int_.<=(4).delete.transact
              Ns.int(count).query.get.head ==> 3

              savepoint { inner =>
                Ns.int_.<=(6).delete.transact
                Ns.int(count).query.get.head ==> 1
              }

              Ns.int(count).query.get.head ==> 1
              middle.rollback()
              Ns.int(count).query.get.head ==> 5
            }
          }
          Ns.int(count).query.get.head ==> 5
        }

        // 1-2 deleted
        Ns.int(count).query.get.head ==> 5
      }


      "innerMiddle" - types { implicit conn =>
        Ns.int.insert(1 to 7).transact

        unitOfWork {
          savepoint { outer =>
            Ns.int(count).query.get.head ==> 7

            Ns.int_.<=(2).delete.transact
            Ns.int(count).query.get.head ==> 5

            savepoint { middle =>
              Ns.int_.<=(4).delete.transact
              Ns.int(count).query.get.head ==> 3

              savepoint { inner =>
                Ns.int_.<=(6).delete.transact
                Ns.int(count).query.get.head ==> 1
                middle.rollback()
                Ns.int(count).query.get.head ==> 5
              }

              Ns.int(count).query.get.head ==> 5
            }
          }
          Ns.int(count).query.get.head ==> 5
        }

        // 1-2 deleted
        Ns.int(count).query.get.head ==> 5
      }


      "middleOuter" - types { implicit conn =>
        Ns.int.insert(1 to 7).transact

        unitOfWork {
          savepoint { outer =>
            Ns.int(count).query.get.head ==> 7

            Ns.int_.<=(2).delete.transact
            Ns.int(count).query.get.head ==> 5

            savepoint { middle =>
              Ns.int_.<=(4).delete.transact
              Ns.int(count).query.get.head ==> 3

              savepoint { inner =>
                Ns.int_.<=(6).delete.transact
                Ns.int(count).query.get.head ==> 1
              }

              Ns.int(count).query.get.head ==> 1
              outer.rollback()
              Ns.int(count).query.get.head ==> 7
            }
          }
          Ns.int(count).query.get.head ==> 7
        }

        // Nothing deleted
        Ns.int(count).query.get.head ==> 7
      }


      "innerMiddleOuter" - types { implicit conn =>
        Ns.int.insert(1 to 7).transact

        unitOfWork {
          savepoint { outer =>
            Ns.int(count).query.get.head ==> 7

            Ns.int_.<=(2).delete.transact
            Ns.int(count).query.get.head ==> 5

            savepoint { middle =>
              Ns.int_.<=(4).delete.transact
              Ns.int(count).query.get.head ==> 3

              savepoint { inner =>
                Ns.int_.<=(6).delete.transact
                Ns.int(count).query.get.head ==> 1
                outer.rollback()
                Ns.int(count).query.get.head ==> 7
              }

              Ns.int(count).query.get.head ==> 7
            }
          }
          Ns.int(count).query.get.head ==> 7
        }

        // Nothing deleted
        Ns.int(count).query.get.head ==> 7
      }
    }
  }
}
