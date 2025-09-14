package molecule.db.common.spi

import cats.effect.IO
import fs2.Stream
import molecule.core.error.{ExecutionError, MoleculeError}
import molecule.db.common.crud.Query
import molecule.db.common.javaSql.ResultSetInterface as RS
import molecule.db.common.spi.Conn
import zio.*
import zio.stream.*

trait StreamingJdbc {

  // Used by both async and io APIs
  def fs2stream[Tpl](
    q: Query[Tpl],
    chunkSize: Int,
    inspect: (Query[Tpl], Conn) => Unit,
    getResultSetAndResolver: (Query[Tpl], Conn) => (RS, RS => Any)
  )(using conn: Conn): fs2.Stream[IO, Tpl] = {

    // Ensure the inspection runs in IO
    val inspectIO: IO[Unit] =
      if (q.printInspect) IO.blocking(inspect(q, conn))
      else IO.unit

    // Resource handling for Statement & ResultSet
    val acquire: IO[(RS, RS => Any)] = IO.blocking {
      val (rs, rs2row) = getResultSetAndResolver(q, conn)
      rs.setFetchSize(chunkSize)
      (rs, rs2row)
    }

    val release: ((RS, RS => Any)) => IO[Unit] = {
      case (rs, _) => IO.blocking(rs.close())
    }

    for {
      _ <- Stream.eval(inspectIO)
      (rs, rs2row) <- Stream.bracket(acquire)(release)
      chunk <- Stream.unfoldEval(()) { _ =>
        IO.blocking {
          val buffer = new collection.mutable.ListBuffer[Tpl]()
          var count  = 0

          // Fetch up to `chunkSize` rows
          while (count < chunkSize && rs.next()) {
            buffer += rs2row(rs).asInstanceOf[Tpl]
            count += 1
          }

          if (buffer.isEmpty) None // End of stream
          else Some((buffer.toList, ())) // Yield chunk and continue
        }
      }
      element <- Stream.emits(chunk) // Flatten chunks into elements
    } yield element
  }


  def zioStream[Tpl](
    q: Query[Tpl],
    chunkSize: Int,
    inspect: (Query[Tpl], Conn) => Unit,
    getResultSetAndResolver: (Query[Tpl], Conn) => (RS, RS => Any)
  ): ZStream[Conn, MoleculeError, Tpl] = {

    def attemptBlockingMolecule[A](block: => A): ZIO[Any, MoleculeError, A] =
      ZIO.attemptBlocking(block).mapError {
        case e: MoleculeError => e
        case other            => ExecutionError(other.getMessage)
      }

    // Ensure the inspection runs in ZIO
    def inspectZIO(conn: Conn): ZIO[Any, MoleculeError, Unit] =
      if (q.printInspect)
        attemptBlockingMolecule(inspect(q, conn))
      else
        ZIO.unit

    // Resource handling for Statement & ResultSet
    def acquire(conn: Conn): ZIO[Any, MoleculeError, (RS, RS => Any)] =
      attemptBlockingMolecule {
        val (rs, rs2row) = getResultSetAndResolver(q, conn)
        rs.setFetchSize(chunkSize)
        (rs, rs2row)
      }

    val release: ((RS, RS => Any)) => UIO[Unit] = {
      case (rs, _) => ZIO.attemptBlocking(rs.close()).orDie
    }

    for {
      conn <- ZStream.fromZIO(ZIO.service[Conn])
      _ <- ZStream.fromZIO(inspectZIO(conn))
      (rs, rs2row) <- ZStream.acquireReleaseWith(acquire(conn))(release)
      element <- ZStream.fromIterableZIO(
        attemptBlockingMolecule {
          val buffer = new collection.mutable.ListBuffer[Tpl]()
          var count  = 0

          // Fetch up to `chunkSize` rows
          while (count < chunkSize && rs.next()) {
            buffer += rs2row(rs).asInstanceOf[Tpl]
            count += 1
          }

          buffer.toList
        }, chunkSize)
    } yield element
  }
}