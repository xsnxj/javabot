package javabot.model

import org.bson.types.ObjectId
import org.mongodb.morphia.annotations.Entity
import org.mongodb.morphia.annotations.Id
import org.mongodb.morphia.annotations.Indexed

import java.io.Serializable
import java.time.LocalDateTime

@Entity(value = "changes", noClassnameStored = true)
public class Change : Serializable, Persistent {

    @Id
    var id: ObjectId? = null
    var message: String? = null
    @Indexed(name = "changed")
    var changeDate: LocalDateTime? = null

    constructor() {
    }

    constructor(message: String, date: LocalDateTime? = LocalDateTime.now()) {
        this.message = message
        this.changeDate = date
    }
}