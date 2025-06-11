package ge.beka.fittreker.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ParticipantDao {
    @Query("SELECT * FROM Participant")
    fun getAll(): Flow<List<Participant>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(participant: Participant)
}

@Dao
interface ExerciseDao {
    @Query("SELECT * FROM Exercise")
    fun getAll(): Flow<List<Exercise>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: Exercise)
}

@Dao
interface TemplateDao {
    @Query("SELECT * FROM Template")
    fun getAll(): Flow<List<Template>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(template: Template)
}

@Dao
interface SessionDao {
    @Query("SELECT * FROM Session ORDER BY date DESC")
    fun getAll(): Flow<List<Session>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(session: Session)
}

*** End of File ***
