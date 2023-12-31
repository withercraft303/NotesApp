package com.example.notes


import android.os.Build
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.UUID

@Entity(tableName="note_table")
data class NoteInfo(
    @PrimaryKey() val noteId: String,
    @ColumnInfo(name = "note_date") val noteDate: String,
    @ColumnInfo(name = "note_time") val noteTime: String,
    @ColumnInfo(name = "creation_user") val noteUser: String,
    @ColumnInfo(name = "note_starred") var noteStarred: Boolean = false,
    @ColumnInfo(name = "note_name") var noteName: String?,
    @ColumnInfo(name = "note_type") val noteType: Int?,
    //filepath
    @ColumnInfo(name = "primary_file") var primaryFile: String?,
    @ColumnInfo(name = "secondary_file") val secondaryFile: String?
){
    @Override
    override fun toString(): String {
        return "<${if(noteStarred){"★"}else{"✰"}}$noteName by $noteUser at $noteTime>\n"
    }

    companion object {
        val timeFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a")
        val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("MMM, dd yyyy")
        fun createEmptyNote(user: String, name: String?, type: Int): NoteInfo {
            val time = LocalDateTime.now()
            return NoteInfo(
                UUID.randomUUID().toString(),
                time.format(dateFormat),
                time.format(timeFormat),
                user,
                false,
                name?: "Untitled Note",
                type,
                null,
                null
            )
        }
    }
}

class NoteType {
    companion object {
        const val written = 0
        const val audio = 1
        const val drawing = 2
        val typeToString = arrayOf<String>("Text Note", "Audio Note", "Drawn Note")
    }
}
/* sample table info
id creation_time creation_user note_starred note_name
0 07,25,10:13 joe false classA
1 07,27,01:46 joe true plans
2 07,27,01:47 joe false plans2
3 06,13,05:31 joe false classA
4 03,20,03:00 joe true classA
5 07,25,07:55 bob false classA
6 07,25,10:13 bob true classA
 */