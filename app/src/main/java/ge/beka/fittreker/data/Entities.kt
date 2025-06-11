package ge.beka.fittreker.data

import androidx.room.*
import java.util.Date

@Entity
data class Participant(
    @PrimaryKey(autoGenerate = true) val participant_id: Int = 0,
    val name: String
)

@Entity
data class Template(
    @PrimaryKey(autoGenerate = true) val template_id: Int = 0,
    val name: String,
    val description: String
)

@Entity(
    foreignKeys = [
        ForeignKey(entity = Template::class, parentColumns = ["template_id"], childColumns = ["template_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Exercise::class, parentColumns = ["exercise_id"], childColumns = ["exercise_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("template_id"), Index("exercise_id")]
)
data class TemplateExercise(
    @PrimaryKey(autoGenerate = true) val template_exercise_id: Int = 0,
    val template_id: Int,
    val exercise_id: Int,
    val sort_order: Int
)

@Entity
data class Session(
    @PrimaryKey(autoGenerate = true) val session_id: Int = 0,
    val date: Date,
    val template_id: Int
)

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true) val exercise_id: Int = 0,
    val name: String,
    val measurement_type: String
)

@Entity(
    foreignKeys = [
        ForeignKey(entity = Session::class, parentColumns = ["session_id"], childColumns = ["session_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Exercise::class, parentColumns = ["exercise_id"], childColumns = ["exercise_id"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = Participant::class, parentColumns = ["participant_id"], childColumns = ["participant_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("session_id"), Index("exercise_id"), Index("participant_id")]
)
data class SessionExercise(
    @PrimaryKey(autoGenerate = true) val session_exercise_id: Int = 0,
    val session_id: Int,
    val exercise_id: Int,
    val participant_id: Int,
    val sort_order: Int
)

@Entity(
    foreignKeys = [
        ForeignKey(entity = SessionExercise::class, parentColumns = ["session_exercise_id"], childColumns = ["session_exercise_id"], onDelete = ForeignKey.CASCADE)
    ],
    indices = [Index("session_exercise_id")]
)
data class SetEntity(
    @PrimaryKey(autoGenerate = true) val set_id: Int = 0,
    val session_exercise_id: Int,
    val set_number: Int,
    val reps: Int?,
    val weight: Float?,
    val duration_sec: Int?,
    val notes: String?
)

*** End of File ***
