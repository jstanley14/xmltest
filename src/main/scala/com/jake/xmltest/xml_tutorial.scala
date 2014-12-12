package com.jake.xmltest

case class Song(title: String, length: String) {
  lazy val time = {
    val Array(minutes, seconds) = length.split(":")
    minutes.toInt*60 + seconds.toInt
  }
}

case class Album(title: String, songs: Seq[Song], description: String) {
  lazy val time = songs.map(_.time).sum
  lazy val length = (time / 60)+":"+(time % 60)
}

case class Artist(name: String, albums: Seq[Album])

object XMLTutorial {
  def run(args: Array[String]) {
    val musicElem = scala.xml.XML.loadFile("/tmp/music.xml")

    val songs = (musicElem \\ "song").map { song =>
      Song((song \ "@title").text, (song \ "@length").text)
    }

    val artists = (musicElem \ "artist").map { artist =>
      val name = (artist \ "@name").text
      val albums = (artist \ "album").map { album =>
        val title = (album \ "@title").text
        val description = (album \ "description").text
        val songList = (album \ "song").map { song =>
          Song((song \ "@title").text, (song \ "@length").text)
        }
        Album(title, songList, description)
      }
      Artist(name, albums)
    }

    val albumLengths = artists.flatMap { artist =>
      artist.albums.map(album => (artist.name, album.title, album.length))
    }

    albumLengths.foreach(println)
  }
}