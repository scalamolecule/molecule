package molecule.coreTests.spi.transaction

import molecule.base.error.ValidationErrors
import molecule.core.api.{Api_async, Api_async_transact}
import molecule.core.spi.{Conn, Spi_async}
import molecule.core.util.Executor._
import molecule.coreTests.dataModels.dsl.Types._
import molecule.coreTests.setup.CoreTestSuite
import utest._
import scala.concurrent.Future

// Translated from
// https://github.com/com-lihaoyi/scalasql/blob/main/scalasql/test/src/api/TransactionTests.scala

trait Transactions_async extends CoreTestSuite with Api_async with Api_async_transact { spi: Spi_async =>

  override lazy val tests = Tests {

    "commit" - types { implicit conn =>
      for {
        _ <- Ns.int.insert(1 to 7).transact
        _ <- Ns.int(count).query.get.map(_.head ==> 7)

        _ <- Ns.int_.delete.transact
        _ <- Ns.int(count).query.get.map(_.head ==> 0)
      } yield ()
    }


    "transact actions" - {

      "simple" - types { implicit conn =>
        for {
          _ <- transact(
            Ns.int(1).save,
            Ns.int(2).save,
          )
          _ <- Ns.int.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "mixed" - types { implicit conn =>
        for {
          _ <- transact(
            Ns.int(1).save, //         List(1)
            Ns.int.insert(2, 3), //    List(1, 2, 3)
            Ns(1).delete, //           List(2, 3)
            Ns(3).int.*(10).update, // List(2, 30)
          )
          _ <- Ns.int.query.get.map(_ ==> List(2, 30))
        } yield ()
      }


      "validation 1" - validation { implicit conn =>
        import molecule.coreTests.dataModels.dsl.Validation._
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


      "validation 2" - validation { implicit conn =>
        import molecule.coreTests.dataModels.dsl.Validation._
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


      "validation 3" - validation { implicit conn =>
        import molecule.coreTests.dataModels.dsl.Validation._
        for {
          _ <- transact(
            Type.int.insert(4, 5),
            Type.int(3).save,
          )
          _ <- Type.int.a1.query.get.map(_ ==> List(3, 4, 5))
        } yield ()
      }
    }


    "unitOfWork" - {

      "simple" - types { implicit conn =>
        for {
          _ <- unitOfWork {
            Ns.int(1).save.transact
            Ns.int(2).save.transact
          }
          _ <- Ns.int.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "mixed" - types { implicit conn =>
        for {
          _ <- unitOfWork {
            Ns.int(1).save.transact //         List(1)
            Ns.int.insert(2, 3).transact //    List(1, 2, 3)
            Ns(1).delete.transact //           List(2, 3)
            Ns(3).int.*(10).update.transact // List(2, 30)
          }
          _ <- Ns.int.query.get.map(_ ==> List(2, 30))
        } yield ()
      }


      "mixed with queries" - types { implicit conn =>
        for {
          _ <- unitOfWork {
            for {
              _ <- Ns.int(1).save.transact
              _ <- Ns.int.query.get.map(_ ==> List(1))

              _ <- Ns.int.insert(2, 3).transact
              _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))

              _ <- Ns(1).delete.transact
              _ <- Ns.int.query.get.map(_ ==> List(2, 3))

              _ <- Ns(3).int.*(10).update.transact
              _ <- Ns.int.query.get.map(_ ==> List(2, 30))
            } yield ()
          }
          _ <- Ns.int.query.get.map(_ ==> List(2, 30))
        } yield ()
      }


      "abort save" - types { implicit conn =>
        for {
          _ <- Ns.int(1).save.transact
          _ <- unitOfWork {
            for {
              _ <- Ns.int(2).save.transact
              _ <- Ns.int.query.get.map(_ ==> List(1, 2))
              _ = throw new Exception()
            } yield ()
          }.recover {
            case _: Exception => ()
          }
          _ <- Ns.int.query.get.map(_ ==> List(1))
        } yield ()
      }


      "abort insert" - types { implicit conn =>
        for {
          _ <- Ns.int(1).save.transact
          _ <- unitOfWork {
            for {
              _ <- Ns.int.insert(2, 3).transact
              _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3))
              _ = throw new Exception()
            } yield ()
          }.recover {
            case _: Exception => ()
          }
          _ <- Ns.int.query.get.map(_ ==> List(1))
        } yield ()
      }


      "abort update" - types { implicit conn =>
        for {
          _ <- Ns.int(1).save.transact
          _ <- unitOfWork {
            for {
              _ <- Ns(1).int.*(10).update.transact
              _ <- Ns.int.query.get.map(_ ==> List(10))
              _ = throw new Exception()
            } yield ()
          }.recover {
            case _: Exception => ()
          }
          _ <- Ns.int.query.get.map(_ ==> List(1))
        } yield ()
      }


      "abort delete" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1, 2).transact
          _ <- unitOfWork {
            for {
              _ <- Ns(1).delete.transact
              _ <- Ns.int.query.get.map(_ ==> List(1, 2))
              _ = throw new Exception()
            } yield ()
          }.recover {
            case _: Exception => ()
          }
          _ <- Ns.int.query.get.map(_ ==> List(1, 2))
        } yield ()
      }


      "abort mixed" - types { implicit conn =>
        for {
          // Initial data
          _ <- Ns.int(1).save.transact

          _ <- unitOfWork {
            for {
              _ <- Ns.int(2).save.transact
              _ <- Ns.int.query.get.map(_ ==> List(1, 2))

              _ <- Ns.int.insert(3, 4).transact
              _ <- Ns.int.query.get.map(_ ==> List(1, 2, 3, 4))

              _ <- Ns(2).delete.transact
              _ <- Ns.int.query.get.map(_ ==> List(1, 3, 4))

              _ <- Ns(4).int.*(10).update.transact
              _ <- Ns.int.query.get.map(_ ==> List(1, 3, 40))

              _ = throw new Exception()
            } yield ()
          }.recover {
            case _: Exception => ()
          }

          // Initial data remains intact
          _ <- Ns.int.query.get.map(_ ==> List(1))
        } yield ()
      }


      "money transfer" - types { implicit conn =>
        for {
          // Initial balance in two bank accounts
          _ <- Ns.s("fromAccount").int(100).save.transact
          _ <- Ns.s("toAccount").int(50).save.transact

          _ <- unitOfWork {
            for {
              _ <- Ns.s_("fromAccount").int.-(200).update.transact
              _ <- Ns.s_("toAccount").int.+(200).update.transact

              _ <- Ns.s_("fromAccount").int.query.get
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
          _ <- Ns.s.int.query.get.map(_ ==> List(
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
            _ <- Ns.s_(from).int.-(amount).update.transact
            _ <- Ns.s_(to).int.+(amount).update.transact

            _ <- Ns.s_(from).int.query.get
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

      "money transfer2" - types { implicit conn =>
        for {
          // Initial balance in two bank accounts
          _ <- Ns.s("fromAccount").int(100).save.transact
          _ <- Ns.s("toAccount").int(50).save.transact

          _ <- transfer("fromAccount", "toAccount", 80)
            .recover { case e =>
              // Do something with failure...
              e.getMessage ==> "Insufficient funds in fromAccount..."
            }

          // Amount transferred
          _ <- Ns.s.int.a1.query.get.map(_ ==> List(
            ("fromAccount", 20),
            ("toAccount", 130),
          ))
        } yield ()
      }
    }


    "savepoint" - {

      "commit" - types { implicit conn =>
        unitOfWork {
          for {
            _ <- Ns.int.insert(1 to 4).transact
            _ <- Ns.int(count).query.get.map(_.head ==> 4)
            _ <- savepoint { _ =>
              for {
                // Delete all
                _ <- Ns.int_.delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 0)
              } yield ()
            }

            // All deleted
            _ <- Ns.int(count).query.get.map(_.head ==> 0)
          } yield ()
        }
      }

      // Without rollbacks, the above is the same as the following:
      "commit2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 4).transact
          _ <- Ns.int(count).query.get.map(_.head ==> 4)

          // Delete all
          _ <- Ns.int_.delete.transact
          _ <- Ns.int(count).query.get.map(_.head ==> 0)

          // All deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 0)
        } yield ()
      }


      "rollback" - types { implicit conn =>
        unitOfWork {
          for {
            _ <- Ns.int.insert(1 to 4).transact
            _ <- Ns.int(count).query.get.map(_.head ==> 4)

            _ <- savepoint { sp =>
              for {
                // Delete all
                _ <- Ns.int_.delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 0)

                // Roll back delete
                _ = sp.rollback()
                _ <- Ns.int(count).query.get.map(_.head ==> 4)
              } yield ()
            }

            // Nothing deleted
            _ <- Ns.int(count).query.get.map(_.head ==> 4)
          } yield ()
        }
      }


      "rollback2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 4).transact
          _ <- Ns.int(count).query.get.map(_.head ==> 4)

          _ <- unitOfWork {
            savepoint { sp =>
              for {
                // Delete all
                _ <- Ns.int_.delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 0)

                // Roll back delete
                _ = sp.rollback()
                _ <- Ns.int(count).query.get.map(_.head ==> 4)
              } yield ()
            }
          }

          // Nothing deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 4)
        } yield ()
      }


