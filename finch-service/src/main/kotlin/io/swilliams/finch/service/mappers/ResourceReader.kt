package io.swilliams.finch.service.mappers

import io.swilliams.finch.service.model.Entry
import org.springframework.core.io.Resource
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

object ResourceReader {

    fun readBody(body: Resource): List<Entry> {
        val stream = BufferedReader(InputStreamReader(body.inputStream))
        val entries = mutableListOf<Entry>()
        var line: String?
        while (stream.readLine().also { line = it } != null) {
            entries.add(parseLine(line!!))
        }
        return entries
    }

    fun parseLine(line: String): Entry {
        val lineParts = line.split("|")
        return Entry(
            uuid = UUID.fromString(lineParts[0]),
            id = lineParts[1],
            name = lineParts[2],
            likes = lineParts[3],
            transport = lineParts[4],
            avgSpeed = lineParts[5].toFloat(),
            topSpeed = lineParts[6].toFloat(),
        )
    }
}
