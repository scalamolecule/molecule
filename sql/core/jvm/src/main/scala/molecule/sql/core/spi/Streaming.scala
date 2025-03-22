package molecule.sql.core.spi

import cats.effect.IO
import fs2.Stream
import molecule.core.action._
import molecule.core.spi._
import molecule.sql.core.javaSql.{ResultSetInterface => Row}
import zio._
import zio.stream._

trait Streaming {

  // Used by both async and io APIs
  def fs2stream[Tpl](
    q: Query[Tpl],
    chunkSize: Int,
    inspect: (Query[Tpl], Conn) => Unit,
    getRS: (Query[Tpl], Conn) => (Row, Row => Any)
  )(implicit conn: Conn): fs2.Stream[IO, Tpl] = {

    // Ensure the inspection runs in IO
    val inspectIO: IO[Unit] =
      if (q.doInspect) IO.blocking(inspect(q, conn))
      else IO.unit

    // Resource handling for Statement & ResultSet
    val acquire: IO[(Row, Row => Any)] = IO.blocking {
      val (row, row2tpl) = getRS(q, conn)
      row.setFetchSize(chunkSize)
      (row, row2tpl)
    }

    val release: ((Row, Row => Any)) => IO[Unit] = {
      case (rs, _) => IO.blocking(rs.close())
    }

    for {
      _ <- Stream.eval(inspectIO)
      (rs, row2tpl) <- Stream.bracket(acquire)(release)
      chunk <- Stream.unfoldEval(()) { _ =>
        IO.blocking {
          val buffer = new collection.mutable.ListBuffer[Tpl]()
          var count  = 0

          // Fetch up to `chunkSize` rows
          while (count < chunkSize && rs.next()) {
            buffer += row2tpl(rs).asInstanceOf[Tpl]
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
    getRS: (Query[Tpl], Conn) => (Row, Row => Any)
  )(implicit conn: Conn): ZStream[Any, Throwable, Tpl] = {

    // Ensure the inspection runs in ZIO
    val inspectZIO: UIO[Unit] =
      if (q.doInspect) ZIO.attemptBlocking(inspect(q, conn)).orDie
      else ZIO.unit

    // Resource handling for Statement & ResultSet
    val acquire: Task[(Row, Row => Any)] = ZIO.attemptBlocking {
      val (row, row2tpl) = getRS(q, conn)
      row.setFetchSize(chunkSize)
      (row, row2tpl)
    }

    val release: ((Row, Row => Any)) => UIO[Unit] = {
      case (rs, _) => ZIO.attemptBlocking(rs.close()).orDie
    }

    for {
      _ <- ZStream.fromZIO(inspectZIO)
      (rs, row2tpl) <- ZStream.acquireReleaseWith(acquire)(release)
      element <- ZStream.fromIterableZIO(
        ZIO.attemptBlocking {
          val buffer = new collection.mutable.ListBuffer[Tpl]()
          var count  = 0

          // Fetch up to `chunkSize` rows
          while (count < chunkSize && rs.next()) {
            buffer += row2tpl(rs).asInstanceOf[Tpl]
            count += 1
          }

          buffer.toList
        }, chunkSize)
    } yield element
  }
}