      "throw" - types { implicit conn =>
        unitOfWork {
          for {
            _ <- Ns.int.insert(1 to 4).transact
            _ <- Ns.int(count).query.get.map(_.head ==> 4)

            _ <- savepoint { _ =>
              for {
                // Delete all
                _ <- Ns.int_.delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 0)

                // Roll back delete by throwing exception
              } yield {
                throw new Exception("handle error...")
              }
            }.recover {
              case e => e.getMessage ==> "handle error..."
            }

            // Nothing deleted
            _ <- Ns.int(count).query.get.map(_.head ==> 4)
          } yield ()
        }
      }

      "throw2" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 4).transact
          _ <- Ns.int(count).query.get.map(_.head ==> 4)

          _ <- unitOfWork {
            savepoint { _ =>
              for {
                // Delete all
                _ <- Ns.int_.delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 0)
              } yield {
                // Roll back delete by throwing exception
                throw new Exception("handle error...")
              }
            }
          }.recover {
            case e => e.getMessage ==> "handle error..."
          }

          // Nothing deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 4)
        } yield ()
      }


      "throwDouble" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 7).transact

          _ <- unitOfWork {
            savepoint { outer =>
              for {
                _ <- Ns.int(count).query.get.map(_.head ==> 7)

                _ <- Ns.int_.<=(3).delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 4)

                _ <- savepoint { inner =>
                  for {
                    // Delete all
                    _ <- Ns.int_.delete.transact
                    _ <- Ns.int(count).query.get.map(_.head ==> 0)
                  } yield {
                    // Roll back delete by throwing exception
                    throw new Exception("inner error...")
                  }
                }.recover { case inner =>
                  throw inner
                }
                _ <- Ns.int(count).query.get.map(_.head ==> 4)
              } yield ()
            }
          }.recover { case inner =>
            inner.getMessage ==> "inner error..."
          }

          // Nothing deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 7)
        } yield ()
      }


      "rollbackDouble" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 7).transact

          _ <- unitOfWork {
            savepoint { outer =>
              for {
                _ <- Ns.int(count).query.get.map(_.head ==> 7)

                _ <- Ns.int_.<=(3).delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 4)

                _ <- savepoint { inner =>
                  for {
                    // Delete all
                    _ <- Ns.int_.delete.transact
                    _ <- Ns.int(count).query.get.map(_.head ==> 0)

                    _ = outer.rollback()
                    _ <- Ns.int(count).query.get.map(_.head ==> 7)
                  } yield ()
                }

                // Nothing deleted
                _ <- Ns.int(count).query.get.map(_.head ==> 7)
              } yield ()
            }
          }

          // Nothing deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 7)
        } yield ()
      }
    }


    "doubleSavepoint" - {

      "commit" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 7).transact

          _ <- unitOfWork {
            savepoint { _ =>
              for {
                _ <- Ns.int(count).query.get.map(_.head ==> 7)

                _ <- Ns.int_.<=(2).delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 5)

                _ <- savepoint { _ =>
                  for {
                    _ <- Ns.int_.<=(4).delete.transact
                    _ <- Ns.int(count).query.get.map(_.head ==> 3)

                    _ <- savepoint { _ =>
                      for {
                        _ <- Ns.int_.<=(6).delete.transact
                        _ <- Ns.int(count).query.get.map(_.head ==> 1)
                      } yield ()
                    }
                    _ <- Ns.int(count).query.get.map(_.head ==> 1)
                  } yield ()
                }
                _ <- Ns.int(count).query.get.map(_.head ==> 1)
              } yield ()
            }
          }

          // 1-6 deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 1)
        } yield ()
      }


      "throw" - {

        "inner" - types { implicit conn =>
          for {
            _ <- Ns.int.insert(1 to 7).transact

            _ <- unitOfWork {
              savepoint { _ =>
                for {
                  _ <- Ns.int(count).query.get.map(_.head ==> 7)

                  _ <- Ns.int_.<=(2).delete.transact
                  _ <- Ns.int(count).query.get.map(_.head ==> 5)

                  _ <- savepoint { _ =>
                    for {
                      _ <- Ns.int_.<=(4).delete.transact
                      _ <- Ns.int(count).query.get.map(_.head ==> 3)

                      _ <- savepoint { _ =>
                        for {
                          _ <- Ns.int_.<=(6).delete.transact
                          _ <- Ns.int(count).query.get.map(_.head ==> 1)
                        } yield {
                          throw new Exception
                        }
                      }.recover {
                        case e: Exception => /*donothing*/
                      }
                      _ <- Ns.int(count).query.get.map(_.head ==> 3)
                    } yield ()
                  }
                  _ <- Ns.int(count).query.get.map(_.head ==> 3)
                } yield ()
              }
            }

            // 1-4 deleted
            _ <- Ns.int(count).query.get.map(_.head ==> 3)
          } yield ()
        }


        "middle" - types { implicit conn =>
          for {
            _ <- Ns.int.insert(1 to 7).transact

            _ <- unitOfWork {
              savepoint { _ =>
                for {
                  _ <- Ns.int(count).query.get.map(_.head ==> 7)

                  _ <- Ns.int_.<=(2).delete.transact
                  _ <- Ns.int(count).query.get.map(_.head ==> 5)

                  _ <- savepoint { _ =>
                    for {
                      _ <- Ns.int_.<=(4).delete.transact
                      _ <- Ns.int(count).query.get.map(_.head ==> 3)

                      _ <- savepoint { _ =>
                        for {
                          _ <- Ns.int_.<=(6).delete.transact
                          _ <- Ns.int(count).query.get.map(_.head ==> 1)
                        } yield ()
                      }
                      _ <- Ns.int(count).query.get.map(_.head ==> 1)
                    } yield {
                      throw new Exception
                    }
                  }.recover {
                    case e: Exception => /*donothing*/
                  }
                  _ <- Ns.int(count).query.get.map(_.head ==> 5)
                } yield ()
              }
            }

            // 1-2 deleted
            _ <- Ns.int(count).query.get.map(_.head ==> 5)
          } yield ()
        }


        "innerMiddle" - types { implicit conn =>
          for {
            _ <- Ns.int.insert(1 to 7).transact

            _ <- unitOfWork {
              savepoint { _ =>
                for {
                  _ <- Ns.int(count).query.get.map(_.head ==> 7)

                  _ <- Ns.int_.<=(2).delete.transact
                  _ <- Ns.int(count).query.get.map(_.head ==> 5)

                  _ <- savepoint { _ =>
                    for {
                      _ <- Ns.int_.<=(4).delete.transact
                      _ <- Ns.int(count).query.get.map(_.head ==> 3)

                      _ <- savepoint { _ =>
                        for {
                          _ <- Ns.int_.<=(6).delete.transact
                          _ <- Ns.int(count).query.get.map(_.head ==> 1)
                        } yield {
                          throw new Exception
                        }
                      }
                    } yield ()
                  }.recover {
                    case e: Exception => /*donothing*/
                  }
                  _ <- Ns.int(count).query.get.map(_.head ==> 5)
                } yield ()
              }
            }

            // 1-2 deleted
            _ <- Ns.int(count).query.get.map(_.head ==> 5)
          } yield ()
        }


        "innerMiddleOuter" - types { implicit conn =>
          for {
            _ <- Ns.int.insert(1 to 7).transact

            _ <- unitOfWork {
              savepoint { _ =>
                for {
                  _ <- Ns.int(count).query.get.map(_.head ==> 7)

                  _ <- Ns.int_.<=(2).delete.transact
                  _ <- Ns.int(count).query.get.map(_.head ==> 5)

                  _ <- savepoint { _ =>
                    for {
                      _ <- Ns.int_.<=(4).delete.transact
                      _ <- Ns.int(count).query.get.map(_.head ==> 3)

                      _ <- savepoint { _ =>
                        for {
                          _ <- Ns.int_.<=(6).delete.transact
                          _ <- Ns.int(count).query.get.map(_.head ==> 1)
                        } yield {
                          throw new Exception
                        }
                      }
                    } yield ()
                  }
                  _ <- Ns.int(count).query.get.map(_.head ==> 5)
                } yield ()
              }.recover {
                case e: Exception => /*donothing*/
              }
            }

            // Nothing deleted
            _ <- Ns.int(count).query.get.map(_.head ==> 7)
          } yield ()
        }
      }
    }


    "rollback" - {

      "inner" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 7).transact

          _ <- unitOfWork {
            savepoint { outer =>
              for {
                _ <- Ns.int(count).query.get.map(_.head ==> 7)

                _ <- Ns.int_.<=(2).delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 5)

                _ <- savepoint { middle =>
                  for {
                    _ <- Ns.int_.<=(4).delete.transact
                    _ <- Ns.int(count).query.get.map(_.head ==> 3)

                    _ <- savepoint { inner =>
                      for {
                        _ <- Ns.int_.<=(6).delete.transact
                        _ <- Ns.int(count).query.get.map(_.head ==> 1)
                        _ = inner.rollback()
                      } yield ()
                    }
                    _ <- Ns.int(count).query.get.map(_.head ==> 3)
                  } yield ()
                }
                _ <- Ns.int(count).query.get.map(_.head ==> 3)
              } yield ()
            }
          }

          // 1-4 deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 3)
        } yield ()
      }


      "middle" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 7).transact

          _ <- unitOfWork {
            savepoint { outer =>
              for {
                _ <- Ns.int(count).query.get.map(_.head ==> 7)

                _ <- Ns.int_.<=(2).delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 5)

                _ <- savepoint { middle =>
                  for {
                    _ <- Ns.int_.<=(4).delete.transact
                    _ <- Ns.int(count).query.get.map(_.head ==> 3)

                    _ <- savepoint { inner =>
                      for {
                        _ <- Ns.int_.<=(6).delete.transact
                        _ <- Ns.int(count).query.get.map(_.head ==> 1)
                      } yield ()
                    }
                    _ <- Ns.int(count).query.get.map(_.head ==> 1)
                    _ = middle.rollback()
                    _ <- Ns.int(count).query.get.map(_.head ==> 5)
                  } yield ()
                }
                _ <- Ns.int(count).query.get.map(_.head ==> 5)
              } yield ()
            }
          }

          // 1-2 deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 5)
        } yield ()
      }


      "innerMiddle" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 7).transact

          _ <- unitOfWork {
            savepoint { outer =>
              for {
                _ <- Ns.int(count).query.get.map(_.head ==> 7)

                _ <- Ns.int_.<=(2).delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 5)

                _ <- savepoint { middle =>
                  for {
                    _ <- Ns.int_.<=(4).delete.transact
                    _ <- Ns.int(count).query.get.map(_.head ==> 3)

                    _ <- savepoint { inner =>
                      for {
                        _ <- Ns.int_.<=(6).delete.transact
                        _ <- Ns.int(count).query.get.map(_.head ==> 1)
                        _ = middle.rollback()
                        _ <- Ns.int(count).query.get.map(_.head ==> 5)
                      } yield ()
                    }
                    _ <- Ns.int(count).query.get.map(_.head ==> 5)
                  } yield ()
                }
                _ <- Ns.int(count).query.get.map(_.head ==> 5)
              } yield ()
            }
          }

          // 1-2 deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 5)
        } yield ()
      }


      "middleOuter" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 7).transact

          _ <- unitOfWork {
            savepoint { outer =>
              for {
                _ <- Ns.int(count).query.get.map(_.head ==> 7)

                _ <- Ns.int_.<=(2).delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 5)

                _ <- savepoint { middle =>
                  for {
                    _ <- Ns.int_.<=(4).delete.transact
                    _ <- Ns.int(count).query.get.map(_.head ==> 3)

                    _ <- savepoint { inner =>
                      for {
                        _ <- Ns.int_.<=(6).delete.transact
                        _ <- Ns.int(count).query.get.map(_.head ==> 1)
                      } yield ()
                    }
                    _ <- Ns.int(count).query.get.map(_.head ==> 1)
                    _ = outer.rollback()
                    _ <- Ns.int(count).query.get.map(_.head ==> 7)
                  } yield ()
                }
                _ <- Ns.int(count).query.get.map(_.head ==> 7)
              } yield ()
            }
          }

          // Nothing deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 7)
        } yield ()
      }


      "innerMiddleOuter" - types { implicit conn =>
        for {
          _ <- Ns.int.insert(1 to 7).transact

          _ <- unitOfWork {
            savepoint { outer =>
              for {
                _ <- Ns.int(count).query.get.map(_.head ==> 7)

                _ <- Ns.int_.<=(2).delete.transact
                _ <- Ns.int(count).query.get.map(_.head ==> 5)

                _ <- savepoint { middle =>
                  for {
                    _ <- Ns.int_.<=(4).delete.transact
                    _ <- Ns.int(count).query.get.map(_.head ==> 3)

                    _ <- savepoint { inner =>
                      for {
                        _ <- Ns.int_.<=(6).delete.transact
                        _ <- Ns.int(count).query.get.map(_.head ==> 1)
                        _ = outer.rollback()
                        _ <- Ns.int(count).query.get.map(_.head ==> 7)
                      } yield ()
                    }
                    _ <- Ns.int(count).query.get.map(_.head ==> 7)
                  } yield ()
                }
                _ <- Ns.int(count).query.get.map(_.head ==> 7)
              } yield ()
            }
          }

          // Nothing deleted
          _ <- Ns.int(count).query.get.map(_.head ==> 7)
        } yield ()
      }
    }
  }
}