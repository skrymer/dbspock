package org.skrymer.dbunit.extras.dataset

import spock.lang.Specification

import java.nio.file.Path

class JsonDataSetSpec extends Specification {

    def 'getTableNames()' () {
        given: 'a json dataset file'
        def file = Path.of('src/test/groovy/org/skrymer/dbunit/extras/dataset/tables.json')
        def jsonDataSet = new JsonDataSet(file)

        when: 'getting table names'
        def tableNames = jsonDataSet.getTableNames()

        then: 'table names are returned'
        tableNames == ['Table1', 'Table2'] as String[]
    }

    def 'getTable()' () {
        given: 'a json dataset file'
        def file = Path.of('src/test/groovy/org/skrymer/dbunit/extras/dataset/tables.json')
        def jsonDataSet = new JsonDataSet(file)

        when: 'getting table'
        def table = jsonDataSet.getTable('Table1')

        then: 'table is returned with expected values'
        table.rowCount == 3
        table.getValue(0, 'column1') == "value1"
        table.getValue(0, 'column2') == 'value2'
        table.getValue(0, 'column3') == 'value3'
    }

    def 'getTable() with replacements' () {
        given: 'a json dataset file'
        def file = Path.of('src/test/groovy/org/skrymer/dbunit/extras/dataset/tables.json')
        def jsonDataSet = new JsonDataSet(file, [
                "{REPLACEMENT1}" : "replaced1",
                "{REPLACEMENT2}" : "replaced2",
                "{REPLACEMENT3}" : "replaced3"
        ])

        when: 'getting table'
        def table = jsonDataSet.getTable('Table2')

        then: 'table is returned with expected replaces values'
        table.rowCount == 1
        table.getValue(0, 'column1') == "replaced1"
        table.getValue(0, 'column2') == 'replaced2'
        table.getValue(0, 'column3') == 'replaced3'
    }

    def 'getTable() with default value when no replacement' () {
        given: 'a json dataset file'
        def file = Path.of('src/test/groovy/org/skrymer/dbunit/extras/dataset/tables.json')
        def jsonDataSet = new JsonDataSet(file)

        when: 'getting table'
        def table = jsonDataSet.getTable('Table2')

        then: 'table is returned with expected default values'
        table.rowCount == 1
        table.getValue(0, 'column1') == "default1"
        table.getValue(0, 'column2') == null // No default value
        table.getValue(0, 'column3') == 'default3'
    }

    // None string values, like null for replacement and default
    // configure placeholder wrapper, defaults to {}
    // configure default value prefix, defaults to ::
}
