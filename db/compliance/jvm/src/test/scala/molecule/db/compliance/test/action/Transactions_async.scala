package molecule.db.compliance.test.action

import molecule.db.base.error.ValidationErrors
import molecule.db.compliance.domains.dsl.Types.*
import molecule.db.compliance.setup.{DbProviders, Test, TestUtils}
import molecule.db.core.api.{Api_async, Api_async_transact}
import molecule.db.core.spi.{Conn, Spi_async}
import molecule.db.core.util.Executor.*
import scala.concurrent.Future


// Translated from
// https://github.com/com-lihaoyi/scalasql/blob/main/scalasql/test/src/api/TransactionTests.scala

case class Transactions_async(
  suite: Test,
  api: Api_async_transact & Api_async & Spi_async & DbProviders
) extends TestUtils {

  import api.*
  import suite.*


  "commit" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 7).transact
      _ <- Entity.int(count).query.get.map(_.head ==> 7)

      _ <- Entity.int_.delete.transact
      _ <- Entity.int(count).query.get.map(_.head ==> 0)
    } yield ()
  }


  "Transact actions: simple" - types { implicit conn =>
    for {
      _ <- transact(
        Entity.int(1).save,
        Entity.int(2).save,
      )
      _ <- Entity.int.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "Transact actions: mixed" - types { implicit conn =>
    for {
      _ <- transact(
        Entity.int(1).save, //         List(1)
        Entity.int.insert(2, 3), //    List(1, 2, 3)
        Entity(1).delete, //           List(2, 3)
        Entity(3).int.*(10).update, // List(2, 30)
      )
      _ <- Entity.int.a1.query.get.map(_ ==> List(2, 30))
    } yield ()
  }


  "Transact actions: validation 1" - validation { implicit conn =>
    import molecule.db.compliance.domains.dsl.Validation.*
    for {
      _ <- transact(
        Type.int(1).save, // not valid
        Type.int.insert(4, 5),
      ).recover {
        case ValidationErrors(errorMap) =>
          errorMap.head._2.head ==>
            s"""Type.int with value `1` doesn't satisfy validation:
               |_ > 2
               |""".stripMargin
      }

      // No data inserted/saved
      _ <- Type.int.query.get.map(_ ==> List())
    } yield ()
  }


  "Transact actions: validation 2" - validation { implicit conn =>
    import molecule.db.compliance.domains.dsl.Validation.*
    for {
      _ <- transact(
        Type.int.insert(4, 5),
        Type.int(2).save, // not valid
      ).recover {
        case ValidationErrors(errorMap) =>
          errorMap.head._2.head ==>
            s"""Type.int with value `2` doesn't satisfy validation:
               |_ > 2
               |""".stripMargin
      }

      // No data inserted/saved
      _ <- Type.int.query.get.map(_ ==> List())
    } yield ()
  }


  "Transact actions: validation 3" - validation { implicit conn =>
    import molecule.db.compliance.domains.dsl.Validation.*
    for {
      _ <- transact(
        Type.int.insert(4, 5),
        Type.int(3).save,
      )
      _ <- Type.int.a1.query.get.map(_ ==> List(3, 4, 5))
    } yield ()
  }


  "UnitOfWork: simple" - types { implicit conn =>
    for {
      _ <- unitOfWork {
        Entity.int(1).save.transact
        Entity.int(2).save.transact
      }
      _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "UnitOfWork: mixed" - types { implicit conn =>
    for {
      _ <- unitOfWork {
        Entity.int(1).save.transact //         List(1)
        Entity.int.insert(2, 3).transact //    List(1, 2, 3)
        Entity(1).delete.transact //           List(2, 3)
        Entity(3).int.*(10).update.transact // List(2, 30)
      }
      _ <- Entity.int.a1.query.get.map(_ ==> List(2, 30))
    } yield ()
  }


  "UnitOfWork: mixed with queries" - types { implicit conn =>
    for {
      _ <- unitOfWork {
        for {
          _ <- Entity.int(1).save.transact
          _ <- Entity.int.query.get.map(_ ==> List(1))

          _ <- Entity.int.insert(2, 3).transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))

          _ <- Entity(1).delete.transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(2, 3))

          _ <- Entity(3).int.*(10).update.transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(2, 30))
        } yield ()
      }
      _ <- Entity.int.a1.query.get.map(_ ==> List(2, 30))
    } yield ()
  }


  "UnitOfWork: abort save" - types { implicit conn =>
    for {
      _ <- Entity.int(1).save.transact
      _ <- unitOfWork {
        for {
          _ <- Entity.int(2).save.transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2))
          _ = throw new Exception()
        } yield ()
      }.recover {
        case _: Exception => ()
      }
      _ <- Entity.int.query.get.map(_ ==> List(1))
    } yield ()
  }


  "UnitOfWork: abort insert" - types { implicit conn =>
    for {
      _ <- Entity.int(1).save.transact
      _ <- unitOfWork {
        for {
          _ <- Entity.int.insert(2, 3).transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3))
          _ = throw new Exception()
        } yield ()
      }.recover {
        case _: Exception => ()
      }
      _ <- Entity.int.query.get.map(_ ==> List(1))
    } yield ()
  }


  "UnitOfWork: abort update" - types { implicit conn =>
    for {
      _ <- Entity.int(1).save.transact
      _ <- unitOfWork {
        for {
          _ <- Entity(1).int.*(10).update.transact
          _ <- Entity.int.query.get.map(_ ==> List(10))
          _ = throw new Exception()
        } yield ()
      }.recover {
        case _: Exception => ()
      }
      _ <- Entity.int.query.get.map(_ ==> List(1))
    } yield ()
  }


  "UnitOfWork: abort delete" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1, 2).transact
      _ <- unitOfWork {
        for {
          _ <- Entity(1).delete.transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2))
          _ = throw new Exception()
        } yield ()
      }.recover {
        case _: Exception => ()
      }
      _ <- Entity.int.query.get.map(_ ==> List(1, 2))
    } yield ()
  }


  "UnitOfWork: abort mixed" - types { implicit conn =>
    for {
      // Initial data
      _ <- Entity.int(1).save.transact

      _ <- unitOfWork {
        for {
          _ <- Entity.int(2).save.transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2))

          _ <- Entity.int.insert(3, 4).transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(1, 2, 3, 4))

          _ <- Entity(2).delete.transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(1, 3, 4))

          _ <- Entity(4).int.*(10).update.transact
          _ <- Entity.int.a1.query.get.map(_ ==> List(1, 3, 40))

          _ = throw new Exception()
        } yield ()
      }.recover {
        case _: Exception => ()
      }

      // Initial data remains intact
      _ <- Entity.int.query.get.map(_ ==> List(1))
    } yield ()
  }


  "UnitOfWork: money transfer" - types { implicit conn =>
    for {
      // Initial balance in two bank accounts
      _ <- Entity.s("fromAccount").int(100).save.transact
      _ <- Entity.s("toAccount").int(50).save.transact

      _ <- unitOfWork {
        for {
          _ <- Entity.s_("fromAccount").int.-(200).update.transact
          _ <- Entity.s_("toAccount").int.+(200).update.transact

          _ <- Entity.s_("fromAccount").int.query.get
            .map { res =>
              val fromBalance = res.head
              // Check that fromAccount had sufficient funds
              if (fromBalance < 0) {
                // Abort all transactions in this unit of work
                throw new Exception(
                  "Insufficient funds in fromAccount..."
                )
              }
            }
        } yield ()
      }.recover {
        case e =>
          // Do something with failure...
          e.getMessage ==> "Insufficient funds in fromAccount..."
      }

      // No data transacted
      _ <- Entity.s.int.query.get.map(_ ==> List(
        ("fromAccount", 100),
        ("toAccount", 50),
      ))
    } yield ()
  }


  // Transfer logic separated with unitOfWork
  def transfer(from: String, to: String, amount: Int)
              (implicit conn: Conn): Future[Unit] = {
    unitOfWork {
      for {
        _ <- Entity.s_(from).int.-(amount).update.transact
        _ <- Entity.s_(to).int.+(amount).update.transact

        _ <- Entity.s_(from).int.query.get
          .map { res =>
            val fromBalance = res.head
            // Check that fromAccount had sufficient funds
            if (fromBalance < 0) {
              // Abort all transactions in this unit of work
              throw new Exception(
                "Insufficient funds in fromAccount..."
              )
            }
          }
      } yield ()
    }
  }

  "UnitOfWork: money transfer2" - types { implicit conn =>
    for {
      // Initial balance in two bank accounts
      _ <- Entity.s("fromAccount").int(100).save.transact
      _ <- Entity.s("toAccount").int(50).save.transact

      _ <- transfer("fromAccount", "toAccount", 80)
        .recover { case e =>
          // Do something with failure...
          e.getMessage ==> "Insufficient funds in fromAccount..."
        }

      // Amount transferred
      _ <- Entity.s.int.a1.query.get.map(_ ==> List(
        ("fromAccount", 20),
        ("toAccount", 130),
      ))
    } yield ()
  }


  "Savepoint: commit" - types { implicit conn =>
    unitOfWork {
      for {
        _ <- Entity.int.insert(1 to 4).transact
        _ <- Entity.int(count).query.get.map(_.head ==> 4)
        _ <- savepoint { _ =>
          for {
            // Delete all
            _ <- Entity.int_.delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 0)
          } yield ()
        }

        // All deleted
        _ <- Entity.int(count).query.get.map(_.head ==> 0)
      } yield ()
    }
  }

  // Without rollbacks, the above is the same as the following:
  "Savepoint: commit2" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 4).transact
      _ <- Entity.int(count).query.get.map(_.head ==> 4)

      // Delete all
      _ <- Entity.int_.delete.transact
      _ <- Entity.int(count).query.get.map(_.head ==> 0)

      // All deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 0)
    } yield ()
  }


  "Savepoint: rollback" - types { implicit conn =>
    unitOfWork {
      for {
        _ <- Entity.int.insert(1 to 4).transact
        _ <- Entity.int(count).query.get.map(_.head ==> 4)

        _ <- savepoint { sp =>
          for {
            // Delete all
            _ <- Entity.int_.delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 0)

            // Roll back delete
            _ = sp.rollback()
            _ <- Entity.int(count).query.get.map(_.head ==> 4)
          } yield ()
        }

        // Nothing deleted
        _ <- Entity.int(count).query.get.map(_.head ==> 4)
      } yield ()
    }
  }


  "Savepoint: rollback2" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 4).transact
      _ <- Entity.int(count).query.get.map(_.head ==> 4)

      _ <- unitOfWork {
        savepoint { sp =>
          for {
            // Delete all
            _ <- Entity.int_.delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 0)

            // Roll back delete
            _ = sp.rollback()
            _ <- Entity.int(count).query.get.map(_.head ==> 4)
          } yield ()
        }
      }

      // Nothing deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 4)
    } yield ()
  }


  "Savepoint: throw" - types { implicit conn =>
    unitOfWork {
      for {
        _ <- Entity.int.insert(1 to 4).transact
        _ <- Entity.int(count).query.get.map(_.head ==> 4)

        _ <- savepoint { _ =>
          for {
            // Delete all
            _ <- Entity.int_.delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 0)

            // Roll back delete by throwing exception
          } yield {
            throw new Exception("handle error...")
          }
        }.recover {
          case e => e.getMessage ==> "handle error..."
        }

        // Nothing deleted
        _ <- Entity.int(count).query.get.map(_.head ==> 4)
      } yield ()
    }
  }

  "Savepoint: throw2" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 4).transact
      _ <- Entity.int(count).query.get.map(_.head ==> 4)

      _ <- unitOfWork {
        savepoint { _ =>
          for {
            // Delete all
            _ <- Entity.int_.delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 0)
          } yield {
            // Roll back delete by throwing exception
            throw new Exception("handle error...")
          }
        }
      }.recover {
        case e => e.getMessage ==> "handle error..."
      }

      // Nothing deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 4)
    } yield ()
  }


  "Savepoint: throwDouble" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 7).transact

      _ <- unitOfWork {
        savepoint { outer =>
          for {
            _ <- Entity.int(count).query.get.map(_.head ==> 7)

            _ <- Entity.int_.<=(3).delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 4)

            _ <- savepoint { inner =>
              for {
                // Delete all
                _ <- Entity.int_.delete.transact
                _ <- Entity.int(count).query.get.map(_.head ==> 0)
              } yield {
                // Roll back delete by throwing exception
                throw new Exception("inner error...")
              }
            }.recover { case inner =>
              throw inner
            }
            _ <- Entity.int(count).query.get.map(_.head ==> 4)
          } yield ()
        }
      }.recover { case inner =>
        inner.getMessage ==> "inner error..."
      }

      // Nothing deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 7)
    } yield ()
  }


  "Savepoint: rollbackDouble" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 7).transact

      _ <- unitOfWork {
        savepoint { outer =>
          for {
            _ <- Entity.int(count).query.get.map(_.head ==> 7)

            _ <- Entity.int_.<=(3).delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 4)

            _ <- savepoint { inner =>
              for {
                // Delete all
                _ <- Entity.int_.delete.transact
                _ <- Entity.int(count).query.get.map(_.head ==> 0)

                _ = outer.rollback()
                _ <- Entity.int(count).query.get.map(_.head ==> 7)
              } yield ()
            }

            // Nothing deleted
            _ <- Entity.int(count).query.get.map(_.head ==> 7)
          } yield ()
        }
      }

      // Nothing deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 7)
    } yield ()
  }


  "Double savepoint: commit" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 7).transact

      _ <- unitOfWork {
        savepoint { _ =>
          for {
            _ <- Entity.int(count).query.get.map(_.head ==> 7)

            _ <- Entity.int_.<=(2).delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 5)

            _ <- savepoint { _ =>
              for {
                _ <- Entity.int_.<=(4).delete.transact
                _ <- Entity.int(count).query.get.map(_.head ==> 3)

                _ <- savepoint { _ =>
                  for {
                    _ <- Entity.int_.<=(6).delete.transact
                    _ <- Entity.int(count).query.get.map(_.head ==> 1)
                  } yield ()
                }
                _ <- Entity.int(count).query.get.map(_.head ==> 1)
              } yield ()
            }
            _ <- Entity.int(count).query.get.map(_.head ==> 1)
          } yield ()
        }
      }

      // 1-6 deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 1)
    } yield ()
  }


  "throw" - {

    "throw: inner" - types { implicit conn =>
      for {
        _ <- Entity.int.insert(1 to 7).transact

        _ <- unitOfWork {
          savepoint { _ =>
            for {
              _ <- Entity.int(count).query.get.map(_.head ==> 7)

              _ <- Entity.int_.<=(2).delete.transact
              _ <- Entity.int(count).query.get.map(_.head ==> 5)

              _ <- savepoint { _ =>
                for {
                  _ <- Entity.int_.<=(4).delete.transact
                  _ <- Entity.int(count).query.get.map(_.head ==> 3)

                  _ <- savepoint { _ =>
                    for {
                      _ <- Entity.int_.<=(6).delete.transact
                      _ <- Entity.int(count).query.get.map(_.head ==> 1)
                    } yield {
                      throw new Exception
                    }
                  }.recover {
                    case e: Exception => /*donothing*/
                  }
                  _ <- Entity.int(count).query.get.map(_.head ==> 3)
                } yield ()
              }
              _ <- Entity.int(count).query.get.map(_.head ==> 3)
            } yield ()
          }
        }

        // 1-4 deleted
        _ <- Entity.int(count).query.get.map(_.head ==> 3)
      } yield ()
    }


    "throw: middle" - types { implicit conn =>
      for {
        _ <- Entity.int.insert(1 to 7).transact

        _ <- unitOfWork {
          savepoint { _ =>
            for {
              _ <- Entity.int(count).query.get.map(_.head ==> 7)

              _ <- Entity.int_.<=(2).delete.transact
              _ <- Entity.int(count).query.get.map(_.head ==> 5)

              _ <- savepoint { _ =>
                for {
                  _ <- Entity.int_.<=(4).delete.transact
                  _ <- Entity.int(count).query.get.map(_.head ==> 3)

                  _ <- savepoint { _ =>
                    for {
                      _ <- Entity.int_.<=(6).delete.transact
                      _ <- Entity.int(count).query.get.map(_.head ==> 1)
                    } yield ()
                  }
                  _ <- Entity.int(count).query.get.map(_.head ==> 1)
                } yield {
                  throw new Exception
                }
              }.recover {
                case e: Exception => /*donothing*/
              }
              _ <- Entity.int(count).query.get.map(_.head ==> 5)
            } yield ()
          }
        }

        // 1-2 deleted
        _ <- Entity.int(count).query.get.map(_.head ==> 5)
      } yield ()
    }


    "throw: innerMiddle" - types { implicit conn =>
      for {
        _ <- Entity.int.insert(1 to 7).transact

        _ <- unitOfWork {
          savepoint { _ =>
            for {
              _ <- Entity.int(count).query.get.map(_.head ==> 7)

              _ <- Entity.int_.<=(2).delete.transact
              _ <- Entity.int(count).query.get.map(_.head ==> 5)

              _ <- savepoint { _ =>
                for {
                  _ <- Entity.int_.<=(4).delete.transact
                  _ <- Entity.int(count).query.get.map(_.head ==> 3)

                  _ <- savepoint { _ =>
                    for {
                      _ <- Entity.int_.<=(6).delete.transact
                      _ <- Entity.int(count).query.get.map(_.head ==> 1)
                    } yield {
                      throw new Exception
                    }
                  }
                } yield ()
              }.recover {
                case e: Exception => /*donothing*/
              }
              _ <- Entity.int(count).query.get.map(_.head ==> 5)
            } yield ()
          }
        }

        // 1-2 deleted
        _ <- Entity.int(count).query.get.map(_.head ==> 5)
      } yield ()
    }


    "throw: innerMiddleOuter" - types { implicit conn =>
      for {
        _ <- Entity.int.insert(1 to 7).transact

        _ <- unitOfWork {
          savepoint { _ =>
            for {
              _ <- Entity.int(count).query.get.map(_.head ==> 7)

              _ <- Entity.int_.<=(2).delete.transact
              _ <- Entity.int(count).query.get.map(_.head ==> 5)

              _ <- savepoint { _ =>
                for {
                  _ <- Entity.int_.<=(4).delete.transact
                  _ <- Entity.int(count).query.get.map(_.head ==> 3)

                  _ <- savepoint { _ =>
                    for {
                      _ <- Entity.int_.<=(6).delete.transact
                      _ <- Entity.int(count).query.get.map(_.head ==> 1)
                    } yield {
                      throw new Exception
                    }
                  }
                } yield ()
              }
              _ <- Entity.int(count).query.get.map(_.head ==> 5)
            } yield ()
          }.recover {
            case e: Exception => /*donothing*/
          }
        }

        // Nothing deleted
        _ <- Entity.int(count).query.get.map(_.head ==> 7)
      } yield ()
    }
  }


  "Rollback: inner" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 7).transact

      _ <- unitOfWork {
        savepoint { outer =>
          for {
            _ <- Entity.int(count).query.get.map(_.head ==> 7)

            _ <- Entity.int_.<=(2).delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 5)

            _ <- savepoint { middle =>
              for {
                _ <- Entity.int_.<=(4).delete.transact
                _ <- Entity.int(count).query.get.map(_.head ==> 3)

                _ <- savepoint { inner =>
                  for {
                    _ <- Entity.int_.<=(6).delete.transact
                    _ <- Entity.int(count).query.get.map(_.head ==> 1)
                    _ = inner.rollback()
                  } yield ()
                }
                _ <- Entity.int(count).query.get.map(_.head ==> 3)
              } yield ()
            }
            _ <- Entity.int(count).query.get.map(_.head ==> 3)
          } yield ()
        }
      }

      // 1-4 deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 3)
    } yield ()
  }


  "Rollback: middle" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 7).transact

      _ <- unitOfWork {
        savepoint { outer =>
          for {
            _ <- Entity.int(count).query.get.map(_.head ==> 7)

            _ <- Entity.int_.<=(2).delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 5)

            _ <- savepoint { middle =>
              for {
                _ <- Entity.int_.<=(4).delete.transact
                _ <- Entity.int(count).query.get.map(_.head ==> 3)

                _ <- savepoint { inner =>
                  for {
                    _ <- Entity.int_.<=(6).delete.transact
                    _ <- Entity.int(count).query.get.map(_.head ==> 1)
                  } yield ()
                }
                _ <- Entity.int(count).query.get.map(_.head ==> 1)
                _ = middle.rollback()
                _ <- Entity.int(count).query.get.map(_.head ==> 5)
              } yield ()
            }
            _ <- Entity.int(count).query.get.map(_.head ==> 5)
          } yield ()
        }
      }

      // 1-2 deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 5)
    } yield ()
  }


  "Rollback: innerMiddle" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 7).transact

      _ <- unitOfWork {
        savepoint { outer =>
          for {
            _ <- Entity.int(count).query.get.map(_.head ==> 7)

            _ <- Entity.int_.<=(2).delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 5)

            _ <- savepoint { middle =>
              for {
                _ <- Entity.int_.<=(4).delete.transact
                _ <- Entity.int(count).query.get.map(_.head ==> 3)

                _ <- savepoint { inner =>
                  for {
                    _ <- Entity.int_.<=(6).delete.transact
                    _ <- Entity.int(count).query.get.map(_.head ==> 1)
                    _ = middle.rollback()
                    _ <- Entity.int(count).query.get.map(_.head ==> 5)
                  } yield ()
                }
                _ <- Entity.int(count).query.get.map(_.head ==> 5)
              } yield ()
            }
            _ <- Entity.int(count).query.get.map(_.head ==> 5)
          } yield ()
        }
      }

      // 1-2 deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 5)
    } yield ()
  }


  "Rollback: middleOuter" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 7).transact

      _ <- unitOfWork {
        savepoint { outer =>
          for {
            _ <- Entity.int(count).query.get.map(_.head ==> 7)

            _ <- Entity.int_.<=(2).delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 5)

            _ <- savepoint { middle =>
              for {
                _ <- Entity.int_.<=(4).delete.transact
                _ <- Entity.int(count).query.get.map(_.head ==> 3)

                _ <- savepoint { inner =>
                  for {
                    _ <- Entity.int_.<=(6).delete.transact
                    _ <- Entity.int(count).query.get.map(_.head ==> 1)
                  } yield ()
                }
                _ <- Entity.int(count).query.get.map(_.head ==> 1)
                _ = outer.rollback()
                _ <- Entity.int(count).query.get.map(_.head ==> 7)
              } yield ()
            }
            _ <- Entity.int(count).query.get.map(_.head ==> 7)
          } yield ()
        }
      }

      // Nothing deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 7)
    } yield ()
  }


  "Rollback: innerMiddleOuter" - types { implicit conn =>
    for {
      _ <- Entity.int.insert(1 to 7).transact

      _ <- unitOfWork {
        savepoint { outer =>
          for {
            _ <- Entity.int(count).query.get.map(_.head ==> 7)

            _ <- Entity.int_.<=(2).delete.transact
            _ <- Entity.int(count).query.get.map(_.head ==> 5)

            _ <- savepoint { middle =>
              for {
                _ <- Entity.int_.<=(4).delete.transact
                _ <- Entity.int(count).query.get.map(_.head ==> 3)

                _ <- savepoint { inner =>
                  for {
                    _ <- Entity.int_.<=(6).delete.transact
                    _ <- Entity.int(count).query.get.map(_.head ==> 1)
                    _ = outer.rollback()
                    _ <- Entity.int(count).query.get.map(_.head ==> 7)
                  } yield ()
                }
                _ <- Entity.int(count).query.get.map(_.head ==> 7)
              } yield ()
            }
            _ <- Entity.int(count).query.get.map(_.head ==> 7)
          } yield ()
        }
      }

      // Nothing deleted
      _ <- Entity.int(count).query.get.map(_.head ==> 7)
    } yield ()
  }
}
